package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdop extends zzdnn {
    @NullableDecl
    private zzdof zzhdn;
    @NullableDecl
    private ScheduledFuture zzhdo;

    private zzdop(zzdof zzdof0) {
        this.zzhdn = (zzdof)zzdlg.checkNotNull(zzdof0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final void afterDone() {
        this.maybePropagateCancellationTo(this.zzhdn);
        ScheduledFuture scheduledFuture0 = this.zzhdo;
        if(scheduledFuture0 != null) {
            scheduledFuture0.cancel(false);
        }
        this.zzhdn = null;
        this.zzhdo = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final String pendingToString() {
        zzdof zzdof0 = this.zzhdn;
        ScheduledFuture scheduledFuture0 = this.zzhdo;
        if(zzdof0 != null) {
            String s = "inputFuture=[" + zzdof0 + "]";
            if(scheduledFuture0 != null) {
                long v = scheduledFuture0.getDelay(TimeUnit.MILLISECONDS);
                return v <= 0L ? s : s + ", remaining delay=[" + v + " ms]";
            }
            return s;
        }
        return null;
    }

    static zzdof zza(zzdop zzdop0) {
        return zzdop0.zzhdn;
    }

    static ScheduledFuture zza(zzdop zzdop0, ScheduledFuture scheduledFuture0) {
        zzdop0.zzhdo = null;
        return null;
    }

    static zzdof zzb(zzdof zzdof0, long v, TimeUnit timeUnit0, ScheduledExecutorService scheduledExecutorService0) {
        zzdof zzdof1 = new zzdop(zzdof0);
        zzdor zzdor0 = new zzdor(((zzdop)zzdof1));
        zzdof1.zzhdo = scheduledExecutorService0.schedule(zzdor0, v, timeUnit0);
        zzdof0.addListener(zzdor0, zzdnm.zzhcu);
        return zzdof1;
    }

    static ScheduledFuture zzb(zzdop zzdop0) {
        return zzdop0.zzhdo;
    }
}

