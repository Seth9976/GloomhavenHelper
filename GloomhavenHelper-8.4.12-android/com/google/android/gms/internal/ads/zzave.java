package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzave extends zzge implements zzavb {
    public zzave() {
        super("com.google.android.gms.ads.internal.signals.ISignalGenerator");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzava zzava0;
        switch(v) {
            case 1: {
                IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
                zzavh zzavh0 = (zzavh)zzgd.zza(parcel0, zzavh.CREATOR);
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzava0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.signals.ISignalCallback");
                    zzava0 = iInterface0 instanceof zzava ? ((zzava)iInterface0) : new zzavc(iBinder0);
                }
                this.zza(iObjectWrapper0, zzavh0, zzava0);
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                this.zzan(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 3: {
                IObjectWrapper iObjectWrapper1 = this.zzb(Stub.asInterface(parcel0.readStrongBinder()), Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper1);
                return true;
            }
            case 4: {
                IObjectWrapper iObjectWrapper2 = this.zzao(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper2);
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

