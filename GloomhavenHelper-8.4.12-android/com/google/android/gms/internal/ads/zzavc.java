package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzavc extends zzgc implements zzava {
    zzavc(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.signals.ISignalCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzava
    public final void onError(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzava
    public final void zza(String s, String s1, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzgd.zza(parcel0, bundle0);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzava
    public final void zzk(String s, String s1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        this.zza(1, parcel0);
    }
}

