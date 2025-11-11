package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzdaa implements zzcye {
    private ApplicationInfo applicationInfo;
    private boolean zzdna;
    private ScheduledExecutorService zzfib;
    private zzalb zzglx;

    public zzdaa(zzalb zzalb0, ScheduledExecutorService scheduledExecutorService0, boolean z, ApplicationInfo applicationInfo0) {
        this.zzglx = zzalb0;
        this.zzfib = scheduledExecutorService0;
        this.zzdna = z;
        this.applicationInfo = applicationInfo0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return this.zzdna ? zzdnt.zzb(zzdnt.zza(this.zzglx.zza(this.applicationInfo), ((long)(((Long)zzvh.zzpd().zzd(zzzx.zzcnj)))), TimeUnit.MILLISECONDS, this.zzfib), zzczz.zzdpv, zzazq.zzdxk) : zzdnt.immediateFailedFuture(new Exception("Auto Collect Location is false."));
    }
}

