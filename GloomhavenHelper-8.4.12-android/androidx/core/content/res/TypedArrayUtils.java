package androidx.core.content.res;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.annotation.AnyRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleableRes;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypedArrayUtils {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    public static int getAttr(@NonNull Context context0, int v, int v1) {
        TypedValue typedValue0 = new TypedValue();
        context0.getTheme().resolveAttribute(v, typedValue0, true);
        return typedValue0.resourceId == 0 ? v1 : v;
    }

    public static boolean getBoolean(@NonNull TypedArray typedArray0, @StyleableRes int v, @StyleableRes int v1, boolean z) {
        return typedArray0.getBoolean(v, typedArray0.getBoolean(v1, z));
    }

    @Nullable
    public static Drawable getDrawable(@NonNull TypedArray typedArray0, @StyleableRes int v, @StyleableRes int v1) {
        Drawable drawable0 = typedArray0.getDrawable(v);
        return drawable0 == null ? typedArray0.getDrawable(v1) : drawable0;
    }

    public static int getInt(@NonNull TypedArray typedArray0, @StyleableRes int v, @StyleableRes int v1, int v2) {
        return typedArray0.getInt(v, typedArray0.getInt(v1, v2));
    }

    public static boolean getNamedBoolean(@NonNull TypedArray typedArray0, @NonNull XmlPullParser xmlPullParser0, @NonNull String s, @StyleableRes int v, boolean z) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getBoolean(v, z) : z;
    }

    @ColorInt
    public static int getNamedColor(@NonNull TypedArray typedArray0, @NonNull XmlPullParser xmlPullParser0, @NonNull String s, @StyleableRes int v, @ColorInt int v1) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getColor(v, v1) : v1;
    }

    public static ComplexColorCompat getNamedComplexColor(@NonNull TypedArray typedArray0, @NonNull XmlPullParser xmlPullParser0, @Nullable Resources.Theme resources$Theme0, @NonNull String s, @StyleableRes int v, @ColorInt int v1) {
        if(TypedArrayUtils.hasAttribute(xmlPullParser0, s)) {
            TypedValue typedValue0 = new TypedValue();
            typedArray0.getValue(v, typedValue0);
            if(typedValue0.type >= 28 && typedValue0.type <= 0x1F) {
                return ComplexColorCompat.from(typedValue0.data);
            }
            ComplexColorCompat complexColorCompat0 = ComplexColorCompat.inflate(typedArray0.getResources(), typedArray0.getResourceId(v, 0), resources$Theme0);
            return complexColorCompat0 == null ? ComplexColorCompat.from(v1) : complexColorCompat0;
        }
        return ComplexColorCompat.from(v1);
    }

    public static float getNamedFloat(@NonNull TypedArray typedArray0, @NonNull XmlPullParser xmlPullParser0, @NonNull String s, @StyleableRes int v, float f) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getFloat(v, f) : f;
    }

    public static int getNamedInt(@NonNull TypedArray typedArray0, @NonNull XmlPullParser xmlPullParser0, @NonNull String s, @StyleableRes int v, int v1) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getInt(v, v1) : v1;
    }

    @AnyRes
    public static int getNamedResourceId(@NonNull TypedArray typedArray0, @NonNull XmlPullParser xmlPullParser0, @NonNull String s, @StyleableRes int v, @AnyRes int v1) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getResourceId(v, v1) : v1;
    }

    @Nullable
    public static String getNamedString(@NonNull TypedArray typedArray0, @NonNull XmlPullParser xmlPullParser0, @NonNull String s, @StyleableRes int v) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getString(v) : null;
    }

    @AnyRes
    public static int getResourceId(@NonNull TypedArray typedArray0, @StyleableRes int v, @StyleableRes int v1, @AnyRes int v2) {
        return typedArray0.getResourceId(v, typedArray0.getResourceId(v1, v2));
    }

    @Nullable
    public static String getString(@NonNull TypedArray typedArray0, @StyleableRes int v, @StyleableRes int v1) {
        String s = typedArray0.getString(v);
        return s == null ? typedArray0.getString(v1) : s;
    }

    @Nullable
    public static CharSequence getText(@NonNull TypedArray typedArray0, @StyleableRes int v, @StyleableRes int v1) {
        CharSequence charSequence0 = typedArray0.getText(v);
        return charSequence0 == null ? typedArray0.getText(v1) : charSequence0;
    }

    @Nullable
    public static CharSequence[] getTextArray(@NonNull TypedArray typedArray0, @StyleableRes int v, @StyleableRes int v1) {
        CharSequence[] arr_charSequence = typedArray0.getTextArray(v);
        return arr_charSequence == null ? typedArray0.getTextArray(v1) : arr_charSequence;
    }

    public static boolean hasAttribute(@NonNull XmlPullParser xmlPullParser0, @NonNull String s) {
        return xmlPullParser0.getAttributeValue("http://schemas.android.com/apk/res/android", s) != null;
    }

    @NonNull
    public static TypedArray obtainAttributes(@NonNull Resources resources0, @Nullable Resources.Theme resources$Theme0, @NonNull AttributeSet attributeSet0, @NonNull int[] arr_v) {
        return resources$Theme0 == null ? resources0.obtainAttributes(attributeSet0, arr_v) : resources$Theme0.obtainStyledAttributes(attributeSet0, arr_v, 0, 0);
    }

    @Nullable
    public static TypedValue peekNamedValue(@NonNull TypedArray typedArray0, @NonNull XmlPullParser xmlPullParser0, @NonNull String s, int v) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.peekValue(v) : null;
    }
}

