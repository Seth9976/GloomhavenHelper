package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzalm extends zzgc implements zzalk {
    zzalm(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }

    @Override  // com.google.android.gms.internal.ads.zzalk
    public final zzalp zzdf(String s) throws RemoteException {
        zzalp zzalp0;
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        Parcel parcel1 = this.transactAndReadException(1, parcel0);
        IBinder iBinder0 = parcel1.readStrongBinder();
        if(iBinder0 == null) {
            zzalp0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            zzalp0 = iInterface0 instanceof zzalp ? ((zzalp)iInterface0) : new zzalr(iBinder0);
        }
        parcel1.recycle();
        return zzalp0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalk
    public final boolean zzdg(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        Parcel parcel1 = this.transactAndReadException(2, parcel0);
        boolean z = zzgd.zza(parcel1);
        parcel1.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzalk
    public final zzanq zzdh(String s) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        Parcel parcel1 = this.transactAndReadException(3, parcel0);
        zzanq zzanq0 = zzant.zzaf(parcel1.readStrongBinder());
        parcel1.recycle();
        return zzanq0;
    }
}

