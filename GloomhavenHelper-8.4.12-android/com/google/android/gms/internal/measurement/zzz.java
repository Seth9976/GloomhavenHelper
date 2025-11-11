package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

final class zzz extends zza {
    private final String zzc;
    private final String zzd;
    private final Bundle zze;
    private final zzx zzf;

    zzz(zzx zzx0, String s, String s1, Bundle bundle0) {
        this.zzf = zzx0;
        this.zzc = s;
        this.zzd = s1;
        this.zze = bundle0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzf.zzr.clearConditionalUserProperty(this.zzc, this.zzd, this.zze);
    }
}

