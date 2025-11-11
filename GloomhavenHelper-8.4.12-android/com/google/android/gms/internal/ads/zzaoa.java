package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;

final class zzaoa implements MediationAdLoadCallback {
    private final zzalq zzdfo;
    private final zzanp zzdfs;
    private final zzanz zzdft;

    zzaoa(zzanz zzanz0, zzanp zzanp0, zzalq zzalq0) {
        this.zzdft = zzanz0;
        this.zzdfs = zzanp0;
        this.zzdfo = zzalq0;
        super();
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String s) {
        try {
            this.zzdfs.zzdm(s);
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
        if(mediationRewardedAd0 == null) {
            zzazh.zzfa("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdfs.zzdm("Adapter returned null.");
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("", remoteException0);
            }
            return null;
        }
        try {
            zzanz.zza(this.zzdft, mediationRewardedAd0);
            this.zzdfs.zztg();
            return new zzaof(this.zzdfo);
        }
        catch(RemoteException remoteException1) {
            zzazh.zzc("", remoteException1);
            return new zzaof(this.zzdfo);
        }
    }
}

