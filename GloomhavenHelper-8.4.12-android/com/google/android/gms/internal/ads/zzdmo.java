package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Deque;

public final class zzdmo {
    private static final OutputStream zzhbc;

    static {
        zzdmo.zzhbc = new zzdmn();
    }

    public static byte[] toByteArray(InputStream inputStream0) throws IOException {
        zzdlg.checkNotNull(inputStream0);
        ArrayDeque arrayDeque0 = new ArrayDeque(20);
        int v = 0x2000;
        for(int v1 = 0; v1 < 0x7FFFFFF7; v1 = v2) {
            byte[] arr_b = new byte[Math.min(v, 0x7FFFFFF7 - v1)];
            arrayDeque0.add(arr_b);
            int v2 = v1;
            int v3 = 0;
            while(v3 < arr_b.length) {
                int v4 = inputStream0.read(arr_b, v3, arr_b.length - v3);
                if(v4 == -1) {
                    return zzdmo.zza(arrayDeque0, v2);
                }
                v3 += v4;
                v2 += v4;
            }
            v = zzdmp.zzx(v, 2);
        }
        if(inputStream0.read() != -1) {
            throw new OutOfMemoryError("input is too large to fit in a byte array");
        }
        return zzdmo.zza(arrayDeque0, 0x7FFFFFF7);
    }

    private static byte[] zza(Deque deque0, int v) {
        byte[] arr_b = new byte[v];
        for(int v1 = v; v1 > 0; v1 -= v2) {
            byte[] arr_b1 = (byte[])deque0.removeFirst();
            int v2 = Math.min(v1, arr_b1.length);
            System.arraycopy(arr_b1, 0, arr_b, v - v1, v2);
        }
        return arr_b;
    }
}

