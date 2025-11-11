package com.google.android.gms.internal.measurement;

final class zzif {
    private static void zzb(byte b, byte b1, byte b2, byte b3, char[] arr_c, int v) throws zzfn {
        if(zzif.zzg(b1) || (b << 28) + (b1 + 0x70) >> 30 != 0 || zzif.zzg(b2) || zzif.zzg(b3)) {
            throw zzfn.zzh();
        }
        int v1 = (b & 7) << 18 | (b1 & 0x3F) << 12 | (b2 & 0x3F) << 6 | b3 & 0x3F;
        arr_c[v] = (char)((v1 >>> 10) + 0xD7C0);
        arr_c[v + 1] = (char)((v1 & 0x3FF) + 0xDC00);
    }

    private static void zzb(byte b, byte b1, byte b2, char[] arr_c, int v) throws zzfn {
        if(zzif.zzg(b1) || b == 0xFFFFFFE0 && b1 < 0xFFFFFFA0 || b == -19 && b1 >= 0xFFFFFFA0 || zzif.zzg(b2)) {
            throw zzfn.zzh();
        }
        arr_c[v] = (char)((b & 15) << 12 | (b1 & 0x3F) << 6 | b2 & 0x3F);
    }

    private static void zzb(byte b, byte b1, char[] arr_c, int v) throws zzfn {
        if(b < -62 || zzif.zzg(b1)) {
            throw zzfn.zzh();
        }
        arr_c[v] = (char)((b & 0x1F) << 6 | b1 & 0x3F);
    }

    private static void zzb(byte b, char[] arr_c, int v) {
        arr_c[v] = (char)b;
    }

    private static boolean zzd(byte b) {
        return b >= 0;
    }

    private static boolean zze(byte b) {
        return b < 0xFFFFFFE0;
    }

    private static boolean zzf(byte b) {
        return b < -16;
    }

    private static boolean zzg(byte b) {
        return b > -65;
    }
}

