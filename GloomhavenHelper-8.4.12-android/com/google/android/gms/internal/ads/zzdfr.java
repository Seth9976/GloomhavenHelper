package com.google.android.gms.internal.ads;

public final class zzdfr implements zzeej {
    private final zzeew zzfet;
    private final zzdfp zzgri;
    private final zzeew zzgrj;

    private zzdfr(zzdfp zzdfp0, zzeew zzeew0, zzeew zzeew1) {
        this.zzgri = zzdfp0;
        this.zzfet = zzeew0;
        this.zzgrj = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzdfn)zzeep.zza(((zzdfl)this.zzfet.get()).zzgo(((String)this.zzgrj.get())), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzdfr zza(zzdfp zzdfp0, zzeew zzeew0, zzeew zzeew1) {
        return new zzdfr(zzdfp0, zzeew0, zzeew1);
    }
}

