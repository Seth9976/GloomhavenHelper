package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbqr implements zzeej {
    private final zzeew zzfgs;

    private zzbqr(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbqp(((Set)this.zzfgs.get()));
    }

    public static zzbqr zzh(zzeew zzeew0) {
        return new zzbqr(zzeew0);
    }
}

