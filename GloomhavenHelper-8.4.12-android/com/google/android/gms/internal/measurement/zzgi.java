package com.google.android.gms.internal.measurement;

final class zzgi {
    private static final zzgg zza;
    private static final zzgg zzb;

    static {
        zzgi.zza = zzgi.zzc();
        zzgi.zzb = new zzgj();
    }

    static zzgg zza() {
        return zzgi.zza;
    }

    static zzgg zzb() {
        return zzgi.zzb;
    }

    private static zzgg zzc() {
        try {
            return (zzgg)Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

