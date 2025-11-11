package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzadz extends zzgc implements zzadx {
    zzadz(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzadx
    public final void zza(zzadn zzadn0, String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzadn0);
        parcel0.writeString(s);
        this.zza(1, parcel0);
    }
}

