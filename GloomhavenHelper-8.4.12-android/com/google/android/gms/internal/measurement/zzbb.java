package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

final class zzbb extends zza {
    private final Bundle zzc;
    private final zzx zzd;

    zzbb(zzx zzx0, Bundle bundle0) {
        this.zzd = zzx0;
        this.zzc = bundle0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        this.zzd.zzr.setConditionalUserProperty(this.zzc, this.zza);
    }
}

