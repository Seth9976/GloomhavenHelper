package com.google.android.gms.common.util;

import android.util.Base64;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Base64Utils {
    @KeepForSdk
    public static byte[] decode(String s) {
        return s == null ? null : Base64.decode(s, 0);
    }

    @KeepForSdk
    public static byte[] decodeUrlSafe(String s) {
        return s == null ? null : Base64.decode(s, 10);
    }

    @KeepForSdk
    public static byte[] decodeUrlSafeNoPadding(String s) {
        return s == null ? null : Base64.decode(s, 11);
    }

    @KeepForSdk
    public static String encode(byte[] arr_b) {
        return arr_b == null ? null : Base64.encodeToString(arr_b, 0);
    }

    @KeepForSdk
    public static String encodeUrlSafe(byte[] arr_b) {
        return arr_b == null ? null : Base64.encodeToString(arr_b, 10);
    }

    @KeepForSdk
    public static String encodeUrlSafeNoPadding(byte[] arr_b) {
        return arr_b == null ? null : Base64.encodeToString(arr_b, 11);
    }
}

