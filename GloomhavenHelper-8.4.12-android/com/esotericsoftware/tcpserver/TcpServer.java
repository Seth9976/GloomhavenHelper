package com.esotericsoftware.tcpserver;

import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class TcpServer extends Retry {
    class ServerConnection extends Connection {
        public ServerConnection(String s, String s1, Socket socket0) throws IOException {
            super(s, s1, socket0);
        }

        @Override  // com.esotericsoftware.tcpserver.Connection
        public void close() {
            boolean z = this.closed;
            super.close();
            if(!z) {
                TcpServer.this.disconnected(this);
                TcpServer.this.connections.remove(this);
            }
        }

        public boolean isValid() {
            return TcpServer.this.connections.contains(this);
        }

        @Override  // com.esotericsoftware.tcpserver.Connection
        public void receive(String s, String s1, byte[] arr_b, int v) {
            TcpServer.this.receive(this, s, s1, arr_b, v);
        }
    }

    final CopyOnWriteArrayList connections;
    private int port;
    private ServerSocket server;

    public TcpServer(String s, String s1) {
        this(s, s1, 0);
    }

    public TcpServer(String s, String s1, int v) {
        super(s, s1);
        this.connections = new CopyOnWriteArrayList();
        this.port = v;
    }

    public void connected(Connection connection0) {
    }

    public void disconnected(Connection connection0) {
    }

    public List getConnections() {
        return this.connections;
    }

    public int getPort() {
        return this.port;
    }

    public static void main(String[] arr_s) throws Exception {
        Log.TRACE();
        new TcpServer("server", "TestServer", 4567) {
            @Override  // com.esotericsoftware.tcpserver.TcpServer
            public void receive(Connection connection0, String s, String s1, byte[] arr_b, int v) {
                System.out.println("Server received: " + s + ", " + s1 + ", " + v);
                this.send("ok good");
            }
        }.start();
        com.esotericsoftware.tcpserver.TcpServer.2 tcpServer$20 = new TcpClient("client", "TestClient", "localhost", 4567) {
            @Override  // com.esotericsoftware.tcpserver.TcpClient
            public void receive(String s, String s1, byte[] arr_b, int v) {
                System.out.println("Client received: " + s + ", " + s1 + ", " + v);
                this.getConnection().close();
            }
        };
        tcpServer$20.start();
        tcpServer$20.waitForConnection(0L);
        tcpServer$20.sendBlocking("yay moo");
    }

    public abstract void receive(Connection arg1, String arg2, String arg3, byte[] arg4, int arg5);

    @Override  // com.esotericsoftware.tcpserver.Retry
    protected void retry() {
        Socket socket0;
        try {
            this.server = new ServerSocket(this.port);
        }
        catch(Exception exception0) {
            if(!this.isFirstTry()) {
                if(Log.DEBUG) {
                    Log.debug(this.category, "Unable to start TCP server.", exception0);
                }
            }
            else if(Log.ERROR) {
                Log.error(this.category, "Unable to start TCP server.", exception0);
            }
            this.failed();
            return;
        }
        try {
            if(Log.INFO) {
                Log.info(this.category, "Listening on port: TCP " + this.port);
            }
            while(true) {
            label_13:
                if(!this.running) {
                    goto label_47;
                }
                try {
                    socket0 = this.server.accept();
                    goto label_22;
                }
                catch(SocketException socketException0) {
                }
                if(this.running) {
                    throw socketException0;
                }
                break;
            }
        }
        catch(Exception exception1) {
            goto label_35;
        }
        catch(Throwable throwable0) {
            goto label_44;
        }
        if(Log.INFO) {
            Log.info(this.category, "Server stopped: TCP " + this.port);
        }
        return;
        try {
            try {
            label_22:
                this.success();
            }
            catch(Exception exception1) {
                goto label_35;
            }
            try {
                ServerConnection tcpServer$ServerConnection0 = new ServerConnection(this, this.category, this.name, socket0);
                this.connections.add(tcpServer$ServerConnection0);
                if(Log.INFO) {
                    Log.info(this.category, "Client connected: " + socket0.getInetAddress() + ":" + socket0.getPort());
                }
                tcpServer$ServerConnection0.start();
                this.connected(tcpServer$ServerConnection0);
                goto label_13;
            }
            catch(Exception exception2) {
                try {
                    if(!Log.ERROR) {
                        goto label_13;
                    }
                    Log.error(this.category, "Error configuring client connection.", exception2);
                    goto label_13;
                }
                catch(Exception exception1) {
                }
            }
        label_35:
            if(Log.ERROR) {
                Log.error(this.category, "Unexpected server error.", exception1);
            }
            Util.closeQuietly(this.server);
            this.failed();
            if(Log.INFO) {
                goto label_40;
            }
            return;
        }
        catch(Throwable throwable0) {
            goto label_44;
        }
    label_40:
        String s = this.category;
        Log.info(s, "Server stopped: TCP " + this.port);
        return;
    label_44:
        if(Log.INFO) {
            Log.info(this.category, "Server stopped: TCP " + this.port);
        }
        throw throwable0;
    label_47:
        if(Log.INFO) {
            s = this.category;
            Log.info(s, "Server stopped: TCP " + this.port);
        }
    }

    public void send(String s) {
        for(Object object0: this.connections) {
            ((Connection)object0).send(s);
        }
    }

    public void send(String s, byte[] arr_b) {
        this.send(s, arr_b, 0, arr_b.length);
    }

    public void send(String s, byte[] arr_b, int v, int v1) {
        for(Object object0: this.connections) {
            ((Connection)object0).send(s, arr_b, v, v1);
        }
    }

    public boolean sendBlocking(String s) {
        boolean z = true;
        for(Object object0: this.connections) {
            if(!((Connection)object0).sendBlocking(s)) {
                z = false;
            }
        }
        return z;
    }

    public boolean sendBlocking(String s, byte[] arr_b) {
        return this.sendBlocking(s, arr_b, 0, arr_b.length);
    }

    public boolean sendBlocking(String s, byte[] arr_b, int v, int v1) {
        boolean z = true;
        for(Object object0: this.connections) {
            if(!((Connection)object0).sendBlocking(s, arr_b, v, v1)) {
                z = false;
            }
        }
        return z;
    }

    public void setPort(int v) {
        this.port = v;
    }

    @Override  // com.esotericsoftware.tcpserver.Retry
    protected void stopped() {
        for(int v = this.connections.size() - 1; v >= 0; --v) {
            ((Connection)this.connections.get(v)).close();
        }
        this.connections.clear();
        Util.closeQuietly(this.server);
    }
}

