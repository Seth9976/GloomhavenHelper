package com.google.android.gms.internal.ads;

public final class zzje implements zzjl {
    private final int length;
    private final long zzagy;
    private final int[] zzane;
    private final long[] zzanf;
    private final long[] zzang;
    private final long[] zzanh;

    public zzje(int[] arr_v, long[] arr_v1, long[] arr_v2, long[] arr_v3) {
        this.zzane = arr_v;
        this.zzanf = arr_v1;
        this.zzang = arr_v2;
        this.zzanh = arr_v3;
        this.length = arr_v.length;
        int v = this.length;
        if(v > 0) {
            this.zzagy = arr_v2[v - 1] + arr_v3[v - 1];
            return;
        }
        this.zzagy = 0L;
    }

    @Override  // com.google.android.gms.internal.ads.zzjl
    public final long getDurationUs() {
        return this.zzagy;
    }

    @Override  // com.google.android.gms.internal.ads.zzjl
    public final long zzdz(long v) {
        return this.zzanf[zzop.zza(this.zzanh, v, true, true)];
    }

    @Override  // com.google.android.gms.internal.ads.zzjl
    public final boolean zzgn() {
        return true;
    }
}

