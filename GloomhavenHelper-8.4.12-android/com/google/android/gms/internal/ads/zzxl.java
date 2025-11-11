package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzxl extends zzgc implements zzxj {
    zzxl(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.internal.client.IVideoController");
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getAspectRatio() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(9, this.obtainAndWriteInterfaceToken());
        float f = parcel0.readFloat();
        parcel0.recycle();
        return f;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getCurrentTime() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        float f = parcel0.readFloat();
        parcel0.recycle();
        return f;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getDuration() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(6, this.obtainAndWriteInterfaceToken());
        float f = parcel0.readFloat();
        parcel0.recycle();
        return f;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final int getPlaybackState() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        int v = parcel0.readInt();
        parcel0.recycle();
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final boolean isClickToExpandEnabled() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(12, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final boolean isCustomControlsEnabled() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(10, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final boolean isMuted() throws RemoteException {
        Parcel parcel0 = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        boolean z = zzgd.zza(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void mute(boolean z) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.writeBoolean(parcel0, z);
        this.zza(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void pause() throws RemoteException {
        this.zza(2, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void play() throws RemoteException {
        this.zza(1, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void stop() throws RemoteException {
        this.zza(13, this.obtainAndWriteInterfaceToken());
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void zza(zzxk zzxk0) throws RemoteException {
        Parcel parcel0 = this.obtainAndWriteInterfaceToken();
        zzgd.zza(parcel0, zzxk0);
        this.zza(8, parcel0);
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final zzxk zzpo() throws RemoteException {
        zzxk zzxk0;
        Parcel parcel0 = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        IBinder iBinder0 = parcel0.readStrongBinder();
        if(iBinder0 == null) {
            zzxk0 = null;
        }
        else {
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
            zzxk0 = iInterface0 instanceof zzxk ? ((zzxk)iInterface0) : new zzxm(iBinder0);
        }
        parcel0.recycle();
        return zzxk0;
    }
}

