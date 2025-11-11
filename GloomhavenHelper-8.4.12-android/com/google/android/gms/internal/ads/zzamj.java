package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;

final class zzamj implements MediationAdLoadCallback {
    private final zzalq zzdet;
    private final Adapter zzdeu;
    private final zzamg zzdev;

    zzamj(zzamg zzamg0, zzalq zzalq0, Adapter adapter0) {
        this.zzdev = zzamg0;
        this.zzdet = zzalq0;
        this.zzdeu = adapter0;
        super();
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String s) {
        try {
            zzazh.zzeb((this.zzdeu.getClass().getCanonicalName() + "failed to loaded medation ad: " + s));
            this.zzdet.onAdFailedToLoad(0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final Object onSuccess(Object object0) {
        return this.zza(((MediationRewardedAd)object0));
    }

    private final MediationRewardedAdCallback zza(MediationRewardedAd mediationRewardedAd0) {
        try {
            zzamg.zza(this.zzdev, mediationRewardedAd0);
            this.zzdet.onAdLoaded();
            return new zzatq(this.zzdet);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return new zzatq(this.zzdet);
        }
    }
}

