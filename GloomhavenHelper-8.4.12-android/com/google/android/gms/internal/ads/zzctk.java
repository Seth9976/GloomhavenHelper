package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzctk implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzghp;

    public zzctk(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzghp = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcti(((Executor)this.zzfev.get()), ((zzcjk)this.zzghp.get()));
    }
}

