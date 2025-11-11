package com.google.android.gms.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@KeepForSdk
public class WorkSourceUtil {
    private static final int zzhj;
    private static final Method zzhk;
    private static final Method zzhl;
    private static final Method zzhm;
    private static final Method zzhn;
    private static final Method zzho;
    private static final Method zzhp;
    private static final Method zzhq;

    static {
        WorkSourceUtil.zzhj = Process.myUid();
        WorkSourceUtil.zzhk = WorkSourceUtil.zzx();
        WorkSourceUtil.zzhl = WorkSourceUtil.zzy();
        WorkSourceUtil.zzhm = WorkSourceUtil.zzz();
        WorkSourceUtil.zzhn = WorkSourceUtil.zzaa();
        WorkSourceUtil.zzho = WorkSourceUtil.zzab();
        WorkSourceUtil.zzhp = WorkSourceUtil.zzac();
        WorkSourceUtil.zzhq = WorkSourceUtil.zzad();
    }

    @Nullable
    @KeepForSdk
    public static WorkSource fromPackage(Context context0, @Nullable String s) {
        ApplicationInfo applicationInfo0;
        if(context0 != null && context0.getPackageManager() != null && s != null) {
            try {
                applicationInfo0 = Wrappers.packageManager(context0).getApplicationInfo(s, 0);
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                String s1 = String.valueOf(s);
                Log.e("WorkSourceUtil", (s1.length() == 0 ? new String("Could not find package: ") : "Could not find package: " + s1));
                return null;
            }
            if(applicationInfo0 == null) {
                String s2 = String.valueOf(s);
                Log.e("WorkSourceUtil", (s2.length() == 0 ? new String("Could not get applicationInfo from package: ") : "Could not get applicationInfo from package: " + s2));
                return null;
            }
            return WorkSourceUtil.zza(applicationInfo0.uid, s);
        }
        return null;
    }

    @KeepForSdk
    public static WorkSource fromPackageAndModuleExperimentalPi(Context context0, String s, String s1) {
        if(context0 != null && context0.getPackageManager() != null && s1 != null && s != null) {
            int v = WorkSourceUtil.zzd(context0, s);
            if(v < 0) {
                return null;
            }
            WorkSource workSource0 = new WorkSource();
            Method method0 = WorkSourceUtil.zzhp;
            if(method0 != null && WorkSourceUtil.zzhq != null) {
                try {
                    Object object0 = method0.invoke(workSource0);
                    if(v != WorkSourceUtil.zzhj) {
                        WorkSourceUtil.zzhq.invoke(object0, v, s);
                    }
                    WorkSourceUtil.zzhq.invoke(object0, WorkSourceUtil.zzhj, s1);
                }
                catch(Exception exception0) {
                    Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", exception0);
                }
                return workSource0;
            }
            WorkSourceUtil.zza(workSource0, v, s);
            return workSource0;
        }
        Log.w("WorkSourceUtil", "Unexpected null arguments");
        return null;
    }

    @KeepForSdk
    public static List getNames(@Nullable WorkSource workSource0) {
        int v1 = workSource0 == null ? 0 : WorkSourceUtil.zza(workSource0);
        if(v1 == 0) {
            return Collections.emptyList();
        }
        List list0 = new ArrayList();
        for(int v = 0; v < v1; ++v) {
            String s = WorkSourceUtil.zza(workSource0, v);
            if(!Strings.isEmptyOrWhitespace(s)) {
                list0.add(s);
            }
        }
        return list0;
    }

    @KeepForSdk
    public static boolean hasWorkSourcePermission(Context context0) {
        if(context0 == null) {
            return false;
        }
        return context0.getPackageManager() == null ? false : Wrappers.packageManager(context0).checkPermission("android.permission.UPDATE_DEVICE_STATS", "com.esotericsoftware.gloomhavenhelper") == 0;
    }

    private static int zza(WorkSource workSource0) {
        Method method0 = WorkSourceUtil.zzhm;
        if(method0 != null) {
            try {
                return (int)(((Integer)method0.invoke(workSource0)));
            }
            catch(Exception exception0) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception0);
            }
        }
        return 0;
    }

    private static WorkSource zza(int v, String s) {
        WorkSource workSource0 = new WorkSource();
        WorkSourceUtil.zza(workSource0, v, s);
        return workSource0;
    }

    @Nullable
    private static String zza(WorkSource workSource0, int v) {
        Method method0 = WorkSourceUtil.zzho;
        if(method0 != null) {
            try {
                return (String)method0.invoke(workSource0, v);
            }
            catch(Exception exception0) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception0);
            }
        }
        return null;
    }

    private static void zza(WorkSource workSource0, int v, @Nullable String s) {
        if(WorkSourceUtil.zzhl != null) {
            if(s == null) {
                s = "";
            }
            try {
                WorkSourceUtil.zzhl.invoke(workSource0, v, s);
            }
            catch(Exception exception0) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception0);
            }
            return;
        }
        Method method0 = WorkSourceUtil.zzhk;
        if(method0 != null) {
            try {
                method0.invoke(workSource0, v);
            }
            catch(Exception exception1) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception1);
            }
        }
    }

    private static Method zzaa() {
        try {
            return WorkSource.class.getMethod("get", Integer.TYPE);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    private static Method zzab() {
        try {
            return WorkSource.class.getMethod("getName", Integer.TYPE);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    private static final Method zzac() {
        try {
            return WorkSource.class.getMethod("createWorkChain");
        }
        catch(Exception exception0) {
            Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", exception0);
            return null;
        }
    }

    @SuppressLint({"PrivateApi"})
    private static final Method zzad() {
        try {
            return Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", Integer.TYPE, String.class);
        }
        catch(Exception exception0) {
            Log.w("WorkSourceUtil", "Missing WorkChain class", exception0);
            return null;
        }
    }

    private static int zzd(Context context0, String s) {
        ApplicationInfo applicationInfo0;
        try {
            applicationInfo0 = Wrappers.packageManager(context0).getApplicationInfo(s, 0);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            String s1 = String.valueOf(s);
            Log.e("WorkSourceUtil", (s1.length() == 0 ? new String("Could not find package: ") : "Could not find package: " + s1));
            return -1;
        }
        if(applicationInfo0 == null) {
            String s2 = String.valueOf(s);
            Log.e("WorkSourceUtil", (s2.length() == 0 ? new String("Could not get applicationInfo from package: ") : "Could not get applicationInfo from package: " + s2));
            return -1;
        }
        return applicationInfo0.uid;
    }

    private static Method zzx() {
        try {
            return WorkSource.class.getMethod("add", Integer.TYPE);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    private static Method zzy() {
        try {
            return WorkSource.class.getMethod("add", Integer.TYPE, String.class);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    private static Method zzz() {
        try {
            return WorkSource.class.getMethod("size");
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

