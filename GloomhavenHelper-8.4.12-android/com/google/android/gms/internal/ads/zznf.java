package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zznf {
    public final int length;
    private int zzagg;
    private final zznd[] zzbel;

    public zznf(zznd[] arr_zznd) {
        this.zzbel = arr_zznd;
        this.length = arr_zznd.length;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 == null || this.getClass() != object0.getClass() ? false : Arrays.equals(this.zzbel, ((zznf)object0).zzbel);
    }

    @Override
    public final int hashCode() {
        if(this.zzagg == 0) {
            this.zzagg = Arrays.hashCode(this.zzbel) + 0x20F;
        }
        return this.zzagg;
    }

    public final zznd zzay(int v) {
        return this.zzbel[v];
    }

    public final zznd[] zzij() {
        return (zznd[])this.zzbel.clone();
    }
}

