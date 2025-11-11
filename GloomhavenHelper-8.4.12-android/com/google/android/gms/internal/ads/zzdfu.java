package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzdfu implements zzeej {
    private final zzdfp zzgri;
    private final zzeew zzgrk;

    private zzdfu(zzdfp zzdfp0, zzeew zzeew0) {
        this.zzgri = zzdfp0;
        this.zzgrk = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzdfn zzdfn0 = (zzdfn)this.zzgrk.get();
        return zzdfu.zzb(this.zzgri, zzdfn0);
    }

    public static Context zzb(zzdfp zzdfp0, zzdfn zzdfn0) {
        return (Context)zzeep.zza(zzdfn0.zzyz, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzdfu zzc(zzdfp zzdfp0, zzeew zzeew0) {
        return new zzdfu(zzdfp0, zzeew0);
    }
}

