package com.google.android.gms.internal.ads;

public final class zzbnu implements zzeej {
    private final zzbnv zzfij;

    private zzbnu(zzbnv zzbnv0) {
        this.zzfij = zzbnv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbnu.zzb(this.zzfij);
    }

    public static zzbnu zza(zzbnv zzbnv0) {
        return new zzbnu(zzbnv0);
    }

    public static zzdei zzb(zzbnv zzbnv0) {
        return (zzdei)zzeep.zza(zzbnv0.zzahn(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

