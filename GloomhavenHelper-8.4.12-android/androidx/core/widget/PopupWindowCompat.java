package androidx.core.widget;

import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat {
    private static final String TAG = "PopupWindowCompatApi21";
    private static Method sGetWindowLayoutTypeMethod;
    private static boolean sGetWindowLayoutTypeMethodAttempted;
    private static Field sOverlapAnchorField;
    private static boolean sOverlapAnchorFieldAttempted;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;

    public static boolean getOverlapAnchor(@NonNull PopupWindow popupWindow0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return popupWindow0.getOverlapAnchor();
        }
        if(Build.VERSION.SDK_INT >= 21) {
            if(!PopupWindowCompat.sOverlapAnchorFieldAttempted) {
                try {
                    PopupWindowCompat.sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    PopupWindowCompat.sOverlapAnchorField.setAccessible(true);
                }
                catch(NoSuchFieldException noSuchFieldException0) {
                    Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", noSuchFieldException0);
                }
                PopupWindowCompat.sOverlapAnchorFieldAttempted = true;
            }
            Field field0 = PopupWindowCompat.sOverlapAnchorField;
            if(field0 != null) {
                try {
                    return ((Boolean)field0.get(popupWindow0)).booleanValue();
                }
                catch(IllegalAccessException illegalAccessException0) {
                    Log.i("PopupWindowCompatApi21", "Could not get overlap anchor field in PopupWindow", illegalAccessException0);
                }
            }
        }
        return false;
    }

    public static int getWindowLayoutType(@NonNull PopupWindow popupWindow0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return popupWindow0.getWindowLayoutType();
        }
        if(!PopupWindowCompat.sGetWindowLayoutTypeMethodAttempted) {
            try {
                PopupWindowCompat.sGetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("getWindowLayoutType");
                PopupWindowCompat.sGetWindowLayoutTypeMethod.setAccessible(true);
            }
            catch(Exception unused_ex) {
            }
            PopupWindowCompat.sGetWindowLayoutTypeMethodAttempted = true;
        }
        Method method0 = PopupWindowCompat.sGetWindowLayoutTypeMethod;
        if(method0 != null) {
            try {
                return (int)(((Integer)method0.invoke(popupWindow0)));
            }
            catch(Exception unused_ex) {
            }
        }
        return 0;
    }

    public static void setOverlapAnchor(@NonNull PopupWindow popupWindow0, boolean z) {
        if(Build.VERSION.SDK_INT >= 23) {
            popupWindow0.setOverlapAnchor(z);
            return;
        }
        if(Build.VERSION.SDK_INT >= 21) {
            if(!PopupWindowCompat.sOverlapAnchorFieldAttempted) {
                try {
                    PopupWindowCompat.sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    PopupWindowCompat.sOverlapAnchorField.setAccessible(true);
                }
                catch(NoSuchFieldException noSuchFieldException0) {
                    Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", noSuchFieldException0);
                }
                PopupWindowCompat.sOverlapAnchorFieldAttempted = true;
            }
            Field field0 = PopupWindowCompat.sOverlapAnchorField;
            if(field0 != null) {
                try {
                    field0.set(popupWindow0, Boolean.valueOf(z));
                }
                catch(IllegalAccessException illegalAccessException0) {
                    Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", illegalAccessException0);
                }
            }
        }
    }

    public static void setWindowLayoutType(@NonNull PopupWindow popupWindow0, int v) {
        if(Build.VERSION.SDK_INT >= 23) {
            popupWindow0.setWindowLayoutType(v);
            return;
        }
        if(!PopupWindowCompat.sSetWindowLayoutTypeMethodAttempted) {
            try {
                PopupWindowCompat.sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                PopupWindowCompat.sSetWindowLayoutTypeMethod.setAccessible(true);
            }
            catch(Exception unused_ex) {
            }
            PopupWindowCompat.sSetWindowLayoutTypeMethodAttempted = true;
        }
        Method method0 = PopupWindowCompat.sSetWindowLayoutTypeMethod;
        if(method0 != null) {
            try {
                method0.invoke(popupWindow0, v);
            }
            catch(Exception unused_ex) {
            }
        }
    }

    public static void showAsDropDown(@NonNull PopupWindow popupWindow0, @NonNull View view0, int v, int v1, int v2) {
        if(Build.VERSION.SDK_INT >= 19) {
            popupWindow0.showAsDropDown(view0, v, v1, v2);
            return;
        }
        if((GravityCompat.getAbsoluteGravity(v2, ViewCompat.getLayoutDirection(view0)) & 7) == 5) {
            v -= popupWindow0.getWidth() - view0.getWidth();
        }
        popupWindow0.showAsDropDown(view0, v, v1);
    }
}

