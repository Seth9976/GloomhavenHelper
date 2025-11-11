package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzyt implements Runnable {
    private final zzatb zzcgf;

    zzyt(zzatb zzatb0) {
        this.zzcgf = zzatb0;
    }

    @Override
    public final void run() {
        zzatb zzatb0 = this.zzcgf;
        if(zzatb0 != null) {
            try {
                zzatb0.onRewardedAdFailedToLoad(1);
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }
}

