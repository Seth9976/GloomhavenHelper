package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzau extends zza {
    private final String zzc;
    private final zzk zzd;
    private final zzx zze;

    zzau(zzx zzx0, String s, zzk zzk0) {
        this.zze = zzx0;
        this.zzc = s;
        this.zzd = zzk0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zze.zzr.getMaxUserProperties(this.zzc, this.zzd);
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    protected final void zzb() {
        this.zzd.zza(null);
    }
}

