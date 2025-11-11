package com.badlogic.gdx.net;

import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class NetJavaSocketImpl implements Socket {
    private java.net.Socket socket;

    public NetJavaSocketImpl(Protocol net$Protocol0, String s, int v, SocketHints socketHints0) {
        try {
            this.socket = new java.net.Socket();
            this.applyHints(socketHints0);
            InetSocketAddress inetSocketAddress0 = new InetSocketAddress(s, v);
            if(socketHints0 != null) {
                this.socket.connect(inetSocketAddress0, socketHints0.connectTimeout);
                return;
            }
            this.socket.connect(inetSocketAddress0);
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Error making a socket connection to " + s + ":" + v, exception0);
        }
    }

    public NetJavaSocketImpl(java.net.Socket socket0, SocketHints socketHints0) {
        this.socket = socket0;
        this.applyHints(socketHints0);
    }

    private void applyHints(SocketHints socketHints0) {
        if(socketHints0 != null) {
            try {
                this.socket.setPerformancePreferences(socketHints0.performancePrefConnectionTime, socketHints0.performancePrefLatency, socketHints0.performancePrefBandwidth);
                this.socket.setTrafficClass(socketHints0.trafficClass);
                this.socket.setTcpNoDelay(socketHints0.tcpNoDelay);
                this.socket.setKeepAlive(socketHints0.keepAlive);
                this.socket.setSendBufferSize(socketHints0.sendBufferSize);
                this.socket.setReceiveBufferSize(socketHints0.receiveBufferSize);
                this.socket.setSoLinger(socketHints0.linger, socketHints0.lingerDuration);
                this.socket.setSoTimeout(socketHints0.socketTimeout);
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error setting socket hints.", exception0);
            }
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        java.net.Socket socket0 = this.socket;
        if(socket0 != null) {
            try {
                socket0.close();
                this.socket = null;
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error closing socket.", exception0);
            }
        }
    }

    @Override  // com.badlogic.gdx.net.Socket
    public InputStream getInputStream() {
        try {
            return this.socket.getInputStream();
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Error getting input stream from socket.", exception0);
        }
    }

    @Override  // com.badlogic.gdx.net.Socket
    public OutputStream getOutputStream() {
        try {
            return this.socket.getOutputStream();
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Error getting output stream from socket.", exception0);
        }
    }

    @Override  // com.badlogic.gdx.net.Socket
    public String getRemoteAddress() {
        return this.socket.getRemoteSocketAddress().toString();
    }

    @Override  // com.badlogic.gdx.net.Socket
    public boolean isConnected() {
        return this.socket == null ? false : this.socket.isConnected();
    }
}

