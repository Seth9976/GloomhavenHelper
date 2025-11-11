package com.google.android.gms.internal.measurement;

public enum zzil {
    DOUBLE(zzio.zzd, 1),
    FLOAT(zzio.zzc, 5),
    INT64(zzio.zzb, 0),
    UINT64(zzio.zzb, 0),
    INT32(zzio.zza, 0),
    FIXED64(zzio.zzb, 1),
    FIXED32(zzio.zza, 5),
    BOOL(zzio.zze, 0),
    STRING(zzio.zzf, 2) {
    },
    GROUP(zzio.zzi, 3) {
    },
    MESSAGE(zzio.zzi, 2) {
    },
    BYTES(zzio.zzg, 2) {
    },
    UINT32(zzio.zza, 0),
    ENUM(zzio.zzh, 0),
    SFIXED32(zzio.zza, 5),
    SFIXED64(zzio.zzb, 1),
    SINT32(zzio.zza, 0),
    SINT64(zzio.zzb, 0);

    private final zzio zzs;
    private final int zzt;

    private zzil(zzio zzio0, int v1) {
        this.zzs = zzio0;
        this.zzt = v1;
    }

    zzil(zzio zzio0, int v1, zzii zzii0) {
        this(zzio0, v1);
    }

    public final zzio zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }
}

