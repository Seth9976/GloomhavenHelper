package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcqf implements zzcmf {
    private final Executor zzfeo;
    private final zzcdq zzgdj;
    private final Context zzur;

    public zzcqf(Context context0, Executor executor0, zzcdq zzcdq0) {
        this.zzur = context0;
        this.zzfeo = executor0;
        this.zzgdj = zzcdq0;
    }

    static void zza(zzcqf zzcqf0, zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) {
        zzcqf.zzc(zzdeq0, zzdei0, zzcmd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final void zza(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa {
        if(!((zzdfb)zzcmd0.zzdel).isInitialized()) {
            zzcqh zzcqh0 = new zzcqh(this, zzdeq0, zzdei0, zzcmd0);
            ((zzcnl)zzcmd0.zzgbd).zza(zzcqh0);
            ((zzdfb)zzcmd0.zzdel).zza(this.zzur, zzdeq0.zzgql.zzfir.zzgqq, null, ((zzasm)zzcmd0.zzgbd), zzdei0.zzgpt.toString());
            return;
        }
        zzcqf.zzc(zzdeq0, zzdei0, zzcmd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final Object zzb(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa, zzcpe {
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, zzcmd0.zzfik);
        zzcdo zzcdo0 = new zzcdo(new zzcqe(zzcmd0));
        zzcdp zzcdp0 = this.zzgdj.zza(zzbnv0, zzcdo0);
        zzcdp0.zzadj().zza(new zzbjo(((zzdfb)zzcmd0.zzdel)), this.zzfeo);
        zzbrc zzbrc0 = zzcdp0.zzadk();
        zzbqg zzbqg0 = zzcdp0.zzadl();
        zzcqj zzcqj0 = new zzcqj(this, zzcdp0.zzaey(), zzbqg0, zzbrc0, zzcdp0.zzafe());
        ((zzcnl)zzcmd0.zzgbd).zza(zzcqj0);
        return zzcdp0.zzafd();
    }

    private static void zzc(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) {
        try {
            ((zzdfb)zzcmd0.zzdel).zza(zzdeq0.zzgql.zzfir.zzgqq, zzdei0.zzgpt.toString());
        }
        catch(Exception exception0) {
            String s = String.valueOf(zzcmd0.zzfik);
            zzawf.zzd((s.length() == 0 ? new String("Fail to load ad from adapter ") : "Fail to load ad from adapter " + s), exception0);
        }
    }
}

