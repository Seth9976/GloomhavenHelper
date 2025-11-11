package com.google.android.gms.common.util;

import android.os.SystemClock;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class DefaultClock implements Clock {
    private static final DefaultClock zzgm;

    static {
        DefaultClock.zzgm = new DefaultClock();
    }

    @Override  // com.google.android.gms.common.util.Clock
    public long currentThreadTimeMillis() {
        return SystemClock.currentThreadTimeMillis();
    }

    @Override  // com.google.android.gms.common.util.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override  // com.google.android.gms.common.util.Clock
    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    @KeepForSdk
    public static Clock getInstance() {
        return DefaultClock.zzgm;
    }

    @Override  // com.google.android.gms.common.util.Clock
    public long nanoTime() {
        return System.nanoTime();
    }
}

