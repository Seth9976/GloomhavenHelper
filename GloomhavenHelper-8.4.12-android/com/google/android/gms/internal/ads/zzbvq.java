package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbvq implements zzeej {
    private final zzeew zzfgs;

    private zzbvq(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbvk(((Set)this.zzfgs.get()));
    }

    public static zzbvq zzu(zzeew zzeew0) {
        return new zzbvq(zzeew0);
    }
}

