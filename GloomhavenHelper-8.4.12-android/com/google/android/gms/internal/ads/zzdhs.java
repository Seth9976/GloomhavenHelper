package com.google.android.gms.internal.ads;

import androidx.annotation.VisibleForTesting;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zzdhs implements zzdof {
    private final Object zzgur;
    private final String zzgus;
    private final zzdof zzgut;

    @VisibleForTesting(otherwise = 3)
    public zzdhs(Object object0, String s, zzdof zzdof0) {
        this.zzgur = object0;
        this.zzgus = s;
        this.zzgut = zzdof0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdof
    public final void addListener(Runnable runnable0, Executor executor0) {
        this.zzgut.addListener(runnable0, executor0);
    }

    @Override
    public final boolean cancel(boolean z) {
        return this.zzgut.cancel(z);
    }

    @Override
    public final Object get() throws InterruptedException, ExecutionException {
        return this.zzgut.get();
    }

    @Override
    public final Object get(long v, TimeUnit timeUnit0) throws InterruptedException, ExecutionException, TimeoutException {
        return this.zzgut.get(v, timeUnit0);
    }

    @Override
    public final boolean isCancelled() {
        return this.zzgut.isCancelled();
    }

    @Override
    public final boolean isDone() {
        return this.zzgut.isDone();
    }

    @Override
    public final String toString() {
        return this.zzgus + "@" + System.identityHashCode(this);
    }

    public final Object zzasy() {
        return this.zzgur;
    }

    public final String zzasz() {
        return this.zzgus;
    }
}

