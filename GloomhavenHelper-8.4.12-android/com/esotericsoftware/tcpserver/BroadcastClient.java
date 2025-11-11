package com.esotericsoftware.tcpserver;

import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.util.Enumeration;

public class BroadcastClient extends Retry {
    private int port;
    private final byte[] receive;
    private final byte[] request;
    private int timeoutMillis;

    public BroadcastClient(String s, String s1) {
        this(s, s1, 0);
    }

    public BroadcastClient(String s, String s1, int v) {
        super(s, s1);
        this.timeoutMillis = 3000;
        this.receive = new byte[BroadcastServer.prefix.length];
        this.request = new byte[BroadcastServer.prefix.length];
        this.port = v;
        this.setRetryDelays(new int[]{6});
    }

    public static DatagramPacket find(int v, int v1) {
        return BroadcastClient.find(v, v1, new byte[BroadcastServer.prefix.length + 4], new byte[BroadcastServer.prefix.length]);
    }

    public static DatagramPacket find(int v, int v1, byte[] arr_b, byte[] arr_b1) {
        DatagramPacket datagramPacket0;
        byte[] arr_b2;
        DatagramSocket datagramSocket0;
        System.arraycopy(BroadcastServer.prefix, 0, arr_b, 0, BroadcastServer.prefix.length);
        try {
            datagramSocket0 = null;
            datagramSocket0 = new DatagramSocket();
            Enumeration enumeration0 = NetworkInterface.getNetworkInterfaces();
        alab1:
            while(true) {
                if(!enumeration0.hasMoreElements()) {
                    goto label_18;
                }
                Enumeration enumeration1 = ((NetworkInterface)enumeration0.nextElement()).getInetAddresses();
            label_8:
                while(enumeration1.hasMoreElements()) {
                    arr_b2 = ((InetAddress)enumeration1.nextElement()).getAddress();
                    arr_b2[3] = -1;
                    break alab1;
                }
            }
        }
        catch(IOException iOException0) {
            goto label_47;
        }
        finally {
            Util.closeQuietly(datagramSocket0);
        }
        try {
            InetAddress inetAddress0 = InetAddress.getByAddress(arr_b2);
            datagramSocket0.send(new DatagramPacket(arr_b, arr_b.length, inetAddress0, v));
        }
        catch(Exception unused_ex) {
        }
        try {
            arr_b2[2] = -1;
        }
        catch(IOException iOException0) {
            goto label_47;
        }
        try {
            InetAddress inetAddress1 = InetAddress.getByAddress(arr_b2);
            datagramSocket0.send(new DatagramPacket(arr_b, arr_b.length, inetAddress1, v));
        }
        catch(Exception unused_ex) {
        }
        goto label_8;
        try {
        label_18:
            if(Log.DEBUG) {
                Log.debug("broadcast", "Broadcasted on port: UDP " + v);
            }
            datagramSocket0.setSoTimeout(v1);
            datagramPacket0 = new DatagramPacket(arr_b1, arr_b1.length);
            try {
                datagramSocket0.receive(datagramPacket0);
            }
            catch(SocketTimeoutException unused_ex) {
                if(Log.DEBUG) {
                    Log.debug("broadcast", "Host discovery timed out.");
                }
                return null;
            }
            for(int v4 = 0; v4 < BroadcastServer.prefix.length; ++v4) {
                if(arr_b1[v4] != BroadcastServer.prefix[v4]) {
                    if(Log.DEBUG) {
                        StringBuilder stringBuilder0 = new StringBuilder();
                        for(int v2 = 0; v2 < BroadcastServer.prefix.length; ++v2) {
                            stringBuilder0.append(Integer.toHexString(arr_b1[v2]) + " ");
                        }
                        stringBuilder0.setLength(stringBuilder0.length() - 1);
                        Log.debug("broadcast", "Client received invalid packet, prefix: " + stringBuilder0);
                    }
                    return null;
                }
            }
            if(Log.INFO) {
                Log.info("broadcast", "Discovered server: " + datagramPacket0.getAddress());
            }
            return datagramPacket0;
        }
        catch(IOException iOException0) {
        label_47:
            if(Log.ERROR) {
                Log.error("broadcast", "Host discovery failed.", iOException0);
            }
            return null;
        }
    }

    public int getPort() {
        return this.port;
    }

    public int getTimeout() {
        return this.timeoutMillis;
    }

    protected byte[] receiveBuffer() {
        return this.receive;
    }

    protected void received(DatagramPacket datagramPacket0) {
    }

    protected byte[] requestBuffer() {
        return this.request;
    }

    @Override  // com.esotericsoftware.tcpserver.Retry
    protected void retry() {
        DatagramPacket datagramPacket0 = BroadcastClient.find(this.port, this.timeoutMillis, this.requestBuffer(), this.receiveBuffer());
        if(this.running && datagramPacket0 != null) {
            this.received(datagramPacket0);
        }
        this.failed();
    }

    public void setPort(int v) {
        this.port = v;
    }

    public void setTimeout(int v) {
        this.timeoutMillis = v;
    }
}

