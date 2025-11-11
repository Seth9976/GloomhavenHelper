package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class zzazq {
    public static final zzdoe zzdxk;
    public static final zzdoe zzdxl;
    public static final zzdoe zzdxm;
    public static final ScheduledExecutorService zzdxn;
    public static final zzdoe zzdxo;
    public static final zzdoe zzdxp;

    static {
        zzazq.zzdxk = zzazq.zza(new ThreadPoolExecutor(2, 0x7FFFFFFF, 10L, TimeUnit.SECONDS, new SynchronousQueue(), zzazq.zzfd("Default")));
        ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(5, 5, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue(), zzazq.zzfd("Loader"));
        threadPoolExecutor0.allowCoreThreadTimeOut(true);
        zzazq.zzdxl = zzazq.zza(threadPoolExecutor0);
        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(1, 1, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue(), zzazq.zzfd("Activeview"));
        threadPoolExecutor1.allowCoreThreadTimeOut(true);
        zzazq.zzdxm = zzazq.zza(threadPoolExecutor1);
        zzazq.zzdxn = new ScheduledThreadPoolExecutor(3, zzazq.zzfd("Schedule"));
        zzazq.zzdxo = zzazq.zza(new zzazs());
        zzazq.zzdxp = zzazq.zza(zzdoh.zzauv());
    }

    private static zzdoe zza(Executor executor0) {
        return new zzazr(executor0, null);
    }

    private static ThreadFactory zzfd(String s) {
        return new zzazp(s);
    }
}

