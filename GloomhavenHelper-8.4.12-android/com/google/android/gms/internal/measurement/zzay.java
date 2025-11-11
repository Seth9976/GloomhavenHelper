package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzay extends zza {
    private final boolean zzc;
    private final zzx zzd;

    zzay(zzx zzx0, boolean z) {
        this.zzd = zzx0;
        this.zzc = z;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzd.zzr.setDataCollectionEnabled(this.zzc);
    }
}

