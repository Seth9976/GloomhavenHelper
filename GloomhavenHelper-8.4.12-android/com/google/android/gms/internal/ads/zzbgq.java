package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbgq implements zzeej {
    private final zzbgn zzeky;

    public zzbgq(zzbgn zzbgn0) {
        this.zzeky = zzbgn0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbgq.zza(this.zzeky);
    }

    public static Context zza(zzbgn zzbgn0) {
        return (Context)zzeep.zza(zzbgn0.zzacu(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

