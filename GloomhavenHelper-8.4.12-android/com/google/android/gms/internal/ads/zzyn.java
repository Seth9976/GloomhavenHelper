package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzyn implements Runnable {
    private final zzyk zzcgc;

    zzyn(zzyk zzyk0) {
        this.zzcgc = zzyk0;
        super();
    }

    @Override
    public final void run() {
        if(zzyk.zza(this.zzcgc) != null) {
            try {
                zzyk.zza(this.zzcgc).onAdFailedToLoad(1);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Could not notify onAdFailedToLoad event.", remoteException0);
            }
        }
    }
}

