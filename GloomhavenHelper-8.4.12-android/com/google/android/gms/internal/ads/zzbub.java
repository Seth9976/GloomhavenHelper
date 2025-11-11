package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbub implements zzeej {
    private final zzbtl zzflk;

    private zzbub(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(this.zzflk.zzaip(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbub zzt(zzbtl zzbtl0) {
        return new zzbub(zzbtl0);
    }
}

