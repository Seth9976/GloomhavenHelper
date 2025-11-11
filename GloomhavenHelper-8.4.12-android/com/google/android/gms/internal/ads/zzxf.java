package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzxf extends zzgc implements zzxd {
    zzxf(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IOnPaidEventListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzxd
    public final void zza(zzum zzum0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzum0);
        this.zza(1, parcel0);
    }
}

