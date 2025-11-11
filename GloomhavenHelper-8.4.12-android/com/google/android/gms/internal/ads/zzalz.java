package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public final class zzalz extends zzgc implements zzalx {
    zzalz(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final String getBody() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final String getCallToAction() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(6, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final Bundle getExtras() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(15, this.obtainAndWriteInterfaceToken());
        Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
        parcel0.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final String getHeadline() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final List getImages() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        List list0 = zzgd.zzb(parcel0);
        parcel0.recycle();
        return list0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final boolean getOverrideClickHandling() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(14, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final boolean getOverrideImpressionRecording() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(13, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final String getPrice() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(9, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final double getStarRating() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        double f = parcel0.readDouble();
        parcel0.recycle();
        return f;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final String getStore() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(8, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final zzxj getVideoController() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(17, this.obtainAndWriteInterfaceToken());
        zzxj zzxj0 = zzxi.zzk(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzxj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final void recordImpression() throws RemoteException {
        this.zza(10, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final void zzc(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, iObjectWrapper1);
        zzgd.zza(parcel0, iObjectWrapper2);
        this.zza(22, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final zzacr zzrk() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        zzacr zzacr0 = zzacq.zzo(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzacr0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final zzacj zzrl() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(19, this.obtainAndWriteInterfaceToken());
        zzacj zzacj0 = zzaci.zzm(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzacj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final IObjectWrapper zzrm() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(21, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final IObjectWrapper zzsz() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(18, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final IObjectWrapper zzta() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(20, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final void zzu(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(11, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final void zzv(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(12, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final void zzw(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(16, parcel0);
    }
}

