package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzfe {
    static final Charset zza;
    public static final byte[] zzb;
    private static final Charset zzc;
    private static final ByteBuffer zzd;
    private static final zzeh zze;

    static {
        zzfe.zza = Charset.forName("UTF-8");
        zzfe.zzc = Charset.forName("ISO-8859-1");
        byte[] arr_b = new byte[0];
        zzfe.zzb = arr_b;
        zzfe.zzd = ByteBuffer.wrap(arr_b);
        zzfe.zze = zzeh.zza(zzfe.zzb, 0, zzfe.zzb.length, false);
    }

    static int zza(int v, byte[] arr_b, int v1, int v2) {
        for(int v3 = v1; v3 < v1 + v2; ++v3) {
            v = v * 0x1F + arr_b[v3];
        }
        return v;
    }

    public static int zza(long v) {
        return (int)(v ^ v >>> 0x20);
    }

    // 去混淆评级： 低(20)
    public static int zza(boolean z) {
        return z ? 0x4CF : 0x4D5;
    }

    static Object zza(Object object0) {
        if(object0 == null) {
            throw new NullPointerException();
        }
        return object0;
    }

    static Object zza(Object object0, Object object1) {
        return ((zzgn)object0).zzbp().zza(((zzgn)object1)).zzt();
    }

    static Object zza(Object object0, String s) {
        if(object0 == null) {
            throw new NullPointerException(s);
        }
        return object0;
    }

    static boolean zza(zzgn zzgn0) {
        return false;
    }

    public static boolean zza(byte[] arr_b) {
        return zzid.zza(arr_b);
    }

    public static String zzb(byte[] arr_b) {
        return new String(arr_b, zzfe.zza);
    }

    public static int zzc(byte[] arr_b) {
        int v = zzfe.zza(arr_b.length, arr_b, 0, arr_b.length);
        return v == 0 ? 1 : v;
    }
}

