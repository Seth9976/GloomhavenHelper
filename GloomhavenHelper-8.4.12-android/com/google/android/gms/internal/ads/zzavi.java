package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzavi extends zzgc implements zzavg {
    zzavi(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.signals.ISignalGeneratorCreator");
    }

    @Override  // com.google.android.gms.internal.ads.zzavg
    public final zzavb zzf(IObjectWrapper iObjectWrapper0, int v) throws RemoteException {
        zzavb zzavb0;
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeInt(v);
        Parcel parcel1 = this.transactAndReadException(1, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        if(iBinder0 == null) {
            zzavb0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.signals.ISignalGenerator");
            zzavb0 = iInterface0 instanceof zzavb ? ((zzavb)iInterface0) : new zzavd(iBinder0);
        }
        parcel1.recycle();
        return zzavb0;
    }
}

