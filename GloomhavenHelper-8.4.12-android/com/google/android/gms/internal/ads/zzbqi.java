package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbqi implements zzeej {
    private final zzeew zzfgs;

    private zzbqi(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbqg(((Set)this.zzfgs.get()));
    }

    public static zzbqi zzg(zzeew zzeew0) {
        return new zzbqi(zzeew0);
    }
}

