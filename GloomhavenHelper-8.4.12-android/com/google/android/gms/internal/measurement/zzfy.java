package com.google.android.gms.internal.measurement;

import java.util.List;

final class zzfy extends zzfx {
    private zzfy() {
        super(null);
    }

    zzfy(zzfw zzfw0) {
    }

    @Override  // com.google.android.gms.internal.measurement.zzfx
    final List zza(Object object0, long v) {
        List list0 = zzfy.zzc(object0, v);
        if(!((zzfk)list0).zza()) {
            int v1 = ((zzfk)list0).size();
            list0 = ((zzfk)list0).zza((v1 == 0 ? 10 : v1 << 1));
            zzia.zza(object0, v, list0);
        }
        return list0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzfx
    final void zza(Object object0, Object object1, long v) {
        zzfk zzfk0 = zzfy.zzc(object0, v);
        zzfk zzfk1 = zzfy.zzc(object1, v);
        int v1 = zzfk0.size();
        int v2 = zzfk1.size();
        if(v1 > 0 && v2 > 0) {
            if(!zzfk0.zza()) {
                zzfk0 = zzfk0.zza(v2 + v1);
            }
            zzfk0.addAll(zzfk1);
        }
        if(v1 > 0) {
            zzfk1 = zzfk0;
        }
        zzia.zza(object0, v, zzfk1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzfx
    final void zzb(Object object0, long v) {
        zzfy.zzc(object0, v).j_();
    }

    private static zzfk zzc(Object object0, long v) {
        return (zzfk)zzia.zzf(object0, v);
    }
}

