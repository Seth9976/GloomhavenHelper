package com.google.ads.mediation;

import com.google.ads.AdRequest.ErrorCode;

@Deprecated
public interface MediationBannerListener {
    void onClick(MediationBannerAdapter arg1);

    void onDismissScreen(MediationBannerAdapter arg1);

    void onFailedToReceiveAd(MediationBannerAdapter arg1, ErrorCode arg2);

    void onLeaveApplication(MediationBannerAdapter arg1);

    void onPresentScreen(MediationBannerAdapter arg1);

    void onReceivedAd(MediationBannerAdapter arg1);
}

