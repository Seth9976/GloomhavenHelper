package com.google.android.gms.internal.ads;

public final class zzbxm implements zzeej {
    private final zzbxk zzfmu;

    private zzbxm(zzbxk zzbxk0) {
        this.zzfmu = zzbxk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzbxk zzbxk0 = this.zzfmu;
        if(zzbxk0 == null) {
            throw null;
        }
        return (zzbxk)zzeep.zza(zzbxk0, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbxm zzc(zzbxk zzbxk0) {
        return new zzbxm(zzbxk0);
    }
}

