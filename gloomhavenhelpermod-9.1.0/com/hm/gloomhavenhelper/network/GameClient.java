package com.hm.gloomhavenhelper.network;

import com.badlogic.gdx.Gdx;
import com.hm.gloomhavenhelper.App;
import com.hm.minlog.Log;
import com.hm.tcpserver.Connection;
import com.hm.tcpserver.TcpClient;

class GameClient extends TcpClient {
   public GameClient() {
      super("client", "Client");
      this.setDaemon(true);
      this.setRetryDelays(1000, 1000, 3000, 3000, 3000, 3000, 5000);
   }

   @Override
   protected void retry() {
      this.setHost(App.config.clientHost);
      this.setPort(App.config.clientPort);
      App.sleep(1000);
      this.setHost(App.config.clientHost);
      this.setPort(App.config.clientPort);
      super.retry();
   }

   @Override
   public void connected(Connection connection) {
      Network.broadcastClient.stop();
      Gdx.app.postRunnable(new Runnable() {
         @Override
         public void run() {
            App.toast("Connected to server.");
         }
      });
   }

   @Override
   public void disconnected(Connection connection) {
      Gdx.app.postRunnable(new Runnable() {
         @Override
         public void run() {
            App.toast("Disconnected from server.");
            GameClient.this.update();
         }
      });
   }

   @Override
   public void receive(String event, final String payload, byte[] bytes, int count) {
      final byte[] copy = new byte[count];
      System.arraycopy(bytes, 0, copy, 0, count);
      if (event.equals(Message.gameState.value)) {
         Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
               Network.loadState(copy);
            }
         });
      } else if (event.equals(Message.rejected.value)) {
         Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
               App.toast("Sorry, your change has been rejected.");
            }
         });
      } else if (event.equals(Message.version.value) && !payload.equals(App.majorMinor)) {
         App.config.client = false;
         this.update();
         Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
               if (Log.WARN) {
                  Log.warn("client", "Server version does not match.\nClient: v" + App.majorMinor + "\nServer: v" + payload);
               }

               App.toast("Server version does not match.");
               App.toast("Client: v" + App.majorMinor);
               App.toast("Server: v" + payload);
               if (App.gloom.mainMenu.clientCheckbox != null) {
                  App.gloom.mainMenu.clientCheckbox.setProgrammaticChangeEvents(true);
                  App.gloom.mainMenu.clientCheckbox.setChecked(false);
                  App.gloom.mainMenu.clientCheckbox.setProgrammaticChangeEvents(false);
               }
            }
         });
      }
   }

   public void update() {
      if (App.gloom.intro != null && App.gloom.intro.introPhase == -1) {
         String host = this.getHost();
         if (!this.isRunning() || host == null || host.equals(App.config.clientHost) && this.getPort() == App.config.clientPort) {
            if (!App.config.client) {
               if (this.isRunning() || Network.broadcastServer.isRunning()) {
                  App.game.thread(new Runnable() {
                     @Override
                     public void run() {
                        synchronized (Network.broadcastServer) {
                           Network.broadcastClient.stop();
                           if (GameClient.this.stop()) {
                              Gdx.graphics.requestRendering();
                           }

                           App.sleep(100);
                        }
                     }
                  });
               }
            } else if (!this.isRunning()) {
               App.game.thread(new Runnable() {
                  @Override
                  public void run() {
                     synchronized (Network.broadcastServer) {
                        App.sleep(100);
                        if (!Network.broadcastServer.isRunning()) {
                           Network.broadcastClient.start();
                        }

                        if (!GameClient.this.isRunning()) {
                           GameClient.this.start();
                        }
                     }

                     Gdx.graphics.requestRendering();
                  }
               });
            } else if (!this.isConnected() && !Network.broadcastServer.isRunning()) {
               App.game.thread(new Runnable() {
                  @Override
                  public void run() {
                     synchronized (Network.broadcastServer) {
                        App.sleep(100);
                        if (!Network.broadcastServer.isRunning()) {
                           Network.broadcastClient.start();
                        }
                     }
                  }
               });
            }
         } else {
            this.setHost(App.config.clientHost);
            this.setPort(App.config.clientPort);
            App.game.thread(new Runnable() {
               @Override
               public void run() {
                  synchronized (Network.broadcastServer) {
                     Network.broadcastClient.stop();
                     GameClient.this.stop();
                     Gdx.graphics.requestRendering();
                     App.sleep(100);
                  }

                  GameClient.this.update();
               }
            });
         }
      }
   }
}
