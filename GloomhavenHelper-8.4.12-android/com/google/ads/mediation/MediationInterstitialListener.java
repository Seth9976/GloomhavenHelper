package com.google.ads.mediation;

import com.google.ads.AdRequest.ErrorCode;

@Deprecated
public interface MediationInterstitialListener {
    void onDismissScreen(MediationInterstitialAdapter arg1);

    void onFailedToReceiveAd(MediationInterstitialAdapter arg1, ErrorCode arg2);

    void onLeaveApplication(MediationInterstitialAdapter arg1);

    void onPresentScreen(MediationInterstitialAdapter arg1);

    void onReceivedAd(MediationInterstitialAdapter arg1);
}

