package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbss implements zzeej {
    private final zzeew zzfgs;

    private zzbss(zzeew zzeew0) {
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbsq(((Set)this.zzfgs.get()));
    }

    public static zzbss zzo(zzeew zzeew0) {
        return new zzbss(zzeew0);
    }
}

