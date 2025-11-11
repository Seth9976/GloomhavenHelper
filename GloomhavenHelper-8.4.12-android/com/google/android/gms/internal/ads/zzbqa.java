package com.google.android.gms.internal.ads;

public final class zzbqa implements zzeej {
    private final zzbpt zzfkc;

    private zzbqa(zzbpt zzbpt0) {
        this.zzfkc = zzbpt0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbqa.zzn(this.zzfkc);
    }

    public static zzbqa zzm(zzbpt zzbpt0) {
        return new zzbqa(zzbpt0);
    }

    public static zzdeu zzn(zzbpt zzbpt0) {
        return (zzdeu)zzeep.zza(zzbpt0.zzahv(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

