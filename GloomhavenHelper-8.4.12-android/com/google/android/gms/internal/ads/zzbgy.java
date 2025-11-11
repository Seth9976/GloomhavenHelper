package com.google.android.gms.internal.ads;

public final class zzbgy implements zzeej {
    private final zzbgn zzeky;

    public zzbgy(zzbgn zzbgn0) {
        this.zzeky = zzbgn0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbgy.zzb(this.zzeky);
    }

    public static zzazo zzb(zzbgn zzbgn0) {
        return (zzazo)zzeep.zza(zzbgn0.zzacw(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

