package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzeq {
    static final class zza {
        private final Object zza;
        private final int zzb;

        zza(Object object0, int v) {
            this.zza = object0;
            this.zzb = v;
        }

        @Override
        public final boolean equals(Object object0) {
            return object0 instanceof zza ? this.zza == ((zza)object0).zza && this.zzb == ((zza)object0).zzb : false;
        }

        @Override
        public final int hashCode() {
            return System.identityHashCode(this.zza) * 0xFFFF + this.zzb;
        }
    }

    private static volatile boolean zza = false;
    private static boolean zzb = true;
    private static final Class zzc;
    private static volatile zzeq zzd;
    private static volatile zzeq zze;
    private static final zzeq zzf;
    private final Map zzg;

    static {
        zzeq.zzc = zzeq.zzc();
        zzeq.zzf = new zzeq(true);
    }

    zzeq() {
        this.zzg = new HashMap();
    }

    private zzeq(boolean z) {
        this.zzg = Collections.emptyMap();
    }

    public static zzeq zza() {
        zzeq zzeq0 = zzeq.zzd;
        if(zzeq0 == null) {
            synchronized(zzeq.class) {
                zzeq0 = zzeq.zzd;
                if(zzeq0 == null) {
                    zzeq0 = zzeq.zzf;
                    zzeq.zzd = zzeq0;
                }
            }
        }
        return zzeq0;
    }

    public final zze zza(zzgn zzgn0, int v) {
        zza zzeq$zza0 = new zza(zzgn0, v);
        return (zze)this.zzg.get(zzeq$zza0);
    }

    public static zzeq zzb() {
        zzeq zzeq0 = zzeq.zze;
        if(zzeq0 == null) {
            synchronized(zzeq.class) {
                zzeq0 = zzeq.zze;
                if(zzeq0 == null) {
                    zzeq0 = zzfc.zza(zzeq.class);
                    zzeq.zze = zzeq0;
                }
                return zzeq0;
            }
        }
        return zzeq0;
    }

    private static Class zzc() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        }
        catch(ClassNotFoundException unused_ex) {
            return null;
        }
    }
}

