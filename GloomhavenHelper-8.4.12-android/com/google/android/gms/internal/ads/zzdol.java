package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzdol extends zzdoi implements zzdoe, ScheduledExecutorService {
    private final ScheduledExecutorService zzhdl;

    zzdol(ScheduledExecutorService scheduledExecutorService0) {
        super(scheduledExecutorService0);
        this.zzhdl = (ScheduledExecutorService)zzdlg.checkNotNull(scheduledExecutorService0);
    }

    @Override
    public final ScheduledFuture schedule(Runnable runnable0, long v, TimeUnit timeUnit0) {
        zzdot zzdot0 = zzdot.zza(runnable0, null);
        return new zzdok(zzdot0, this.zzhdl.schedule(zzdot0, v, timeUnit0));
    }

    @Override
    public final ScheduledFuture schedule(Callable callable0, long v, TimeUnit timeUnit0) {
        zzdot zzdot0 = zzdot.zze(callable0);
        return new zzdok(zzdot0, this.zzhdl.schedule(zzdot0, v, timeUnit0));
    }

    @Override
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable0, long v, long v1, TimeUnit timeUnit0) {
        zzdon zzdon0 = new zzdon(runnable0);
        return new zzdok(zzdon0, this.zzhdl.scheduleAtFixedRate(zzdon0, v, v1, timeUnit0));
    }

    @Override
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable0, long v, long v1, TimeUnit timeUnit0) {
        zzdon zzdon0 = new zzdon(runnable0);
        return new zzdok(zzdon0, this.zzhdl.scheduleWithFixedDelay(zzdon0, v, v1, timeUnit0));
    }
}

