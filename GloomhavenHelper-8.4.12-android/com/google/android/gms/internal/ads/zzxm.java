package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzxm extends zzgc implements zzxk {
    zzxm(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
    }

    @Override  // com.google.android.gms.internal.ads.zzxk
    public final void onVideoEnd() throws RemoteException {
        this.zza(4, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzxk
    public final void onVideoMute(boolean z) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.writeBoolean(parcel0, z);
        this.zza(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzxk
    public final void onVideoPause() throws RemoteException {
        this.zza(3, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzxk
    public final void onVideoPlay() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzxk
    public final void onVideoStart() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }
}

