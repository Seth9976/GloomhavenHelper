package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdCallback;

final class zzaob implements MediationAdLoadCallback {
    private final zzalq zzdfo;
    private final zzanz zzdft;
    private final zzanj zzdfu;

    zzaob(zzanz zzanz0, zzanj zzanj0, zzalq zzalq0) {
        this.zzdft = zzanz0;
        this.zzdfu = zzanj0;
        this.zzdfo = zzalq0;
        super();
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String s) {
        try {
            this.zzdfu.zzdm(s);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final Object onSuccess(Object object0) {
        return this.zza(((MediationInterstitialAd)object0));
    }

    private final MediationInterstitialAdCallback zza(MediationInterstitialAd mediationInterstitialAd0) {
        if(mediationInterstitialAd0 == null) {
            zzazh.zzfa("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdfu.zzdm("Adapter returned null.");
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("", remoteException0);
            }
            return null;
        }
        try {
            zzanz.zza(this.zzdft, mediationInterstitialAd0);
            this.zzdfu.zztg();
            return new zzaof(this.zzdfo);
        }
        catch(RemoteException remoteException1) {
            zzazh.zzc("", remoteException1);
            return new zzaof(this.zzdfo);
        }
    }
}

