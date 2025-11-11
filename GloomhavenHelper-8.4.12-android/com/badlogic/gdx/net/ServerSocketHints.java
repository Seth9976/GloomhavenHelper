package com.badlogic.gdx.net;

public class ServerSocketHints {
    public int acceptTimeout;
    public int backlog;
    public int performancePrefBandwidth;
    public int performancePrefConnectionTime;
    public int performancePrefLatency;
    public int receiveBufferSize;
    public boolean reuseAddress;

    public ServerSocketHints() {
        this.backlog = 16;
        this.performancePrefConnectionTime = 0;
        this.performancePrefLatency = 1;
        this.performancePrefBandwidth = 0;
        this.reuseAddress = true;
        this.acceptTimeout = 5000;
        this.receiveBufferSize = 0x1000;
    }
}

