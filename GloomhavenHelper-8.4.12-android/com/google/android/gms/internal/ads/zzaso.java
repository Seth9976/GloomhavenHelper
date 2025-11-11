package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzaso extends zzgc implements zzasm {
    zzaso(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zza(IObjectWrapper iObjectWrapper0, zzasq zzasq0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        zzgd.zza(parcel0, zzasq0);
        this.zza(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzaf(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzag(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzah(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(4, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzai(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzaj(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(6, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzak(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(8, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzal(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(10, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzam(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(11, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzb(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, bundle0);
        this.zza(12, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzd(IObjectWrapper iObjectWrapper0, int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeInt(v);
        this.zza(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zze(IObjectWrapper iObjectWrapper0, int v) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        parcel0.writeInt(v);
        this.zza(9, parcel0);
    }
}

