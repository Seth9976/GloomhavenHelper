package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbsc implements zzeej {
    private final zzeew zzfgs;

    private zzbsc(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbrv(((Set)this.zzfgs.get()));
    }

    public static zzbsc zzn(zzeew zzeew0) {
        return new zzbsc(zzeew0);
    }
}

