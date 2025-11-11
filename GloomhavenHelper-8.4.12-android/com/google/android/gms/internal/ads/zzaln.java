package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzaln extends zzge implements zzalk {
    public zzaln() {
        super("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                zzalp zzalp0 = this.zzdf(parcel0.readString());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzalp0);
                return true;
            }
            case 2: {
                boolean z = this.zzdg(parcel0.readString());
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 3: {
                zzanq zzanq0 = this.zzdh(parcel0.readString());
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzanq0);
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzalk zzac(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        return iInterface0 instanceof zzalk ? ((zzalk)iInterface0) : new zzalm(iBinder0);
    }
}

