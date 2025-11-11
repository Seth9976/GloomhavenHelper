package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzasr extends zzge implements zzass {
    public zzasr() {
        super("com.google.android.gms.ads.internal.rewarded.client.IRewardItem");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                String s = this.getType();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 2: {
                int v2 = this.getAmount();
                parcel1.writeNoException();
                parcel1.writeInt(v2);
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzass zzan(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardItem");
        return iInterface0 instanceof zzass ? ((zzass)iInterface0) : new zzasu(iBinder0);
    }
}

