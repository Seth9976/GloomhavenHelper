package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzasv extends zzgc implements zzast {
    zzasv(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final Bundle getAdMetadata() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(9, this.obtainAndWriteInterfaceToken());
        Bundle bundle0 = (Bundle)zzgd.zza(parcel0, Bundle.CREATOR);
        parcel0.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final String getMediationAdapterClassName() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final boolean isLoaded() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(IObjectWrapper iObjectWrapper0, boolean z) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.writeBoolean(parcel0, z);
        this.zza(10, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzasy zzasy0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzasy0);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzatg zzatg0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzatg0);
        this.zza(6, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzato zzato0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzato0);
        this.zza(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzuh zzuh0, zzatb zzatb0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzuh0);
        zzgd.zza(parcel0, zzatb0);
        this.zza(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzwy zzwy0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzwy0);
        this.zza(8, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzxd zzxd0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzxd0);
        this.zza(13, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zzh(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final zzxe zzkg() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(12, this.obtainAndWriteInterfaceToken());
        zzxe zzxe0 = zzxh.zzj(parcel0.readStrongBinder());
        parcel0.recycle();
        return zzxe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final zzass zzqc() throws RemoteException {
        zzass zzass0;
        Parcel parcel0 = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzass0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardItem");
            zzass0 = iInterface0 instanceof zzass ? ((zzass)iInterface0) : new zzasu(iBinder0);
        }
        parcel0.recycle();
        return zzass0;
    }
}

