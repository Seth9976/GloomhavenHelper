package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import java.util.concurrent.TimeUnit;

@TargetApi(14)
public final class zzbbd {
    private final long zzdzy;
    private long zzdzz;
    private boolean zzeaa;

    zzbbd() {
        this.zzdzy = TimeUnit.MILLISECONDS.toNanos(((long)(((Long)zzvh.zzpd().zzd(zzzx.zzchw)))));
        this.zzeaa = true;
    }

    public final void zza(SurfaceTexture surfaceTexture0, zzbau zzbau0) {
        if(zzbau0 == null) {
            return;
        }
        long v = surfaceTexture0.getTimestamp();
        if(this.zzeaa || Math.abs(v - this.zzdzz) >= this.zzdzy) {
            this.zzeaa = false;
            this.zzdzz = v;
            zzbbg zzbbg0 = new zzbbg(this, zzbau0);
            zzawo.zzdtx.post(zzbbg0);
        }
    }

    public final void zzxz() {
        this.zzeaa = true;
    }
}

