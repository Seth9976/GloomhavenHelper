package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzdgz implements ThreadFactory {
    private final AtomicInteger zzyi;

    zzdgz() {
        this.zzyi = new AtomicInteger(1);
    }

    @Override
    public final Thread newThread(Runnable runnable0) {
        return new Thread(runnable0, "AdWorker(NG) #" + this.zzyi.getAndIncrement());
    }
}

