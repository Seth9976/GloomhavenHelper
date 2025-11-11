package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class zzdno extends zzdnx {
    public final zzdno zza(long v, TimeUnit timeUnit0, ScheduledExecutorService scheduledExecutorService0) {
        return (zzdno)zzdnt.zza(this, v, timeUnit0, scheduledExecutorService0);
    }

    public final zzdno zza(zzdku zzdku0, Executor executor0) {
        zzdlg.checkNotNull(zzdku0);
        zzdmx zzdmx0 = new zzdmx(this, zzdku0);
        this.addListener(zzdmx0, zzdoh.zza(executor0, zzdmx0));
        return zzdmx0;
    }

    public final zzdno zza(Class class0, zzdku zzdku0, Executor executor0) {
        zzdmu zzdmu0 = new zzdmu(this, class0, zzdku0);
        this.addListener(zzdmu0, zzdoh.zza(executor0, zzdmu0));
        return zzdmu0;
    }

    public final zzdno zzb(zzdng zzdng0, Executor executor0) {
        zzdlg.checkNotNull(executor0);
        zzdmy zzdmy0 = new zzdmy(this, zzdng0);
        this.addListener(zzdmy0, zzdoh.zza(executor0, zzdmy0));
        return zzdmy0;
    }

    public static zzdno zzg(zzdof zzdof0) {
        return zzdof0 instanceof zzdno ? ((zzdno)zzdof0) : new zzdnq(zzdof0);
    }
}

