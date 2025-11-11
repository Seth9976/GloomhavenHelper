package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

public final class zzij {
    private static final int[] zzalc;
    private static final int[] zzald;
    private static final int[] zzale;

    static {
        zzij.zzalc = new int[]{1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
        zzij.zzald = new int[]{-1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
        zzij.zzale = new int[]{0x40, 0x70, 0x80, 0xC0, 0xE0, 0x100, 0x180, 0x1C0, 0x200, 640, 0x300, 0x380, 0x400, 0x480, 0x500, 0x600, 0x780, 0x800, 0x900, 0xA00, 0xA80, 0xB00, 0xB07, 0xB80, 0xC00, 0xF00, 0x1000, 0x1800, 0x1E00};
    }

    public static int zzj(ByteBuffer byteBuffer0) {
        int v = byteBuffer0.position();
        int v1 = byteBuffer0.get(v + 4);
        return ((byteBuffer0.get(v + 5) & 0xFC) >> 2 | (v1 & 1) << 6) + 1 << 5;
    }
}

