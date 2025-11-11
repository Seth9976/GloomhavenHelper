package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public final class zzdoh {
    public static zzdoe zza(ExecutorService executorService0) {
        if(executorService0 instanceof zzdoe) {
            return (zzdoe)executorService0;
        }
        return executorService0 instanceof ScheduledExecutorService ? new zzdol(((ScheduledExecutorService)executorService0)) : new zzdoi(executorService0);
    }

    static Executor zza(Executor executor0, zzdmt zzdmt0) {
        zzdlg.checkNotNull(executor0);
        zzdlg.checkNotNull(zzdmt0);
        return executor0 == zzdnm.zzhcu ? executor0 : new zzdog(executor0, zzdmt0);
    }

    public static Executor zzauv() {
        return zzdnm.zzhcu;
    }
}

