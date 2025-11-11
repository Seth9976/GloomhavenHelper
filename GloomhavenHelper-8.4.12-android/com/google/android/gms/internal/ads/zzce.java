package com.google.android.gms.internal.ads;

public enum zzce implements zzdzb {
    UNKNOWN_PROTO(0),
    AFMA_SIGNALS(1),
    UNITY_SIGNALS(2),
    PARTNER_SIGNALS(3);

    private final int value;
    private static final zzdze zzen;

    static {
        zzce.zzen = new zzch();
    }

    private zzce(int v1) {
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
        return zzcg.zzew;
    }

    public static zzce zzk(int v) {
        switch(v) {
            case 0: {
                return zzce.zzlg;
            }
            case 1: {
                return zzce.zzlh;
            }
            case 2: {
                return zzce.zzli;
            }
            case 3: {
                return zzce.zzlj;
            }
            default: {
                return null;
            }
        }
    }
}

