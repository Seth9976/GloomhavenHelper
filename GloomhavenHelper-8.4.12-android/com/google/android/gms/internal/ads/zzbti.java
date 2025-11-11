package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbti implements zzeej {
    private final zzeew zzfgs;

    private zzbti(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbsz(((Set)this.zzfgs.get()));
    }

    public static zzbti zzr(zzeew zzeew0) {
        return new zzbti(zzeew0);
    }
}

