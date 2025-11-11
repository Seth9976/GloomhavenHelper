package com.google.android.gms.internal.ads;

public final class zzbxp implements zzeej {
    private final zzbxk zzfmu;

    private zzbxp(zzbxk zzbxk0) {
        this.zzfmu = zzbxk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbxp.zze(this.zzfmu);
    }

    public static zzbxp zzd(zzbxk zzbxk0) {
        return new zzbxp(zzbxk0);
    }

    public static zzbzg zze(zzbxk zzbxk0) {
        return (zzbzg)zzeep.zza(zzbxk0.zzajk(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

