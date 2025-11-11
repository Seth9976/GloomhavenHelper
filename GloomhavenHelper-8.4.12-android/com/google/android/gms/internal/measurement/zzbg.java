package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzbg extends zza {
    private final Activity zzc;
    private final zzd zzd;

    zzbg(zzd zzx$zzd0, Activity activity0) {
        this.zzd = zzx$zzd0;
        this.zzc = activity0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzc);
        zzx.this.zzr.onActivityPaused(iObjectWrapper0, this.zzb);
    }
}

