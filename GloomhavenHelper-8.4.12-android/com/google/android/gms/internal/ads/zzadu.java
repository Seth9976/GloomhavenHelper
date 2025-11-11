package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzadu extends zzgc implements zzads {
    zzadu(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzads
    public final void zza(zzadj zzadj0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzadj0);
        this.zza(1, parcel0);
    }
}

