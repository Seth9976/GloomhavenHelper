package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

public class NestedScrollingParentHelper {
    private int mNestedScrollAxes;
    private final ViewGroup mViewGroup;

    public NestedScrollingParentHelper(@NonNull ViewGroup viewGroup0) {
        this.mViewGroup = viewGroup0;
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollAxes;
    }

    public void onNestedScrollAccepted(@NonNull View view0, @NonNull View view1, int v) {
        this.onNestedScrollAccepted(view0, view1, v, 0);
    }

    public void onNestedScrollAccepted(@NonNull View view0, @NonNull View view1, int v, int v1) {
        this.mNestedScrollAxes = v;
    }

    public void onStopNestedScroll(@NonNull View view0) {
        this.onStopNestedScroll(view0, 0);
    }

    public void onStopNestedScroll(@NonNull View view0, int v) {
        this.mNestedScrollAxes = 0;
    }
}

