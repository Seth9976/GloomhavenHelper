package androidx.core.app;

import android.app.ActivityManager;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;

public final class ActivityManagerCompat {
    public static boolean isLowRamDevice(@NonNull ActivityManager activityManager0) {
        return Build.VERSION.SDK_INT < 19 ? false : activityManager0.isLowRamDevice();
    }
}

