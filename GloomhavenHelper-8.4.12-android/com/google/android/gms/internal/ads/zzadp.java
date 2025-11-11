package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public final class zzadp extends zzgc implements zzadn {
    zzadp(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final void destroy() throws RemoteException {
        this.zza(8, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final List getAvailableAssetNames() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        List list0 = parcel0.createStringArrayList();
        parcel0.recycle();
        return list0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final String getCustomTemplateId() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final zzxj getVideoController() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        zzxj zzxj0 = zzxi.zzk(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzxj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final void performClick(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final void recordImpression() throws RemoteException {
        this.zza(6, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final String zzcu(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        Parcel parcel1 = this.transactAndReadException(1, parcel0);
        String s1 = parcel1.readString();
        parcel1.recycle();
        return s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final zzacr zzcv(String s) throws RemoteException {
        zzacr zzacr0;
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        Parcel parcel1 = this.transactAndReadException(2, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        if(iBinder0 == null) {
            zzacr0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
            zzacr0 = iInterface0 instanceof zzacr ? ((zzacr)iInterface0) : new zzact(iBinder0);
        }
        parcel1.recycle();
        return zzacr0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final boolean zzp(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.transactAndReadException(10, parcel0);
        boolean z = zzgd.zza(parcel1);
        parcel1.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final void zzq(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final IObjectWrapper zzrj() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final IObjectWrapper zzro() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(9, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final boolean zzrp() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(12, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final boolean zzrq() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(13, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final void zzrr() throws RemoteException {
        this.zza(15, this.obtainAndWriteInterfaceToken());
    }
}

