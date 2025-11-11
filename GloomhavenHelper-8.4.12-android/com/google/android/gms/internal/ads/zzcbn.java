package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public final class zzcbn implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzeut;
    private final zzeew zzeuv;
    private final zzeew zzfda;
    private final zzeew zzfev;
    private final zzeew zzfgu;
    private final zzeew zzfnx;
    private final zzeew zzfsr;
    private final zzeew zzfss;
    private final zzeew zzfst;

    public zzcbn(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7, zzeew zzeew8, zzeew zzeew9) {
        this.zzelc = zzeew0;
        this.zzeut = zzeew1;
        this.zzfnx = zzeew2;
        this.zzfda = zzeew3;
        this.zzfsr = zzeew4;
        this.zzfss = zzeew5;
        this.zzfev = zzeew6;
        this.zzfgu = zzeew7;
        this.zzeuv = zzeew8;
        this.zzfst = zzeew9;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcbc(((Context)this.zzelc.get()), ((zzcax)this.zzeut.get()), ((zzdq)this.zzfnx.get()), ((zzazo)this.zzfda.get()), ((zza)this.zzfsr.get()), ((zzsn)this.zzfss.get()), ((Executor)this.zzfev.get()), ((zzdeu)this.zzfgu.get()), ((zzcbt)this.zzeuv.get()), ((ScheduledExecutorService)this.zzfst.get()));
    }
}

