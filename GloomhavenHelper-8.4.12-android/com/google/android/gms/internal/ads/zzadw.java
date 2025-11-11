package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzadw extends zzge implements zzadx {
    public zzadw() {
        super("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzadn zzadn0;
        if(v == 1) {
            IBinder iBinder0 = parcel0.readStrongBinder();
            if(iBinder0 == null) {
                zzadn0 = null;
            }
            else {
                IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                zzadn0 = iInterface0 instanceof zzadn ? ((zzadn)iInterface0) : new zzadp(iBinder0);
            }
            this.zza(zzadn0, parcel0.readString());
            parcel1.writeNoException();
            return true;
        }
        return false;
    }

    public static zzadx zzu(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        return iInterface0 instanceof zzadx ? ((zzadx)iInterface0) : new zzadz(iBinder0);
    }
}

