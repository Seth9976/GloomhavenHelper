package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzam extends zza {
    private final zzk zzc;
    private final zzx zzd;

    zzam(zzx zzx0, zzk zzk0) {
        this.zzd = zzx0;
        this.zzc = zzk0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzd.zzr.getGmpAppId(this.zzc);
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    protected final void zzb() {
        this.zzc.zza(null);
    }
}

