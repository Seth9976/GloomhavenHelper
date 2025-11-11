package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

final class zzar extends zza {
    private final Bundle zzc;
    private final zzk zzd;
    private final zzx zze;

    zzar(zzx zzx0, Bundle bundle0, zzk zzk0) {
        this.zze = zzx0;
        this.zzc = bundle0;
        this.zzd = zzk0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zze.zzr.performAction(this.zzc, this.zzd, this.zza);
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    protected final void zzb() {
        this.zzd.zza(null);
    }
}

