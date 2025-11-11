package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import javax.net.ssl.SSLSocketFactory;

final class zzbcb extends SSLSocketFactory {
    private SSLSocketFactory zzecu;
    private final zzbcc zzecv;

    zzbcb(zzbcc zzbcc0) {
        this.zzecv = zzbcc0;
        super();
        this.zzecu = (SSLSocketFactory)SSLSocketFactory.getDefault();
    }

    @Override  // javax.net.SocketFactory
    public final Socket createSocket(String s, int v) throws IOException {
        return this.zza(this.zzecu.createSocket(s, v));
    }

    @Override  // javax.net.SocketFactory
    public final Socket createSocket(String s, int v, InetAddress inetAddress0, int v1) throws IOException {
        return this.zza(this.zzecu.createSocket(s, v, inetAddress0, v1));
    }

    @Override  // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress0, int v) throws IOException {
        return this.zza(this.zzecu.createSocket(inetAddress0, v));
    }

    @Override  // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress0, int v, InetAddress inetAddress1, int v1) throws IOException {
        return this.zza(this.zzecu.createSocket(inetAddress0, v, inetAddress1, v1));
    }

    @Override  // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket0, String s, int v, boolean z) throws IOException {
        return this.zza(this.zzecu.createSocket(socket0, s, v, z));
    }

    @Override  // javax.net.ssl.SSLSocketFactory
    public final String[] getDefaultCipherSuites() {
        return this.zzecu.getDefaultCipherSuites();
    }

    @Override  // javax.net.ssl.SSLSocketFactory
    public final String[] getSupportedCipherSuites() {
        return this.zzecu.getSupportedCipherSuites();
    }

    private final Socket zza(Socket socket0) throws SocketException {
        if(this.zzecv.zzecx > 0) {
            socket0.setReceiveBufferSize(this.zzecv.zzecx);
        }
        this.zzecv.zzb(socket0);
        return socket0;
    }
}

