package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbro implements zzeej {
    private final zzeew zzfgs;

    private zzbro(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbrm(((Set)this.zzfgs.get()));
    }

    public static zzbro zzk(zzeew zzeew0) {
        return new zzbro(zzeew0);
    }
}

