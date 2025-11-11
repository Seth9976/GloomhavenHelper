package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbwc implements zzeej {
    private final zzeew zzffv;
    private final zzbvz zzfmc;

    private zzbwc(zzbvz zzbvz0, zzeew zzeew0) {
        this.zzfmc = zzbvz0;
        this.zzffv = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzbxc zzbxc0 = (zzbxc)this.zzffv.get();
        return (Set)zzeep.zza(this.zzfmc.zza(zzbxc0), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbwc zza(zzbvz zzbvz0, zzeew zzeew0) {
        return new zzbwc(zzbvz0, zzeew0);
    }
}

