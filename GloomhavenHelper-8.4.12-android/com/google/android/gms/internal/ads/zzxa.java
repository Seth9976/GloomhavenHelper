package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;

public final class zzxa extends zzgc implements zzwy {
    zzxa(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IOnAdMetadataChangedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzwy
    public final void onAdMetadataChanged() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }
}

