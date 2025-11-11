package com.google.android.gms.ads.mediation;

public interface MediationInterstitialListener {
    void onAdClicked(MediationInterstitialAdapter arg1);

    void onAdClosed(MediationInterstitialAdapter arg1);

    void onAdFailedToLoad(MediationInterstitialAdapter arg1, int arg2);

    void onAdLeftApplication(MediationInterstitialAdapter arg1);

    void onAdLoaded(MediationInterstitialAdapter arg1);

    void onAdOpened(MediationInterstitialAdapter arg1);
}

