package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

public final class zzbug implements zzeej {
    private final zzeew zzfex;
    private final zzbtl zzflk;

    private zzbug(zzbtl zzbtl0, zzeew zzeew0) {
        this.zzflk = zzbtl0;
        this.zzfex = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Clock clock0 = (Clock)this.zzfex.get();
        return (zzcpc)zzeep.zza(this.zzflk.zza(clock0), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbug zzb(zzbtl zzbtl0, zzeew zzeew0) {
        return new zzbug(zzbtl0, zzeew0);
    }
}

