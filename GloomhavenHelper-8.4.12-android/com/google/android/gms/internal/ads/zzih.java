package com.google.android.gms.internal.ads;

public final class zzih extends Exception {
    private final int errorCode;

    public zzih(int v) {
        super("AudioTrack write failed: " + v);
        this.errorCode = v;
    }
}

