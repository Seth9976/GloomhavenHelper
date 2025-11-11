package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public abstract class zzago extends zzge implements zzagp {
    public zzago() {
        super("com.google.android.gms.ads.internal.httpcache.IHttpAssetsCacheCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            this.zza(((ParcelFileDescriptor)zzgd.zza(parcel0, ParcelFileDescriptor.CREATOR)));
            return true;
        }
        return false;
    }
}

