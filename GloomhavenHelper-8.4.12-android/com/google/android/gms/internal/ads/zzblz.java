package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

public final class zzblz implements zzeej {
    private final zzeew zzffv;
    private final zzbln zzfgr;

    public zzblz(zzbln zzbln0, zzeew zzeew0) {
        this.zzfgr = zzbln0;
        this.zzffv = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(Collections.singleton(new zzbuv(((zzbmw)this.zzffv.get()), zzazq.zzdxp)), "Cannot return null from a non-@Nullable @Provides method");
    }
}

