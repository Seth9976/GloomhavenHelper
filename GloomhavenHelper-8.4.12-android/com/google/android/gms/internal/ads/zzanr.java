package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzanr extends zzgc implements zzanp {
    zzanr(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzanp
    public final void zzdm(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanp
    public final void zztg() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }
}

