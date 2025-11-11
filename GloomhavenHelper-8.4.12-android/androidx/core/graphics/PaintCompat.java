package androidx.core.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;
import androidx.core.util.Pair;

public final class PaintCompat {
    private static final String EM_STRING = "m";
    private static final String TOFU_STRING = "\uDB3F\uDFFD";
    private static final ThreadLocal sRectThreadLocal;

    static {
        PaintCompat.sRectThreadLocal = new ThreadLocal();
    }

    public static boolean hasGlyph(@NonNull Paint paint0, @NonNull String s) {
        if(Build.VERSION.SDK_INT >= 23) {
            return paint0.hasGlyph(s);
        }
        int v = s.length();
        if(v == 1 && Character.isWhitespace(s.charAt(0))) {
            return true;
        }
        float f = paint0.measureText("\uDB3F\uDFFD");
        float f1 = paint0.measureText("m");
        float f2 = paint0.measureText(s);
        float f3 = 0.0f;
        if(f2 == 0.0f) {
            return false;
        }
        if(s.codePointCount(0, s.length()) > 1) {
            if(f2 > f1 * 2.0f) {
                return false;
            }
            for(int v1 = 0; v1 < v; v1 = v2) {
                int v2 = Character.charCount(s.codePointAt(v1)) + v1;
                f3 += paint0.measureText(s, v1, v2);
            }
            if(f2 >= f3) {
                return false;
            }
        }
        if(f2 != f) {
            return true;
        }
        Pair pair0 = PaintCompat.obtainEmptyRects();
        paint0.getTextBounds("\uDB3F\uDFFD", 0, 2, ((Rect)pair0.first));
        paint0.getTextBounds(s, 0, v, ((Rect)pair0.second));
        return !((Rect)pair0.first).equals(pair0.second);
    }

    private static Pair obtainEmptyRects() {
        Pair pair0 = (Pair)PaintCompat.sRectThreadLocal.get();
        if(pair0 == null) {
            pair0 = new Pair(new Rect(), new Rect());
            PaintCompat.sRectThreadLocal.set(pair0);
            return pair0;
        }
        ((Rect)pair0.first).setEmpty();
        ((Rect)pair0.second).setEmpty();
        return pair0;
    }
}

