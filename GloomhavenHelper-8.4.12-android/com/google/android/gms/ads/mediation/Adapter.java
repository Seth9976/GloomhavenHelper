package com.google.android.gms.ads.mediation;

import android.content.Context;
import java.util.List;

public abstract class Adapter implements MediationExtrasReceiver {
    public abstract VersionInfo getSDKVersionInfo();

    public abstract VersionInfo getVersionInfo();

    public abstract void initialize(Context arg1, InitializationCompleteCallback arg2, List arg3);

    public void loadBannerAd(MediationBannerAdConfiguration mediationBannerAdConfiguration0, MediationAdLoadCallback mediationAdLoadCallback0) {
        mediationAdLoadCallback0.onFailure(this.getClass().getSimpleName() + " does not support banner ads.");
    }

    public void loadInterstitialAd(MediationInterstitialAdConfiguration mediationInterstitialAdConfiguration0, MediationAdLoadCallback mediationAdLoadCallback0) {
        mediationAdLoadCallback0.onFailure(this.getClass().getSimpleName() + " does not support interstitial ads.");
    }

    public void loadNativeAd(MediationNativeAdConfiguration mediationNativeAdConfiguration0, MediationAdLoadCallback mediationAdLoadCallback0) {
        mediationAdLoadCallback0.onFailure(this.getClass().getSimpleName() + " does not support native ads.");
    }

    public void loadRewardedAd(MediationRewardedAdConfiguration mediationRewardedAdConfiguration0, MediationAdLoadCallback mediationAdLoadCallback0) {
        mediationAdLoadCallback0.onFailure(this.getClass().getSimpleName() + " does not support rewarded ads.");
    }
}

