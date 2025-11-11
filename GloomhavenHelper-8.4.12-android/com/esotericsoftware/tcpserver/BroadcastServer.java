package com.esotericsoftware.tcpserver;

import com.esotericsoftware.minlog.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class BroadcastServer extends Retry {
    private final byte[] buffer;
    private int port;
    public static final byte[] prefix;
    private DatagramSocket socket;

    static {
        BroadcastServer.prefix = new byte[]{62, 0x7E, -17, 61, 0x7F, -16, 0x3F, 0x7D, -18};
    }

    public BroadcastServer(String s, String s1) {
        this(s, s1, 0);
    }

    public BroadcastServer(String s, String s1, int v) {
        super(s, s1);
        this.buffer = new byte[BroadcastServer.prefix.length];
        this.port = v;
    }

    public int getPort() {
        return this.port;
    }

    public static void main(String[] arr_s) throws Exception {
        Log.TRACE();
        new BroadcastServer("broadcast", "test", 0xD055).start();
        System.out.println(BroadcastClient.find(0xD055, 1000).getAddress());
    }

    protected byte[] receiveBuffer() {
        return this.buffer;
    }

    protected byte[] responseBuffer(DatagramPacket datagramPacket0) {
        return this.buffer;
    }

    @Override  // com.esotericsoftware.tcpserver.Retry
    protected void retry() {
        DatagramPacket datagramPacket0;
        byte[] arr_b;
        try {
            this.socket = new DatagramSocket(this.port);
        }
        catch(Exception exception0) {
            if(!this.isFirstTry()) {
                if(Log.DEBUG) {
                    Log.debug(this.category, "Unable to start broadcast server.", exception0);
                }
            }
            else if(Log.ERROR) {
                Log.error(this.category, "Unable to start broadcast server.", exception0);
            }
            this.failed();
            return;
        }
        this.success();
        try {
            if(Log.INFO) {
                Log.info(this.category, "Listening on port: UDP " + this.port);
            }
            arr_b = this.receiveBuffer();
            while(true) {
            label_15:
                if(!this.running) {
                    goto label_64;
                }
                datagramPacket0 = new DatagramPacket(arr_b, arr_b.length);
                try {
                    this.socket.receive(datagramPacket0);
                    goto label_25;
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
            goto label_52;
        }
        catch(Throwable throwable0) {
            goto label_61;
        }
        if(Log.INFO) {
            Log.info(this.category, "Server stopped: UDP " + this.port);
        }
        return;
        try {
            try {
            label_25:
                if(datagramPacket0.getLength() >= BroadcastServer.prefix.length) {
                    int v1 = 0;
                    while(v1 < BroadcastServer.prefix.length) {
                        if(arr_b[v1] == BroadcastServer.prefix[v1]) {
                            ++v1;
                            continue;
                        }
                        if(!Log.DEBUG) {
                            goto label_15;
                        }
                        StringBuilder stringBuilder0 = new StringBuilder();
                        for(int v = 0; v < BroadcastServer.prefix.length; ++v) {
                            stringBuilder0.append(Integer.toHexString(arr_b[v]) + " ");
                        }
                        stringBuilder0.setLength(stringBuilder0.length() - 1);
                        Log.debug(this.category, "Server received invalid packet, prefix: " + stringBuilder0);
                        goto label_15;
                    }
                    byte[] arr_b1 = this.responseBuffer(datagramPacket0);
                    System.arraycopy(BroadcastServer.prefix, 0, arr_b1, 0, BroadcastServer.prefix.length);
                    InetAddress inetAddress0 = datagramPacket0.getAddress();
                    int v2 = datagramPacket0.getPort();
                    DatagramPacket datagramPacket1 = new DatagramPacket(arr_b1, arr_b1.length, inetAddress0, v2);
                    this.socket.send(datagramPacket1);
                    goto label_15;
                }
                if(!Log.DEBUG) {
                    goto label_15;
                }
                Log.debug(this.category, "Server received invalid packet, length: " + datagramPacket0.getLength());
                goto label_15;
            }
            catch(Exception exception1) {
            }
        label_52:
            if(Log.ERROR) {
                Log.error(this.category, "Unexpected broadcast server error.", exception1);
            }
            Util.closeQuietly(this.socket);
            this.failed();
            if(Log.INFO) {
                goto label_57;
            }
            return;
        }
        catch(Throwable throwable0) {
            goto label_61;
        }
    label_57:
        String s = this.category;
        Log.info(s, "Server stopped: UDP " + this.port);
        return;
    label_61:
        if(Log.INFO) {
            Log.info(this.category, "Server stopped: UDP " + this.port);
        }
        throw throwable0;
    label_64:
        if(Log.INFO) {
            s = this.category;
            Log.info(s, "Server stopped: UDP " + this.port);
        }
    }

    public void setPort(int v) {
        this.port = v;
    }

    @Override  // com.esotericsoftware.tcpserver.Retry
    protected void stopped() {
        Util.closeQuietly(this.socket);
    }
}

