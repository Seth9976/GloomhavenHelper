package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzbgh extends zzgc implements zzbgf {
    zzbgh(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.measurement.IMeasurementManager");
    }

    @Override  // com.google.android.gms.internal.ads.zzbgf
    public final void zza(IObjectWrapper iObjectWrapper0, zzbgd zzbgd0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzbgd0);
        this.zza(2, parcel0);
    }
}

