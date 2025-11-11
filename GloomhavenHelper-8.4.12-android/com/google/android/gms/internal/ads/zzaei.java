package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaei extends zzgc implements zzaeg {
    zzaei(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.IOnUnifiedNativeAdLoadedListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzaeg
    public final void zza(zzaer zzaer0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaer0);
        this.zza(1, parcel0);
    }
}

