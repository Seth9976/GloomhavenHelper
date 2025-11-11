package com.badlogic.gdx.net;

public class SocketHints {
    public int connectTimeout;
    public boolean keepAlive;
    public boolean linger;
    public int lingerDuration;
    public int performancePrefBandwidth;
    public int performancePrefConnectionTime;
    public int performancePrefLatency;
    public int receiveBufferSize;
    public int sendBufferSize;
    public int socketTimeout;
    public boolean tcpNoDelay;
    public int trafficClass;

    public SocketHints() {
        this.connectTimeout = 5000;
        this.performancePrefConnectionTime = 0;
        this.performancePrefLatency = 1;
        this.performancePrefBandwidth = 0;
        this.trafficClass = 20;
        this.keepAlive = true;
        this.tcpNoDelay = true;
        this.sendBufferSize = 0x1000;
        this.receiveBufferSize = 0x1000;
        this.linger = false;
        this.lingerDuration = 0;
        this.socketTimeout = 0;
    }
}

