package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class zzdnp extends zzdlo implements Future {
    @Override
    public boolean cancel(boolean z) {
        return this.zzaut().cancel(z);
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return this.zzaut().get();
    }

    @Override
    public Object get(long v, TimeUnit timeUnit0) throws InterruptedException, ExecutionException, TimeoutException {
        return this.zzaut().get(v, timeUnit0);
    }

    @Override
    public boolean isCancelled() {
        return this.zzaut().isCancelled();
    }

    @Override
    public boolean isDone() {
        return this.zzaut().isDone();
    }

    @Override  // com.google.android.gms.internal.ads.zzdlo
    protected Object zzatv() {
        return this.zzaut();
    }

    protected abstract Future zzaut();
}

