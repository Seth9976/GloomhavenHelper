package com.google.android.gms.common.util;

import android.os.Build.VERSION;
import androidx.core.os.BuildCompat;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public final class PlatformVersion {
    @KeepForSdk
    public static boolean isAtLeastHoneycomb() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastHoneycombMR1() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastIceCreamSandwich() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastIceCreamSandwichMR1() [...] // Inlined contents

    @KeepForSdk
    public static boolean isAtLeastJellyBean() [...] // Inlined contents

    @KeepForSdk
    public static boolean isAtLeastJellyBeanMR1() [...] // 潜在的解密器

    @KeepForSdk
    public static boolean isAtLeastJellyBeanMR2() [...] // 潜在的解密器

    @KeepForSdk
    public static boolean isAtLeastKitKat() [...] // 潜在的解密器

    @KeepForSdk
    public static boolean isAtLeastKitKatWatch() [...] // 潜在的解密器

    @KeepForSdk
    public static boolean isAtLeastLollipop() [...] // 潜在的解密器

    @KeepForSdk
    public static boolean isAtLeastLollipopMR1() {
        return Build.VERSION.SDK_INT >= 22;
    }

    @KeepForSdk
    public static boolean isAtLeastM() [...] // 潜在的解密器

    @KeepForSdk
    public static boolean isAtLeastN() [...] // 潜在的解密器

    @KeepForSdk
    public static boolean isAtLeastO() [...] // 潜在的解密器

    @KeepForSdk
    public static boolean isAtLeastP() [...] // 潜在的解密器

    // 去混淆评级： 低(30)
    @KeepForSdk
    public static boolean isAtLeastQ() {
        return BuildCompat.isAtLeastQ() || Build.VERSION.CODENAME.equals("REL") && Build.VERSION.SDK_INT >= 29 || Build.VERSION.CODENAME.length() == 1 && Build.VERSION.CODENAME.charAt(0) >= 81 && Build.VERSION.CODENAME.charAt(0) <= 90;
    }
}

