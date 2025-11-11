package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.PriorityQueue;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzqy {
    @VisibleForTesting
    private static long zza(long v, int v1) {
        if(v1 == 0) {
            return 1L;
        }
        if(v1 == 1) {
            return v;
        }
        return v1 % 2 == 0 ? zzqy.zza(v * v % 0x4000FFFFL, v1 / 2) % 0x4000FFFFL : v * (zzqy.zza(v * v % 0x4000FFFFL, v1 / 2) % 0x4000FFFFL) % 0x4000FFFFL;
    }

    @VisibleForTesting
    private static String zza(String[] arr_s, int v, int v1) {
        int v2 = v1 + v;
        if(arr_s.length < v2) {
            zzawf.zzey("Unable to construct shingle");
            return "";
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        while(v < v2 - 1) {
            stringBuilder0.append(arr_s[v]);
            stringBuilder0.append(' ');
            ++v;
        }
        stringBuilder0.append(arr_s[v2 - 1]);
        return stringBuilder0.toString();
    }

    @VisibleForTesting
    private static void zza(int v, long v1, String s, int v2, PriorityQueue priorityQueue0) {
        zzqx zzqx0 = new zzqx(v1, s, v2);
        if(priorityQueue0.size() == v && (((zzqx)priorityQueue0.peek()).zzbqp > zzqx0.zzbqp || ((zzqx)priorityQueue0.peek()).value > zzqx0.value)) {
            return;
        }
        if(priorityQueue0.contains(zzqx0)) {
            return;
        }
        priorityQueue0.add(zzqx0);
        if(priorityQueue0.size() > v) {
            priorityQueue0.poll();
        }
    }

    public static void zza(String[] arr_s, int v, int v1, PriorityQueue priorityQueue0) {
        if(arr_s.length < v1) {
            zzqy.zza(v, zzqy.zzb(arr_s, 0, arr_s.length), zzqy.zza(arr_s, 0, arr_s.length), arr_s.length, priorityQueue0);
            return;
        }
        long v2 = zzqy.zzb(arr_s, 0, v1);
        zzqy.zza(v, v2, zzqy.zza(arr_s, 0, v1), v1, priorityQueue0);
        long v3 = zzqy.zza(0x1001FFFL, v1 - 1);
        for(int v4 = 1; v4 < arr_s.length - v1 + 1; ++v4) {
            v2 = ((v2 + 0x4000FFFFL - (((long)zzqt.zzby(arr_s[v4 - 1])) + 0x7FFFFFFFL) % 0x4000FFFFL * v3 % 0x4000FFFFL) % 0x4000FFFFL * 0x1001FFFL % 0x4000FFFFL + (((long)zzqt.zzby(arr_s[v4 + v1 - 1])) + 0x7FFFFFFFL) % 0x4000FFFFL) % 0x4000FFFFL;
            zzqy.zza(v, v2, zzqy.zza(arr_s, v4, v1), arr_s.length, priorityQueue0);
        }
    }

    private static long zzb(String[] arr_s, int v, int v1) {
        long v2 = (((long)zzqt.zzby(arr_s[0])) + 0x7FFFFFFFL) % 0x4000FFFFL;
        for(int v3 = 1; v3 < v1; ++v3) {
            v2 = (v2 * 0x1001FFFL % 0x4000FFFFL + (((long)zzqt.zzby(arr_s[v3])) + 0x7FFFFFFFL) % 0x4000FFFFL) % 0x4000FFFFL;
        }
        return v2;
    }
}

