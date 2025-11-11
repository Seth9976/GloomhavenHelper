package com.google.android.gms.internal.ads;

import java.security.SecureRandom;

public final class zzdwq {
    private static final ThreadLocal zzhnf;

    static {
        zzdwq.zzhnf = new zzdwt();
    }

    private static SecureRandom zzbae() {
        SecureRandom secureRandom0 = new SecureRandom();
        secureRandom0.nextLong();
        return secureRandom0;
    }

    public static byte[] zzey(int v) {
        byte[] arr_b = new byte[v];
        ((SecureRandom)zzdwq.zzhnf.get()).nextBytes(arr_b);
        return arr_b;
    }
}

