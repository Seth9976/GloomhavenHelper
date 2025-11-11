package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzdjy extends zzgc implements zzdjz {
    zzdjy(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.gass.internal.IGassService");
    }

    @Override  // com.google.android.gms.internal.ads.zzdjz
    public final zzdjx zza(zzdjv zzdjv0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzdjv0);
        Parcel parcel1 = this.transactAndReadException(1, parcel0);
        zzdjx zzdjx0 = (zzdjx)zzgd.zza(parcel1, zzdjx.CREATOR);
        parcel1.recycle();
        return zzdjx0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdjz
    public final zzdke zza(zzdkc zzdkc0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzdkc0);
        Parcel parcel1 = this.transactAndReadException(3, parcel0);
        zzdke zzdke0 = (zzdke)zzgd.zza(parcel1, zzdke.CREATOR);
        parcel1.recycle();
        return zzdke0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdjz
    public final void zza(zzdjq zzdjq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzdjq0);
        this.zza(2, parcel0);
    }
}

