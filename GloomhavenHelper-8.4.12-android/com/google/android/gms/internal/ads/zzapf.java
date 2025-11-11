package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzapf extends zzgc implements zzapg {
    zzapf(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
    }

    @Override  // com.google.android.gms.internal.ads.zzapg
    public final IBinder zzae(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.transactAndReadException(1, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        parcel1.recycle();
        return iBinder0;
    }
}

