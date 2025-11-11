package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzdzt extends zzdzr {
    private static final Class zzhus;

    static {
        zzdzt.zzhus = Collections.unmodifiableList(Collections.emptyList()).getClass();
    }

    private zzdzt() {
        super(null);
    }

    zzdzt(zzdzu zzdzu0) {
    }

    private static List zza(Object object0, long v, int v1) {
        List list0 = zzdzt.zzc(object0, v);
        if(list0.isEmpty()) {
            if(list0 instanceof zzdzs) {
                list0 = new zzdzp(v1);
            }
            else if(!(list0 instanceof zzeat) || !(list0 instanceof zzdzi)) {
                list0 = new ArrayList(v1);
            }
            else {
                list0 = ((zzdzi)list0).zzfd(v1);
            }
            zzecb.zza(object0, v, list0);
            return list0;
        }
        Class class0 = list0.getClass();
        if(zzdzt.zzhus.isAssignableFrom(class0)) {
            ArrayList arrayList0 = new ArrayList(list0.size() + v1);
            arrayList0.addAll(list0);
            zzecb.zza(object0, v, arrayList0);
            return arrayList0;
        }
        if(list0 instanceof zzeca) {
            zzdzp zzdzp0 = new zzdzp(list0.size() + v1);
            zzdzp0.addAll(((zzeca)list0));
            zzecb.zza(object0, v, zzdzp0);
            return zzdzp0;
        }
        if(list0 instanceof zzeat && list0 instanceof zzdzi && !((zzdzi)list0).zzbam()) {
            list0 = ((zzdzi)list0).zzfd(list0.size() + v1);
            zzecb.zza(object0, v, list0);
        }
        return list0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdzr
    final List zza(Object object0, long v) {
        return zzdzt.zza(object0, v, 10);
    }

    @Override  // com.google.android.gms.internal.ads.zzdzr
    final void zza(Object object0, Object object1, long v) {
        List list0 = zzdzt.zzc(object1, v);
        List list1 = zzdzt.zza(object0, v, list0.size());
        int v1 = list1.size();
        if(v1 > 0 && list0.size() > 0) {
            list1.addAll(list0);
        }
        if(v1 > 0) {
            list0 = list1;
        }
        zzecb.zza(object0, v, list0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdzr
    final void zzb(Object object0, long v) {
        List list1;
        List list0 = (List)zzecb.zzp(object0, v);
        if(list0 instanceof zzdzs) {
            list1 = ((zzdzs)list0).zzbdv();
        }
        else {
            Class class0 = list0.getClass();
            if(zzdzt.zzhus.isAssignableFrom(class0)) {
                return;
            }
            if(list0 instanceof zzeat && list0 instanceof zzdzi) {
                if(((zzdzi)list0).zzbam()) {
                    ((zzdzi)list0).zzban();
                }
                return;
            }
            list1 = Collections.unmodifiableList(list0);
        }
        zzecb.zza(object0, v, list1);
    }

    private static List zzc(Object object0, long v) {
        return (List)zzecb.zzp(object0, v);
    }
}

