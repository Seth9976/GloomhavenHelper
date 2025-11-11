package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzadv extends zzge implements zzads {
    public zzadv() {
        super("com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzadj zzadj0;
        if(v == 1) {
            IBinder iBinder0 = parcel0.readStrongBinder();
            if(iBinder0 == null) {
                zzadj0 = null;
            }
            else {
                IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                zzadj0 = iInterface0 instanceof zzadj ? ((zzadj)iInterface0) : new zzadl(iBinder0);
            }
            this.zza(zzadj0);
            parcel1.writeNoException();
            return true;
        }
        return false;
    }

    public static zzads zzt(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
        return iInterface0 instanceof zzads ? ((zzads)iInterface0) : new zzadu(iBinder0);
    }
}

