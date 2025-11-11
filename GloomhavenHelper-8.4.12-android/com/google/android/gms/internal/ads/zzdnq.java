package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zzdnq extends zzdno {
    private final zzdof zzhcw;

    zzdnq(zzdof zzdof0) {
        this.zzhcw = (zzdof)zzdlg.checkNotNull(zzdof0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    public final void addListener(Runnable runnable0, Executor executor0) {
        this.zzhcw.addListener(runnable0, executor0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    public final boolean cancel(boolean z) {
        return this.zzhcw.cancel(z);
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    public final Object get() throws InterruptedException, ExecutionException {
        return this.zzhcw.get();
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    public final Object get(long v, TimeUnit timeUnit0) throws InterruptedException, ExecutionException, TimeoutException {
        return this.zzhcw.get(v, timeUnit0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    public final boolean isCancelled() {
        return this.zzhcw.isCancelled();
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    public final boolean isDone() {
        return this.zzhcw.isDone();
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    public final String toString() {
        return this.zzhcw.toString();
    }
}

