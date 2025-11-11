package com.google.android.gms.internal.ads;

public enum zzecm {
    DOUBLE(zzecp.zzhzl, 1),
    FLOAT(zzecp.zzhzk, 5),
    INT64(zzecp.zzhzj, 0),
    UINT64(zzecp.zzhzj, 0),
    INT32(zzecp.zzhzi, 0),
    FIXED64(zzecp.zzhzj, 1),
    FIXED32(zzecp.zzhzi, 5),
    BOOL(zzecp.zzhzm, 0),
    STRING(zzecp.zzhzn, 2) {
    },
    GROUP(zzecp.zzhzq, 3) {
    },
    MESSAGE(zzecp.zzhzq, 2) {
    },
    BYTES(zzecp.zzhzo, 2) {
    },
    UINT32(zzecp.zzhzi, 0),
    ENUM(zzecp.zzhzp, 0),
    SFIXED32(zzecp.zzhzi, 5),
    SFIXED64(zzecp.zzhzj, 1),
    SINT32(zzecp.zzhzi, 0),
    SINT64(zzecp.zzhzj, 0);

    private final zzecp zzhzf;
    private final int zzhzg;

    private zzecm(zzecp zzecp0, int v1) {
        this.zzhzf = zzecp0;
        this.zzhzg = v1;
    }

    zzecm(zzecp zzecp0, int v1, zzecj zzecj0) {
        this(zzecp0, v1);
    }

    public final zzecp zzbfo() {
        return this.zzhzf;
    }

    public final int zzbfp() {
        return this.zzhzg;
    }
}

