package com.google.android.gms.internal.ads;

import java.util.UUID;

public final class zzgl {
    public static final int CHANNEL_OUT_7POINT1_SURROUND;
    public static final UUID zzacq;
    private static final UUID zzacr;
    private static final UUID zzacs;
    private static final UUID zzact;

    static {
        zzgl.CHANNEL_OUT_7POINT1_SURROUND = zzop.SDK_INT >= 23 ? 0x18FC : 1020;
        zzgl.zzacq = new UUID(0L, 0L);
        zzgl.zzacr = new UUID(0x1077EFECC0B24D02L, 0xACE33C1E52E2FB4BL);
        zzgl.zzacs = new UUID(0xEDEF8BA979D64ACEL, -6645017420763422227L);
        zzgl.zzact = new UUID(0x9A04F07998404286L, 0xAB92E65BE0885F95L);
    }

    public static long zzdo(long v) {
        return v == 0x8000000000000001L ? 0x8000000000000001L : v / 1000L;
    }

    public static long zzdp(long v) {
        return v == 0x8000000000000001L ? 0x8000000000000001L : v * 1000L;
    }
}

