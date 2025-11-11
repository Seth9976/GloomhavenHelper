package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzai extends zza {
    private final long zzc;
    private final zzx zzd;

    zzai(zzx zzx0, long v) {
        this.zzd = zzx0;
        this.zzc = v;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzd.zzr.setSessionTimeoutDuration(this.zzc);
    }
}

