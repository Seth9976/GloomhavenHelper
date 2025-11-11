package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzagq extends zzgc implements zzagr {
    zzagq(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.httpcache.IHttpAssetsCacheService");
    }

    @Override  // com.google.android.gms.internal.ads.zzagr
    public final void zza(zzagl zzagl0, zzagp zzagp0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzagl0);
        zzgd.zza(parcel0, zzagp0);
        this.zzb(2, parcel0);
    }
}

