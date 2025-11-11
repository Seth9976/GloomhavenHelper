package com.google.android.gms.internal.ads;

import android.os.Binder;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;

public abstract class zzcji implements BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    protected final Object mLock;
    protected final zzazy zzdcg;
    protected boolean zzfyh;
    protected boolean zzfyi;
    protected zzaqx zzfyj;
    @GuardedBy("mLock")
    @VisibleForTesting(otherwise = 3)
    protected zzaqf zzfyk;

    public zzcji() {
        this.zzdcg = new zzazy();
        this.mLock = new Object();
        this.zzfyh = false;
        this.zzfyi = false;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult0) {
        zzawf.zzeb("Disconnected from remote ad request service.");
        zzcjv zzcjv0 = new zzcjv(0);
        this.zzdcg.setException(zzcjv0);
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public void onConnectionSuspended(int v) {
        zzawf.zzeb("Cannot connect to remote service, fallback to local instance.");
    }

    protected final void zzans() {
        synchronized(this.mLock) {
            this.zzfyi = true;
            if(this.zzfyk.isConnected() || this.zzfyk.isConnecting()) {
                this.zzfyk.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }
}

