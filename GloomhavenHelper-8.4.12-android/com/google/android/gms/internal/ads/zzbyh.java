package com.google.android.gms.internal.ads;

public final class zzbyh implements zzeej {
    private final zzeew zzenl;
    private final zzbyc zzfnz;

    public zzbyh(zzbyc zzbyc0, zzeew zzeew0) {
        this.zzfnz = zzbyc0;
        this.zzenl = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbzd)zzeep.zza(((zzbzc)this.zzenl.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}

