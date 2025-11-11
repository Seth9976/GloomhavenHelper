package com.google.android.gms.internal.ads;

public final class zzbyg implements zzeej {
    private final zzbyc zzfnz;
    private final zzeew zzfoa;

    public zzbyg(zzbyc zzbyc0, zzeew zzeew0) {
        this.zzfnz = zzbyc0;
        this.zzfoa = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbzh)zzeep.zza(((zzbxz)this.zzfoa.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}

