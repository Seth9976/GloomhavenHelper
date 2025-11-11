package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzgc implements IInterface {
    private final IBinder zzaay;
    private final String zzaaz;

    protected zzgc(IBinder iBinder0, String s) {
        this.zzaay = iBinder0;
        this.zzaaz = s;
    }

    @Override  // android.os.IInterface
    public IBinder asBinder() {
        return this.zzaay;
    }

    protected final Parcel obtainAndWriteInterfaceToken() {
        Parcel parcel0 = Parcel.obtain();
        parcel0.writeInterfaceToken(this.zzaaz);
        return parcel0;
    }

    protected final Parcel transactAndReadException(int v, Parcel parcel0) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        try {
            this.zzaay.transact(v, parcel0, parcel1, 0);
            parcel1.readException();
            return parcel1;
        }
        catch(RuntimeException runtimeException0) {
            parcel1.recycle();
            throw runtimeException0;
        }
        finally {
            parcel0.recycle();
        }
    }

    protected final void zza(int v, Parcel parcel0) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        try {
            this.zzaay.transact(v, parcel0, parcel1, 0);
            parcel1.readException();
        }
        finally {
            parcel0.recycle();
            parcel1.recycle();
        }
    }

    protected final void zzb(int v, Parcel parcel0) throws RemoteException {
        try {
            this.zzaay.transact(2, parcel0, null, 1);
        }
        finally {
            parcel0.recycle();
        }
    }
}

