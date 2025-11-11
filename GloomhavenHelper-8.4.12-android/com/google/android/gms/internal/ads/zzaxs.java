package com.google.android.gms.internal.ads;

public final class zzaxs extends Exception {
    private final int errorCode;

    public zzaxs(String s, int v) {
        super(s);
        this.errorCode = v;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }
}

