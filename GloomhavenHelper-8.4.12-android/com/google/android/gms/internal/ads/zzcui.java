package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzcui implements zzcye {
    private final Executor executor;
    private final zzavr zzbmv;

    zzcui(Executor executor0, zzavr zzavr0) {
        this.executor = executor0;
        this.zzbmv = zzavr0;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzclz)).booleanValue() ? zzdnt.zzaj(null) : zzdnt.zzb(this.zzbmv.zzvl(), zzcuh.zzdpv, this.executor);
    }
}

