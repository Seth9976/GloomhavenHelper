package com.google.android.gms.ads;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.ads.zzxu;
import com.google.android.gms.internal.ads.zzyd;

public class MobileAds {
    public static final class Settings {
        private final zzyd zzabt;

        public Settings() {
            this.zzabt = new zzyd();
        }

        @Deprecated
        public final String getTrackingId() {
            return null;
        }

        @Deprecated
        public final boolean isGoogleAnalyticsEnabled() {
            return false;
        }

        @Deprecated
        public final Settings setGoogleAnalyticsEnabled(boolean z) {
            return this;
        }

        @Deprecated
        public final Settings setTrackingId(String s) {
            return this;
        }

        final zzyd zzdp() {
            return this.zzabt;
        }
    }

    public static InitializationStatus getInitializationStatus() {
        return zzxu.zzpy().getInitializationStatus();
    }

    @NonNull
    public static RequestConfiguration getRequestConfiguration() {
        return zzxu.zzpy().getRequestConfiguration();
    }

    public static RewardedVideoAd getRewardedVideoAdInstance(Context context0) {
        return zzxu.zzpy().getRewardedVideoAdInstance(context0);
    }

    public static String getVersionString() {
        return zzxu.zzpy().getVersionString();
    }

    public static void initialize(Context context0) {
        MobileAds.initialize(context0, null, null);
    }

    public static void initialize(Context context0, OnInitializationCompleteListener onInitializationCompleteListener0) {
        zzxu.zzpy().zza(context0, null, null, onInitializationCompleteListener0);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void initialize(Context context0, String s) {
        MobileAds.initialize(context0, s, null);
    }

    @RequiresPermission("android.permission.INTERNET")
    @Deprecated
    public static void initialize(Context context0, String s, Settings mobileAds$Settings0) {
        zzxu.zzpy().zza(context0, s, (mobileAds$Settings0 == null ? null : mobileAds$Settings0.zzdp()), null);
    }

    public static void openDebugMenu(Context context0, String s) {
        zzxu.zzpy().openDebugMenu(context0, s);
    }

    @KeepForSdk
    public static void registerRtbAdapter(Class class0) {
        zzxu.zzpy().registerRtbAdapter(class0);
    }

    public static void setAppMuted(boolean z) {
        zzxu.zzpy().setAppMuted(z);
    }

    public static void setAppVolume(float f) {
        zzxu.zzpy().setAppVolume(f);
    }

    public static void setRequestConfiguration(@NonNull RequestConfiguration requestConfiguration0) {
        zzxu.zzpy().setRequestConfiguration(requestConfiguration0);
    }
}

