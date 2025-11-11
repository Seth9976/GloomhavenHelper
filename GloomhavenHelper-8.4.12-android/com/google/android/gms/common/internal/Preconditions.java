package com.google.android.gms.common.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.zzc;

@KeepForSdk
public final class Preconditions {
    private Preconditions() {
        throw new AssertionError("Uninstantiable");
    }

    @KeepForSdk
    public static void checkArgument(boolean z) {
        if(!z) {
            throw new IllegalArgumentException();
        }
    }

    @KeepForSdk
    public static void checkArgument(boolean z, Object object0) {
        if(!z) {
            throw new IllegalArgumentException(String.valueOf(object0));
        }
    }

    @KeepForSdk
    public static void checkArgument(boolean z, String s, Object[] arr_object) {
        if(!z) {
            throw new IllegalArgumentException(String.format(s, arr_object));
        }
    }

    @KeepForSdk
    public static void checkHandlerThread(Handler handler0) {
        Preconditions.checkHandlerThread(handler0, "Must be called on the handler thread");
    }

    @KeepForSdk
    public static void checkHandlerThread(Handler handler0, String s) {
        if(Looper.myLooper() != handler0.getLooper()) {
            throw new IllegalStateException(s);
        }
    }

    @KeepForSdk
    public static void checkMainThread(String s) {
        if(!zzc.isMainThread()) {
            throw new IllegalStateException(s);
        }
    }

    @KeepForSdk
    public static String checkNotEmpty(String s) {
        if(TextUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        return s;
    }

    @KeepForSdk
    public static String checkNotEmpty(String s, Object object0) {
        if(TextUtils.isEmpty(s)) {
            throw new IllegalArgumentException(String.valueOf(object0));
        }
        return s;
    }

    @KeepForSdk
    public static void checkNotMainThread() {
        Preconditions.checkNotMainThread("Must not be called on the main application thread");
    }

    @KeepForSdk
    public static void checkNotMainThread(String s) {
        if(zzc.isMainThread()) {
            throw new IllegalStateException(s);
        }
    }

    @NonNull
    @KeepForSdk
    public static Object checkNotNull(@Nullable Object object0) {
        if(object0 == null) {
            throw new NullPointerException("null reference");
        }
        return object0;
    }

    @NonNull
    @KeepForSdk
    public static Object checkNotNull(Object object0, Object object1) {
        if(object0 == null) {
            throw new NullPointerException(String.valueOf(object1));
        }
        return object0;
    }

    @KeepForSdk
    public static int checkNotZero(int v) {
        if(v == 0) {
            throw new IllegalArgumentException("Given Integer is zero");
        }
        return v;
    }

    @KeepForSdk
    public static int checkNotZero(int v, Object object0) {
        if(v == 0) {
            throw new IllegalArgumentException(String.valueOf(object0));
        }
        return v;
    }

    @KeepForSdk
    public static long checkNotZero(long v) {
        if(v == 0L) {
            throw new IllegalArgumentException("Given Long is zero");
        }
        return v;
    }

    @KeepForSdk
    public static long checkNotZero(long v, Object object0) {
        if(v == 0L) {
            throw new IllegalArgumentException(String.valueOf(object0));
        }
        return v;
    }

    @KeepForSdk
    public static void checkState(boolean z) {
        if(!z) {
            throw new IllegalStateException();
        }
    }

    @KeepForSdk
    public static void checkState(boolean z, Object object0) {
        if(!z) {
            throw new IllegalStateException(String.valueOf(object0));
        }
    }

    @KeepForSdk
    public static void checkState(boolean z, String s, Object[] arr_object) {
        if(!z) {
            throw new IllegalStateException(String.format(s, arr_object));
        }
    }
}

