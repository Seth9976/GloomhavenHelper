package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.zzk;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzo extends zza implements zzm {
    zzo(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    @Override  // com.google.android.gms.common.internal.zzm
    public final boolean zza(zzk zzk0, IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zza(parcel0, zzk0);
        zzc.zza(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.zza(5, parcel0);
        boolean z = zzc.zza(parcel1);
        parcel1.recycle();
        return z;
    }
}

