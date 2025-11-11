package com.google.android.gms.internal.ads;

final class zzkt {
    public final int[] zzane;
    public final long[] zzanf;
    public final int zzavm;
    public final int zzavs;
    public final int[] zzavu;
    public final long[] zzaxs;

    public zzkt(long[] arr_v, int[] arr_v1, int v, long[] arr_v2, int[] arr_v3) {
        boolean z = true;
        zzob.checkArgument(arr_v1.length == arr_v2.length);
        zzob.checkArgument(arr_v.length == arr_v2.length);
        if(arr_v3.length != arr_v2.length) {
            z = false;
        }
        zzob.checkArgument(z);
        this.zzanf = arr_v;
        this.zzane = arr_v1;
        this.zzavs = v;
        this.zzaxs = arr_v2;
        this.zzavu = arr_v3;
        this.zzavm = arr_v.length;
    }

    public final int zzec(long v) {
        for(int v1 = zzop.zza(this.zzaxs, v, true, false); v1 >= 0; --v1) {
            if((this.zzavu[v1] & 1) != 0) {
                return v1;
            }
        }
        return -1;
    }

    public final int zzed(long v) {
        for(int v1 = zzop.zzb(this.zzaxs, v, true, false); v1 < this.zzaxs.length; ++v1) {
            if((this.zzavu[v1] & 1) != 0) {
                return v1;
            }
        }
        return -1;
    }
}

