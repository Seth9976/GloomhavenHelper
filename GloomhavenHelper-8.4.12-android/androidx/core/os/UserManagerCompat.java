package androidx.core.os;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.UserManager;

public class UserManagerCompat {
    public static boolean isUserUnlocked(Context context0) {
        return Build.VERSION.SDK_INT < 24 ? true : ((UserManager)context0.getSystemService(UserManager.class)).isUserUnlocked();
    }
}

