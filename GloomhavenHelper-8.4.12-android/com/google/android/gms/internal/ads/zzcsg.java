package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import javax.annotation.concurrent.GuardedBy;

public final class zzcsg implements zzbqh, zzbqm, zzbqu, zzbrn, zztz {
    @GuardedBy("this")
    private zzvk zzggd;

    @Override  // com.google.android.gms.internal.ads.zztz
    public final void onAdClicked() {
        synchronized(this) {
            if(this.zzggd != null) {
                try {
                    this.zzggd.onAdClicked();
                }
                catch(RemoteException remoteException0) {
                    zzawf.zzd("Remote Exception at onAdClicked.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdClosed() {
        synchronized(this) {
            if(this.zzggd != null) {
                try {
                    this.zzggd.onAdClosed();
                }
                catch(RemoteException remoteException0) {
                    zzawf.zzd("Remote Exception at onAdClosed.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqm
    public final void onAdFailedToLoad(int v) {
        synchronized(this) {
            if(this.zzggd != null) {
                try {
                    this.zzggd.onAdFailedToLoad(v);
                }
                catch(RemoteException remoteException0) {
                    zzawf.zzd("Remote Exception at onAdFailedToLoad.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        synchronized(this) {
            if(this.zzggd != null) {
                try {
                    this.zzggd.onAdImpression();
                }
                catch(RemoteException remoteException0) {
                    zzawf.zzd("Remote Exception at onAdImpression.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdLeftApplication() {
        synchronized(this) {
            if(this.zzggd != null) {
                try {
                    this.zzggd.onAdLeftApplication();
                }
                catch(RemoteException remoteException0) {
                    zzawf.zzd("Remote Exception at onAdLeftApplication.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        synchronized(this) {
            if(this.zzggd != null) {
                try {
                    this.zzggd.onAdLoaded();
                }
                catch(RemoteException remoteException0) {
                    zzawf.zzd("Remote Exception at onAdLoaded.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdOpened() {
        synchronized(this) {
            if(this.zzggd != null) {
                try {
                    this.zzggd.onAdOpened();
                }
                catch(RemoteException remoteException0) {
                    zzawf.zzd("Remote Exception at onAdOpened.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoCompleted() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoStarted() {
    }

    public final zzvk zzaon() {
        synchronized(this) {
        }
        return this.zzggd;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void zzb(zzarr zzarr0, String s, String s1) {
    }

    public final void zzc(zzvk zzvk0) {
        synchronized(this) {
            this.zzggd = zzvk0;
        }
    }
}

