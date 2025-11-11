package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public final class zzahe extends zzgc implements zzahc {
    zzahe(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.initialization.IInitializationCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzahc
    public final void zzd(List list0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeTypedList(list0);
        this.zza(1, parcel0);
    }
}

