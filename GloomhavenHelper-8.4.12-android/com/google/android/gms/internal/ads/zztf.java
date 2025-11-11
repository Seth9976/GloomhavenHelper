package com.google.android.gms.internal.ads;

public enum zztf implements zzdzb {
    ENUM_FALSE(0),
    ENUM_TRUE(1),
    ENUM_UNKNOWN(1000);

    private final int value;
    private static final zzdze zzen;

    static {
        zztf.zzen = new zzte();
    }

    private zztf(int v1) {
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
        return zztg.zzew;
    }

    public static zztf zzbx(int v) {
        if(v != 1000) {
            switch(v) {
                case 0: {
                    return zztf.zzbwh;
                }
                case 1: {
                    return zztf.zzbwi;
                }
                default: {
                    return null;
                }
            }
        }
        return zztf.zzbwj;
    }
}

