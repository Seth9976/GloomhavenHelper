package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;

@KeepForSdk
public class ClientLibraryUtils {
    @KeepForSdk
    public static int getClientVersion(Context context0, String s) {
        PackageInfo packageInfo0 = ClientLibraryUtils.zzb(context0, s);
        if(packageInfo0 != null && packageInfo0.applicationInfo != null) {
            Bundle bundle0 = packageInfo0.applicationInfo.metaData;
            return bundle0 == null ? -1 : bundle0.getInt("com.google.android.gms.version", -1);
        }
        return -1;
    }

    @KeepForSdk
    public static boolean isPackageSide() [...] // Inlined contents

    @Nullable
    private static PackageInfo zzb(Context context0, String s) {
        try {
            return Wrappers.packageManager(context0).getPackageInfo(s, 0x80);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    public static boolean zzc(Context context0, String s) {
        "com.google.android.gms".equals(s);
        try {
            if((Wrappers.packageManager(context0).getApplicationInfo(s, 0).flags & 0x200000) != 0) {
                return true;
            }
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
        }
        return false;
    }
}

