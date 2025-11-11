package com.google.android.gms.internal.ads;

final class zzcpz implements zzdku {
    private final zzdei zzfgp;
    private final zzbdv zzfsx;
    private final zzcpv zzgdo;
    private final zzcdp zzgdp;

    zzcpz(zzcpv zzcpv0, zzbdv zzbdv0, zzdei zzdei0, zzcdp zzcdp0) {
        this.zzgdo = zzcpv0;
        this.zzfsx = zzbdv0;
        this.zzfgp = zzdei0;
        this.zzgdp = zzcdp0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdku
    public final Object apply(Object object0) {
        zzbdv zzbdv0 = this.zzfsx;
        zzcdp zzcdp0 = this.zzgdp;
        if(this.zzfgp.zzdnk) {
            zzbdv0.zzaas();
        }
        zzbdv0.zzzz();
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjj)).booleanValue()) {
            zzawu.zza(zzbdv0);
        }
        return zzcdp0.zzafd();
    }
}

