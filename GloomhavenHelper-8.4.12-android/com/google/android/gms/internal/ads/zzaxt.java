package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

public final class zzaxt {
    private final String[] zzduy;
    private final double[] zzduz;
    private final double[] zzdva;
    private final int[] zzdvb;
    private int zzdvc;

    private zzaxt(zzaxy zzaxy0) {
        int v = zzaxy0.zzdvk.size();
        this.zzduy = (String[])zzaxy0.zzdvj.toArray(new String[v]);
        this.zzduz = zzaxt.zzf(zzaxy0.zzdvk);
        this.zzdva = zzaxt.zzf(zzaxy0.zzdvl);
        this.zzdvb = new int[v];
        this.zzdvc = 0;
    }

    zzaxt(zzaxy zzaxy0, zzaxw zzaxw0) {
        this(zzaxy0);
    }

    public final void zza(double f) {
        ++this.zzdvc;
        for(int v = 0; true; ++v) {
            double[] arr_f = this.zzdva;
            if(v >= arr_f.length) {
                break;
            }
            if(arr_f[v] <= f && f < this.zzduz[v]) {
                ++this.zzdvb[v];
            }
            if(f < this.zzdva[v]) {
                break;
            }
        }
    }

    private static double[] zzf(List list0) {
        double[] arr_f = new double[list0.size()];
        for(int v = 0; v < arr_f.length; ++v) {
            arr_f[v] = (double)(((Double)list0.get(v)));
        }
        return arr_f;
    }

    public final List zzxe() {
        List list0 = new ArrayList(this.zzduy.length);
        for(int v = 0; true; ++v) {
            String[] arr_s = this.zzduy;
            if(v >= arr_s.length) {
                break;
            }
            list0.add(new zzaxv(arr_s[v], this.zzdva[v], this.zzduz[v], ((double)this.zzdvb[v]) / ((double)this.zzdvc), this.zzdvb[v]));
        }
        return list0;
    }
}

