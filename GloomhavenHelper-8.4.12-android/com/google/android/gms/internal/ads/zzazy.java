package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class zzazy implements zzdof {
    private final zzdom zzdxu;

    public zzazy() {
        this.zzdxu = zzdom.zzauw();
    }

    @Override  // com.google.android.gms.internal.ads.zzdof
    public void addListener(Runnable runnable0, Executor executor0) {
        this.zzdxu.addListener(runnable0, executor0);
    }

    @Override
    public boolean cancel(boolean z) {
        return this.zzdxu.cancel(z);
    }

    @Override
    public Object get() throws ExecutionException, InterruptedException {
        return this.zzdxu.get();
    }

    @Override
    public Object get(long v, TimeUnit timeUnit0) throws ExecutionException, InterruptedException, TimeoutException {
        return this.zzdxu.get(v, timeUnit0);
    }

    @Override
    public boolean isCancelled() {
        return this.zzdxu.isCancelled();
    }

    @Override
    public boolean isDone() {
        return this.zzdxu.isDone();
    }

    public final boolean set(@Nullable Object object0) {
        return zzazy.zzas(this.zzdxu.set(object0));
    }

    public final boolean setException(Throwable throwable0) {
        return zzazy.zzas(this.zzdxu.setException(throwable0));
    }

    private static boolean zzas(boolean z) {
        if(!z) {
            zzq.zzkz().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture");
        }
        return z;
    }
}

