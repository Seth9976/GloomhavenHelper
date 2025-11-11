package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class AppOpsManagerCompat {
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_ERRORED = 2;
    public static final int MODE_IGNORED = 1;

    public static int noteOp(@NonNull Context context0, @NonNull String s, int v, @NonNull String s1) {
        return Build.VERSION.SDK_INT < 19 ? 1 : ((AppOpsManager)context0.getSystemService("appops")).noteOp(s, v, s1);
    }

    public static int noteOpNoThrow(@NonNull Context context0, @NonNull String s, int v, @NonNull String s1) {
        return Build.VERSION.SDK_INT < 19 ? 1 : ((AppOpsManager)context0.getSystemService("appops")).noteOpNoThrow(s, v, s1);
    }

    public static int noteProxyOp(@NonNull Context context0, @NonNull String s, @NonNull String s1) {
        return Build.VERSION.SDK_INT < 23 ? 1 : ((AppOpsManager)context0.getSystemService(AppOpsManager.class)).noteProxyOp(s, s1);
    }

    public static int noteProxyOpNoThrow(@NonNull Context context0, @NonNull String s, @NonNull String s1) {
        return Build.VERSION.SDK_INT < 23 ? 1 : ((AppOpsManager)context0.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(s, s1);
    }

    @Nullable
    public static String permissionToOp(@NonNull String s) {
        return Build.VERSION.SDK_INT < 23 ? null : AppOpsManager.permissionToOp(s);
    }
}

