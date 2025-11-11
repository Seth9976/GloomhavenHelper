package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzals extends zzgc implements zzalq {
    zzals(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdClicked() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdClosed() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdFailedToLoad(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdImpression() throws RemoteException {
        this.zza(8, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdLeftApplication() throws RemoteException {
        this.zza(4, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdLoaded() throws RemoteException {
        this.zza(6, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdOpened() throws RemoteException {
        this.zza(5, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAppEvent(String s, String s1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        this.zza(9, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoEnd() throws RemoteException {
        this.zza(11, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoPause() throws RemoteException {
        this.zza(15, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoPlay() throws RemoteException {
        this.zza(20, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzadn zzadn0, String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzadn0);
        parcel0.writeString(s);
        this.zza(10, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzalv zzalv0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzalv0);
        this.zza(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzass zzass0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzass0);
        this.zza(16, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzb(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, bundle0);
        this.zza(19, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzb(zzasq zzasq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzasq0);
        this.zza(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzco(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(17, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzdk(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(12, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzsx() throws RemoteException {
        this.zza(13, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzsy() throws RemoteException {
        this.zza(18, this.obtainAndWriteInterfaceToken());
    }
}

