package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzarx extends zzge implements zzary {
    public zzarx() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v != 34) {
            zzasb zzasb0 = null;
            switch(v) {
                case 1: {
                    this.zza(((zzash)zzgd.zza(parcel0, zzash.CREATOR)));
                    parcel1.writeNoException();
                    return true;
                }
                case 2: {
                    this.show();
                    parcel1.writeNoException();
                    return true;
                }
                case 3: {
                    IBinder iBinder0 = parcel0.readStrongBinder();
                    if(iBinder0 != null) {
                        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                        zzasb0 = iInterface0 instanceof zzasb ? ((zzasb)iInterface0) : new zzasd(iBinder0);
                    }
                    this.zza(zzasb0);
                    parcel1.writeNoException();
                    return true;
                }
                case 5: {
                    boolean z = this.isLoaded();
                    parcel1.writeNoException();
                    zzgd.writeBoolean(parcel1, z);
                    return true;
                }
                case 6: {
                    this.pause();
                    parcel1.writeNoException();
                    return true;
                }
                case 7: {
                    this.resume();
                    parcel1.writeNoException();
                    return true;
                }
                case 8: {
                    this.destroy();
                    parcel1.writeNoException();
                    return true;
                }
                case 9: {
                    this.zzj(Stub.asInterface(parcel0.readStrongBinder()));
                    parcel1.writeNoException();
                    return true;
                }
                case 10: {
                    this.zzk(Stub.asInterface(parcel0.readStrongBinder()));
                    parcel1.writeNoException();
                    return true;
                }
                case 11: {
                    this.zzl(Stub.asInterface(parcel0.readStrongBinder()));
                    parcel1.writeNoException();
                    return true;
                }
                case 12: {
                    String s = this.getMediationAdapterClassName();
                    parcel1.writeNoException();
                    parcel1.writeString(s);
                    return true;
                }
                case 13: {
                    this.setUserId(parcel0.readString());
                    parcel1.writeNoException();
                    return true;
                }
                case 14: {
                    this.zza(zzwd.zzd(parcel0.readStrongBinder()));
                    parcel1.writeNoException();
                    return true;
                }
                case 15: {
                    Bundle bundle0 = this.getAdMetadata();
                    parcel1.writeNoException();
                    zzgd.zzb(parcel1, bundle0);
                    return true;
                }
                case 16: {
                    IBinder iBinder1 = parcel0.readStrongBinder();
                    if(iBinder1 != null) {
                        IInterface iInterface1 = iBinder1.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedAdSkuListener");
                        zzasb0 = iInterface1 instanceof zzarw ? ((zzarw)iInterface1) : new zzarv(iBinder1);
                    }
                    this.zza(((zzarw)zzasb0));
                    parcel1.writeNoException();
                    return true;
                }
                case 17: {
                    this.setAppPackageName(parcel0.readString());
                    parcel1.writeNoException();
                    return true;
                }
                case 18: {
                    this.zzi(Stub.asInterface(parcel0.readStrongBinder()));
                    parcel1.writeNoException();
                    return true;
                }
                case 19: {
                    this.setCustomData(parcel0.readString());
                    parcel1.writeNoException();
                    return true;
                }
                case 20: {
                    boolean z1 = this.zzqd();
                    parcel1.writeNoException();
                    zzgd.writeBoolean(parcel1, z1);
                    return true;
                }
                case 21: {
                    zzxe zzxe0 = this.zzkg();
                    parcel1.writeNoException();
                    zzgd.zza(parcel1, zzxe0);
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        this.setImmersiveMode(zzgd.zza(parcel0));
        parcel1.writeNoException();
        return true;
    }

    public static zzary zzak(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        return iInterface0 instanceof zzary ? ((zzary)iInterface0) : new zzasa(iBinder0);
    }
}

