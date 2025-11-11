package com.hm.tcpserver;

import com.hm.minlog.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class BroadcastServer extends Retry {
   public static final byte[] prefix = new byte[]{62, 126, -17, 61, 127, -16, 63, 125, -18};
   private int port;
   private DatagramSocket socket;
   private final byte[] buffer = new byte[prefix.length];

   public BroadcastServer(String category, String name) {
      this(category, name, 0);
   }

   public BroadcastServer(String category, String name, int port) {
      super(category, name);
      this.port = port;
   }

   @Override
   protected void retry() {
      try {
         this.socket = new DatagramSocket(this.port);
      } catch (Exception var6) {
         if (this.isFirstTry()) {
            if (Log.ERROR) {
               Log.error(this.category, "Unable to start broadcast server.", var6);
            }
         } else if (Log.DEBUG) {
            Log.debug(this.category, "Unable to start broadcast server.", var6);
         }

         this.failed();
         return;
      }

      this.success();

      try {
         if (Log.INFO) {
            Log.info(this.category, "Listening on port: UDP " + this.port);
         }

         byte[] ex = this.receiveBuffer();
      } catch (Exception var7) {
         if (Log.ERROR) {
            Log.error(this.category, "Unexpected broadcast server error.", var7);
         }

         Util.closeQuietly(this.socket);
         this.failed();
      } finally {
         if (Log.INFO) {
            Log.info(this.category, "Server stopped: UDP " + this.port);
         }
      }

      if (Log.INFO) {
         Log.info(this.category, "Server stopped: UDP " + this.port);
      }
   }

   protected byte[] receiveBuffer() {
      return this.buffer;
   }

   protected byte[] responseBuffer(DatagramPacket packet) {
      return this.buffer;
   }

   @Override
   protected void stopped() {
      Util.closeQuietly(this.socket);
   }

   public int getPort() {
      return this.port;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public static void main(String[] args) throws Exception {
      Log.TRACE();
      BroadcastServer server = new BroadcastServer("broadcast", "test", 53333);
      server.start();
      System.out.println(BroadcastClient.find(53333, 1000).getAddress());
   }
}
