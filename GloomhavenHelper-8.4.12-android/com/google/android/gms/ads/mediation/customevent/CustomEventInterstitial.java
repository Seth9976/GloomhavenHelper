package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;

public interface CustomEventInterstitial extends CustomEvent {
    void requestInterstitialAd(Context arg1, CustomEventInterstitialListener arg2, String arg3, MediationAdRequest arg4, Bundle arg5);

    void showInterstitial();
}

