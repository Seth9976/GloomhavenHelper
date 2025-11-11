package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class zzdoi extends zzdmw {
    private final ExecutorService zzhdh;

    zzdoi(ExecutorService executorService0) {
        this.zzhdh = (ExecutorService)zzdlg.checkNotNull(executorService0);
    }

    @Override
    public final boolean awaitTermination(long v, TimeUnit timeUnit0) throws InterruptedException {
        return this.zzhdh.awaitTermination(v, timeUnit0);
    }

    @Override
    public final void execute(Runnable runnable0) {
        this.zzhdh.execute(runnable0);
    }

    @Override
    public final boolean isShutdown() {
        return this.zzhdh.isShutdown();
    }

    @Override
    public final boolean isTerminated() {
        return this.zzhdh.isTerminated();
    }

    @Override
    public final void shutdown() {
        this.zzhdh.shutdown();
    }

    @Override
    public final List shutdownNow() {
        return this.zzhdh.shutdownNow();
    }
}

