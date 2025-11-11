package com.google.android.gms.internal.ads;

import android.view.View;

public final class zzbkt implements zzeej {
    private final zzbkq zzffu;

    public zzbkt(zzbkq zzbkq0) {
        this.zzffu = zzbkq0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbkt.zza(this.zzffu);
    }

    public static View zza(zzbkq zzbkq0) {
        return (View)zzeep.zza(zzbkq0.zzagd(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

