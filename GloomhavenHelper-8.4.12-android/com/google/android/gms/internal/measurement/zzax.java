package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.measurement.internal.zzgp;

final class zzax extends zza {
    private final zzgp zzc;
    private final zzx zzd;

    zzax(zzx zzx0, zzgp zzgp0) {
        this.zzd = zzx0;
        this.zzc = zzgp0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        Pair pair0;
        for(int v = 0; true; ++v) {
            pair0 = null;
            if(v >= this.zzd.zzf.size()) {
                break;
            }
            Object object0 = ((Pair)this.zzd.zzf.get(v)).first;
            if(this.zzc.equals(object0)) {
                pair0 = (Pair)this.zzd.zzf.get(v);
                break;
            }
        }
        if(pair0 == null) {
            Log.w(this.zzd.zzc, "OnEventListener had not been registered.");
            return;
        }
        this.zzd.zzr.unregisterOnMeasurementEventListener(((zzs)pair0.second));
        this.zzd.zzf.remove(pair0);
    }
}

