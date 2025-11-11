package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzac extends zza {
    private final String zzc;
    private final String zzd;
    private final zzk zze;
    private final zzx zzf;

    zzac(zzx zzx0, String s, String s1, zzk zzk0) {
        this.zzf = zzx0;
        this.zzc = s;
        this.zzd = s1;
        this.zze = zzk0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzf.zzr.getConditionalUserProperties(this.zzc, this.zzd, this.zze);
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    protected final void zzb() {
        this.zze.zza(null);
    }
}

