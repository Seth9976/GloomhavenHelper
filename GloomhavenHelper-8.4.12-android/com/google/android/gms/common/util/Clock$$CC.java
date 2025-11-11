package com.google.android.gms.common.util;

import android.os.SystemClock;
import com.google.android.gms.common.annotation.KeepForSdk;

public class Clock..CC {
    @KeepForSdk
    public static long currentThreadTimeMillis(Clock clock0) {
        return SystemClock.currentThreadTimeMillis();
    }
}

