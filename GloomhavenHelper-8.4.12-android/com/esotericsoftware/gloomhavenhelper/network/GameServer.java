package com.esotericsoftware.gloomhavenhelper.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.gloomhavenhelper.App;
import com.esotericsoftware.gloomhavenhelper.PlayerRow;
import com.esotericsoftware.gloomhavenhelper.model.AttackModifier;
import com.esotericsoftware.gloomhavenhelper.model.CharacterClass;
import com.esotericsoftware.gloomhavenhelper.util.Input;
import com.esotericsoftware.gloomhavenhelper.util.Serialization;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.tcpserver.Connection;
import com.esotericsoftware.tcpserver.TcpServer;

class GameServer extends TcpServer {
    public GameServer() {
        super("server", "Server");
        this.setDaemon(true);
        this.setRetryDelays(new int[]{1000, 3000, 5000});
    }

    @Override  // com.esotericsoftware.tcpserver.TcpServer
    public void connected(Connection connection0) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                App.toast("Client connected.");
            }
        });
        connection0.send(Message.version.value + " " + App.majorMinor);
        Gdx.app.postRunnable(() -> {
            Serialization.output.clear();
            Serialization.output.writeInt(App.state.changeNumber);
            Serialization.write(Serialization.output, App.state);
            byte[] arr_b1 = Network.combine(Serialization.output, null, 0);
            this.val$connection.send("s", arr_b1, 0, arr_b1.length);
        });

        class com.esotericsoftware.gloomhavenhelper.network.GameServer.2 implements Runnable {
            com.esotericsoftware.gloomhavenhelper.network.GameServer.2(Connection connection0) {
            }

            @Override
            public void run() {
                GameServer.this.sendState(this.val$connection, null, 0);
            }
        }

    }

    @Override  // com.esotericsoftware.tcpserver.TcpServer
    public void disconnected(Connection connection0) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                App.toast("Client disconnected.");
            }
        });
    }

    @Override  // com.esotericsoftware.tcpserver.TcpServer
    public void receive(Connection connection0, String s, String s1, byte[] arr_b, int v) {
        if(s.equals(Message.undo.value)) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    App.gloom.mainMenu.undoItem.setProgrammaticChangeEvents(true);
                    App.gloom.mainMenu.undoItem.toggle();
                    App.gloom.mainMenu.undoItem.setProgrammaticChangeEvents(false);
                }
            });
            return;
        }
        if(s.equals(Message.redo.value)) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    App.gloom.mainMenu.redoItem.setProgrammaticChangeEvents(true);
                    App.gloom.mainMenu.redoItem.toggle();
                    App.gloom.mainMenu.redoItem.setProgrammaticChangeEvents(false);
                }
            });
            return;
        }
        if(s.equals(Message.init.value)) {
            try {
                Input input0 = new Input(arr_b);
                CharacterClass characterClass0 = CharacterClass.valueOf(input0.readString());
                int v1 = input0.readInt(true);
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        for(Object object0: App.gloom.playerRows) {
                            PlayerRow playerRow0 = (PlayerRow)object0;
                            if(playerRow0.player.characterClass == characterClass0) {
                                playerRow0.player.init(v1);
                                App.state.changed();
                                return;
                            }
                            if(false) {
                                break;
                            }
                        }
                    }
                });
                return;
            }
            catch(Exception exception0) {
                connection0.close();
                throw new RuntimeException("Error parsing initiative data from client.", exception0);
            }
        }
        if(s.equals("s")) {
            byte[] arr_b1 = new byte[v];
            System.arraycopy(arr_b, 0, arr_b1, 0, v);
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    byte[] arr_b;
                    if(App.state.changeNumber + 1 != Input.readInt(arr_b, 0)) {
                        if(Log.WARN) {
                            Log.warn("server", "Rejected change: " + Input.readInt(arr_b, 0) + ", required: " + (App.state.changeNumber + 1));
                        }
                        AttackModifier attackModifier0 = App.state.attackModifier1();
                        if(attackModifier0 == null) {
                            arr_b = null;
                        }
                        else {
                            AttackModifier attackModifier1 = App.state.attackModifier2();
                            arr_b = new byte[]{98, ((byte)(attackModifier0.ordinal() + 1)), ((byte)(attackModifier1 == null ? 0 : attackModifier1.ordinal() + 1))};
                        }
                        Network.send(Message.rejected, null, 0, 0);
                        GameServer.this.sendState(connection0, arr_b, 0);
                        return;
                    }
                    try {
                        Network.loadState(arr_b1);
                    }
                    catch(Exception exception0) {
                        connection0.close();
                        throw new RuntimeException("Error parsing game state data from client.", exception0);
                    }
                    for(Object object0: GameServer.this.getConnections()) {
                        Connection connection0 = (Connection)object0;
                        if(connection0 != connection0) {
                            connection0.send("s", arr_b1, 0, arr_b1.length);
                        }
                    }
                }
            });
            return;
        }
        connection0.close();
        throw new RuntimeException("Invalid event: " + s);
    }

    @Override  // com.esotericsoftware.tcpserver.TcpServer
    protected void retry() {
        this.setPort(App.config.serverPort);
        App.sleep(1000);
        this.setPort(App.config.serverPort);
        super.retry();
    }

    // 检测为 Lambda 实现
    void sendState(Connection connection0, byte[] arr_b, int v) [...]

    public void update() {
        if(App.gloom.intro != null && App.gloom.intro.introPhase == -1) {
            if(this.isRunning() && this.getPort() != App.config.serverPort) {
                this.setPort(App.config.serverPort);
                App.game.thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized(Network.broadcastServer) {
                            Network.broadcastServer.stop();
                            GameServer.this.stop();
                            App.sleep(100);
                        }
                        Gdx.graphics.requestRendering();
                        GameServer.this.update();
                    }
                });
                return;
            }
            if(App.config.server) {
                if(!this.isRunning()) {
                    App.game.thread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized(Network.broadcastServer) {
                                App.sleep(100);
                                if(!GameServer.this.isRunning()) {
                                    GameServer.this.start();
                                }
                                if(!Network.broadcastServer.isRunning()) {
                                    Network.broadcastServer.start();
                                }
                            }
                            Gdx.graphics.requestRendering();
                        }
                    });
                }
            }
            else if(this.isRunning() || Network.broadcastServer.isRunning()) {
                App.game.thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized(Network.broadcastServer) {
                            Network.broadcastServer.stop();
                            if(GameServer.this.stop()) {
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

