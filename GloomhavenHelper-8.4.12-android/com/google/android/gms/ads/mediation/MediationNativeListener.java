package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

public interface MediationNativeListener {
    void onAdClicked(MediationNativeAdapter arg1);

    void onAdClosed(MediationNativeAdapter arg1);

    void onAdFailedToLoad(MediationNativeAdapter arg1, int arg2);

    void onAdImpression(MediationNativeAdapter arg1);

    void onAdLeftApplication(MediationNativeAdapter arg1);

    @Deprecated
    void onAdLoaded(MediationNativeAdapter arg1, NativeAdMapper arg2);

    void onAdLoaded(MediationNativeAdapter arg1, UnifiedNativeAdMapper arg2);

    void onAdOpened(MediationNativeAdapter arg1);

    void onVideoEnd(MediationNativeAdapter arg1);

    void zza(MediationNativeAdapter arg1, NativeCustomTemplateAd arg2);

    void zza(MediationNativeAdapter arg1, NativeCustomTemplateAd arg2, String arg3);
}

