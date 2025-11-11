package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzcuq implements zzcye {
    private final Executor executor;
    private final ScheduledExecutorService zzfib;
    private final Context zzur;

    public zzcuq(Context context0, ScheduledExecutorService scheduledExecutorService0, Executor executor0) {
        this.zzur = context0;
        this.zzfib = scheduledExecutorService0;
        this.executor = executor0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcrj)).booleanValue()) {
            return zzdnt.zzaj(null);
        }
        zzazy zzazy0 = new zzazy();
        try {
            new zzcup(zzazy0).zzbm(false);
            return zzdnt.zzb(zzdnt.zzb(zzdnt.zza(zzazy0, 200L, TimeUnit.MILLISECONDS, this.zzfib), new zzcus(this), this.executor), Throwable.class, zzcur.zzblf, this.executor);
        }
        catch(Throwable unused_ex) {
            zzawf.zzey("ArCoreApk is not ready.");
            zzazy0.set(Boolean.FALSE);
            return zzdnt.zzb(zzdnt.zzb(zzdnt.zza(zzazy0, 200L, TimeUnit.MILLISECONDS, this.zzfib), new zzcus(this), this.executor), Throwable.class, zzcur.zzblf, this.executor);
        }
    }
}

