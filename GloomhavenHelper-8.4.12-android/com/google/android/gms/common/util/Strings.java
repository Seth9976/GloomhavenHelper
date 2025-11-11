package com.google.android.gms.common.util;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.regex.Pattern;

@KeepForSdk
@VisibleForTesting
public class Strings {
    private static final Pattern zzhh;

    static {
        Strings.zzhh = Pattern.compile("\\$\\{(.*?)\\}");
    }

    // 去混淆评级： 低(20)
    @Nullable
    @KeepForSdk
    public static String emptyToNull(@Nullable String s) {
        return TextUtils.isEmpty(s) ? null : s;
    }

    @KeepForSdk
    public static boolean isEmptyOrWhitespace(@Nullable String s) {
        return s == null || s.trim().isEmpty();
    }
}

