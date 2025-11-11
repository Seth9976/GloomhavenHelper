package com.google.android.gms.internal.ads;

public final class zzdai implements zzeej {
    private final zzdaf zzgmb;

    public zzdai(zzdaf zzdaf0) {
        this.zzgmb = zzdaf0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzdai.zzc(this.zzgmb);
    }

    public static String zzc(zzdaf zzdaf0) {
        return (String)zzeep.zza(zzdaf0.zzapv(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

