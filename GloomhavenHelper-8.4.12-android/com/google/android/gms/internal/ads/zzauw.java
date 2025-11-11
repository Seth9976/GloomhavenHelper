package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzauw implements ThreadFactory {
    private final AtomicInteger zzyi;

    zzauw(zzaui zzaui0) {
        this.zzyi = new AtomicInteger(1);
    }

    @Override
    public final Thread newThread(@NonNull Runnable runnable0) {
        return new Thread(runnable0, "AdWorker(SCION_TASK_EXECUTOR) #" + this.zzyi.getAndIncrement());
    }
}

