package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzamk extends zzalt {
    private final zzasm zzden;
    private final Adapter zzdew;

    zzamk(Adapter adapter0, zzasm zzasm0) {
        this.zzdew = adapter0;
        this.zzden = zzasm0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdClicked() throws RemoteException {
        zzasm zzasm0 = this.zzden;
        if(zzasm0 != null) {
            zzasm0.zzak(ObjectWrapper.wrap(this.zzdew));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdClosed() throws RemoteException {
        zzasm zzasm0 = this.zzden;
        if(zzasm0 != null) {
            zzasm0.zzaj(ObjectWrapper.wrap(this.zzdew));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdFailedToLoad(int v) throws RemoteException {
        zzasm zzasm0 = this.zzden;
        if(zzasm0 != null) {
            zzasm0.zze(ObjectWrapper.wrap(this.zzdew), v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdImpression() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdLeftApplication() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdLoaded() throws RemoteException {
        zzasm zzasm0 = this.zzden;
        if(zzasm0 != null) {
            zzasm0.zzag(ObjectWrapper.wrap(this.zzdew));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdOpened() throws RemoteException {
        zzasm zzasm0 = this.zzden;
        if(zzasm0 != null) {
            zzasm0.zzah(ObjectWrapper.wrap(this.zzdew));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAppEvent(String s, String s1) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoEnd() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoPause() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoPlay() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzadn zzadn0, String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzalv zzalv0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzass zzass0) throws RemoteException {
        zzasm zzasm0 = this.zzden;
        if(zzasm0 != null) {
            zzasm0.zza(ObjectWrapper.wrap(this.zzdew), new zzasq(zzass0.getType(), zzass0.getAmount()));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzb(Bundle bundle0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzb(zzasq zzasq0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzco(int v) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzdk(String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzsx() throws RemoteException {
        zzasm zzasm0 = this.zzden;
        if(zzasm0 != null) {
            zzasm0.zzai(ObjectWrapper.wrap(this.zzdew));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzsy() throws RemoteException {
        zzasm zzasm0 = this.zzden;
        if(zzasm0 != null) {
            zzasm0.zzam(ObjectWrapper.wrap(this.zzdew));
        }
    }
}

