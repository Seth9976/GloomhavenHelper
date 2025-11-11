package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzww extends zzgc implements zzwu {
    zzww(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IMuteThisAdReason");
    }

    @Override  // com.google.android.gms.internal.ads.zzwu
    public final String getDescription() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzwu
    public final String zzpm() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }
}

