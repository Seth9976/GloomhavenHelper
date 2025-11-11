package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.os.Bundle;

public interface MediationInterstitialAdapter extends MediationAdapter {
    void requestInterstitialAd(Context arg1, MediationInterstitialListener arg2, Bundle arg3, MediationAdRequest arg4, Bundle arg5);

    void showInterstitial();
}

