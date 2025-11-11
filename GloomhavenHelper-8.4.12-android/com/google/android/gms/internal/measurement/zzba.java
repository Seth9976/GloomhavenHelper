package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

final class zzba extends zza {
    private final Long zzc;
    private final String zzd;
    private final String zze;
    private final Bundle zzf;
    private final boolean zzg;
    private final boolean zzh;
    private final zzx zzi;

    zzba(zzx zzx0, Long long0, String s, String s1, Bundle bundle0, boolean z, boolean z1) {
        this.zzi = zzx0;
        this.zzc = long0;
        this.zzd = s;
        this.zze = s1;
        this.zzf = bundle0;
        this.zzg = z;
        this.zzh = z1;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzi.zzr.logEvent(this.zzd, this.zze, this.zzf, this.zzg, this.zzh, (this.zzc == null ? this.zza : ((long)this.zzc)));
    }
}

