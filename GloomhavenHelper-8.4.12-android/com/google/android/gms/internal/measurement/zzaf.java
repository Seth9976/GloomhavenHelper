package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzaf extends zza {
    private final long zzc;
    private final zzx zzd;

    zzaf(zzx zzx0, long v) {
        this.zzd = zzx0;
        this.zzc = v;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzd.zzr.setMinimumSessionDuration(this.zzc);
    }
}

