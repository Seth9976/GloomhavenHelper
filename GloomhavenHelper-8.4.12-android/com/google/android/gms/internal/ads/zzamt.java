package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzamt implements Runnable {
    private final zzamr zzdfi;

    zzamt(zzamr zzamr0) {
        this.zzdfi = zzamr0;
        super();
    }

    @Override
    public final void run() {
        try {
            this.zzdfi.zzdex.onAdOpened();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

