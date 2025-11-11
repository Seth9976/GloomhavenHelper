package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.text.TextUtils;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

final class zzbcc implements zzno {
    private static final Pattern zzbev;
    private static final AtomicReference zzbew;
    private final int zzbey;
    private final int zzbez;
    private final String zzbfa;
    private final zznw zzbfd;
    private final zzoc zzbfe;
    private zznp zzbff;
    private HttpURLConnection zzbfg;
    private InputStream zzbfh;
    private boolean zzbfi;
    private long zzbfj;
    private long zzbfk;
    private long zzbfl;
    private long zzcf;
    private SSLSocketFactory zzecw;
    private int zzecx;
    private Set zzecy;

    static {
        zzbcc.zzbev = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
        zzbcc.zzbew = new AtomicReference();
    }

    zzbcc(String s, zzoc zzoc0, int v, int v1, int v2) {
        this.zzecw = new zzbcb(this);
        this.zzecy = new HashSet();
        this.zzbfa = zzob.checkNotEmpty(s);
        this.zzbfe = zzoc0;
        this.zzbfd = new zznw();
        this.zzbey = v;
        this.zzbez = v1;
        this.zzecx = v2;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final void close() throws zznu {
        try {
            if(this.zzbfh != null) {
                try {
                    this.zzbfh.close();
                }
                catch(IOException iOException0) {
                    throw new zznu(iOException0, this.zzbff, 3);
                }
            }
        }
        finally {
            this.zzbfh = null;
            this.zzin();
            if(this.zzbfi) {
                this.zzbfi = false;
                zzoc zzoc0 = this.zzbfe;
                if(zzoc0 != null) {
                    zzoc0.zze(this);
                }
            }
            this.zzecy.clear();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final Uri getUri() {
        return this.zzbfg == null ? null : Uri.parse(this.zzbfg.getURL().toString());
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final int read(byte[] arr_b, int v, int v1) throws zznu {
        try {
            if(this.zzbfl != this.zzbfj) {
                byte[] arr_b1 = (byte[])zzbcc.zzbew.getAndSet(null);
                if(arr_b1 == null) {
                    arr_b1 = new byte[0x1000];
                }
                while(this.zzbfl != this.zzbfj) {
                    int v2 = this.zzbfh.read(arr_b1, 0, ((int)Math.min(this.zzbfj - this.zzbfl, arr_b1.length)));
                    if(Thread.interrupted()) {
                        throw new InterruptedIOException();
                    }
                    if(v2 == -1) {
                        throw new EOFException();
                    }
                    this.zzbfl += (long)v2;
                    if(this.zzbfe != null) {
                        this.zzbfe.zzc(this, v2);
                    }
                }
                zzbcc.zzbew.set(arr_b1);
            }
            if(v1 == 0) {
                return 0;
            }
            if(this.zzbfk != -1L) {
                long v3 = this.zzbfk - this.zzcf;
                if(v3 == 0L) {
                    return -1;
                }
                v1 = (int)Math.min(v1, v3);
            }
            int v4 = this.zzbfh.read(arr_b, v, v1);
            if(v4 == -1) {
                if(this.zzbfk != -1L) {
                    throw new EOFException();
                }
                return -1;
            }
            this.zzcf += (long)v4;
            if(this.zzbfe != null) {
                this.zzbfe.zzc(this, v4);
            }
            return v4;
        }
        catch(IOException iOException0) {
            throw new zznu(iOException0, this.zzbff, 2);
        }
    }

    final void setReceiveBufferSize(int v) {
        this.zzecx = v;
        for(Object object0: this.zzecy) {
            Socket socket0 = (Socket)object0;
            if(!socket0.isClosed()) {
                try {
                    socket0.setReceiveBufferSize(this.zzecx);
                }
                catch(SocketException socketException0) {
                    zzawf.zzd("Failed to update receive buffer size.", socketException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final long zza(zznp zznp0) throws zznu {
        zzoc zzoc0;
        int v5;
        long v3;
        HttpURLConnection httpURLConnection0;
        try {
            this.zzbff = zznp0;
            this.zzcf = 0L;
            this.zzbfl = 0L;
            URL uRL0 = new URL(zznp0.uri.toString());
            long v = zznp0.zzana;
            long v1 = zznp0.zzce;
            boolean z = zznp0.zzba(1);
            byte[] arr_b = zznp0.zzbet;
            URL uRL1 = uRL0;
            int v2 = 0;
            while(true) {
                if(v2 > 20) {
                    throw new NoRouteToHostException("Too many redirects: " + (v2 + 1));
                }
                httpURLConnection0 = (HttpURLConnection)uRL1.openConnection();
                if(httpURLConnection0 instanceof HttpsURLConnection) {
                    ((HttpsURLConnection)httpURLConnection0).setSSLSocketFactory(this.zzecw);
                }
                httpURLConnection0.setConnectTimeout(this.zzbey);
                httpURLConnection0.setReadTimeout(this.zzbez);
                Iterator iterator0 = this.zzbfd.zziq().entrySet().iterator();
                while(true) {
                    v3 = -1L;
                    if(!iterator0.hasNext()) {
                        break;
                    }
                    Object object0 = iterator0.next();
                    httpURLConnection0.setRequestProperty(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue()));
                }
                if(v != 0L || v1 != -1L) {
                    httpURLConnection0.setRequestProperty("Range", (v1 == -1L ? "bytes=" + v + "-" : "bytes=" + v + "-" + (v + v1 - 1L)));
                }
                httpURLConnection0.setRequestProperty("User-Agent", this.zzbfa);
                if(!z) {
                    httpURLConnection0.setRequestProperty("Accept-Encoding", "identity");
                }
                httpURLConnection0.setInstanceFollowRedirects(false);
                httpURLConnection0.setDoOutput(arr_b != null);
                if(arr_b == null) {
                    httpURLConnection0.connect();
                }
                else {
                    httpURLConnection0.setRequestMethod("POST");
                    if(arr_b.length != 0) {
                        httpURLConnection0.setFixedLengthStreamingMode(arr_b.length);
                        httpURLConnection0.connect();
                        OutputStream outputStream0 = httpURLConnection0.getOutputStream();
                        outputStream0.write(arr_b);
                        outputStream0.close();
                    }
                }
                int v4 = httpURLConnection0.getResponseCode();
                if(v4 != 300 && v4 != 301 && v4 != 302 && v4 != 303 && (arr_b != null || v4 != 307 && v4 != 308)) {
                    break;
                }
                String s = httpURLConnection0.getHeaderField("Location");
                httpURLConnection0.disconnect();
                if(s == null) {
                    throw new ProtocolException("Null location redirect");
                }
                URL uRL2 = new URL(uRL1, s);
                String s1 = uRL2.getProtocol();
                if(!"https".equals(s1) && !"http".equals(s1)) {
                    String s2 = String.valueOf(s1);
                    throw new ProtocolException((s2.length() == 0 ? new String("Unsupported protocol redirect: ") : "Unsupported protocol redirect: " + s2));
                }
                uRL1 = uRL2;
                ++v2;
                arr_b = null;
            }
            this.zzbfg = httpURLConnection0;
        }
        catch(IOException iOException0) {
            String s3 = zznp0.uri.toString();
            throw new zznu((s3.length() == 0 ? new String("Unable to connect to ") : "Unable to connect to " + s3), iOException0, zznp0, 1);
        }
        try {
            v5 = this.zzbfg.getResponseCode();
        }
        catch(IOException iOException1) {
            this.zzin();
            String s4 = zznp0.uri.toString();
            throw new zznu((s4.length() == 0 ? new String("Unable to connect to ") : "Unable to connect to " + s4), iOException1, zznp0, 1);
        }
        if(v5 >= 200 && v5 <= 299) {
            this.zzbfj = v5 != 200 || zznp0.zzana == 0L ? 0L : zznp0.zzana;
            if(zznp0.zzba(1)) {
                this.zzbfk = zznp0.zzce;
            }
            else if(zznp0.zzce != -1L) {
                this.zzbfk = zznp0.zzce;
            }
            else {
                long v6 = zzbcc.zzc(this.zzbfg);
                if(v6 != -1L) {
                    v3 = v6 - this.zzbfj;
                }
                this.zzbfk = v3;
            }
            try {
                this.zzbfh = this.zzbfg.getInputStream();
                this.zzbfi = true;
                zzoc0 = this.zzbfe;
            }
            catch(IOException iOException2) {
                this.zzin();
                throw new zznu(iOException2, zznp0, 1);
            }
            if(zzoc0 != null) {
                zzoc0.zza(this, zznp0);
            }
            return this.zzbfk;
        }
        Map map0 = this.zzbfg.getHeaderFields();
        this.zzin();
        zznt zznt0 = new zznt(v5, map0, zznp0);
        if(v5 == 0x1A0) {
            zznt0.initCause(new zznq(0));
        }
        throw zznt0;
    }

    private final void zzb(Socket socket0) {
        this.zzecy.add(socket0);
    }

    private static long zzc(HttpURLConnection httpURLConnection0) {
        long v;
        String s = httpURLConnection0.getHeaderField("Content-Length");
        if(TextUtils.isEmpty(s)) {
            v = -1L;
        }
        else {
            try {
                v = Long.parseLong(s);
            }
            catch(NumberFormatException unused_ex) {
                zzawf.zzey(("Unexpected Content-Length [" + s + "]"));
                v = -1L;
            }
        }
        String s1 = httpURLConnection0.getHeaderField("Content-Range");
        if(!TextUtils.isEmpty(s1)) {
            Matcher matcher0 = zzbcc.zzbev.matcher(s1);
            if(matcher0.find()) {
                try {
                    long v1 = Long.parseLong(matcher0.group(2)) - Long.parseLong(matcher0.group(1)) + 1L;
                    if(v < 0L) {
                        return v1;
                    }
                    if(v != v1) {
                        zzawf.zzfa(("Inconsistent headers [" + s + "] [" + s1 + "]"));
                        return Math.max(v, v1);
                    }
                }
                catch(NumberFormatException unused_ex) {
                    zzawf.zzey(("Unexpected Content-Range [" + s1 + "]"));
                }
            }
        }
        return v;
    }

    private final void zzin() {
        HttpURLConnection httpURLConnection0 = this.zzbfg;
        if(httpURLConnection0 != null) {
            try {
                httpURLConnection0.disconnect();
            }
            catch(Exception exception0) {
                zzawf.zzc("Unexpected error while disconnecting", exception0);
            }
            this.zzbfg = null;
        }
    }
}

