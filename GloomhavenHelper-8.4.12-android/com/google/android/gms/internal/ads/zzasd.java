package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzasd extends zzgc implements zzasb {
    zzasd(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoAdClosed() throws RemoteException {
        this.zza(4, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoAdFailedToLoad(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoAdLeftApplication() throws RemoteException {
        this.zza(6, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoAdLoaded() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoAdOpened() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoCompleted() throws RemoteException {
        this.zza(8, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void onRewardedVideoStarted() throws RemoteException {
        this.zza(3, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzasb
    public final void zza(zzarr zzarr0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzarr0);
        this.zza(5, parcel0);
    }
}

