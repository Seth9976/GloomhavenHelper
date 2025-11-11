package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzwi extends zzgc implements zzwg {
    zzwi(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IClientApi");
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzacv zza(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, iObjectWrapper1);
        Parcel parcel1 = this.transactAndReadException(5, parcel0);
        zzacv zzacv0 = zzacu.zzp(parcel1.readStrongBinder());
        parcel1.recycle();
        return zzacv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzacy zza(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, iObjectWrapper1);
        zzgd.zza(parcel0, iObjectWrapper2);
        Parcel parcel1 = this.transactAndReadException(11, parcel0);
        zzacy zzacy0 = zzadb.zzq(parcel1.readStrongBinder());
        parcel1.recycle();
        return zzacy0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzary zza(IObjectWrapper iObjectWrapper0, zzalk zzalk0, int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzalk0);
        parcel0.writeInt(v);
        Parcel parcel1 = this.transactAndReadException(6, parcel0);
        zzary zzary0 = zzarx.zzak(parcel1.readStrongBinder());
        parcel1.recycle();
        return zzary0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzvq zza(IObjectWrapper iObjectWrapper0, String s, zzalk zzalk0, int v) throws RemoteException {
        zzvq zzvq0;
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzalk0);
        parcel0.writeInt(v);
        Parcel parcel1 = this.transactAndReadException(3, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        if(iBinder0 == null) {
            zzvq0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            zzvq0 = iInterface0 instanceof zzvq ? ((zzvq)iInterface0) : new zzvs(iBinder0);
        }
        parcel1.recycle();
        return zzvq0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzvx zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, String s, int v) throws RemoteException {
        zzvx zzvx0;
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuk0);
        parcel0.writeString(s);
        parcel0.writeInt(v);
        Parcel parcel1 = this.transactAndReadException(10, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        if(iBinder0 == null) {
            zzvx0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzvx0 = iInterface0 instanceof zzvx ? ((zzvx)iInterface0) : new zzvz(iBinder0);
        }
        parcel1.recycle();
        return zzvx0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzvx zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, String s, zzalk zzalk0, int v) throws RemoteException {
        zzvx zzvx0;
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuk0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzalk0);
        parcel0.writeInt(v);
        Parcel parcel1 = this.transactAndReadException(1, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        if(iBinder0 == null) {
            zzvx0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzvx0 = iInterface0 instanceof zzvx ? ((zzvx)iInterface0) : new zzvz(iBinder0);
        }
        parcel1.recycle();
        return zzvx0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzwn zza(IObjectWrapper iObjectWrapper0, int v) throws RemoteException {
        zzwn zzwn0;
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeInt(v);
        Parcel parcel1 = this.transactAndReadException(9, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        if(iBinder0 == null) {
            zzwn0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            zzwn0 = iInterface0 instanceof zzwn ? ((zzwn)iInterface0) : new zzwp(iBinder0);
        }
        parcel1.recycle();
        return zzwn0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzapb zzb(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.transactAndReadException(8, parcel0);
        zzapb zzapb0 = zzape.zzag(parcel1.readStrongBinder());
        parcel1.recycle();
        return zzapb0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzast zzb(IObjectWrapper iObjectWrapper0, String s, zzalk zzalk0, int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzalk0);
        parcel0.writeInt(v);
        Parcel parcel1 = this.transactAndReadException(12, parcel0);
        zzast zzast0 = zzasw.zzao(parcel1.readStrongBinder());
        parcel1.recycle();
        return zzast0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzvx zzb(IObjectWrapper iObjectWrapper0, zzuk zzuk0, String s, zzalk zzalk0, int v) throws RemoteException {
        zzvx zzvx0;
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuk0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzalk0);
        parcel0.writeInt(v);
        Parcel parcel1 = this.transactAndReadException(2, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        if(iBinder0 == null) {
            zzvx0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzvx0 = iInterface0 instanceof zzvx ? ((zzvx)iInterface0) : new zzvz(iBinder0);
        }
        parcel1.recycle();
        return zzvx0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzvx zzc(IObjectWrapper iObjectWrapper0, zzuk zzuk0, String s, zzalk zzalk0, int v) throws RemoteException {
        zzvx zzvx0;
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuk0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzalk0);
        parcel0.writeInt(v);
        Parcel parcel1 = this.transactAndReadException(13, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        if(iBinder0 == null) {
            zzvx0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzvx0 = iInterface0 instanceof zzvx ? ((zzvx)iInterface0) : new zzvz(iBinder0);
        }
        parcel1.recycle();
        return zzvx0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzwn zzc(IObjectWrapper iObjectWrapper0) throws RemoteException {
        zzwn zzwn0;
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.transactAndReadException(4, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        if(iBinder0 == null) {
            zzwn0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            zzwn0 = iInterface0 instanceof zzwn ? ((zzwn)iInterface0) : new zzwp(iBinder0);
        }
        parcel1.recycle();
        return zzwn0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzapq zzd(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.transactAndReadException(7, parcel0);
        zzapq zzapq0 = zzapp.zzai(parcel1.readStrongBinder());
        parcel1.recycle();
        return zzapq0;
    }
}

