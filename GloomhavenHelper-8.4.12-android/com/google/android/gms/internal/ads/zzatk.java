package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.rewarded.RewardedAdCallback;

public final class zzatk extends zzasx {
    private final RewardedAdCallback zzdpf;

    public zzatk(RewardedAdCallback rewardedAdCallback0) {
        this.zzdpf = rewardedAdCallback0;
    }

    @Override  // com.google.android.gms.internal.ads.zzasy
    public final void onRewardedAdClosed() {
    }

    @Override  // com.google.android.gms.internal.ads.zzasy
    public final void onRewardedAdFailedToShow(int v) {
    }

    @Override  // com.google.android.gms.internal.ads.zzasy
    public final void onRewardedAdOpened() {
    }

    @Override  // com.google.android.gms.internal.ads.zzasy
    public final void zza(zzass zzass0) {
        RewardedAdCallback rewardedAdCallback0 = this.zzdpf;
        if(rewardedAdCallback0 != null) {
            rewardedAdCallback0.onUserEarnedReward(new zzath(zzass0));
        }
    }
}

