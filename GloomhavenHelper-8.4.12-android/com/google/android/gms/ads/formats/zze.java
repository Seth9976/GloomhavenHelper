package com.google.android.gms.ads.formats;

import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.internal.ads.zzacc;

final class zze implements zzacc {
    private final UnifiedNativeAdView zzbku;

    zze(UnifiedNativeAdView unifiedNativeAdView0) {
        this.zzbku = unifiedNativeAdView0;
    }

    @Override  // com.google.android.gms.internal.ads.zzacc
    public final void setMediaContent(MediaContent mediaContent0) {
        this.zzbku.zza(mediaContent0);
    }
}

