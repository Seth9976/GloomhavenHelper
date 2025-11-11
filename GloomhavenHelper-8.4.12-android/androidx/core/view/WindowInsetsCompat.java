package androidx.core.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.WindowInsets;
import androidx.annotation.Nullable;

public class WindowInsetsCompat {
    private final Object mInsets;

    public WindowInsetsCompat(WindowInsetsCompat windowInsetsCompat0) {
        WindowInsets windowInsets0 = null;
        if(Build.VERSION.SDK_INT >= 20) {
            if(windowInsetsCompat0 != null) {
                windowInsets0 = new WindowInsets(((WindowInsets)windowInsetsCompat0.mInsets));
            }
            this.mInsets = windowInsets0;
            return;
        }
        this.mInsets = null;
    }

    private WindowInsetsCompat(Object object0) {
        this.mInsets = object0;
    }

    public WindowInsetsCompat consumeDisplayCutout() {
        return Build.VERSION.SDK_INT < 28 ? this : new WindowInsetsCompat(((WindowInsets)this.mInsets).consumeDisplayCutout());
    }

    public WindowInsetsCompat consumeStableInsets() {
        return Build.VERSION.SDK_INT < 21 ? null : new WindowInsetsCompat(((WindowInsets)this.mInsets).consumeStableInsets());
    }

    public WindowInsetsCompat consumeSystemWindowInsets() {
        return Build.VERSION.SDK_INT < 20 ? null : new WindowInsetsCompat(((WindowInsets)this.mInsets).consumeSystemWindowInsets());
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 != null && this.getClass() == object0.getClass()) {
            return this.mInsets == null ? ((WindowInsetsCompat)object0).mInsets == null : this.mInsets.equals(((WindowInsetsCompat)object0).mInsets);
        }
        return false;
    }

    @Nullable
    public DisplayCutoutCompat getDisplayCutout() {
        return Build.VERSION.SDK_INT < 28 ? null : DisplayCutoutCompat.wrap(((WindowInsets)this.mInsets).getDisplayCutout());
    }

    public int getStableInsetBottom() {
        return Build.VERSION.SDK_INT < 21 ? 0 : ((WindowInsets)this.mInsets).getStableInsetBottom();
    }

    public int getStableInsetLeft() {
        return Build.VERSION.SDK_INT < 21 ? 0 : ((WindowInsets)this.mInsets).getStableInsetLeft();
    }

    public int getStableInsetRight() {
        return Build.VERSION.SDK_INT < 21 ? 0 : ((WindowInsets)this.mInsets).getStableInsetRight();
    }

    public int getStableInsetTop() {
        return Build.VERSION.SDK_INT < 21 ? 0 : ((WindowInsets)this.mInsets).getStableInsetTop();
    }

    public int getSystemWindowInsetBottom() {
        return Build.VERSION.SDK_INT < 20 ? 0 : ((WindowInsets)this.mInsets).getSystemWindowInsetBottom();
    }

    public int getSystemWindowInsetLeft() {
        return Build.VERSION.SDK_INT < 20 ? 0 : ((WindowInsets)this.mInsets).getSystemWindowInsetLeft();
    }

    public int getSystemWindowInsetRight() {
        return Build.VERSION.SDK_INT < 20 ? 0 : ((WindowInsets)this.mInsets).getSystemWindowInsetRight();
    }

    public int getSystemWindowInsetTop() {
        return Build.VERSION.SDK_INT < 20 ? 0 : ((WindowInsets)this.mInsets).getSystemWindowInsetTop();
    }

    public boolean hasInsets() {
        return Build.VERSION.SDK_INT < 20 ? false : ((WindowInsets)this.mInsets).hasInsets();
    }

    public boolean hasStableInsets() {
        return Build.VERSION.SDK_INT < 21 ? false : ((WindowInsets)this.mInsets).hasStableInsets();
    }

    public boolean hasSystemWindowInsets() {
        return Build.VERSION.SDK_INT < 20 ? false : ((WindowInsets)this.mInsets).hasSystemWindowInsets();
    }

    @Override
    public int hashCode() {
        return this.mInsets == null ? 0 : this.mInsets.hashCode();
    }

    public boolean isConsumed() {
        return Build.VERSION.SDK_INT < 21 ? false : ((WindowInsets)this.mInsets).isConsumed();
    }

    public boolean isRound() {
        return Build.VERSION.SDK_INT < 20 ? false : ((WindowInsets)this.mInsets).isRound();
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int v, int v1, int v2, int v3) {
        return Build.VERSION.SDK_INT < 20 ? null : new WindowInsetsCompat(((WindowInsets)this.mInsets).replaceSystemWindowInsets(v, v1, v2, v3));
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect0) {
        return Build.VERSION.SDK_INT < 21 ? null : new WindowInsetsCompat(((WindowInsets)this.mInsets).replaceSystemWindowInsets(rect0));
    }

    static Object unwrap(WindowInsetsCompat windowInsetsCompat0) {
        return windowInsetsCompat0 == null ? null : windowInsetsCompat0.mInsets;
    }

    static WindowInsetsCompat wrap(Object object0) {
        return object0 == null ? null : new WindowInsetsCompat(object0);
    }
}

