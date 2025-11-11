package com.google.android.gms.ads.reward.mediation;

import android.os.Bundle;
import com.google.android.gms.ads.reward.RewardItem;

public interface MediationRewardedVideoAdListener {
    void onAdClicked(MediationRewardedVideoAdAdapter arg1);

    void onAdClosed(MediationRewardedVideoAdAdapter arg1);

    void onAdFailedToLoad(MediationRewardedVideoAdAdapter arg1, int arg2);

    void onAdLeftApplication(MediationRewardedVideoAdAdapter arg1);

    void onAdLoaded(MediationRewardedVideoAdAdapter arg1);

    void onAdOpened(MediationRewardedVideoAdAdapter arg1);

    void onInitializationFailed(MediationRewardedVideoAdAdapter arg1, int arg2);

    void onInitializationSucceeded(MediationRewardedVideoAdAdapter arg1);

    void onRewarded(MediationRewardedVideoAdAdapter arg1, RewardItem arg2);

    void onVideoCompleted(MediationRewardedVideoAdAdapter arg1);

    void onVideoStarted(MediationRewardedVideoAdAdapter arg1);

    void zzb(Bundle arg1);
}

