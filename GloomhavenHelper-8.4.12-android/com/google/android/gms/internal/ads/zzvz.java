package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzvz extends zzgc implements zzvx {
    zzvz(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IAdManager");
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void destroy() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final Bundle getAdMetadata() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(37, this.obtainAndWriteInterfaceToken());
        Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
        parcel0.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String getAdUnitId() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(0x1F, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String getMediationAdapterClassName() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(18, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzxj getVideoController() throws RemoteException {
        zzxj zzxj0;
        Parcel parcel0 = this.transactAndReadException(26, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzxj0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
            zzxj0 = iInterface0 instanceof zzxj ? ((zzxj)iInterface0) : new zzxl(iBinder0);
        }
        parcel0.recycle();
        return zzxj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean isLoading() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(23, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean isReady() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void pause() throws RemoteException {
        this.zza(5, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void resume() throws RemoteException {
        this.zza(6, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setImmersiveMode(boolean z) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.writeBoolean(parcel0, z);
        this.zza(34, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.writeBoolean(parcel0, z);
        this.zza(22, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void setUserId(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(25, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void showInterstitial() throws RemoteException {
        this.zza(9, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void stopLoading() throws RemoteException {
        this.zza(10, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzaaq zzaaq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaaq0);
        this.zza(19, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzapl zzapl0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzapl0);
        this.zza(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzapr zzapr0, String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzapr0);
        parcel0.writeString(s);
        this.zza(15, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzasb zzasb0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzasb0);
        this.zza(24, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzrh zzrh0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzrh0);
        this.zza(40, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzuk zzuk0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzuk0);
        this.zza(13, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzur zzur0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzur0);
        this.zza(39, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvj zzvj0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzvj0);
        this.zza(20, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzvk zzvk0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzvk0);
        this.zza(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwa zzwa0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzwa0);
        this.zza(36, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwf zzwf0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzwf0);
        this.zza(8, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzwl zzwl0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzwl0);
        this.zza(21, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzxd zzxd0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzxd0);
        this.zza(42, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzxp zzxp0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzxp0);
        this.zza(30, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zza(zzzc zzzc0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzzc0);
        this.zza(29, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final boolean zza(zzuh zzuh0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzuh0);
        Parcel parcel1 = this.transactAndReadException(4, parcel0);
        boolean z = zzgd.zza(parcel1);
        parcel1.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzbs(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        this.zza(38, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final IObjectWrapper zzkc() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final void zzkd() throws RemoteException {
        this.zza(11, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzuk zzke() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(12, this.obtainAndWriteInterfaceToken());
        zzuk zzuk0 = (zzuk)zzgd.zza(parcel0, zzuk.CREATOR);
        parcel0.recycle();
        return zzuk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final String zzkf() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(35, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzxe zzkg() throws RemoteException {
        zzxe zzxe0;
        Parcel parcel0 = this.transactAndReadException(41, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzxe0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IResponseInfo");
            zzxe0 = iInterface0 instanceof zzxe ? ((zzxe)iInterface0) : new zzxg(iBinder0);
        }
        parcel0.recycle();
        return zzxe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzwf zzkh() throws RemoteException {
        zzwf zzwf0;
        Parcel parcel0 = this.transactAndReadException(0x20, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzwf0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
            zzwf0 = iInterface0 instanceof zzwf ? ((zzwf)iInterface0) : new zzwh(iBinder0);
        }
        parcel0.recycle();
        return zzwf0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvx
    public final zzvk zzki() throws RemoteException {
        zzvk zzvk0;
        Parcel parcel0 = this.transactAndReadException(33, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzvk0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
            zzvk0 = iInterface0 instanceof zzvk ? ((zzvk)iInterface0) : new zzvm(iBinder0);
        }
        parcel0.recycle();
        return zzvk0;
    }
}

