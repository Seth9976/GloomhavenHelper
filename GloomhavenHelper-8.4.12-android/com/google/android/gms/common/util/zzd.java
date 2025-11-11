package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
public final class zzd {
    private static final Pattern zzhi;

    static {
        zzd.zzhi = Pattern.compile("\\\\u[0-9a-fA-F]{4}");
    }

    public static String unescape(String s) {
        if(!TextUtils.isEmpty(s)) {
            Matcher matcher0 = zzd.zzhi.matcher(s);
            StringBuffer stringBuffer0 = null;
            while(matcher0.find()) {
                if(stringBuffer0 == null) {
                    stringBuffer0 = new StringBuffer();
                }
                matcher0.appendReplacement(stringBuffer0, new String(Character.toChars(Integer.parseInt(matcher0.group().substring(2), 16))));
            }
            if(stringBuffer0 == null) {
                return s;
            }
            matcher0.appendTail(stringBuffer0);
            return stringBuffer0.toString();
        }
        return s;
    }
}

