package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzahs extends zzge implements zzaht {
    public zzahs() {
        super("com.google.android.gms.ads.internal.instream.client.IInstreamAdLoadCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzahn zzahn0;
        switch(v) {
            case 1: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzahn0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.instream.client.IInstreamAd");
                    zzahn0 = iInterface0 instanceof zzahn ? ((zzahn)iInterface0) : new zzahp(iBinder0);
                }
                this.zza(zzahn0);
                break;
            }
            case 2: {
                this.onInstreamAdFailedToLoad(parcel0.readInt());
                break;
            }
            default: {
                return false;
            }
        }
        parcel1.writeNoException();
        return true;
    }

    public static zzaht zzab(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.instream.client.IInstreamAdLoadCallback");
        return iInterface0 instanceof zzaht ? ((zzaht)iInterface0) : new zzahv(iBinder0);
    }
}

