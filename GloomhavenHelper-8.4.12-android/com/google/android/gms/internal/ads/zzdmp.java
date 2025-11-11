package com.google.android.gms.internal.ads;

public final class zzdmp {
    private static final byte[] zzhbd;
    private static final int[] zzhbe;
    private static final int[] zzhbf;
    private static final int[] zzhbg;
    private static int[] zzhbh;

    static {
        zzdmp.zzhbd = new byte[]{9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
        zzdmp.zzhbe = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
        zzdmp.zzhbf = new int[]{3, 0x1F, 316, 0xC5A, 0x7B86, 0x4D343, 0x3040A5, 0x1E28678, 0x12D940B6, 0x7FFFFFFF};
        zzdmp.zzhbg = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 0x58980, 0x375F00, 0x2611500, 479001600};
        zzdmp.zzhbh = new int[]{0x7FFFFFFF, 0x7FFFFFFF, 0x10000, 0x929, 477, 0xC1, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};
    }

    public static int zzx(int v, int v1) {
        if(((long)v) << 1 > 0x7FFFFFFFL) {
            return 0x7FFFFFFF;
        }
        return ((long)v) << 1 >= 0xFFFFFFFF80000000L ? ((int)(((long)v) << 1)) : 0x80000000;
    }
}

