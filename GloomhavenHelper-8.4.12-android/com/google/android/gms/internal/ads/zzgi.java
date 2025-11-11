package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzgi extends zzgc implements zzgh {
    zzgi(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.clearcut.IClearcut");
    }

    @Override  // com.google.android.gms.internal.ads.zzgh
    public final void zza(IObjectWrapper iObjectWrapper0, String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzgh
    public final void zza(IObjectWrapper iObjectWrapper0, String s, String s1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeString(null);
        this.zza(8, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzgh
    public final void zza(int[] arr_v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeIntArray(null);
        this.zza(4, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzgh
    public final void zzc(byte[] arr_b) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeByteArray(arr_b);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzgh
    public final void zzdt() throws RemoteException {
        this.zza(3, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzgh
    public final void zzm(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(6, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzgh
    public final void zzn(int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        this.zza(7, parcel0);
    }
}

