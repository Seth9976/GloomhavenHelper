package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;

@KeepForSdk
public final class UidVerifier {
    @KeepForSdk
    public static boolean isGooglePlayServicesUid(Context context0, int v) {
        if(!UidVerifier.uidHasPackageName(context0, v, "com.google.android.gms")) {
            return false;
        }
        PackageManager packageManager0 = context0.getPackageManager();
        try {
            PackageInfo packageInfo0 = packageManager0.getPackageInfo("com.google.android.gms", 0x40);
            return GoogleSignatureVerifier.getInstance(context0).isGooglePublicSignedPackage(packageInfo0);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            if(Log.isLoggable("UidVerifier", 3)) {
                Log.d("UidVerifier", "Package manager can\'t find google play services package, defaulting to false");
            }
            return false;
        }
    }

    @TargetApi(19)
    @KeepForSdk
    public static boolean uidHasPackageName(Context context0, int v, String s) {
        return Wrappers.packageManager(context0).zzb(v, s);
    }
}

