package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class zzdkn implements zzdki {
    private zzdkn() {
    }

    zzdkn(zzdkk zzdkk0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzdki
    @NonNull
    public final ExecutorService zzdt(int v) {
        return Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
    }

    @Override  // com.google.android.gms.internal.ads.zzdki
    @NonNull
    public final ExecutorService zzdu(int v) {
        ThreadFactory threadFactory0 = Executors.defaultThreadFactory();
        ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory0);
        threadPoolExecutor0.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor0);
    }
}

