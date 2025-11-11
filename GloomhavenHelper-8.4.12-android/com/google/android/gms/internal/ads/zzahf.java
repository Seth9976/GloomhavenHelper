package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzahf extends zzge implements zzahc {
    public zzahf() {
        super("com.google.android.gms.ads.internal.initialization.IInitializationCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            this.zzd(parcel0.createTypedArrayList(zzagz.CREATOR));
            parcel1.writeNoException();
            return true;
        }
        return false;
    }

    public static zzahc zzaa(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.initialization.IInitializationCallback");
        return iInterface0 instanceof zzahc ? ((zzahc)iInterface0) : new zzahe(iBinder0);
    }
}

