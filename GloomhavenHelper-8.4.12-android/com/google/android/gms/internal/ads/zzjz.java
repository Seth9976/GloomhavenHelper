package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzjz {
    private int length;
    private int state;
    private final byte[] zzank;
    private static final long[] zzarc;

    static {
        zzjz.zzarc = new long[]{0x80L, 0x40L, 0x20L, 16L, 8L, 4L, 2L, 1L};
    }

    public zzjz() {
        this.zzank = new byte[8];
    }

    public final void reset() {
        this.state = 0;
        this.length = 0;
    }

    public static long zza(byte[] arr_b, int v, boolean z) {
        long v1 = ((long)arr_b[0]) & 0xFFL;
        if(z) {
            v1 &= ~zzjz.zzarc[v - 1];
        }
        for(int v2 = 1; v2 < v; ++v2) {
            v1 = v1 << 8 | ((long)arr_b[v2]) & 0xFFL;
        }
        return v1;
    }

    public final long zza(zzjf zzjf0, boolean z, boolean z1, int v) throws IOException, InterruptedException {
        if(this.state == 0) {
            if(!zzjf0.zza(this.zzank, 0, 1, z)) {
                return -1L;
            }
            this.length = zzjz.zzak(this.zzank[0] & 0xFF);
            if(this.length == -1) {
                throw new IllegalStateException("No valid varint length mask found");
            }
            this.state = 1;
        }
        int v1 = this.length;
        if(v1 > v) {
            this.state = 0;
            return -2L;
        }
        if(v1 != 1) {
            zzjf0.readFully(this.zzank, 1, v1 - 1);
        }
        this.state = 0;
        return zzjz.zza(this.zzank, this.length, z1);
    }

    public static int zzak(int v) {
        for(int v1 = 0; true; ++v1) {
            long[] arr_v = zzjz.zzarc;
            if(v1 >= arr_v.length) {
                break;
            }
            if((arr_v[v1] & ((long)v)) != 0L) {
                return v1 + 1;
            }
        }
        return -1;
    }

    public final int zzgt() {
        return this.length;
    }
}

