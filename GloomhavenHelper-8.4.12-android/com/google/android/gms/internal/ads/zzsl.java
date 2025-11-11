package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;

final class zzsl implements BaseOnConnectionFailedListener {
    private final zzazy zzbsb;
    private final zzsf zzbsc;

    zzsl(zzsf zzsf0, zzazy zzazy0) {
        this.zzbsc = zzsf0;
        this.zzbsb = zzazy0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult0) {
        synchronized(this.zzbsc.lock) {
            RuntimeException runtimeException0 = new RuntimeException("Connection failed.");
            this.zzbsb.setException(runtimeException0);
        }
    }
}

