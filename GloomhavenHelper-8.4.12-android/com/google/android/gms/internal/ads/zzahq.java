package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzahq extends zzgc implements zzaho {
    zzahq(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.instream.client.IInstreamAdCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzaho
    public final void zzcn(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaho
    public final void zzrz() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }
}

