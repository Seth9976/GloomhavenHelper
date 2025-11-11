package androidx.core.view;

import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NestedScrollingChildHelper {
    private boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParentNonTouch;
    private ViewParent mNestedScrollingParentTouch;
    private int[] mTempNestedScrollConsumed;
    private final View mView;

    public NestedScrollingChildHelper(@NonNull View view0) {
        this.mView = view0;
    }

    public boolean dispatchNestedFling(float f, float f1, boolean z) {
        if(this.isNestedScrollingEnabled()) {
            ViewParent viewParent0 = this.getNestedScrollingParentForType(0);
            return viewParent0 == null ? false : ViewParentCompat.onNestedFling(viewParent0, this.mView, f, f1, z);
        }
        return false;
    }

    public boolean dispatchNestedPreFling(float f, float f1) {
        if(this.isNestedScrollingEnabled()) {
            ViewParent viewParent0 = this.getNestedScrollingParentForType(0);
            return viewParent0 == null ? false : ViewParentCompat.onNestedPreFling(viewParent0, this.mView, f, f1);
        }
        return false;
    }

    public boolean dispatchNestedPreScroll(int v, int v1, @Nullable int[] arr_v, @Nullable int[] arr_v1) {
        return this.dispatchNestedPreScroll(v, v1, arr_v, arr_v1, 0);
    }

    public boolean dispatchNestedPreScroll(int v, int v1, @Nullable int[] arr_v, @Nullable int[] arr_v1, int v2) {
        int v4;
        int v3;
        if(this.isNestedScrollingEnabled()) {
            ViewParent viewParent0 = this.getNestedScrollingParentForType(v2);
            if(viewParent0 == null) {
                return false;
            }
            if(v != 0 || v1 != 0) {
                if(arr_v1 == null) {
                    v3 = 0;
                    v4 = 0;
                }
                else {
                    this.mView.getLocationInWindow(arr_v1);
                    v3 = arr_v1[0];
                    v4 = arr_v1[1];
                }
                if(arr_v == null) {
                    if(this.mTempNestedScrollConsumed == null) {
                        this.mTempNestedScrollConsumed = new int[2];
                    }
                    arr_v = this.mTempNestedScrollConsumed;
                }
                arr_v[0] = 0;
                arr_v[1] = 0;
                ViewParentCompat.onNestedPreScroll(viewParent0, this.mView, v, v1, arr_v, v2);
                if(arr_v1 != null) {
                    this.mView.getLocationInWindow(arr_v1);
                    arr_v1[0] -= v3;
                    arr_v1[1] -= v4;
                }
                return arr_v[0] != 0 || arr_v[1] != 0;
            }
            else if(arr_v1 != null) {
                arr_v1[0] = 0;
                arr_v1[1] = 0;
                return false;
            }
        }
        return false;
    }

    public boolean dispatchNestedScroll(int v, int v1, int v2, int v3, @Nullable int[] arr_v) {
        return this.dispatchNestedScroll(v, v1, v2, v3, arr_v, 0);
    }

    public boolean dispatchNestedScroll(int v, int v1, int v2, int v3, @Nullable int[] arr_v, int v4) {
        int v6;
        int v5;
        if(this.isNestedScrollingEnabled()) {
            ViewParent viewParent0 = this.getNestedScrollingParentForType(v4);
            if(viewParent0 == null) {
                return false;
            }
            if(v != 0 || v1 != 0 || v2 != 0 || v3 != 0) {
                if(arr_v == null) {
                    v5 = 0;
                    v6 = 0;
                }
                else {
                    this.mView.getLocationInWindow(arr_v);
                    v5 = arr_v[0];
                    v6 = arr_v[1];
                }
                ViewParentCompat.onNestedScroll(viewParent0, this.mView, v, v1, v2, v3, v4);
                if(arr_v != null) {
                    this.mView.getLocationInWindow(arr_v);
                    arr_v[0] -= v5;
                    arr_v[1] -= v6;
                }
                return true;
            }
            else if(arr_v != null) {
                arr_v[0] = 0;
                arr_v[1] = 0;
                return false;
            }
        }
        return false;
    }

    private ViewParent getNestedScrollingParentForType(int v) {
        switch(v) {
            case 0: {
                return this.mNestedScrollingParentTouch;
            }
            case 1: {
                return this.mNestedScrollingParentNonTouch;
            }
            default: {
                return null;
            }
        }
    }

    public boolean hasNestedScrollingParent() {
        return this.hasNestedScrollingParent(0);
    }

    public boolean hasNestedScrollingParent(int v) {
        return this.getNestedScrollingParentForType(v) != null;
    }

    public boolean isNestedScrollingEnabled() {
        return this.mIsNestedScrollingEnabled;
    }

    public void onDetachedFromWindow() {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void onStopNestedScroll(@NonNull View view0) {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void setNestedScrollingEnabled(boolean z) {
        if(this.mIsNestedScrollingEnabled) {
            ViewCompat.stopNestedScroll(this.mView);
        }
        this.mIsNestedScrollingEnabled = z;
    }

    private void setNestedScrollingParentForType(int v, ViewParent viewParent0) {
        switch(v) {
            case 0: {
                this.mNestedScrollingParentTouch = viewParent0;
                return;
            }
            case 1: {
                this.mNestedScrollingParentNonTouch = viewParent0;
            }
        }
    }

    public boolean startNestedScroll(int v) {
        return this.startNestedScroll(v, 0);
    }

    public boolean startNestedScroll(int v, int v1) {
        if(this.hasNestedScrollingParent(v1)) {
            return true;
        }
        if(this.isNestedScrollingEnabled()) {
            ViewParent viewParent0 = this.mView.getParent();
            View view0 = this.mView;
            while(viewParent0 != null) {
                if(ViewParentCompat.onStartNestedScroll(viewParent0, view0, this.mView, v, v1)) {
                    this.setNestedScrollingParentForType(v1, viewParent0);
                    ViewParentCompat.onNestedScrollAccepted(viewParent0, view0, this.mView, v, v1);
                    return true;
                }
                if(viewParent0 instanceof View) {
                    view0 = (View)viewParent0;
                }
                viewParent0 = viewParent0.getParent();
            }
        }
        return false;
    }

    public void stopNestedScroll() {
        this.stopNestedScroll(0);
    }

    public void stopNestedScroll(int v) {
        ViewParent viewParent0 = this.getNestedScrollingParentForType(v);
        if(viewParent0 != null) {
            ViewParentCompat.onStopNestedScroll(viewParent0, this.mView, v);
            this.setNestedScrollingParentForType(v, null);
        }
    }
}

