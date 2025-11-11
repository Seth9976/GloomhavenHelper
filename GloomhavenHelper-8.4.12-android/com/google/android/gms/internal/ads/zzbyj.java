package com.google.android.gms.internal.ads;

public final class zzbyj implements zzeej {
    private final zzbyc zzfnz;

    public zzbyj(zzbyc zzbyc0) {
        this.zzfnz = zzbyc0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbyj.zza(this.zzfnz);
    }

    public static zzccv zza(zzbyc zzbyc0) {
        return (zzccv)zzeep.zza(zzbyc0.zzajx(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

