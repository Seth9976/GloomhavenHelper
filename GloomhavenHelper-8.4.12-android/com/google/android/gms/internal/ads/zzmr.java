package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzmr {
    public final int length;
    private int zzagg;
    private final zzgz[] zzbch;

    public zzmr(zzgz[] arr_zzgz) {
        zzob.checkState(arr_zzgz.length > 0);
        this.zzbch = arr_zzgz;
        this.length = arr_zzgz.length;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.length == ((zzmr)object0).length && Arrays.equals(this.zzbch, ((zzmr)object0).zzbch);
    }

    @Override
    public final int hashCode() {
        if(this.zzagg == 0) {
            this.zzagg = Arrays.hashCode(this.zzbch) + 0x20F;
        }
        return this.zzagg;
    }

    public final zzgz zzav(int v) {
        return this.zzbch[v];
    }

    public final int zzh(zzgz zzgz0) {
        for(int v = 0; true; ++v) {
            zzgz[] arr_zzgz = this.zzbch;
            if(v >= arr_zzgz.length) {
                break;
            }
            if(zzgz0 == arr_zzgz[v]) {
                return v;
            }
        }
        return -1;
    }
}

