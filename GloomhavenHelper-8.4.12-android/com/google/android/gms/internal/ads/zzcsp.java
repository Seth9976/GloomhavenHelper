package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzcsp implements zzbqm {
    private final zzcsg zzggq;
    private final zzaht zzggr;

    zzcsp(zzcsg zzcsg0, zzaht zzaht0) {
        this.zzggq = zzcsg0;
        this.zzggr = zzaht0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqm
    public final void onAdFailedToLoad(int v) {
        zzaht zzaht0 = this.zzggr;
        this.zzggq.onAdFailedToLoad(v);
        if(zzaht0 != null) {
            try {
                zzaht0.onInstreamAdFailedToLoad(v);
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }
}

