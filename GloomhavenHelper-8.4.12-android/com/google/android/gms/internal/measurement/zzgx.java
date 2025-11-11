package com.google.android.gms.internal.measurement;

final class zzgx {
    private static final zzgv zza;
    private static final zzgv zzb;

    static {
        zzgx.zza = zzgx.zzc();
        zzgx.zzb = new zzgu();
    }

    static zzgv zza() {
        return zzgx.zza;
    }

    static zzgv zzb() {
        return zzgx.zzb;
    }

    private static zzgv zzc() {
        try {
            return (zzgv)Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

