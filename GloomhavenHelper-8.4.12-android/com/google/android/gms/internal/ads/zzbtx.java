package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbtx implements zzeej {
    private final zzbtl zzflk;

    private zzbtx(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(this.zzflk.zzaio(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbtx zzo(zzbtl zzbtl0) {
        return new zzbtx(zzbtl0);
    }
}

