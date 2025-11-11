package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzxh extends zzge implements zzxe {
    public zzxh() {
        super("com.google.android.gms.ads.internal.client.IResponseInfo");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                String s = this.getMediationAdapterClassName();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 2: {
                String s1 = this.getResponseId();
                parcel1.writeNoException();
                parcel1.writeString(s1);
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzxe zzj(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IResponseInfo");
        return iInterface0 instanceof zzxe ? ((zzxe)iInterface0) : new zzxg(iBinder0);
    }
}

