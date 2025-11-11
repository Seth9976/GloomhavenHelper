package com.google.android.gms.internal.ads;

final class zzxs extends zzvg {
    private final zzxt zzcfc;

    zzxs(zzxt zzxt0) {
        this.zzcfc = zzxt0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzvg
    public final void onAdFailedToLoad(int v) {
        this.zzcfc.zzcfe.zza(this.zzcfc.zzdq());
        super.onAdFailedToLoad(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzvg
    public final void onAdLoaded() {
        zzxj zzxj0 = this.zzcfc.zzdq();
        this.zzcfc.zzcfe.zza(zzxj0);
        super.onAdLoaded();
    }
}

