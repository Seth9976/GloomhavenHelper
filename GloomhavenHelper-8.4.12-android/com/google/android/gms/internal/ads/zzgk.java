package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzgk extends Exception {
    private final int type;
    private final int zzacp;

    private zzgk(int v, String s, Throwable throwable0, int v1) {
        super(null, throwable0);
        this.type = v;
        this.zzacp = v1;
    }

    public static zzgk zza(IOException iOException0) {
        return new zzgk(0, null, iOException0, -1);
    }

    public static zzgk zza(Exception exception0, int v) {
        return new zzgk(1, null, exception0, v);
    }

    static zzgk zza(RuntimeException runtimeException0) {
        return new zzgk(2, null, runtimeException0, -1);
    }
}

