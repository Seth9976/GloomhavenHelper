package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.ads.mediation.MediationInterstitialAdCallback;
import com.google.android.gms.ads.mediation.MediationNativeAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardItem;

final class zzaof implements MediationBannerAdCallback, MediationInterstitialAdCallback, MediationNativeAdCallback, MediationRewardedAdCallback {
    private zzalq zzdfy;

    zzaof(zzalq zzalq0) {
        this.zzdfy = zzalq0;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void onAdClosed() {
        try {
            this.zzdfy.onAdClosed();
        }
        catch(RemoteException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onAdFailedToShow(String s) {
        try {
            String s1 = String.valueOf(s);
            zzazh.zzfa((s1.length() == 0 ? new String("Mediated ad failed to show: ") : "Mediated ad failed to show: " + s1));
            this.zzdfy.zzco(0);
        }
        catch(RemoteException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerAdCallback, com.google.android.gms.ads.mediation.MediationInterstitialAdCallback, com.google.android.gms.ads.mediation.MediationNativeAdCallback
    public final void onAdLeftApplication() {
        try {
            this.zzdfy.onAdLeftApplication();
        }
        catch(RemoteException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void onAdOpened() {
        try {
            this.zzdfy.onAdOpened();
        }
        catch(RemoteException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onUserEarnedReward(RewardItem rewardItem0) {
        try {
            this.zzdfy.zza(new zzatp(rewardItem0));
        }
        catch(RemoteException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeAdCallback, com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onVideoComplete() {
        try {
            this.zzdfy.onVideoEnd();
        }
        catch(RemoteException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeAdCallback
    public final void onVideoMute() {
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeAdCallback
    public final void onVideoPause() {
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeAdCallback
    public final void onVideoPlay() {
        try {
            this.zzdfy.onVideoPlay();
        }
        catch(RemoteException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onVideoStart() {
        try {
            this.zzdfy.zzsx();
        }
        catch(RemoteException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeAdCallback
    public final void onVideoUnmute() {
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void reportAdClicked() {
        try {
            this.zzdfy.onAdClicked();
        }
        catch(RemoteException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void reportAdImpression() {
        try {
            this.zzdfy.onAdImpression();
        }
        catch(RemoteException unused_ex) {
        }
    }
}

