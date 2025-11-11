package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzxg extends zzgc implements zzxe {
    zzxg(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IResponseInfo");
    }

    @Override  // com.google.android.gms.internal.ads.zzxe
    public final String getMediationAdapterClassName() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzxe
    public final String getResponseId() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }
}

