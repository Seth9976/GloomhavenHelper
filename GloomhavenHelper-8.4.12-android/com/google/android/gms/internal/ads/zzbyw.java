package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import javax.annotation.Nullable;

public final class zzbyw extends zzxi {
    private final Object lock;
    @Nullable
    private zzxj zzfoy;
    @Nullable
    private final zzamd zzfoz;

    public zzbyw(@Nullable zzxj zzxj0, @Nullable zzamd zzamd0) {
        this.lock = new Object();
        this.zzfoy = zzxj0;
        this.zzfoz = zzamd0;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getAspectRatio() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getCurrentTime() throws RemoteException {
        return this.zzfoz == null ? 0.0f : this.zzfoz.getVideoCurrentTime();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getDuration() throws RemoteException {
        return this.zzfoz == null ? 0.0f : this.zzfoz.getVideoDuration();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final int getPlaybackState() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final boolean isClickToExpandEnabled() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final boolean isCustomControlsEnabled() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final boolean isMuted() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void mute(boolean z) throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void play() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void stop() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void zza(zzxk zzxk0) throws RemoteException {
        synchronized(this.lock) {
            if(this.zzfoy != null) {
                this.zzfoy.zza(zzxk0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final zzxk zzpo() throws RemoteException {
        synchronized(this.lock) {
            if(this.zzfoy != null) {
                return this.zzfoy.zzpo();
            }
        }
        return null;
    }
}

