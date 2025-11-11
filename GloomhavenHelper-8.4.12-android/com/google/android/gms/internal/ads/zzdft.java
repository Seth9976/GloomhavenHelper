package com.google.android.gms.internal.ads;

public final class zzdft implements zzeej {
    private final zzdfp zzgri;
    private final zzeew zzgrk;

    private zzdft(zzdfp zzdfp0, zzeew zzeew0) {
        this.zzgri = zzdfp0;
        this.zzgrk = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzawc)zzeep.zza(((zzdfn)this.zzgrk.get()).zzdrf, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzdft zzb(zzdfp zzdfp0, zzeew zzeew0) {
        return new zzdft(zzdfp0, zzeew0);
    }
}

