package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeUnit;

public final class zzctf implements zzeej {
    private final zzeew zzfgx;
    private final zzeew zzghj;
    private final zzeew zzghk;

    public zzctf(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfgx = zzeew0;
        this.zzghj = zzeew1;
        this.zzghk = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzdif zzdif0 = (zzdif)this.zzfgx.get();
        zzcti zzcti0 = (zzcti)this.zzghj.get();
        zzdof zzdof0 = ((zzbpm)this.zzghk.get()).zzaht();
        return (zzdof)zzeep.zza(zzdif0.zza(zzdig.zzgvx, zzdof0).zza(zzcti0).zza(((long)(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcpu)))))), TimeUnit.SECONDS).zzata(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

