package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.common.internal.Preconditions;

public final class zzatq implements MediationRewardedAdCallback {
    private final zzalq zzdex;

    public zzatq(zzalq zzalq0) {
        this.zzdex = zzalq0;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void onAdClosed() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdClosed.");
        try {
            this.zzdex.onAdClosed();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onAdFailedToShow(String s) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdFailedToShow.");
        String s1 = String.valueOf(s);
        zzazh.zzfa((s1.length() == 0 ? new String("Mediation ad failed to show: ") : "Mediation ad failed to show: " + s1));
        try {
            this.zzdex.zzco(0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void onAdOpened() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdOpened.");
        try {
            this.zzdex.onAdOpened();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onUserEarnedReward(RewardItem rewardItem0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onUserEarnedReward.");
        try {
            zzatp zzatp0 = new zzatp(rewardItem0);
            this.zzdex.zza(zzatp0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onVideoComplete() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onVideoComplete.");
        try {
            this.zzdex.zzsy();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onVideoStart() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onVideoStart.");
        try {
            this.zzdex.zzsx();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void reportAdClicked() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called reportAdClicked.");
        try {
            this.zzdex.onAdClicked();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void reportAdImpression() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called reportAdImpression.");
        try {
            this.zzdex.onAdImpression();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

