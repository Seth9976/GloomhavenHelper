package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzwe extends zzge implements zzwf {
    public zzwe() {
        super("com.google.android.gms.ads.internal.client.IAppEventListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            this.onAppEvent(parcel0.readString(), parcel0.readString());
            parcel1.writeNoException();
            return true;
        }
        return false;
    }

    public static zzwf zze(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
        return iInterface0 instanceof zzwf ? ((zzwf)iInterface0) : new zzwh(iBinder0);
    }
}

