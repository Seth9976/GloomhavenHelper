package com.google.android.gms.internal.ads;

import android.os.RemoteException;

public final class zzame extends zzxi {
    private final Object lock;
    private volatile zzxk zzdek;

    public zzame() {
        this.lock = new Object();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getAspectRatio() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getCurrentTime() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getDuration() throws RemoteException {
        throw new RemoteException();
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
            this.zzdek = zzxk0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final zzxk zzpo() throws RemoteException {
        synchronized(this.lock) {
        }
        return this.zzdek;
    }
}

