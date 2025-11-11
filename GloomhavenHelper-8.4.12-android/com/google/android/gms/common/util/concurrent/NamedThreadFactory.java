package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@KeepForSdk
public class NamedThreadFactory implements ThreadFactory {
    private final String name;
    private final int priority;
    private final ThreadFactory zzhr;

    @KeepForSdk
    public NamedThreadFactory(String s) {
        this(s, 0);
    }

    private NamedThreadFactory(String s, int v) {
        this.zzhr = Executors.defaultThreadFactory();
        this.name = (String)Preconditions.checkNotNull(s, "Name must not be null");
        this.priority = 0;
    }

    @Override
    public Thread newThread(Runnable runnable0) {
        zza zza0 = new zza(runnable0, 0);
        Thread thread0 = this.zzhr.newThread(zza0);
        thread0.setName(this.name);
        return thread0;
    }
}

