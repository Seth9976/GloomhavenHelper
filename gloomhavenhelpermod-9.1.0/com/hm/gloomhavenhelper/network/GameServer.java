package com.hm.gloomhavenhelper.network;

import com.badlogic.gdx.Gdx;
import com.hm.gloomhavenhelper.App;
import com.hm.gloomhavenhelper.PlayerRow;
import com.hm.gloomhavenhelper.model.AttackModifier;
import com.hm.gloomhavenhelper.model.CharacterClass;
import com.hm.gloomhavenhelper.util.Input;
import com.hm.gloomhavenhelper.util.Serialization;
import com.hm.minlog.Log;
import com.hm.tcpserver.Connection;
import com.hm.tcpserver.TcpServer;

class GameServer extends TcpServer {
   public GameServer() {
      super("server", "Server");
      this.setDaemon(true);
      this.setRetryDelays(1000, 3000, 5000);
   }

   @Override
   protected void retry() {
      this.setPort(App.config.serverPort);
      App.sleep(1000);
      this.setPort(App.config.serverPort);
      super.retry();
   }

   @Override
   public void connected(final Connection connection) {
      Gdx.app.postRunnable(new Runnable() {
         @Override
         public void run() {
            App.toast("Client connected.");
         }
      });
      connection.send(Message.version.value + " " + App.majorMinor);
      Gdx.app.postRunnable(new Runnable() {
         @Override
         public void run() {
            GameServer.this.sendState(connection, (byte[])null, 0);
         }
      });
   }

   void sendState(Connection connection, byte[] animate, int offset) {
      Serialization.output.clear();
      Serialization.output.writeInt(App.state.changeNumber);
      Serialization.write(Serialization.output, App.state);
      byte[] bytes = Network.combine(Serialization.output, animate, offset);
      connection.send(Message.gameState.value, bytes, 0, bytes.length);
   }

   @Override
   public void disconnected(Connection connection) {
      Gdx.app.postRunnable(new Runnable() {
         @Override
         public void run() {
            App.toast("Client disconnected.");
         }
      });
   }

   @Override
   public void receive(final Connection connection, String event, String payload, byte[] bytes, int count) {
      if (event.equals(Message.undo.value)) {
         Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
               App.gloom.mainMenu.undoItem.setProgrammaticChangeEvents(true);
               App.gloom.mainMenu.undoItem.toggle();
               App.gloom.mainMenu.undoItem.setProgrammaticChangeEvents(false);
            }
         });
      } else if (event.equals(Message.redo.value)) {
         Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
               App.gloom.mainMenu.redoItem.setProgrammaticChangeEvents(true);
               App.gloom.mainMenu.redoItem.toggle();
               App.gloom.mainMenu.redoItem.setProgrammaticChangeEvents(false);
            }
         });
      } else if (event.equals(Message.init.value)) {
         try {
            Input input = new Input(bytes);
            final CharacterClass tempAnus = null;

            for (CharacterClass charClass : App.allClasses) {
               if (charClass.asString().equals(input.readString())) {
                  tempAnus = charClass;
                  break;
               }
            }

            final int init = input.readInt(true);
            Gdx.app.postRunnable(new Runnable() {
               @Override
               public void run() {
                  for (PlayerRow row : App.gloom.playerRows) {
                     if (row.player.characterClass == tempAnus) {
                        row.player.init(init);
                        App.state.changed();
                        break;
                     }
                  }
               }
            });
         } catch (Exception var10) {
            connection.close();
            throw new RuntimeException("Error parsing initiative data from client.", var10);
         }
      } else if (!event.equals(Message.gameState.value)) {
         connection.close();
         throw new RuntimeException("Invalid event: " + event);
      } else {
         final byte[] copy = new byte[count];
         System.arraycopy(bytes, 0, copy, 0, count);
         final int changeNumber = Input.readInt(bytes, 0);
         Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
               if (App.state.changeNumber + 1 != changeNumber) {
                  if (Log.WARN) {
                     Log.warn("server", "Rejected change: " + changeNumber + ", required: " + (App.state.changeNumber + 1));
                  }

                  byte[] animate = null;
                  AttackModifier attackModifier1 = App.state.attackModifier1();
                  if (attackModifier1 != null) {
                     AttackModifier attackModifier2 = App.state.attackModifier2();
                     animate = new byte[]{98, (byte)(attackModifier1.ordinal() + 1), (byte)(attackModifier2 == null ? 0 : attackModifier2.ordinal() + 1)};
                  }

                  Network.send(Message.rejected, null, 0, 0);
                  GameServer.this.sendState(connection, animate, 0);
               } else {
                  try {
                     Network.loadState(copy);
                  } catch (Exception var4) {
                     connection.close();
                     throw new RuntimeException("Error parsing game state data from client.", var4);
                  }

                  for (Connection other : GameServer.this.getConnections()) {
                     if (other != connection) {
                        other.send(Message.gameState.value, copy, 0, copy.length);
                     }
                  }
               }
            }
         });
      }
   }

   public void update() {
      if (App.gloom.intro != null && App.gloom.intro.introPhase == -1) {
         if (this.isRunning() && this.getPort() != App.config.serverPort) {
            this.setPort(App.config.serverPort);
            App.game.thread(new Runnable() {
               @Override
               public void run() {
                  synchronized (Network.broadcastServer) {
                     Network.broadcastServer.stop();
                     GameServer.this.stop();
                     App.sleep(100);
                  }

                  Gdx.graphics.requestRendering();
                  GameServer.this.update();
               }
            });
         } else {
            if (App.config.server) {
               if (!this.isRunning()) {
                  App.game.thread(new Runnable() {
                     @Override
                     public void run() {
                        synchronized (Network.broadcastServer) {
                           App.sleep(100);
                           if (!GameServer.this.isRunning()) {
                              GameServer.this.start();
                           }

                           if (!Network.broadcastServer.isRunning()) {
                              Network.broadcastServer.start();
                           }
                        }

                        Gdx.graphics.requestRendering();
                     }
                  });
               }
            } else if (this.isRunning() || Network.broadcastServer.isRunning()) {
               App.game.thread(new Runnable() {
                  @Override
                  public void run() {
                     synchronized (Network.broadcastServer) {
                        Network.broadcastServer.stop();
                        if (GameServer.this.stop()) {
                           Gdx.graphics.requestRendering();
                        }

                        App.sleep(100);
                     }
                  }
               });
            }
         }
      }
   }
}
