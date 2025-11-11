package com.hm.tcpserver;

import com.hm.minlog.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TcpClient extends Retry {
   private String host;
   private int port;
   private int connectTimeout = 10000;
   private int readTimeout;
   private int reconnectDelay = 10000;
   volatile TcpClient.ClientConnection connection;
   private final Object waitForConnection = new Object();
   final Object waitForClose = new Object();

   public TcpClient(String category, String name) {
      this(category, name, "", 0);
   }

   public TcpClient(String category, String name, String host, int port) {
      super(category, name);
      this.host = host;
      this.port = port;
   }

   @Override
   protected void retry() {
      Socket socket = null;

      try {
         socket = new Socket();
         socket.connect(new InetSocketAddress(this.host, this.port), this.connectTimeout);
         socket.setSoTimeout(this.readTimeout);
         socket.setTcpNoDelay(false);
      } catch (Throwable var8) {
         if (this.isFirstTry()) {
            if (Log.ERROR) {
               Log.error(this.category, "Unable to connect: " + this.host + ":" + this.port);
            }
         } else if (Log.DEBUG) {
            Log.debug(this.category, "Unable to connect: " + this.host + ":" + this.port);
         }

         this.failed();
         return;
      }

      synchronized (this.runLock) {
         if (!this.running) {
            Util.closeQuietly(socket);
            return;
         }

         this.success();
         if (Log.INFO) {
            Log.info(this.category, "Connected: " + socket.getInetAddress() + ":" + socket.getPort());
         }

         try {
            this.connection = new TcpClient.ClientConnection(this.category, this.name, socket);
            this.connection.start();
         } catch (IOException var7) {
            this.connection = null;
            if (Log.ERROR) {
               Log.error(this.category, "Error configuring client connection.", var7);
            }

            this.failed();
            return;
         }

         this.connected(this.connection);
         synchronized (this.waitForConnection) {
            this.waitForConnection.notifyAll();
         }
      }

      this.waitForClose(0L);
      this.disconnected(this.connection);
   }

   @Override
   protected void stopped() {
      TcpClient.ClientConnection connection = this.connection;
      if (connection != null) {
         connection.close();
         this.connection = null;
      }
   }

   public boolean send(String message) {
      Connection connection = this.getConnection();
      if (connection == null) {
         if (Log.DEBUG) {
            Log.debug(this.category, "Unable to send, connection is closed: " + message);
         }

         return false;
      } else {
         connection.send(message);
         return true;
      }
   }

   public boolean send(String message, byte[] bytes) {
      return this.send(message, bytes, 0, bytes.length);
   }

   public boolean send(String message, byte[] bytes, int offset, int count) {
      Connection connection = this.getConnection();
      if (connection == null) {
         if (Log.DEBUG) {
            Log.debug(this.category, "Unable to send, connection is closed: " + message);
         }

         return false;
      } else {
         connection.send(message, bytes, offset, count);
         return true;
      }
   }

   public boolean sendBlocking(String message) {
      Connection connection = this.getConnection();
      if (connection == null) {
         if (Log.DEBUG) {
            Log.debug(this.category, "Unable to send, connection is closed: " + message);
         }

         return false;
      } else {
         return connection.sendBlocking(message);
      }
   }

   public boolean sendBlocking(String message, byte[] bytes) {
      return this.sendBlocking(message, bytes);
   }

   public boolean sendBlocking(String message, byte[] bytes, int offset, int count) {
      Connection connection = this.getConnection();
      if (connection == null) {
         if (Log.DEBUG) {
            Log.debug(this.category, "Unable to send, connection is closed: " + message);
         }

         return false;
      } else {
         return connection.sendBlocking(message, bytes, offset, count);
      }
   }

   public void connected(Connection connection) {
   }

   public void disconnected(Connection connection) {
   }

   public void receive(String event, String payload, byte[] bytes, int count) {
   }

   public Connection getConnection() {
      TcpClient.ClientConnection connection = this.connection;
      return connection != null && !connection.closed ? connection : null;
   }

   public boolean isConnected() {
      TcpClient.ClientConnection connection = this.connection;
      return connection != null && !connection.closed;
   }

   public boolean waitForConnection(long millis) {
      if (Log.TRACE) {
         Log.trace(this.category, "Waiting for connection.");
      }

      long until = System.currentTimeMillis() + millis;

      while (true) {
         synchronized (this.waitForConnection) {
            Connection connection = this.getConnection();
            if (connection != null) {
               return true;
            }

            long wait = 0L;
            if (millis > 0L) {
               wait = until - System.currentTimeMillis();
               if (wait < 0L) {
                  return false;
               }
            }

            try {
               this.waitForConnection.wait(wait);
            } catch (InterruptedException var11) {
            }
         }
      }
   }

   public boolean waitForClose(long millis) {
      if (Log.TRACE) {
         Log.trace(this.category, "Waiting for close.");
      }

      long until = System.currentTimeMillis() + millis;

      while (true) {
         synchronized (this.waitForClose) {
            Connection connection = this.getConnection();
            if (connection == null) {
               return true;
            }

            long wait = 0L;
            if (millis > 0L) {
               wait = until - System.currentTimeMillis();
               if (wait < 0L) {
                  return false;
               }
            }

            try {
               this.waitForClose.wait(wait);
            } catch (InterruptedException var11) {
            }
         }
      }
   }

   public String getHost() {
      return this.host;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public int getPort() {
      return this.port;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public int getConnectTimeout() {
      return this.connectTimeout;
   }

   public void setConnectTimeout(int millis) {
      this.connectTimeout = millis;
   }

   public int getReadTimeout() {
      return this.readTimeout;
   }

   public void setReadTimeout(int millis) {
      this.readTimeout = millis;
   }

   class ClientConnection extends Connection {
      public ClientConnection(String category, String name, Socket socket) throws IOException {
         super(category, name, socket);
      }

      @Override
      public void receive(String event, String payload, byte[] bytes, int count) {
         TcpClient.this.receive(event, payload, bytes, count);
      }

      @Override
      public void close() {
         super.close();
         TcpClient.this.connection = null;
         synchronized (TcpClient.this.waitForClose) {
            TcpClient.this.waitForClose.notifyAll();
         }
      }
   }
}
