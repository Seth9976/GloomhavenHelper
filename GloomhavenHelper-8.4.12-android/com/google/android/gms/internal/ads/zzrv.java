package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;

final class zzrv implements BaseOnConnectionFailedListener {
    private final zzrr zzbrq;

    zzrv(zzrr zzrr0) {
        this.zzbrq = zzrr0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult0) {
        synchronized(this.zzbrq.lock) {
            this.zzbrq.zzbrn = null;
            if(this.zzbrq.zzbrm != null) {
                zzrr.zza(this.zzbrq, null);
            }
            this.zzbrq.lock.notifyAll();
        }
    }
}

