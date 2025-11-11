package androidx.core.text;

import android.os.Build.VERSION;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Locale;

public final class TextUtilsCompat {
    private static final String ARAB_SCRIPT_SUBTAG = "Arab";
    private static final String HEBR_SCRIPT_SUBTAG = "Hebr";
    private static final Locale ROOT;

    static {
        TextUtilsCompat.ROOT = new Locale("", "");
    }

    private static int getLayoutDirectionFromFirstChar(@NonNull Locale locale0) {
        switch(Character.getDirectionality(locale0.getDisplayName(locale0).charAt(0))) {
            case 1: 
            case 2: {
                return 1;
            }
            default: {
                return 0;
            }
        }
    }

    public static int getLayoutDirectionFromLocale(@Nullable Locale locale0) {
        if(Build.VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(locale0);
        }
        if(locale0 != null && !locale0.equals(TextUtilsCompat.ROOT)) {
            String s = ICUCompat.maximizeAndGetScript(locale0);
            if(s == null) {
                return TextUtilsCompat.getLayoutDirectionFromFirstChar(locale0);
            }
            return !s.equalsIgnoreCase("Arab") && !s.equalsIgnoreCase("Hebr") ? 0 : 1;
        }
        return 0;
    }

    @NonNull
    public static String htmlEncode(@NonNull String s) {
        if(Build.VERSION.SDK_INT >= 17) {
            return TextUtils.htmlEncode(s);
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        for(int v = 0; v < s.length(); ++v) {
            int v1 = s.charAt(v);
            switch(v1) {
                case 34: {
                    stringBuilder0.append("&quot;");
                    break;
                }
                case 38: {
                    stringBuilder0.append("&amp;");
                    break;
                }
                case 39: {
                    stringBuilder0.append("&#39;");
                    break;
                }
                case 60: {
                    stringBuilder0.append("&lt;");
                    break;
                }
                case 62: {
                    stringBuilder0.append("&gt;");
                    break;
                }
                default: {
                    stringBuilder0.append(((char)v1));
                }
            }
        }
        return stringBuilder0.toString();
    }
}

