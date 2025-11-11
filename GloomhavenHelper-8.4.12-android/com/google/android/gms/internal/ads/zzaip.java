package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzaip implements Runnable {
    private final zzaim zzdaa;
    private final zzafz zzdab;
    private final Map zzdac;

    zzaip(zzaim zzaim0, zzafz zzafz0, Map map0) {
        this.zzdaa = zzaim0;
        this.zzdab = zzafz0;
        this.zzdac = map0;
    }

    @Override
    public final void run() {
        this.zzdaa.zza(this.zzdab, this.zzdac);
    }
}

