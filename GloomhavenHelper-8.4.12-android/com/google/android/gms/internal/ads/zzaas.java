package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaas extends zzgc implements zzaaq {
    zzaas(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.customrenderedad.client.IOnCustomRenderedAdLoadedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzaaq
    public final void zza(zzaap zzaap0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaap0);
        this.zza(1, parcel0);
    }
}

