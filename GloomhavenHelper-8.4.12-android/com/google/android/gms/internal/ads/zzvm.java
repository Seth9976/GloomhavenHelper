package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzvm extends zzgc implements zzvk {
    zzvm(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IAdListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdClicked() throws RemoteException {
        this.zza(6, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdClosed() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdFailedToLoad(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdImpression() throws RemoteException {
        this.zza(7, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdLeftApplication() throws RemoteException {
        this.zza(3, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdLoaded() throws RemoteException {
        this.zza(4, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdOpened() throws RemoteException {
        this.zza(5, this.obtainAndWriteInterfaceToken());
    }
}

