package androidx.core.view.animation;

import android.graphics.Path;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

public final class PathInterpolatorCompat {
    public static Interpolator create(float f, float f1) {
        return Build.VERSION.SDK_INT >= 21 ? new PathInterpolator(f, f1) : new PathInterpolatorApi14(f, f1);
    }

    public static Interpolator create(float f, float f1, float f2, float f3) {
        return Build.VERSION.SDK_INT >= 21 ? new PathInterpolator(f, f1, f2, f3) : new PathInterpolatorApi14(f, f1, f2, f3);
    }

    public static Interpolator create(Path path0) {
        return Build.VERSION.SDK_INT >= 21 ? new PathInterpolator(path0) : new PathInterpolatorApi14(path0);
    }
}

