package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzanm extends zzgc implements zzank {
    zzanm(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzank
    public final void zza(zzamd zzamd0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzamd0);
        this.zza(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzank
    public final void zzdm(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(2, parcel0);
    }
}

