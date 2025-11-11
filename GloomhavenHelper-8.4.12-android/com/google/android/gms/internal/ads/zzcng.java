package com.google.android.gms.internal.ads;

final class zzcng implements zzdnu {
    private final zzcnf zzgca;

    zzcng(zzcnf zzcnf0) {
        this.zzgca = zzcnf0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        ((zzblg)object0).zzags();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        this.zzgca.zzflh.onAdFailedToLoad(zzcid.zzd(throwable0));
        zzdfc.zzc(throwable0, "DelayedBannerAd.onFailure");
    }
}

