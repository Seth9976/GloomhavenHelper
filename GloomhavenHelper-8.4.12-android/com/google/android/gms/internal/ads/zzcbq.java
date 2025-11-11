package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzcbq implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzfsl;

    public zzcbq(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzfsl = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcbm(((Executor)this.zzfev.get()), ((zzcbc)this.zzfsl.get()));
    }
}

