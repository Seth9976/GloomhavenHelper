package androidx.core.util;

import android.text.TextUtils;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.util.Collection;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
public class Preconditions {
    public static void checkArgument(boolean z) {
        if(!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, Object object0) {
        if(!z) {
            throw new IllegalArgumentException(String.valueOf(object0));
        }
    }

    public static float checkArgumentFinite(float f, String s) {
        if(Float.isNaN(f)) {
            throw new IllegalArgumentException(s + " must not be NaN");
        }
        if(Float.isInfinite(f)) {
            throw new IllegalArgumentException(s + " must not be infinite");
        }
        return f;
    }

    public static float checkArgumentInRange(float f, float f1, float f2, String s) {
        if(Float.isNaN(f)) {
            throw new IllegalArgumentException(s + " must not be NaN");
        }
        if(f < f1) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", s, f1, f2));
        }
        if(f > f2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", s, f1, f2));
        }
        return f;
    }

    public static int checkArgumentInRange(int v, int v1, int v2, String s) {
        if(v < v1) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", s, v1, v2));
        }
        if(v > v2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", s, v1, v2));
        }
        return v;
    }

    public static long checkArgumentInRange(long v, long v1, long v2, String s) {
        if(v < v1) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", s, v1, v2));
        }
        if(v > v2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", s, v1, v2));
        }
        return v;
    }

    @IntRange(from = 0L)
    public static int checkArgumentNonnegative(int v) {
        if(v < 0) {
            throw new IllegalArgumentException();
        }
        return v;
    }

    @IntRange(from = 0L)
    public static int checkArgumentNonnegative(int v, String s) {
        if(v < 0) {
            throw new IllegalArgumentException(s);
        }
        return v;
    }

    public static long checkArgumentNonnegative(long v) {
        if(v < 0L) {
            throw new IllegalArgumentException();
        }
        return v;
    }

    public static long checkArgumentNonnegative(long v, String s) {
        if(v < 0L) {
            throw new IllegalArgumentException(s);
        }
        return v;
    }

    public static int checkArgumentPositive(int v, String s) {
        if(v <= 0) {
            throw new IllegalArgumentException(s);
        }
        return v;
    }

    public static float[] checkArrayElementsInRange(float[] arr_f, float f, float f1, String s) {
        Preconditions.checkNotNull(arr_f, s + " must not be null");
        for(int v = 0; v < arr_f.length; ++v) {
            float f2 = arr_f[v];
            if(Float.isNaN(f2)) {
                throw new IllegalArgumentException(s + "[" + v + "] must not be NaN");
            }
            if(f2 < f) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too low)", s, v, f, f1));
            }
            if(f2 > f1) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too high)", s, v, f, f1));
            }
        }
        return arr_f;
    }

    public static Object[] checkArrayElementsNotNull(Object[] arr_object, String s) {
        if(arr_object == null) {
            throw new NullPointerException(s + " must not be null");
        }
        for(int v = 0; v < arr_object.length; ++v) {
            if(arr_object[v] == null) {
                throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", s, v));
            }
        }
        return arr_object;
    }

    @NonNull
    public static Collection checkCollectionElementsNotNull(Collection collection0, String s) {
        if(collection0 == null) {
            throw new NullPointerException(s + " must not be null");
        }
        long v = 0L;
        for(Object object0: collection0) {
            if(object0 == null) {
                throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", s, v));
            }
            ++v;
        }
        return collection0;
    }

    public static Collection checkCollectionNotEmpty(Collection collection0, String s) {
        if(collection0 == null) {
            throw new NullPointerException(s + " must not be null");
        }
        if(collection0.isEmpty()) {
            throw new IllegalArgumentException(s + " is empty");
        }
        return collection0;
    }

    public static int checkFlagsArgument(int v, int v1) {
        if((v & v1) != v) {
            throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(v) + ", but only 0x" + Integer.toHexString(v1) + " are allowed");
        }
        return v;
    }

    @NonNull
    public static Object checkNotNull(Object object0) {
        if(object0 == null) {
            throw new NullPointerException();
        }
        return object0;
    }

    @NonNull
    public static Object checkNotNull(Object object0, Object object1) {
        if(object0 == null) {
            throw new NullPointerException(String.valueOf(object1));
        }
        return object0;
    }

    public static void checkState(boolean z) {
        Preconditions.checkState(z, null);
    }

    public static void checkState(boolean z, String s) {
        if(!z) {
            throw new IllegalStateException(s);
        }
    }

    @NonNull
    public static CharSequence checkStringNotEmpty(CharSequence charSequence0) {
        if(TextUtils.isEmpty(charSequence0)) {
            throw new IllegalArgumentException();
        }
        return charSequence0;
    }

    @NonNull
    public static CharSequence checkStringNotEmpty(CharSequence charSequence0, Object object0) {
        if(TextUtils.isEmpty(charSequence0)) {
            throw new IllegalArgumentException(String.valueOf(object0));
        }
        return charSequence0;
    }
}

