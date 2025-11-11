package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzu extends zza implements zzs {
    zzu(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    @Override  // com.google.android.gms.internal.measurement.zzs
    public final int zza() throws RemoteException {
        Parcel parcel0 = this.zza(2, this.a_());
        int v = parcel0.readInt();
        parcel0.recycle();
        return v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzs
    public final void zza(String s, String s1, Bundle bundle0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzb.zza(parcel0, bundle0);
        parcel0.writeLong(v);
        this.zzb(1, parcel0);
    }
}

