package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzauz extends zzge implements zzava {
    public zzauz() {
        super("com.google.android.gms.ads.internal.signals.ISignalCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                this.zzk(parcel0.readString(), parcel0.readString());
                break;
            }
            case 2: {
                this.onError(parcel0.readString());
                break;
            }
            case 3: {
                this.zza(parcel0.readString(), parcel0.readString(), ((Bundle)zzgd.zza(parcel0, Bundle.CREATOR)));
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

