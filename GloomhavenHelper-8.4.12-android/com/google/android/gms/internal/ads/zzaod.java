package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationNativeAdCallback;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;

final class zzaod implements MediationAdLoadCallback {
    private final zzalq zzdfo;
    private final zzank zzdfw;

    zzaod(zzanz zzanz0, zzank zzank0, zzalq zzalq0) {
        this.zzdfw = zzank0;
        this.zzdfo = zzalq0;
        super();
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String s) {
        try {
            this.zzdfw.zzdm(s);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final Object onSuccess(Object object0) {
        return this.zza(((UnifiedNativeAdMapper)object0));
    }

    private final MediationNativeAdCallback zza(UnifiedNativeAdMapper unifiedNativeAdMapper0) {
        if(unifiedNativeAdMapper0 == null) {
            zzazh.zzfa("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdfw.zzdm("Adapter returned null.");
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("", remoteException0);
            }
            return null;
        }
        try {
            zzanf zzanf0 = new zzanf(unifiedNativeAdMapper0);
            this.zzdfw.zza(zzanf0);
            return new zzaof(this.zzdfo);
        }
        catch(RemoteException remoteException1) {
            zzazh.zzc("", remoteException1);
            return new zzaof(this.zzdfo);
        }
    }
}

