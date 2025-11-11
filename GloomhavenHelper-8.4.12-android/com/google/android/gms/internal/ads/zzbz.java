package com.google.android.gms.internal.ads;

public enum zzbz implements zzdzb {
    UNKNOWN_ENCRYPTION_METHOD(0),
    BITSLICER(1),
    TINK_HYBRID(2),
    UNENCRYPTED(3),
    DG(4);

    private final int value;
    private static final zzdze zzen;

    static {
        zzbz.zzen = new zzcb();
    }

    private zzbz(int v1) {
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
        return zzca.zzew;
    }

    public static zzbz zzi(int v) {
        switch(v) {
            case 0: {
                return zzbz.zzkv;
            }
            case 1: {
                return zzbz.zzkw;
            }
            case 2: {
                return zzbz.zzkx;
            }
            case 3: {
                return zzbz.zzky;
            }
            case 4: {
                return zzbz.zzkz;
            }
            default: {
                return null;
            }
        }
    }
}

