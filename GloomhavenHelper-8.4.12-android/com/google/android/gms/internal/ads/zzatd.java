package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzatd extends zzgc implements zzatb {
    zzatd(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.rewarded.client.IRewardedAdLoadCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzatb
    public final void onRewardedAdFailedToLoad(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzatb
    public final void onRewardedAdLoaded() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }
}

