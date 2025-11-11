package com.google.android.gms.internal.ads;

final class zzdxk {
    private static final Class zzhnw;
    private static final boolean zzhnx;

    static {
        zzdxk.zzhnw = zzdxk.zzhh("libcore.io.Memory");
        zzdxk.zzhnx = zzdxk.zzhh("org.robolectric.Robolectric") != null;
    }

    static boolean zzbap() [...] // 潜在的解密器

    static Class zzbaq() {
        return zzdxk.zzhnw;
    }

    private static Class zzhh(String s) {
        try {
            return Class.forName(s);
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }
}

