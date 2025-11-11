package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzaar extends zzgc implements zzaap {
    zzaar(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzaap
    public final String getContent() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzaap
    public final void recordClick() throws RemoteException {
        this.zza(4, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzaap
    public final void recordImpression() throws RemoteException {
        this.zza(5, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzaap
    public final void zzn(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaap
    public final String zzqx() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }
}

