package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzctg implements zzeej {
    private final zzcta zzghi;

    public zzctg(zzcta zzcta0) {
        this.zzghi = zzcta0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(this.zzghi.zzaoy(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

