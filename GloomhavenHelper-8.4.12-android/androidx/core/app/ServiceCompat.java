package androidx.core.app;

import android.app.Service;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ServiceCompat {
    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StopForegroundFlags {
    }

    public static final int START_STICKY = 1;
    public static final int STOP_FOREGROUND_DETACH = 2;
    public static final int STOP_FOREGROUND_REMOVE = 1;

    public static void stopForeground(@NonNull Service service0, int v) {
        boolean z = true;
        if(Build.VERSION.SDK_INT >= 24) {
            service0.stopForeground(v);
            return;
        }
        if((v & 1) == 0) {
            z = false;
        }
        service0.stopForeground(z);
    }
}

