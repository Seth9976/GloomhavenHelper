package com.google.android.gms.internal.ads;

public final class zzbpl implements zzeej {
    private final zzeew zzfjm;

    private zzbpl(zzeew zzeew0) {
        this.zzfjm = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbsf)zzeep.zza(((zzbsf)this.zzfjm.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbpl zzf(zzeew zzeew0) {
        return new zzbpl(zzeew0);
    }
}

