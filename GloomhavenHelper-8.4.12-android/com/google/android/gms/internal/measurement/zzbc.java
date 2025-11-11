package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzbc extends zza {
    private final Activity zzc;
    private final Bundle zzd;
    private final zzd zze;

    zzbc(zzd zzx$zzd0, Activity activity0, Bundle bundle0) {
        this.zze = zzx$zzd0;
        this.zzc = activity0;
        this.zzd = bundle0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzc);
        zzx.this.zzr.onActivityCreated(iObjectWrapper0, this.zzd, this.zzb);
    }
}

