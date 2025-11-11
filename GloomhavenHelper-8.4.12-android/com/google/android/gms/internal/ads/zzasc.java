package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzasc extends zzgc implements zzarz {
    zzasc(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
    }

    @Override  // com.google.android.gms.internal.ads.zzarz
    public final IBinder zzb(IObjectWrapper iObjectWrapper0, zzalk zzalk0, int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzalk0);
        parcel0.writeInt(v);
        Parcel parcel1 = this.transactAndReadException(1, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        parcel1.recycle();
        return iBinder0;
    }
}

