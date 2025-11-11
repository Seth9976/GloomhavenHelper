package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;

public final class zzdfi implements zzeej {
    private final zzdff zzgre;

    public zzdfi(zzdff zzdff0) {
        this.zzgre = zzdff0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Clock)zzeep.zza(DefaultClock.getInstance(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

