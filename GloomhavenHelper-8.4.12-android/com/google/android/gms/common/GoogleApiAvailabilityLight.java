package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;

@KeepForSdk
@ShowFirstParty
public class GoogleApiAvailabilityLight {
    @KeepForSdk
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @KeepForSdk
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0;
    @KeepForSdk
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    @KeepForSdk
    static final String TRACKING_SOURCE_DIALOG = "d";
    @KeepForSdk
    static final String TRACKING_SOURCE_NOTIFICATION = "n";
    private static final GoogleApiAvailabilityLight zzm;

    static {
        GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        GoogleApiAvailabilityLight.zzm = new GoogleApiAvailabilityLight();
    }

    @KeepForSdk
    public void cancelAvailabilityErrorNotifications(Context context0) {
        GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(context0);
    }

    @KeepForSdk
    @ShowFirstParty
    public int getApkVersion(Context context0) {
        return GooglePlayServicesUtilLight.getApkVersion(context0);
    }

    @KeepForSdk
    @ShowFirstParty
    public int getClientVersion(Context context0) {
        return GooglePlayServicesUtilLight.getClientVersion(context0);
    }

    @Nullable
    @KeepForSdk
    @ShowFirstParty
    @Deprecated
    public Intent getErrorResolutionIntent(int v) {
        return this.getErrorResolutionIntent(null, v, null);
    }

    @Nullable
    @KeepForSdk
    @ShowFirstParty
    public Intent getErrorResolutionIntent(Context context0, int v, @Nullable String s) {
        switch(v) {
            case 1: 
            case 2: {
                return context0 == null || !DeviceProperties.isWearableWithoutPlayStore(context0) ? zzg.zza("com.google.android.gms", GoogleApiAvailabilityLight.zza(context0, s)) : zzg.zzs();
            }
            case 3: {
                return zzg.zzg("com.google.android.gms");
            }
            default: {
                return null;
            }
        }
    }

    @Nullable
    @KeepForSdk
    public PendingIntent getErrorResolutionPendingIntent(Context context0, int v, int v1) {
        return this.getErrorResolutionPendingIntent(context0, v, v1, null);
    }

    @Nullable
    @KeepForSdk
    @ShowFirstParty
    public PendingIntent getErrorResolutionPendingIntent(Context context0, int v, int v1, @Nullable String s) {
        Intent intent0 = this.getErrorResolutionIntent(context0, v, s);
        return intent0 == null ? null : PendingIntent.getActivity(context0, v1, intent0, 0x8000000);
    }

    @KeepForSdk
    public String getErrorString(int v) {
        return GooglePlayServicesUtilLight.getErrorString(v);
    }

    @KeepForSdk
    public static GoogleApiAvailabilityLight getInstance() {
        return GoogleApiAvailabilityLight.zzm;
    }

    @KeepForSdk
    @HideFirstParty
    public int isGooglePlayServicesAvailable(Context context0) {
        return this.isGooglePlayServicesAvailable(context0, GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @KeepForSdk
    public int isGooglePlayServicesAvailable(Context context0, int v) {
        int v1 = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context0, v);
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context0, v1) ? 18 : v1;
    }

    @KeepForSdk
    @ShowFirstParty
    public boolean isPlayServicesPossiblyUpdating(Context context0, int v) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context0, v);
    }

    @KeepForSdk
    @ShowFirstParty
    public boolean isPlayStorePossiblyUpdating(Context context0, int v) {
        return GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context0, v);
    }

    @KeepForSdk
    public boolean isUninstalledAppPossiblyUpdating(Context context0, String s) {
        return GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(context0, s);
    }

    @KeepForSdk
    public boolean isUserResolvableError(int v) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(v);
    }

    @KeepForSdk
    public void verifyGooglePlayServicesIsAvailable(Context context0, int v) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context0, v);
    }

    @VisibleForTesting
    private static String zza(@Nullable Context context0, @Nullable String s) {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("gcore_");
        stringBuilder0.append(GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        stringBuilder0.append("-");
        if(!TextUtils.isEmpty(s)) {
            stringBuilder0.append(s);
        }
        stringBuilder0.append("-");
        if(context0 != null) {
            stringBuilder0.append("com.esotericsoftware.gloomhavenhelper");
        }
        stringBuilder0.append("-");
        if(context0 != null) {
            try {
                stringBuilder0.append(Wrappers.packageManager(context0).getPackageInfo("com.esotericsoftware.gloomhavenhelper", 0).versionCode);
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
            }
        }
        return stringBuilder0.toString();
    }
}

