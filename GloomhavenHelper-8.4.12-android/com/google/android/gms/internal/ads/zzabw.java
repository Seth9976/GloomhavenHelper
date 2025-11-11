package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class zzabw {
    private static final AtomicReference zzcwc;
    static final AtomicBoolean zzcwd;

    static {
        zzabw.zzcwc = new AtomicReference();
        zzabw.zzcwd = new AtomicBoolean();
    }

    public static void zza(zzabx zzabx0) {
        zzabw.zzcwc.set(zzabx0);
    }

    static zzabx zzra() {
        return (zzabx)zzabw.zzcwc.get();
    }
}

