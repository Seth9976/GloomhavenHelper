package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzcys implements zzcye {
    private final Executor executor;
    private final ScheduledExecutorService zzfib;
    private final zzavn zzglb;
    private final Context zzur;

    public zzcys(zzavn zzavn0, Context context0, ScheduledExecutorService scheduledExecutorService0, Executor executor0) {
        this.zzglb = zzavn0;
        this.zzur = context0;
        this.zzfib = scheduledExecutorService0;
        this.executor = executor0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjw)).booleanValue()) {
            zzdno zzdno0 = zzdno.zzg(this.zzglb.zzam(this.zzur)).zza(zzcyr.zzdpv, this.executor).zza(((long)(((Long)zzvh.zzpd().zzd(zzzx.zzcjx)))), TimeUnit.MILLISECONDS, this.zzfib);
            zzcyu zzcyu0 = new zzcyu(this);
            return zzdno0.zza(Throwable.class, zzcyu0, this.executor);
        }
        return zzdnt.immediateFailedFuture(new Exception("Did not ad Ad ID into query param."));
    }

    final zzcyp zze(Throwable throwable0) {
        return new zzcyp(null, zzayx.zzbm(this.zzur));
    }
}

