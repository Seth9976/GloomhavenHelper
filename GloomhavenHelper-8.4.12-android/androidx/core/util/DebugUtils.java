package androidx.core.util;

import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY_GROUP})
public class DebugUtils {
    public static void buildShortClassTag(Object object0, StringBuilder stringBuilder0) {
        if(object0 == null) {
            stringBuilder0.append("null");
            return;
        }
        String s = object0.getClass().getSimpleName();
        if(s == null || s.length() <= 0) {
            s = object0.getClass().getName();
            int v = s.lastIndexOf(46);
            if(v > 0) {
                s = s.substring(v + 1);
            }
        }
        stringBuilder0.append(s);
        stringBuilder0.append('{');
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(object0)));
    }
}

