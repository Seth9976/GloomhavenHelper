package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzahm extends zzge implements zzahn {
    public zzahm() {
        super("com.google.android.gms.ads.internal.instream.client.IInstreamAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzaho zzaho0;
        switch(v) {
            case 3: {
                zzxj zzxj0 = this.getVideoController();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxj0);
                return true;
            }
            case 4: {
                this.destroy();
                parcel1.writeNoException();
                return true;
            }
            case 5: {
                IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzaho0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.instream.client.IInstreamAdCallback");
                    zzaho0 = iInterface0 instanceof zzaho ? ((zzaho)iInterface0) : new zzahq(iBinder0);
                }
                this.zza(iObjectWrapper0, zzaho0);
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                this.zzr(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 7: {
                zzacm zzacm0 = this.zzrv();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzacm0);
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

