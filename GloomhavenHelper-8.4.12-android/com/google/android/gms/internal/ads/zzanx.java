package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzanx extends zzgc implements zzanv {
    zzanx(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzanv
    public final void onFailure(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanv
    public final void zzdo(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(1, parcel0);
    }
}

