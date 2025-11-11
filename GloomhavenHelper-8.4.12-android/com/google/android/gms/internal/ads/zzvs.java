package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

public final class zzvs extends zzgc implements zzvq {
    zzvs(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(PublisherAdViewOptions publisherAdViewOptions0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, publisherAdViewOptions0);
        this.zza(9, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzach zzach0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzach0);
        this.zza(6, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzadr zzadr0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzadr0);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzads zzads0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzads0);
        this.zza(4, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzaef zzaef0, zzuk zzuk0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaef0);
        zzgd.zza(parcel0, zzuk0);
        this.zza(8, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzaeg zzaeg0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaeg0);
        this.zza(10, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzahl zzahl0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzahl0);
        this.zza(13, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzaht zzaht0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzaht0);
        this.zza(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(String s, zzady zzady0, zzadx zzadx0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeString(s);
        zzgd.zza(parcel0, zzady0);
        zzgd.zza(parcel0, zzadx0);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zzb(zzvk zzvk0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzvk0);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zzb(zzwl zzwl0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzwl0);
        this.zza(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final zzvp zzpi() throws RemoteException {
        zzvp zzvp0;
        Parcel parcel0 = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzvp0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoader");
            zzvp0 = iInterface0 instanceof zzvp ? ((zzvp)iInterface0) : new zzvr(iBinder0);
        }
        parcel0.recycle();
        return zzvp0;
    }
}

