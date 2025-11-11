package com.google.ads.mediation.customevent;

import android.app.Activity;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;

@Deprecated
public interface CustomEventBanner extends CustomEvent {
    void requestBannerAd(CustomEventBannerListener arg1, Activity arg2, String arg3, String arg4, AdSize arg5, MediationAdRequest arg6, Object arg7);
}

