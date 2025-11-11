package com.esotericsoftware.tcpserver;

import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TcpClient extends Retry {
    class ClientConnection extends Connection {
        public ClientConnection(String s, String s1, Socket socket0) throws IOException {
            super(s, s1, socket0);
        }

        @Override  // com.esotericsoftware.tcpserver.Connection
        public void close() {
            super.close();
            TcpClient.this.connection = null;
            synchronized(TcpClient.this.waitForClose) {
                TcpClient.this.waitForClose.notifyAll();
            }
        }

        @Override  // com.esotericsoftware.tcpserver.Connection
        public void receive(String s, String s1, byte[] arr_b, int v) {
            TcpClient.this.receive(s, s1, arr_b, v);
        }
    }

    private int connectTimeout;
    volatile ClientConnection connection;
    private String host;
    private int port;
    private int readTimeout;
    private int reconnectDelay;
    final Object waitForClose;
    private final Object waitForConnection;

    public TcpClient(String s, String s1) {
        this(s, s1, "", 0);
    }

    public TcpClient(String s, String s1, String s2, int v) {
        super(s, s1);
        this.connectTimeout = 10000;
        this.reconnectDelay = 10000;
        this.waitForConnection = new Object();
        this.waitForClose = new Object();
        this.host = s2;
        this.port = v;
    }

    public void connected(Connection connection0) {
    }

    public void disconnected(Connection connection0) {
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    // 去混淆评级： 低(20)
    public Connection getConnection() {
        return this.connection != null && !this.connection.closed ? this.connection : null;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public boolean isConnected() {
        return this.connection != null && !this.connection.closed;
    }

    public void receive(String s, String s1, byte[] arr_b, int v) {
    }

    @Override  // com.esotericsoftware.tcpserver.Retry
    protected void retry() {
        Socket socket0;
        try {
            socket0 = new Socket();
            socket0.connect(new InetSocketAddress(this.host, this.port), this.connectTimeout);
            socket0.setSoTimeout(this.readTimeout);
            socket0.setTcpNoDelay(false);
        }
        catch(Throwable unused_ex) {
            if(!this.isFirstTry()) {
                if(Log.DEBUG) {
                    Log.debug(this.category, "Unable to connect: " + this.host + ":" + this.port);
                }
            }
            else if(Log.ERROR) {
                Log.error(this.category, "Unable to connect: " + this.host + ":" + this.port);
            }
            this.failed();
            return;
        }
        synchronized(this.runLock) {
            if(!this.running) {
                Util.closeQuietly(socket0);
                return;
            }
            this.success();
            if(Log.INFO) {
                Log.info(this.category, "Connected: " + socket0.getInetAddress() + ":" + socket0.getPort());
            }
            try {
                this.connection = new ClientConnection(this, this.category, this.name, socket0);
                this.connection.start();
            }
            catch(IOException iOException0) {
                this.connection = null;
                if(Log.ERROR) {
                    Log.error(this.category, "Error configuring client connection.", iOException0);
                }
                this.failed();
                return;
            }
            this.connected(this.connection);
            synchronized(this.waitForConnection) {
                this.waitForConnection.notifyAll();
            }
        }
        this.waitForClose(0L);
        this.disconnected(this.connection);
    }

    public boolean send(String s) {
        Connection connection0 = this.getConnection();
        if(connection0 == null) {
            if(Log.DEBUG) {
                Log.debug(this.category, "Unable to send, connection is closed: " + s);
            }
            return false;
        }
        connection0.send(s);
        return true;
    }

    public boolean send(String s, byte[] arr_b) {
        return this.send(s, arr_b, 0, arr_b.length);
    }

    public boolean send(String s, byte[] arr_b, int v, int v1) {
        Connection connection0 = this.getConnection();
        if(connection0 == null) {
            if(Log.DEBUG) {
                Log.debug(this.category, "Unable to send, connection is closed: " + s);
            }
            return false;
        }
        connection0.send(s, arr_b, v, v1);
        return true;
    }

    public boolean sendBlocking(String s) {
        Connection connection0 = this.getConnection();
        if(connection0 == null) {
            if(Log.DEBUG) {
                Log.debug(this.category, "Unable to send, connection is closed: " + s);
            }
            return false;
        }
        return connection0.sendBlocking(s);
    }

    public boolean sendBlocking(String s, byte[] arr_b) {
        return this.sendBlocking(s, arr_b);
    }

    public boolean sendBlocking(String s, byte[] arr_b, int v, int v1) {
        Connection connection0 = this.getConnection();
        if(connection0 == null) {
            if(Log.DEBUG) {
                Log.debug(this.category, "Unable to send, connection is closed: " + s);
            }
            return false;
        }
        return connection0.sendBlocking(s, arr_b, v, v1);
    }

    public void setConnectTimeout(int v) {
        this.connectTimeout = v;
    }

    public void setHost(String s) {
        this.host = s;
    }

    public void setPort(int v) {
        this.port = v;
    }

    public void setReadTimeout(int v) {
        this.readTimeout = v;
    }

    @Override  // com.esotericsoftware.tcpserver.Retry
    protected void stopped() {
        ClientConnection tcpClient$ClientConnection0 = this.connection;
        if(tcpClient$ClientConnection0 != null) {
            tcpClient$ClientConnection0.close();
            this.connection = null;
        }
    }

    public boolean waitForClose(long v) {
        if(Log.TRACE) {
            Log.trace(this.category, "Waiting for close.");
        }
        long v1 = System.currentTimeMillis();
        while(true) {
            long v2 = 0L;
            synchronized(this.waitForClose) {
                if(this.getConnection() == null) {
                    return true;
                }
                if(v > 0L) {
                    long v4 = v1 + v - System.currentTimeMillis();
                    if(v4 < 0L) {
                        return false;
                    }
                    v2 = v4;
                }
                try {
                    this.waitForClose.wait(v2);
                }
                catch(InterruptedException unused_ex) {
                }
            }
        }
    }

    public boolean waitForConnection(long v) {
        if(Log.TRACE) {
            Log.trace(this.category, "Waiting for connection.");
        }
        long v1 = System.currentTimeMillis();
        while(true) {
            long v2 = 0L;
            synchronized(this.waitForConnection) {
                if(this.getConnection() != null) {
                    return true;
                }
                if(v > 0L) {
                    long v4 = v1 + v - System.currentTimeMillis();
                    if(v4 < 0L) {
                        return false;
                    }
                    v2 = v4;
                }
                try {
                    this.waitForConnection.wait(v2);
                }
                catch(InterruptedException unused_ex) {
                }
            }
        }
    }
}

