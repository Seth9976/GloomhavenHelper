package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.measurement.internal.zzgp;

final class zzaw extends zza {
    private final zzgp zzc;
    private final zzx zzd;

    zzaw(zzx zzx0, zzgp zzgp0) {
        this.zzd = zzx0;
        this.zzc = zzgp0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    final void zza() throws RemoteException {
        for(int v = 0; v < this.zzd.zzf.size(); ++v) {
            Object object0 = ((Pair)this.zzd.zzf.get(v)).first;
            if(this.zzc.equals(object0)) {
                Log.w(this.zzd.zzc, "OnEventListener already registered.");
                return;
            }
        }
        zzb zzx$zzb0 = new zzb(this.zzc);
        this.zzd.zzf.add(new Pair(this.zzc, zzx$zzb0));
        this.zzd.zzr.registerOnMeasurementEventListener(zzx$zzb0);
    }
}

