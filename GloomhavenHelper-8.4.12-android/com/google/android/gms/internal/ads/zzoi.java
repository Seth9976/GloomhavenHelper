package com.google.android.gms.internal.ads;

public final class zzoi {
    public static boolean zzbi(String s) {
        return "audio".equals(zzoi.zzbk(s));
    }

    public static boolean zzbj(String s) {
        return "video".equals(zzoi.zzbk(s));
    }

    private static String zzbk(String s) {
        if(s == null) {
            return null;
        }
        int v = s.indexOf(0x2F);
        if(v == -1) {
            String s1 = String.valueOf(s);
            throw new IllegalArgumentException((s1.length() == 0 ? new String("Invalid mime type: ") : "Invalid mime type: " + s1));
        }
        return s.substring(0, v);
    }
}

