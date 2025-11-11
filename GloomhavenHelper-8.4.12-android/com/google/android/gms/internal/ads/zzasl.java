package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzasl extends zzge implements zzasm {
    public zzasl() {
        super("com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                this.zzaf(Stub.asInterface(parcel0.readStrongBinder()));
                break;
            }
            case 2: {
                this.zzd(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readInt());
                break;
            }
            case 3: {
                this.zzag(Stub.asInterface(parcel0.readStrongBinder()));
                break;
            }
            case 4: {
                this.zzah(Stub.asInterface(parcel0.readStrongBinder()));
                break;
            }
            case 5: {
                this.zzai(Stub.asInterface(parcel0.readStrongBinder()));
                break;
            }
            case 6: {
                this.zzaj(Stub.asInterface(parcel0.readStrongBinder()));
                break;
            }
            case 7: {
                this.zza(Stub.asInterface(parcel0.readStrongBinder()), ((zzasq)zzgd.zza(parcel0, zzasq.CREATOR)));
                break;
            }
            case 8: {
                this.zzak(Stub.asInterface(parcel0.readStrongBinder()));
                break;
            }
            case 9: {
                this.zze(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readInt());
                break;
            }
            case 10: {
                this.zzal(Stub.asInterface(parcel0.readStrongBinder()));
                break;
            }
            case 11: {
                this.zzam(Stub.asInterface(parcel0.readStrongBinder()));
                break;
            }
            case 12: {
                this.zzb(((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
                break;
            }
            default: {
                return false;
            }
        }
        parcel1.writeNoException();
        return true;
    }

    public static zzasm zzam(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
        return iInterface0 instanceof zzasm ? ((zzasm)iInterface0) : new zzaso(iBinder0);
    }
}

