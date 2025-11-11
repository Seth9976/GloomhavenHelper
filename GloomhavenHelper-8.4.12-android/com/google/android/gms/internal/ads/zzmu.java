package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzmu {
    public final int length;
    private int zzagg;
    public static final zzmu zzbdl;
    private final zzmr[] zzbdm;

    static {
        zzmu.zzbdl = new zzmu(new zzmr[0]);
    }

    public zzmu(zzmr[] arr_zzmr) {
        this.zzbdm = arr_zzmr;
        this.length = arr_zzmr.length;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.length == ((zzmu)object0).length && Arrays.equals(this.zzbdm, ((zzmu)object0).zzbdm);
    }

    @Override
    public final int hashCode() {
        if(this.zzagg == 0) {
            this.zzagg = Arrays.hashCode(this.zzbdm);
        }
        return this.zzagg;
    }

    public final int zza(zzmr zzmr0) {
        for(int v = 0; v < this.length; ++v) {
            if(this.zzbdm[v] == zzmr0) {
                return v;
            }
        }
        return -1;
    }

    public final zzmr zzaw(int v) {
        return this.zzbdm[v];
    }
}

