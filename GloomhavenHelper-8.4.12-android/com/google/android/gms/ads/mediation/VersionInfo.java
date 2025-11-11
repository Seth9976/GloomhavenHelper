package com.google.android.gms.ads.mediation;

public final class VersionInfo {
    private final int zzekj;
    private final int zzekk;
    private final int zzekl;

    public VersionInfo(int v, int v1, int v2) {
        this.zzekj = v;
        this.zzekk = v1;
        this.zzekl = v2;
    }

    public final int getMajorVersion() {
        return this.zzekj;
    }

    public final int getMicroVersion() {
        return this.zzekl;
    }

    public final int getMinorVersion() {
        return this.zzekk;
    }
}

