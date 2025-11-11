package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzfz extends zzfx {
    private static final Class zza;

    static {
        zzfz.zza = Collections.unmodifiableList(Collections.emptyList()).getClass();
    }

    private zzfz() {
        super(null);
    }

    zzfz(zzfw zzfw0) {
    }

    private static List zza(Object object0, long v, int v1) {
        List list0 = zzfz.zzc(object0, v);
        if(list0.isEmpty()) {
            if(list0 instanceof zzfu) {
                list0 = new zzfv(v1);
            }
            else if(!(list0 instanceof zzgz) || !(list0 instanceof zzfk)) {
                list0 = new ArrayList(v1);
            }
            else {
                list0 = ((zzfk)list0).zza(v1);
            }
            zzia.zza(object0, v, list0);
            return list0;
        }
        Class class0 = list0.getClass();
        if(zzfz.zza.isAssignableFrom(class0)) {
            ArrayList arrayList0 = new ArrayList(list0.size() + v1);
            arrayList0.addAll(list0);
            zzia.zza(object0, v, arrayList0);
            return arrayList0;
        }
        if(list0 instanceof zzhz) {
            zzfv zzfv0 = new zzfv(list0.size() + v1);
            zzfv0.addAll(((zzhz)list0));
            zzia.zza(object0, v, zzfv0);
            return zzfv0;
        }
        if(list0 instanceof zzgz && list0 instanceof zzfk && !((zzfk)list0).zza()) {
            list0 = ((zzfk)list0).zza(list0.size() + v1);
            zzia.zza(object0, v, list0);
        }
        return list0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzfx
    final List zza(Object object0, long v) {
        return zzfz.zza(object0, v, 10);
    }

    @Override  // com.google.android.gms.internal.measurement.zzfx
    final void zza(Object object0, Object object1, long v) {
        List list0 = zzfz.zzc(object1, v);
        List list1 = zzfz.zza(object0, v, list0.size());
        int v1 = list1.size();
        if(v1 > 0 && list0.size() > 0) {
            list1.addAll(list0);
        }
        if(v1 > 0) {
            list0 = list1;
        }
        zzia.zza(object0, v, list0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzfx
    final void zzb(Object object0, long v) {
        List list1;
        List list0 = (List)zzia.zzf(object0, v);
        if(list0 instanceof zzfu) {
            list1 = ((zzfu)list0).i_();
        }
        else {
            Class class0 = list0.getClass();
            if(zzfz.zza.isAssignableFrom(class0)) {
                return;
            }
            if(list0 instanceof zzgz && list0 instanceof zzfk) {
                if(((zzfk)list0).zza()) {
                    ((zzfk)list0).j_();
                }
                return;
            }
            list1 = Collections.unmodifiableList(list0);
        }
        zzia.zza(object0, v, list1);
    }

    private static List zzc(Object object0, long v) {
        return (List)zzia.zzf(object0, v);
    }
}

