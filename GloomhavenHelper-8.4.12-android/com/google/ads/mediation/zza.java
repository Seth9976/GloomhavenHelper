package com.google.ads.mediation;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

final class zza implements RewardedVideoAdListener {
    private final AbstractAdViewAdapter zzlr;

    zza(AbstractAdViewAdapter abstractAdViewAdapter0) {
        this.zzlr = abstractAdViewAdapter0;
        super();
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewarded(RewardItem rewardItem0) {
        AbstractAdViewAdapter.zza(this.zzlr).onRewarded(this.zzlr, rewardItem0);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdClosed() {
        AbstractAdViewAdapter.zza(this.zzlr).onAdClosed(this.zzlr);
        AbstractAdViewAdapter.zza(this.zzlr, null);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdFailedToLoad(int v) {
        AbstractAdViewAdapter.zza(this.zzlr).onAdFailedToLoad(this.zzlr, v);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdLeftApplication() {
        AbstractAdViewAdapter.zza(this.zzlr).onAdLeftApplication(this.zzlr);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdLoaded() {
        AbstractAdViewAdapter.zza(this.zzlr).onAdLoaded(this.zzlr);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdOpened() {
        AbstractAdViewAdapter.zza(this.zzlr).onAdOpened(this.zzlr);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoCompleted() {
        AbstractAdViewAdapter.zza(this.zzlr).onVideoCompleted(this.zzlr);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoStarted() {
        AbstractAdViewAdapter.zza(this.zzlr).onVideoStarted(this.zzlr);
    }
}

