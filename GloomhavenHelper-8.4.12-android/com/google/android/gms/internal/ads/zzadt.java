package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzadt extends zzgc implements zzadr {
    zzadt(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.IOnAppInstallAdLoadedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzadr
    public final void zza(zzadf zzadf0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzadf0);
        this.zza(1, parcel0);
    }
}

