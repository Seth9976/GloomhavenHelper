package com.google.android.gms.internal.ads;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzdok extends zzdnr implements zzdof, ScheduledFuture {
    private final ScheduledFuture zzhdk;

    public zzdok(zzdof zzdof0, ScheduledFuture scheduledFuture0) {
        super(zzdof0);
        this.zzhdk = scheduledFuture0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdnp
    public final boolean cancel(boolean z) {
        boolean z1 = super.cancel(z);
        if(z1) {
            this.zzhdk.cancel(z);
        }
        return z1;
    }

    @Override
    public final int compareTo(Object object0) {
        return this.zzhdk.compareTo(((Delayed)object0));
    }

    @Override
    public final long getDelay(TimeUnit timeUnit0) {
        return this.zzhdk.getDelay(timeUnit0);
    }
}

