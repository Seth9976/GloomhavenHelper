package androidx.core.os;

import android.os.Build.VERSION;
import android.os.Trace;

public final class TraceCompat {
    public static void beginSection(String s) {
        if(Build.VERSION.SDK_INT >= 18) {
            Trace.beginSection(s);
        }
    }

    public static void endSection() {
        if(Build.VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
}

