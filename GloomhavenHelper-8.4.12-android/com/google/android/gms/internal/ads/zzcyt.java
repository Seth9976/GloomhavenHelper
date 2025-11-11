package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public final class zzcyt implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzevg;
    private final zzeew zzfev;
    private final zzeew zzfst;

    public zzcyt(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzevg = zzeew0;
        this.zzelc = zzeew1;
        this.zzfst = zzeew2;
        this.zzfev = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcys(((zzavn)this.zzevg.get()), ((Context)this.zzelc.get()), ((ScheduledExecutorService)this.zzfst.get()), ((Executor)this.zzfev.get()));
    }
}

