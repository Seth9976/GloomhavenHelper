package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzckp implements Callable {
    private final zzdof zzfig;
    private final zzdof zzfjy;
    private final zzckh zzfzf;

    zzckp(zzckh zzckh0, zzdof zzdof0, zzdof zzdof1) {
        this.zzfzf = zzckh0;
        this.zzfjy = zzdof0;
        this.zzfig = zzdof1;
    }

    @Override
    public final Object call() {
        return this.zzfzf.zza(this.zzfjy, this.zzfig);
    }
}

