package androidx.core.text;

import android.os.Build.VERSION;
import android.util.Log;
import androidx.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public final class ICUCompat {
    private static final String TAG = "ICUCompat";
    private static Method sAddLikelySubtagsMethod;
    private static Method sGetScriptMethod;

    static {
        if(Build.VERSION.SDK_INT >= 21) {
            try {
                ICUCompat.sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
                return;
            }
            catch(Exception exception0) {
                throw new IllegalStateException(exception0);
            }
        }
        try {
            Class class0 = Class.forName("libcore.icu.ICU");
            if(class0 != null) {
                ICUCompat.sGetScriptMethod = class0.getMethod("getScript", String.class);
                ICUCompat.sAddLikelySubtagsMethod = class0.getMethod("addLikelySubtags", String.class);
            }
        }
        catch(Exception exception1) {
            ICUCompat.sGetScriptMethod = null;
            ICUCompat.sAddLikelySubtagsMethod = null;
            Log.w("ICUCompat", exception1);
        }
    }

    private static String addLikelySubtags(Locale locale0) {
        String s = locale0.toString();
        if(ICUCompat.sAddLikelySubtagsMethod != null) {
            try {
                return (String)ICUCompat.sAddLikelySubtagsMethod.invoke(null, s);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.w("ICUCompat", illegalAccessException0);
                return s;
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.w("ICUCompat", invocationTargetException0);
                return s;
            }
        }
        return s;
    }

    private static String getScript(String s) {
        if(ICUCompat.sGetScriptMethod != null) {
            try {
                return (String)ICUCompat.sGetScriptMethod.invoke(null, s);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.w("ICUCompat", illegalAccessException0);
                return null;
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.w("ICUCompat", invocationTargetException0);
                return null;
            }
        }
        return null;
    }

    @Nullable
    public static String maximizeAndGetScript(Locale locale0) {
        if(Build.VERSION.SDK_INT >= 21) {
            try {
                return ((Locale)ICUCompat.sAddLikelySubtagsMethod.invoke(null, locale0)).getScript();
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.w("ICUCompat", invocationTargetException0);
                return locale0.getScript();
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.w("ICUCompat", illegalAccessException0);
                return locale0.getScript();
            }
        }
        String s = ICUCompat.addLikelySubtags(locale0);
        return s == null ? null : ICUCompat.getScript(s);
    }
}

