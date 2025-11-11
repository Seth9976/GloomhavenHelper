package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import javax.annotation.concurrent.GuardedBy;

public final class zzcni extends zzalt implements zzbrk {
    @GuardedBy("this")
    private zzalq zzdfy;
    @GuardedBy("this")
    private zzbrj zzgcb;

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdClicked() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onAdClicked();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdClosed() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onAdClosed();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdFailedToLoad(int v) throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onAdFailedToLoad(v);
            }
            if(this.zzgcb != null) {
                this.zzgcb.onAdFailedToLoad(v);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdImpression() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onAdImpression();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdLeftApplication() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onAdLeftApplication();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdLoaded() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onAdLoaded();
            }
            if(this.zzgcb != null) {
                this.zzgcb.onAdLoaded();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdOpened() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onAdOpened();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAppEvent(String s, String s1) throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onAppEvent(s, s1);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoEnd() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onVideoEnd();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoPause() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onVideoPause();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoPlay() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.onVideoPlay();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzadn zzadn0, String s) throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.zza(zzadn0, s);
            }
        }
    }

    public final void zza(zzalq zzalq0) {
        synchronized(this) {
            this.zzdfy = zzalq0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzalv zzalv0) throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.zza(zzalv0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzass zzass0) throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.zza(zzass0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbrk
    public final void zza(zzbrj zzbrj0) {
        synchronized(this) {
            this.zzgcb = zzbrj0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzb(Bundle bundle0) throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.zzb(bundle0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzb(zzasq zzasq0) throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.zzb(zzasq0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzco(int v) throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.zzco(v);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzdk(String s) throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.zzdk(s);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzsx() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.zzsx();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzsy() throws RemoteException {
        synchronized(this) {
            if(this.zzdfy != null) {
                this.zzdfy.zzsy();
            }
        }
    }
}

