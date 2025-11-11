package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzdpq {
    private final Object zzhed;
    private final byte[] zzhee;
    private final zzdug zzhef;
    private final zzduy zzheg;
    private final int zzheh;

    zzdpq(Object object0, byte[] arr_b, zzdug zzdug0, zzduy zzduy0, int v) {
        this.zzhed = object0;
        this.zzhee = Arrays.copyOf(arr_b, arr_b.length);
        this.zzhef = zzdug0;
        this.zzheg = zzduy0;
        this.zzheh = v;
    }

    public final Object zzavh() {
        return this.zzhed;
    }

    public final zzdug zzavi() {
        return this.zzhef;
    }

    public final zzduy zzavj() {
        return this.zzheg;
    }

    public final byte[] zzavk() {
        return this.zzhee == null ? null : Arrays.copyOf(this.zzhee, this.zzhee.length);
    }
}

