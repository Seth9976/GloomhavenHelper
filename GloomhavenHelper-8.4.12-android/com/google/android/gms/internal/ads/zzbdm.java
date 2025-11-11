package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.Clock;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class zzbdm extends zzbda implements zzoc {
    private String url;
    private ByteBuffer zzakt;
    private final zzbbj zzeck;
    private boolean zzeeu;
    private final zzbdj zzefa;
    private final zzbcs zzefb;
    private boolean zzefc;
    private final Object zzefd;
    private boolean zzefe;

    public zzbdm(zzbbm zzbbm0, zzbbj zzbbj0) {
        super(zzbbm0);
        this.zzeck = zzbbj0;
        this.zzefa = new zzbdj();
        this.zzefb = new zzbcs();
        this.zzefd = new Object();
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final void abort() {
        this.zzeeu = true;
    }

    public final ByteBuffer getByteBuffer() {
        synchronized(this.zzefd) {
            if(this.zzakt != null && !this.zzefc) {
                this.zzakt.flip();
                this.zzefc = true;
            }
            this.zzeeu = true;
            return this.zzakt;
        }
    }

    public final String getUrl() {
        return this.url;
    }

    @Override  // com.google.android.gms.internal.ads.zzoc
    public final void zza(Object object0, zznp zznp0) {
        if(((zzno)object0) instanceof zznr) {
            this.zzefa.zza(((zznr)(((zzno)object0))));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzoc
    public final void zzc(Object object0, int v) {
    }

    @Override  // com.google.android.gms.internal.ads.zzoc
    public final void zze(Object object0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final boolean zzfj(String s) {
        this.url = s;
        String s1 = this.zzfk(s);
        String s2 = "error";
        try {
            zznr zznr0 = new zznr(this.zzdwe, null, this, this.zzeck.zzean, this.zzeck.zzeap, true, null);
            if(this.zzeck.zzeat) {
                zznr0 = new zzbce(this.mContext, zznr0, null, null);
            }
            zznr0.zza(new zznp(Uri.parse(s)));
            zzbbm zzbbm0 = (zzbbm)this.zzeem.get();
            if(zzbbm0 != null) {
                zzbbm0.zza(s1, this);
            }
            Clock clock0 = zzq.zzlc();
            long v = clock0.currentTimeMillis();
            long v1 = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzchr)));
            long v2 = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzchq)));
            this.zzakt = ByteBuffer.allocate(this.zzeck.zzeam);
            byte[] arr_b = new byte[0x2000];
            long v3 = v;
            while(true) {
                int v4 = zznr0.read(arr_b, 0, Math.min(this.zzakt.remaining(), 0x2000));
                if(v4 == -1) {
                    this.zzefe = true;
                    this.zzb(s, s1, ((long)(((int)this.zzefb.zzl(this.zzakt)))));
                    return true;
                }
                synchronized(this.zzefd) {
                    if(!this.zzeeu) {
                        this.zzakt.put(arr_b, 0, v4);
                    }
                }
                if(this.zzakt.remaining() <= 0) {
                    this.zzyg();
                    return true;
                }
                if(this.zzeeu) {
                    break;
                }
                long v6 = clock0.currentTimeMillis();
                if(v6 - v3 >= v1) {
                    this.zzyg();
                    v3 = v6;
                }
                if(v6 - v > 1000L * v2) {
                    s2 = "downloadTimeout";
                    throw new IOException("Timeout exceeded. Limit: " + v2 + " sec");
                }
            }
            s2 = "externalAbort";
            throw new IOException("Precache abort at " + this.zzakt.limit() + " bytes");
        }
        catch(Exception exception0) {
            String s3 = exception0.getClass().getCanonicalName() + ":" + exception0.getMessage();
            zzawf.zzfa(("Failed to preload url " + s + " Exception: " + s3));
            this.zza(s, s1, s2, s3);
            return false;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    protected final String zzfk(String s) {
        String s1 = super.zzfk(s);
        return s1.length() == 0 ? new String("cache:") : "cache:" + s1;
    }

    private final void zzyg() {
        int v = (int)this.zzefa.zzzv();
        int v1 = (int)this.zzefb.zzl(this.zzakt);
        int v2 = this.zzakt.position();
        int v3 = Math.round(((float)v1) * (((float)v2) / ((float)v)));
        this.zza(this.url, this.zzfk(this.url), v2, v, ((long)v3), ((long)v1), v3 > 0, 0, 0);
    }

    public final boolean zzzx() {
        return this.zzefe;
    }
}

