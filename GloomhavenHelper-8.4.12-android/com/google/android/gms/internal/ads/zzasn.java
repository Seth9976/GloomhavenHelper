package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzasn implements MediationRewardedVideoAdListener {
    private final zzasm zzdpc;

    public zzasn(zzasm zzasm0) {
        this.zzdpc = zzasm0;
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdClicked(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdClicked.");
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            this.zzdpc.zzak(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdClosed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdClosed.");
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            this.zzdpc.zzaj(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdFailedToLoad(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0, int v) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdFailedToLoad.");
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            this.zzdpc.zze(iObjectWrapper0, v);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdLeftApplication(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdLeftApplication.");
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            this.zzdpc.zzal(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdLoaded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdLoaded.");
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            this.zzdpc.zzag(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdOpened(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdOpened.");
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            this.zzdpc.zzah(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onInitializationFailed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0, int v) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onInitializationFailed.");
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            this.zzdpc.zzd(iObjectWrapper0, v);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onInitializationSucceeded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onInitializationSucceeded.");
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            this.zzdpc.zzaf(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onRewarded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0, RewardItem rewardItem0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onRewarded.");
        try {
            if(rewardItem0 != null) {
                IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
                zzasq zzasq0 = new zzasq(rewardItem0);
                this.zzdpc.zza(iObjectWrapper0, zzasq0);
                return;
            }
            IObjectWrapper iObjectWrapper1 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            zzasq zzasq1 = new zzasq("", 1);
            this.zzdpc.zza(iObjectWrapper1, zzasq1);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onVideoCompleted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onVideoCompleted.");
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            this.zzdpc.zzam(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onVideoStarted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onVideoStarted.");
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationRewardedVideoAdAdapter0);
            this.zzdpc.zzai(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void zzb(Bundle bundle0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdMetadataChanged.");
        try {
            this.zzdpc.zzb(bundle0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

