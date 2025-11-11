package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzrj extends zzgc implements zzrh {
    zzrj(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.appopen.client.IAppOpenAdLoadCallback");
    }

    @Override  // com.google.android.gms.internal.ads.zzrh
    public final void onAppOpenAdFailedToLoad(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzrh
    public final void zza(zzrg zzrg0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzrg0);
        this.zza(1, parcel0);
    }
}

