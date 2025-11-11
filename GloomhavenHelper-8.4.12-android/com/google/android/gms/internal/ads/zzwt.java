package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzwt extends zzge implements zzwq {
    public zzwt() {
        super("com.google.android.gms.ads.internal.client.IMuteThisAdListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            this.onAdMuted();
            parcel1.writeNoException();
            return true;
        }
        return false;
    }

    public static zzwq zzf(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IMuteThisAdListener");
        return iInterface0 instanceof zzwq ? ((zzwq)iInterface0) : new zzws(iBinder0);
    }
}

