package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzsd extends zzgc implements zzse {
    zzsd(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.cache.ICacheService");
    }

    @Override  // com.google.android.gms.internal.ads.zzse
    public final zzry zza(zzrz zzrz0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzrz0);
        Parcel parcel1 = this.transactAndReadException(1, parcel0);
        zzry zzry0 = (zzry)zzgd.zza(parcel1, zzry.CREATOR);
        parcel1.recycle();
        return zzry0;
    }
}

