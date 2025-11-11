package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzrk extends zzge implements zzrh {
    public zzrk() {
        super("com.google.android.gms.ads.internal.appopen.client.IAppOpenAdLoadCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzrg zzrg0;
        switch(v) {
            case 1: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzrg0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.appopen.client.IAppOpenAd");
                    zzrg0 = iInterface0 instanceof zzrg ? ((zzrg)iInterface0) : new zzri(iBinder0);
                }
                this.zza(zzrg0);
                break;
            }
            case 2: {
                this.onAppOpenAdFailedToLoad(parcel0.readInt());
                break;
            }
            default: {
                return false;
            }
        }
        parcel1.writeNoException();
        return true;
    }

    public static zzrh zzb(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.appopen.client.IAppOpenAdLoadCallback");
        return iInterface0 instanceof zzrh ? ((zzrh)iInterface0) : new zzrj(iBinder0);
    }
}

