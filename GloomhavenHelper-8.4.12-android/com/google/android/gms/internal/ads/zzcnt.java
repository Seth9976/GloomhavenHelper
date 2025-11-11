package com.google.android.gms.internal.ads;

final class zzcnt implements zzdku {
    private final zzdei zzfgp;
    private final zzbdv zzfsx;
    private final zzcnp zzgch;
    private final zzbvw zzgcm;

    zzcnt(zzcnp zzcnp0, zzbdv zzbdv0, zzdei zzdei0, zzbvw zzbvw0) {
        this.zzgch = zzcnp0;
        this.zzfsx = zzbdv0;
        this.zzfgp = zzdei0;
        this.zzgcm = zzbvw0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdku
    public final Object apply(Object object0) {
        zzbdv zzbdv0 = this.zzfsx;
        zzbvw zzbvw0 = this.zzgcm;
        if(this.zzfgp.zzdnk) {
            zzbdv0.zzaas();
        }
        zzbdv0.zzzz();
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjj)).booleanValue()) {
            zzawu.zza(zzbdv0);
        }
        return zzbvw0.zzaex();
    }
}

