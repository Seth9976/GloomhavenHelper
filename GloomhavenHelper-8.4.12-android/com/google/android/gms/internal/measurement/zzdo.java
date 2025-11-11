package com.google.android.gms.internal.measurement;

final class zzdo {
    private static final Class zza;
    private static final boolean zzb;

    static {
        zzdo.zza = zzdo.zza("libcore.io.Memory");
        zzdo.zzb = zzdo.zza("org.robolectric.Robolectric") != null;
    }

    private static Class zza(String s) {
        try {
            return Class.forName(s);
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    static boolean zza() [...] // 潜在的解密器

    static Class zzb() {
        return zzdo.zza;
    }
}

