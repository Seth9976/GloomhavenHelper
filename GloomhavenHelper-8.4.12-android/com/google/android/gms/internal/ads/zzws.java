package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;

public final class zzws extends zzgc implements zzwq {
    zzws(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IMuteThisAdListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzwq
    public final void onAdMuted() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }
}

