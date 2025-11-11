package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzj extends zza implements zzi {
    zzj(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    @Override  // com.google.android.gms.dynamite.zzi
    public final int zza(IObjectWrapper iObjectWrapper0, String s, boolean z) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        zzc.writeBoolean(parcel0, z);
        Parcel parcel1 = this.zza(3, parcel0);
        int v = parcel1.readInt();
        parcel1.recycle();
        return v;
    }

    @Override  // com.google.android.gms.dynamite.zzi
    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper0, String s, int v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(v);
        Parcel parcel1 = this.zza(2, parcel0);
        IObjectWrapper iObjectWrapper1 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper1;
    }

    @Override  // com.google.android.gms.dynamite.zzi
    public final int zzak() throws RemoteException {
        Parcel parcel0 = this.zza(6, this.zza());
        int v = parcel0.readInt();
        parcel0.recycle();
        return v;
    }

    @Override  // com.google.android.gms.dynamite.zzi
    public final int zzb(IObjectWrapper iObjectWrapper0, String s, boolean z) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        zzc.writeBoolean(parcel0, z);
        Parcel parcel1 = this.zza(5, parcel0);
        int v = parcel1.readInt();
        parcel1.recycle();
        return v;
    }

    @Override  // com.google.android.gms.dynamite.zzi
    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper0, String s, int v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(v);
        Parcel parcel1 = this.zza(4, parcel0);
        IObjectWrapper iObjectWrapper1 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper1;
    }
}

