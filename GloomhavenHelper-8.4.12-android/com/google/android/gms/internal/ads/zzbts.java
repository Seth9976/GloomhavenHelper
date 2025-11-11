package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbts implements zzeej {
    private final zzbtl zzflk;

    private zzbts(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(this.zzflk.zzain(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbts zzk(zzbtl zzbtl0) {
        return new zzbts(zzbtl0);
    }
}

