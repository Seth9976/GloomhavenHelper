package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzb;
import java.util.concurrent.Executor;

public final class zzcmt implements zzcmf {
    private final zzazo zzblr;
    private final Executor zzfeo;
    private final zzbmc zzgbj;
    private final Context zzur;

    public zzcmt(Context context0, zzazo zzazo0, zzbmc zzbmc0, Executor executor0) {
        this.zzur = context0;
        this.zzblr = zzazo0;
        this.zzgbj = zzbmc0;
        this.zzfeo = executor0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final void zza(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa {
        zzuk zzuk1;
        zzuk zzuk0 = zzdeq0.zzgql.zzfir.zzblv;
        if(zzuk0.zzcdj) {
            AdSize adSize0 = zzb.zza(zzuk0.width, zzuk0.height);
            zzuk1 = new zzuk(this.zzur, adSize0);
        }
        else {
            zzuk1 = zzdex.zzb(this.zzur, zzdei0.zzgps);
        }
        if(this.zzblr.zzdxg < 4100000) {
            ((zzdfb)zzcmd0.zzdel).zza(this.zzur, zzuk1, zzdeq0.zzgql.zzfir.zzgqq, zzdei0.zzgpt.toString(), ((zzalq)zzcmd0.zzgbd));
            return;
        }
        String s = zzayf.zza(zzdei0.zzgpq);
        ((zzdfb)zzcmd0.zzdel).zza(this.zzur, zzuk1, zzdeq0.zzgql.zzfir.zzgqq, zzdei0.zzgpt.toString(), s, ((zzalq)zzcmd0.zzgbd));
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final Object zzb(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa, zzcpe {
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, zzcmd0.zzfik);
        View view0 = ((zzdfb)zzcmd0.zzdel).getView();
        ((zzdfb)zzcmd0.zzdel).getClass();
        zzbln zzbln0 = new zzbln(view0, null, zzcms.zza(((zzdfb)zzcmd0.zzdel)), ((zzdeh)zzdei0.zzgps.get(0)));
        zzblj zzblj0 = this.zzgbj.zza(zzbnv0, zzbln0);
        zzblj0.zzaep().zzq(((zzdfb)zzcmd0.zzdel).getView());
        zzblj0.zzadj().zza(new zzbjo(((zzdfb)zzcmd0.zzdel)), this.zzfeo);
        zzcqs zzcqs0 = zzblj0.zzado();
        ((zzcni)zzcmd0.zzgbd).zza(zzcqs0);
        return zzblj0.zzaeo();
    }
}

