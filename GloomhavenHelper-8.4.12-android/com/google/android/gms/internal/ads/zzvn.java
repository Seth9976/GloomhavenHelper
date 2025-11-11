package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzvn extends zzge implements zzvk {
    public zzvn() {
        super("com.google.android.gms.ads.internal.client.IAdListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                this.onAdClosed();
                break;
            }
            case 2: {
                this.onAdFailedToLoad(parcel0.readInt());
                break;
            }
            case 3: {
                this.onAdLeftApplication();
                break;
            }
            case 4: {
                this.onAdLoaded();
                break;
            }
            case 5: {
                this.onAdOpened();
                break;
            }
            case 6: {
                this.onAdClicked();
                break;
            }
            case 7: {
                this.onAdImpression();
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

