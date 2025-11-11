package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.wrappers.Wrappers;

public final class zzcig {
    @Nullable
    public static PackageInfo zza(Context context0, ApplicationInfo applicationInfo0) {
        try {
            return Wrappers.packageManager(context0).getPackageInfo(applicationInfo0.packageName, 0);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }
}

