package androidx.core.os;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class HandlerCompat {
    public static boolean postDelayed(@NonNull Handler handler0, @NonNull Runnable runnable0, @Nullable Object object0, long v) {
        if(Build.VERSION.SDK_INT >= 28) {
            return handler0.postDelayed(runnable0, object0, v);
        }
        Message message0 = Message.obtain(handler0, runnable0);
        message0.obj = object0;
        return handler0.sendMessageDelayed(message0, v);
    }
}

