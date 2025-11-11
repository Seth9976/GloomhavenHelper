package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public final class zzaet extends zzgc implements zzaer {
    zzaet(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void cancelUnconfirmedClick() throws RemoteException {
        this.zza(22, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void destroy() throws RemoteException {
        this.zza(13, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getAdvertiser() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getBody() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getCallToAction() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(6, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final Bundle getExtras() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(20, this.obtainAndWriteInterfaceToken());
        Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
        parcel0.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getHeadline() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final List getImages() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        List list0 = zzgd.zzb(parcel0);
        parcel0.recycle();
        return list0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getMediationAdapterClassName() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(12, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final List getMuteThisAdReasons() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(23, this.obtainAndWriteInterfaceToken());
        List list0 = zzgd.zzb(parcel0);
        parcel0.recycle();
        return list0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getPrice() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(10, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final double getStarRating() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(8, this.obtainAndWriteInterfaceToken());
        double f = parcel0.readDouble();
        parcel0.recycle();
        return f;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getStore() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(9, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final zzxj getVideoController() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        zzxj zzxj0 = zzxi.zzk(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzxj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final boolean isCustomClickGestureEnabled() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(30, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final boolean isCustomMuteThisAdEnabled() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(24, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void performClick(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, bundle0);
        this.zza(15, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void recordCustomClickGesture() throws RemoteException {
        this.zza(28, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final boolean recordImpression(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, bundle0);
        Parcel parcel1 = this.transactAndReadException(16, parcel0);
        boolean z = zzgd.zza(parcel1);
        parcel1.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void reportTouchEvent(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, bundle0);
        this.zza(17, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void zza(zzaem zzaem0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaem0);
        this.zza(21, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void zza(zzwq zzwq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzwq0);
        this.zza(26, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void zza(zzwu zzwu0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzwu0);
        this.zza(25, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void zza(zzxd zzxd0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzxd0);
        this.zza(0x20, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final zzxe zzkg() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(0x1F, this.obtainAndWriteInterfaceToken());
        zzxe zzxe0 = zzxh.zzj(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzxe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final IObjectWrapper zzrj() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(18, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final zzacr zzrk() throws RemoteException {
        zzacr zzacr0;
        Parcel parcel0 = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzacr0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
            zzacr0 = iInterface0 instanceof zzacr ? ((zzacr)iInterface0) : new zzact(iBinder0);
        }
        parcel0.recycle();
        return zzacr0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final zzacj zzrl() throws RemoteException {
        zzacj zzacj0;
        Parcel parcel0 = this.transactAndReadException(14, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzacj0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
            zzacj0 = iInterface0 instanceof zzacj ? ((zzacj)iInterface0) : new zzacl(iBinder0);
        }
        parcel0.recycle();
        return zzacj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final IObjectWrapper zzrm() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(19, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void zzru() throws RemoteException {
        this.zza(27, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final zzacm zzrv() throws RemoteException {
        zzacm zzacm0;
        Parcel parcel0 = this.transactAndReadException(29, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzacm0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IMediaContent");
            zzacm0 = iInterface0 instanceof zzacm ? ((zzacm)iInterface0) : new zzaco(iBinder0);
        }
        parcel0.recycle();
        return zzacm0;
    }
}

