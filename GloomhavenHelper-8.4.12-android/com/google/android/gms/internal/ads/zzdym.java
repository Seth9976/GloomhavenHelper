package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzdym {
    static final class zza {
        private final int number;
        private final Object object;

        zza(Object object0, int v) {
            this.object = object0;
            this.number = v;
        }

        @Override
        public final boolean equals(Object object0) {
            return object0 instanceof zza ? this.object == ((zza)object0).object && this.number == ((zza)object0).number : false;
        }

        @Override
        public final int hashCode() {
            return System.identityHashCode(this.object) * 0xFFFF + this.number;
        }
    }

    private static volatile boolean zzhpo = false;
    private static boolean zzhpp = true;
    private static volatile zzdym zzhpq;
    private static volatile zzdym zzhpr;
    private static final zzdym zzhps;
    private final Map zzhpt;

    static {
        zzdym.zzhps = new zzdym(true);
    }

    zzdym() {
        this.zzhpt = new HashMap();
    }

    private zzdym(boolean z) {
        this.zzhpt = Collections.emptyMap();
    }

    public final zzd zza(zzeah zzeah0, int v) {
        zza zzdym$zza0 = new zza(zzeah0, v);
        return (zzd)this.zzhpt.get(zzdym$zza0);
    }

    public static zzdym zzbcg() {
        zzdym zzdym0 = zzdym.zzhpq;
        if(zzdym0 == null) {
            synchronized(zzdym.class) {
                zzdym0 = zzdym.zzhpq;
                if(zzdym0 == null) {
                    zzdym0 = zzdym.zzhps;
                    zzdym.zzhpq = zzdym0;
                }
            }
        }
        return zzdym0;
    }

    public static zzdym zzbch() {
        zzdym zzdym0 = zzdym.zzhpr;
        if(zzdym0 != null) {
            return zzdym0;
        }
        synchronized(zzdym.class) {
            zzdym zzdym1 = zzdym.zzhpr;
            if(zzdym1 != null) {
                return zzdym1;
            }
            zzdym zzdym2 = zzdyy.zze(zzdym.class);
            zzdym.zzhpr = zzdym2;
            return zzdym2;
        }
    }
}

