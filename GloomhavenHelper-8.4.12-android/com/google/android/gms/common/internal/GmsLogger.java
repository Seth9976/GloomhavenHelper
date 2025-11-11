package com.google.android.gms.common.internal;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class GmsLogger {
    private static final int zzef = 15;
    private static final String zzeg;
    private final String zzeh;
    private final String zzei;

    static {
    }

    public GmsLogger(String s) {
        this(s, null);
    }

    public GmsLogger(String s, String s1) {
        Preconditions.checkNotNull(s, "log tag cannot be null");
        Preconditions.checkArgument(s.length() <= 23, "tag \"%s\" is longer than the %d character maximum", new Object[]{s, 23});
        this.zzeh = s;
        if(s1 != null && s1.length() > 0) {
            this.zzei = s1;
            return;
        }
        this.zzei = null;
    }

    @KeepForSdk
    public final boolean canLog(int v) {
        return Log.isLoggable(this.zzeh, v);
    }

    @KeepForSdk
    public final boolean canLogPii() [...] // Inlined contents

    @KeepForSdk
    public final void d(String s, String s1) {
        if(this.canLog(3)) {
            Log.d(s, this.zzh(s1));
        }
    }

    @KeepForSdk
    public final void d(String s, String s1, Throwable throwable0) {
        if(this.canLog(3)) {
            Log.d(s, this.zzh(s1), throwable0);
        }
    }

    @KeepForSdk
    public final void e(String s, String s1) {
        if(this.canLog(6)) {
            Log.e(s, this.zzh(s1));
        }
    }

    @KeepForSdk
    public final void e(String s, String s1, Throwable throwable0) {
        if(this.canLog(6)) {
            Log.e(s, this.zzh(s1), throwable0);
        }
    }

    @KeepForSdk
    public final void efmt(String s, String s1, Object[] arr_object) {
        if(this.canLog(6)) {
            Log.e(s, this.zza(s1, arr_object));
        }
    }

    @KeepForSdk
    public final void i(String s, String s1) {
        if(this.canLog(4)) {
            Log.i(s, this.zzh(s1));
        }
    }

    @KeepForSdk
    public final void i(String s, String s1, Throwable throwable0) {
        if(this.canLog(4)) {
            Log.i(s, this.zzh(s1), throwable0);
        }
    }

    @KeepForSdk
    public final void pii(String s, String s1) {
    }

    @KeepForSdk
    public final void pii(String s, String s1, Throwable throwable0) {
    }

    @KeepForSdk
    public final void v(String s, String s1) {
        if(this.canLog(2)) {
            Log.v(s, this.zzh(s1));
        }
    }

    @KeepForSdk
    public final void v(String s, String s1, Throwable throwable0) {
        if(this.canLog(2)) {
            Log.v(s, this.zzh(s1), throwable0);
        }
    }

    @KeepForSdk
    public final void w(String s, String s1) {
        if(this.canLog(5)) {
            Log.w(s, this.zzh(s1));
        }
    }

    @KeepForSdk
    public final void w(String s, String s1, Throwable throwable0) {
        if(this.canLog(5)) {
            Log.w(s, this.zzh(s1), throwable0);
        }
    }

    @KeepForSdk
    public final void wfmt(String s, String s1, Object[] arr_object) {
        if(this.canLog(5)) {
            String s2 = this.zza(s1, arr_object);
            Log.w(this.zzeh, s2);
        }
    }

    @KeepForSdk
    public final void wtf(String s, String s1, Throwable throwable0) {
        if(this.canLog(7)) {
            Log.e(s, this.zzh(s1), throwable0);
            Log.wtf(s, this.zzh(s1), throwable0);
        }
    }

    private final String zza(String s, Object[] arr_object) {
        String s1 = String.format(s, arr_object);
        return this.zzei == null ? s1 : this.zzei + s1;
    }

    private final String zzh(String s) {
        return this.zzei == null ? s : this.zzei + s;
    }
}

