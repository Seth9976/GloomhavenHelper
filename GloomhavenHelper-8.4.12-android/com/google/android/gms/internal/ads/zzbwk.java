package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

public final class zzbwk implements zzeej {
    private final zzeew zzffv;

    private zzbwk(zzeew zzeew0) {
        this.zzffv = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(Collections.singleton(zzbuv.zzb(((zzbxe)this.zzffv.get()), zzazq.zzdxp)), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbwk zzv(zzeew zzeew0) {
        return new zzbwk(zzeew0);
    }
}

