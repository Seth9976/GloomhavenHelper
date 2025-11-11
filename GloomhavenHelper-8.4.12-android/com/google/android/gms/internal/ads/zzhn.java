package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

public final class zzhn {
    private static final int[] zzahf;
    private static final int[] zzahg;
    private static final int[] zzahh;
    private static final int[] zzahi;
    private static final int[] zzahj;
    private static final int[] zzahk;

    static {
        zzhn.zzahf = new int[]{1, 2, 3, 6};
        zzhn.zzahg = new int[]{48000, 44100, 32000};
        zzhn.zzahh = new int[]{24000, 22050, 16000};
        zzhn.zzahi = new int[]{2, 1, 2, 3, 3, 4, 4, 5};
        zzhn.zzahj = new int[]{0x20, 40, 0x30, 56, 0x40, 80, 0x60, 0x70, 0x80, 0xA0, 0xC0, 0xE0, 0x100, 320, 0x180, 0x1C0, 0x200, 0x240, 640};
        zzhn.zzahk = new int[]{69, 87, 104, 0x79, 0x8B, 0xAE, 0xD0, 0xF3, 278, 348, 417, 487, 557, 696, 835, 0x3CF, 0x45A, 0x4E5, 0x571};
    }

    public static zzgz zza(zzom zzom0, String s, String s1, zziu zziu0) {
        int v = zzhn.zzahg[(zzom0.readUnsignedByte() & 0xC0) >> 6];
        int v1 = zzom0.readUnsignedByte();
        int v2 = zzhn.zzahi[(v1 & 56) >> 3];
        return (v1 & 4) == 0 ? zzgz.zza(s, "audio/ac3", null, -1, -1, v2, v, null, null, 0, s1) : zzgz.zza(s, "audio/ac3", null, -1, -1, v2 + 1, v, null, null, 0, s1);
    }

    public static zzgz zzb(zzom zzom0, String s, String s1, zziu zziu0) {
        zzom0.zzbi(2);
        int v = zzhn.zzahg[(zzom0.readUnsignedByte() & 0xC0) >> 6];
        int v1 = zzom0.readUnsignedByte();
        int v2 = zzhn.zzahi[(v1 & 14) >> 1];
        return (v1 & 1) == 0 ? zzgz.zza(s, "audio/eac3", null, -1, -1, v2, v, null, null, 0, s1) : zzgz.zza(s, "audio/eac3", null, -1, -1, v2 + 1, v, null, null, 0, s1);
    }

    public static int zzfd() [...] // Inlined contents

    public static int zzh(ByteBuffer byteBuffer0) {
        return ((byteBuffer0.get(byteBuffer0.position() + 4) & 0xC0) >> 6 == 3 ? 6 : zzhn.zzahf[(byteBuffer0.get(byteBuffer0.position() + 4) & 0x30) >> 4]) * 0x100;
    }
}

