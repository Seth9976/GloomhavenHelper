package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzrf extends zzge implements zzrg {
    public zzrf() {
        super("com.google.android.gms.ads.internal.appopen.client.IAppOpenAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzrm zzrm0;
        switch(v) {
            case 2: {
                zzvx zzvx0 = this.zzdr();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzvx0);
                return true;
            }
            case 3: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzrm0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.appopen.client.IAppOpenAdPresentationCallback");
                    zzrm0 = iInterface0 instanceof zzrm ? ((zzrm)iInterface0) : new zzro(iBinder0);
                }
                this.zza(zzrm0);
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

