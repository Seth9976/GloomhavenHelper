package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzdlh {
    @NullableDecl
    public static String emptyToNull(@NullableDecl String s) {
        return zzdlc.emptyToNull(s);
    }

    private static String zzad(@NullableDecl Object object0) {
        return String.valueOf(object0);
    }

    public static String zzb(@NullableDecl String s, @NullableDecl Object[] arr_object) {
        String s1 = String.valueOf(s);
        int v = 0;
        for(int v1 = 0; v1 < arr_object.length; ++v1) {
            arr_object[v1] = zzdlh.zzad(arr_object[v1]);
        }
        StringBuilder stringBuilder0 = new StringBuilder(s1.length() + arr_object.length * 16);
        int v2 = 0;
        while(v < arr_object.length) {
            int v3 = s1.indexOf("%s", v2);
            if(v3 == -1) {
                break;
            }
            stringBuilder0.append(s1, v2, v3);
            stringBuilder0.append(arr_object[v]);
            v2 = v3 + 2;
            ++v;
        }
        stringBuilder0.append(s1, v2, s1.length());
        if(v < arr_object.length) {
            stringBuilder0.append(" [");
            int v4 = v + 1;
            stringBuilder0.append(arr_object[v]);
            while(v4 < arr_object.length) {
                stringBuilder0.append(", ");
                stringBuilder0.append(arr_object[v4]);
                ++v4;
            }
            stringBuilder0.append(']');
        }
        return stringBuilder0.toString();
    }
}

