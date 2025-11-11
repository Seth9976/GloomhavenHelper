package com.google.android.gms.internal.ads;

import android.util.Base64;

public final class zzci {
    // 去混淆评级： 低(20)
    public static String zza(byte[] arr_b, boolean z) {
        return z ? Base64.encodeToString(arr_b, 11) : Base64.encodeToString(arr_b, 2);
    }

    public static byte[] zza(String s, boolean z) throws IllegalArgumentException {
        byte[] arr_b = Base64.decode(s, (z ? 11 : 2));
        if(arr_b.length == 0 && s.length() > 0) {
            String s1 = String.valueOf(s);
            throw new IllegalArgumentException((s1.length() == 0 ? new String("Unable to decode ") : "Unable to decode " + s1));
        }
        return arr_b;
    }
}

