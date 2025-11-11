package com.hm.tcpserver;

import com.hm.minlog.Log;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class TcpServer extends Retry {
   final CopyOnWriteArrayList connections = new CopyOnWriteArrayList();
   private int port;
   private ServerSocket server;

   public TcpServer(String category, String name) {
      this(category, name, 0);
   }

   public TcpServer(String category, String name, int port) {
      super(category, name);
      this.port = port;
   }

   @Override
   protected void retry() {
      try {
         this.server = new ServerSocket(this.port);
      } catch (Exception var8) {
         if (this.isFirstTry()) {
            if (Log.ERROR) {
               Log.error(this.category, "Unable to start TCP server.", var8);
            }
         } else if (Log.DEBUG) {
            Log.debug(this.category, "Unable to start TCP server.", var8);
         }

         this.failed();
         return;
      }

      try {
         if (Log.INFO) {
            Log.info(this.category, "Listening on port: TCP " + this.port);
         }
      } catch (Exception var6) {
         Util.closeQuietly(this.server);
         this.failed();
      } finally {
         if (Log.INFO) {
            Log.info(this.category, "Server stopped: TCP " + this.port);
         }
      }

      if (Log.INFO) {
         Log.info(this.category, "Server stopped: TCP " + this.port);
      }
   }

   @Override
   protected void stopped() {
      for (int i = this.connections.size() - 1; i >= 0; i--) {
         ((Connection)this.connections.get(i)).close();
      }

      this.connections.clear();
      Util.closeQuietly(this.server);
   }

   public void connected(Connection connection) {
   }

   public void disconnected(Connection connection) {
   }

   public List getConnections() {
      return this.connections;
   }

   public void send(String message) {
      for (Connection connection : this.connections) {
         connection.send(message);
      }
   }

   public void send(String message, byte[] bytes) {
      this.send(message, bytes, 0, bytes.length);
   }

   public void send(String message, byte[] bytes, int offset, int count) {
      for (Connection connection : this.connections) {
         connection.send(message, bytes, offset, count);
      }
   }

   public boolean sendBlocking(String message) {
      boolean success = true;

      for (Connection connection : this.connections) {
         if (!connection.sendBlocking(message)) {
            success = false;
         }
      }

      return success;
   }

   public boolean sendBlocking(String message, byte[] bytes) {
      return this.sendBlocking(message, bytes, 0, bytes.length);
   }

   public boolean sendBlocking(String message, byte[] bytes, int offset, int count) {
      boolean success = true;

      for (Connection connection : this.connections) {
         if (!connection.sendBlocking(message, bytes, offset, count)) {
            success = false;
         }
      }

      return success;
   }

   public abstract void receive(Connection var1, String var2, String var3, byte[] var4, int var5);

   public int getPort() {
      return this.port;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public static void main(String[] args) throws Exception {
      Log.TRACE();
      TcpServer server = new TcpServer("server", "TestServer", 4567) {
         @Override
         public void receive(Connection connection, String event, String payload, byte[] bytes, int count) {
            System.out.println("Server received: " + event + ", " + payload + ", " + count);
            this.send("ok good");
         }
      };
      server.start();
      TcpClient client = new TcpClient("client", "TestClient", "localhost", 4567) {
         @Override
         public void receive(String event, String payload, byte[] bytes, int count) {
            System.out.println("Client received: " + event + ", " + payload + ", " + count);
            this.getConnection().close();
         }
      };
      client.start();
      client.waitForConnection(0L);
      client.sendBlocking("yay moo");
   }

   private class ServerConnection extends Connection {
      public ServerConnection(String category, String name, Socket socket) throws IOException {
         super(category, name, socket);
      }

      public boolean isValid() {
         return TcpServer.this.connections.contains(this);
      }

      @Override
      public void receive(String event, String payload, byte[] bytes, int count) {
         TcpServer.this.receive(this, event, payload, bytes, count);
      }

      @Override
      public void close() {
         boolean wasClosed = this.closed;
         super.close();
         if (!wasClosed) {
            TcpServer.this.disconnected(this);
            TcpServer.this.connections.remove(this);
         }
      }
   }
}
