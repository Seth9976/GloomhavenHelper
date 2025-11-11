package androidx.core.content;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public final class MimeTypeFilter {
    @Nullable
    public static String matches(@Nullable String s, @NonNull String[] arr_s) {
        if(s == null) {
            return null;
        }
        String[] arr_s1 = s.split("/");
        for(int v = 0; v < arr_s.length; ++v) {
            String s1 = arr_s[v];
            if(MimeTypeFilter.mimeTypeAgainstFilter(arr_s1, s1.split("/"))) {
                return s1;
            }
        }
        return null;
    }

    @Nullable
    public static String matches(@Nullable String[] arr_s, @NonNull String s) {
        if(arr_s == null) {
            return null;
        }
        String[] arr_s1 = s.split("/");
        for(int v = 0; v < arr_s.length; ++v) {
            String s1 = arr_s[v];
            if(MimeTypeFilter.mimeTypeAgainstFilter(s1.split("/"), arr_s1)) {
                return s1;
            }
        }
        return null;
    }

    public static boolean matches(@Nullable String s, @NonNull String s1) {
        return s == null ? false : MimeTypeFilter.mimeTypeAgainstFilter(s.split("/"), s1.split("/"));
    }

    @NonNull
    public static String[] matchesMany(@Nullable String[] arr_s, @NonNull String s) {
        if(arr_s == null) {
            return new String[0];
        }
        ArrayList arrayList0 = new ArrayList();
        String[] arr_s1 = s.split("/");
        for(int v = 0; v < arr_s.length; ++v) {
            String s1 = arr_s[v];
            if(MimeTypeFilter.mimeTypeAgainstFilter(s1.split("/"), arr_s1)) {
                arrayList0.add(s1);
            }
        }
        return (String[])arrayList0.toArray(new String[arrayList0.size()]);
    }

    private static boolean mimeTypeAgainstFilter(@NonNull String[] arr_s, @NonNull String[] arr_s1) {
        if(arr_s1.length != 2) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype.");
        }
        if(arr_s1[0].isEmpty() || arr_s1[1].isEmpty()) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty.");
        }
        if(arr_s.length != 2) {
            return false;
        }
        return "*".equals(arr_s1[0]) || arr_s1[0].equals(arr_s[0]) ? "*".equals(arr_s1[1]) || arr_s1[1].equals(arr_s[1]) : false;
    }
}

