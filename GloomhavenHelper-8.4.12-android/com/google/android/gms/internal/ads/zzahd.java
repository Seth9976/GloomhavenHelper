package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzahd extends zzgc implements zzahb {
    zzahd(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.initialization.IAdapterInitializationCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzahb
    public final void onInitializationFailed(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzahb
    public final void onInitializationSucceeded() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }
}

