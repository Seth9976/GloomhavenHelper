package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zztr extends zzect {
    public Integer zzcah;
    private zztf zzcai;
    private zzc zzcaj;
    public zztq zzcak;
    private zzb[] zzcal;
    private zzd zzcam;
    private zzk zzcan;
    private zzi zzcao;
    private zzf zzcap;
    private zzg zzcaq;
    private zztx[] zzcar;

    public zztr() {
        this.zzcah = null;
        this.zzcai = null;
        this.zzcaj = null;
        this.zzcak = null;
        this.zzcal = new zzb[0];
        this.zzcam = null;
        this.zzcan = null;
        this.zzcao = null;
        this.zzcap = null;
        this.zzcaq = null;
        this.zzcar = zztx.zzoo();
        this.zzhzu = null;
        this.zzhnu = -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    public final void zza(zzecr zzecr0) throws IOException {
        Integer integer0 = this.zzcah;
        if(integer0 != null) {
            zzecr0.zzac(7, ((int)integer0));
        }
        zztq zztq0 = this.zzcak;
        if(zztq0 != null) {
            zzecr0.zza(10, zztq0);
        }
        if(this.zzcal != null && this.zzcal.length > 0) {
            for(int v1 = 0; true; ++v1) {
                zzb[] arr_zzsz$zzb = this.zzcal;
                if(v1 >= arr_zzsz$zzb.length) {
                    break;
                }
                zzb zzsz$zzb0 = arr_zzsz$zzb[v1];
                if(zzsz$zzb0 != null) {
                    zzecr0.zze(11, zzsz$zzb0);
                }
            }
        }
        if(this.zzcar != null && this.zzcar.length > 0) {
            for(int v = 0; true; ++v) {
                zztx[] arr_zztx = this.zzcar;
                if(v >= arr_zztx.length) {
                    break;
                }
                zztx zztx0 = arr_zztx[v];
                if(zztx0 != null) {
                    zzecr0.zza(17, zztx0);
                }
            }
        }
        super.zza(zzecr0);
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    protected final int zzon() {
        int v = super.zzon();
        Integer integer0 = this.zzcah;
        if(integer0 != null) {
            v += zzecr.zzag(7, ((int)integer0));
        }
        zztq zztq0 = this.zzcak;
        if(zztq0 != null) {
            v += zzecr.zzb(10, zztq0);
        }
        if(this.zzcal != null && this.zzcal.length > 0) {
            int v2 = v;
            for(int v3 = 0; true; ++v3) {
                zzb[] arr_zzsz$zzb = this.zzcal;
                if(v3 >= arr_zzsz$zzb.length) {
                    break;
                }
                zzb zzsz$zzb0 = arr_zzsz$zzb[v3];
                if(zzsz$zzb0 != null) {
                    v2 += zzdyi.zzc(11, zzsz$zzb0);
                }
            }
            v = v2;
        }
        if(this.zzcar != null && this.zzcar.length > 0) {
            for(int v1 = 0; true; ++v1) {
                zztx[] arr_zztx = this.zzcar;
                if(v1 >= arr_zztx.length) {
                    break;
                }
                zztx zztx0 = arr_zztx[v1];
                if(zztx0 != null) {
                    v += zzecr.zzb(17, zztx0);
                }
            }
        }
        return v;
    }
}

