package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;

final class zzagx implements BaseConnectionCallbacks {
    private final zzazy zzbsb;
    private final zzagt zzcze;

    zzagx(zzagt zzagt0, zzazy zzazy0) {
        this.zzcze = zzagt0;
        this.zzbsb = zzazy0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle0) {
        try {
            zzagr zzagr0 = this.zzcze.zzczd.zzrw();
            this.zzbsb.set(zzagr0);
        }
        catch(DeadObjectException deadObjectException0) {
            this.zzbsb.setException(deadObjectException0);
        }
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnectionSuspended(int v) {
        RuntimeException runtimeException0 = new RuntimeException("onConnectionSuspended: " + v);
        this.zzbsb.setException(runtimeException0);
    }
}

