package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzacx extends zzgc implements zzacv {
    zzacx(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void destroy() throws RemoteException {
        this.zza(4, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zza(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zza(zzacm zzacm0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzacm0);
        this.zza(8, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zzb(String s, IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zzc(IObjectWrapper iObjectWrapper0, int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeInt(v);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final IObjectWrapper zzcp(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        Parcel parcel1 = this.transactAndReadException(2, parcel0);
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zze(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(6, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zzf(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zzg(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(9, parcel0);
    }
}

