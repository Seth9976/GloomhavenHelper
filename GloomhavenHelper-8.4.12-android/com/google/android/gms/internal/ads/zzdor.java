package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdor implements Runnable {
    @NullableDecl
    private zzdop zzhdp;

    zzdor(zzdop zzdop0) {
        this.zzhdp = zzdop0;
    }

    @Override
    public final void run() {
        zzdop zzdop0 = this.zzhdp;
        if(zzdop0 == null) {
            return;
        }
        zzdof zzdof0 = zzdop.zza(zzdop0);
        if(zzdof0 == null) {
            return;
        }
        this.zzhdp = null;
        if(zzdof0.isDone()) {
            zzdop0.setFuture(zzdof0);
            return;
        }
        try {
            ScheduledFuture scheduledFuture0 = zzdop.zzb(zzdop0);
            zzdop.zza(zzdop0, null);
            String s = "Timed out";
            if(scheduledFuture0 != null) {
                try {
                    long v1 = Math.abs(scheduledFuture0.getDelay(TimeUnit.MILLISECONDS));
                    if(v1 > 10L) {
                        s = "Timed out (timeout delayed by " + v1 + " ms after scheduled time)";
                    }
                }
                catch(Throwable throwable0) {
                    zzdop0.setException(new zzdoq("Timed out", null));
                    throw throwable0;
                }
            }
            zzdop0.setException(new zzdoq(s + ": " + zzdof0, null));
        }
        finally {
            zzdof0.cancel(true);
        }
    }
}

