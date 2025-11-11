package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbtz implements zzeej {
    private final zzbtl zzflk;

    private zzbtz(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbtz.zzr(this.zzflk);
    }

    public static zzbtz zzq(zzbtl zzbtl0) {
        return new zzbtz(zzbtl0);
    }

    public static Set zzr(zzbtl zzbtl0) {
        return (Set)zzeep.zza(zzbtl0.zzaii(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

