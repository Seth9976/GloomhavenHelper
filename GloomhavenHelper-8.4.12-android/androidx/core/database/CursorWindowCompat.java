package androidx.core.database;

import android.database.CursorWindow;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class CursorWindowCompat {
    @NonNull
    public static CursorWindow create(@Nullable String s, long v) {
        if(Build.VERSION.SDK_INT >= 28) {
            return new CursorWindow(s, v);
        }
        return Build.VERSION.SDK_INT < 15 ? new CursorWindow(false) : new CursorWindow(s);
    }
}

