package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbtp implements zzeej {
    private final zzbtl zzflk;
    private final zzeew zzfll;

    private zzbtp(zzbtl zzbtl0, zzeew zzeew0) {
        this.zzflk = zzbtl0;
        this.zzfll = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Set set0 = (Set)this.zzfll.get();
        return (zzbqk)zzeep.zza(this.zzflk.zzc(set0), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbtp zza(zzbtl zzbtl0, zzeew zzeew0) {
        return new zzbtp(zzbtl0, zzeew0);
    }
}

