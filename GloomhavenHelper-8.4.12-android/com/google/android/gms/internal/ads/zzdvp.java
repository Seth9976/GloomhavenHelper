package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class zzdvp {
    public static final void zza(ByteBuffer byteBuffer0, ByteBuffer byteBuffer1, ByteBuffer byteBuffer2, int v) {
        if(v < 0 || byteBuffer1.remaining() < v || byteBuffer2.remaining() < v || byteBuffer0.remaining() < v) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        for(int v1 = 0; v1 < v; ++v1) {
            byteBuffer0.put(((byte)(byteBuffer1.get() ^ byteBuffer2.get())));
        }
    }

    public static final byte[] zza(byte[] arr_b, int v, byte[] arr_b1, int v1, int v2) {
        if(v2 < 0 || arr_b.length - v2 < v || arr_b1.length - v2 < v1) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        byte[] arr_b2 = new byte[v2];
        for(int v3 = 0; v3 < v2; ++v3) {
            arr_b2[v3] = (byte)(arr_b[v3 + v] ^ arr_b1[v3 + v1]);
        }
        return arr_b2;
    }

    public static byte[] zza(byte[][] arr2_b) throws GeneralSecurityException {
        int v1 = 0;
        for(int v = 0; v < arr2_b.length; ++v) {
            byte[] arr_b = arr2_b[v];
            if(v1 > 0x7FFFFFFF - arr_b.length) {
                throw new GeneralSecurityException("exceeded size limit");
            }
            v1 += arr_b.length;
        }
        byte[] arr_b1 = new byte[v1];
        int v3 = 0;
        for(int v2 = 0; v2 < arr2_b.length; ++v2) {
            byte[] arr_b2 = arr2_b[v2];
            System.arraycopy(arr_b2, 0, arr_b1, v3, arr_b2.length);
            v3 += arr_b2.length;
        }
        return arr_b1;
    }

    public static final byte[] zzd(byte[] arr_b, byte[] arr_b1) {
        if(arr_b.length != arr_b1.length) {
            throw new IllegalArgumentException("The lengths of x and y should match.");
        }
        return zzdvp.zza(arr_b, 0, arr_b1, 0, arr_b.length);
    }
}

