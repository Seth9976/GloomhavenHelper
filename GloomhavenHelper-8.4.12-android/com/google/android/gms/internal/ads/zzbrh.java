package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbrh implements zzeej {
    private final zzeew zzfgs;

    private zzbrh(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbrc(((Set)this.zzfgs.get()));
    }

    public static zzbrh zzj(zzeew zzeew0) {
        return new zzbrh(zzeew0);
    }
}

