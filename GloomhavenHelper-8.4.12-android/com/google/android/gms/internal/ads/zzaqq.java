package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaqq extends zzgc implements zzaqo {
    zzaqq(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final zzaqk zza(zzaqi zzaqi0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaqi0);
        Parcel parcel1 = this.transactAndReadException(1, parcel0);
        zzaqk zzaqk0 = (zzaqk)zzgd.zza(parcel1, zzaqk.CREATOR);
        parcel1.recycle();
        return zzaqk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final void zza(zzaqi zzaqi0, zzaqp zzaqp0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaqi0);
        zzgd.zza(parcel0, zzaqp0);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final void zza(zzaqx zzaqx0, zzaqr zzaqr0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaqx0);
        zzgd.zza(parcel0, zzaqr0);
        this.zza(4, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final void zza(String s, zzaqr zzaqr0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzaqr0);
        this.zza(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final void zzb(zzaqx zzaqx0, zzaqr zzaqr0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaqx0);
        zzgd.zza(parcel0, zzaqr0);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final void zzc(zzaqx zzaqx0, zzaqr zzaqr0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaqx0);
        zzgd.zza(parcel0, zzaqr0);
        this.zza(6, parcel0);
    }
}

