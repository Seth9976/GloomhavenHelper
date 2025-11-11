package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class DrawableCompat {
    private static final String TAG = "DrawableCompat";
    private static Method sGetLayoutDirectionMethod;
    private static boolean sGetLayoutDirectionMethodFetched;
    private static Method sSetLayoutDirectionMethod;
    private static boolean sSetLayoutDirectionMethodFetched;

    public static void applyTheme(@NonNull Drawable drawable0, @NonNull Resources.Theme resources$Theme0) {
        if(Build.VERSION.SDK_INT >= 21) {
            drawable0.applyTheme(resources$Theme0);
        }
    }

    public static boolean canApplyTheme(@NonNull Drawable drawable0) {
        return Build.VERSION.SDK_INT < 21 ? false : drawable0.canApplyTheme();
    }

    public static void clearColorFilter(@NonNull Drawable drawable0) {
        if(Build.VERSION.SDK_INT >= 23) {
            drawable0.clearColorFilter();
            return;
        }
        if(Build.VERSION.SDK_INT >= 21) {
            drawable0.clearColorFilter();
            if(drawable0 instanceof InsetDrawable) {
                DrawableCompat.clearColorFilter(((InsetDrawable)drawable0).getDrawable());
                return;
            }
            if(drawable0 instanceof WrappedDrawable) {
                DrawableCompat.clearColorFilter(((WrappedDrawable)drawable0).getWrappedDrawable());
                return;
            }
            if(drawable0 instanceof DrawableContainer) {
                DrawableContainer.DrawableContainerState drawableContainer$DrawableContainerState0 = (DrawableContainer.DrawableContainerState)((DrawableContainer)drawable0).getConstantState();
                if(drawableContainer$DrawableContainerState0 != null) {
                    int v1 = drawableContainer$DrawableContainerState0.getChildCount();
                    for(int v = 0; v < v1; ++v) {
                        Drawable drawable1 = drawableContainer$DrawableContainerState0.getChild(v);
                        if(drawable1 != null) {
                            DrawableCompat.clearColorFilter(drawable1);
                        }
                    }
                }
            }
        }
        else {
            drawable0.clearColorFilter();
        }
    }

    public static int getAlpha(@NonNull Drawable drawable0) {
        return Build.VERSION.SDK_INT < 19 ? 0 : drawable0.getAlpha();
    }

    public static ColorFilter getColorFilter(@NonNull Drawable drawable0) {
        return Build.VERSION.SDK_INT < 21 ? null : drawable0.getColorFilter();
    }

    public static int getLayoutDirection(@NonNull Drawable drawable0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return drawable0.getLayoutDirection();
        }
        if(Build.VERSION.SDK_INT >= 17) {
            if(!DrawableCompat.sGetLayoutDirectionMethodFetched) {
                try {
                    DrawableCompat.sGetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("getLayoutDirection");
                    DrawableCompat.sGetLayoutDirectionMethod.setAccessible(true);
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    Log.i("DrawableCompat", "Failed to retrieve getLayoutDirection() method", noSuchMethodException0);
                }
                DrawableCompat.sGetLayoutDirectionMethodFetched = true;
            }
            Method method0 = DrawableCompat.sGetLayoutDirectionMethod;
            if(method0 != null) {
                try {
                    return (int)(((Integer)method0.invoke(drawable0)));
                }
                catch(Exception exception0) {
                    Log.i("DrawableCompat", "Failed to invoke getLayoutDirection() via reflection", exception0);
                    DrawableCompat.sGetLayoutDirectionMethod = null;
                }
            }
            return 0;
        }
        return 0;
    }

    public static void inflate(@NonNull Drawable drawable0, @NonNull Resources resources0, @NonNull XmlPullParser xmlPullParser0, @NonNull AttributeSet attributeSet0, @Nullable Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        if(Build.VERSION.SDK_INT >= 21) {
            drawable0.inflate(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
            return;
        }
        drawable0.inflate(resources0, xmlPullParser0, attributeSet0);
    }

    public static boolean isAutoMirrored(@NonNull Drawable drawable0) {
        return Build.VERSION.SDK_INT < 19 ? false : drawable0.isAutoMirrored();
    }

    @Deprecated
    public static void jumpToCurrentState(@NonNull Drawable drawable0) {
        drawable0.jumpToCurrentState();
    }

    public static void setAutoMirrored(@NonNull Drawable drawable0, boolean z) {
        if(Build.VERSION.SDK_INT >= 19) {
            drawable0.setAutoMirrored(z);
        }
    }

    public static void setHotspot(@NonNull Drawable drawable0, float f, float f1) {
        if(Build.VERSION.SDK_INT >= 21) {
            drawable0.setHotspot(f, f1);
        }
    }

    public static void setHotspotBounds(@NonNull Drawable drawable0, int v, int v1, int v2, int v3) {
        if(Build.VERSION.SDK_INT >= 21) {
            drawable0.setHotspotBounds(v, v1, v2, v3);
        }
    }

    public static boolean setLayoutDirection(@NonNull Drawable drawable0, int v) {
        if(Build.VERSION.SDK_INT >= 23) {
            return drawable0.setLayoutDirection(v);
        }
        if(Build.VERSION.SDK_INT >= 17) {
            if(!DrawableCompat.sSetLayoutDirectionMethodFetched) {
                try {
                    DrawableCompat.sSetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
                    DrawableCompat.sSetLayoutDirectionMethod.setAccessible(true);
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", noSuchMethodException0);
                }
                DrawableCompat.sSetLayoutDirectionMethodFetched = true;
            }
            Method method0 = DrawableCompat.sSetLayoutDirectionMethod;
            if(method0 != null) {
                try {
                    method0.invoke(drawable0, v);
                    return true;
                }
                catch(Exception exception0) {
                    Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", exception0);
                    DrawableCompat.sSetLayoutDirectionMethod = null;
                }
            }
            return false;
        }
        return false;
    }

    public static void setTint(@NonNull Drawable drawable0, @ColorInt int v) {
        if(Build.VERSION.SDK_INT >= 21) {
            drawable0.setTint(v);
            return;
        }
        if(drawable0 instanceof TintAwareDrawable) {
            ((TintAwareDrawable)drawable0).setTint(v);
        }
    }

    public static void setTintList(@NonNull Drawable drawable0, @Nullable ColorStateList colorStateList0) {
        if(Build.VERSION.SDK_INT >= 21) {
            drawable0.setTintList(colorStateList0);
            return;
        }
        if(drawable0 instanceof TintAwareDrawable) {
            ((TintAwareDrawable)drawable0).setTintList(colorStateList0);
        }
    }

    public static void setTintMode(@NonNull Drawable drawable0, @NonNull PorterDuff.Mode porterDuff$Mode0) {
        if(Build.VERSION.SDK_INT >= 21) {
            drawable0.setTintMode(porterDuff$Mode0);
            return;
        }
        if(drawable0 instanceof TintAwareDrawable) {
            ((TintAwareDrawable)drawable0).setTintMode(porterDuff$Mode0);
        }
    }

    // 去混淆评级： 低(20)
    public static Drawable unwrap(@NonNull Drawable drawable0) {
        return drawable0 instanceof WrappedDrawable ? ((WrappedDrawable)drawable0).getWrappedDrawable() : drawable0;
    }

    public static Drawable wrap(@NonNull Drawable drawable0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return drawable0;
        }
        if(Build.VERSION.SDK_INT >= 21) {
            return !(drawable0 instanceof TintAwareDrawable) ? new WrappedDrawableApi21(drawable0) : drawable0;
        }
        return !(drawable0 instanceof TintAwareDrawable) ? new WrappedDrawableApi14(drawable0) : drawable0;
    }
}

