package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;

final class zzead implements zzeae {
    @Override  // com.google.android.gms.internal.ads.zzeae
    public final Map zzar(Object object0) {
        return (zzeab)object0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeae
    public final zzeac zzas(Object object0) {
        zzdzz zzdzz0 = (zzdzz)object0;
        throw new NoSuchMethodError();
    }

    @Override  // com.google.android.gms.internal.ads.zzeae
    public final Map zzat(Object object0) {
        return (zzeab)object0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeae
    public final boolean zzau(Object object0) {
        return !((zzeab)object0).isMutable();
    }

    @Override  // com.google.android.gms.internal.ads.zzeae
    public final Object zzav(Object object0) {
        ((zzeab)object0).zzban();
        return object0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeae
    public final Object zzaw(Object object0) {
        return zzeab.zzbdz().zzbea();
    }

    @Override  // com.google.android.gms.internal.ads.zzeae
    public final int zzb(int v, Object object0, Object object1) {
        zzdzz zzdzz0 = (zzdzz)object1;
        if(((zzeab)object0).isEmpty()) {
            return 0;
        }
        Iterator iterator0 = ((zzeab)object0).entrySet().iterator();
        if(!iterator0.hasNext()) {
            return 0;
        }
        Object object2 = iterator0.next();
        ((Map.Entry)object2).getKey();
        ((Map.Entry)object2).getValue();
        throw new NoSuchMethodError();
    }

    @Override  // com.google.android.gms.internal.ads.zzeae
    public final Object zze(Object object0, Object object1) {
        zzeab zzeab0 = (zzeab)object0;
        if(!((zzeab)object1).isEmpty()) {
            if(!zzeab0.isMutable()) {
                zzeab0 = zzeab0.zzbea();
            }
            zzeab0.zza(((zzeab)object1));
        }
        return zzeab0;
    }
}

