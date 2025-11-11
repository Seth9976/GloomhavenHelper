package androidx.core.content.res;

import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.R.styleable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
final class GradientColorInflaterCompat {
    static final class ColorStops {
        final int[] mColors;
        final float[] mOffsets;

        ColorStops(@ColorInt int v, @ColorInt int v1) {
            this.mColors = new int[]{v, v1};
            this.mOffsets = new float[]{0.0f, 1.0f};
        }

        ColorStops(@ColorInt int v, @ColorInt int v1, @ColorInt int v2) {
            this.mColors = new int[]{v, v1, v2};
            this.mOffsets = new float[]{0.0f, 0.5f, 1.0f};
        }

        ColorStops(@NonNull List list0, @NonNull List list1) {
            int v = list0.size();
            this.mColors = new int[v];
            this.mOffsets = new float[v];
            for(int v1 = 0; v1 < v; ++v1) {
                this.mColors[v1] = (int)(((Integer)list0.get(v1)));
                this.mOffsets[v1] = (float)(((Float)list1.get(v1)));
            }
        }
    }

    private static final int TILE_MODE_CLAMP = 0;
    private static final int TILE_MODE_MIRROR = 2;
    private static final int TILE_MODE_REPEAT = 1;

    private static ColorStops checkColors(@Nullable ColorStops gradientColorInflaterCompat$ColorStops0, @ColorInt int v, @ColorInt int v1, boolean z, @ColorInt int v2) {
        if(gradientColorInflaterCompat$ColorStops0 != null) {
            return gradientColorInflaterCompat$ColorStops0;
        }
        return z ? new ColorStops(v, v2, v1) : new ColorStops(v, v1);
    }

    static Shader createFromXml(@NonNull Resources resources0, @NonNull XmlPullParser xmlPullParser0, @Nullable Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        AttributeSet attributeSet0 = Xml.asAttributeSet(xmlPullParser0);
        do {
            int v = xmlPullParser0.next();
        }
        while(v != 1 && v != 2);
        if(v != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return GradientColorInflaterCompat.createFromXmlInner(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
    }

    static Shader createFromXmlInner(@NonNull Resources resources0, @NonNull XmlPullParser xmlPullParser0, @NonNull AttributeSet attributeSet0, @Nullable Resources.Theme resources$Theme0) throws IOException, XmlPullParserException {
        String s = xmlPullParser0.getName();
        if(s.equals("gradient")) {
            TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, styleable.GradientColor);
            float f = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "startX", styleable.GradientColor_android_startX, 0.0f);
            float f1 = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "startY", styleable.GradientColor_android_startY, 0.0f);
            float f2 = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "endX", styleable.GradientColor_android_endX, 0.0f);
            float f3 = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "endY", styleable.GradientColor_android_endY, 0.0f);
            float f4 = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "centerX", styleable.GradientColor_android_centerX, 0.0f);
            float f5 = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "centerY", styleable.GradientColor_android_centerY, 0.0f);
            int v = TypedArrayUtils.getNamedInt(typedArray0, xmlPullParser0, "type", styleable.GradientColor_android_type, 0);
            int v1 = TypedArrayUtils.getNamedColor(typedArray0, xmlPullParser0, "startColor", styleable.GradientColor_android_startColor, 0);
            boolean z = TypedArrayUtils.hasAttribute(xmlPullParser0, "centerColor");
            int v2 = TypedArrayUtils.getNamedColor(typedArray0, xmlPullParser0, "centerColor", styleable.GradientColor_android_centerColor, 0);
            int v3 = TypedArrayUtils.getNamedColor(typedArray0, xmlPullParser0, "endColor", styleable.GradientColor_android_endColor, 0);
            int v4 = TypedArrayUtils.getNamedInt(typedArray0, xmlPullParser0, "tileMode", styleable.GradientColor_android_tileMode, 0);
            float f6 = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "gradientRadius", styleable.GradientColor_android_gradientRadius, 0.0f);
            typedArray0.recycle();
            ColorStops gradientColorInflaterCompat$ColorStops0 = GradientColorInflaterCompat.checkColors(GradientColorInflaterCompat.inflateChildElements(resources0, xmlPullParser0, attributeSet0, resources$Theme0), v1, v3, z, v2);
            switch(v) {
                case 1: {
                    if(f6 <= 0.0f) {
                        throw new XmlPullParserException("<gradient> tag requires \'gradientRadius\' attribute with radial type");
                    }
                    Shader.TileMode shader$TileMode1 = GradientColorInflaterCompat.parseTileMode(v4);
                    return new RadialGradient(f4, f5, f6, gradientColorInflaterCompat$ColorStops0.mColors, gradientColorInflaterCompat$ColorStops0.mOffsets, shader$TileMode1);
                }
                case 2: {
                    return new SweepGradient(f4, f5, gradientColorInflaterCompat$ColorStops0.mColors, gradientColorInflaterCompat$ColorStops0.mOffsets);
                }
                default: {
                    Shader.TileMode shader$TileMode0 = GradientColorInflaterCompat.parseTileMode(v4);
                    return new LinearGradient(f, f1, f2, f3, gradientColorInflaterCompat$ColorStops0.mColors, gradientColorInflaterCompat$ColorStops0.mOffsets, shader$TileMode0);
                }
            }
        }
        throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": invalid gradient color tag " + s);
    }

    private static ColorStops inflateChildElements(@NonNull Resources resources0, @NonNull XmlPullParser xmlPullParser0, @NonNull AttributeSet attributeSet0, @Nullable Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        int v = xmlPullParser0.getDepth();
        ArrayList arrayList0 = new ArrayList(20);
        ArrayList arrayList1 = new ArrayList(20);
        int v1;
        while((v1 = xmlPullParser0.next()) != 1) {
            int v2 = xmlPullParser0.getDepth();
            if(v2 < v + 1 && v1 == 3) {
                break;
            }
            if(v1 == 2 && v2 <= v + 1 && xmlPullParser0.getName().equals("item")) {
                TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, styleable.GradientColorItem);
                if(!typedArray0.hasValue(styleable.GradientColorItem_android_color) || !typedArray0.hasValue(styleable.GradientColorItem_android_offset)) {
                    throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": <item> tag requires a \'color\' attribute and a \'offset\' " + "attribute!");
                }
                int v3 = typedArray0.getColor(styleable.GradientColorItem_android_color, 0);
                float f = typedArray0.getFloat(styleable.GradientColorItem_android_offset, 0.0f);
                typedArray0.recycle();
                arrayList1.add(v3);
                arrayList0.add(f);
            }
        }
        return arrayList1.size() <= 0 ? null : new ColorStops(arrayList1, arrayList0);
    }

    private static Shader.TileMode parseTileMode(int v) {
        switch(v) {
            case 1: {
                return Shader.TileMode.REPEAT;
            }
            case 2: {
                return Shader.TileMode.MIRROR;
            }
            default: {
                return Shader.TileMode.CLAMP;
            }
        }
    }
}

