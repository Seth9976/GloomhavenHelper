package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzvi extends zzge implements zzvj {
    public zzvi() {
        super("com.google.android.gms.ads.internal.client.IAdClickListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            this.onAdClicked();
            parcel1.writeNoException();
            return true;
        }
        return false;
    }
}

