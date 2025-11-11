package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

public final class zzbtr implements zzeej {
    private final zzbtl zzflk;

    private zzbtr(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbtr.zzj(this.zzflk);
    }

    public static zzbtr zzi(zzbtl zzbtl0) {
        return new zzbtr(zzbtl0);
    }

    public static Set zzj(zzbtl zzbtl0) {
        return (Set)zzeep.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

