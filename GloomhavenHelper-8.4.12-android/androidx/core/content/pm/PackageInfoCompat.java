package androidx.core.content.pm;

import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;

public final class PackageInfoCompat {
    public static long getLongVersionCode(@NonNull PackageInfo packageInfo0) {
        return Build.VERSION.SDK_INT < 28 ? ((long)packageInfo0.versionCode) : packageInfo0.getLongVersionCode();
    }
}

