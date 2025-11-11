package com.google.android.gms.internal.ads;

import android.os.Build.VERSION;
import android.os.Build;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public final class zzop {
    public static final String DEVICE;
    public static final String MANUFACTURER;
    public static final String MODEL;
    public static final int SDK_INT;
    public static final String zzbhc;
    private static final Pattern zzbhd;
    private static final Pattern zzbhe;
    private static final Pattern zzbhf;
    private static final int[] zzbhg;

    static {
        zzop.SDK_INT = Build.VERSION.SDK_INT != 25 || Build.VERSION.CODENAME.charAt(0) != 0x4F ? Build.VERSION.SDK_INT : 26;
        zzop.DEVICE = Build.DEVICE;
        zzop.MANUFACTURER = Build.MANUFACTURER;
        zzop.MODEL = Build.MODEL;
        zzop.zzbhc = zzop.DEVICE + ", " + zzop.MODEL + ", " + zzop.MANUFACTURER + ", " + 33;
        zzop.zzbhd = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)([\\.,](\\d+))?([Zz]|((\\+|\\-)(\\d?\\d):?(\\d\\d)))?");
        zzop.zzbhe = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
        zzop.zzbhf = Pattern.compile("%([A-Fa-f0-9]{2})");
        zzop.zzbhg = new int[]{0, 0x4C11DB7, 0x9823B6E, 222504665, 0x130476DC, 398814059, 445009330, 507990021, 0x2608EDB8, 0x22C9F00F, 0x2F8AD6D6, 0x2B4BCB61, 890018660, 0x31CD86D3, 1015980042, 944750013, 0x4C11DB70, 0x48D0C6C7, 1167319070, 0x4152FDA9, 0x5F15ADAC, 0x5BD4B01B, 1452775106, 1381403509, 1780037320, 0x6ED82B7F, 0x639B0DA6, 0x675A1011, 2031960084, 0x7DDC5DA3, 1889500026, 0x745E66CD, 0x9823B6E0, -1662866601, 0x91A18D8E, 0x95609039, 0x8B27C03C, 0x8FE6DD8B, -2103051438, -2040207643, -1104454824, -1159051537, 0xB7A96036, 0xB3687D81, 0xAD2F2D84, -1444007885, -1532160278, 0xA06C0B5D, 0xD4326D90, 0xD0F37027, 0xDDB056FE, 0xD9714B49, -952755380, 0xC3F706FB, -827056094, 0xCA753D95, 0xF23A8028, 0xF6FB9D9F, 0xFBB8BB46, 0xFF79A6F1, 0xE13EF6F4, 0xE5FFEB43, -390279782, 0xEC7DD02D, 0x34867077, 0x30476DC0, 0x3D044B19, 969234094, 0x278206AB, 591600412, 0x2E003DC5, 0x2AC12072, 0x128E9DCF, 0x164F8078, 0x1B0CA6A1, 0x1FCDBB16, 0x18AEB13, 0x54BF6A4, 0x808D07D, 214552010, 2023205639, 2086057648, 0x71159069, 0x75D48DDE, 1804852699, 0x6F52C06C, 1645340341, 0x66D0FB02, 0x5E9F46BF, 0x5A5E5B08, 1461550545, 0x53DC6066, 1302016099, 1230646740, 0x44190B0D, 1087903418, 0xACA5C697, 0xA864DB20, 0xA527FDF9, 0xA1E6E04E, 0xBFA1B04B, 0xBB60ADFC, -1239184603, 0xB2E29692, -1968362705, -1905510760, 0x832F1041, 0x87EE0DF6, 0x99A95DF3, 0x9D684044, 0x902B669D, 0x94EA7B2A, 0xE0B41DE7, 0xE4750050, 0xE9362689, 0xEDF73B3E, -206542021, -143559028, 0xFA325055, 0xFEF34DE2, 0xC6BCF05F, -1031934488, 0xCF3ECB31, 0xCBFFD686, -709327229, -780559564, -600130067, 0xD8FBA05A, 0x690CE0EE, 0x6DCDFD59, 0x608EDB80, 0x644FC637, 2047383090, 0x7EC98B85, 0x738AAD5C, 2001449195, 0x4F040D56, 0x4BC510E1, 1183200824, 0x42472B8F, 0x5C007B8A, 1489069629, 0x558240E4, 0x51435D53, 0x251D3B9E, 568075817, 0x2C9F00F0, 0x285E1D47, 907627842, 0x32D850F5, 1067152940, 0x3B5A6B9B, 0x315D626, 0x7D4CB91, 177728840, 0xE56F0FF, 0x1011A0FA, 0x14D0BD4D, 429104020, 0x1D528623, 0xF12F560E, 0xF5EE4BB9, -122852000, -60002089, -500490030, -420856475, 0xEBA91BBC, 0xEF68060B, 0xD727BBB6, 0xD3E6A601, 0xDEA580D8, -630940305, -1004286614, 0xC0E2D0DD, 0xCDA1F604, 0xC960EBB3, -1119974018, 0xB9FF90C9, -1262701040, 0xB07DABA7, 0xAE3AFBA2, 0xAAFBE615, 0xA7B8C0CC, 0xA379DD7B, -1690935098, 0x9FF77D71, 0x92B45BA8, -1770699233, -2009983462, 0x8CF30BAD, -2119160460, 0x857130C3, 0x5D8A9099, 0x594B8D2E, 0x5408ABF7, 0x50C9B640, 1317987909, 0x4A4FFBF2, 0x470CDD2B, 0x43CDC09C, 2072149281, 0x7F436096, 0x7200464F, 0x76C15BF8, 0x68860BFD, 1816598090, 0x61043093, 1707420964, 295390185, 0x155A565E, 404320391, 0x1CD86D30, 0x29F3D35, 106832002, 0xB1D065B, 266083308, 0x3793A651, 861060070, 0x3E119D3F, 0x3AD08088, 0x2497D08D, 0x2056CD3A, 0x2D15EBE3, 0x29D4F654, -978770311, -1050133554, 0xCC2B1D17, 0xC8EA00A0, 0xD6AD50A5, 0xD26C4D12, 0xDF2F6BCB, -605129092, 0xE3A1CBC1, -413084042, 0xEA23F0AF, -287118056, 0xF0A5BD1D, 0xF464A0AA, -114850189, 0xFDE69BC4, 0x89B8FD09, 0x8D79E0BE, 0x803AC667, 0x84FBDBD0, 0x9ABC8BD5, -1635936670, -1824608069, 0x97FFAD0C, 0xAFB010B1, 0xAB710D06, -1506661409, 0xA2F33668, -1129027987, -1200260134, 0xB5365D03, 0xB1F740B4};
    }

    public static float zza(float f, float f1, float f2) {
        return Math.max(0.1f, Math.min(f, 8.0f));
    }

    public static int zza(long[] arr_v, long v, boolean z, boolean z1) {
        int v2;
        int v1 = Arrays.binarySearch(arr_v, v);
        if(v1 < 0) {
            v2 = -(v1 + 2);
            return z1 ? Math.max(0, v2) : v2;
        }
        do {
            --v1;
        }
        while(v1 >= 0 && arr_v[v1] == v);
        v2 = v1 + 1;
        return z1 ? Math.max(0, v2) : v2;
    }

    public static long zza(long v, long v1, long v2) {
        if(v2 >= v1 && v2 % v1 == 0L) {
            return v / (v2 / v1);
        }
        return v2 >= v1 || v1 % v2 != 0L ? ((long)(((double)v) * (((double)v1) / ((double)v2)))) : v * (v1 / v2);
    }

    public static String zza(Object[] arr_object) {
        StringBuilder stringBuilder0 = new StringBuilder();
        for(int v = 0; v < arr_object.length; ++v) {
            stringBuilder0.append(arr_object[v].getClass().getSimpleName());
            if(v < arr_object.length - 1) {
                stringBuilder0.append(", ");
            }
        }
        return stringBuilder0.toString();
    }

    public static void zza(zzno zzno0) {
        if(zzno0 != null) {
            try {
                zzno0.close();
            }
            catch(IOException unused_ex) {
            }
        }
    }

    public static void zza(long[] arr_v, long v, long v1) {
        int v2 = 0;
        if(v1 >= 1000000L && v1 % 1000000L == 0L) {
            while(v2 < arr_v.length) {
                arr_v[v2] /= v1 / 1000000L;
                ++v2;
            }
            return;
        }
        if(v1 < 1000000L && 1000000L % v1 == 0L) {
            while(v2 < arr_v.length) {
                arr_v[v2] *= 1000000L / v1;
                ++v2;
            }
            return;
        }
        while(v2 < arr_v.length) {
            arr_v[v2] = (long)(((double)arr_v[v2]) * (1000000.0 / ((double)v1)));
            ++v2;
        }
    }

    public static boolean zza(Object object0, Object object1) {
        return object0 == null ? object1 == null : object0.equals(object1);
    }

    public static int zzb(long[] arr_v, long v, boolean z, boolean z1) {
        int v2;
        int v1 = Arrays.binarySearch(arr_v, v);
        if(v1 < 0) {
            v2 = ~v1;
            return z1 ? Math.min(arr_v.length - 1, v2) : v2;
        }
        do {
            ++v1;
        }
        while(v1 < arr_v.length && arr_v[v1] == v);
        v2 = z ? v1 - 1 : v1;
        return z1 ? Math.min(arr_v.length - 1, v2) : v2;
    }

    public static int zzbk(int v) {
        switch(v) {
            case 8: {
                return 3;
            }
            case 16: {
                return 2;
            }
            case 24: {
                return 0x80000000;
            }
            case 0x20: {
                return 0x40000000;
            }
            default: {
                return 0;
            }
        }
    }

    public static int zzbl(int v) {
        switch(v) {
            case 0: {
                return 0x1000000;
            }
            case 1: {
                return 0x360000;
            }
            case 2: {
                return 0xC80000;
            }
            case 3: {
                return 0x20000;
            }
            case 4: {
                return 0x20000;
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }

    public static ExecutorService zzbl(String s) {
        return Executors.newSingleThreadExecutor(new zzos(s));
    }

    public static String zzbm(String s) {
        return s == null ? null : new Locale(s).getLanguage();
    }

    public static byte[] zzbn(String s) {
        return s.getBytes(Charset.defaultCharset());
    }

    public static int zzbo(String s) {
        int v = s.length();
        zzob.checkArgument(v <= 4);
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 = v2 << 8 | s.charAt(v1);
        }
        return v2;
    }

    public static byte[] zzbp(String s) {
        byte[] arr_b = new byte[s.length() / 2];
        for(int v = 0; v < arr_b.length; ++v) {
            arr_b[v] = (byte)((Character.digit(s.charAt(v << 1), 16) << 4) + Character.digit(s.charAt((v << 1) + 1), 16));
        }
        return arr_b;
    }

    public static int zzd(int v, int v1, int v2) {
        return Math.max(v1, Math.min(v, v2));
    }

    public static int zzf(int v, int v1) [...] // Inlined contents

    public static int zzg(int v, int v1) {
        switch(v) {
            case 0x80000000: {
                return v1 * 3;
            }
            case 2: {
                return v1 << 1;
            }
            case 3: {
                return v1;
            }
            case 0x40000000: {
                return v1 << 2;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
}

