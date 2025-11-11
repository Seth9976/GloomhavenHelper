package com.google.android.gms.internal.ads;

public enum zzcd implements zzdzb {
    ENUM_FALSE(0),
    ENUM_TRUE(1),
    ENUM_FAILURE(2),
    ENUM_UNKNOWN(1000);

    private final int value;
    private static final zzdze zzen;

    static {
        zzcd.zzen = new zzcc();
    }

    private zzcd(int v1) {
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
        return zzcf.zzew;
    }

    public static zzcd zzj(int v) {
        if(v != 1000) {
            switch(v) {
                case 0: {
                    return zzcd.zzlb;
                }
                case 1: {
                    return zzcd.zzlc;
                }
                case 2: {
                    return zzcd.zzld;
                }
                default: {
                    return null;
                }
            }
        }
        return zzcd.zzle;
    }
}

