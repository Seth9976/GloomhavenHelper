package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

public final class zzbue implements zzeej {
    private final zzbtl zzflk;

    private zzbue(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbue zzw(zzbtl zzbtl0) {
        return new zzbue(zzbtl0);
    }
}

