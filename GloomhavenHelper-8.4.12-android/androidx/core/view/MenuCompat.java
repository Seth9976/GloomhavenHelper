package androidx.core.view;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.internal.view.SupportMenu;

public final class MenuCompat {
    @SuppressLint({"NewApi"})
    public static void setGroupDividerEnabled(Menu menu0, boolean z) {
        if(menu0 instanceof SupportMenu) {
            ((SupportMenu)menu0).setGroupDividerEnabled(z);
            return;
        }
        if(Build.VERSION.SDK_INT >= 28) {
            menu0.setGroupDividerEnabled(z);
        }
    }

    @Deprecated
    public static void setShowAsAction(MenuItem menuItem0, int v) {
        menuItem0.setShowAsAction(v);
    }
}

