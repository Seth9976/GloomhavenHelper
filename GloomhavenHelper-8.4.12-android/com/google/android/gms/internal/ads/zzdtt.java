package com.google.android.gms.internal.ads;

public enum zzdtt implements zzdzb {
    UNKNOWN_CURVE(0),
    NIST_P256(2),
    NIST_P384(3),
    NIST_P521(4),
    CURVE25519(5),
    UNRECOGNIZED(-1);

    private final int value;
    private static final zzdze zzen;

    static {
        zzdtt.zzen = new zzdts();
    }

    private zzdtt(int v1) {
        this.value = v1;
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("<");
        stringBuilder0.append(this.getClass().getName());
        stringBuilder0.append('@');
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(this)));
        if(this != zzdtt.zzhik) {
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
        if(this == zzdtt.zzhik) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static zzdtt zzel(int v) {
        if(v != 0) {
            switch(v) {
                case 2: {
                    return zzdtt.zzhig;
                }
                case 3: {
                    return zzdtt.zzhih;
                }
                case 4: {
                    return zzdtt.zzhii;
                }
                case 5: {
                    return zzdtt.zzhij;
                }
                default: {
                    return null;
                }
            }
        }
        return zzdtt.zzhif;
    }
}

