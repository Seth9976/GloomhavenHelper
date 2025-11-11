package androidx.core.os;

import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
final class LocaleHelper {
    static Locale forLanguageTag(String s) {
        if(s.contains("-")) {
            String[] arr_s = s.split("-", -1);
            if(arr_s.length > 2) {
                return new Locale(arr_s[0], arr_s[1], arr_s[2]);
            }
            if(arr_s.length > 1) {
                return new Locale(arr_s[0], arr_s[1]);
            }
            if(arr_s.length != 1) {
                throw new IllegalArgumentException("Can not parse language tag: [" + s + "]");
            }
            return new Locale(arr_s[0]);
        }
        if(s.contains("_")) {
            String[] arr_s1 = s.split("_", -1);
            if(arr_s1.length > 2) {
                return new Locale(arr_s1[0], arr_s1[1], arr_s1[2]);
            }
            if(arr_s1.length > 1) {
                return new Locale(arr_s1[0], arr_s1[1]);
            }
            if(arr_s1.length != 1) {
                throw new IllegalArgumentException("Can not parse language tag: [" + s + "]");
            }
            return new Locale(arr_s1[0]);
        }
        return new Locale(s);
    }

    static String toLanguageTag(Locale locale0) {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(locale0.getLanguage());
        String s = locale0.getCountry();
        if(s != null && !s.isEmpty()) {
            stringBuilder0.append("-");
            stringBuilder0.append(locale0.getCountry());
        }
        return stringBuilder0.toString();
    }
}

