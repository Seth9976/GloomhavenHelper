package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzblo implements zzeej {
    private final zzbln zzfgr;
    private final zzeew zzfgs;

    public zzblo(zzbln zzbln0, zzeew zzeew0) {
        this.zzfgr = zzbln0;
        this.zzfgs = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Set set0 = (Set)this.zzfgs.get();
        return zzblo.zza(this.zzfgr, set0);
    }

    public static zzbrm zza(zzbln zzbln0, Set set0) {
        return (zzbrm)zzeep.zza(zzbln0.zza(set0), "Cannot return null from a non-@Nullable @Provides method");
    }
}

