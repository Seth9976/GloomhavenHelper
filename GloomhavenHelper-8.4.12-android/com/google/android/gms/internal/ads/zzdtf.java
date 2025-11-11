package com.google.android.gms.internal.ads;

public enum zzdtf implements zzdzb {
    UNKNOWN_FORMAT(0),
    UNCOMPRESSED(1),
    COMPRESSED(2),
    DO_NOT_USE_CRUNCHY_UNCOMPRESSED(3),
    UNRECOGNIZED(-1);

    private final int value;
    private static final zzdze zzen;

    static {
        zzdtf.zzen = new zzdte();
    }

    private zzdtf(int v1) {
        this.value = v1;
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("<");
        stringBuilder0.append(this.getClass().getName());
        stringBuilder0.append('@');
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(this)));
        if(this != zzdtf.zzhhm) {
            stringBuilder0.append(" number=");
            stringBuilder0.append(this.zzaf());
        }
        stringBuilder0.append(" name=");
        stringBuilder0.append(this.name());
        stringBuilder0.append('>');
        return stringBuilder0.toString();
    }

    @Override  // com.google.android.gms.internal.ads.zzdzb
    public final int zzaf() {
        if(this == zzdtf.zzhhm) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static zzdtf zzei(int v) {
        switch(v) {
            case 0: {
                return zzdtf.zzhhi;
            }
            case 1: {
                return zzdtf.zzhhj;
            }
            case 2: {
                return zzdtf.zzhhk;
            }
            case 3: {
                return zzdtf.zzhhl;
            }
            default: {
                return null;
            }
        }
    }
}

