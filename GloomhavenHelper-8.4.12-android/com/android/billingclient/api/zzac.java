package com.android.billingclient.api;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzac implements ThreadFactory {
    private final ThreadFactory zza;
    private final AtomicInteger zzb;

    zzac(BillingClientImpl billingClientImpl0) {
        this.zza = Executors.defaultThreadFactory();
        this.zzb = new AtomicInteger(1);
    }

    @Override
    public final Thread newThread(Runnable runnable0) {
        Thread thread0 = this.zza.newThread(runnable0);
        thread0.setName("PlayBillingLibrary-" + this.zzb.getAndIncrement());
        return thread0;
    }
}

