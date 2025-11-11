package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

final class zzcvf {
    private final Clock zzbmz;
    public final zzdof zzgiv;
    private final long zzgiw;

    public zzcvf(zzdof zzdof0, long v, Clock clock0) {
        this.zzgiv = zzdof0;
        this.zzbmz = clock0;
        this.zzgiw = clock0.elapsedRealtime() + v;
    }

    public final boolean hasExpired() {
        long v = this.zzbmz.elapsedRealtime();
        return this.zzgiw < v;
    }
}

