package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import androidx.annotation.Nullable;

final class zzaaj extends zzaae {
    @Nullable
    private static String zzct(@Nullable String s) {
        if(TextUtils.isEmpty(s)) {
            return s;
        }
        int v = 0;
        int v1 = s.length();
        while(v < s.length() && s.charAt(v) == 44) {
            ++v;
        }
        while(v1 > 0 && s.charAt(v1 - 1) == 44) {
            --v1;
        }
        if(v1 < v) {
            return null;
        }
        return v != 0 || v1 != s.length() ? s.substring(v, v1) : s;
    }

    @Override  // com.google.android.gms.internal.ads.zzaae
    public final String zzg(@Nullable String s, String s1) {
        String s2 = zzaaj.zzct(s);
        String s3 = zzaaj.zzct(s1);
        if(TextUtils.isEmpty(s2)) {
            return s3;
        }
        return TextUtils.isEmpty(s3) ? s2 : s2 + "," + s3;
    }
}

