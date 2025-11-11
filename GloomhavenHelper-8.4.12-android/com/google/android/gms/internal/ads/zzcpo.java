package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzcpo extends zzano {
    private zzcmd zzgbp;

    private zzcpo(zzcpn zzcpn0, zzcmd zzcmd0) {
        this.zzgbp = zzcmd0;
    }

    zzcpo(zzcpn zzcpn0, zzcmd zzcmd0, zzcpp zzcpp0) {
        this(zzcpn0, zzcmd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanp
    public final void zzdm(String s) throws RemoteException {
        ((zzcni)this.zzgbp.zzgbd).onAdFailedToLoad(0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanp
    public final void zztg() throws RemoteException {
        ((zzcni)this.zzgbp.zzgbd).onAdLoaded();
    }
}

