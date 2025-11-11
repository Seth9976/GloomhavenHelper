package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;

public final class zzaec extends zzgc implements zzaed {
    zzaec(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.IOnMediaContentChangedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzaed
    public final void zzrt() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }
}

