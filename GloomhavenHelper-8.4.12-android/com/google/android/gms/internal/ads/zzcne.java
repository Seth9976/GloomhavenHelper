package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzcne implements Callable {
    private final zzdei zzfgp;
    private final zzdeq zzfsg;
    private final zzcnf zzgbx;

    zzcne(zzcnf zzcnf0, zzdeq zzdeq0, zzdei zzdei0) {
        this.zzgbx = zzcnf0;
        this.zzfsg = zzdeq0;
        this.zzfgp = zzdei0;
    }

    @Override
    public final Object call() {
        return this.zzgbx.zzc(this.zzfsg, this.zzfgp);
    }
}

