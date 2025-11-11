package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzae extends zza {
    private final Activity zzc;
    private final String zzd;
    private final String zze;
    private final zzx zzf;

    zzae(zzx zzx0, Activity activity0, String s, String s1) {
        this.zzf = zzx0;
        this.zzc = activity0;
        this.zzd = s;
        this.zze = s1;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzc);
        this.zzf.zzr.setCurrentScreen(iObjectWrapper0, this.zzd, this.zze, this.zza);
    }
}

