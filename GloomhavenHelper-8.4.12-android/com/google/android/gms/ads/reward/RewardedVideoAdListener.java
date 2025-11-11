package com.google.android.gms.ads.reward;

public interface RewardedVideoAdListener {
    void onRewarded(RewardItem arg1);

    void onRewardedVideoAdClosed();

    void onRewardedVideoAdFailedToLoad(int arg1);

    void onRewardedVideoAdLeftApplication();

    void onRewardedVideoAdLoaded();

    void onRewardedVideoAdOpened();

    void onRewardedVideoCompleted();

    void onRewardedVideoStarted();
}

