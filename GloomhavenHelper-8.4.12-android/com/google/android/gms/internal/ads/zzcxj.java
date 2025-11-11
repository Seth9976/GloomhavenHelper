package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

public final class zzcxj implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzelp;
    private final zzeew zzfev;
    private final zzeew zzffj;
    private final zzeew zzfgu;
    private final zzeew zzfst;
    private final zzeew zzgeb;

    private zzcxj(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6) {
        this.zzfev = zzeew0;
        this.zzfst = zzeew1;
        this.zzffj = zzeew2;
        this.zzelp = zzeew3;
        this.zzelc = zzeew4;
        this.zzfgu = zzeew5;
        this.zzgeb = zzeew6;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcxg(((zzdoe)this.zzfev.get()), ((ScheduledExecutorService)this.zzfst.get()), ((String)this.zzffj.get()), ((zzcrq)this.zzelp.get()), ((Context)this.zzelc.get()), ((zzdeu)this.zzfgu.get()), ((zzcro)this.zzgeb.get()));
    }

    public static zzcxj zza(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6) {
        return new zzcxj(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4, zzeew5, zzeew6);
    }
}

