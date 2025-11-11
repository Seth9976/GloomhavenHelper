package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

public final class zzcml implements zzcly {
    private final Executor zzfeo;
    private final zzdeu zzfir;
    private final zzceb zzfrh;
    private final zzbmc zzgbj;
    private final zzdku zzgbk;
    private final Context zzur;

    public zzcml(zzbmc zzbmc0, Context context0, Executor executor0, zzceb zzceb0, zzdeu zzdeu0, zzdku zzdku0) {
        this.zzur = context0;
        this.zzgbj = zzbmc0;
        this.zzfeo = executor0;
        this.zzfrh = zzceb0;
        this.zzfir = zzdeu0;
        this.zzgbk = zzdku0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return zzdei0.zzgpq != null && zzdei0.zzgpq.zzdiw != null;
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        return zzdnt.zzb(zzdnt.zzaj(null), (Object object0) -> {
            zzuk zzuk0 = zzdex.zzb(this.zzur, zzdei0.zzgps);
            zzbdv zzbdv0 = this.zzfrh.zzc(zzuk0);
            zzbdv0.zzba(zzdei0.zzdmq);
            zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, null);
            View view0 = zzbdv0.getView();
            zzaxg zzaxg0 = (zzaxg)this.zzgbk.apply(zzdei0);
            zzceo zzceo0 = new zzceo(this.zzur, view0, zzaxg0);
            zzbdv0.getClass();
            zzbln zzbln0 = new zzbln(zzceo0, zzbdv0, zzcmn.zzp(zzbdv0), zzdex.zze(zzuk0));
            zzblj zzblj0 = this.zzgbj.zza(zzbnv0, zzbln0);
            zzblj0.zzaek().zzb(zzbdv0, false);
            zzblj0.zzadm().zza(new zzcmm(zzbdv0), zzazq.zzdxp);
            zzblj0.zzaek();
            zzdof zzdof0 = zzced.zza(zzbdv0, zzdei0.zzgpq.zzdiu, zzdei0.zzgpq.zzdiw);
            if(zzdei0.zzdnk) {
                zzbdv0.getClass();
                zzdof0.addListener(zzcmp.zzh(zzbdv0), this.zzfeo);
            }
            zzdof0.addListener(() -> {
                zzbdv0.zzzz();
                zzbeq zzbeq0 = zzbdv0.zzyq();
                if(this.zzfir.zzgqp != null && zzbeq0 != null) {
                    zzbeq0.zzb(this.zzfir.zzgqp);
                }
            }, this.zzfeo);
            return zzdnt.zzb(zzdof0, new zzcmr(zzblj0), zzazq.zzdxp);
        }, this.zzfeo);
    }

    // 检测为 Lambda 实现
    final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0, Object object0) throws Exception [...]

    // 检测为 Lambda 实现
    final void zzo(zzbdv zzbdv0) [...]
}

