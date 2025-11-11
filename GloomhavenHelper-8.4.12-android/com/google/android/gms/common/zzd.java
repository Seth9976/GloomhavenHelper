package com.google.android.gms.common;

import java.util.concurrent.Callable;

final class zzd implements Callable {
    private final boolean zzq;
    private final String zzr;
    private final zze zzs;

    zzd(boolean z, String s, zze zze0) {
        this.zzq = z;
        this.zzr = s;
        this.zzs = zze0;
    }

    @Override
    public final Object call() {
        return zzc.zza(this.zzq, this.zzr, this.zzs);
    }
}

