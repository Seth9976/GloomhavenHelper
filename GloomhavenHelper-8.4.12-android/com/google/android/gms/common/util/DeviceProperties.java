package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class DeviceProperties {
    private static Boolean zzgn;
    private static Boolean zzgo;
    private static Boolean zzgp;
    private static Boolean zzgq;
    private static Boolean zzgr;
    private static Boolean zzgs;
    private static Boolean zzgt;
    private static Boolean zzgu;

    @KeepForSdk
    public static boolean isAuto(Context context0) {
        if(DeviceProperties.zzgt == null) {
            DeviceProperties.zzgt = Boolean.valueOf(context0.getPackageManager().hasSystemFeature("android.hardware.type.automotive"));
        }
        return DeviceProperties.zzgt.booleanValue();
    }

    @KeepForSdk
    public static boolean isLatchsky(Context context0) {
        if(DeviceProperties.zzgr == null) {
            PackageManager packageManager0 = context0.getPackageManager();
            DeviceProperties.zzgr = Boolean.valueOf(packageManager0.hasSystemFeature("com.google.android.feature.services_updater") && packageManager0.hasSystemFeature("cn.google.services"));
        }
        return DeviceProperties.zzgr.booleanValue();
    }

    @TargetApi(21)
    @KeepForSdk
    public static boolean isSidewinder(Context context0) {
        if(DeviceProperties.zzgq == null) {
            DeviceProperties.zzgq = Boolean.valueOf(context0.getPackageManager().hasSystemFeature("cn.google"));
        }
        return DeviceProperties.zzgq.booleanValue();
    }

    @KeepForSdk
    public static boolean isTablet(Resources resources0) {
        boolean z = false;
        if(resources0 == null) {
            return false;
        }
        if(DeviceProperties.zzgn == null) {
            if((resources0.getConfiguration().screenLayout & 15) <= 3) {
                if(DeviceProperties.zzgo == null) {
                    Configuration configuration0 = resources0.getConfiguration();
                    DeviceProperties.zzgo = Boolean.valueOf((configuration0.screenLayout & 15) <= 3 && configuration0.smallestScreenWidthDp >= 600);
                }
                if(DeviceProperties.zzgo.booleanValue()) {
                    z = true;
                }
            }
            else {
                z = true;
            }
            DeviceProperties.zzgn = Boolean.valueOf(z);
        }
        return DeviceProperties.zzgn.booleanValue();
    }

    @KeepForSdk
    public static boolean isTv(Context context0) {
        if(DeviceProperties.zzgu == null) {
            PackageManager packageManager0 = context0.getPackageManager();
            DeviceProperties.zzgu = Boolean.valueOf(packageManager0.hasSystemFeature("com.google.android.tv") || packageManager0.hasSystemFeature("android.hardware.type.television") || packageManager0.hasSystemFeature("android.software.leanback"));
        }
        return DeviceProperties.zzgu.booleanValue();
    }

    @KeepForSdk
    public static boolean isUserBuild() {
        return "user".equals(Build.TYPE);
    }

    @TargetApi(20)
    @KeepForSdk
    public static boolean isWearable(Context context0) {
        if(DeviceProperties.zzgp == null) {
            DeviceProperties.zzgp = Boolean.valueOf(context0.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return DeviceProperties.zzgp.booleanValue();
    }

    // 去混淆评级： 中等(80)
    @TargetApi(26)
    @KeepForSdk
    public static boolean isWearableWithoutPlayStore(Context context0) {
        return DeviceProperties.isWearable(context0) && (DeviceProperties.isSidewinder(context0) && false);
    }

    public static boolean zzf(Context context0) {
        if(DeviceProperties.zzgs == null) {
            DeviceProperties.zzgs = Boolean.valueOf(context0.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context0.getPackageManager().hasSystemFeature("android.hardware.type.embedded"));
        }
        return DeviceProperties.zzgs.booleanValue();
    }
}

