package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzdbn implements zzdco {
    private final Executor executor;
    private zzbpr zzgnf;

    public zzdbn() {
        this.executor = zzdoh.zzauv();
    }

    @Override  // com.google.android.gms.internal.ads.zzdco
    public final zzdof zza(zzdcp zzdcp0, zzdcq zzdcq0) {
        zzbpq zzbpq0 = zzdcq0.zzc(zzdcp0.zzgoi);
        zzbpq0.zza(new zzdcu(true));
        this.zzgnf = (zzbpr)zzbpq0.zzadi();
        zzbod zzbod0 = this.zzgnf.zzadx();
        zzdgk zzdgk0 = new zzdgk();
        return zzdcp0.zzgoh == null ? zzdno.zzg(zzbod0.zzahp()).zzb(new zzdbq(this, zzdgk0, zzbod0), this.executor).zza(new zzdbp(zzdgk0), this.executor) : zzdno.zzg(zzbod0.zza(zzdcp0.zzgoh)).zzb(new zzdbq(this, zzdgk0, zzbod0), this.executor).zza(new zzdbp(zzdgk0), this.executor);
    }

    @Override  // com.google.android.gms.internal.ads.zzdco
    public final Object zzaqj() {
        return this.zzgnf;
    }
}

