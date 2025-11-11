package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbrt implements zzeej {
    private final zzeew zzfgs;

    private zzbrt(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbrr(((Set)this.zzfgs.get()));
    }

    public static zzbrt zzm(zzeew zzeew0) {
        return new zzbrt(zzeew0);
    }
}

