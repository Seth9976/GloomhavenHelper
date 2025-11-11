package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;

public final class zzro extends zzgc implements zzrm {
    zzro(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.appopen.client.IAppOpenAdPresentationCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzrm
    public final void onAppOpenAdClosed() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }
}

