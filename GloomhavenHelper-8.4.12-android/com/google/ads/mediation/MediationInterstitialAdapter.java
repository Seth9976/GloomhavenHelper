package com.google.ads.mediation;

import android.app.Activity;

@Deprecated
public interface MediationInterstitialAdapter extends MediationAdapter {
    void requestInterstitialAd(MediationInterstitialListener arg1, Activity arg2, MediationServerParameters arg3, MediationAdRequest arg4, NetworkExtras arg5);

    void showInterstitial();
}

