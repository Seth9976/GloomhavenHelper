package androidx.core.content.res;

import android.content.res.Resources;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;

public final class ConfigurationHelper {
    public static int getDensityDpi(@NonNull Resources resources0) {
        return Build.VERSION.SDK_INT < 17 ? resources0.getDisplayMetrics().densityDpi : resources0.getConfiguration().densityDpi;
    }
}

