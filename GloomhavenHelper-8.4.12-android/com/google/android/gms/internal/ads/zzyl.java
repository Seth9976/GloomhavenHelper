package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzyl implements Runnable {
    private final zzyi zzcga;

    zzyl(zzyi zzyi0) {
        this.zzcga = zzyi0;
        super();
    }

    @Override
    public final void run() {
        if(zzyg.zza(this.zzcga.zzcfz) != null) {
            try {
                zzyg.zza(this.zzcga.zzcfz).onAdFailedToLoad(1);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Could not notify onAdFailedToLoad event.", remoteException0);
            }
        }
    }
}

