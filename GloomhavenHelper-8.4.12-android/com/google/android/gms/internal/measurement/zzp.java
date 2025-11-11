package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzp extends zza implements zzn {
    zzp(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    @Override  // com.google.android.gms.internal.measurement.zzn
    public final void zza(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, bundle0);
        this.zzb(1, parcel0);
    }
}

