package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityWindowInfo;

public class AccessibilityWindowInfoCompat {
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private static final int UNDEFINED = -1;
    private Object mInfo;

    private AccessibilityWindowInfoCompat(Object object0) {
        this.mInfo = object0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null) {
            return false;
        }
        if(this.getClass() != object0.getClass()) {
            return false;
        }
        return this.mInfo == null ? ((AccessibilityWindowInfoCompat)object0).mInfo == null : this.mInfo.equals(((AccessibilityWindowInfoCompat)object0).mInfo);
    }

    public AccessibilityNodeInfoCompat getAnchor() {
        return Build.VERSION.SDK_INT < 24 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getAnchor());
    }

    public void getBoundsInScreen(Rect rect0) {
        if(Build.VERSION.SDK_INT >= 21) {
            ((AccessibilityWindowInfo)this.mInfo).getBoundsInScreen(rect0);
        }
    }

    public AccessibilityWindowInfoCompat getChild(int v) {
        return Build.VERSION.SDK_INT < 21 ? null : AccessibilityWindowInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getChild(v));
    }

    public int getChildCount() {
        return Build.VERSION.SDK_INT < 21 ? 0 : ((AccessibilityWindowInfo)this.mInfo).getChildCount();
    }

    public int getId() {
        return Build.VERSION.SDK_INT < 21 ? -1 : ((AccessibilityWindowInfo)this.mInfo).getId();
    }

    public int getLayer() {
        return Build.VERSION.SDK_INT < 21 ? -1 : ((AccessibilityWindowInfo)this.mInfo).getLayer();
    }

    public AccessibilityWindowInfoCompat getParent() {
        return Build.VERSION.SDK_INT < 21 ? null : AccessibilityWindowInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getParent());
    }

    public AccessibilityNodeInfoCompat getRoot() {
        return Build.VERSION.SDK_INT < 21 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getRoot());
    }

    public CharSequence getTitle() {
        return Build.VERSION.SDK_INT < 24 ? null : ((AccessibilityWindowInfo)this.mInfo).getTitle();
    }

    public int getType() {
        return Build.VERSION.SDK_INT < 21 ? -1 : ((AccessibilityWindowInfo)this.mInfo).getType();
    }

    @Override
    public int hashCode() {
        return this.mInfo == null ? 0 : this.mInfo.hashCode();
    }

    public boolean isAccessibilityFocused() {
        return Build.VERSION.SDK_INT < 21 ? true : ((AccessibilityWindowInfo)this.mInfo).isAccessibilityFocused();
    }

    public boolean isActive() {
        return Build.VERSION.SDK_INT < 21 ? true : ((AccessibilityWindowInfo)this.mInfo).isActive();
    }

    public boolean isFocused() {
        return Build.VERSION.SDK_INT < 21 ? true : ((AccessibilityWindowInfo)this.mInfo).isFocused();
    }

    public static AccessibilityWindowInfoCompat obtain() {
        return Build.VERSION.SDK_INT < 21 ? null : AccessibilityWindowInfoCompat.wrapNonNullInstance(AccessibilityWindowInfo.obtain());
    }

    public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat accessibilityWindowInfoCompat0) {
        return Build.VERSION.SDK_INT < 21 || accessibilityWindowInfoCompat0 == null ? null : AccessibilityWindowInfoCompat.wrapNonNullInstance(AccessibilityWindowInfo.obtain(((AccessibilityWindowInfo)accessibilityWindowInfoCompat0.mInfo)));
    }

    public void recycle() {
        if(Build.VERSION.SDK_INT >= 21) {
            ((AccessibilityWindowInfo)this.mInfo).recycle();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        Rect rect0 = new Rect();
        this.getBoundsInScreen(rect0);
        stringBuilder0.append("AccessibilityWindowInfo[");
        stringBuilder0.append("id=");
        stringBuilder0.append(this.getId());
        stringBuilder0.append(", type=");
        stringBuilder0.append(AccessibilityWindowInfoCompat.typeToString(this.getType()));
        stringBuilder0.append(", layer=");
        stringBuilder0.append(this.getLayer());
        stringBuilder0.append(", bounds=");
        stringBuilder0.append(rect0);
        stringBuilder0.append(", focused=");
        stringBuilder0.append(this.isFocused());
        stringBuilder0.append(", active=");
        stringBuilder0.append(this.isActive());
        stringBuilder0.append(", hasParent=");
        boolean z = true;
        stringBuilder0.append(this.getParent() != null);
        stringBuilder0.append(", hasChildren=");
        if(this.getChildCount() <= 0) {
            z = false;
        }
        stringBuilder0.append(z);
        stringBuilder0.append(']');
        return stringBuilder0.toString();
    }

    private static String typeToString(int v) {
        switch(v) {
            case 1: {
                return "TYPE_APPLICATION";
            }
            case 2: {
                return "TYPE_INPUT_METHOD";
            }
            case 3: {
                return "TYPE_SYSTEM";
            }
            case 4: {
                return "TYPE_ACCESSIBILITY_OVERLAY";
            }
            default: {
                return "<UNKNOWN>";
            }
        }
    }

    static AccessibilityWindowInfoCompat wrapNonNullInstance(Object object0) {
        return object0 == null ? null : new AccessibilityWindowInfoCompat(object0);
    }
}

