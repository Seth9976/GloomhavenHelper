package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public class NumberUtils {
    @KeepForSdk
    public static long parseHexLong(String s) {
        if(s.length() > 16) {
            throw new NumberFormatException("Invalid input: " + s + " exceeds 16 characters");
        }
        if(s.length() == 16) {
            long v = Long.parseLong(s.substring(8), 16);
            return Long.parseLong(s.substring(0, 8), 16) << 0x20 | v;
        }
        return Long.parseLong(s, 16);
    }
}

