package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzans extends zzgc implements zzanq {
    zzans(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final zzxj getVideoController() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        zzxj zzxj0 = zzxi.zzk(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzxj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(IObjectWrapper iObjectWrapper0, String s, Bundle bundle0, Bundle bundle1, zzuk zzuk0, zzanv zzanv0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, bundle0);
        zzgd.zza(parcel0, bundle1);
        zzgd.zza(parcel0, zzuk0);
        zzgd.zza(parcel0, zzanv0);
        this.zza(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(String s, String s1, zzuh zzuh0, IObjectWrapper iObjectWrapper0, zzane zzane0, zzalq zzalq0, zzuk zzuk0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzgd.zza(parcel0, zzuh0);
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzane0);
        zzgd.zza(parcel0, zzalq0);
        zzgd.zza(parcel0, zzuk0);
        this.zza(13, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(String s, String s1, zzuh zzuh0, IObjectWrapper iObjectWrapper0, zzanj zzanj0, zzalq zzalq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzgd.zza(parcel0, zzuh0);
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzanj0);
        zzgd.zza(parcel0, zzalq0);
        this.zza(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(String s, String s1, zzuh zzuh0, IObjectWrapper iObjectWrapper0, zzank zzank0, zzalq zzalq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzgd.zza(parcel0, zzuh0);
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzank0);
        zzgd.zza(parcel0, zzalq0);
        this.zza(18, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(String s, String s1, zzuh zzuh0, IObjectWrapper iObjectWrapper0, zzanp zzanp0, zzalq zzalq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzgd.zza(parcel0, zzuh0);
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzanp0);
        zzgd.zza(parcel0, zzalq0);
        this.zza(16, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zza(String[] arr_s, Bundle[] arr_bundle) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeStringArray(arr_s);
        parcel0.writeTypedArray(arr_bundle, 0);
        this.zza(11, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final boolean zzaa(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.transactAndReadException(17, parcel0);
        boolean z = zzgd.zza(parcel1);
        parcel1.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zzdn(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(19, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final zzaoe zzth() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        zzaoe zzaoe0 = (zzaoe)zzgd.zza(parcel0, zzaoe.CREATOR);
        parcel0.recycle();
        return zzaoe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final zzaoe zzti() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        zzaoe zzaoe0 = (zzaoe)zzgd.zza(parcel0, zzaoe.CREATOR);
        parcel0.recycle();
        return zzaoe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final void zzy(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(10, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzanq
    public final boolean zzz(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.transactAndReadException(15, parcel0);
        boolean z = zzgd.zza(parcel1);
        parcel1.recycle();
        return z;
    }
}

