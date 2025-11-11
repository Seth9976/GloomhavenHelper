package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public final class zzaqt extends zzgc implements zzaqr {
    zzaqt(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzaqr
    public final void zza(zzaxp zzaxp0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaxp0);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaqr
    public final void zzb(ParcelFileDescriptor parcelFileDescriptor0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, parcelFileDescriptor0);
        this.zza(1, parcel0);
    }
}

