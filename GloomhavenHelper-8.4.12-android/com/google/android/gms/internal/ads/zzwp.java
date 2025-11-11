package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public final class zzwp extends zzgc implements zzwn {
    zzwp(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final String getVersionString() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(9, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void initialize() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void setAppMuted(boolean z) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.writeBoolean(parcel0, z);
        this.zza(4, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void setAppVolume(float f) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeFloat(f);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(zzahc zzahc0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzahc0);
        this.zza(12, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(zzalk zzalk0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzalk0);
        this.zza(11, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(zzyw zzyw0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzyw0);
        this.zza(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(String s, IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(6, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zzb(IObjectWrapper iObjectWrapper0, String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zzce(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zzcf(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(10, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final float zzpj() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        float f = parcel0.readFloat();
        parcel0.recycle();
        return f;
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final boolean zzpk() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(8, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final List zzpl() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(13, this.obtainAndWriteInterfaceToken());
        List list0 = parcel0.createTypedArrayList(zzagz.CREATOR);
        parcel0.recycle();
        return list0;
    }
}

