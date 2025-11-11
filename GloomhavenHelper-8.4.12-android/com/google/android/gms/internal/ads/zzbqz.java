package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbqz implements zzeej {
    private final zzeew zzfgs;

    private zzbqz(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbqw(((Set)this.zzfgs.get()));
    }

    public static zzbqz zzi(zzeew zzeew0) {
        return new zzbqz(zzeew0);
    }
}

