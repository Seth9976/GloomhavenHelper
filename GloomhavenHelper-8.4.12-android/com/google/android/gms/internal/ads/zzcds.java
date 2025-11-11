package com.google.android.gms.internal.ads;

public final class zzcds implements zzeej {
    private final zzeew zzfeu;
    private final zzeew zzfgu;
    private final zzeew zzfud;

    public zzcds(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfeu = zzeew0;
        this.zzfud = zzeew1;
        this.zzfgu = zzeew2;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return ((zzdeu)this.zzfgu.get()).zzgqx.contains("new_rewarded") ? ((zzcly)zzeep.zza(((zzcly)this.zzfud.get()), "Cannot return null from a non-@Nullable @Provides method")) : ((zzcly)zzeep.zza(((zzcly)this.zzfeu.get()), "Cannot return null from a non-@Nullable @Provides method"));
    }
}

