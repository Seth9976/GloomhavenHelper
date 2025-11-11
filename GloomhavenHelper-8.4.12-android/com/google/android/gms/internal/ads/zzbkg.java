package com.google.android.gms.internal.ads;

public final class zzbkg implements zzeej {
    private final zzeew zzffg;

    private zzbkg(zzeew zzeew0) {
        this.zzffg = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzakt)zzeep.zza(((zzakk)this.zzffg.get()).zzsm(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbkg zzb(zzeew zzeew0) {
        return new zzbkg(zzeew0);
    }
}

