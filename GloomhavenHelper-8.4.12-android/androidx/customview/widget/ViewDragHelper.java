package androidx.customview.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.view.ViewCompat;
import java.util.Arrays;

public class ViewDragHelper {
    public static abstract class Callback {
        public int clampViewPositionHorizontal(@NonNull View view0, int v, int v1) {
            return 0;
        }

        public int clampViewPositionVertical(@NonNull View view0, int v, int v1) {
            return 0;
        }

        public int getOrderedChildIndex(int v) [...] // Inlined contents

        public int getViewHorizontalDragRange(@NonNull View view0) {
            return 0;
        }

        public int getViewVerticalDragRange(@NonNull View view0) [...] // Inlined contents

        public void onEdgeDragStarted(int v, int v1) {
        }

        public boolean onEdgeLock(int v) {
            return false;
        }

        public void onEdgeTouched(int v, int v1) {
        }

        public void onViewCaptured(@NonNull View view0, int v) {
        }

        public void onViewDragStateChanged(int v) {
        }

        public void onViewPositionChanged(@NonNull View view0, int v, int v1, @Px int v2, @Px int v3) {
        }

        public void onViewReleased(@NonNull View view0, float f, float f1) {
        }

        public abstract boolean tryCaptureView(@NonNull View arg1, int arg2);
    }

    private static final int BASE_SETTLE_DURATION = 0x100;
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    private static final int EDGE_SIZE = 20;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "ViewDragHelper";
    private int mActivePointerId;
    private final Callback mCallback;
    private View mCapturedView;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private OverScroller mScroller;
    private final Runnable mSetIdleRunnable;
    private int mTouchSlop;
    private int mTrackingEdges;
    private VelocityTracker mVelocityTracker;
    private static final Interpolator sInterpolator;

    static {
        ViewDragHelper.sInterpolator = new Interpolator() {
            @Override  // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return (f - 1.0f) * (f - 1.0f) * (f - 1.0f) * (f - 1.0f) * (f - 1.0f) + 1.0f;
            }
        };
    }

    private ViewDragHelper(@NonNull Context context0, @NonNull ViewGroup viewGroup0, @NonNull Callback viewDragHelper$Callback0) {
        this.mActivePointerId = -1;
        this.mSetIdleRunnable = () -> {
            ViewDragHelper.this.mParentView.removeCallbacks(ViewDragHelper.this.mSetIdleRunnable);
            if(ViewDragHelper.this.mDragState != 0) {
                ViewDragHelper.this.mDragState = 0;
                ViewDragHelper.this.mCallback.onViewDragStateChanged(0);
                if(ViewDragHelper.this.mDragState == 0) {
                    ViewDragHelper.this.mCapturedView = null;
                }
            }
        };
        if(viewGroup0 == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if(viewDragHelper$Callback0 == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.mParentView = viewGroup0;
        this.mCallback = viewDragHelper$Callback0;
        ViewConfiguration viewConfiguration0 = ViewConfiguration.get(context0);
        this.mEdgeSize = (int)(context0.getResources().getDisplayMetrics().density * 20.0f + 0.5f);
        this.mTouchSlop = viewConfiguration0.getScaledTouchSlop();
        this.mMaxVelocity = (float)viewConfiguration0.getScaledMaximumFlingVelocity();
        this.mMinVelocity = (float)viewConfiguration0.getScaledMinimumFlingVelocity();
        this.mScroller = new OverScroller(context0, ViewDragHelper.sInterpolator);
    }

    public void abort() {
        this.cancel();
        if(this.mDragState == 2) {
            int v = this.mScroller.getCurrX();
            int v1 = this.mScroller.getCurrY();
            this.mScroller.abortAnimation();
            int v2 = this.mScroller.getCurrX();
            int v3 = this.mScroller.getCurrY();
            this.mCallback.onViewPositionChanged(this.mCapturedView, v2, v3, v2 - v, v3 - v1);
        }
        this.setDragState(0);
    }

    protected boolean canScroll(@NonNull View view0, boolean z, int v, int v1, int v2, int v3) {
        if(view0 instanceof ViewGroup) {
            int v4 = view0.getScrollX();
            int v5 = view0.getScrollY();
            for(int v6 = ((ViewGroup)view0).getChildCount() - 1; v6 >= 0; --v6) {
                View view1 = ((ViewGroup)view0).getChildAt(v6);
                int v7 = v2 + v4;
                if(v7 >= view1.getLeft() && v7 < view1.getRight()) {
                    int v8 = v3 + v5;
                    if(v8 >= view1.getTop() && v8 < view1.getBottom() && this.canScroll(view1, true, v, v1, v7 - view1.getLeft(), v8 - view1.getTop())) {
                        return true;
                    }
                }
            }
        }
        return z && (view0.canScrollHorizontally(-v) || view0.canScrollVertically(-v1));
    }

    public void cancel() {
        this.mActivePointerId = -1;
        this.clearMotionHistory();
        VelocityTracker velocityTracker0 = this.mVelocityTracker;
        if(velocityTracker0 != null) {
            velocityTracker0.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void captureChildView(@NonNull View view0, int v) {
        if(view0.getParent() != this.mParentView) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper\'s tracked parent view (" + this.mParentView + ")");
        }
        this.mCapturedView = view0;
        this.mActivePointerId = v;
        this.mCallback.onViewCaptured(view0, v);
        this.setDragState(1);
    }

    private boolean checkNewEdgeDrag(float f, float f1, int v, int v1) {
        float f2 = Math.abs(f);
        float f3 = Math.abs(f1);
        if((this.mInitialEdgesTouched[v] & v1) == v1 && (this.mTrackingEdges & v1) != 0 && (this.mEdgeDragsLocked[v] & v1) != v1 && (this.mEdgeDragsInProgress[v] & v1) != v1 && (f2 > ((float)this.mTouchSlop) || f3 > ((float)this.mTouchSlop))) {
            if(f2 < f3 * 0.5f && this.mCallback.onEdgeLock(v1)) {
                this.mEdgeDragsLocked[v] |= v1;
                return false;
            }
            return (this.mEdgeDragsInProgress[v] & v1) == 0 && f2 > ((float)this.mTouchSlop);
        }
        return false;
    }

    // 去混淆评级： 低(30)
    private boolean checkTouchSlop(View view0, float f, float f1) {
        return view0 == null ? false : this.mCallback.getViewHorizontalDragRange(view0) > 0 && Math.abs(f) > ((float)this.mTouchSlop);
    }

    public boolean checkTouchSlop(int v) {
        for(int v1 = 0; v1 < this.mInitialMotionX.length; ++v1) {
            if(this.checkTouchSlop(v, v1)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTouchSlop(int v, int v1) {
        if(!this.isPointerDown(v1)) {
            return false;
        }
        boolean z = (v & 1) == 1;
        boolean z1 = (v & 2) == 2;
        float f = this.mLastMotionX[v1] - this.mInitialMotionX[v1];
        float f1 = this.mLastMotionY[v1] - this.mInitialMotionY[v1];
        if(z && z1) {
            return f * f + f1 * f1 > ((float)(this.mTouchSlop * this.mTouchSlop));
        }
        return z ? Math.abs(f) > ((float)this.mTouchSlop) : z1 && Math.abs(f1) > ((float)this.mTouchSlop);
    }

    private float clampMag(float f, float f1, float f2) {
        float f3 = Math.abs(f);
        if(f3 < f1) {
            return 0.0f;
        }
        if(f3 > f2) {
            return f > 0.0f ? f2 : -f2;
        }
        return f;
    }

    private int clampMag(int v, int v1, int v2) {
        int v3 = Math.abs(v);
        if(v3 < v1) {
            return 0;
        }
        if(v3 > v2) {
            return v > 0 ? v2 : -v2;
        }
        return v;
    }

    private void clearMotionHistory() {
        float[] arr_f = this.mInitialMotionX;
        if(arr_f == null) {
            return;
        }
        Arrays.fill(arr_f, 0.0f);
        Arrays.fill(this.mInitialMotionY, 0.0f);
        Arrays.fill(this.mLastMotionX, 0.0f);
        Arrays.fill(this.mLastMotionY, 0.0f);
        Arrays.fill(this.mInitialEdgesTouched, 0);
        Arrays.fill(this.mEdgeDragsInProgress, 0);
        Arrays.fill(this.mEdgeDragsLocked, 0);
        this.mPointersDown = 0;
    }

    private void clearMotionHistory(int v) {
        if(this.mInitialMotionX != null && this.isPointerDown(v)) {
            this.mInitialMotionX[v] = 0.0f;
            this.mInitialMotionY[v] = 0.0f;
            this.mLastMotionX[v] = 0.0f;
            this.mLastMotionY[v] = 0.0f;
            this.mInitialEdgesTouched[v] = 0;
            this.mEdgeDragsInProgress[v] = 0;
            this.mEdgeDragsLocked[v] = 0;
            this.mPointersDown &= ~(1 << v);
        }
    }

    private int computeAxisDuration(int v, int v1, int v2) {
        if(v == 0) {
            return 0;
        }
        int v3 = this.mParentView.getWidth();
        int v4 = Math.abs(v1);
        return v4 <= 0 ? Math.min(((int)((((float)Math.abs(v)) / ((float)v2) + 1.0f) * 256.0f)), 600) : Math.min(Math.round(Math.abs((((float)(v3 / 2)) + this.distanceInfluenceForSnapDuration(Math.min(1.0f, ((float)Math.abs(v)) / ((float)v3))) * ((float)(v3 / 2))) / ((float)v4)) * 1000.0f) * 4, 600);
    }

    private int computeSettleDuration(View view0, int v, int v1, int v2, int v3) {
        float f1;
        float f;
        int v4 = this.clampMag(v2, ((int)this.mMinVelocity), ((int)this.mMaxVelocity));
        int v5 = this.clampMag(v3, ((int)this.mMinVelocity), ((int)this.mMaxVelocity));
        int v6 = Math.abs(v);
        int v7 = Math.abs(v1);
        int v8 = Math.abs(v4);
        int v9 = Math.abs(v5);
        int v10 = v8 + v9;
        int v11 = v6 + v7;
        if(v4 != 0) {
            f = (float)v8;
            f1 = (float)v10;
            return v5 == 0 ? ((int)(((float)this.computeAxisDuration(v, v4, this.mCallback.getViewHorizontalDragRange(view0))) * (f / f1) + ((float)this.computeAxisDuration(v1, 0, 0)) * (((float)v7) / ((float)v11)))) : ((int)(((float)this.computeAxisDuration(v, v4, this.mCallback.getViewHorizontalDragRange(view0))) * (f / f1) + ((float)this.computeAxisDuration(v1, v5, 0)) * (((float)v9) / ((float)v10))));
        }
        f = (float)v6;
        f1 = (float)v11;
        return v5 == 0 ? ((int)(((float)this.computeAxisDuration(v, 0, this.mCallback.getViewHorizontalDragRange(view0))) * (f / f1) + ((float)this.computeAxisDuration(v1, 0, 0)) * (((float)v7) / ((float)v11)))) : ((int)(((float)this.computeAxisDuration(v, 0, this.mCallback.getViewHorizontalDragRange(view0))) * (f / f1) + ((float)this.computeAxisDuration(v1, v5, 0)) * (((float)v9) / ((float)v10))));
    }

    public boolean continueSettling(boolean z) {
        if(this.mDragState == 2) {
            boolean z1 = this.mScroller.computeScrollOffset();
            int v = this.mScroller.getCurrX();
            int v1 = this.mScroller.getCurrY();
            int v2 = v - this.mCapturedView.getLeft();
            int v3 = v1 - this.mCapturedView.getTop();
            if(v2 != 0) {
                ViewCompat.offsetLeftAndRight(this.mCapturedView, v2);
            }
            if(v3 != 0) {
                ViewCompat.offsetTopAndBottom(this.mCapturedView, v3);
            }
            if(v2 != 0 || v3 != 0) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, v, v1, v2, v3);
            }
            if(z1 && v == this.mScroller.getFinalX() && v1 == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                z1 = false;
            }
            if(!z1) {
                if(z) {
                    this.mParentView.post(this.mSetIdleRunnable);
                    return this.mDragState == 2;
                }
                this.setDragState(0);
            }
        }
        return this.mDragState == 2;
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup0, float f, @NonNull Callback viewDragHelper$Callback0) {
        ViewDragHelper viewDragHelper0 = ViewDragHelper.create(viewGroup0, viewDragHelper$Callback0);
        viewDragHelper0.mTouchSlop = (int)(((float)viewDragHelper0.mTouchSlop) * (1.0f / f));
        return viewDragHelper0;
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup0, @NonNull Callback viewDragHelper$Callback0) {
        return new ViewDragHelper(viewGroup0.getContext(), viewGroup0, viewDragHelper$Callback0);
    }

    private void dispatchViewReleased(float f, float f1) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f, f1);
        this.mReleaseInProgress = false;
        if(this.mDragState == 1) {
            this.setDragState(0);
        }
    }

    private float distanceInfluenceForSnapDuration(float f) {
        return (float)Math.sin((f - 0.5f) * 0.471239f);
    }

    private void dragTo(int v, int v1, int v2, int v3) {
        int v9;
        int v7;
        int v4 = this.mCapturedView.getLeft();
        int v5 = this.mCapturedView.getTop();
        if(v2 == 0) {
            v7 = v;
        }
        else {
            int v6 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, v, v2);
            ViewCompat.offsetLeftAndRight(this.mCapturedView, v6 - v4);
            v7 = v6;
        }
        if(v3 == 0) {
            v9 = v1;
        }
        else {
            int v8 = this.mCallback.clampViewPositionVertical(this.mCapturedView, v1, v3);
            ViewCompat.offsetTopAndBottom(this.mCapturedView, v8 - v5);
            v9 = v8;
        }
        if(v2 != 0 || v3 != 0) {
            this.mCallback.onViewPositionChanged(this.mCapturedView, v7, v9, v7 - v4, v9 - v5);
        }
    }

    private void ensureMotionHistorySizeForId(int v) {
        if(this.mInitialMotionX == null || this.mInitialMotionX.length <= v) {
            float[] arr_f = new float[v + 1];
            float[] arr_f1 = new float[v + 1];
            float[] arr_f2 = new float[v + 1];
            float[] arr_f3 = new float[v + 1];
            int[] arr_v = new int[v + 1];
            int[] arr_v1 = new int[v + 1];
            int[] arr_v2 = new int[v + 1];
            float[] arr_f4 = this.mInitialMotionX;
            if(arr_f4 != null) {
                System.arraycopy(arr_f4, 0, arr_f, 0, arr_f4.length);
                System.arraycopy(this.mInitialMotionY, 0, arr_f1, 0, this.mInitialMotionY.length);
                System.arraycopy(this.mLastMotionX, 0, arr_f2, 0, this.mLastMotionX.length);
                System.arraycopy(this.mLastMotionY, 0, arr_f3, 0, this.mLastMotionY.length);
                System.arraycopy(this.mInitialEdgesTouched, 0, arr_v, 0, this.mInitialEdgesTouched.length);
                System.arraycopy(this.mEdgeDragsInProgress, 0, arr_v1, 0, this.mEdgeDragsInProgress.length);
                System.arraycopy(this.mEdgeDragsLocked, 0, arr_v2, 0, this.mEdgeDragsLocked.length);
            }
            this.mInitialMotionX = arr_f;
            this.mInitialMotionY = arr_f1;
            this.mLastMotionX = arr_f2;
            this.mLastMotionY = arr_f3;
            this.mInitialEdgesTouched = arr_v;
            this.mEdgeDragsInProgress = arr_v1;
            this.mEdgeDragsLocked = arr_v2;
        }
    }

    @Nullable
    public View findTopChildUnder(int v, int v1) {
        for(int v2 = this.mParentView.getChildCount() - 1; v2 >= 0; --v2) {
            View view0 = this.mParentView.getChildAt(v2);
            if(v >= view0.getLeft() && v < view0.getRight() && v1 >= view0.getTop() && v1 < view0.getBottom()) {
                return view0;
            }
        }
        return null;
    }

    public void flingCapturedView(int v, int v1, int v2, int v3) {
        if(!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
        }
        this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), ((int)this.mVelocityTracker.getXVelocity(this.mActivePointerId)), ((int)this.mVelocityTracker.getYVelocity(this.mActivePointerId)), v, v2, v1, v3);
        this.setDragState(2);
    }

    private boolean forceSettleCapturedViewAt(int v, int v1, int v2, int v3) {
        int v4 = this.mCapturedView.getLeft();
        int v5 = this.mCapturedView.getTop();
        int v6 = v - v4;
        int v7 = v1 - v5;
        if(v6 == 0 && v7 == 0) {
            this.mScroller.abortAnimation();
            this.setDragState(0);
            return false;
        }
        int v8 = this.computeSettleDuration(this.mCapturedView, v6, v7, v2, v3);
        this.mScroller.startScroll(v4, v5, v6, v7, v8);
        this.setDragState(2);
        return true;
    }

    public int getActivePointerId() {
        return this.mActivePointerId;
    }

    @Nullable
    public View getCapturedView() {
        return this.mCapturedView;
    }

    @Px
    public int getEdgeSize() {
        return this.mEdgeSize;
    }

    private int getEdgesTouched(int v, int v1) {
        int v2 = v >= this.mParentView.getLeft() + this.mEdgeSize ? 0 : 1;
        if(v1 < this.mParentView.getTop() + this.mEdgeSize) {
            v2 |= 4;
        }
        if(v > this.mParentView.getRight() - this.mEdgeSize) {
            v2 |= 2;
        }
        return v1 <= this.mParentView.getBottom() - this.mEdgeSize ? v2 : v2 | 8;
    }

    public float getMinVelocity() {
        return this.mMinVelocity;
    }

    @Px
    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public int getViewDragState() {
        return this.mDragState;
    }

    public boolean isCapturedViewUnder(int v, int v1) {
        return this.isViewUnder(this.mCapturedView, v, v1);
    }

    public boolean isEdgeTouched(int v) {
        for(int v1 = 0; v1 < this.mInitialEdgesTouched.length; ++v1) {
            if(this.isEdgeTouched(v, v1)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEdgeTouched(int v, int v1) {
        return this.isPointerDown(v1) && (v & this.mInitialEdgesTouched[v1]) != 0;
    }

    public boolean isPointerDown(int v) {
        return (1 << v & this.mPointersDown) != 0;
    }

    private boolean isValidPointerForActionMove(int v) {
        if(!this.isPointerDown(v)) {
            Log.e("ViewDragHelper", "Ignoring pointerId=" + v + " because ACTION_DOWN was not received " + "for this pointer before ACTION_MOVE. It likely happened because " + " ViewDragHelper did not receive all the events in the event stream.");
            return false;
        }
        return true;
    }

    public boolean isViewUnder(@Nullable View view0, int v, int v1) {
        return view0 == null ? false : v >= view0.getLeft() && v < view0.getRight() && v1 >= view0.getTop() && v1 < view0.getBottom();
    }

    public void processTouchEvent(@NonNull MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        int v1 = motionEvent0.getActionIndex();
        if(v == 0) {
            this.cancel();
        }
        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent0);
        int v2 = 0;
        switch(v) {
            case 0: {
                float f = motionEvent0.getX();
                float f1 = motionEvent0.getY();
                int v7 = motionEvent0.getPointerId(0);
                View view0 = this.findTopChildUnder(((int)f), ((int)f1));
                this.saveInitialMotion(f, f1, v7);
                this.tryCaptureViewForDrag(view0, v7);
                int v8 = this.mInitialEdgesTouched[v7];
                int v9 = this.mTrackingEdges;
                if((v8 & v9) != 0) {
                    this.mCallback.onEdgeTouched(v8 & v9, v7);
                    return;
                }
                break;
            }
            case 1: {
                if(this.mDragState == 1) {
                    this.releaseViewForPointerUp();
                }
                this.cancel();
                return;
            }
            case 2: {
                if(this.mDragState != 1) {
                    int v14 = motionEvent0.getPointerCount();
                    while(v2 < v14) {
                        int v15 = motionEvent0.getPointerId(v2);
                        if(this.isValidPointerForActionMove(v15)) {
                            float f4 = motionEvent0.getX(v2);
                            float f5 = motionEvent0.getY(v2);
                            float f6 = f4 - this.mInitialMotionX[v15];
                            float f7 = f5 - this.mInitialMotionY[v15];
                            this.reportNewEdgeDrags(f6, f7, v15);
                            if(this.mDragState == 1) {
                                break;
                            }
                            View view1 = this.findTopChildUnder(((int)f4), ((int)f5));
                            if(!this.checkTouchSlop(view1, f6, f7) || !this.tryCaptureViewForDrag(view1, v15)) {
                                goto label_68;
                            }
                            break;
                        }
                    label_68:
                        ++v2;
                    }
                    this.saveLastMotion(motionEvent0);
                    return;
                }
                else if(this.isValidPointerForActionMove(this.mActivePointerId)) {
                    int v10 = motionEvent0.findPointerIndex(this.mActivePointerId);
                    float f2 = motionEvent0.getX(v10);
                    float f3 = motionEvent0.getY(v10);
                    int v11 = this.mActivePointerId;
                    int v12 = (int)(f2 - this.mLastMotionX[v11]);
                    int v13 = (int)(f3 - this.mLastMotionY[v11]);
                    this.dragTo(this.mCapturedView.getLeft() + v12, this.mCapturedView.getTop() + v13, v12, v13);
                    this.saveLastMotion(motionEvent0);
                    return;
                }
                break;
            }
            case 3: {
                if(this.mDragState == 1) {
                    this.dispatchViewReleased(0.0f, 0.0f);
                }
                this.cancel();
                return;
            }
            case 5: {
                int v16 = motionEvent0.getPointerId(v1);
                float f8 = motionEvent0.getX(v1);
                float f9 = motionEvent0.getY(v1);
                this.saveInitialMotion(f8, f9, v16);
                if(this.mDragState == 0) {
                    this.tryCaptureViewForDrag(this.findTopChildUnder(((int)f8), ((int)f9)), v16);
                    int v17 = this.mInitialEdgesTouched[v16];
                    int v18 = this.mTrackingEdges;
                    if((v17 & v18) != 0) {
                        this.mCallback.onEdgeTouched(v17 & v18, v16);
                        return;
                    }
                }
                else if(this.isCapturedViewUnder(((int)f8), ((int)f9))) {
                    this.tryCaptureViewForDrag(this.mCapturedView, v16);
                    return;
                }
                break;
            }
            case 6: {
                int v3 = motionEvent0.getPointerId(v1);
                if(this.mDragState == 1 && v3 == this.mActivePointerId) {
                    int v4 = motionEvent0.getPointerCount();
                    while(true) {
                        int v5 = -1;
                        if(v2 >= v4) {
                            break;
                        }
                        int v6 = motionEvent0.getPointerId(v2);
                        if(v6 != this.mActivePointerId && (this.findTopChildUnder(((int)motionEvent0.getX(v2)), ((int)motionEvent0.getY(v2))) == this.mCapturedView && this.tryCaptureViewForDrag(this.mCapturedView, v6))) {
                            v5 = this.mActivePointerId;
                            break;
                        }
                        ++v2;
                    }
                    if(v5 == -1) {
                        this.releaseViewForPointerUp();
                    }
                }
                this.clearMotionHistory(v3);
            }
        }
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        this.dispatchViewReleased(this.clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), this.clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    private void reportNewEdgeDrags(float f, float f1, int v) {
        int v1 = this.checkNewEdgeDrag(f, f1, v, 1) ? 1 : 0;
        if(this.checkNewEdgeDrag(f1, f, v, 4)) {
            v1 |= 4;
        }
        if(this.checkNewEdgeDrag(f, f1, v, 2)) {
            v1 |= 2;
        }
        if(this.checkNewEdgeDrag(f1, f, v, 8)) {
            v1 |= 8;
        }
        if(v1 != 0) {
            this.mEdgeDragsInProgress[v] |= v1;
            this.mCallback.onEdgeDragStarted(v1, v);
        }
    }

    private void saveInitialMotion(float f, float f1, int v) {
        this.ensureMotionHistorySizeForId(v);
        float[] arr_f = this.mInitialMotionX;
        this.mLastMotionX[v] = f;
        arr_f[v] = f;
        float[] arr_f1 = this.mInitialMotionY;
        this.mLastMotionY[v] = f1;
        arr_f1[v] = f1;
        int[] arr_v = this.mInitialEdgesTouched;
        arr_v[v] = this.getEdgesTouched(((int)f), ((int)f1));
        this.mPointersDown |= 1 << v;
    }

    private void saveLastMotion(MotionEvent motionEvent0) {
        int v = motionEvent0.getPointerCount();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = motionEvent0.getPointerId(v1);
            if(this.isValidPointerForActionMove(v2)) {
                float f = motionEvent0.getX(v1);
                float f1 = motionEvent0.getY(v1);
                this.mLastMotionX[v2] = f;
                this.mLastMotionY[v2] = f1;
            }
        }
    }

    // 检测为 Lambda 实现
    void setDragState(int v) [...]

    public void setEdgeTrackingEnabled(int v) {
        this.mTrackingEdges = v;
    }

    public void setMinVelocity(float f) {
        this.mMinVelocity = f;
    }

    public boolean settleCapturedViewAt(int v, int v1) {
        if(!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        }
        return this.forceSettleCapturedViewAt(v, v1, ((int)this.mVelocityTracker.getXVelocity(this.mActivePointerId)), ((int)this.mVelocityTracker.getYVelocity(this.mActivePointerId)));
    }

    public boolean shouldInterceptTouchEvent(@NonNull MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        int v1 = motionEvent0.getActionIndex();
        if(v == 0) {
            this.cancel();
        }
        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent0);
        switch(v) {
            case 0: {
                float f = motionEvent0.getX();
                float f1 = motionEvent0.getY();
                int v2 = motionEvent0.getPointerId(0);
                this.saveInitialMotion(f, f1, v2);
                View view0 = this.findTopChildUnder(((int)f), ((int)f1));
                if(view0 == this.mCapturedView && this.mDragState == 2) {
                    this.tryCaptureViewForDrag(view0, v2);
                }
                int v3 = this.mInitialEdgesTouched[v2];
                int v4 = this.mTrackingEdges;
                if((v3 & v4) != 0) {
                    this.mCallback.onEdgeTouched(v3 & v4, v2);
                    return this.mDragState == 1;
                }
                break;
            }
            case 2: {
                if(this.mInitialMotionX != null && this.mInitialMotionY != null) {
                    int v5 = motionEvent0.getPointerCount();
                    int v6 = 0;
                    while(v6 < v5) {
                        int v7 = motionEvent0.getPointerId(v6);
                        if(this.isValidPointerForActionMove(v7)) {
                            float f2 = motionEvent0.getX(v6);
                            float f3 = motionEvent0.getY(v6);
                            float f4 = f2 - this.mInitialMotionX[v7];
                            float f5 = f3 - this.mInitialMotionY[v7];
                            View view1 = this.findTopChildUnder(((int)f2), ((int)f3));
                            boolean z = view1 != null && this.checkTouchSlop(view1, f4, f5);
                            if(z) {
                                int v8 = view1.getLeft();
                                int v9 = view1.getTop();
                                this.mCallback.clampViewPositionVertical(view1, v9 + ((int)f5), ((int)f5));
                                int v10 = this.mCallback.getViewHorizontalDragRange(view1);
                                if(v10 != 0 && (v10 <= 0 || this.mCallback.clampViewPositionHorizontal(view1, v8 + ((int)f4), ((int)f4)) != v8)) {
                                    goto label_43;
                                }
                                break;
                            }
                        label_43:
                            this.reportNewEdgeDrags(f4, f5, v7);
                            if(this.mDragState != 1 && (!z || !this.tryCaptureViewForDrag(view1, v7))) {
                                goto label_45;
                            }
                            break;
                        }
                    label_45:
                        ++v6;
                    }
                    this.saveLastMotion(motionEvent0);
                    return this.mDragState == 1;
                }
                break;
            }
            case 1: 
            case 3: {
                this.cancel();
                return this.mDragState == 1;
            }
            case 5: {
                int v11 = motionEvent0.getPointerId(v1);
                float f6 = motionEvent0.getX(v1);
                float f7 = motionEvent0.getY(v1);
                this.saveInitialMotion(f6, f7, v11);
                int v12 = this.mDragState;
                if(v12 == 0) {
                    int v13 = this.mInitialEdgesTouched[v11];
                    int v14 = this.mTrackingEdges;
                    if((v13 & v14) != 0) {
                        this.mCallback.onEdgeTouched(v13 & v14, v11);
                        return this.mDragState == 1;
                    }
                }
                else if(v12 == 2) {
                    View view2 = this.findTopChildUnder(((int)f6), ((int)f7));
                    if(view2 == this.mCapturedView) {
                        this.tryCaptureViewForDrag(view2, v11);
                        return this.mDragState == 1;
                    }
                }
                break;
            }
            case 6: {
                this.clearMotionHistory(motionEvent0.getPointerId(v1));
                return this.mDragState == 1;
            }
            default: {
                return this.mDragState == 1;
            }
        }
        return this.mDragState == 1;
    }

    public boolean smoothSlideViewTo(@NonNull View view0, int v, int v1) {
        this.mCapturedView = view0;
        this.mActivePointerId = -1;
        boolean z = this.forceSettleCapturedViewAt(v, v1, 0, 0);
        if(!z && this.mDragState == 0 && this.mCapturedView != null) {
            this.mCapturedView = null;
        }
        return z;
    }

    boolean tryCaptureViewForDrag(View view0, int v) {
        if(view0 == this.mCapturedView && this.mActivePointerId == v) {
            return true;
        }
        if(view0 != null && this.mCallback.tryCaptureView(view0, v)) {
            this.mActivePointerId = v;
            this.captureChildView(view0, v);
            return true;
        }
        return false;
    }

    class androidx.customview.widget.ViewDragHelper.2 implements Runnable {
        @Override
        public void run() {
            ViewDragHelper.this.setDragState(0);
        }
    }

}

