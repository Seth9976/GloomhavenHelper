package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class InstantApps {
    private static Context zzhv;
    private static Boolean zzhw;

    @KeepForSdk
    public static boolean isInstantApp(Context context0) {
        synchronized(InstantApps.class) {
            Context context1 = context0.getApplicationContext();
            if(InstantApps.zzhv != null && InstantApps.zzhw != null && InstantApps.zzhv == context1) {
                return InstantApps.zzhw.booleanValue();
            }
            InstantApps.zzhw = Boolean.valueOf(context1.getPackageManager().isInstantApp());
            InstantApps.zzhv = context1;
            return InstantApps.zzhw.booleanValue();
        }
    }
}

