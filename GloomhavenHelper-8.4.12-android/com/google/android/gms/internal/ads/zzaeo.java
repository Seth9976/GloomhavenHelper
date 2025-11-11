package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaeo extends zzgc implements zzaem {
    zzaeo(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzaem
    public final void onUnconfirmedClickCancelled() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzaem
    public final void onUnconfirmedClickReceived(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(1, parcel0);
    }
}

