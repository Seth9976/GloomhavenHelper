package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbrs implements zzeej {
    private final zzeew zzfgs;

    private zzbrs(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbrq(((Set)this.zzfgs.get()));
    }

    public static zzbrs zzl(zzeew zzeew0) {
        return new zzbrs(zzeew0);
    }
}

