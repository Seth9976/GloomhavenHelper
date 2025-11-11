package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;

final class zzgj implements zzgg {
    @Override  // com.google.android.gms.internal.measurement.zzgg
    public final int zza(int v, Object object0, Object object1) {
        if(((zzgh)object0).isEmpty()) {
            return 0;
        }
        Iterator iterator0 = ((zzgh)object0).entrySet().iterator();
        if(!iterator0.hasNext()) {
            return 0;
        }
        Object object2 = iterator0.next();
        ((Map.Entry)object2).getKey();
        ((Map.Entry)object2).getValue();
        throw new NoSuchMethodError();
    }

    @Override  // com.google.android.gms.internal.measurement.zzgg
    public final Object zza(Object object0, Object object1) {
        zzgh zzgh0 = (zzgh)object0;
        if(!((zzgh)object1).isEmpty()) {
            if(!zzgh0.zzd()) {
                zzgh0 = zzgh0.zzb();
            }
            zzgh0.zza(((zzgh)object1));
        }
        return zzgh0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgg
    public final Map zza(Object object0) {
        return (zzgh)object0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgg
    public final Map zzb(Object object0) {
        return (zzgh)object0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgg
    public final boolean zzc(Object object0) {
        return !((zzgh)object0).zzd();
    }

    @Override  // com.google.android.gms.internal.measurement.zzgg
    public final Object zzd(Object object0) {
        ((zzgh)object0).zzc();
        return object0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgg
    public final Object zze(Object object0) {
        return zzgh.zza().zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzgg
    public final zzge zzf(Object object0) {
        throw new NoSuchMethodError();
    }
}

