package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzadq extends zzge implements zzadr {
    public zzadq() {
        super("com.google.android.gms.ads.internal.formats.client.IOnAppInstallAdLoadedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzadf zzadf0;
        if(v == 1) {
            IBinder iBinder0 = parcel0.readStrongBinder();
            if(iBinder0 == null) {
                zzadf0 = null;
            }
            else {
                IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                zzadf0 = iInterface0 instanceof zzadf ? ((zzadf)iInterface0) : new zzadh(iBinder0);
            }
            this.zza(zzadf0);
            parcel1.writeNoException();
            return true;
        }
        return false;
    }

    public static zzadr zzs(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnAppInstallAdLoadedListener");
        return iInterface0 instanceof zzadr ? ((zzadr)iInterface0) : new zzadt(iBinder0);
    }
}

