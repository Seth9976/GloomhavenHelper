package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzdzc {
    private static final Charset ISO_8859_1;
    static final Charset UTF_8;
    public static final byte[] zzhtq;
    private static final ByteBuffer zzhtr;
    private static final zzdxz zzhts;

    static {
        zzdzc.UTF_8 = Charset.forName("UTF-8");
        zzdzc.ISO_8859_1 = Charset.forName("ISO-8859-1");
        byte[] arr_b = new byte[0];
        zzdzc.zzhtq = arr_b;
        zzdzc.zzhtr = ByteBuffer.wrap(arr_b);
        zzdzc.zzhts = zzdxz.zzb(zzdzc.zzhtq, 0, zzdzc.zzhtq.length, false);
    }

    static Object checkNotNull(Object object0) {
        if(object0 == null) {
            throw new NullPointerException();
        }
        return object0;
    }

    public static int hashCode(byte[] arr_b) {
        int v = zzdzc.zza(arr_b.length, arr_b, 0, arr_b.length);
        return v == 0 ? 1 : v;
    }

    static int zza(int v, byte[] arr_b, int v1, int v2) {
        for(int v3 = v1; v3 < v1 + v2; ++v3) {
            v = v * 0x1F + arr_b[v3];
        }
        return v;
    }

    static Object zza(Object object0, String s) {
        if(object0 == null) {
            throw new NullPointerException(s);
        }
        return object0;
    }

    // 去混淆评级： 低(20)
    public static int zzbr(boolean z) {
        return z ? 0x4CF : 0x4D5;
    }

    static Object zzd(Object object0, Object object1) {
        return ((zzeah)object0).zzbdd().zzf(((zzeah)object1)).zzbcw();
    }

    public static int zzfr(long v) {
        return (int)(v ^ v >>> 0x20);
    }

    static boolean zzk(zzeah zzeah0) {
        if(zzeah0 instanceof zzdxf) {
            zzdxf zzdxf0 = (zzdxf)zzeah0;
        }
        return false;
    }

    public static boolean zzx(byte[] arr_b) {
        return zzece.zzx(arr_b);
    }

    public static String zzy(byte[] arr_b) {
        return new String(arr_b, zzdzc.UTF_8);
    }
}

