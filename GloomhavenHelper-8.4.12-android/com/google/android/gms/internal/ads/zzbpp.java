package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzbpp implements Callable {
    private final zzbpm zzfjx;
    private final zzdof zzfjy;

    zzbpp(zzbpm zzbpm0, zzdof zzdof0) {
        this.zzfjx = zzbpm0;
        this.zzfjy = zzdof0;
    }

    @Override
    public final Object call() {
        return this.zzfjx.zzc(this.zzfjy);
    }
}

