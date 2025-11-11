package com.google.android.gms.internal.ads;

public final class zzbxc implements zzbqh {
    private final zzbqp zzfmi;
    private final zzdei zzfmj;

    public zzbxc(zzbqp zzbqp0, zzdei zzdei0) {
        this.zzfmi = zzbqp0;
        this.zzfmj = zzdei0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdClosed() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdLeftApplication() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdOpened() {
        if(this.zzfmj.zzgqb == 0 || this.zzfmj.zzgqb == 1) {
            this.zzfmi.onAdImpression();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoCompleted() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoStarted() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void zzb(zzarr zzarr0, String s, String s1) {
    }
}

