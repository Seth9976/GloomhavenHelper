package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbus implements zzeej {
    private final zzeew zzfgs;

    private zzbus(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbur(((Set)this.zzfgs.get()));
    }

    public static zzbus zzs(zzeew zzeew0) {
        return new zzbus(zzeew0);
    }
}

