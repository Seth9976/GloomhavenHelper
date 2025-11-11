package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

final class zzagv extends zzago {
    private final zzazy zzbsb;

    zzagv(zzags zzags0, zzazy zzazy0) {
        this.zzbsb = zzazy0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzagp
    public final void zza(ParcelFileDescriptor parcelFileDescriptor0) throws RemoteException {
        this.zzbsb.set(parcelFileDescriptor0);
    }
}

