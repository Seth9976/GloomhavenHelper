package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzwh extends zzgc implements zzwf {
    zzwh(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IAppEventListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzwf
    public final void onAppEvent(String s, String s1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        this.zza(1, parcel0);
    }
}

