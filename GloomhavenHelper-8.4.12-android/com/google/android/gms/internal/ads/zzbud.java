package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbud implements zzeej {
    private final zzbtl zzflk;

    private zzbud(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(this.zzflk.zzaim(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbud zzv(zzbtl zzbtl0) {
        return new zzbud(zzbtl0);
    }
}

