package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

public final class zzcpv implements zzcly {
    private final zzazo zzblr;
    private final Executor zzfeo;
    private final zzdeu zzfir;
    private final zzceb zzfrh;
    private final zzcdq zzgdj;
    private final Context zzur;

    public zzcpv(Context context0, zzazo zzazo0, zzdeu zzdeu0, Executor executor0, zzcdq zzcdq0, zzceb zzceb0) {
        this.zzur = context0;
        this.zzfir = zzdeu0;
        this.zzgdj = zzcdq0;
        this.zzfeo = executor0;
        this.zzblr = zzazo0;
        this.zzfrh = zzceb0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return zzdei0.zzgpq != null && zzdei0.zzgpq.zzdiw != null;
    }

    // 检测为 Lambda 实现
    final zzdof zzb(zzdei zzdei0, zzcer zzcer0, zzdeq zzdeq0, Object object0) throws Exception [...]

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        zzcer zzcer0 = new zzcer();
        zzdof zzdof0 = zzdnt.zzb(zzdnt.zzaj(null), (Object object0) -> {
            zzbdv zzbdv0 = this.zzfrh.zza(this.zzfir.zzblv, zzdei0.zzehg);
            zzbdv0.zzba(zzdei0.zzdmq);
            View view0 = zzbdv0.getView();
            zzcer0.zza(this.zzur, view0);
            zzazy zzazy0 = new zzazy();
            zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, null);
            zzcdo zzcdo0 = new zzcdo(new zzcqb(this.zzur, this.zzfrh, this.zzfir, this.zzblr, zzdei0, zzazy0, zzbdv0, null), zzbdv0);
            zzcdp zzcdp0 = this.zzgdj.zza(zzbnv0, zzcdo0);
            zzazy0.set(zzcdp0);
            zzagg.zza(zzbdv0, zzcdp0.zzafe());
            zzcdp0.zzadm().zza(new zzcpw(zzbdv0), zzazq.zzdxp);
            zzcdp0.zzaek().zzb(zzbdv0, true);
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcrj)).booleanValue() && zzdei0.zzehg) {
                return zzdnt.zzb(zzdnt.zzaj(null), new zzcpz(this, zzbdv0, zzdei0, zzcdp0), this.zzfeo);
            }
            zzcdp0.zzaek();
            return zzdnt.zzb(zzced.zza(zzbdv0, zzdei0.zzgpq.zzdiu, zzdei0.zzgpq.zzdiw), new zzcpz(this, zzbdv0, zzdei0, zzcdp0), this.zzfeo);
        }, this.zzfeo);
        zzcer0.getClass();
        zzdof0.addListener(zzcpx.zza(zzcer0), this.zzfeo);
        return zzdof0;
    }
}

