package com.google.android.gms.common.config;

import android.content.Context;
import android.os.Binder;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashSet;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class GservicesValue {
    interface zza {
        Long getLong(String arg1, Long arg2);

        String getString(String arg1, String arg2);

        Boolean zza(String arg1, Boolean arg2);

        Float zza(String arg1, Float arg2);

        Integer zza(String arg1, Integer arg2);
    }

    protected final String mKey;
    private static final Object sLock;
    private static zza zzbm;
    private static int zzbn;
    private static Context zzbo;
    @GuardedBy("sLock")
    private static HashSet zzbp;
    protected final Object zzbq;
    private Object zzbr;

    static {
        GservicesValue.sLock = new Object();
        GservicesValue.zzbm = null;
        GservicesValue.zzbn = 0;
    }

    protected GservicesValue(String s, Object object0) {
        this.zzbr = null;
        this.mKey = s;
        this.zzbq = object0;
    }

    @KeepForSdk
    public final Object get() {
        Object object1;
        Object object0 = this.zzbr;
        if(object0 != null) {
            return object0;
        }
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
        synchronized(GservicesValue.sLock) {
            GservicesValue.zzbp = null;
            GservicesValue.zzbo = null;
        }
        try {
            object1 = this.zzd(this.mKey);
        }
        catch(SecurityException unused_ex) {
            long v1 = Binder.clearCallingIdentity();
            try {
                return this.zzd(this.mKey);
            }
            finally {
                Binder.restoreCallingIdentity(v1);
            }
        }
        finally {
            StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        }
        return object1;
    }

    @KeepForSdk
    @Deprecated
    public final Object getBinderSafe() {
        return this.get();
    }

    @KeepForSdk
    public static boolean isInitialized() {
        synchronized(GservicesValue.sLock) {
        }
        return false;
    }

    @KeepForSdk
    @VisibleForTesting
    public void override(Object object0) {
        Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        this.zzbr = object0;
        synchronized(GservicesValue.sLock) {
        }
    }

    @KeepForSdk
    @VisibleForTesting
    public void resetOverride() {
        this.zzbr = null;
    }

    @KeepForSdk
    public static GservicesValue value(String s, Float float0) {
        return new zzd(s, float0);
    }

    @KeepForSdk
    public static GservicesValue value(String s, Integer integer0) {
        return new zzc(s, integer0);
    }

    @KeepForSdk
    public static GservicesValue value(String s, Long long0) {
        return new zzb(s, long0);
    }

    @KeepForSdk
    public static GservicesValue value(String s, String s1) {
        return new zze(s, s1);
    }

    @KeepForSdk
    public static GservicesValue value(String s, boolean z) {
        return new com.google.android.gms.common.config.zza(s, Boolean.valueOf(z));
    }

    protected abstract Object zzd(String arg1);

    private static boolean zzi() {
        synchronized(GservicesValue.sLock) {
        }
        return false;
    }
}

