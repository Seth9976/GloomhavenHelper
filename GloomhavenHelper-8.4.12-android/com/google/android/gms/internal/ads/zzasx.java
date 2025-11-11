package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzasx extends zzge implements zzasy {
    public zzasx() {
        super("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzass zzass0;
        switch(v) {
            case 1: {
                this.onRewardedAdOpened();
                break;
            }
            case 2: {
                this.onRewardedAdClosed();
                break;
            }
            case 3: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzass0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardItem");
                    zzass0 = iInterface0 instanceof zzass ? ((zzass)iInterface0) : new zzasu(iBinder0);
                }
                this.zza(zzass0);
                break;
            }
            case 4: {
                this.onRewardedAdFailedToShow(parcel0.readInt());
                break;
            }
            default: {
                return false;
            }
        }
        parcel1.writeNoException();
        return true;
    }
}

