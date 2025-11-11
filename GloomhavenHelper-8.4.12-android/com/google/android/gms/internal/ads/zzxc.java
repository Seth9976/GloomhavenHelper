package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzxc extends zzge implements zzxd {
    public zzxc() {
        super("com.google.android.gms.ads.internal.client.IOnPaidEventListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            this.zza(((zzum)zzgd.zza(parcel0, zzum.CREATOR)));
            parcel1.writeNoException();
            return true;
        }
        return false;
    }

    public static zzxd zzi(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IOnPaidEventListener");
        return iInterface0 instanceof zzxd ? ((zzxd)iInterface0) : new zzxf(iBinder0);
    }
}

