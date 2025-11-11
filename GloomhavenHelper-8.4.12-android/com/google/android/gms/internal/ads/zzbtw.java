package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbtw implements zzeej {
    private final zzbtl zzflk;

    private zzbtw(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(this.zzflk.zzaih(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbtw zzn(zzbtl zzbtl0) {
        return new zzbtw(zzbtl0);
    }
}

