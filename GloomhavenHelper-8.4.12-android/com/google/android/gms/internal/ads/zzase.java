package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzase extends zzge implements zzasb {
    public zzase() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzarr zzarr0;
        switch(v) {
            case 1: {
                this.onRewardedVideoAdLoaded();
                break;
            }
            case 2: {
                this.onRewardedVideoAdOpened();
                break;
            }
            case 3: {
                this.onRewardedVideoStarted();
                break;
            }
            case 4: {
                this.onRewardedVideoAdClosed();
                break;
            }
            case 5: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzarr0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardItem");
                    zzarr0 = iInterface0 instanceof zzarr ? ((zzarr)iInterface0) : new zzart(iBinder0);
                }
                this.zza(zzarr0);
                break;
            }
            case 6: {
                this.onRewardedVideoAdLeftApplication();
                break;
            }
            case 7: {
                this.onRewardedVideoAdFailedToLoad(parcel0.readInt());
                break;
            }
            case 8: {
                this.onRewardedVideoCompleted();
                break;
            }
            default: {
                return false;
            }
        }
        parcel1.writeNoException();
        return true;
    }

    public static zzasb zzal(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        return iInterface0 instanceof zzasb ? ((zzasb)iInterface0) : new zzasd(iBinder0);
    }
}

