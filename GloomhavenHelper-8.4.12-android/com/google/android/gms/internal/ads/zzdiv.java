package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzdiv extends zzgc implements zzdit {
    zzdiv(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.omid.IOmid");
    }

    @Override  // com.google.android.gms.internal.ads.zzdit
    public final String getVersion() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(6, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzdit
    public final IObjectWrapper zza(String s, IObjectWrapper iObjectWrapper0, String s1, String s2, String s3, String s4) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s1);
        parcel0.writeString(s2);
        parcel0.writeString(s3);
        parcel0.writeString(s4);
        Parcel parcel1 = this.transactAndReadException(9, parcel0);
        IObjectWrapper iObjectWrapper1 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdit
    public final void zzab(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(4, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdit
    public final void zzac(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdit
    public final boolean zzau(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.transactAndReadException(2, parcel0);
        boolean z = zzgd.zza(parcel1);
        parcel1.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzdit
    public final void zzc(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, iObjectWrapper1);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdit
    public final void zzd(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, iObjectWrapper1);
        this.zza(8, parcel0);
    }
}

