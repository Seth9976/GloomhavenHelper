package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public final class zzdbs implements zzdco {
    private final Executor executor;
    private final zzdco zzgmg;
    private final zzdco zzgnp;
    private final zzdco zzgnq;
    private final zzdgs zzgnr;
    @GuardedBy("this")
    private zzbpr zzgns;

    public zzdbs(zzdco zzdco0, zzdco zzdco1, zzdco zzdco2, zzdgs zzdgs0, Executor executor0) {
        this.zzgmg = zzdco0;
        this.zzgnp = zzdco1;
        this.zzgnq = zzdco2;
        this.zzgnr = zzdgs0;
        this.executor = executor0;
    }

    private final zzdof zza(zzdgk zzdgk0, zzdcp zzdcp0, zzdcq zzdcq0) {
        zzbpq zzbpq0 = zzdcq0.zzc(zzdcp0.zzgoi);
        if(zzdgk0.zzgtl != null) {
            zzbpr zzbpr0 = (zzbpr)zzbpq0.zzadi();
            zzdgk0.zzgtl.zzahj().zzb(zzbpr0.zzaeg());
            return zzdnt.zzaj(zzdgk0.zzgtl);
        }
        zzbpq0.zza(zzdgk0.zzenb);
        zzdbu zzdbu0 = new zzdbu(zzbpq0);
        zzdof zzdof0 = this.zzgmg.zza(zzdcp0, zzdbu0);
        this.zzgns = (zzbpr)this.zzgmg.zzaqj();
        return zzdof0;
    }

    // 检测为 Lambda 实现
    final zzdof zza(zzdcp zzdcp0, zzdbv zzdbv0, zzdcq zzdcq0, zzdcb zzdcb0) throws Exception [...]

    @Override  // com.google.android.gms.internal.ads.zzdco
    public final zzdof zza(zzdcp zzdcp0, zzdcq zzdcq0) {
        synchronized(this) {
            zzdeu zzdeu0 = ((zzbpr)zzdcq0.zzc(zzdcp0.zzgoi).zzadi()).zzaef();
            zzdbv zzdbv0 = new zzdbv(zzdcq0, zzdcp0, zzdeu0.zzgqq, zzdeu0.zzgqr, this.executor, zzdeu0.zzgqu, null);
            return zzdno.zzg(this.zzgnp.zza(zzdcp0, zzdcq0)).zzb((zzdcb zzdcb0) -> {
                if(zzdcb0 != null) {
                    zzdbv zzdbv1 = new zzdbv(zzdbv0.zzgnu, zzdbv0.zzgnv, zzdbv0.zzdjt, zzdbv0.zzbri, zzdbv0.executor, zzdbv0.zzgif, zzdcb0.zzgnw);
                    if(zzdcb0.zzgoc != null) {
                        this.zzgns = null;
                        this.zzgnr.zzb(zzdbv1);
                        return this.zza(zzdcb0.zzgoc, zzdcp0, zzdcq0);
                    }
                    zzdof zzdof0 = this.zzgnr.zzc(zzdbv1);
                    if(zzdof0 != null) {
                        this.zzgns = (zzbpr)this.zzgnq.zzaqj();
                        return zzdnt.zzb(zzdof0, new zzdbt(this), this.executor);
                    }
                    this.zzgnr.zzb(zzdbv1);
                    zzdcp0 = new zzdcp(zzdcp0.zzgoi, zzdcb0.zzfyj);
                }
                zzdof zzdof1 = this.zzgmg.zza(zzdcp0, zzdcq0);
                this.zzgns = (zzbpr)this.zzgmg.zzaqj();
                return zzdof1;
            }, this.executor);
        }
    }

    final zzdof zza(zzdgw zzdgw0) throws Exception {
        if(zzdgw0 == null || zzdgw0.zzgoc == null || zzdgw0.zzgua == null) {
            throw new zzcke("Empty prefetch");
        }
        zza zzsz$zza0 = (zza)(((zzdyz)zza.zzne().zza(com.google.android.gms.internal.ads.zzsz.zza.zza.zznc().zzb(zzc.zzbuz).zza(zzd.zzng())).zzbcx()));
        zzdgw0.zzgoc.zzgtk.zzahr().zzc(zzsz$zza0);
        return this.zza(zzdgw0.zzgoc, ((zzdbv)zzdgw0.zzgua).zzgnv, ((zzdbv)zzdgw0.zzgua).zzgnu);
    }

    @Override  // com.google.android.gms.internal.ads.zzdco
    public final Object zzaqj() {
        return this.zzaqk();
    }

    private final zzbpr zzaqk() {
        synchronized(this) {
        }
        return this.zzgns;
    }
}

