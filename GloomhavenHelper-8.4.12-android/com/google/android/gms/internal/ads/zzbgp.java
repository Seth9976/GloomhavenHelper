package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

public final class zzbgp implements zzeej {
    private final zzbgn zzeky;

    public zzbgp(zzbgn zzbgn0) {
        this.zzeky = zzbgn0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (WeakReference)zzeep.zza(this.zzeky.zzacv(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

