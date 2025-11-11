package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

public final class zzbop implements zzbqh, zzbqu, zzbrn, zzbsn, zztz {
    private final Clock zzbmz;
    private final zzavq zzfjd;

    public zzbop(Clock clock0, zzavq zzavq0) {
        this.zzbmz = clock0;
        this.zzfjd = zzavq0;
    }

    @Override  // com.google.android.gms.internal.ads.zztz
    public final void onAdClicked() {
        this.zzfjd.zzvb();
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdClosed() {
        this.zzfjd.zzvc();
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        this.zzfjd.zzva();
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdLeftApplication() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        this.zzfjd.zzan(true);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdOpened() {
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

    @Override  // com.google.android.gms.internal.ads.zzbsn
    public final void zzb(zzdeq zzdeq0) {
        long v = this.zzbmz.elapsedRealtime();
        this.zzfjd.zzey(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbsn
    public final void zzd(zzaqx zzaqx0) {
    }

    public final void zzf(zzuh zzuh0) {
        this.zzfjd.zze(zzuh0);
    }

    public final String zzvd() {
        return this.zzfjd.zzvd();
    }
}

