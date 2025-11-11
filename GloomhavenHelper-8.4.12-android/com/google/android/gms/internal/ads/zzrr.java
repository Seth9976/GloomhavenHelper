package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;
import jeb.synthetic.FIN;

public final class zzrr {
    private final Object lock;
    private final Runnable zzbrl;
    @Nullable
    @GuardedBy("lock")
    private zzsa zzbrm;
    @Nullable
    @GuardedBy("lock")
    private zzse zzbrn;
    @Nullable
    @GuardedBy("lock")
    private Context zzur;

    public zzrr() {
        this.zzbrl = new zzru(this);
        this.lock = new Object();
    }

    private final void connect() {
        synchronized(this.lock) {
            if(this.zzur != null && this.zzbrm == null) {
                this.zzbrm = this.zza(new zzrw(this), new zzrv(this));
                this.zzbrm.checkAvailabilityAndConnect();
            }
        }
    }

    private final void disconnect() {
        synchronized(this.lock) {
            if(this.zzbrm == null) {
                return;
            }
            if(this.zzbrm.isConnected() || this.zzbrm.isConnecting()) {
                this.zzbrm.disconnect();
            }
            this.zzbrm = null;
            this.zzbrn = null;
            Binder.flushPendingCommands();
        }
    }

    public final void initialize(Context context0) {
        if(context0 == null) {
            return;
        }
        synchronized(this.lock) {
            if(this.zzur != null) {
                return;
            }
            this.zzur = context0.getApplicationContext();
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcnw)).booleanValue()) {
                this.connect();
            }
            else if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcnv)).booleanValue()) {
                zzq.zzky().zza(new zzrt(this));
            }
        }
    }

    @VisibleForTesting
    private final zzsa zza(BaseConnectionCallbacks baseGmsClient$BaseConnectionCallbacks0, BaseOnConnectionFailedListener baseGmsClient$BaseOnConnectionFailedListener0) {
        synchronized(this) {
            return new zzsa(this.zzur, zzq.zzlj().zzxg(), baseGmsClient$BaseConnectionCallbacks0, baseGmsClient$BaseOnConnectionFailedListener0);
        }
    }

    static zzsa zza(zzrr zzrr0, zzsa zzsa0) {
        zzrr0.zzbrm = null;
        return null;
    }

    public final zzry zza(zzrz zzrz0) {
        Object object0 = this.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(this.zzbrn == null) {
            zzry zzry0 = new zzry();
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return zzry0;
        }
        try {
            zzry zzry1 = this.zzbrn.zza(zzrz0);
            FIN.finallyExec$NT(v);
            return zzry1;
        }
        catch(RemoteException remoteException0) {
            zzawf.zzc("Unable to call into cache service.", remoteException0);
            FIN.finallyExec$NT(v);
            return new zzry();
        }
    }

    public final void zzmt() {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcnx)).booleanValue()) {
            synchronized(this.lock) {
                this.connect();
                zzawo.zzdtx.removeCallbacks(this.zzbrl);
                long v1 = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzcny)));
                zzawo.zzdtx.postDelayed(this.zzbrl, v1);
            }
        }
    }
}

