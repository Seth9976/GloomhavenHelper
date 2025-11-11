package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzatf extends zzgc implements zzatg {
    zzatf(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.rewarded.client.IRewardedAdSkuListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzatg
    public final void zza(zzass zzass0, String s, String s1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzass0);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        this.zza(2, parcel0);
    }
}

