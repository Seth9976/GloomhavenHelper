package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;

public final class zzwc extends zzgc implements zzwa {
    zzwc(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IAdMetadataListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzwa
    public final void onAdMetadataChanged() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }
}

