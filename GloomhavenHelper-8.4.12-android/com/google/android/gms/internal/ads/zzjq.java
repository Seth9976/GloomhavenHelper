package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzjq {
    public final int zzanr;
    public final byte[] zzans;

    public zzjq(int v, byte[] arr_b) {
        this.zzanr = 1;
        this.zzans = arr_b;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.zzanr == ((zzjq)object0).zzanr && Arrays.equals(this.zzans, ((zzjq)object0).zzans);
    }

    @Override
    public final int hashCode() {
        int v = Arrays.hashCode(this.zzans);
        return this.zzanr * 0x1F + v;
    }
}

