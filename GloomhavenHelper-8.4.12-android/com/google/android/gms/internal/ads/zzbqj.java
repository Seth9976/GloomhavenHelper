package com.google.android.gms.internal.ads;

final class zzbqj implements zzbtm {
    private final int zzdxb;

    zzbqj(int v) {
        this.zzdxb = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzbtm
    public final void zzp(Object object0) {
        ((zzbqm)object0).onAdFailedToLoad(this.zzdxb);
    }
}

