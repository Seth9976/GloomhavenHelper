package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationBannerAd;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzany implements MediationAdLoadCallback {
    private final zzane zzdfn;
    private final zzalq zzdfo;

    zzany(zzanz zzanz0, zzane zzane0, zzalq zzalq0) {
        this.zzdfn = zzane0;
        this.zzdfo = zzalq0;
        super();
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String s) {
        try {
            this.zzdfn.zzdm(s);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final Object onSuccess(Object object0) {
        return this.zza(((MediationBannerAd)object0));
    }

    private final MediationBannerAdCallback zza(MediationBannerAd mediationBannerAd0) {
        if(mediationBannerAd0 == null) {
            zzazh.zzfa("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdfn.zzdm("Adapter returned null.");
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("", remoteException0);
            }
            return null;
        }
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(mediationBannerAd0.getView());
            this.zzdfn.zzx(iObjectWrapper0);
            return new zzaof(this.zzdfo);
        }
        catch(RemoteException remoteException1) {
            zzazh.zzc("", remoteException1);
            return new zzaof(this.zzdfo);
        }
    }
}

