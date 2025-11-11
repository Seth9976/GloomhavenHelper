package androidx.core.internal.view;

import android.view.Menu;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface SupportMenu extends Menu {
    public static final int CATEGORY_MASK = 0xFFFF0000;
    public static final int CATEGORY_SHIFT = 16;
    public static final int FLAG_KEEP_OPEN_ON_SUBMENU_OPENED = 4;
    public static final int SUPPORTED_MODIFIERS_MASK = 0x1100F;
    public static final int USER_MASK = 0xFFFF;
    public static final int USER_SHIFT;

    @Override  // android.view.Menu
    void setGroupDividerEnabled(boolean arg1);
}

