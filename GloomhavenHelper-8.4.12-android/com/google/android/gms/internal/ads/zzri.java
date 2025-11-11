package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzri extends zzgc implements zzrg {
    zzri(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.appopen.client.IAppOpenAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzrg
    public final void zza(zzrm zzrm0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzrm0);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzrg
    public final zzvx zzdr() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        zzvx zzvx0 = zzvw.zzc(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzvx0;
    }
}

