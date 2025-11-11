package com.google.android.gms.internal.ads;

import android.text.TextUtils;

public final class zzob {
    public static void checkArgument(boolean z) {
        if(!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, Object object0) {
        if(!z) {
            throw new IllegalArgumentException(String.valueOf(object0));
        }
    }

    public static String checkNotEmpty(String s) {
        if(TextUtils.isEmpty(s)) {
            throw new IllegalArgumentException();
        }
        return s;
    }

    public static Object checkNotNull(Object object0) {
        if(object0 == null) {
            throw new NullPointerException();
        }
        return object0;
    }

    public static void checkState(boolean z) {
        if(!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean z, Object object0) {
        if(!z) {
            throw new IllegalStateException(String.valueOf(object0));
        }
    }

    public static int zzc(int v, int v1, int v2) {
        if(v < 0 || v >= v2) {
            throw new IndexOutOfBoundsException();
        }
        return v;
    }
}

