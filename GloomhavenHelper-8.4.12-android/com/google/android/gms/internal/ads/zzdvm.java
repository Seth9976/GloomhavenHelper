package com.google.android.gms.internal.ads;

final class zzdvm {
    static byte[] zzp(byte[] arr_b) {
        if(arr_b.length != 16) {
            throw new IllegalArgumentException("value must be a block.");
        }
        byte[] arr_b1 = new byte[16];
        for(int v = 0; v < 16; ++v) {
            arr_b1[v] = (byte)(arr_b[v] << 1 & 0xFE);
            if(v < 15) {
                arr_b1[v] = (byte)(arr_b1[v] | ((byte)(arr_b[v + 1] >> 7 & 1)));
            }
        }
        arr_b1[15] = (byte)(((byte)(arr_b[0] >> 7 & 0x87)) ^ arr_b1[15]);
        return arr_b1;
    }
}

