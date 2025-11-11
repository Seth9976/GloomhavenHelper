package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class MurmurHash3 {
    @KeepForSdk
    public static int murmurhash3_x86_32(byte[] arr_b, int v, int v1, int v2) {
        int v6;
        int v3 = (v1 & -4) + v;
        while(v < v3) {
            int v4 = (arr_b[v] & 0xFF | (arr_b[v + 1] & 0xFF) << 8 | (arr_b[v + 2] & 0xFF) << 16 | arr_b[v + 3] << 24) * 0xCC9E2D51;
            int v5 = v2 ^ (v4 << 15 | v4 >>> 17) * 461845907;
            v2 = (v5 >>> 19 | v5 << 13) * 5 - 430675100;
            v += 4;
        }
        switch(v1 & 3) {
            case 1: {
                v6 = (arr_b[v3] & 0xFF) * 0xCC9E2D51;
                v2 ^= (v6 >>> 17 | v6 << 15) * 461845907;
                break;
            }
            case 2: {
                v6 = (arr_b[v3] & 0xFF | (arr_b[v3 + 1] & 0xFF) << 8) * 0xCC9E2D51;
                v2 ^= (v6 >>> 17 | v6 << 15) * 461845907;
                break;
            }
            case 3: {
                v6 = (arr_b[v3] & 0xFF | ((arr_b[v3 + 2] & 0xFF) << 16 | (arr_b[v3 + 1] & 0xFF) << 8)) * 0xCC9E2D51;
                v2 ^= (v6 >>> 17 | v6 << 15) * 461845907;
            }
        }
        int v7 = (v2 ^ v1 ^ (v2 ^ v1) >>> 16) * -2048144789;
        int v8 = (v7 ^ v7 >>> 13) * -1028477387;
        return v8 ^ v8 >>> 16;
    }
}

