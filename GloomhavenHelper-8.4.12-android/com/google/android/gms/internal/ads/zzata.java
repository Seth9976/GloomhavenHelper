package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzata extends zzgc implements zzasy {
    zzata(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzasy
    public final void onRewardedAdClosed() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzasy
    public final void onRewardedAdFailedToShow(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(4, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasy
    public final void onRewardedAdOpened() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzasy
    public final void zza(zzass zzass0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzass0);
        this.zza(3, parcel0);
    }
}

