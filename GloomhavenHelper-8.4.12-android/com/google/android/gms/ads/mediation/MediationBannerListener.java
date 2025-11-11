package com.google.android.gms.ads.mediation;

public interface MediationBannerListener {
    void onAdClicked(MediationBannerAdapter arg1);

    void onAdClosed(MediationBannerAdapter arg1);

    void onAdFailedToLoad(MediationBannerAdapter arg1, int arg2);

    void onAdLeftApplication(MediationBannerAdapter arg1);

    void onAdLoaded(MediationBannerAdapter arg1);

    void onAdOpened(MediationBannerAdapter arg1);

    void zza(MediationBannerAdapter arg1, String arg2, String arg3);
}

