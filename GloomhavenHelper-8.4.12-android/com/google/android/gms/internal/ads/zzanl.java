package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzanl extends zzgc implements zzanj {
    zzanl(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzanj
    public final void zzdm(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanj
    public final void zztg() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }
}

