package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class zzeds implements zzbf {
    private String type;
    private long zzavf;
    private static zzeeb zzcr;
    private zzbi zzifg;
    boolean zzifi;
    private boolean zzifj;
    private ByteBuffer zzifk;
    private long zzifl;
    private long zzifm;
    private zzedv zzifn;
    private ByteBuffer zzifo;

    static {
        zzeds.zzcr = zzeeb.zzn(zzeds.class);
    }

    protected zzeds(String s) {
        this.zzifm = -1L;
        this.zzifo = null;
        this.type = s;
        this.zzifj = true;
        this.zzifi = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzbf
    public final String getType() {
        return this.type;
    }

    @Override  // com.google.android.gms.internal.ads.zzbf
    public final void zza(zzbi zzbi0) {
        this.zzifg = zzbi0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbf
    public final void zza(zzedv zzedv0, ByteBuffer byteBuffer0, long v, zzbe zzbe0) throws IOException {
        this.zzifl = zzedv0.position();
        this.zzavf = this.zzifl - ((long)byteBuffer0.remaining());
        this.zzifm = v;
        this.zzifn = zzedv0;
        zzedv0.zzfc(zzedv0.position() + v);
        this.zzifj = false;
        this.zzifi = false;
        this.zzbgj();
    }

    private final void zzbgi() {
        synchronized(this) {
            if(!this.zzifj) {
                try {
                    String s = String.valueOf(this.type);
                    zzeds.zzcr.zzhs((s.length() == 0 ? new String("mem mapping ") : "mem mapping " + s));
                    this.zzifk = this.zzifn.zzh(this.zzifl, this.zzifm);
                    this.zzifj = true;
                }
                catch(IOException iOException0) {
                    throw new RuntimeException(iOException0);
                }
            }
        }
    }

    public final void zzbgj() {
        synchronized(this) {
            this.zzbgi();
            String s = String.valueOf(this.type);
            zzeds.zzcr.zzhs((s.length() == 0 ? new String("parsing details of ") : "parsing details of " + s));
            if(this.zzifk != null) {
                ByteBuffer byteBuffer0 = this.zzifk;
                this.zzifi = true;
                byteBuffer0.rewind();
                this.zzg(byteBuffer0);
                if(byteBuffer0.remaining() > 0) {
                    this.zzifo = byteBuffer0.slice();
                }
                this.zzifk = null;
            }
        }
    }

    protected abstract void zzg(ByteBuffer arg1);
}

