package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeoutException;

final class zzdoq extends TimeoutException {
    private zzdoq(String s) {
        super(s);
    }

    zzdoq(String s, zzdoo zzdoo0) {
        this(s);
    }

    @Override
    public final Throwable fillInStackTrace() {
        synchronized(this) {
            this.setStackTrace(new StackTraceElement[0]);
            return this;
        }
    }
}

