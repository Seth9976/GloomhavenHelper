package androidx.core.os;

import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public final class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";

    public static String getStorageState(File file0) {
        if(Build.VERSION.SDK_INT >= 19) {
            return Environment.getStorageState(file0);
        }
        try {
            if(file0.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath())) {
                return Environment.getExternalStorageState();
            }
        }
        catch(IOException iOException0) {
            Log.w("EnvironmentCompat", "Failed to resolve canonical path: " + iOException0);
        }
        return "unknown";
    }
}

