package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzalt extends zzge implements zzalq {
    public zzalt() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzalv zzalv0;
        switch(v) {
            case 1: {
                this.onAdClicked();
                break;
            }
            case 2: {
                this.onAdClosed();
                break;
            }
            case 3: {
                this.onAdFailedToLoad(parcel0.readInt());
                break;
            }
            case 4: {
                this.onAdLeftApplication();
                break;
            }
            case 5: {
                this.onAdOpened();
                break;
            }
            case 6: {
                this.onAdLoaded();
                break;
            }
            case 7: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzalv0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
                    zzalv0 = iInterface0 instanceof zzalv ? ((zzalv)iInterface0) : new zzalu(iBinder0);
                }
                this.zza(zzalv0);
                break;
            }
            case 8: {
                this.onAdImpression();
                break;
            }
            case 9: {
                this.onAppEvent(parcel0.readString(), parcel0.readString());
                break;
            }
            case 10: {
                this.zza(zzadm.zzr(parcel0.readStrongBinder()), parcel0.readString());
                break;
            }
            case 11: {
                this.onVideoEnd();
                break;
            }
            case 12: {
                this.zzdk(parcel0.readString());
                break;
            }
            case 13: {
                this.zzsx();
                break;
            }
            case 14: {
                this.zzb(((zzasq)zzgd.zza(parcel0, zzasq.CREATOR)));
                break;
            }
            case 15: {
                this.onVideoPause();
                break;
            }
            case 16: {
                this.zza(zzasr.zzan(parcel0.readStrongBinder()));
                break;
            }
            case 17: {
                this.zzco(parcel0.readInt());
                break;
            }
            case 18: {
                this.zzsy();
                break;
            }
            case 19: {
                this.zzb(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                break;
            }
            case 20: {
                this.onVideoPlay();
                break;
            }
            default: {
                return false;
            }
        }
        parcel1.writeNoException();
        return true;
    }

    public static zzalq zzad(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
        return iInterface0 instanceof zzalq ? ((zzalq)iInterface0) : new zzals(iBinder0);
    }
}

