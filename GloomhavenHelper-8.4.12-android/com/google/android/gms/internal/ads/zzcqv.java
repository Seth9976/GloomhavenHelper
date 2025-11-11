package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import androidx.annotation.Nullable;

public final class zzcqv implements zzcly {
    private final zzdif zzfis;
    private final zzbmc zzgbj;
    private final zzdoe zzgef;
    private final Context zzgel;
    @Nullable
    private final zzaaq zzgem;

    public zzcqv(Context context0, zzbmc zzbmc0, zzdif zzdif0, zzdoe zzdoe0, @Nullable zzaaq zzaaq0) {
        this.zzgel = context0;
        this.zzgbj = zzbmc0;
        this.zzfis = zzdif0;
        this.zzgef = zzdoe0;
        this.zzgem = zzaaq0;
    }

    final void zza(zzaan zzaan0) throws Exception {
        this.zzgem.zza(zzaan0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return this.zzgem != null && zzdei0.zzgpq != null && zzdei0.zzgpq.zzdiw != null;
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        View view0 = new View(this.zzgel);
        Object object0 = zzdei0.zzgps.get(0);
        zzcqw zzcqw0 = new zzcqw(this, view0, null, zzcqu.zzgek, ((zzdeh)object0));
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, null);
        zzblj zzblj0 = this.zzgbj.zza(zzbnv0, zzcqw0);
        zzaan zzaan0 = new zzaan(zzblj0.zzaeq(), zzdei0.zzgpq.zzdiu, zzdei0.zzgpq.zzdiw);
        return this.zzfis.zzu(zzdig.zzgvu).zza(new zzcqx(this, zzaan0), this.zzgef).zzw(zzdig.zzgvv).zze(zzdnt.zzaj(zzblj0.zzaeo())).zzata();
    }
}

