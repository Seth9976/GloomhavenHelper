package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzasw extends zzge implements zzast {
    public zzasw() {
        super("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzatb zzatb0 = null;
        switch(v) {
            case 1: {
                zzuh zzuh0 = (zzuh)zzgd.zza(parcel0, zzuh.CREATOR);
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 != null) {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdLoadCallback");
                    zzatb0 = iInterface0 instanceof zzatb ? ((zzatb)iInterface0) : new zzatd(iBinder0);
                }
                this.zza(zzuh0, zzatb0);
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                IBinder iBinder1 = parcel0.readStrongBinder();
                if(iBinder1 != null) {
                    IInterface iInterface1 = iBinder1.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCallback");
                    zzatb0 = iInterface1 instanceof zzasy ? ((zzasy)iInterface1) : new zzata(iBinder1);
                }
                this.zza(((zzasy)zzatb0));
                parcel1.writeNoException();
                return true;
            }
            case 3: {
                boolean z = this.isLoaded();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 4: {
                String s = this.getMediationAdapterClassName();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 5: {
                this.zzh(Stub.asInterface(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                IBinder iBinder2 = parcel0.readStrongBinder();
                if(iBinder2 != null) {
                    IInterface iInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdSkuListener");
                    zzatb0 = iInterface2 instanceof zzatg ? ((zzatg)iInterface2) : new zzatf(iBinder2);
                }
                this.zza(((zzatg)zzatb0));
                parcel1.writeNoException();
                return true;
            }
            case 7: {
                this.zza(((zzato)zzgd.zza(parcel0, zzato.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 8: {
                this.zza(zzxb.zzh(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 9: {
                Bundle bundle0 = this.getAdMetadata();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, bundle0);
                return true;
            }
            case 10: {
                this.zza(Stub.asInterface(parcel0.readStrongBinder()), zzgd.zza(parcel0));
                parcel1.writeNoException();
                return true;
            }
            case 11: {
                zzass zzass0 = this.zzqc();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzass0);
                return true;
            }
            case 12: {
                zzxe zzxe0 = this.zzkg();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxe0);
                return true;
            }
            case 13: {
                this.zza(zzxc.zzi(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzast zzao(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
        return iInterface0 instanceof zzast ? ((zzast)iInterface0) : new zzasv(iBinder0);
    }
}

