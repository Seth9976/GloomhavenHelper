package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzaeh extends zzgc implements zzaef {
    zzaeh(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.IOnPublisherAdViewLoadedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzaef
    public final void zza(zzvx zzvx0, IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzvx0);
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(1, parcel0);
    }
}

