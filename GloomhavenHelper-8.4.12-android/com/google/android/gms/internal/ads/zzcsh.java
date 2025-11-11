package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import javax.annotation.concurrent.GuardedBy;

public final class zzcsh implements zztz {
    @GuardedBy("this")
    private zzvj zzgge;

    @Override  // com.google.android.gms.internal.ads.zztz
    public final void onAdClicked() {
        synchronized(this) {
            if(this.zzgge != null) {
                try {
                    this.zzgge.onAdClicked();
                }
                catch(RemoteException remoteException0) {
                    zzawf.zzd("Remote Exception at onAdClicked.", remoteException0);
                }
            }
        }
    }

    public final void zzb(zzvj zzvj0) {
        synchronized(this) {
            this.zzgge = zzvj0;
        }
    }
}

