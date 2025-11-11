package com.esotericsoftware.gloomhavenhelper.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.gloomhavenhelper.App;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.tcpserver.Connection;
import com.esotericsoftware.tcpserver.TcpClient;

class GameClient extends TcpClient {
    public GameClient() {
        super("client", "Client");
        this.setDaemon(true);
        this.setRetryDelays(new int[]{1000, 1000, 3000, 3000, 3000, 3000, 5000});
    }

    @Override  // com.esotericsoftware.tcpserver.TcpClient
    public void connected(Connection connection0) {
        Network.broadcastClient.stop();
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                App.toast("Connected to server.");
            }
        });
    }

    @Override  // com.esotericsoftware.tcpserver.TcpClient
    public void disconnected(Connection connection0) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                App.toast("Disconnected from server.");
                GameClient.this.update();
            }
        });
    }

    @Override  // com.esotericsoftware.tcpserver.TcpClient
    public void receive(String s, String s1, byte[] arr_b, int v) {
        byte[] arr_b1 = new byte[v];
        System.arraycopy(arr_b, 0, arr_b1, 0, v);
        if(s.equals("s")) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    Network.loadState(arr_b1);
                }
            });
            return;
        }
        if(s.equals("r")) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    App.toast("Sorry, your change has been rejected.");
                }
            });
            return;
        }
        if(s.equals("v") && !s1.equals("8.4")) {
            App.config.client = false;
            this.update();
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    if(Log.WARN) {
                        Log.warn("client", "Server version does not match.\nClient: v" + App.majorMinor + "\nServer: v" + s1);
                    }
                    App.toast("Server version does not match.");
                    App.toast("Client: v8.4");
                    App.toast(("Server: v" + s1));
                    if(App.gloom.mainMenu.clientCheckbox != null) {
                        App.gloom.mainMenu.clientCheckbox.setProgrammaticChangeEvents(true);
                        App.gloom.mainMenu.clientCheckbox.setChecked(false);
                        App.gloom.mainMenu.clientCheckbox.setProgrammaticChangeEvents(false);
                    }
                }
            });
        }
    }

    @Override  // com.esotericsoftware.tcpserver.TcpClient
    protected void retry() {
        this.setHost(App.config.clientHost);
        this.setPort(App.config.clientPort);
        App.sleep(1000);
        this.setHost(App.config.clientHost);
        this.setPort(App.config.clientPort);
        super.retry();
    }

    public void update() {
        if(App.gloom.intro != null && App.gloom.intro.introPhase == -1) {
            String s = this.getHost();
            if(this.isRunning() && s != null && (!s.equals(App.config.clientHost) || this.getPort() != App.config.clientPort)) {
                this.setHost(App.config.clientHost);
                this.setPort(App.config.clientPort);
                App.game.thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized(Network.broadcastServer) {
                            Network.broadcastClient.stop();
                            GameClient.this.stop();
                            Gdx.graphics.requestRendering();
                            App.sleep(100);
                        }
                        GameClient.this.update();
                    }
                });
                return;
            }
            if(App.config.client) {
                if(!this.isRunning()) {
                    App.game.thread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized(Network.broadcastServer) {
                                App.sleep(100);
                                if(!Network.broadcastServer.isRunning()) {
                                    Network.broadcastClient.start();
                                }
                                if(!GameClient.this.isRunning()) {
                                    GameClient.this.start();
                                }
                            }
                            Gdx.graphics.requestRendering();
                        }
                    });
                    return;
                }
                if(!this.isConnected() && !Network.broadcastServer.isRunning()) {
                    App.game.thread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized(Network.broadcastServer) {
                                App.sleep(100);
                                if(!Network.broadcastServer.isRunning()) {
                                    Network.broadcastClient.start();
                                }
                            }
                        }
                    });
                }
            }
            else if(this.isRunning() || Network.broadcastServer.isRunning()) {
                App.game.thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized(Network.broadcastServer) {
                            Network.broadcastClient.stop();
                            if(GameClient.this.stop()) {
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

