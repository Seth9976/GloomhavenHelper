package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import java.util.concurrent.Callable;

public final class zzayp {
    @Deprecated
    public static Object zza(Context context0, Callable callable0) {
        try {
            return zzayp.zza(callable0);
        }
        catch(Throwable throwable0) {
            zzazh.zzc("Unexpected exception.", throwable0);
            zzaqa.zzu(context0).zza(throwable0, "StrictModeUtil.runWithLaxStrictMode");
            return null;
        }
    }

    public static Object zza(zzdlk zzdlk0) {
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(strictMode$ThreadPolicy0).permitDiskReads().permitDiskWrites().build());
            return zzdlk0.get();
        }
        finally {
            StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        }
    }

    private static Object zza(Callable callable0) throws Exception {
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(strictMode$ThreadPolicy0).permitDiskReads().permitDiskWrites().build());
            return callable0.call();
        }
        finally {
            StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        }
    }
}

