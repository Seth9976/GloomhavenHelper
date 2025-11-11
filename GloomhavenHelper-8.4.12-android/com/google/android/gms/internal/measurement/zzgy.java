package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzgy {
    private static final zzgy zza;
    private final zzhf zzb;
    private final ConcurrentMap zzc;

    static {
        zzgy.zza = new zzgy();
    }

    private zzgy() {
        this.zzc = new ConcurrentHashMap();
        this.zzb = new zzga();
    }

    public static zzgy zza() {
        return zzgy.zza;
    }

    public final zzhc zza(Class class0) {
        zzfe.zza(class0, "messageType");
        zzhc zzhc0 = (zzhc)this.zzc.get(class0);
        if(zzhc0 == null) {
            zzhc0 = this.zzb.zza(class0);
            zzfe.zza(class0, "messageType");
            zzfe.zza(zzhc0, "schema");
            zzhc zzhc1 = (zzhc)this.zzc.putIfAbsent(class0, zzhc0);
            return zzhc1 == null ? zzhc0 : zzhc1;
        }
        return zzhc0;
    }

    public final zzhc zza(Object object0) {
        return this.zza(object0.getClass());
    }
}

