package com.badlogic.gdx.net;

import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.net.InetSocketAddress;

public class NetJavaServerSocketImpl implements ServerSocket {
    private Protocol protocol;
    private java.net.ServerSocket server;

    public NetJavaServerSocketImpl(Protocol net$Protocol0, int v, ServerSocketHints serverSocketHints0) {
        this(net$Protocol0, null, v, serverSocketHints0);
    }

    public NetJavaServerSocketImpl(Protocol net$Protocol0, String s, int v, ServerSocketHints serverSocketHints0) {
        this.protocol = net$Protocol0;
        try {
            this.server = new java.net.ServerSocket();
            if(serverSocketHints0 != null) {
                this.server.setPerformancePreferences(serverSocketHints0.performancePrefConnectionTime, serverSocketHints0.performancePrefLatency, serverSocketHints0.performancePrefBandwidth);
                this.server.setReuseAddress(serverSocketHints0.reuseAddress);
                this.server.setSoTimeout(serverSocketHints0.acceptTimeout);
                this.server.setReceiveBufferSize(serverSocketHints0.receiveBufferSize);
            }
            InetSocketAddress inetSocketAddress0 = s == null ? new InetSocketAddress(v) : new InetSocketAddress(s, v);
            if(serverSocketHints0 != null) {
                this.server.bind(inetSocketAddress0, serverSocketHints0.backlog);
                return;
            }
            this.server.bind(inetSocketAddress0);
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Cannot create a server socket at port " + v + ".", exception0);
        }
    }

    @Override  // com.badlogic.gdx.net.ServerSocket
    public Socket accept(SocketHints socketHints0) {
        try {
            return new NetJavaSocketImpl(this.server.accept(), socketHints0);
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Error accepting socket.", exception0);
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        java.net.ServerSocket serverSocket0 = this.server;
        if(serverSocket0 != null) {
            try {
                serverSocket0.close();
                this.server = null;
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error closing server.", exception0);
            }
        }
    }

    @Override  // com.badlogic.gdx.net.ServerSocket
    public Protocol getProtocol() {
        return this.protocol;
    }
}

