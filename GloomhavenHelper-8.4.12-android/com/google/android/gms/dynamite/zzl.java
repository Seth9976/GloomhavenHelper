package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzl extends zza implements zzk {
    zzl(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    @Override  // com.google.android.gms.dynamite.zzk
    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper0, String s, int v, IObjectWrapper iObjectWrapper1) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(v);
        zzc.zza(parcel0, iObjectWrapper1);
        Parcel parcel1 = this.zza(2, parcel0);
        IObjectWrapper iObjectWrapper2 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper2;
    }

    @Override  // com.google.android.gms.dynamite.zzk
    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper0, String s, int v, IObjectWrapper iObjectWrapper1) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(v);
        zzc.zza(parcel0, iObjectWrapper1);
        Parcel parcel1 = this.zza(3, parcel0);
        IObjectWrapper iObjectWrapper2 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper2;
    }
}

