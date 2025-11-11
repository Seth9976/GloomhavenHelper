package com.hm.gloomhavenhelper.network;

import com.hm.gloomhavenhelper.App;
import com.hm.gloomhavenhelper.util.Input;
import com.hm.gloomhavenhelper.util.Output;
import com.hm.tcpserver.BroadcastClient;
import com.hm.tcpserver.BroadcastServer;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Network {
   static final GameServer gameServer = new GameServer();
   static final GameClient gameClient = new GameClient();
   static final BroadcastServer broadcastServer = new BroadcastServer("broadcast", "BroadcastServer", 58887) {
      private byte[] response;

      @Override
      protected byte[] responseBuffer(DatagramPacket packet) {
         Output.writeInt(this.response, BroadcastServer.prefix.length, App.config.serverPort);
         return this.response;
      }
   };
   static final BroadcastClient broadcastClient = new BroadcastClient("broadcast", "BroadcastClient", 58887) {
      private byte[] buffer;

      @Override
      protected void retry() {
         App.sleep(3000);
         if (this.running) {
            if (!App.config.server) {
               if (App.config.client) {
                  if (!Network.gameClient.isConnected()) {
                     super.retry();
                  }
               }
            }
         }
      }

      @Override
      protected byte[] receiveBuffer() {
         return this.buffer;
      }

      @Override
      protected void received(DatagramPacket packet) {
         try {
            if (App.config.server) {
               return;
            }

            if (!App.config.client) {
               return;
            }

            if (!Network.gameClient.isConnected()) {
               App.config.clientHost = packet.getAddress().getHostAddress();
               int port = Input.readInt(packet.getData(), BroadcastServer.prefix.length);
               if (port > 0) {
                  App.config.clientPort = port;
               }

               Network.gameClient.update();
               App.sleep(3000);
               return;
            }
         } finally {
            this.stop();
         }
      }
   };

   public static void update() {
      gameClient.update();
      gameServer.update();
   }

   public static void send(Message message, byte[] bytes, int offset, int count) {
      if (App.config.server) {
         gameServer.send(message.value, bytes, 0, count);
      } else {
         gameClient.send(message.value, bytes, 0, count);
      }
   }

   public static boolean clientRunning() {
      return gameClient.isRunning();
   }

   public static boolean clientIsConnected() {
      return gameClient.getConnection() != null;
   }

   public static boolean serverRunning() {
      return gameServer.isRunning();
   }

   public static boolean serverHasClient() {
      return gameServer.isRunning() && gameServer.getConnections().size() > 0;
   }

   public static String getHost() {
      return gameClient.getHost();
   }

   public static String getIPs() {
      StringBuilder buffer = new StringBuilder();

      try {
         Enumeration ifaces = NetworkInterface.getNetworkInterfaces();

         while (ifaces.hasMoreElements()) {
            NetworkInterface iface = (NetworkInterface)ifaces.nextElement();
            Enumeration addresses = iface.getInetAddresses();

            while (addresses.hasMoreElements()) {
               InetAddress address = (InetAddress)addresses.nextElement();
               if (!address.isLoopbackAddress() && address instanceof Inet4Address) {
                  buffer.append(address.getHostAddress());
                  buffer.append('\n');
               }
            }
         }
      } catch (Exception var5) {
      }

      if (buffer.length() > 0) {
         buffer.setLength(buffer.length() - 1);
      }

      return buffer.toString();
   }

   public static void loadState(byte[] bytes) {
      try {
         int position = App.game.loadState(bytes, true);
         int remaining = bytes[position] & 255;
         if (remaining > 0) {
            int i = bytes.length - remaining;
            switch (bytes[i]) {
               case 97:
                  App.animateAttackCard(App.readAttackModifier(bytes[i + 1]), App.readAttackModifier(bytes[i + 2]), App.readAttackModifier(bytes[i + 3]));
                  return;
               case 98:
                  App.setAttackCards(App.readAttackModifier(bytes[i + 1]), App.readAttackModifier(bytes[i + 2]));
                  return;
               default:
                  throw new RuntimeException();
            }
         }
      } catch (Exception var4) {
         throw new RuntimeException("Unable to read game state.", var4);
      }
   }

   public static byte[] combine(Output output, byte[] animate, int offset) {
      int count = output.position();
      byte[] copy = new byte[animate != null ? count + 1 + animate.length - offset : count + 1];
      System.arraycopy(output.getBuffer(), 0, copy, 0, count);
      if (animate != null) {
         copy[count] = (byte)(animate.length - offset);
         if (animate.length - offset > 255) {
            throw new RuntimeException();
         }

         System.arraycopy(animate, offset, copy, count + 1, animate.length - offset);
      }

      return copy;
   }
}
