package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzdbx implements zzdco {
    private final Executor executor;
    private final zzdfz zzgnx;
    private zzdnu zzgny;

    public zzdbx(zzdfz zzdfz0, Executor executor0) {
        this.zzgny = new zzdcc(this);
        this.zzgnx = zzdfz0;
        this.executor = executor0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdco
    public final zzdof zza(zzdcp zzdcp0, zzdcq zzdcq0) {
        zzdno zzdno0 = zzdno.zzg(new zzdci(this.zzgnx, zzdcp0.zzgoi, zzdcq0, this.executor).zzaqo()).zzb(new zzdca(this, zzdcp0, zzdcq0), this.executor);
        zzdbz zzdbz0 = new zzdbz(this);
        return zzdno0.zza(Exception.class, zzdbz0, this.executor);
    }

    final zzdof zza(zzdcp zzdcp0, zzdcq zzdcq0, zzdcm zzdcm0) throws Exception {
        zzdgj zzdgj0 = zzdcm0.zzgnw;
        zzaqx zzaqx0 = zzdcm0.zzfyj;
        zzdgk zzdgk0 = zzdgj0 == null ? null : this.zzgnx.zza(zzdgj0);
        if(zzdgj0 == null) {
            return zzdnt.zzaj(null);
        }
        if(zzdgk0 != null && zzaqx0 != null) {
            zzdnt.zza(((zzbpr)zzdcq0.zzc(zzdcp0.zzgoi).zzadi()).zzadx().zzc(zzaqx0), this.zzgny, this.executor);
        }
        return zzdnt.zzaj(new zzdcb(zzdgj0, zzaqx0, zzdgk0));
    }

    @Override  // com.google.android.gms.internal.ads.zzdco
    public final Object zzaqj() {
        return null;
    }
}

