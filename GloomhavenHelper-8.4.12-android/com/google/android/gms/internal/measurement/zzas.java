package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzas extends zza {
    private final int zzc;
    private final String zzd;
    private final Object zze;
    private final Object zzf;
    private final Object zzg;
    private final zzx zzh;

    zzas(zzx zzx0, boolean z, int v, String s, Object object0, Object object1, Object object2) {
        this.zzh = zzx0;
        this.zzc = 5;
        this.zzd = s;
        this.zze = object0;
        this.zzf = null;
        this.zzg = null;
        super(false);
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zze);
        IObjectWrapper iObjectWrapper1 = ObjectWrapper.wrap(this.zzf);
        IObjectWrapper iObjectWrapper2 = ObjectWrapper.wrap(this.zzg);
        this.zzh.zzr.logHealthData(this.zzc, this.zzd, iObjectWrapper0, iObjectWrapper1, iObjectWrapper2);
    }
}

