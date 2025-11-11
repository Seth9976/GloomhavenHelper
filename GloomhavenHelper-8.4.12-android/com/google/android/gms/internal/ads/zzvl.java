package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;

public final class zzvl extends zzgc implements zzvj {
    zzvl(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IAdClickListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzvj
    public final void onAdClicked() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }
}

