package androidx.core.view;

import android.os.Build.VERSION;
import android.view.ViewGroup.MarginLayoutParams;

public final class MarginLayoutParamsCompat {
    public static int getLayoutDirection(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
        int v = Build.VERSION.SDK_INT < 17 ? 0 : viewGroup$MarginLayoutParams0.getLayoutDirection();
        return v == 0 || v == 1 ? v : 0;
    }

    public static int getMarginEnd(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
        return Build.VERSION.SDK_INT < 17 ? viewGroup$MarginLayoutParams0.rightMargin : viewGroup$MarginLayoutParams0.getMarginEnd();
    }

    public static int getMarginStart(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
        return Build.VERSION.SDK_INT < 17 ? viewGroup$MarginLayoutParams0.leftMargin : viewGroup$MarginLayoutParams0.getMarginStart();
    }

    public static boolean isMarginRelative(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
        return Build.VERSION.SDK_INT < 17 ? false : viewGroup$MarginLayoutParams0.isMarginRelative();
    }

    public static void resolveLayoutDirection(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v) {
        if(Build.VERSION.SDK_INT >= 17) {
            viewGroup$MarginLayoutParams0.resolveLayoutDirection(v);
        }
    }

    public static void setLayoutDirection(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v) {
        if(Build.VERSION.SDK_INT >= 17) {
            viewGroup$MarginLayoutParams0.setLayoutDirection(v);
        }
    }

    public static void setMarginEnd(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v) {
        if(Build.VERSION.SDK_INT >= 17) {
            viewGroup$MarginLayoutParams0.setMarginEnd(v);
            return;
        }
        viewGroup$MarginLayoutParams0.rightMargin = v;
    }

    public static void setMarginStart(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v) {
        if(Build.VERSION.SDK_INT >= 17) {
            viewGroup$MarginLayoutParams0.setMarginStart(v);
            return;
        }
        viewGroup$MarginLayoutParams0.leftMargin = v;
    }
}

