package androidx.core.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.DisplayCutout;
import java.util.List;

public final class DisplayCutoutCompat {
    private final Object mDisplayCutout;

    public DisplayCutoutCompat(Rect rect0, List list0) {
        this((Build.VERSION.SDK_INT < 28 ? null : new DisplayCutout(rect0, list0)));
    }

    private DisplayCutoutCompat(Object object0) {
        this.mDisplayCutout = object0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 != null && this.getClass() == object0.getClass()) {
            return this.mDisplayCutout == null ? ((DisplayCutoutCompat)object0).mDisplayCutout == null : this.mDisplayCutout.equals(((DisplayCutoutCompat)object0).mDisplayCutout);
        }
        return false;
    }

    public List getBoundingRects() {
        return Build.VERSION.SDK_INT < 28 ? null : ((DisplayCutout)this.mDisplayCutout).getBoundingRects();
    }

    public int getSafeInsetBottom() {
        return Build.VERSION.SDK_INT < 28 ? 0 : ((DisplayCutout)this.mDisplayCutout).getSafeInsetBottom();
    }

    public int getSafeInsetLeft() {
        return Build.VERSION.SDK_INT < 28 ? 0 : ((DisplayCutout)this.mDisplayCutout).getSafeInsetLeft();
    }

    public int getSafeInsetRight() {
        return Build.VERSION.SDK_INT < 28 ? 0 : ((DisplayCutout)this.mDisplayCutout).getSafeInsetRight();
    }

    public int getSafeInsetTop() {
        return Build.VERSION.SDK_INT < 28 ? 0 : ((DisplayCutout)this.mDisplayCutout).getSafeInsetTop();
    }

    @Override
    public int hashCode() {
        return this.mDisplayCutout == null ? 0 : this.mDisplayCutout.hashCode();
    }

    @Override
    public String toString() {
        return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
    }

    static DisplayCutoutCompat wrap(Object object0) {
        return object0 == null ? null : new DisplayCutoutCompat(object0);
    }
}

