package com.google.android.gms.internal.ads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzeaw {
    private static final zzeaw zzhvz;
    private final zzebg zzhwa;
    private final ConcurrentMap zzhwb;

    static {
        zzeaw.zzhvz = new zzeaw();
    }

    private zzeaw() {
        this.zzhwb = new ConcurrentHashMap();
        this.zzhwa = new zzdzy();
    }

    public final zzebd zzba(Object object0) {
        return this.zzh(object0.getClass());
    }

    public static zzeaw zzbem() {
        return zzeaw.zzhvz;
    }

    public final zzebd zzh(Class class0) {
        zzdzc.zza(class0, "messageType");
        zzebd zzebd0 = (zzebd)this.zzhwb.get(class0);
        if(zzebd0 == null) {
            zzebd0 = this.zzhwa.zzg(class0);
            zzdzc.zza(class0, "messageType");
            zzdzc.zza(zzebd0, "schema");
            zzebd zzebd1 = (zzebd)this.zzhwb.putIfAbsent(class0, zzebd0);
            return zzebd1 == null ? zzebd0 : zzebd1;
        }
        return zzebd0;
    }
}

