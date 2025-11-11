package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public final class zzadh extends zzgc implements zzadf {
    zzadh(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final void destroy() throws RemoteException {
        this.zza(12, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getBody() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getCallToAction() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final Bundle getExtras() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
        parcel0.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getHeadline() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final List getImages() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        List list0 = zzgd.zzb(parcel0);
        parcel0.recycle();
        return list0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getMediationAdapterClassName() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(19, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getPrice() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(10, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final double getStarRating() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(8, this.obtainAndWriteInterfaceToken());
        double f = parcel0.readDouble();
        parcel0.recycle();
        return f;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getStore() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(9, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final zzxj getVideoController() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(13, this.obtainAndWriteInterfaceToken());
        zzxj zzxj0 = zzxi.zzk(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzxj0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final void performClick(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, bundle0);
        this.zza(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final boolean recordImpression(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, bundle0);
        Parcel parcel1 = this.transactAndReadException(15, parcel0);
        boolean z = zzgd.zza(parcel1);
        parcel1.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final void reportTouchEvent(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, bundle0);
        this.zza(16, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final IObjectWrapper zzrj() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final zzacr zzrk() throws RemoteException {
        zzacr zzacr0;
        Parcel parcel0 = this.transactAndReadException(6, this.obtainAndWriteInterfaceToken());
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

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final zzacj zzrl() throws RemoteException {
        zzacj zzacj0;
        Parcel parcel0 = this.transactAndReadException(17, this.obtainAndWriteInterfaceToken());
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

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final IObjectWrapper zzrm() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(18, this.obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }
}

