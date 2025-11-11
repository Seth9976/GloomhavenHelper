package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzahp extends zzgc implements zzahn {
    zzahp(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.instream.client.IInstreamAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzahn
    public final void destroy() throws RemoteException {
        this.zza(4, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzahn
    public final zzxj getVideoController() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        zzxj zzxj0 = zzxi.zzk(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzxj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzahn
    public final void zza(IObjectWrapper iObjectWrapper0, zzaho zzaho0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzaho0);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzahn
    public final void zzr(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(6, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzahn
    public final zzacm zzrv() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        zzacm zzacm0 = zzacp.zzn(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzacm0;
    }
}

