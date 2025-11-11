package androidx.core.graphics;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;

public final class BitmapCompat {
    public static int getAllocationByteCount(@NonNull Bitmap bitmap0) {
        return Build.VERSION.SDK_INT < 19 ? bitmap0.getByteCount() : bitmap0.getAllocationByteCount();
    }

    public static boolean hasMipMap(@NonNull Bitmap bitmap0) {
        return Build.VERSION.SDK_INT < 18 ? false : bitmap0.hasMipMap();
    }

    public static void setHasMipMap(@NonNull Bitmap bitmap0, boolean z) {
        if(Build.VERSION.SDK_INT >= 18) {
            bitmap0.setHasMipMap(z);
        }
    }
}

