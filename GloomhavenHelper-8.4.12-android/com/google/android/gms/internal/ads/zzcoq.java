package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzcoq extends zzann {
    private zzcmd zzgbp;
    private final zzcoo zzgcw;

    private zzcoq(zzcoo zzcoo0, zzcmd zzcmd0) {
        this.zzgcw = zzcoo0;
        super();
        this.zzgbp = zzcmd0;
    }

    zzcoq(zzcoo zzcoo0, zzcmd zzcmd0, zzcor zzcor0) {
        this(zzcoo0, zzcmd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzank
    public final void zza(zzamd zzamd0) throws RemoteException {
        this.zzgcw.zzgcv = zzamd0;
        ((zzcni)this.zzgbp.zzgbd).onAdLoaded();
    }

    @Override  // com.google.android.gms.internal.ads.zzank
    public final void zzdm(String s) throws RemoteException {
        ((zzcni)this.zzgbp.zzgbd).onAdFailedToLoad(0);
    }
}

