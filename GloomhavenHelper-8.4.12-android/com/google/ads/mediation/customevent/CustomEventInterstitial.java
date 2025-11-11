package com.google.ads.mediation.customevent;

import android.app.Activity;
import com.google.ads.mediation.MediationAdRequest;

@Deprecated
public interface CustomEventInterstitial extends CustomEvent {
    void requestInterstitialAd(CustomEventInterstitialListener arg1, Activity arg2, String arg3, String arg4, MediationAdRequest arg5, Object arg6);

    void showInterstitial();
}

