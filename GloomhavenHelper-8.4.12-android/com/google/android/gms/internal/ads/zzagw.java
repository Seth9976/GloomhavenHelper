package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;

final class zzagw implements BaseOnConnectionFailedListener {
    private final zzazy zzbsb;

    zzagw(zzagt zzagt0, zzazy zzazy0) {
        this.zzbsb = zzazy0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult0) {
        RuntimeException runtimeException0 = new RuntimeException("Connection failed.");
        this.zzbsb.setException(runtimeException0);
    }
}

