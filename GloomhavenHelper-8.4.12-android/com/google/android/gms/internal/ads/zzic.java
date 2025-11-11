package com.google.android.gms.internal.ads;

public final class zzic extends Exception {
    private final int zzakn;

    public zzic(int v, int v1, int v2, int v3) {
        super("AudioTrack init failed: " + v + ", Config(" + v1 + ", " + v2 + ", " + v3 + ")");
        this.zzakn = v;
    }
}

