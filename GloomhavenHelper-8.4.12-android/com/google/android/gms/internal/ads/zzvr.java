package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzvr extends zzgc implements zzvp {
    zzvr(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IAdLoader");
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final String getMediationAdapterClassName() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final boolean isLoading() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final void zza(zzuh zzuh0, int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzuh0);
        parcel0.writeInt(v);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final void zzb(zzuh zzuh0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzuh0);
        this.zza(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final String zzkf() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }
}

