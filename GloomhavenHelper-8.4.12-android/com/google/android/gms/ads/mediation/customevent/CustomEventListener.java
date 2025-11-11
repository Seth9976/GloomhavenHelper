package com.google.android.gms.ads.mediation.customevent;

public interface CustomEventListener {
    void onAdClicked();

    void onAdClosed();

    void onAdFailedToLoad(int arg1);

    void onAdLeftApplication();

    void onAdOpened();
}

