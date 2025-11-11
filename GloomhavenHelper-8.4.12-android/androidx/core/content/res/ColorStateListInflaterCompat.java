package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.R.attr;
import androidx.core.R.styleable;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class ColorStateListInflaterCompat {
    private static final int DEFAULT_COLOR = 0xFFFF0000;

    @NonNull
    public static ColorStateList createFromXml(@NonNull Resources resources0, @NonNull XmlPullParser xmlPullParser0, @Nullable Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        AttributeSet attributeSet0 = Xml.asAttributeSet(xmlPullParser0);
        do {
            int v = xmlPullParser0.next();
        }
        while(v != 1 && v != 2);
        if(v != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return ColorStateListInflaterCompat.createFromXmlInner(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
    }

    @NonNull
    public static ColorStateList createFromXmlInner(@NonNull Resources resources0, @NonNull XmlPullParser xmlPullParser0, @NonNull AttributeSet attributeSet0, @Nullable Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        String s = xmlPullParser0.getName();
        if(!s.equals("selector")) {
            throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": invalid color state list tag " + s);
        }
        return ColorStateListInflaterCompat.inflate(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
    }

    private static ColorStateList inflate(@NonNull Resources resources0, @NonNull XmlPullParser xmlPullParser0, @NonNull AttributeSet attributeSet0, @Nullable Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        int v = xmlPullParser0.getDepth();
        int[][] arr2_v = new int[20][];
        int[] arr_v = new int[20];
        int v1 = 0;
        int v2;
        while((v2 = xmlPullParser0.next()) != 1) {
            int v3 = xmlPullParser0.getDepth();
            if(v3 < v + 1 && v2 == 3) {
                break;
            }
            if(v2 == 2 && v3 <= v + 1 && xmlPullParser0.getName().equals("item")) {
                TypedArray typedArray0 = ColorStateListInflaterCompat.obtainAttributes(resources0, resources$Theme0, attributeSet0, styleable.ColorStateListItem);
                int v4 = typedArray0.getColor(styleable.ColorStateListItem_android_color, 0xFFFF00FF);
                float f = 1.0f;
                if(typedArray0.hasValue(styleable.ColorStateListItem_android_alpha)) {
                    f = typedArray0.getFloat(styleable.ColorStateListItem_android_alpha, 1.0f);
                }
                else if(typedArray0.hasValue(styleable.ColorStateListItem_alpha)) {
                    f = typedArray0.getFloat(styleable.ColorStateListItem_alpha, 1.0f);
                }
                typedArray0.recycle();
                int v5 = attributeSet0.getAttributeCount();
                int[] arr_v1 = new int[v5];
                int v7 = 0;
                for(int v6 = 0; v6 < v5; ++v6) {
                    int v8 = attributeSet0.getAttributeNameResource(v6);
                    if(v8 != 0x10101A5 && v8 != 0x101031F && v8 != attr.alpha) {
                        if(!attributeSet0.getAttributeBooleanValue(v6, false)) {
                            v8 = -v8;
                        }
                        arr_v1[v7] = v8;
                        ++v7;
                    }
                }
                int[] arr_v2 = StateSet.trimStateSet(arr_v1, v7);
                arr_v = GrowingArrayUtils.append(arr_v, v1, ColorStateListInflaterCompat.modulateColorAlpha(v4, f));
                arr2_v = (int[][])GrowingArrayUtils.append(arr2_v, v1, arr_v2);
                ++v1;
            }
        }
        int[] arr_v3 = new int[v1];
        int[][] arr2_v1 = new int[v1][];
        System.arraycopy(arr_v, 0, arr_v3, 0, v1);
        System.arraycopy(arr2_v, 0, arr2_v1, 0, v1);
        return new ColorStateList(arr2_v1, arr_v3);
    }

    @ColorInt
    private static int modulateColorAlpha(@ColorInt int v, @FloatRange(from = 0.0, to = 1.0) float f) {
        return v & 0xFFFFFF | Math.round(((float)Color.alpha(v)) * f) << 24;
    }

    private static TypedArray obtainAttributes(Resources resources0, Resources.Theme resources$Theme0, AttributeSet attributeSet0, int[] arr_v) {
        return resources$Theme0 == null ? resources0.obtainAttributes(attributeSet0, arr_v) : resources$Theme0.obtainStyledAttributes(attributeSet0, arr_v, 0, 0);
    }
}

