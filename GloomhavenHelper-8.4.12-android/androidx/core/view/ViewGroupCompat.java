package androidx.core.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.core.R.id;

public final class ViewGroupCompat {
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;

    public static int getLayoutMode(@NonNull ViewGroup viewGroup0) {
        return Build.VERSION.SDK_INT < 18 ? 0 : viewGroup0.getLayoutMode();
    }

    public static int getNestedScrollAxes(@NonNull ViewGroup viewGroup0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return viewGroup0.getNestedScrollAxes();
        }
        return viewGroup0 instanceof NestedScrollingParent ? ((NestedScrollingParent)viewGroup0).getNestedScrollAxes() : 0;
    }

    public static boolean isTransitionGroup(@NonNull ViewGroup viewGroup0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return viewGroup0.isTransitionGroup();
        }
        Boolean boolean0 = (Boolean)viewGroup0.getTag(id.tag_transition_group);
        return boolean0 != null && boolean0.booleanValue() || viewGroup0.getBackground() != null || ViewCompat.getTransitionName(viewGroup0) != null;
    }

    @Deprecated
    public static boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup0, View view0, AccessibilityEvent accessibilityEvent0) {
        return viewGroup0.onRequestSendAccessibilityEvent(view0, accessibilityEvent0);
    }

    public static void setLayoutMode(@NonNull ViewGroup viewGroup0, int v) {
        if(Build.VERSION.SDK_INT >= 18) {
            viewGroup0.setLayoutMode(v);
        }
    }

    @Deprecated
    public static void setMotionEventSplittingEnabled(ViewGroup viewGroup0, boolean z) {
        viewGroup0.setMotionEventSplittingEnabled(z);
    }

    public static void setTransitionGroup(@NonNull ViewGroup viewGroup0, boolean z) {
        if(Build.VERSION.SDK_INT >= 21) {
            viewGroup0.setTransitionGroup(z);
            return;
        }
        viewGroup0.setTag(id.tag_transition_group, Boolean.valueOf(z));
    }
}

