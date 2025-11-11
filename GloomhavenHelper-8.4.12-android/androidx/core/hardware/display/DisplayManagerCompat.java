package androidx.core.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.WeakHashMap;

public final class DisplayManagerCompat {
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private final Context mContext;
    private static final WeakHashMap sInstances;

    static {
        DisplayManagerCompat.sInstances = new WeakHashMap();
    }

    private DisplayManagerCompat(Context context0) {
        this.mContext = context0;
    }

    @Nullable
    public Display getDisplay(int v) {
        if(Build.VERSION.SDK_INT >= 17) {
            return ((DisplayManager)this.mContext.getSystemService("display")).getDisplay(v);
        }
        Display display0 = ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay();
        return display0.getDisplayId() == v ? display0 : null;
    }

    @NonNull
    public Display[] getDisplays() {
        return Build.VERSION.SDK_INT < 17 ? new Display[]{((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay()} : ((DisplayManager)this.mContext.getSystemService("display")).getDisplays();
    }

    @NonNull
    public Display[] getDisplays(@Nullable String s) {
        if(Build.VERSION.SDK_INT >= 17) {
            return ((DisplayManager)this.mContext.getSystemService("display")).getDisplays(s);
        }
        return s == null ? new Display[0] : new Display[]{((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay()};
    }

    @NonNull
    public static DisplayManagerCompat getInstance(@NonNull Context context0) {
        synchronized(DisplayManagerCompat.sInstances) {
            DisplayManagerCompat displayManagerCompat0 = (DisplayManagerCompat)DisplayManagerCompat.sInstances.get(context0);
            if(displayManagerCompat0 == null) {
                displayManagerCompat0 = new DisplayManagerCompat(context0);
                DisplayManagerCompat.sInstances.put(context0, displayManagerCompat0);
            }
            return displayManagerCompat0;
        }
    }
}

