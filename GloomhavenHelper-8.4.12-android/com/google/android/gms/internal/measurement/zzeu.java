package com.google.android.gms.internal.measurement;

final class zzeu {
    private static final zzes zza;
    private static final zzes zzb;

    static {
        zzeu.zza = new zzer();
        zzeu.zzb = zzeu.zzc();
    }

    static zzes zza() {
        return zzeu.zza;
    }

    static zzes zzb() {
        zzes zzes0 = zzeu.zzb;
        if(zzes0 == null) {
            throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
        }
        return zzes0;
    }

    private static zzes zzc() {
        try {
            return (zzes)Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

