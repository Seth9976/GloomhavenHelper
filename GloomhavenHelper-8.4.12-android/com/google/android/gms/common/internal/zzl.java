package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzl extends zza implements IGmsCallbacks {
    zzl(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override  // com.google.android.gms.common.internal.IGmsCallbacks
    public final void onPostInitComplete(int v, IBinder iBinder0, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeInt(v);
        parcel0.writeStrongBinder(iBinder0);
        zzc.zza(parcel0, bundle0);
        this.zzb(1, parcel0);
    }

    @Override  // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zza(int v, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeInt(v);
        zzc.zza(parcel0, bundle0);
        this.zzb(2, parcel0);
    }

    @Override  // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zza(int v, IBinder iBinder0, zzb zzb0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeInt(v);
        parcel0.writeStrongBinder(iBinder0);
        zzc.zza(parcel0, zzb0);
        this.zzb(3, parcel0);
    }
}

