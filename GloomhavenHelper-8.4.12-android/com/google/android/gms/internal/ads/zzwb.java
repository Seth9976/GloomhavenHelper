package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzwb extends zzgc implements zzvy {
    zzwb(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IAdManagerCreator");
    }

    @Override  // com.google.android.gms.internal.ads.zzvy
    public final IBinder zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, String s, zzalk zzalk0, int v, int v1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuk0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzalk0);
        parcel0.writeInt(v);
        parcel0.writeInt(v1);
        Parcel parcel1 = this.transactAndReadException(2, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        parcel1.recycle();
        return iBinder0;
    }
}

