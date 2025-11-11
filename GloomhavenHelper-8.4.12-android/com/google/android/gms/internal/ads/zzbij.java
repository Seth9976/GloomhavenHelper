package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zza;

public final class zzbij implements zzeej {
    private final zzbih zzfcs;

    public zzbij(zzbih zzbih0) {
        this.zzfcs = zzbih0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zza)zzeep.zza(this.zzfcs.zzafj(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

