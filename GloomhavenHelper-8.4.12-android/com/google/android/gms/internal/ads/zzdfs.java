package com.google.android.gms.internal.ads;

public final class zzdfs implements zzeej {
    private final zzdfp zzgri;
    private final zzeew zzgrk;

    private zzdfs(zzdfp zzdfp0, zzeew zzeew0) {
        this.zzgri = zzdfp0;
        this.zzgrk = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzdfn zzdfn0 = (zzdfn)this.zzgrk.get();
        return zzdfs.zza(this.zzgri, zzdfn0);
    }

    public static zzawh zza(zzdfp zzdfp0, zzdfn zzdfn0) {
        return (zzawh)zzeep.zza(zzdfn0.zzdsq, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzdfs zza(zzdfp zzdfp0, zzeew zzeew0) {
        return new zzdfs(zzdfp0, zzeew0);
    }
}

