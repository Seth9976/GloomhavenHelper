package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzamv implements Runnable {
    private final zzamr zzdfi;

    zzamv(zzamr zzamr0) {
        this.zzdfi = zzamr0;
        super();
    }

    @Override
    public final void run() {
        try {
            this.zzdfi.zzdex.onAdClosed();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

