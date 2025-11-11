package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@KeepForSdk
@ShowFirstParty
public class GooglePlayServicesUtilLight {
    @KeepForSdk
    static final int GMS_AVAILABILITY_NOTIFICATION_ID = 10436;
    @KeepForSdk
    static final int GMS_GENERAL_ERROR_NOTIFICATION_ID = 0x9B6D;
    @KeepForSdk
    public static final String GOOGLE_PLAY_GAMES_PACKAGE = "com.google.android.play.games";
    @KeepForSdk
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @KeepForSdk
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;
    @KeepForSdk
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    @KeepForSdk
    @VisibleForTesting
    static final AtomicBoolean sCanceledAvailabilityNotification = null;
    @VisibleForTesting
    private static boolean zzah = false;
    @VisibleForTesting
    private static boolean zzai = false;
    private static boolean zzaj = false;
    @VisibleForTesting
    private static boolean zzak = false;
    private static final AtomicBoolean zzal;

    static {
        GooglePlayServicesUtilLight.sCanceledAvailabilityNotification = new AtomicBoolean();
        GooglePlayServicesUtilLight.zzal = new AtomicBoolean();
    }

    @KeepForSdk
    @Deprecated
    public static void cancelAvailabilityErrorNotifications(Context context0) {
        if(GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.getAndSet(true)) {
            return;
        }
        try {
            NotificationManager notificationManager0 = (NotificationManager)context0.getSystemService("notification");
            if(notificationManager0 != null) {
                notificationManager0.cancel(10436);
            }
        }
        catch(SecurityException unused_ex) {
        }
    }

    @KeepForSdk
    @ShowFirstParty
    public static void enableUsingApkIndependentContext() {
        GooglePlayServicesUtilLight.zzal.set(true);
    }

    @KeepForSdk
    @Deprecated
    public static void ensurePlayServicesAvailable(Context context0, int v) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int v1 = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context0, v);
        if(v1 != 0) {
            Intent intent0 = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(context0, v1, "e");
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + v1);
            if(intent0 != null) {
                throw new GooglePlayServicesRepairableException(v1, "Google Play Services not available", intent0);
            }
            throw new GooglePlayServicesNotAvailableException(v1);
        }
        return;
    }

    @KeepForSdk
    @ShowFirstParty
    @Deprecated
    public static int getApkVersion(Context context0) {
        try {
            return context0.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    @KeepForSdk
    @ShowFirstParty
    @Deprecated
    public static int getClientVersion(Context context0) {
        Preconditions.checkState(true);
        return ClientLibraryUtils.getClientVersion(context0, "com.esotericsoftware.gloomhavenhelper");
    }

    @KeepForSdk
    @Deprecated
    public static PendingIntent getErrorPendingIntent(int v, Context context0, int v1) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(context0, v, v1);
    }

    @KeepForSdk
    @VisibleForTesting
    @Deprecated
    public static String getErrorString(int v) {
        return ConnectionResult.zza(v);
    }

    @KeepForSdk
    @ShowFirstParty
    @Deprecated
    public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int v) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(null, v, null);
    }

    @KeepForSdk
    public static Context getRemoteContext(Context context0) {
        try {
            return context0.createPackageContext("com.google.android.gms", 3);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    @KeepForSdk
    public static Resources getRemoteResource(Context context0) {
        try {
            return context0.getPackageManager().getResourcesForApplication("com.google.android.gms");
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    @KeepForSdk
    @ShowFirstParty
    public static boolean honorsDebugCertificates(Context context0) {
        if(!GooglePlayServicesUtilLight.zzak) {
            try {
                PackageInfo packageInfo0 = Wrappers.packageManager(context0).getPackageInfo("com.google.android.gms", 0x40);
                GoogleSignatureVerifier.getInstance(context0);
                GooglePlayServicesUtilLight.zzaj = packageInfo0 == null || GoogleSignatureVerifier.zza(packageInfo0, false) || !GoogleSignatureVerifier.zza(packageInfo0, true) ? false : true;
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", packageManager$NameNotFoundException0);
            }
            finally {
                GooglePlayServicesUtilLight.zzak = true;
            }
        }
        return GooglePlayServicesUtilLight.zzaj || !DeviceProperties.isUserBuild();
    }

    @KeepForSdk
    @HideFirstParty
    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context0) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context0, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @KeepForSdk
    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context0, int v) {
        try {
            context0.getResources().getString(string.common_google_play_services_unknown_issue);
        }
        catch(Throwable unused_ex) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if(!GooglePlayServicesUtilLight.zzal.get()) {
            int v1 = zzp.zzd(context0);
            if(v1 == 0) {
                throw new IllegalStateException("A required meta-data tag in your app\'s AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            }
            if(v1 != GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                throw new IllegalStateException("The meta-data tag in your app\'s AndroidManifest.xml does not have the right value.  Expected " + 12451000 + " but found " + v1 + ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            }
        }
        return DeviceProperties.isWearableWithoutPlayStore(context0) || DeviceProperties.zzf(context0) ? GooglePlayServicesUtilLight.zza(context0, false, v) : GooglePlayServicesUtilLight.zza(context0, true, v);
    }

    @KeepForSdk
    @Deprecated
    public static boolean isGooglePlayServicesUid(Context context0, int v) {
        return UidVerifier.isGooglePlayServicesUid(context0, v);
    }

    @KeepForSdk
    @ShowFirstParty
    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(Context context0, int v) {
        switch(v) {
            case 1: {
                return GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(context0, "com.google.android.gms");
            }
            case 18: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    @KeepForSdk
    @ShowFirstParty
    @Deprecated
    public static boolean isPlayStorePossiblyUpdating(Context context0, int v) {
        return v == 9 ? GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(context0, "com.android.vending") : false;
    }

    // 去混淆评级： 低(20)
    @TargetApi(18)
    @KeepForSdk
    public static boolean isRestrictedUserProfile(Context context0) {
        Bundle bundle0 = ((UserManager)context0.getSystemService("user")).getApplicationRestrictions("com.esotericsoftware.gloomhavenhelper");
        return bundle0 != null && "true".equals(bundle0.getString("restricted_profile"));
    }

    @KeepForSdk
    @ShowFirstParty
    @VisibleForTesting
    @Deprecated
    public static boolean isSidewinderDevice(Context context0) {
        return DeviceProperties.isSidewinder(context0);
    }

    @TargetApi(21)
    static boolean isUninstalledAppPossiblyUpdating(Context context0, String s) {
        boolean z = s.equals("com.google.android.gms");
        try {
            List list0 = context0.getPackageManager().getPackageInstaller().getAllSessions();
        }
        catch(Exception unused_ex) {
            return false;
        }
        for(Object object0: list0) {
            if(s.equals(((PackageInstaller.SessionInfo)object0).getAppPackageName())) {
                return true;
            }
            if(false) {
                break;
            }
        }
        PackageManager packageManager0 = context0.getPackageManager();
        try {
            ApplicationInfo applicationInfo0 = packageManager0.getApplicationInfo(s, 0x2000);
            if(z) {
                return applicationInfo0.enabled;
            }
            if(applicationInfo0.enabled && !GooglePlayServicesUtilLight.isRestrictedUserProfile(context0)) {
                return true;
            }
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
        }
        return false;
    }

    @KeepForSdk
    @Deprecated
    public static boolean isUserRecoverableError(int v) {
        return v == 9 || (v == 1 || v == 2 || v == 3);
    }

    @TargetApi(19)
    @KeepForSdk
    @Deprecated
    public static boolean uidHasPackageName(Context context0, int v, String s) {
        return UidVerifier.uidHasPackageName(context0, v, s);
    }

    @VisibleForTesting
    private static int zza(Context context0, boolean z, int v) {
        PackageInfo packageInfo1;
        Preconditions.checkArgument(v >= 0);
        PackageManager packageManager0 = context0.getPackageManager();
        PackageInfo packageInfo0 = null;
        if(z) {
            try {
                packageInfo0 = packageManager0.getPackageInfo("com.android.vending", 0x2040);
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                return 9;
            }
        }
        try {
            packageInfo1 = packageManager0.getPackageInfo("com.google.android.gms", 0x40);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
        GoogleSignatureVerifier.getInstance(context0);
        if(!GoogleSignatureVerifier.zza(packageInfo1, true)) {
            Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
            return 9;
        }
        if(z && (!GoogleSignatureVerifier.zza(packageInfo0, true) || !packageInfo0.signatures[0].equals(packageInfo1.signatures[0]))) {
            Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
            return 9;
        }
        if(zzb.zzc(packageInfo1.versionCode) < zzb.zzc(v)) {
            Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires " + v + " but found " + packageInfo1.versionCode);
            return 2;
        }
        ApplicationInfo applicationInfo0 = packageInfo1.applicationInfo;
        if(applicationInfo0 == null) {
            try {
                return packageManager0.getApplicationInfo("com.google.android.gms", 0).enabled ? 0 : 3;
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", packageManager$NameNotFoundException0);
                return 1;
            }
        }
        return applicationInfo0.enabled ? 0 : 3;
    }
}

