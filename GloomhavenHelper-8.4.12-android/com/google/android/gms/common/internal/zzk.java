package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;

public final class zzk extends zza implements zzi {
    zzk(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.common.internal.ICertData");
    }

    @Override  // com.google.android.gms.common.internal.zzi
    public final IObjectWrapper zzb() throws RemoteException {
        Parcel parcel0 = this.zza(1, this.zza());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.common.internal.zzi
    public final int zzc() throws RemoteException {
        Parcel parcel0 = this.zza(2, this.zza());
        int v = parcel0.readInt();
        parcel0.recycle();
        return v;
    }
}

