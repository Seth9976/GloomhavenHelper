package com.google.android.gms.internal.ads;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzel implements ThreadFactory {
    private final ThreadFactory zzyh;
    private final AtomicInteger zzyi;

    zzel() {
        this.zzyh = Executors.defaultThreadFactory();
        this.zzyi = new AtomicInteger(1);
    }

    @Override
    public final Thread newThread(Runnable runnable0) {
        Thread thread0 = this.zzyh.newThread(runnable0);
        thread0.setName("gads-" + this.zzyi.getAndIncrement());
        return thread0;
    }
}

