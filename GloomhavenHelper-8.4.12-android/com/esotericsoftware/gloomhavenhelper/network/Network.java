package com.esotericsoftware.gloomhavenhelper.network;

import com.esotericsoftware.gloomhavenhelper.App;
import com.esotericsoftware.gloomhavenhelper.GameConfig;
import com.esotericsoftware.gloomhavenhelper.util.Input;
import com.esotericsoftware.gloomhavenhelper.util.Output;
import com.esotericsoftware.tcpserver.BroadcastClient;
import com.esotericsoftware.tcpserver.BroadcastServer;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Network {
    static final BroadcastClient broadcastClient;
    static final BroadcastServer broadcastServer;
    static final GameClient gameClient;
    static final GameServer gameServer;

    static {
        Network.gameServer = new GameServer();
        Network.gameClient = new GameClient();
        Network.broadcastServer = new BroadcastServer("broadcast", "BroadcastServer", 0xE607) {
            private final byte[] response;

            {
                String s = "broadcast";  // 捕获的参数
                String s1 = "BroadcastServer";  // 捕获的参数
                int v = 0xE607;  // 捕获的参数
                this.response = new byte[BroadcastServer.prefix.length + 4];
                this.setDaemon(true);
                System.arraycopy(BroadcastServer.prefix, 0, this.response, 0, BroadcastServer.prefix.length);
            }

            @Override  // com.esotericsoftware.tcpserver.BroadcastServer
            protected byte[] responseBuffer(DatagramPacket datagramPacket0) {
                Output.writeInt(this.response, BroadcastServer.prefix.length, App.config.serverPort);
                return this.response;
            }
        };
        Network.broadcastClient = new BroadcastClient("broadcast", "BroadcastClient", 0xE607) {
            private final byte[] buffer;

            {
                String s = "broadcast";  // 捕获的参数
                String s1 = "BroadcastClient";  // 捕获的参数
                int v = 0xE607;  // 捕获的参数
                this.buffer = new byte[BroadcastServer.prefix.length + 4];
                this.setDaemon(true);
                this.setRetryDelays(new int[]{3});
            }

            @Override  // com.esotericsoftware.tcpserver.BroadcastClient
            protected byte[] receiveBuffer() {
                return this.buffer;
            }

            @Override  // com.esotericsoftware.tcpserver.BroadcastClient
            protected void received(DatagramPacket datagramPacket0) {
                if(App.config.server) {
                    this.stop();
                    return;
                }
                if(!App.config.client) {
                    this.stop();
                    return;
                }
                try {
                    if(!Network.gameClient.isConnected()) {
                        GameConfig gameConfig0 = App.config;
                        gameConfig0.clientHost = datagramPacket0.getAddress().getHostAddress();
                        int v = Input.readInt(datagramPacket0.getData(), BroadcastServer.prefix.length);
                        if(v > 0) {
                            App.config.clientPort = v;
                        }
                        Network.gameClient.update();
                        App.sleep(3000);
                        goto label_18;
                    }
                    goto label_20;
                }
                catch(Throwable throwable0) {
                    this.stop();
                    throw throwable0;
                }
            label_18:
                this.stop();
                return;
            label_20:
                this.stop();
            }

            @Override  // com.esotericsoftware.tcpserver.BroadcastClient
            protected void retry() {
                App.sleep(3000);
                if(!this.running) {
                    return;
                }
                if(App.config.server) {
                    return;
                }
                if(!App.config.client) {
                    return;
                }
                if(Network.gameClient.isConnected()) {
                    return;
                }
                super.retry();
            }
        };
    }

    public static boolean clientIsConnected() [...] // 潜在的解密器

    public static boolean clientRunning() [...] // 潜在的解密器

    public static byte[] combine(Output output0, byte[] arr_b, int v) {
        int v1 = output0.position();
        byte[] arr_b1 = new byte[(arr_b == null ? v1 + 1 : v1 + 1 + arr_b.length - v)];
        System.arraycopy(output0.getBuffer(), 0, arr_b1, 0, v1);
        if(arr_b != null) {
            arr_b1[v1] = (byte)(arr_b.length - v);
            if(arr_b.length - v > 0xFF) {
                throw new RuntimeException();
            }
            System.arraycopy(arr_b, v, arr_b1, v1 + 1, arr_b.length - v);
            return arr_b1;
        }
        return arr_b1;
    }

    // 去混淆评级： 低(20)
    public static String getHost() {
        return "";
    }

    public static String getIPs() {
        StringBuilder stringBuilder0 = new StringBuilder();
        try {
            Enumeration enumeration0 = NetworkInterface.getNetworkInterfaces();
            while(enumeration0.hasMoreElements()) {
                Enumeration enumeration1 = ((NetworkInterface)enumeration0.nextElement()).getInetAddresses();
                while(enumeration1.hasMoreElements()) {
                    InetAddress inetAddress0 = (InetAddress)enumeration1.nextElement();
                    if(!inetAddress0.isLoopbackAddress() && inetAddress0 instanceof Inet4Address) {
                        stringBuilder0.append(inetAddress0.getHostAddress());
                        stringBuilder0.append('\n');
                    }
                }
            }
        }
        catch(Exception unused_ex) {
        }
        if(stringBuilder0.length() > 0) {
            stringBuilder0.setLength(stringBuilder0.length() - 1);
        }
        return stringBuilder0.toString();
    }

    public static void loadState(byte[] arr_b) {
        try {
            int v = arr_b[App.game.loadState(arr_b, true)] & 0xFF;
            if(v > 0) {
                int v1 = arr_b.length - v;
                switch(arr_b[v1]) {
                    case 97: {
                        App.animateAttackCard(App.readAttackModifier(arr_b[v1 + 1]), App.readAttackModifier(arr_b[v1 + 2]), App.readAttackModifier(arr_b[v1 + 3]));
                        break;
                    }
                    case 98: {
                        App.setAttackCards(App.readAttackModifier(arr_b[v1 + 1]), App.readAttackModifier(arr_b[v1 + 2]));
                        return;
                    }
                    default: {
                        throw new RuntimeException();
                    }
                }
            }
        }
        catch(Exception exception0) {
            throw new RuntimeException("Unable to read game state.", exception0);
        }
    }

    public static void send(Message message0, byte[] arr_b, int v, int v1) {
        if(App.config.server) {
            Network.gameServer.send(message0.value, arr_b, 0, v1);
            return;
        }
        Network.gameClient.send(message0.value, arr_b, 0, v1);
    }

    public static boolean serverHasClient() [...] // 潜在的解密器

    public static boolean serverRunning() [...] // 潜在的解密器

    public static void update() {
        Network.gameClient.update();
        Network.gameServer.update();
    }
}

