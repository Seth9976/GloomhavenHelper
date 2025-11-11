package androidx.core.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

@Deprecated
public final class ScrollerCompat {
    OverScroller mScroller;

    ScrollerCompat(Context context0, Interpolator interpolator0) {
        this.mScroller = interpolator0 == null ? new OverScroller(context0) : new OverScroller(context0, interpolator0);
    }

    @Deprecated
    public void abortAnimation() {
        this.mScroller.abortAnimation();
    }

    @Deprecated
    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }

    @Deprecated
    public static ScrollerCompat create(Context context0) {
        return ScrollerCompat.create(context0, null);
    }

    @Deprecated
    public static ScrollerCompat create(Context context0, Interpolator interpolator0) {
        return new ScrollerCompat(context0, interpolator0);
    }

    @Deprecated
    public void fling(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        this.mScroller.fling(v, v1, v2, v3, v4, v5, v6, v7);
    }

    @Deprecated
    public void fling(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9) {
        this.mScroller.fling(v, v1, v2, v3, v4, v5, v6, v7, v8, v9);
    }

    @Deprecated
    public float getCurrVelocity() {
        return this.mScroller.getCurrVelocity();
    }

    @Deprecated
    public int getCurrX() {
        return this.mScroller.getCurrX();
    }

    @Deprecated
    public int getCurrY() {
        return this.mScroller.getCurrY();
    }

    @Deprecated
    public int getFinalX() {
        return this.mScroller.getFinalX();
    }

    @Deprecated
    public int getFinalY() {
        return this.mScroller.getFinalY();
    }

    @Deprecated
    public boolean isFinished() {
        return this.mScroller.isFinished();
    }

    @Deprecated
    public boolean isOverScrolled() {
        return this.mScroller.isOverScrolled();
    }

    @Deprecated
    public void notifyHorizontalEdgeReached(int v, int v1, int v2) {
        this.mScroller.notifyHorizontalEdgeReached(v, v1, v2);
    }

    @Deprecated
    public void notifyVerticalEdgeReached(int v, int v1, int v2) {
        this.mScroller.notifyVerticalEdgeReached(v, v1, v2);
    }

    @Deprecated
    public boolean springBack(int v, int v1, int v2, int v3, int v4, int v5) {
        return this.mScroller.springBack(v, v1, v2, v3, v4, v5);
    }

    @Deprecated
    public void startScroll(int v, int v1, int v2, int v3) {
        this.mScroller.startScroll(v, v1, v2, v3);
    }

    @Deprecated
    public void startScroll(int v, int v1, int v2, int v3, int v4) {
        this.mScroller.startScroll(v, v1, v2, v3, v4);
    }
}

