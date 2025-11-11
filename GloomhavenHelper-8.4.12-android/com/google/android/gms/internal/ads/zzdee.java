package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.AdMetadataListener;

final class zzdee extends AdMetadataListener {
    private final zzdec zzgpc;
    private final zzwa zzgpd;

    zzdee(zzdec zzdec0, zzwa zzwa0) {
        this.zzgpc = zzdec0;
        this.zzgpd = zzwa0;
        super();
    }

    @Override  // com.google.android.gms.ads.reward.AdMetadataListener
    public final void onAdMetadataChanged() {
        if(zzdec.zza(this.zzgpc) != null) {
            try {
                this.zzgpd.onAdMetadataChanged();
            }
            catch(RemoteException remoteException0) {
                zzawf.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }
}

