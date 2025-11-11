package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

public final class zzblu implements zzeej {
    private final zzeew zzffv;
    private final zzbln zzfgr;

    public zzblu(zzbln zzbln0, zzeew zzeew0) {
        this.zzfgr = zzbln0;
        this.zzffv = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzbmw zzbmw0 = (zzbmw)this.zzffv.get();
        return zzblu.zza(this.zzfgr, zzbmw0);
    }

    public static Set zza(zzbln zzbln0, zzbmw zzbmw0) {
        return (Set)zzeep.zza(Collections.singleton(new zzbuv(zzbmw0, zzazq.zzdxp)), "Cannot return null from a non-@Nullable @Provides method");
    }
}

