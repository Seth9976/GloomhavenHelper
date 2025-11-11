package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

public final class zzdhl implements zzeej {
    private final zzeew zzgun;

    public zzdhl(zzeew zzeew0) {
        this.zzgun = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (ScheduledExecutorService)zzeep.zza(new ScheduledThreadPoolExecutor(1, ((ThreadFactory)this.zzgun.get())), "Cannot return null from a non-@Nullable @Provides method");
    }
}

