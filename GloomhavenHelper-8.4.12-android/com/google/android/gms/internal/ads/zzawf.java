package com.google.android.gms.internal.ads;

import android.util.Log;

public final class zzawf extends zzazh {
    public static void zza(String s, Throwable throwable0) {
        if(zzawf.zzvx()) {
            Log.v("Ads", s, throwable0);
        }
    }

    public static void zzee(String s) {
        if(zzawf.zzvx()) {
            Log.v("Ads", s);
        }
    }

    // 去混淆评级： 低(20)
    public static boolean zzvx() {
        return zzawf.isLoggable(2) && ((Boolean)zzabi.zzcuw.get()).booleanValue();
    }
}

