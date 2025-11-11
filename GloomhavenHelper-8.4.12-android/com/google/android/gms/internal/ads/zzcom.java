package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcom implements zzcmf {
    private final Executor zzfeo;
    private final zzbxo zzgcr;
    private final Context zzur;

    public zzcom(Context context0, zzbxo zzbxo0, Executor executor0) {
        this.zzur = context0;
        this.zzgcr = zzbxo0;
        this.zzfeo = executor0;
    }

    private static boolean zza(zzdeq zzdeq0, int v) {
        return zzdeq0.zzgql.zzfir.zzgqs.contains(Integer.toString(v));
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final void zza(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa {
        String s = zzayf.zza(zzdei0.zzgpq);
        ((zzdfb)zzcmd0.zzdel).zza(this.zzur, zzdeq0.zzgql.zzfir.zzgqq, zzdei0.zzgpt.toString(), s, ((zzalq)zzcmd0.zzgbd), zzdeq0.zzgql.zzfir.zzdff, zzdeq0.zzgql.zzfir.zzgqs);
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final Object zzb(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa, zzcpe {
        zzbyz zzbyz0;
        zzalx zzalx0 = ((zzdfb)zzcmd0.zzdel).zzsq();
        zzaly zzaly0 = ((zzdfb)zzcmd0.zzdel).zzsr();
        zzamd zzamd0 = ((zzdfb)zzcmd0.zzdel).zzsw();
        if(zzamd0 != null && zzcom.zza(zzdeq0, 6)) {
            zzbyz0 = zzbyz.zzb(zzamd0);
        }
        else {
            if(zzalx0 != null && zzcom.zza(zzdeq0, 6)) {
                zzbyz0 = zzbyz.zzb(zzalx0);
                goto label_17;
            }
            if(zzalx0 != null && zzcom.zza(zzdeq0, 2)) {
                zzbyz0 = zzbyz.zza(zzalx0);
                goto label_17;
            }
            if(zzaly0 != null && zzcom.zza(zzdeq0, 6)) {
                zzbyz0 = zzbyz.zzb(zzaly0);
            }
            else if(zzaly0 != null && zzcom.zza(zzdeq0, 1)) {
                zzbyz0 = zzbyz.zza(zzaly0);
            }
            else {
                throw new zzcpe("No native ad mappers", 0);
            }
        }
    label_17:
        String s = Integer.toString(zzbyz0.zzake());
        if(!zzdeq0.zzgql.zzfir.zzgqs.contains(s)) {
            throw new zzcpe("No corresponding native ad listener", 0);
        }
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, zzcmd0.zzfik);
        zzbzl zzbzl0 = new zzbzl(zzbyz0);
        zzcao zzcao0 = new zzcao(zzaly0, zzalx0, zzamd0);
        zzbza zzbza0 = this.zzgcr.zza(zzbnv0, zzbzl0, zzcao0);
        zzcqs zzcqs0 = zzbza0.zzado();
        ((zzcni)zzcmd0.zzgbd).zza(zzcqs0);
        zzbza0.zzadj().zza(new zzbjo(((zzdfb)zzcmd0.zzdel)), this.zzfeo);
        return zzbza0.zzadq();
    }
}

