package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;

final class zzrw implements BaseConnectionCallbacks {
    private final zzrr zzbrq;

    zzrw(zzrr zzrr0) {
        this.zzbrq = zzrr0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle0) {
        synchronized(this.zzbrq.lock) {
            if(this.zzbrq.zzbrm != null) {
                try {
                    zzse zzse0 = this.zzbrq.zzbrm.zzmx();
                    this.zzbrq.zzbrn = zzse0;
                }
                catch(DeadObjectException deadObjectException0) {
                    zzawf.zzc("Unable to obtain a cache service instance.", deadObjectException0);
                    this.zzbrq.disconnect();
                }
            }
            this.zzbrq.lock.notifyAll();
        }
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnectionSuspended(int v) {
        synchronized(this.zzbrq.lock) {
            this.zzbrq.zzbrn = null;
            this.zzbrq.lock.notifyAll();
        }
    }
}

