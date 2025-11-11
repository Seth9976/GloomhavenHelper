package com.google.android.gms.internal.ads;

final class zzdbj implements zzdcd {
    private final int zzdxb;

    zzdbj(int v) {
        this.zzdxb = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdcd
    public final void zzr(Object object0) {
        ((zzrh)object0).onAppOpenAdFailedToLoad(this.zzdxb);
    }
}

