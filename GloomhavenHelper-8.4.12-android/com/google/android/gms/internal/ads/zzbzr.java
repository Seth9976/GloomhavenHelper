package com.google.android.gms.internal.ads;

public final class zzbzr implements zzeej {
    private final zzbzl zzfqb;

    private zzbzr(zzbzl zzbzl0) {
        this.zzfqb = zzbzl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbzr.zzc(this.zzfqb);
    }

    public static zzbzr zzb(zzbzl zzbzl0) {
        return new zzbzr(zzbzl0);
    }

    public static zzbyz zzc(zzbzl zzbzl0) {
        return (zzbyz)zzeep.zza(zzbzl0.zzalc(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

