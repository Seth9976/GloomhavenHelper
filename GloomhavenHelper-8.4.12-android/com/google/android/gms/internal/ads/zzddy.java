package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.AdMetadataListener;

final class zzddy extends AdMetadataListener {
    private final zzddw zzgox;
    private final zzwy zzgpb;

    zzddy(zzddw zzddw0, zzwy zzwy0) {
        this.zzgox = zzddw0;
        this.zzgpb = zzwy0;
        super();
    }

    @Override  // com.google.android.gms.ads.reward.AdMetadataListener
    public final void onAdMetadataChanged() {
        if(zzddw.zza(this.zzgox) != null) {
            try {
                this.zzgpb.onAdMetadataChanged();
            }
            catch(RemoteException remoteException0) {
                zzawf.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }
}

