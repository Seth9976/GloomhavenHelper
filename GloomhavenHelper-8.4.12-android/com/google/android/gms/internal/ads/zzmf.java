package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public final class zzmf implements zzme {
    private final zzhk zzadd;
    private final zzme[] zzbbo;
    private final ArrayList zzbbp;
    private zzmd zzbbq;
    private zzhj zzbbr;
    private Object zzbbs;
    private int zzbbt;
    private zzmh zzbbu;

    public zzmf(zzme[] arr_zzme) {
        this.zzbbo = arr_zzme;
        this.zzbbp = new ArrayList(Arrays.asList(arr_zzme));
        this.zzadd = new zzhk();
        this.zzbbt = -1;
    }

    private final void zza(int v, zzhj zzhj0, Object object0) {
        zzmh zzmh0;
        if(this.zzbbu == null) {
            int v1 = zzhj0.zzfa();
            for(int v2 = 0; true; ++v2) {
                if(v2 >= v1) {
                    if(this.zzbbt == -1) {
                        this.zzbbt = zzhj0.zzfb();
                    }
                    else if(zzhj0.zzfb() != this.zzbbt) {
                        zzmh0 = new zzmh(1);
                        break;
                    }
                    zzmh0 = null;
                    break;
                }
                if(zzhj0.zza(v2, this.zzadd, false).zzagu) {
                    zzmh0 = new zzmh(0);
                    break;
                }
            }
            this.zzbbu = zzmh0;
        }
        if(this.zzbbu != null) {
            return;
        }
        this.zzbbp.remove(this.zzbbo[v]);
        if(v == 0) {
            this.zzbbr = zzhj0;
            this.zzbbs = object0;
        }
        if(this.zzbbp.isEmpty()) {
            this.zzbbq.zzb(this.zzbbr, this.zzbbs);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzme
    public final zzmc zza(int v, zznm zznm0) {
        zzmc[] arr_zzmc = new zzmc[this.zzbbo.length];
        for(int v1 = 0; v1 < arr_zzmc.length; ++v1) {
            arr_zzmc[v1] = this.zzbbo[v1].zza(v, zznm0);
        }
        return new zzmg(arr_zzmc);
    }

    @Override  // com.google.android.gms.internal.ads.zzme
    public final void zza(zzgn zzgn0, boolean z, zzmd zzmd0) {
        this.zzbbq = zzmd0;
        for(int v = 0; true; ++v) {
            zzme[] arr_zzme = this.zzbbo;
            if(v >= arr_zzme.length) {
                break;
            }
            arr_zzme[v].zza(zzgn0, false, new zzmi(this, v));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzme
    public final void zzb(zzmc zzmc0) {
        for(int v = 0; true; ++v) {
            zzme[] arr_zzme = this.zzbbo;
            if(v >= arr_zzme.length) {
                break;
            }
            arr_zzme[v].zzb(((zzmg)zzmc0).zzbbv[v]);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzme
    public final void zzhw() throws IOException {
        zzmh zzmh0 = this.zzbbu;
        if(zzmh0 != null) {
            throw zzmh0;
        }
        zzme[] arr_zzme = this.zzbbo;
        for(int v = 0; v < arr_zzme.length; ++v) {
            arr_zzme[v].zzhw();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzme
    public final void zzhx() {
        zzme[] arr_zzme = this.zzbbo;
        for(int v = 0; v < arr_zzme.length; ++v) {
            arr_zzme[v].zzhx();
        }
    }
}

