package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzcod extends zzani {
    private zzcmd zzgbp;

    private zzcod(zzcny zzcny0, zzcmd zzcmd0) {
        this.zzgbp = zzcmd0;
    }

    zzcod(zzcny zzcny0, zzcmd zzcmd0, zzcoa zzcoa0) {
        this(zzcny0, zzcmd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanj
    public final void zzdm(String s) throws RemoteException {
        ((zzcni)this.zzgbp.zzgbd).onAdFailedToLoad(0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanj
    public final void zztg() throws RemoteException {
        ((zzcni)this.zzgbp.zzgbd).onAdLoaded();
    }
}

