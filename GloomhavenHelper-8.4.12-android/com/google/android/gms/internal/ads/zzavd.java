package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzavd extends zzgc implements zzavb {
    zzavd(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.signals.ISignalGenerator");
    }

    @Override  // com.google.android.gms.internal.ads.zzavb
    public final void zza(IObjectWrapper iObjectWrapper0, zzavh zzavh0, zzava zzava0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzavh0);
        zzgd.zza(parcel0, zzava0);
        this.zza(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzavb
    public final void zzan(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzavb
    public final IObjectWrapper zzao(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.transactAndReadException(4, parcel0);
        IObjectWrapper iObjectWrapper1 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper1;
    }

    @Override  // com.google.android.gms.internal.ads.zzavb
    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, iObjectWrapper1);
        Parcel parcel1 = this.transactAndReadException(3, parcel0);
        IObjectWrapper iObjectWrapper2 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper2;
    }
}

