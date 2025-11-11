package com.hm.tcpserver;

import com.hm.minlog.Log;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.util.Enumeration;

public class BroadcastClient extends Retry {
   private int port;
   private int timeoutMillis = 3000;
   private final byte[] receive = new byte[BroadcastServer.prefix.length];
   private final byte[] request = new byte[BroadcastServer.prefix.length];

   public BroadcastClient(String category, String name) {
      this(category, name, 0);
   }

   public BroadcastClient(String category, String name, int port) {
      super(category, name);
      this.port = port;
      this.setRetryDelays(6);
   }

   @Override
   protected void retry() {
      DatagramPacket packet = find(this.port, this.timeoutMillis, this.requestBuffer(), this.receiveBuffer());
      if (this.running && packet != null) {
         this.received(packet);
      }

      this.failed();
   }

   protected byte[] requestBuffer() {
      return this.request;
   }

   protected byte[] receiveBuffer() {
      return this.receive;
   }

   protected void received(DatagramPacket packet) {
   }

   public int getPort() {
      return this.port;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public int getTimeout() {
      return this.timeoutMillis;
   }

   public void setTimeout(int millis) {
      this.timeoutMillis = millis;
   }

   public static DatagramPacket find(int port, int timeoutMillis) {
      byte[] request = new byte[BroadcastServer.prefix.length + 4];
      byte[] receive = new byte[BroadcastServer.prefix.length];
      return find(port, timeoutMillis, request, receive);
   }

   public static DatagramPacket find(int port, int timeoutMillis, byte[] requestBuffer, byte[] receiveBuffer) {
      System.arraycopy(BroadcastServer.prefix, 0, requestBuffer, 0, BroadcastServer.prefix.length);
      DatagramSocket socket = null;

      Object var25;
      try {
         socket = new DatagramSocket();
         Enumeration ifaces = NetworkInterface.getNetworkInterfaces();

         while (ifaces.hasMoreElements()) {
            NetworkInterface iface = (NetworkInterface)ifaces.nextElement();
            Enumeration addresses = iface.getInetAddresses();

            while (addresses.hasMoreElements()) {
               byte[] ip = ((InetAddress)addresses.nextElement()).getAddress();
               ip[3] = -1;

               try {
                  socket.send(new DatagramPacket(requestBuffer, requestBuffer.length, InetAddress.getByAddress(ip), port));
               } catch (Exception var17) {
               }

               ip[2] = -1;

               try {
                  socket.send(new DatagramPacket(requestBuffer, requestBuffer.length, InetAddress.getByAddress(ip), port));
               } catch (Exception var16) {
               }
            }
         }

         if (Log.DEBUG) {
            Log.debug("broadcast", "Broadcasted on port: UDP " + port);
         }

         socket.setSoTimeout(timeoutMillis);
         DatagramPacket packet = new DatagramPacket(receiveBuffer, receiveBuffer.length);

         label215: {
            try {
               socket.receive(packet);
            } catch (SocketTimeoutException var18) {
               if (Log.DEBUG) {
                  Log.debug("broadcast", "Host discovery timed out.");
               }
               break label215;
            }

            int i = 0;

            for (int n = BroadcastServer.prefix.length; i < n; i++) {
               if (receiveBuffer[i] != BroadcastServer.prefix[i]) {
                  if (Log.DEBUG) {
                     StringBuilder buffer = new StringBuilder();

                     for (int var24 = 0; var24 < n; var24++) {
                        buffer.append(Integer.toHexString(receiveBuffer[var24]) + " ");
                     }

                     buffer.setLength(buffer.length() - 1);
                     Log.debug("broadcast", "Client received invalid packet, prefix: " + buffer);
                  }

                  return null;
               }
            }

            if (Log.INFO) {
               Log.info("broadcast", "Discovered server: " + packet.getAddress());
            }

            return packet;
         }

         var25 = null;
      } catch (IOException var19) {
         if (Log.ERROR) {
            Log.error("broadcast", "Host discovery failed.", var19);
         }

         return null;
      } finally {
         Util.closeQuietly(socket);
      }

      return (DatagramPacket)var25;
   }
}
