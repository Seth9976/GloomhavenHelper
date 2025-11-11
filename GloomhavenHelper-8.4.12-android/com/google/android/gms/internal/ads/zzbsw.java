package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbsw implements zzeej {
    private final zzeew zzfgs;

    private zzbsw(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbsu(((Set)this.zzfgs.get()));
    }

    public static zzbsw zzp(zzeew zzeew0) {
        return new zzbsw(zzeew0);
    }
}

