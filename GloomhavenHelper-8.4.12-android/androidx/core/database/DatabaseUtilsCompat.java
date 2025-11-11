package androidx.core.database;

import android.text.TextUtils;

@Deprecated
public final class DatabaseUtilsCompat {
    @Deprecated
    public static String[] appendSelectionArgs(String[] arr_s, String[] arr_s1) {
        if(arr_s != null && arr_s.length != 0) {
            String[] arr_s2 = new String[arr_s.length + arr_s1.length];
            System.arraycopy(arr_s, 0, arr_s2, 0, arr_s.length);
            System.arraycopy(arr_s1, 0, arr_s2, arr_s.length, arr_s1.length);
            return arr_s2;
        }
        return arr_s1;
    }

    @Deprecated
    public static String concatenateWhere(String s, String s1) {
        if(TextUtils.isEmpty(s)) {
            return s1;
        }
        return TextUtils.isEmpty(s1) ? s : "(" + s + ") AND (" + s1 + ")";
    }
}

