package com.google.android.gms.internal.ads;

public enum zzdtv implements zzdzb {
    UNKNOWN_HASH(0),
    SHA1(1),
    SHA384(2),
    SHA256(3),
    SHA512(4),
    UNRECOGNIZED(-1);

    private final int value;
    private static final zzdze zzen;

    static {
        zzdtv.zzen = new zzdtu();
    }

    private zzdtv(int v1) {
        this.value = v1;
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("<");
        stringBuilder0.append(this.getClass().getName());
        stringBuilder0.append('@');
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(this)));
        if(this != zzdtv.zzhir) {
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
        if(this == zzdtv.zzhir) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static zzdtv zzem(int v) {
        switch(v) {
            case 0: {
                return zzdtv.zzhim;
            }
            case 1: {
                return zzdtv.zzhin;
            }
            case 2: {
                return zzdtv.zzhio;
            }
            case 3: {
                return zzdtv.zzhip;
            }
            case 4: {
                return zzdtv.zzhiq;
            }
            default: {
                return null;
            }
        }
    }
}

