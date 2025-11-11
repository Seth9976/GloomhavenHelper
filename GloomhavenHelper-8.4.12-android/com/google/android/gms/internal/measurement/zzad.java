package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzad extends zza {
    private final boolean zzc;
    private final zzx zzd;

    zzad(zzx zzx0, boolean z) {
        this.zzd = zzx0;
        this.zzc = z;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzd.zzr.setMeasurementEnabled(this.zzc, this.zza);
    }
}

