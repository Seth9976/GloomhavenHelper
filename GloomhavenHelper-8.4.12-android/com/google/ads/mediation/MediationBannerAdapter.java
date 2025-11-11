package com.google.ads.mediation;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdSize;

@Deprecated
public interface MediationBannerAdapter extends MediationAdapter {
    View getBannerView();

    void requestBannerAd(MediationBannerListener arg1, Activity arg2, MediationServerParameters arg3, AdSize arg4, MediationAdRequest arg5, NetworkExtras arg6);
}

