package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzyv implements Runnable {
    private final zzys zzcgh;

    zzyv(zzys zzys0) {
        this.zzcgh = zzys0;
        super();
    }

    @Override
    public final void run() {
        if(zzys.zza(this.zzcgh) != null) {
            try {
                zzys.zza(this.zzcgh).onRewardedVideoAdFailedToLoad(1);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Could not notify onRewardedVideoAdFailedToLoad event.", remoteException0);
            }
        }
    }
}

