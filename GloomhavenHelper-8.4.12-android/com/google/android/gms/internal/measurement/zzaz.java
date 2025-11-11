package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzaz extends zza {
    private final String zzc;
    private final String zzd;
    private final Object zze;
    private final boolean zzf;
    private final zzx zzg;

    zzaz(zzx zzx0, String s, String s1, Object object0, boolean z) {
        this.zzg = zzx0;
        this.zzc = s;
        this.zzd = s1;
        this.zze = object0;
        this.zzf = z;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zze);
        this.zzg.zzr.setUserProperty(this.zzc, this.zzd, iObjectWrapper0, this.zzf, this.zza);
    }
}

