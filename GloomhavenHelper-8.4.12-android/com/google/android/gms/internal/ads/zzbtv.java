package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbtv implements zzeej {
    private final zzbtl zzflk;

    private zzbtv(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(this.zzflk.zzaij(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbtv zzm(zzbtl zzbtl0) {
        return new zzbtv(zzbtl0);
    }
}

