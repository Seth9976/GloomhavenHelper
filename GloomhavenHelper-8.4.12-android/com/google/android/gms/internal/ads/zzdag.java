package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

public final class zzdag implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzevg;
    private final zzeew zzfst;

    public zzdag(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzevg = zzeew0;
        this.zzfst = zzeew1;
        this.zzelc = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzdae(((zzapj)this.zzevg.get()), ((ScheduledExecutorService)this.zzfst.get()), ((Context)this.zzelc.get()));
    }
}

