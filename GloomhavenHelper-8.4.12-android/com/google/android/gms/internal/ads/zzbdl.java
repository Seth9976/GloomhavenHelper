package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.Clock;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@TargetApi(16)
public final class zzbdl extends zzbda implements zzbcn {
    private String zzdzm;
    private boolean zzeeu;
    private zzbcf zzeex;
    private Exception zzeey;
    private boolean zzeez;

    public zzbdl(zzbbm zzbbm0, zzbbj zzbbj0) {
        super(zzbbm0);
        this.zzeex = new zzbcf(zzbbm0.getContext(), zzbbj0);
        this.zzeex.zza(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final void abort() {
        this.zzfm(null);
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final void release() {
        zzbcf zzbcf0 = this.zzeex;
        if(zzbcf0 != null) {
            zzbcf0.zza(null);
            this.zzeex.release();
        }
        super.release();
    }

    @Override  // com.google.android.gms.internal.ads.zzbcn
    public final void zza(String s, Exception exception0) {
        String s1 = (String)zzvh.zzpd().zzd(zzzx.zzchl);
        if(s1 != null) {
            List list0 = Arrays.asList(s1.split(","));
            if(list0.contains("all")) {
                return;
            }
            if(list0.contains(exception0.getClass().getCanonicalName())) {
                return;
            }
        }
        this.zzeey = exception0;
        zzawf.zzd("Precache error", exception0);
        this.zzfm(s);
    }

    private static String zzb(String s, Exception exception0) {
        return s + "/" + exception0.getClass().getCanonicalName() + ":" + exception0.getMessage();
    }

    @Override  // com.google.android.gms.internal.ads.zzbcn
    public final void zzb(boolean z, long v) {
        zzbbm zzbbm0 = (zzbbm)this.zzeem.get();
        if(zzbbm0 != null) {
            zzbdo zzbdo0 = new zzbdo(zzbbm0, z, v);
            zzazq.zzdxo.execute(zzbdo0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final void zzcv(int v) {
        this.zzeex.zzzu().zzdc(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final void zzcw(int v) {
        this.zzeex.zzzu().zzdd(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final void zzcx(int v) {
        this.zzeex.zzzu().zzcx(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final void zzcy(int v) {
        this.zzeex.zzzu().zzcy(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbcn
    public final void zzda(int v) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final boolean zze(String s, String[] arr_s) {
        String s3;
        long v12;
        long v11;
        long v10;
        long v9;
        long v8;
        long v5;
        long v4;
        long v3;
        long v2;
        this.zzdzm = s;
        String s1 = this.zzfk(s);
        String s2 = "error";
        try {
            Uri[] arr_uri = new Uri[arr_s.length];
            for(int v = 0; v < arr_s.length; ++v) {
                arr_uri[v] = Uri.parse(arr_s[v]);
            }
            this.zzeex.zza(arr_uri, this.zzdwe);
            zzbbm zzbbm0 = (zzbbm)this.zzeem.get();
            if(zzbbm0 != null) {
                zzbbm0.zza(s1, this);
            }
            Clock clock0 = zzq.zzlc();
            long v1 = clock0.currentTimeMillis();
            v2 = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzchr)));
            v3 = ((long)(((Long)zzvh.zzpd().zzd(zzzx.zzchq)))) * 1000L;
            v4 = (long)(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzchp)))));
            v5 = -1L;
            while(true) {
            label_19:
                __monitor_enter(this);
                break;
            }
        }
        catch(Exception exception0) {
            zzawf.zzfa(("Failed to preload url " + s + " Exception: " + exception0.getMessage()));
            this.release();
            this.zza(s, s1, s2, zzbdl.zzb(s2, exception0));
            return false;
        }
        try {
            if(clock0.currentTimeMillis() - v1 > v3) {
                goto label_71;
            }
            if(this.zzeeu) {
                goto label_66;
            }
            if(this.zzeez) {
                __monitor_exit(this);
                return true;
            }
            zzgn zzgn0 = this.zzeex.zzzr();
            if(zzgn0 == null) {
                goto label_64;
            }
            long v6 = zzgn0.getDuration();
            if(v6 > 0L) {
                long v7 = zzgn0.getBufferedPosition();
                if(v7 == v5) {
                    v8 = v4;
                    v9 = v3;
                    v10 = v2;
                }
                else {
                    v8 = v4;
                    v9 = v3;
                    v10 = v2;
                    this.zza(s, s1, v7, v6, v7 > 0L, 0, 0);
                    v5 = v7;
                }
                if(v7 >= v6) {
                    this.zzb(s, s1, v6);
                    __monitor_exit(this);
                    return true;
                }
                if(this.zzeex.getBytesTransferred() >= v8 && v7 > 0L) {
                    __monitor_exit(this);
                    return true;
                }
                v11 = v5;
                v12 = v10;
            }
            else {
                v8 = v4;
                v9 = v3;
                v11 = v5;
                v12 = v2;
            }
            try {
                this.wait(v12);
                goto label_58;
            }
            catch(InterruptedException unused_ex) {
            }
            s3 = "interrupted";
        }
        catch(Throwable throwable0) {
            goto label_75;
        }
        try {
            throw new IOException("Wait interrupted.");
        }
        catch(Throwable throwable0) {
            goto label_74;
        }
        try {
        label_58:
            __monitor_exit(this);
            v2 = v12;
            v5 = v11;
            v4 = v8;
            v3 = v9;
            goto label_19;
        label_64:
            s3 = "exoPlayerReleased";
        }
        catch(Throwable throwable0) {
            goto label_75;
        }
        try {
            throw new IOException("ExoPlayer was released during preloading.");
        }
        catch(Throwable throwable0) {
            goto label_74;
        }
        try {
        label_66:
            if(this.zzeey != null) {
                s3 = "badUrl";
                throw this.zzeey;
            }
            goto label_69;
        }
        catch(Throwable throwable0) {
            goto label_75;
        }
        try {
            throw this.zzeey;
        }
        catch(Throwable throwable0) {
            goto label_74;
        }
        try {
        label_69:
            s3 = "externalAbort";
        }
        catch(Throwable throwable0) {
            goto label_75;
        }
        try {
            throw new IOException("Abort requested before buffering finished. ");
        }
        catch(Throwable throwable0) {
            goto label_74;
        }
        try {
        label_71:
            s3 = "downloadTimeout";
        }
        catch(Throwable throwable0) {
            goto label_75;
        }
        try {
            throw new IOException("Timeout reached. Limit: " + v3 + " ms");
        }
        catch(Throwable throwable0) {
        label_74:
            s2 = s3;
            while(true) {
                try {
                label_75:
                    __monitor_exit(this);
                    throw throwable0;
                }
                catch(Throwable throwable0) {
                }
            }
        }
        goto label_75;
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final boolean zzfj(String s) {
        return this.zze(s, new String[]{s});
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    protected final String zzfk(String s) {
        String s1 = super.zzfk(s);
        return s1.length() == 0 ? new String("cache:") : "cache:" + s1;
    }

    private final void zzfm(String s) {
        synchronized(this) {
            this.zzeeu = true;
            this.notify();
            this.release();
        }
        String s1 = this.zzdzm;
        if(s1 != null) {
            String s2 = this.zzfk(s1);
            Exception exception0 = this.zzeey;
            if(exception0 != null) {
                this.zza(this.zzdzm, s2, "badUrl", zzbdl.zzb(s, exception0));
                return;
            }
            this.zza(this.zzdzm, s2, "externalAbort", "Programmatic precache abort.");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbcn
    public final void zzn(int v, int v1) {
    }

    public final zzbcf zzzw() {
        synchronized(this) {
            this.zzeez = true;
            this.notify();
        }
        this.zzeex.zza(null);
        zzbcf zzbcf0 = this.zzeex;
        this.zzeex = null;
        return zzbcf0;
    }
}

