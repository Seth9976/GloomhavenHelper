package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class CrashUtils {
    private static final String[] zzgg;
    private static DropBoxManager zzgh;
    private static boolean zzgi;
    private static int zzgj;
    @GuardedBy("CrashUtils.class")
    private static int zzgk;
    @GuardedBy("CrashUtils.class")
    private static int zzgl;

    static {
        CrashUtils.zzgg = new String[]{"android.", "com.android.", "dalvik.", "java.", "javax."};
        CrashUtils.zzgh = null;
        CrashUtils.zzgi = false;
        CrashUtils.zzgj = -1;
        CrashUtils.zzgk = 0;
        CrashUtils.zzgl = 0;
    }

    @KeepForSdk
    public static boolean addDynamiteErrorToDropBox(Context context0, Throwable throwable0) {
        return CrashUtils.zza(context0, throwable0, 0x20000000);
    }

    private static boolean zza(Context context0, Throwable throwable0, int v) {
        try {
            Preconditions.checkNotNull(context0);
            Preconditions.checkNotNull(throwable0);
        }
        catch(Exception exception0) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", exception0);
        }
        return false;
    }
}

