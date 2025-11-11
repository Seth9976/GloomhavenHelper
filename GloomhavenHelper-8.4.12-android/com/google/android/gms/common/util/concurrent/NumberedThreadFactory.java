package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
public class NumberedThreadFactory implements ThreadFactory {
    private final int priority;
    private final ThreadFactory zzhr;
    private final String zzhs;
    private final AtomicInteger zzht;

    @KeepForSdk
    public NumberedThreadFactory(String s) {
        this(s, 0);
    }

    private NumberedThreadFactory(String s, int v) {
        this.zzht = new AtomicInteger();
        this.zzhr = Executors.defaultThreadFactory();
        this.zzhs = (String)Preconditions.checkNotNull(s, "Name must not be null");
        this.priority = 0;
    }

    @Override
    public Thread newThread(Runnable runnable0) {
        zza zza0 = new zza(runnable0, 0);
        Thread thread0 = this.zzhr.newThread(zza0);
        int v = this.zzht.getAndIncrement();
        thread0.setName(this.zzhs + "[" + v + "]");
        return thread0;
    }
}

