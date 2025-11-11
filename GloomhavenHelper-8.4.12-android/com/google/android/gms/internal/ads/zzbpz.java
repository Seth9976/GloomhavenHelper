package com.google.android.gms.internal.ads;

public final class zzbpz implements zzeej {
    private final zzbpt zzfkc;

    private zzbpz(zzbpt zzbpt0) {
        this.zzfkc = zzbpt0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zza)zzeep.zza(this.zzfkc.zzahu(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbpz zzl(zzbpt zzbpt0) {
        return new zzbpz(zzbpt0);
    }
}

