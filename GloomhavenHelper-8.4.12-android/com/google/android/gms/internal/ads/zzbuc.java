package com.google.android.gms.internal.ads;

public final class zzbuc implements zzeej {
    private final zzbtl zzflk;

    private zzbuc(zzbtl zzbtl0) {
        this.zzflk = zzbtl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzbtl zzbtl0 = this.zzflk;
        if(zzbtl0 == null) {
            throw null;
        }
        return (zzbtl)zzeep.zza(zzbtl0, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbuc zzu(zzbtl zzbtl0) {
        return new zzbuc(zzbtl0);
    }
}

