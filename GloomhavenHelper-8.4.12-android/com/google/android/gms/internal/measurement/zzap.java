package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzap extends zza {
    private final String zzc;
    private final String zzd;
    private final boolean zze;
    private final zzk zzf;
    private final zzx zzg;

    zzap(zzx zzx0, String s, String s1, boolean z, zzk zzk0) {
        this.zzg = zzx0;
        this.zzc = s;
        this.zzd = s1;
        this.zze = z;
        this.zzf = zzk0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzg.zzr.getUserProperties(this.zzc, this.zzd, this.zze, this.zzf);
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    protected final void zzb() {
        this.zzf.zza(null);
    }
}

