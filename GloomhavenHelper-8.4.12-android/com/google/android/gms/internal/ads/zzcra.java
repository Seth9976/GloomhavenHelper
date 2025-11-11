package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

public final class zzcra implements zzcly {
    private final zzdif zzfis;
    private final zzdoe zzgef;
    @Nullable
    private final zzaaq zzgem;
    private final zzcrf zzgeq;

    public zzcra(zzdif zzdif0, zzdoe zzdoe0, @Nullable zzaaq zzaaq0, zzcrf zzcrf0) {
        this.zzfis = zzdif0;
        this.zzgef = zzdoe0;
        this.zzgem = zzaaq0;
        this.zzgeq = zzcrf0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return this.zzgem != null && zzdei0.zzgpq != null && zzdei0.zzgpq.zzdiw != null;
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        zzazy zzazy0 = new zzazy();
        zzcri zzcri0 = new zzcri();
        zzcri0.zza(new zzcrc(this, zzazy0, zzdeq0, zzdei0, zzcri0));
        zzaan zzaan0 = new zzaan(zzcri0, zzdei0.zzgpq.zzdiu, zzdei0.zzgpq.zzdiw);
        return this.zzfis.zzu(zzdig.zzgvu).zza(new zzcrd(this, zzaan0), this.zzgef).zzw(zzdig.zzgvv).zze(zzazy0).zzata();
    }

    final void zzb(zzaan zzaan0) throws Exception {
        this.zzgem.zza(zzaan0);
    }
}

