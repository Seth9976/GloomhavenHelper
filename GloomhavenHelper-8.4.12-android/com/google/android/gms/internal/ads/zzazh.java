package com.google.android.gms.internal.ads;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;

public class zzazh {
    public static boolean isLoggable(int v) {
        return v >= 5 || Log.isLoggable("Ads", v);
    }

    public static void zzb(String s, Throwable throwable0) {
        if(zzazh.isLoggable(3)) {
            Log.d("Ads", s, throwable0);
        }
    }

    public static void zzc(String s, Throwable throwable0) {
        if(zzazh.isLoggable(6)) {
            Log.e("Ads", s, throwable0);
        }
    }

    public static void zzd(String s, Throwable throwable0) {
        if(zzazh.isLoggable(5)) {
            Log.w("Ads", s, throwable0);
        }
    }

    public static void zze(String s, @Nullable Throwable throwable0) {
        if(zzazh.isLoggable(5)) {
            if(throwable0 != null) {
                zzazh.zzd(zzazh.zzfb(s), throwable0);
                return;
            }
            zzazh.zzfa(zzazh.zzfb(s));
        }
    }

    public static void zzeb(String s) {
        if(zzazh.isLoggable(3)) {
            Log.d("Ads", s);
        }
    }

    public static void zzey(String s) {
        if(zzazh.isLoggable(6)) {
            Log.e("Ads", s);
        }
    }

    public static void zzez(String s) {
        if(zzazh.isLoggable(4)) {
            Log.i("Ads", s);
        }
    }

    public static void zzfa(String s) {
        if(zzazh.isLoggable(5)) {
            Log.w("Ads", s);
        }
    }

    @VisibleForTesting
    private static String zzfb(String s) {
        StackTraceElement[] arr_stackTraceElement = Thread.currentThread().getStackTrace();
        return arr_stackTraceElement.length < 4 ? s : s + " @" + arr_stackTraceElement[3].getLineNumber();
    }

    public static void zzfc(String s) {
        zzazh.zze(s, null);
    }
}

