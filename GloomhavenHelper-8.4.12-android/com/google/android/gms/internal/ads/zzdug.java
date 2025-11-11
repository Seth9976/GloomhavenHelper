package com.google.android.gms.internal.ads;

public enum zzdug implements zzdzb {
    UNKNOWN_STATUS(0),
    ENABLED(1),
    DISABLED(2),
    DESTROYED(3),
    UNRECOGNIZED(-1);

    private final int value;
    private static final zzdze zzen;

    static {
        zzdug.zzen = new zzduf();
    }

    private zzdug(int v1) {
        this.value = v1;
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("<");
        stringBuilder0.append(this.getClass().getName());
        stringBuilder0.append('@');
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(this)));
        if(this != zzdug.zzhjn) {
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
        if(this == zzdug.zzhjn) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static zzdug zzep(int v) {
        switch(v) {
            case 0: {
                return zzdug.zzhjj;
            }
            case 1: {
                return zzdug.zzhjk;
            }
            case 2: {
                return zzdug.zzhjl;
            }
            case 3: {
                return zzdug.zzhjm;
            }
            default: {
                return null;
            }
        }
    }
}

