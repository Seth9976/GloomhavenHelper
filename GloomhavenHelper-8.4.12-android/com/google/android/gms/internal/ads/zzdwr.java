package com.google.android.gms.internal.ads;

import java.util.Arrays;

final class zzdwr {
    private static void zza(byte[] arr_b, long v, int v1) {
        int v2 = 0;
        while(v2 < 4) {
            arr_b[v1 + v2] = (byte)(((int)(0xFFL & v)));
            ++v2;
            v >>= 8;
        }
    }

    private static long zze(byte[] arr_b, int v) {
        return ((long)((arr_b[v + 3] & 0xFF) << 24 | (arr_b[v] & 0xFF | (arr_b[v + 1] & 0xFF) << 8 | (arr_b[v + 2] & 0xFF) << 16))) & 0xFFFFFFFFL;
    }

    static byte[] zze(byte[] arr_b, byte[] arr_b1) {
        if(arr_b.length != 0x20) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        long v = zzdwr.zzg(arr_b, 0, 0);
        long v1 = zzdwr.zzg(arr_b, 3, 2);
        long v2 = zzdwr.zzg(arr_b, 6, 4);
        long v3 = zzdwr.zzg(arr_b, 9, 6);
        long v4 = zzdwr.zzg(arr_b, 12, 8);
        long v5 = (v2 & 0x3FFC0FFL) * 5L;
        long v6 = (v3 & 0x3F03FFFL) * 5L;
        long v7 = (v4 & 0xFFFFFL) * 5L;
        byte[] arr_b2 = new byte[17];
        long v8 = 0L;
        long v9 = 0L;
        long v10 = 0L;
        long v11 = 0L;
        long v12 = 0L;
        int v13 = 0;
        while(v13 < arr_b1.length) {
            int v14 = Math.min(16, arr_b1.length - v13);
            System.arraycopy(arr_b1, v13, arr_b2, 0, v14);
            arr_b2[v14] = 1;
            if(v14 != 16) {
                Arrays.fill(arr_b2, v14 + 1, 17, 0);
            }
            long v15 = v12 + zzdwr.zzg(arr_b2, 0, 0);
            long v16 = v8 + zzdwr.zzg(arr_b2, 3, 2);
            long v17 = v9 + zzdwr.zzg(arr_b2, 6, 4);
            long v18 = v10 + zzdwr.zzg(arr_b2, 9, 6);
            long v19 = v11 + (zzdwr.zzg(arr_b2, 12, 8) | ((long)(arr_b2[16] << 24)));
            long v20 = v15 * (v & 0x3FFFFFFL) + v16 * v7 + v17 * v6 + v18 * v5 + v19 * ((v1 & 0x3FFFF03L) * 5L);
            long v21 = v15 * (v1 & 0x3FFFF03L) + v16 * (v & 0x3FFFFFFL) + v17 * v7 + v18 * v6 + v19 * v5 + (v20 >> 26);
            long v22 = v15 * (v2 & 0x3FFC0FFL) + v16 * (v1 & 0x3FFFF03L) + v17 * (v & 0x3FFFFFFL) + v18 * v7 + v19 * v6 + (v21 >> 26);
            long v23 = v15 * (v3 & 0x3F03FFFL) + v16 * (v2 & 0x3FFC0FFL) + v17 * (v1 & 0x3FFFF03L) + v18 * (v & 0x3FFFFFFL) + v19 * v7 + (v22 >> 26);
            long v24 = v15 * (v4 & 0xFFFFFL) + v16 * (v3 & 0x3F03FFFL) + v17 * (v2 & 0x3FFC0FFL) + v18 * (v1 & 0x3FFFF03L) + v19 * (v & 0x3FFFFFFL) + (v23 >> 26);
            long v25 = (v20 & 0x3FFFFFFL) + (v24 >> 26) * 5L;
            v8 = (v21 & 0x3FFFFFFL) + (v25 >> 26);
            v13 += 16;
            v9 = v22 & 0x3FFFFFFL;
            v10 = v23 & 0x3FFFFFFL;
            v11 = v24 & 0x3FFFFFFL;
            v12 = v25 & 0x3FFFFFFL;
        }
        long v26 = v9 + (v8 >> 26);
        long v27 = v10 + (v26 >> 26);
        long v28 = v11 + (v27 >> 26);
        long v29 = v12 + (v28 >> 26) * 5L;
        long v30 = (v8 & 0x3FFFFFFL) + (v29 >> 26);
        long v31 = (v26 & 0x3FFFFFFL) + (v30 >> 26);
        long v32 = (v27 & 0x3FFFFFFL) + (v31 >> 26);
        long v33 = (v28 & 0x3FFFFFFL) + (v32 >> 26) - 0x4000000L;
        long v34 = v31 & 0x3FFFFFFL & ~(v33 >> 0x3F) | v26 & 0x3FFFFFFL & v33 >> 0x3F;
        long v35 = v32 & 0x3FFFFFFL & ~(v33 >> 0x3F) | v27 & 0x3FFFFFFL & v33 >> 0x3F;
        long v36 = ((v30 << 26 | (v29 & 0x3FFFFFFL & v33 >> 0x3F | (v29 & 0x3FFFFFFL) + 5L & 0x3FFFFFFL & ~(v33 >> 0x3F))) & 0xFFFFFFFFL) + zzdwr.zze(arr_b, 16);
        long v37 = ((v30 >> 6 | v34 << 20) & 0xFFFFFFFFL) + zzdwr.zze(arr_b, 20) + (v36 >> 0x20);
        long v38 = ((v34 >> 12 | v35 << 14) & 0xFFFFFFFFL) + zzdwr.zze(arr_b, 24) + (v37 >> 0x20);
        long v39 = zzdwr.zze(arr_b, 28);
        byte[] arr_b3 = new byte[16];
        zzdwr.zza(arr_b3, v36 & 0xFFFFFFFFL, 0);
        zzdwr.zza(arr_b3, v37 & 0xFFFFFFFFL, 4);
        zzdwr.zza(arr_b3, v38 & 0xFFFFFFFFL, 8);
        zzdwr.zza(arr_b3, ((v35 >> 18 | (v33 & ~(v33 >> 0x3F) | v28 & 0x3FFFFFFL & v33 >> 0x3F) << 8) & 0xFFFFFFFFL) + v39 + (v38 >> 0x20) & 0xFFFFFFFFL, 12);
        return arr_b3;
    }

    private static long zzg(byte[] arr_b, int v, int v1) {
        return zzdwr.zze(arr_b, v) >> v1 & 0x3FFFFFFL;
    }
}

