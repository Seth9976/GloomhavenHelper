package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbvb implements zzeej {
    private final zzeew zzfgs;

    private zzbvb(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbuz(((Set)this.zzfgs.get()));
    }

    public static zzbvb zzt(zzeew zzeew0) {
        return new zzbvb(zzeew0);
    }
}

