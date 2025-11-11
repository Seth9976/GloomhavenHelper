package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.Preconditions;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
    public static abstract class FontCallback {
        @RestrictTo({Scope.LIBRARY_GROUP})
        public final void callbackFailAsync(int v, @Nullable Handler handler0) {
            if(handler0 == null) {
                handler0 = new Handler(Looper.getMainLooper());
            }
            handler0.post(() -> ;);

            class androidx.core.content.res.ResourcesCompat.FontCallback.2 implements Runnable {
                androidx.core.content.res.ResourcesCompat.FontCallback.2(int v) {
                }

                @Override
                public void run() {
                    FontCallback.this.onFontRetrievalFailed(this.val$reason);
                }
            }

        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public final void callbackSuccessAsync(Typeface typeface0, @Nullable Handler handler0) {
            if(handler0 == null) {
                handler0 = new Handler(Looper.getMainLooper());
            }
            handler0.post(() -> ;);

            class androidx.core.content.res.ResourcesCompat.FontCallback.1 implements Runnable {
                androidx.core.content.res.ResourcesCompat.FontCallback.1(Typeface typeface0) {
                }

                @Override
                public void run() {
                    FontCallback.this.onFontRetrieved(this.val$typeface);
                }
            }

        }

        // 检测为 Lambda 实现
        public abstract void onFontRetrievalFailed(int arg1);

        // 检测为 Lambda 实现
        public abstract void onFontRetrieved(@NonNull Typeface arg1);
    }

    private static final String TAG = "ResourcesCompat";

    @ColorInt
    public static int getColor(@NonNull Resources resources0, @ColorRes int v, @Nullable Resources.Theme resources$Theme0) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT < 23 ? resources0.getColor(v) : resources0.getColor(v, resources$Theme0);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Resources resources0, @ColorRes int v, @Nullable Resources.Theme resources$Theme0) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT < 23 ? resources0.getColorStateList(v) : resources0.getColorStateList(v, resources$Theme0);
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Resources resources0, @DrawableRes int v, @Nullable Resources.Theme resources$Theme0) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT < 21 ? resources0.getDrawable(v) : resources0.getDrawable(v, resources$Theme0);
    }

    @Nullable
    public static Drawable getDrawableForDensity(@NonNull Resources resources0, @DrawableRes int v, int v1, @Nullable Resources.Theme resources$Theme0) throws Resources.NotFoundException {
        if(Build.VERSION.SDK_INT >= 21) {
            return resources0.getDrawableForDensity(v, v1, resources$Theme0);
        }
        return Build.VERSION.SDK_INT < 15 ? resources0.getDrawable(v) : resources0.getDrawableForDensity(v, v1);
    }

    // 去混淆评级： 低(20)
    @Nullable
    public static Typeface getFont(@NonNull Context context0, @FontRes int v) throws Resources.NotFoundException {
        return context0.isRestricted() ? null : ResourcesCompat.loadFont(context0, v, new TypedValue(), 0, null, null, false);
    }

    // 去混淆评级： 低(20)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Typeface getFont(@NonNull Context context0, @FontRes int v, TypedValue typedValue0, int v1, @Nullable FontCallback resourcesCompat$FontCallback0) throws Resources.NotFoundException {
        return context0.isRestricted() ? null : ResourcesCompat.loadFont(context0, v, typedValue0, v1, resourcesCompat$FontCallback0, null, true);
    }

    public static void getFont(@NonNull Context context0, @FontRes int v, @NonNull FontCallback resourcesCompat$FontCallback0, @Nullable Handler handler0) throws Resources.NotFoundException {
        Preconditions.checkNotNull(resourcesCompat$FontCallback0);
        if(context0.isRestricted()) {
            resourcesCompat$FontCallback0.callbackFailAsync(-4, handler0);
            return;
        }
        ResourcesCompat.loadFont(context0, v, new TypedValue(), 0, resourcesCompat$FontCallback0, handler0, false);
    }

    private static Typeface loadFont(@NonNull Context context0, int v, TypedValue typedValue0, int v1, @Nullable FontCallback resourcesCompat$FontCallback0, @Nullable Handler handler0, boolean z) {
        Resources resources0 = context0.getResources();
        resources0.getValue(v, typedValue0, true);
        Typeface typeface0 = ResourcesCompat.loadFont(context0, resources0, typedValue0, v, v1, resourcesCompat$FontCallback0, handler0, z);
        if(typeface0 == null && resourcesCompat$FontCallback0 == null) {
            throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(v) + " could not be retrieved.");
        }
        return typeface0;
    }

    private static Typeface loadFont(@NonNull Context context0, Resources resources0, TypedValue typedValue0, int v, int v1, @Nullable FontCallback resourcesCompat$FontCallback0, @Nullable Handler handler0, boolean z) {
        if(typedValue0.string == null) {
            throw new Resources.NotFoundException("Resource \"" + resources0.getResourceName(v) + "\" (" + Integer.toHexString(v) + ") is not a Font: " + typedValue0);
        }
        String s = typedValue0.string.toString();
        if(!s.startsWith("res/")) {
            if(resourcesCompat$FontCallback0 != null) {
                resourcesCompat$FontCallback0.callbackFailAsync(-3, handler0);
            }
            return null;
        }
        Typeface typeface0 = TypefaceCompat.findFromCache(resources0, v, v1);
        if(typeface0 != null) {
            if(resourcesCompat$FontCallback0 != null) {
                resourcesCompat$FontCallback0.callbackSuccessAsync(typeface0, handler0);
            }
            return typeface0;
        }
        try {
            if(s.toLowerCase().endsWith(".xml")) {
                FamilyResourceEntry fontResourcesParserCompat$FamilyResourceEntry0 = FontResourcesParserCompat.parse(resources0.getXml(v), resources0);
                if(fontResourcesParserCompat$FamilyResourceEntry0 == null) {
                    Log.e("ResourcesCompat", "Failed to find font-family tag");
                    if(resourcesCompat$FontCallback0 != null) {
                        resourcesCompat$FontCallback0.callbackFailAsync(-3, handler0);
                    }
                    return null;
                }
                return TypefaceCompat.createFromResourcesFamilyXml(context0, fontResourcesParserCompat$FamilyResourceEntry0, resources0, v, v1, resourcesCompat$FontCallback0, handler0, z);
            }
            Typeface typeface1 = TypefaceCompat.createFromResourcesFontFile(context0, resources0, v, s, v1);
            if(resourcesCompat$FontCallback0 != null) {
                if(typeface1 != null) {
                    resourcesCompat$FontCallback0.callbackSuccessAsync(typeface1, handler0);
                    return typeface1;
                }
                resourcesCompat$FontCallback0.callbackFailAsync(-3, handler0);
            }
            return typeface1;
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("ResourcesCompat", "Failed to parse xml resource " + s, xmlPullParserException0);
        }
        catch(IOException iOException0) {
            Log.e("ResourcesCompat", "Failed to read xml resource " + s, iOException0);
        }
        if(resourcesCompat$FontCallback0 != null) {
            resourcesCompat$FontCallback0.callbackFailAsync(-3, handler0);
        }
        return null;
    }
}

