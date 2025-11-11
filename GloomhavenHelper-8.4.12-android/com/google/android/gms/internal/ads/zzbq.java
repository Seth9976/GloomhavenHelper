package com.google.android.gms.internal.ads;

public enum zzbq implements zzdzb {
    ENUM_SIGNAL_SOURCE_UNKNOWN(0),
    ENUM_SIGNAL_SOURCE_DISABLE(1),
    ENUM_SIGNAL_SOURCE_ADSHIELD(2),
    ENUM_SIGNAL_SOURCE_GASS(3),
    ENUM_SIGNAL_SOURCE_CALLER_PROVIDED(4);

    private final int value;
    private static final zzdze zzen;

    static {
        zzbq.zzen = new zzbp();
    }

    private zzbq(int v1) {
        this.value = v1;
    }

    @Override
    public final String toString() {
        return "<" + this.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + this.name() + '>';
    }

    @Override  // com.google.android.gms.internal.ads.zzdzb
    public final int zzaf() {
        return this.value;
    }

    public static zzdzd zzag() {
        return zzbr.zzew;
    }

    public static zzbq zze(int v) {
        switch(v) {
            case 0: {
                return zzbq.zzei;
            }
            case 1: {
                return zzbq.zzej;
            }
            case 2: {
                return zzbq.zzek;
            }
            case 3: {
                return zzbq.zzel;
            }
            case 4: {
                return zzbq.zzem;
            }
            default: {
                return null;
            }
        }
    }
}

