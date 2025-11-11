package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzccf implements zzeej {
    private final zzeew zzfgm;
    private final zzeew zzfrk;
    private final zzeew zzftb;

    public zzccf(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfgm = zzeew0;
        this.zzfrk = zzeew1;
        this.zzftb = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcby(((Executor)this.zzfgm.get()), ((zzbkk)this.zzfrk.get()), ((zzbuu)this.zzftb.get()));
    }
}

