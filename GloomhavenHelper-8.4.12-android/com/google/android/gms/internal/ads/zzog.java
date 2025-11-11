package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzog {
    private int size;
    private long[] zzbgl;

    public zzog() {
        this(0x20);
    }

    private zzog(int v) {
        this.zzbgl = new long[0x20];
    }

    public final void add(long v) {
        int v1 = this.size;
        long[] arr_v = this.zzbgl;
        if(v1 == arr_v.length) {
            this.zzbgl = Arrays.copyOf(arr_v, v1 << 1);
        }
        int v2 = this.size;
        this.size = v2 + 1;
        this.zzbgl[v2] = v;
    }

    public final long get(int v) {
        if(v < 0 || v >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index " + v + ", size is " + this.size);
        }
        return this.zzbgl[v];
    }

    public final int size() {
        return this.size;
    }
}

