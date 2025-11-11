package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzcwp implements zzcye {
    private final ScheduledExecutorService zzffm;
    private final zzcye zzgiy;
    private final long zzgjn;

    public zzcwp(zzcye zzcye0, long v, ScheduledExecutorService scheduledExecutorService0) {
        this.zzgiy = zzcye0;
        this.zzgjn = v;
        this.zzffm = scheduledExecutorService0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzdof zzdof0 = this.zzgiy.zzapb();
        long v = this.zzgjn;
        if(v > 0L) {
            zzdof0 = zzdnt.zza(zzdof0, v, TimeUnit.MILLISECONDS, this.zzffm);
        }
        return zzdnt.zzb(zzdof0, Throwable.class, zzcws.zzblf, zzazq.zzdxp);
    }
}

