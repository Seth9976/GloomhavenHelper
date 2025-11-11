package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;

public final class zzafd extends zzaej {
    private final OnUnifiedNativeAdLoadedListener zzcxt;

    public zzafd(OnUnifiedNativeAdLoadedListener unifiedNativeAd$OnUnifiedNativeAdLoadedListener0) {
        this.zzcxt = unifiedNativeAd$OnUnifiedNativeAdLoadedListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaeg
    public final void zza(zzaer zzaer0) {
        zzaes zzaes0 = new zzaes(zzaer0);
        this.zzcxt.onUnifiedNativeAdLoaded(zzaes0);
    }
}

