package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public final class zzalr extends zzgc implements zzalp {
    zzalr(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void destroy() throws RemoteException {
        this.zza(5, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final Bundle getInterstitialAdapterInfo() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(18, this.obtainAndWriteInterfaceToken());
        Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
        parcel0.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzxj getVideoController() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(26, this.obtainAndWriteInterfaceToken());
        zzxj zzxj0 = zzxi.zzk(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzxj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final boolean isInitialized() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(13, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void pause() throws RemoteException {
        this.zza(8, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void resume() throws RemoteException {
        this.zza(9, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void setImmersiveMode(boolean z) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.writeBoolean(parcel0, z);
        this.zza(25, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void showInterstitial() throws RemoteException {
        this.zza(4, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void showVideo() throws RemoteException {
        this.zza(12, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzahb zzahb0, List list0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzahb0);
        parcel0.writeTypedList(list0);
        this.zza(0x1F, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzasm zzasm0, List list0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzasm0);
        parcel0.writeStringList(list0);
        this.zza(23, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, zzalq zzalq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuh0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzalq0);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, zzasm zzasm0, String s1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuh0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzasm0);
        parcel0.writeString(s1);
        this.zza(10, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, String s1, zzalq zzalq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuh0);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzgd.zza(parcel0, zzalq0);
        this.zza(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, String s1, zzalq zzalq0, zzach zzach0, List list0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuh0);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzgd.zza(parcel0, zzalq0);
        zzgd.zza(parcel0, zzach0);
        parcel0.writeStringList(list0);
        this.zza(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, zzuh zzuh0, String s, zzalq zzalq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuk0);
        zzgd.zza(parcel0, zzuh0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzalq0);
        this.zza(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, zzuh zzuh0, String s, String s1, zzalq zzalq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuk0);
        zzgd.zza(parcel0, zzuh0);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzgd.zza(parcel0, zzalq0);
        this.zza(6, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(zzuh zzuh0, String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzuh0);
        parcel0.writeString(s);
        this.zza(11, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(zzuh zzuh0, String s, String s1) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzuh0);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        this.zza(20, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zzb(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, zzalq zzalq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzuh0);
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzalq0);
        this.zza(28, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zzs(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(21, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final IObjectWrapper zzsp() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzalx zzsq() throws RemoteException {
        zzalx zzalx0;
        Parcel parcel0 = this.transactAndReadException(15, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzalx0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
            zzalx0 = iInterface0 instanceof zzalx ? ((zzalx)iInterface0) : new zzalz(iBinder0);
        }
        parcel0.recycle();
        return zzalx0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzaly zzsr() throws RemoteException {
        zzaly zzaly0;
        Parcel parcel0 = this.transactAndReadException(16, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzaly0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
            zzaly0 = iInterface0 instanceof zzaly ? ((zzaly)iInterface0) : new zzama(iBinder0);
        }
        parcel0.recycle();
        return zzaly0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final Bundle zzss() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(17, this.obtainAndWriteInterfaceToken());
        Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
        parcel0.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final Bundle zzst() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(19, this.obtainAndWriteInterfaceToken());
        Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
        parcel0.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final boolean zzsu() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(22, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzadn zzsv() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(24, this.obtainAndWriteInterfaceToken());
        zzadn zzadn0 = zzadm.zzr(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzadn0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzamd zzsw() throws RemoteException {
        zzamd zzamd0;
        Parcel parcel0 = this.transactAndReadException(27, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzamd0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
            zzamd0 = iInterface0 instanceof zzamd ? ((zzamd)iInterface0) : new zzamf(iBinder0);
        }
        parcel0.recycle();
        return zzamd0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zzt(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(30, parcel0);
    }
}

