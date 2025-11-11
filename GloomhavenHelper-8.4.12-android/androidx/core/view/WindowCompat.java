package androidx.core.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

public final class WindowCompat {
    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

    @NonNull
    public static View requireViewById(@NonNull Window window0, @IdRes int v) {
        if(Build.VERSION.SDK_INT >= 28) {
            return window0.requireViewById(v);
        }
        View view0 = window0.findViewById(v);
        if(view0 == null) {
            throw new IllegalArgumentException("ID does not reference a View inside this Window");
        }
        return view0;
    }
}

