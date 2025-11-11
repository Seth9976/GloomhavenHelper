package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcme implements zzcly {
    private final Executor zzfeo;
    private final zzdeu zzfir;
    private final zzceb zzfrh;
    private final zzbku zzgbe;
    private final Context zzur;

    public zzcme(zzbku zzbku0, Context context0, Executor executor0, zzceb zzceb0, zzdeu zzdeu0) {
        this.zzur = context0;
        this.zzgbe = zzbku0;
        this.zzfeo = executor0;
        this.zzfrh = zzceb0;
        this.zzfir = zzdeu0;
    }

    final zzdof zza(zzdeq zzdeq0, zzdei zzdei0, Object object0) throws Exception {
        zzuk zzuk0 = zzdex.zzb(this.zzur, zzdei0.zzgps);
        zzbdv zzbdv0 = this.zzfrh.zzc(zzuk0);
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, null);
        zzbkq zzbkq0 = new zzbkq(zzbdv0.getView(), zzbdv0, zzdex.zze(zzuk0), zzdei0.zzffs, zzdei0.zzfft);
        zzbkr zzbkr0 = this.zzgbe.zza(zzbnv0, zzbkq0);
        zzbkr0.zzaek().zzb(zzbdv0, false);
        zzbkr0.zzadm().zza(new zzcmg(zzbdv0), zzazq.zzdxp);
        zzbkr0.zzaek();
        return zzdnt.zzb(zzced.zza(zzbdv0, zzdei0.zzgpq.zzdiu, zzdei0.zzgpq.zzdiw), new zzcmj(zzbkr0), zzazq.zzdxp);
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return zzdei0.zzgpq != null && zzdei0.zzgpq.zzdiw != null;
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        return zzdnt.zzb(zzdnt.zzaj(null), new zzcmh(this, zzdeq0, zzdei0), this.zzfeo);
    }
}

