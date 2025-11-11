package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public final class zzasi extends zzase {
    @Nullable
    private RewardedVideoAdListener zzcfp;

    public zzasi(@Nullable RewardedVideoAdListener rewardedVideoAdListener0) {
        this.zzcfp = rewardedVideoAdListener0;
    }

    @Nullable
    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        return this.zzcfp;
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoAdClosed() {
        RewardedVideoAdListener rewardedVideoAdListener0 = this.zzcfp;
        if(rewardedVideoAdListener0 != null) {
            rewardedVideoAdListener0.onRewardedVideoAdClosed();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoAdFailedToLoad(int v) {
        RewardedVideoAdListener rewardedVideoAdListener0 = this.zzcfp;
        if(rewardedVideoAdListener0 != null) {
            rewardedVideoAdListener0.onRewardedVideoAdFailedToLoad(v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoAdLeftApplication() {
        RewardedVideoAdListener rewardedVideoAdListener0 = this.zzcfp;
        if(rewardedVideoAdListener0 != null) {
            rewardedVideoAdListener0.onRewardedVideoAdLeftApplication();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoAdLoaded() {
        RewardedVideoAdListener rewardedVideoAdListener0 = this.zzcfp;
        if(rewardedVideoAdListener0 != null) {
            rewardedVideoAdListener0.onRewardedVideoAdLoaded();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoAdOpened() {
        RewardedVideoAdListener rewardedVideoAdListener0 = this.zzcfp;
        if(rewardedVideoAdListener0 != null) {
            rewardedVideoAdListener0.onRewardedVideoAdOpened();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoCompleted() {
        RewardedVideoAdListener rewardedVideoAdListener0 = this.zzcfp;
        if(rewardedVideoAdListener0 != null) {
            rewardedVideoAdListener0.onRewardedVideoCompleted();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoStarted() {
        RewardedVideoAdListener rewardedVideoAdListener0 = this.zzcfp;
        if(rewardedVideoAdListener0 != null) {
            rewardedVideoAdListener0.onRewardedVideoStarted();
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener0) {
        this.zzcfp = rewardedVideoAdListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void zza(zzarr zzarr0) {
        RewardedVideoAdListener rewardedVideoAdListener0 = this.zzcfp;
        if(rewardedVideoAdListener0 != null) {
            rewardedVideoAdListener0.onRewarded(new zzasg(zzarr0));
        }
    }
}

