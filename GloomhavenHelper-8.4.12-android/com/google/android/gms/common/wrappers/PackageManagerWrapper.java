package com.google.android.gms.common.wrappers;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Process;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class PackageManagerWrapper {
    private final Context zzhx;

    public PackageManagerWrapper(Context context0) {
        this.zzhx = context0;
    }

    @KeepForSdk
    public int checkCallingOrSelfPermission(String s) {
        return this.zzhx.checkCallingOrSelfPermission(s);
    }

    @KeepForSdk
    public int checkPermission(String s, String s1) {
        return this.zzhx.getPackageManager().checkPermission(s, s1);
    }

    @KeepForSdk
    public ApplicationInfo getApplicationInfo(String s, int v) throws PackageManager.NameNotFoundException {
        return this.zzhx.getPackageManager().getApplicationInfo(s, v);
    }

    @KeepForSdk
    public CharSequence getApplicationLabel(String s) throws PackageManager.NameNotFoundException {
        return this.zzhx.getPackageManager().getApplicationLabel(this.zzhx.getPackageManager().getApplicationInfo(s, 0));
    }

    @KeepForSdk
    public PackageInfo getPackageInfo(String s, int v) throws PackageManager.NameNotFoundException {
        return this.zzhx.getPackageManager().getPackageInfo(s, v);
    }

    public final String[] getPackagesForUid(int v) {
        return this.zzhx.getPackageManager().getPackagesForUid(v);
    }

    @KeepForSdk
    public boolean isCallerInstantApp() {
        if(Binder.getCallingUid() == Process.myUid()) {
            return InstantApps.isInstantApp(this.zzhx);
        }
        String s = this.zzhx.getPackageManager().getNameForUid(Binder.getCallingUid());
        return s == null ? false : this.zzhx.getPackageManager().isInstantApp(s);
    }

    public final PackageInfo zza(String s, int v, int v1) throws PackageManager.NameNotFoundException {
        return this.zzhx.getPackageManager().getPackageInfo(s, 0x40);
    }

    @TargetApi(19)
    public final boolean zzb(int v, String s) {
        try {
            ((AppOpsManager)this.zzhx.getSystemService("appops")).checkPackage(v, s);
            return true;
        }
        catch(SecurityException unused_ex) {
            return false;
        }
    }
}

