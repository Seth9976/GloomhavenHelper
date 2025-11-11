package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import java.util.Arrays;

@TargetApi(21)
public final class zzhm {
    private static final zzhm zzahc;
    private final int[] zzahd;
    private final int zzahe;

    static {
        zzhm.zzahc = new zzhm(new int[]{2}, 2);
    }

    private zzhm(int[] arr_v, int v) {
        this.zzahd = Arrays.copyOf(arr_v, arr_v.length);
        Arrays.sort(this.zzahd);
        this.zzahe = 2;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof zzhm ? Arrays.equals(this.zzahd, ((zzhm)object0).zzahd) && this.zzahe == ((zzhm)object0).zzahe : false;
    }

    @Override
    public final int hashCode() {
        int v = Arrays.hashCode(this.zzahd);
        return this.zzahe + v * 0x1F;
    }

    @Override
    public final String toString() {
        String s = Arrays.toString(this.zzahd);
        return "AudioCapabilities[maxChannelCount=" + this.zzahe + ", supportedEncodings=" + s + "]";
    }

    public final boolean zzq(int v) {
        return Arrays.binarySearch(this.zzahd, v) >= 0;
    }
}

