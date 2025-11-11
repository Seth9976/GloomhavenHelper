package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public final class zzacl extends zzgc implements zzacj {
    zzacl(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }

    @Override  // com.google.android.gms.internal.ads.zzacj
    public final String getText() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzacj
    public final List zzrb() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        List list0 = zzgd.zzb(parcel0);
        parcel0.recycle();
        return list0;
    }
}

