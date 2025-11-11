package com.google.android.gms.internal.ads;

import android.util.Pair;

public final class zzoe {
    private static final byte[] zzbgi;
    private static final int[] zzbgj;
    private static final int[] zzbgk;

    static {
        zzoe.zzbgi = new byte[]{0, 0, 0, 1};
        zzoe.zzbgj = new int[]{96000, 88200, 0xFA00, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
        zzoe.zzbgk = new int[]{0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};
    }

    private static int zza(zzoj zzoj0) {
        int v = zzoj0.zzbd(5);
        return v == 0x1F ? zzoj0.zzbd(6) + 0x20 : v;
    }

    private static int zzb(zzoj zzoj0) {
        int v = zzoj0.zzbd(4);
        if(v == 15) {
            return zzoj0.zzbd(24);
        }
        zzob.checkArgument(v < 13);
        return zzoe.zzbgj[v];
    }

    public static byte[] zzc(byte[] arr_b, int v, int v1) {
        byte[] arr_b1 = new byte[zzoe.zzbgi.length + v1];
        System.arraycopy(zzoe.zzbgi, 0, arr_b1, 0, zzoe.zzbgi.length);
        System.arraycopy(arr_b, v, arr_b1, zzoe.zzbgi.length, v1);
        return arr_b1;
    }

    public static Pair zze(byte[] arr_b) {
        zzoj zzoj0 = new zzoj(arr_b);
        int v = zzoe.zza(zzoj0);
        int v1 = zzoe.zzb(zzoj0);
        int v2 = zzoj0.zzbd(4);
        if(v == 5 || v == 29) {
            v1 = zzoe.zzb(zzoj0);
            if(zzoe.zza(zzoj0) == 22) {
                v2 = zzoj0.zzbd(4);
            }
        }
        int v3 = zzoe.zzbgk[v2];
        zzob.checkArgument(v3 != -1);
        return Pair.create(v1, v3);
    }
}

