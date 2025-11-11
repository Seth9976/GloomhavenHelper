package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class zzcsk {
    @Nullable
    @GuardedBy("this")
    private zzxe zzaca;
    @GuardedBy("this")
    private boolean zzadk;
    private final String zzbri;
    private final zzcso zzggj;

    public zzcsk(zzcso zzcso0, String s) {
        this.zzadk = false;
        this.zzggj = zzcso0;
        this.zzbri = s;
    }

    public final String getMediationAdapterClassName() {
        synchronized(this) {
            if(this.zzaca != null) {
                try {
                    return this.zzaca.getMediationAdapterClassName();
                }
                catch(RemoteException remoteException0) {
                    zzawf.zze("#007 Could not call remote method.", remoteException0);
                    return null;
                }
            }
            return null;
        }
    }

    public final boolean isLoading() throws RemoteException {
        synchronized(this) {
            return this.zzggj.isLoading();
        }
    }

    static boolean zza(zzcsk zzcsk0, boolean z) {
        zzcsk0.zzadk = false;
        return false;
    }

    public final void zza(zzuh zzuh0, int v) throws RemoteException {
        synchronized(this) {
            this.zzaca = null;
            zzcst zzcst0 = new zzcst(v);
            zzcsn zzcsn0 = new zzcsn(this);
            this.zzadk = this.zzggj.zza(zzuh0, this.zzbri, zzcst0, zzcsn0);
        }
    }

    public final String zzkf() {
        synchronized(this) {
            if(this.zzaca != null) {
                try {
                    return this.zzaca.getMediationAdapterClassName();
                }
                catch(RemoteException remoteException0) {
                    zzawf.zze("#007 Could not call remote method.", remoteException0);
                    return null;
                }
            }
            return null;
        }
    }
}

