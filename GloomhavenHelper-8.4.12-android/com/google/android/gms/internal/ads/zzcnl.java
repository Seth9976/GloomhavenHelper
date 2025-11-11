package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

public final class zzcnl extends zzasl implements zzbrk {
    @GuardedBy("this")
    private zzbrj zzgcb;
    @GuardedBy("this")
    private zzasm zzgce;
    @GuardedBy("this")
    private zzbvi zzgcf;

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zza(IObjectWrapper iObjectWrapper0, zzasq zzasq0) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zza(iObjectWrapper0, zzasq0);
            }
        }
    }

    public final void zza(zzasm zzasm0) {
        synchronized(this) {
            this.zzgce = zzasm0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbrk
    public final void zza(zzbrj zzbrj0) {
        synchronized(this) {
            this.zzgcb = zzbrj0;
        }
    }

    public final void zza(zzbvi zzbvi0) {
        synchronized(this) {
            this.zzgcf = zzbvi0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzaf(IObjectWrapper iObjectWrapper0) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zzaf(iObjectWrapper0);
            }
            if(this.zzgcf != null) {
                this.zzgcf.onInitializationSucceeded();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzag(IObjectWrapper iObjectWrapper0) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zzag(iObjectWrapper0);
            }
            if(this.zzgcb != null) {
                this.zzgcb.onAdLoaded();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzah(IObjectWrapper iObjectWrapper0) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zzah(iObjectWrapper0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzai(IObjectWrapper iObjectWrapper0) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zzai(iObjectWrapper0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzaj(IObjectWrapper iObjectWrapper0) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zzaj(iObjectWrapper0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzak(IObjectWrapper iObjectWrapper0) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zzak(iObjectWrapper0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzal(IObjectWrapper iObjectWrapper0) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zzal(iObjectWrapper0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzam(IObjectWrapper iObjectWrapper0) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zzam(iObjectWrapper0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzb(Bundle bundle0) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zzb(bundle0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzd(IObjectWrapper iObjectWrapper0, int v) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zzd(iObjectWrapper0, v);
            }
            if(this.zzgcf != null) {
                this.zzgcf.zzdh(v);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zze(IObjectWrapper iObjectWrapper0, int v) throws RemoteException {
        synchronized(this) {
            if(this.zzgce != null) {
                this.zzgce.zze(iObjectWrapper0, v);
            }
            if(this.zzgcb != null) {
                this.zzgcb.onAdFailedToLoad(v);
            }
        }
    }
}

