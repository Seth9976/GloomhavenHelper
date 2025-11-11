package com.google.android.gms.internal.common;

import android.annotation.TargetApi;
import android.content.Context;
import androidx.annotation.RequiresApi;

public final class zzg {
    private static volatile boolean zziy;

    static {
        zzg.zziy = false;
    }

    // 去混淆评级： 低(20)
    @TargetApi(24)
    @RequiresApi(24)
    public static Context getDeviceProtectedStorageContext(Context context0) {
        return context0.isDeviceProtectedStorage() ? context0 : context0.createDeviceProtectedStorageContext();
    }

    public static boolean zzam() [...] // 潜在的解密器
}

