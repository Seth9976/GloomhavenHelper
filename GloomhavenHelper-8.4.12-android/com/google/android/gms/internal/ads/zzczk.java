package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public final class zzczk implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzevg;
    private final zzeew zzfev;
    private final zzeew zzfst;
    private final zzeew zzgic;
    private final zzeew zzgln;

    public zzczk(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5) {
        this.zzevg = zzeew0;
        this.zzgln = zzeew1;
        this.zzelc = zzeew2;
        this.zzgic = zzeew3;
        this.zzfst = zzeew4;
        this.zzfev = zzeew5;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzczg(((zzavn)this.zzevg.get()), ((int)(((Integer)this.zzgln.get()))), ((Context)this.zzelc.get()), ((zzavr)this.zzgic.get()), ((ScheduledExecutorService)this.zzfst.get()), ((Executor)this.zzfev.get()));
    }
}

