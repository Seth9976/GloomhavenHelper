package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzedp extends zzect {
    public String url;
    private static volatile zzedp[] zziev;
    public Integer zziew;
    public zzedn zziex;
    private zze zziey;
    private Integer zziez;
    private int[] zzifa;
    private String zzifb;
    public zza zzifc;
    public String[] zzifd;

    public zzedp() {
        this.zziew = null;
        this.url = null;
        this.zziex = null;
        this.zziey = null;
        this.zziez = null;
        this.zzifa = zzedb.zzhve;
        this.zzifb = null;
        this.zzifc = null;
        this.zzifd = zzedb.zziak;
        this.zzhzu = null;
        this.zzhnu = -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    public final void zza(zzecr zzecr0) throws IOException {
        zzecr0.zzac(1, ((int)this.zziew));
        String s = this.url;
        if(s != null) {
            zzecr0.zzf(2, s);
        }
        zzedn zzedn0 = this.zziex;
        if(zzedn0 != null) {
            zzecr0.zza(3, zzedn0);
        }
        if(this.zzifa != null && this.zzifa.length > 0) {
            for(int v1 = 0; true; ++v1) {
                int[] arr_v = this.zzifa;
                if(v1 >= arr_v.length) {
                    break;
                }
                zzecr0.zzac(6, arr_v[v1]);
            }
        }
        zza zzede$zzb$zzh$zza0 = this.zzifc;
        if(zzede$zzb$zzh$zza0 != null) {
            zzecr0.zzac(8, zzede$zzb$zzh$zza0.zzaf());
        }
        if(this.zzifd != null && this.zzifd.length > 0) {
            for(int v = 0; true; ++v) {
                String[] arr_s = this.zzifd;
                if(v >= arr_s.length) {
                    break;
                }
                String s1 = arr_s[v];
                if(s1 != null) {
                    zzecr0.zzf(9, s1);
                }
            }
        }
        super.zza(zzecr0);
    }

    public static zzedp[] zzbgh() {
        if(zzedp.zziev == null) {
            Object object0 = zzecx.zzhzz;
            synchronized(object0) {
                if(zzedp.zziev == null) {
                    zzedp.zziev = new zzedp[0];
                }
            }
        }
        return zzedp.zziev;
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    protected final int zzon() {
        int[] arr_v;
        int v = super.zzon() + zzecr.zzag(1, ((int)this.zziew));
        String s = this.url;
        if(s != null) {
            v += zzecr.zzg(2, s);
        }
        zzedn zzedn0 = this.zziex;
        if(zzedn0 != null) {
            v += zzecr.zzb(3, zzedn0);
        }
        if(this.zzifa != null && this.zzifa.length > 0) {
            int v3 = 0;
            for(int v2 = 0; true; ++v2) {
                arr_v = this.zzifa;
                if(v2 >= arr_v.length) {
                    break;
                }
                v3 += zzecr.zzga(arr_v[v2]);
            }
            v = v + v3 + arr_v.length;
        }
        zza zzede$zzb$zzh$zza0 = this.zzifc;
        if(zzede$zzb$zzh$zza0 != null) {
            v += zzecr.zzag(8, zzede$zzb$zzh$zza0.zzaf());
        }
        if(this.zzifd != null && this.zzifd.length > 0) {
            int v4 = 0;
            int v5 = 0;
            for(int v1 = 0; true; ++v1) {
                String[] arr_s = this.zzifd;
                if(v1 >= arr_s.length) {
                    break;
                }
                String s1 = arr_s[v1];
                if(s1 != null) {
                    ++v5;
                    v4 += zzecr.zzhk(s1);
                }
            }
            return v + v4 + v5;
        }
        return v;
    }
}

