package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcpi implements zzcmf {
    private final Executor zzfeo;
    private final zzcdq zzgdj;
    private final Context zzur;

    public zzcpi(Context context0, Executor executor0, zzcdq zzcdq0) {
        this.zzur = context0;
        this.zzfeo = executor0;
        this.zzgdj = zzcdq0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final void zza(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa {
        try {
            ((zzdfb)zzcmd0.zzdel).zzb(this.zzur, zzdeq0.zzgql.zzfir.zzgqq, zzdei0.zzgpt.toString(), ((zzalq)zzcmd0.zzgbd));
        }
        catch(Exception exception0) {
            String s = String.valueOf(zzcmd0.zzfik);
            zzawf.zzd((s.length() == 0 ? new String("Fail to load ad from adapter ") : "Fail to load ad from adapter " + s), exception0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final Object zzb(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa, zzcpe {
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, zzcmd0.zzfik);
        zzcdo zzcdo0 = new zzcdo(new zzcpl(zzcmd0));
        zzcdp zzcdp0 = this.zzgdj.zza(zzbnv0, zzcdo0);
        zzcdp0.zzadj().zza(new zzbjo(((zzdfb)zzcmd0.zzdel)), this.zzfeo);
        zzcpq zzcpq0 = zzcdp0.zzaff();
        ((zzcni)zzcmd0.zzgbd).zza(zzcpq0);
        return zzcdp0.zzafd();
    }
}

