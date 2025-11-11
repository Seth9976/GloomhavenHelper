package com.google.android.gms.common.providers;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ScheduledExecutorService;

@KeepForSdk
public class PooledExecutorsProvider {
    public interface PooledExecutorFactory {
        @KeepForSdk
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    private static PooledExecutorFactory zzey;

    @KeepForSdk
    public static PooledExecutorFactory getInstance() {
        synchronized(PooledExecutorsProvider.class) {
            if(PooledExecutorsProvider.zzey == null) {
                PooledExecutorsProvider.zzey = new zza();
            }
            return PooledExecutorsProvider.zzey;
        }
    }
}

