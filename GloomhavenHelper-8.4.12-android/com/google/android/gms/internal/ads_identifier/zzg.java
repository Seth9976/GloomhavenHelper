package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzg extends zza implements zze {
    zzg(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    @Override  // com.google.android.gms.internal.ads_identifier.zze
    public final String getId() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads_identifier.zze
    public final boolean zzb(boolean z) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzc.zza(parcel0, true);
        Parcel parcel1 = this.transactAndReadException(2, parcel0);
        boolean z1 = zzc.zza(parcel1);
        parcel1.recycle();
        return z1;
    }

    @Override  // com.google.android.gms.internal.ads_identifier.zze
    public final boolean zzc() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(6, this.obtainAndWriteInterfaceToken());
        boolean z = zzc.zza(parcel0);
        parcel0.recycle();
        return z;
    }
}

