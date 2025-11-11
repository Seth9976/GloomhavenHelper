package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzag extends zza {
    private final zzx zzc;

    zzag(zzx zzx0) {
        this.zzc = zzx0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzc.zzr.resetAnalyticsData(this.zza);
    }
}

