package com.google.android.gms.ads.formats;

import android.widget.ImageView.ScaleType;
import com.google.android.gms.internal.ads.zzace;

final class zzd implements zzace {
    private final UnifiedNativeAdView zzbku;

    zzd(UnifiedNativeAdView unifiedNativeAdView0) {
        this.zzbku = unifiedNativeAdView0;
    }

    @Override  // com.google.android.gms.internal.ads.zzace
    public final void setImageScaleType(ImageView.ScaleType imageView$ScaleType0) {
        this.zzbku.zza(imageView$ScaleType0);
    }
}

