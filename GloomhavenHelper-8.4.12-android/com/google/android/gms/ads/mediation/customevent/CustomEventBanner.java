package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;

public interface CustomEventBanner extends CustomEvent {
    void requestBannerAd(Context arg1, CustomEventBannerListener arg2, String arg3, AdSize arg4, MediationAdRequest arg5, Bundle arg6);
}

