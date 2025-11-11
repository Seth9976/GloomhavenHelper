package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzcmz extends zzanh {
    private zzcmd zzgbp;
    private final zzcmu zzgbq;

    private zzcmz(zzcmu zzcmu0, zzcmd zzcmd0) {
        this.zzgbq = zzcmu0;
        super();
        this.zzgbp = zzcmd0;
    }

    zzcmz(zzcmu zzcmu0, zzcmd zzcmd0, zzcmw zzcmw0) {
        this(zzcmu0, zzcmd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzane
    public final void zzdm(String s) throws RemoteException {
        ((zzcni)this.zzgbp.zzgbd).onAdFailedToLoad(0);
    }

    @Override  // com.google.android.gms.internal.ads.zzane
    public final void zzx(IObjectWrapper iObjectWrapper0) throws RemoteException {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
        this.zzgbq.view = view0;
        ((zzcni)this.zzgbp.zzgbd).onAdLoaded();
    }
}

