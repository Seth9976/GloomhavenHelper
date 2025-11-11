package com.google.android.gms.internal.ads;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class zzdmw extends AbstractExecutorService implements zzdoe {
    @Override
    protected final RunnableFuture newTaskFor(Runnable runnable0, Object object0) {
        return zzdot.zza(runnable0, object0);
    }

    @Override
    protected final RunnableFuture newTaskFor(Callable callable0) {
        return zzdot.zze(callable0);
    }

    @Override
    public Future submit(Runnable runnable0) {
        return this.zzf(runnable0);
    }

    @Override
    public Future submit(Runnable runnable0, @NullableDecl Object object0) {
        return (zzdof)super.submit(runnable0, object0);
    }

    @Override
    public Future submit(Callable callable0) {
        return this.zzd(callable0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdoe
    public final zzdof zzd(Callable callable0) {
        return (zzdof)super.submit(callable0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdoe
    public final zzdof zzf(Runnable runnable0) {
        return (zzdof)super.submit(runnable0);
    }
}

