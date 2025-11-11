package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzasa extends zzgc implements zzary {
    zzasa(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void destroy() throws RemoteException {
        this.zza(8, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final Bundle getAdMetadata() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(15, this.obtainAndWriteInterfaceToken());
        Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
        parcel0.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final String getMediationAdapterClassName() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(12, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final boolean isLoaded() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void pause() throws RemoteException {
        this.zza(6, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void resume() throws RemoteException {
        this.zza(7, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setAppPackageName(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(17, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setCustomData(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(19, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setImmersiveMode(boolean z) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.writeBoolean(parcel0, z);
        this.zza(34, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setUserId(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(13, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void show() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzarw zzarw0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzarw0);
        this.zza(16, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzasb zzasb0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzasb0);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzash zzash0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzash0);
        this.zza(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzwa zzwa0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzwa0);
        this.zza(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzi(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(18, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzj(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(9, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzk(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(10, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final zzxe zzkg() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(21, this.obtainAndWriteInterfaceToken());
        zzxe zzxe0 = zzxh.zzj(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzxe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzl(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(11, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final boolean zzqd() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(20, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }
}

