package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.measurement.internal.zzgq;

final class zzaj extends zza {
    private final zzgq zzc;
    private final zzx zzd;

    zzaj(zzx zzx0, zzgq zzgq0) {
        this.zzd = zzx0;
        this.zzc = zzgq0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        zzc zzx$zzc0 = new zzc(this.zzc);
        this.zzd.zzr.setEventInterceptor(zzx$zzc0);
    }
}

