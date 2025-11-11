package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzahv extends zzgc implements zzaht {
    zzahv(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.instream.client.IInstreamAdLoadCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzaht
    public final void onInstreamAdFailedToLoad(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaht
    public final void zza(zzahn zzahn0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzahn0);
        this.zza(1, parcel0);
    }
}

