package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzav extends zza {
    private final zzk zzc;
    private final int zzd;
    private final zzx zze;

    zzav(zzx zzx0, zzk zzk0, int v) {
        this.zze = zzx0;
        this.zzc = zzk0;
        this.zzd = v;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zze.zzr.getTestFlag(this.zzc, this.zzd);
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    protected final void zzb() {
        this.zzc.zza(null);
    }
}

