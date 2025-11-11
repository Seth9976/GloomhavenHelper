package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public final class zzbg {
    public static int zza(byte b) {
        return b < 0 ? b + 0x100 : b;
    }

    public static long zza(ByteBuffer byteBuffer0) {
        long v = (long)byteBuffer0.getInt();
        return v >= 0L ? v : v + 0x100000000L;
    }

    public static int zzb(ByteBuffer byteBuffer0) {
        return (zzbg.zza(byteBuffer0.get()) << 8) + zzbg.zza(byteBuffer0.get());
    }

    public static long zzc(ByteBuffer byteBuffer0) {
        long v = zzbg.zza(byteBuffer0) << 0x20;
        if(v < 0L) {
            throw new RuntimeException("I don\'t know how to deal with UInt64! long is not sufficient and I don\'t want to use BigInt");
        }
        return v + zzbg.zza(byteBuffer0);
    }

    public static double zzd(ByteBuffer byteBuffer0) {
        byte[] arr_b = new byte[4];
        byteBuffer0.get(arr_b);
        return ((double)(arr_b[0] << 24 & 0xFF000000 | arr_b[1] << 16 & 0xFF0000 | arr_b[2] << 8 & 0xFF00 | arr_b[3] & 0xFF)) / 65536.0;
    }

    public static double zze(ByteBuffer byteBuffer0) {
        byte[] arr_b = new byte[4];
        byteBuffer0.get(arr_b);
        return ((double)(arr_b[0] << 24 & 0xFF000000 | arr_b[1] << 16 & 0xFF0000 | arr_b[2] << 8 & 0xFF00 | arr_b[3] & 0xFF)) / 1073741824.0;
    }

    public static String zzf(ByteBuffer byteBuffer0) {
        byte[] arr_b = new byte[4];
        byteBuffer0.get(arr_b);
        try {
            return new String(arr_b, "ISO-8859-1");
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new RuntimeException(unsupportedEncodingException0);
        }
    }
}

