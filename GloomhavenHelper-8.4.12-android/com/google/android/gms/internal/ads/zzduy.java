package com.google.android.gms.internal.ads;

public enum zzduy implements zzdzb {
    UNKNOWN_PREFIX(0),
    TINK(1),
    LEGACY(2),
    RAW(3),
    CRUNCHY(4),
    UNRECOGNIZED(-1);

    private final int value;
    private static final zzdze zzen;

    static {
        zzduy.zzen = new zzdux();
    }

    private zzduy(int v1) {
        this.value = v1;
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("<");
        stringBuilder0.append(this.getClass().getName());
        stringBuilder0.append('@');
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(this)));
        if(this != zzduy.zzhku) {
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
        if(this == zzduy.zzhku) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static zzduy zzew(int v) {
        switch(v) {
            case 0: {
                return zzduy.zzhkp;
            }
            case 1: {
                return zzduy.zzhkq;
            }
            case 2: {
                return zzduy.zzhkr;
            }
            case 3: {
                return zzduy.zzhks;
            }
            case 4: {
                return zzduy.zzhkt;
            }
            default: {
                return null;
            }
        }
    }
}

