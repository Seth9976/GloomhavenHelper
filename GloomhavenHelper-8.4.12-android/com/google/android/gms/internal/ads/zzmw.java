package com.google.android.gms.internal.ads;

import java.util.Arrays;

public class zzmw implements zznd {
    private final int length;
    private int zzagg;
    private final zzgz[] zzbch;
    private final zzmr zzbdn;
    private final int[] zzbdo;
    private final long[] zzbdp;

    public zzmw(zzmr zzmr0, int[] arr_v) {
        zzob.checkState(arr_v.length > 0);
        int v2;
        this.zzbdn = (zzmr)zzob.checkNotNull(zzmr0);
        this.length = arr_v.length;
        this.zzbch = new zzgz[this.length];
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            this.zzbch[v1] = zzmr0.zzav(arr_v[v1]);
        }
        zzmy zzmy0 = new zzmy(null);
        Arrays.sort(this.zzbch, zzmy0);
        this.zzbdo = new int[this.length];
        for(int v = 0; true; ++v) {
            v2 = this.length;
            if(v >= v2) {
                break;
            }
            this.zzbdo[v] = zzmr0.zzh(this.zzbch[v]);
        }
        this.zzbdp = new long[v2];
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.zzbdn == ((zzmw)object0).zzbdn && Arrays.equals(this.zzbdo, ((zzmw)object0).zzbdo);
    }

    @Override
    public int hashCode() {
        if(this.zzagg == 0) {
            int v = Arrays.hashCode(this.zzbdo);
            this.zzagg = System.identityHashCode(this.zzbdn) * 0x1F + v;
        }
        return this.zzagg;
    }

    @Override  // com.google.android.gms.internal.ads.zznd
    public final int length() {
        return this.zzbdo.length;
    }

    @Override  // com.google.android.gms.internal.ads.zznd
    public final zzgz zzav(int v) {
        return this.zzbch[v];
    }

    @Override  // com.google.android.gms.internal.ads.zznd
    public final int zzax(int v) {
        return this.zzbdo[0];
    }

    @Override  // com.google.android.gms.internal.ads.zznd
    public final zzmr zzii() {
        return this.zzbdn;
    }
}

