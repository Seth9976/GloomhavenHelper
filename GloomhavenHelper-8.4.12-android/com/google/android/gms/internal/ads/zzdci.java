package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final class zzdci {
    private final Executor executor;
    private final zzdcq zzgnu;
    private final zzdfz zzgnx;
    private final zzdcn zzgoe;
    private zzdcm zzgof;

    public zzdci(zzdfz zzdfz0, zzdcn zzdcn0, zzdcq zzdcq0, Executor executor0) {
        this.zzgnx = zzdfz0;
        this.zzgoe = zzdcn0;
        this.zzgnu = zzdcq0;
        this.executor = executor0;
    }

    public final zzdof zzaqo() {
        zzdcm zzdcm0 = this.zzgof;
        if(zzdcm0 != null) {
            return zzdnt.zzaj(zzdcm0);
        }
        if(!((Boolean)zzabn.zzcvg.get()).booleanValue()) {
            this.zzgof = new zzdcm(null, this.zzaqp(), null);
            return zzdnt.zzb(zzdnt.zzaj(this.zzgof), zzdch.zzdpv, this.executor);
        }
        zzdno zzdno0 = zzdno.zzg(((zzbpr)this.zzgnu.zzc(this.zzgoe).zza(new zzdby(this.zzgnx.zzari().zzgsv)).zzadi()).zzadx().zza(this.zzgnx.zzari())).zza(new zzdcj(this), this.executor);
        zzdck zzdck0 = new zzdck(this);
        return zzdnt.zzb(zzdno0.zza(zzcjv.class, zzdck0, this.executor), zzdch.zzdpv, this.executor);
    }

    @Deprecated
    private final zzdgj zzaqp() {
        zzdeu zzdeu0 = ((zzbpr)this.zzgnu.zzc(this.zzgoe).zzadi()).zzaef();
        return this.zzgnx.zza(zzdeu0.zzgqq, zzdeu0.zzgqr, zzdeu0.zzgqu);
    }
}

