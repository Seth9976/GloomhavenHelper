package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzapd extends zzgc implements zzapb {
    zzapd(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onActivityResult(int v, int v1, Intent intent0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        parcel0.writeInt(v);
        parcel0.writeInt(v1);
        zzgd.zza(parcel0, intent0);
        this.zza(12, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onBackPressed() throws RemoteException {
        this.zza(10, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onCreate(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, bundle0);
        this.zza(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onDestroy() throws RemoteException {
        this.zza(8, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onPause() throws RemoteException {
        this.zza(5, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onRestart() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onResume() throws RemoteException {
        this.zza(4, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onSaveInstanceState(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, bundle0);
        Parcel parcel1 = this.transactAndReadException(6, parcel0);
        if(parcel1.readInt() != 0) {
            bundle0.readFromParcel(parcel1);
        }
        parcel1.recycle();
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onStart() throws RemoteException {
        this.zza(3, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onStop() throws RemoteException {
        this.zza(7, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void zzad(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, iObjectWrapper0);
        this.zza(13, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void zzdk() throws RemoteException {
        this.zza(9, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final boolean zztr() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }
}

