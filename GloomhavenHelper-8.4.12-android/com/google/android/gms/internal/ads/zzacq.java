package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzacq extends zzge implements zzacr {
    public zzacq() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                IObjectWrapper iObjectWrapper0 = this.zzrg();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper0);
                return true;
            }
            case 2: {
                Uri uri0 = this.getUri();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, uri0);
                return true;
            }
            case 3: {
                double f = this.getScale();
                parcel1.writeNoException();
                parcel1.writeDouble(f);
                return true;
            }
            case 4: {
                int v2 = this.getWidth();
                parcel1.writeNoException();
                parcel1.writeInt(v2);
                return true;
            }
            case 5: {
                int v3 = this.getHeight();
                parcel1.writeNoException();
                parcel1.writeInt(v3);
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzacr zzo(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        return iInterface0 instanceof zzacr ? ((zzacr)iInterface0) : new zzact(iBinder0);
    }
}

