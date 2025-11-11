package com.google.android.gms.internal.ads;

final class zzcmm implements zzbqu {
    private final zzbdv zzeiw;

    zzcmm(zzbdv zzbdv0) {
        this.zzeiw = zzbdv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        zzbdv zzbdv0 = this.zzeiw;
        if(zzbdv0.zzaaf() != null) {
            zzbdv0.zzaaf().zzabe();
        }
    }
}

