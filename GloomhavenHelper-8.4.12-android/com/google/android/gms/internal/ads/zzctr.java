package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzctr implements zzcye {
    private final Executor executor;
    private final zzdof zzghv;

    public zzctr(zzdof zzdof0, Executor executor0) {
        this.zzghv = zzdof0;
        this.executor = executor0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return zzdnt.zzb(this.zzghv, zzctu.zzblf, this.executor);
    }
}

