package androidx.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import java.lang.reflect.Method;

public final class ViewConfigurationCompat {
    private static final String TAG = "ViewConfigCompat";
    private static Method sGetScaledScrollFactorMethod;

    static {
        if(Build.VERSION.SDK_INT == 25) {
            try {
                ViewConfigurationCompat.sGetScaledScrollFactorMethod = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor");
            }
            catch(Exception unused_ex) {
                Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
    }

    private static float getLegacyScrollFactor(ViewConfiguration viewConfiguration0, Context context0) {
        if(Build.VERSION.SDK_INT >= 25) {
            Method method0 = ViewConfigurationCompat.sGetScaledScrollFactorMethod;
            if(method0 != null) {
                try {
                    return (float)(((int)(((Integer)method0.invoke(viewConfiguration0)))));
                }
                catch(Exception unused_ex) {
                    Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
                }
            }
        }
        TypedValue typedValue0 = new TypedValue();
        return context0.getTheme().resolveAttribute(0x101004D, typedValue0, true) ? typedValue0.getDimension(context0.getResources().getDisplayMetrics()) : 0.0f;
    }

    public static float getScaledHorizontalScrollFactor(@NonNull ViewConfiguration viewConfiguration0, @NonNull Context context0) {
        return Build.VERSION.SDK_INT < 26 ? ViewConfigurationCompat.getLegacyScrollFactor(viewConfiguration0, context0) : viewConfiguration0.getScaledHorizontalScrollFactor();
    }

    public static int getScaledHoverSlop(ViewConfiguration viewConfiguration0) {
        return Build.VERSION.SDK_INT < 28 ? viewConfiguration0.getScaledTouchSlop() / 2 : viewConfiguration0.getScaledHoverSlop();
    }

    @Deprecated
    public static int getScaledPagingTouchSlop(ViewConfiguration viewConfiguration0) {
        return viewConfiguration0.getScaledPagingTouchSlop();
    }

    public static float getScaledVerticalScrollFactor(@NonNull ViewConfiguration viewConfiguration0, @NonNull Context context0) {
        return Build.VERSION.SDK_INT < 26 ? ViewConfigurationCompat.getLegacyScrollFactor(viewConfiguration0, context0) : viewConfiguration0.getScaledVerticalScrollFactor();
    }

    @Deprecated
    public static boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration0) {
        return viewConfiguration0.hasPermanentMenuKey();
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration0, @NonNull Context context0) {
        if(Build.VERSION.SDK_INT >= 28) {
            return viewConfiguration0.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
        Resources resources0 = context0.getResources();
        int v = resources0.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
        return v != 0 && resources0.getBoolean(v);
    }
}

