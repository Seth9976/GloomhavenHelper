package com.google.android.gms.internal.common;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzb extends Binder implements IInterface {
    private static zzd zzc;

    static {
    }

    protected zzb(String s) {
        this.attachInterface(this, s);
    }

    @Override  // android.os.IInterface
    public IBinder asBinder() [...] // Inlined contents

    @Override  // android.os.Binder
    public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v > 0xFFFFFF) {
            return super.onTransact(v, parcel0, parcel1, v1) ? true : this.zza(v, parcel0, parcel1, v1);
        }
        parcel0.enforceInterface(this.getInterfaceDescriptor());
        return this.zza(v, parcel0, parcel1, v1);
    }

    protected boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        return false;
    }
}

