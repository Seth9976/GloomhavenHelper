package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Asserts {
    private Asserts() {
        throw new AssertionError("Uninstantiable");
    }

    @KeepForSdk
    public static void checkMainThread(String s) {
        if(Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return;
        }
        Log.e("Asserts", "checkMainThread: current thread Thread[jeb-dexdec-sb-st-223,5,main] IS NOT the main thread " + Looper.getMainLooper().getThread() + "!");
        throw new IllegalStateException(s);
    }

    @KeepForSdk
    public static void checkNotMainThread(String s) {
        if(Looper.getMainLooper().getThread() != Thread.currentThread()) {
            return;
        }
        Log.e("Asserts", "checkNotMainThread: current thread Thread[jeb-dexdec-sb-st-221,5,main] IS the main thread " + Looper.getMainLooper().getThread() + "!");
        throw new IllegalStateException(s);
    }

    @KeepForSdk
    public static void checkNotNull(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("null reference");
        }
    }

    @KeepForSdk
    public static void checkNotNull(Object object0, Object object1) {
        if(object0 == null) {
            throw new IllegalArgumentException(String.valueOf(object1));
        }
    }

    @KeepForSdk
    public static void checkNull(Object object0) {
        if(object0 != null) {
            throw new IllegalArgumentException("non-null reference");
        }
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
}

