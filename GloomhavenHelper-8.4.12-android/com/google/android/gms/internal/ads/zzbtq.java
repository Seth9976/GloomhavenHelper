package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

public final class zzbtq implements zzeej {
    private final zzbtl zzflk;

    private zzbtq(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbtq zzh(zzbtl zzbtl0) {
        return new zzbtq(zzbtl0);
    }
}

