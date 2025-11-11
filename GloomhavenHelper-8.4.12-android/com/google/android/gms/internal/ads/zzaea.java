package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaea extends zzgc implements zzady {
    zzaea(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.IOnCustomTemplateAdLoadedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzady
    public final void zzb(zzadn zzadn0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzadn0);
        this.zza(1, parcel0);
    }
}

