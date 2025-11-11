package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zztq extends zzect {
    public String zzcac;
    private zzb[] zzcad;
    private zztf zzcae;
    private zztf zzcaf;
    private zztf zzcag;

    public zztq() {
        this.zzcac = null;
        this.zzcad = new zzb[0];
        this.zzcae = null;
        this.zzcaf = null;
        this.zzcag = null;
        this.zzhzu = null;
        this.zzhnu = -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    public final void zza(zzecr zzecr0) throws IOException {
        String s = this.zzcac;
        if(s != null) {
            zzecr0.zzf(1, s);
        }
        if(this.zzcad != null && this.zzcad.length > 0) {
            for(int v = 0; true; ++v) {
                zzb[] arr_zzsz$zzb = this.zzcad;
                if(v >= arr_zzsz$zzb.length) {
                    break;
                }
                zzb zzsz$zzb0 = arr_zzsz$zzb[v];
                if(zzsz$zzb0 != null) {
                    zzecr0.zze(2, zzsz$zzb0);
                }
            }
        }
        super.zza(zzecr0);
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    protected final int zzon() {
        int v = super.zzon();
        String s = this.zzcac;
        if(s != null) {
            v += zzecr.zzg(1, s);
        }
        if(this.zzcad != null && this.zzcad.length > 0) {
            for(int v1 = 0; true; ++v1) {
                zzb[] arr_zzsz$zzb = this.zzcad;
                if(v1 >= arr_zzsz$zzb.length) {
                    break;
                }
                zzb zzsz$zzb0 = arr_zzsz$zzb[v1];
                if(zzsz$zzb0 != null) {
                    v += zzdyi.zzc(2, zzsz$zzb0);
                }
            }
        }
        return v;
    }
}

