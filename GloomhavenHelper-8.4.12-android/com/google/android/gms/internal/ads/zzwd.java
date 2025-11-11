package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzwd extends zzge implements zzwa {
    public zzwd() {
        super("com.google.android.gms.ads.internal.client.IAdMetadataListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            this.onAdMetadataChanged();
            parcel1.writeNoException();
            return true;
        }
        return false;
    }

    public static zzwa zzd(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdMetadataListener");
        return iInterface0 instanceof zzwa ? ((zzwa)iInterface0) : new zzwc(iBinder0);
    }
}

