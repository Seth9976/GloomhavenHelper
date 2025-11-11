package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;

public final class zzh {
    @NonNull
    private final String mPackageName;
    private final int zzdt;
    @NonNull
    private final String zzej;
    private final boolean zzek;

    public zzh(@NonNull String s, @NonNull String s1, boolean z, int v) {
        this.mPackageName = s;
        this.zzej = s1;
        this.zzek = z;
        this.zzdt = 0x81;
    }

    @NonNull
    final String getPackageName() {
        return this.mPackageName;
    }

    final int zzq() {
        return this.zzdt;
    }

    @NonNull
    final String zzt() {
        return this.zzej;
    }
}

