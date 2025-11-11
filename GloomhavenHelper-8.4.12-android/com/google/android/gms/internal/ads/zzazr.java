package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

final class zzazr extends zzdmw {
    private final Executor zzdxq;

    private zzazr(Executor executor0) {
        this.zzdxq = executor0;
    }

    zzazr(Executor executor0, zzazp zzazp0) {
        this(executor0);
    }

    @Override
    public final boolean awaitTermination(long v, TimeUnit timeUnit0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void execute(Runnable runnable0) {
        this.zzdxq.execute(runnable0);
    }

    @Override
    public final boolean isShutdown() {
        return false;
    }

    @Override
    public final boolean isTerminated() {
        return false;
    }

    @Override
    public final void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final List shutdownNow() {
        throw new UnsupportedOperationException();
    }
}

