package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzdin implements zzeej {
    private final zzeew zzfgs;

    private zzdin(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzdii(((Set)this.zzfgs.get()));
    }

    public static zzdin zzap(zzeew zzeew0) {
        return new zzdin(zzeew0);
    }
}

