package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzak extends zza {
    private final String zzc;
    private final zzx zzd;

    zzak(zzx zzx0, String s) {
        this.zzd = zzx0;
        this.zzc = s;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzd.zzr.endAdUnitExposure(this.zzc, this.zzb);
    }
}

