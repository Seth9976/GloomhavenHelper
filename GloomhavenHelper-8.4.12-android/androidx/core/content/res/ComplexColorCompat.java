package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class ComplexColorCompat {
    private static final String LOG_TAG = "ComplexColorCompat";
    private int mColor;
    private final ColorStateList mColorStateList;
    private final Shader mShader;

    private ComplexColorCompat(Shader shader0, ColorStateList colorStateList0, @ColorInt int v) {
        this.mShader = shader0;
        this.mColorStateList = colorStateList0;
        this.mColor = v;
    }

    @NonNull
    private static ComplexColorCompat createFromXml(@NonNull Resources resources0, @ColorRes int v, @Nullable Resources.Theme resources$Theme0) throws IOException, XmlPullParserException {
        XmlResourceParser xmlResourceParser0 = resources0.getXml(v);
        AttributeSet attributeSet0 = Xml.asAttributeSet(xmlResourceParser0);
        do {
            int v1 = xmlResourceParser0.next();
        }
        while(v1 != 1 && v1 != 2);
        if(v1 == 2) {
            String s = xmlResourceParser0.getName();
            switch(s) {
                case "gradient": {
                    return ComplexColorCompat.from(GradientColorInflaterCompat.createFromXmlInner(resources0, xmlResourceParser0, attributeSet0, resources$Theme0));
                }
                case "selector": {
                    return ComplexColorCompat.from(ColorStateListInflaterCompat.createFromXmlInner(resources0, xmlResourceParser0, attributeSet0, resources$Theme0));
                }
                default: {
                    throw new XmlPullParserException(xmlResourceParser0.getPositionDescription() + ": unsupported complex color tag " + s);
                }
            }
        }
        throw new XmlPullParserException("No start tag found");
    }

    static ComplexColorCompat from(@ColorInt int v) {
        return new ComplexColorCompat(null, null, v);
    }

    static ComplexColorCompat from(@NonNull ColorStateList colorStateList0) {
        return new ComplexColorCompat(null, colorStateList0, colorStateList0.getDefaultColor());
    }

    static ComplexColorCompat from(@NonNull Shader shader0) {
        return new ComplexColorCompat(shader0, null, 0);
    }

    @ColorInt
    public int getColor() {
        return this.mColor;
    }

    @Nullable
    public Shader getShader() {
        return this.mShader;
    }

    @Nullable
    public static ComplexColorCompat inflate(@NonNull Resources resources0, @ColorRes int v, @Nullable Resources.Theme resources$Theme0) {
        try {
            return ComplexColorCompat.createFromXml(resources0, v, resources$Theme0);
        }
        catch(Exception exception0) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", exception0);
            return null;
        }
    }

    public boolean isGradient() {
        return this.mShader != null;
    }

    public boolean isStateful() {
        return this.mShader == null && (this.mColorStateList != null && this.mColorStateList.isStateful());
    }

    public boolean onStateChanged(int[] arr_v) {
        if(this.isStateful()) {
            int v = this.mColorStateList.getDefaultColor();
            int v1 = this.mColorStateList.getColorForState(arr_v, v);
            if(v1 != this.mColor) {
                this.mColor = v1;
                return true;
            }
        }
        return false;
    }

    public void setColor(@ColorInt int v) {
        this.mColor = v;
    }

    public boolean willDraw() {
        return this.isGradient() || this.mColor != 0;
    }
}

