package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public abstract class zzaqu extends zzge implements zzaqr {
    public zzaqu() {
        super("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                this.zzb(((ParcelFileDescriptor)zzgd.zza(parcel0, ParcelFileDescriptor.CREATOR)));
                break;
            }
            case 2: {
                this.zza(((zzaxp)zzgd.zza(parcel0, zzaxp.CREATOR)));
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

