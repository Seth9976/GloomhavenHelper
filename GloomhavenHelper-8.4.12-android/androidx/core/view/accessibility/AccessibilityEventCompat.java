package androidx.core.view.accessibility;

import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;

public final class AccessibilityEventCompat {
    public static final int CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION = 4;
    public static final int CONTENT_CHANGE_TYPE_SUBTREE = 1;
    public static final int CONTENT_CHANGE_TYPE_TEXT = 2;
    public static final int CONTENT_CHANGE_TYPE_UNDEFINED = 0;
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 0x4000;
    public static final int TYPE_ASSIST_READING_CONTEXT = 0x1000000;
    public static final int TYPE_GESTURE_DETECTION_END = 0x80000;
    public static final int TYPE_GESTURE_DETECTION_START = 0x40000;
    @Deprecated
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 0x400;
    @Deprecated
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 0x200;
    public static final int TYPE_TOUCH_INTERACTION_END = 0x200000;
    public static final int TYPE_TOUCH_INTERACTION_START = 0x100000;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 0x8000;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 0x10000;
    public static final int TYPE_VIEW_CONTEXT_CLICKED = 0x800000;
    @Deprecated
    public static final int TYPE_VIEW_HOVER_ENTER = 0x80;
    @Deprecated
    public static final int TYPE_VIEW_HOVER_EXIT = 0x100;
    @Deprecated
    public static final int TYPE_VIEW_SCROLLED = 0x1000;
    @Deprecated
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 0x2000;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 0x20000;
    public static final int TYPE_WINDOWS_CHANGED = 0x400000;
    @Deprecated
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 0x800;

    @Deprecated
    public static void appendRecord(AccessibilityEvent accessibilityEvent0, AccessibilityRecordCompat accessibilityRecordCompat0) {
        accessibilityEvent0.appendRecord(((AccessibilityRecord)accessibilityRecordCompat0.getImpl()));
    }

    @Deprecated
    public static AccessibilityRecordCompat asRecord(AccessibilityEvent accessibilityEvent0) {
        return new AccessibilityRecordCompat(accessibilityEvent0);
    }

    public static int getAction(AccessibilityEvent accessibilityEvent0) {
        return Build.VERSION.SDK_INT < 16 ? 0 : accessibilityEvent0.getAction();
    }

    public static int getContentChangeTypes(AccessibilityEvent accessibilityEvent0) {
        return Build.VERSION.SDK_INT < 19 ? 0 : accessibilityEvent0.getContentChangeTypes();
    }

    public static int getMovementGranularity(AccessibilityEvent accessibilityEvent0) {
        return Build.VERSION.SDK_INT < 16 ? 0 : accessibilityEvent0.getMovementGranularity();
    }

    @Deprecated
    public static AccessibilityRecordCompat getRecord(AccessibilityEvent accessibilityEvent0, int v) {
        return new AccessibilityRecordCompat(accessibilityEvent0.getRecord(v));
    }

    @Deprecated
    public static int getRecordCount(AccessibilityEvent accessibilityEvent0) {
        return accessibilityEvent0.getRecordCount();
    }

    public static void setAction(AccessibilityEvent accessibilityEvent0, int v) {
        if(Build.VERSION.SDK_INT >= 16) {
            accessibilityEvent0.setAction(v);
        }
    }

    public static void setContentChangeTypes(AccessibilityEvent accessibilityEvent0, int v) {
        if(Build.VERSION.SDK_INT >= 19) {
            accessibilityEvent0.setContentChangeTypes(v);
        }
    }

    public static void setMovementGranularity(AccessibilityEvent accessibilityEvent0, int v) {
        if(Build.VERSION.SDK_INT >= 16) {
            accessibilityEvent0.setMovementGranularity(v);
        }
    }
}

