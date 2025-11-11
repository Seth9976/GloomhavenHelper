package androidx.core.view;

import android.os.Build.VERSION;
import android.view.ScaleGestureDetector;

public final class ScaleGestureDetectorCompat {
    public static boolean isQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector0) {
        return Build.VERSION.SDK_INT < 19 ? false : scaleGestureDetector0.isQuickScaleEnabled();
    }

    @Deprecated
    public static boolean isQuickScaleEnabled(Object object0) {
        return ScaleGestureDetectorCompat.isQuickScaleEnabled(((ScaleGestureDetector)object0));
    }

    public static void setQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector0, boolean z) {
        if(Build.VERSION.SDK_INT >= 19) {
            scaleGestureDetector0.setQuickScaleEnabled(z);
        }
    }

    @Deprecated
    public static void setQuickScaleEnabled(Object object0, boolean z) {
        ScaleGestureDetectorCompat.setQuickScaleEnabled(((ScaleGestureDetector)object0), z);
    }
}

