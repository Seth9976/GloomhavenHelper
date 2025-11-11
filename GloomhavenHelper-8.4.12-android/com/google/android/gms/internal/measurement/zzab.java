package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzab extends zza {
    private final String zzc;
    private final zzx zzd;

    zzab(zzx zzx0, String s) {
        this.zzd = zzx0;
        this.zzc = s;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzd.zzr.setUserId(this.zzc, this.zza);
    }
}

