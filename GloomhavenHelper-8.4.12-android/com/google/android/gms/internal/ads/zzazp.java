package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzazp implements ThreadFactory {
    private final String zzdxj;
    private final AtomicInteger zzyi;

    zzazp(String s) {
        this.zzdxj = s;
        super();
        this.zzyi = new AtomicInteger(1);
    }

    @Override
    public final Thread newThread(Runnable runnable0) {
        int v = this.zzyi.getAndIncrement();
        return new Thread(runnable0, "AdWorker(" + this.zzdxj + ") #" + v);
    }
}

