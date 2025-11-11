package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ListViewCompat;

public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingChild, NestedScrollingParent {
    public interface OnChildScrollUpCallback {
        boolean canChildScrollUp(@NonNull SwipeRefreshLayout arg1, @Nullable View arg2);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    private static final int ALPHA_ANIMATION_DURATION = 300;
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;
    @VisibleForTesting
    static final int CIRCLE_DIAMETER = 40;
    @VisibleForTesting
    static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    public static final int DEFAULT = 1;
    private static final int DEFAULT_CIRCLE_TARGET = 0x40;
    public static final int DEFAULT_SLINGSHOT_DISTANCE = -1;
    private static final float DRAG_RATE = 0.5f;
    private static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    private static final int[] LAYOUT_ATTRS = null;
    private static final String LOG_TAG = "SwipeRefreshLayout";
    private static final int MAX_ALPHA = 0xFF;
    private static final float MAX_PROGRESS_ANGLE = 0.8f;
    private static final int SCALE_DOWN_DURATION = 150;
    private static final int STARTING_PROGRESS_ALPHA = 76;
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private OnChildScrollUpCallback mChildScrollUpCallback;
    private int mCircleDiameter;
    CircleImageView mCircleView;
    private int mCircleViewIndex;
    int mCurrentTargetOffsetTop;
    int mCustomSlingshotDistance;
    private final DecelerateInterpolator mDecelerateInterpolator;
    protected int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private boolean mNestedScrollInProgress;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    boolean mNotify;
    protected int mOriginalOffsetTop;
    private final int[] mParentOffsetInWindow;
    private final int[] mParentScrollConsumed;
    CircularProgressDrawable mProgress;
    private Animation.AnimationListener mRefreshListener;
    boolean mRefreshing;
    private boolean mReturningToStart;
    boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    int mSpinnerOffsetEnd;
    float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance;
    private float mTotalUnconsumed;
    private int mTouchSlop;
    boolean mUsingCustomStart;

    static {
        SwipeRefreshLayout.LAYOUT_ATTRS = new int[]{0x101000E};
    }

    public SwipeRefreshLayout(@NonNull Context context0) {
        this(context0, null);
    }

    public SwipeRefreshLayout(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mRefreshing = false;
        this.mTotalDragDistance = -1.0f;
        this.mParentScrollConsumed = new int[2];
        this.mParentOffsetInWindow = new int[2];
        this.mActivePointerId = -1;
        this.mCircleViewIndex = -1;
        this.mRefreshListener = new Animation.AnimationListener() {
            @Override  // android.view.animation.Animation$AnimationListener
            public void onAnimationEnd(Animation animation0) {
                if(SwipeRefreshLayout.this.mRefreshing) {
                    SwipeRefreshLayout.this.mProgress.setAlpha(0xFF);
                    SwipeRefreshLayout.this.mProgress.start();
                    if(SwipeRefreshLayout.this.mNotify && SwipeRefreshLayout.this.mListener != null) {
                        SwipeRefreshLayout.this.mListener.onRefresh();
                    }
                    SwipeRefreshLayout.this.mCurrentTargetOffsetTop = SwipeRefreshLayout.this.mCircleView.getTop();
                    return;
                }
                SwipeRefreshLayout.this.reset();
            }

            @Override  // android.view.animation.Animation$AnimationListener
            public void onAnimationRepeat(Animation animation0) {
            }

            @Override  // android.view.animation.Animation$AnimationListener
            public void onAnimationStart(Animation animation0) {
            }
        };
        this.mAnimateToCorrectPosition = new Animation() {
            @Override  // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation0) {
                int v = SwipeRefreshLayout.this.mFrom + ((int)(((float)((SwipeRefreshLayout.this.mUsingCustomStart ? SwipeRefreshLayout.this.mSpinnerOffsetEnd : SwipeRefreshLayout.this.mSpinnerOffsetEnd - Math.abs(SwipeRefreshLayout.this.mOriginalOffsetTop)) - SwipeRefreshLayout.this.mFrom)) * f));
                int v1 = SwipeRefreshLayout.this.mCircleView.getTop();
                SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(v - v1);
                SwipeRefreshLayout.this.mProgress.setArrowScale(1.0f - f);
            }
        };
        this.mAnimateToStartPosition = new Animation() {
            @Override  // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation0) {
                SwipeRefreshLayout.this.moveToStart(f);
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context0).getScaledTouchSlop();
        this.mMediumAnimationDuration = this.getResources().getInteger(0x10E0001);
        this.setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics0 = this.getResources().getDisplayMetrics();
        this.mCircleDiameter = (int)(displayMetrics0.density * 40.0f);
        this.createProgressView();
        this.setChildrenDrawingOrderEnabled(true);
        this.mSpinnerOffsetEnd = (int)(displayMetrics0.density * 64.0f);
        this.mTotalDragDistance = (float)this.mSpinnerOffsetEnd;
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        this.setNestedScrollingEnabled(true);
        this.mCurrentTargetOffsetTop = -this.mCircleDiameter;
        this.mOriginalOffsetTop = -this.mCircleDiameter;
        this.moveToStart(1.0f);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, SwipeRefreshLayout.LAYOUT_ATTRS);
        this.setEnabled(typedArray0.getBoolean(0, true));
        typedArray0.recycle();
    }

    private void animateOffsetToCorrectPosition(int v, Animation.AnimationListener animation$AnimationListener0) {
        this.mFrom = v;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200L);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if(animation$AnimationListener0 != null) {
            this.mCircleView.setAnimationListener(animation$AnimationListener0);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int v, Animation.AnimationListener animation$AnimationListener0) {
        if(this.mScale) {
            this.startScaleDownReturnToStartAnimation(v, animation$AnimationListener0);
            return;
        }
        this.mFrom = v;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(200L);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        if(animation$AnimationListener0 != null) {
            this.mCircleView.setAnimationListener(animation$AnimationListener0);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToStartPosition);
    }

    public boolean canChildScrollUp() {
        OnChildScrollUpCallback swipeRefreshLayout$OnChildScrollUpCallback0 = this.mChildScrollUpCallback;
        if(swipeRefreshLayout$OnChildScrollUpCallback0 != null) {
            return swipeRefreshLayout$OnChildScrollUpCallback0.canChildScrollUp(this, this.mTarget);
        }
        View view0 = this.mTarget;
        return view0 instanceof ListView ? ListViewCompat.canScrollList(((ListView)view0), -1) : view0.canScrollVertically(-1);
    }

    private void createProgressView() {
        this.mCircleView = new CircleImageView(this.getContext(), 0xFFFAFAFA);
        this.mProgress = new CircularProgressDrawable(this.getContext());
        this.mProgress.setStyle(1);
        this.mCircleView.setImageDrawable(this.mProgress);
        this.mCircleView.setVisibility(8);
        this.addView(this.mCircleView);
    }

    @Override  // androidx.core.view.NestedScrollingChild, android.view.View
    public boolean dispatchNestedFling(float f, float f1, boolean z) {
        return this.mNestedScrollingChildHelper.dispatchNestedFling(f, f1, z);
    }

    @Override  // androidx.core.view.NestedScrollingChild, android.view.View
    public boolean dispatchNestedPreFling(float f, float f1) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(f, f1);
    }

    @Override  // androidx.core.view.NestedScrollingChild, android.view.View
    public boolean dispatchNestedPreScroll(int v, int v1, int[] arr_v, int[] arr_v1) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(v, v1, arr_v, arr_v1);
    }

    @Override  // androidx.core.view.NestedScrollingChild, android.view.View
    public boolean dispatchNestedScroll(int v, int v1, int v2, int v3, int[] arr_v) {
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(v, v1, v2, v3, arr_v);
    }

    private void ensureTarget() {
        if(this.mTarget == null) {
            for(int v = 0; v < this.getChildCount(); ++v) {
                View view0 = this.getChildAt(v);
                if(!view0.equals(this.mCircleView)) {
                    this.mTarget = view0;
                    return;
                }
            }
        }
    }

    private void finishSpinner(float f) {
        if(f > this.mTotalDragDistance) {
            this.setRefreshing(true, true);
            return;
        }
        this.mRefreshing = false;
        this.mProgress.setStartEndTrim(0.0f, 0.0f);
        androidx.swiperefreshlayout.widget.SwipeRefreshLayout.5 swipeRefreshLayout$50 = this.mScale ? null : new Animation.AnimationListener() {
            @Override  // android.view.animation.Animation$AnimationListener
            public void onAnimationEnd(Animation animation0) {
                if(!SwipeRefreshLayout.this.mScale) {
                    SwipeRefreshLayout.this.startScaleDownAnimation(null);
                }
            }

            @Override  // android.view.animation.Animation$AnimationListener
            public void onAnimationRepeat(Animation animation0) {
            }

            @Override  // android.view.animation.Animation$AnimationListener
            public void onAnimationStart(Animation animation0) {
            }
        };
        this.animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, swipeRefreshLayout$50);
        this.mProgress.setArrowEnabled(false);
    }

    @Override  // android.view.ViewGroup
    protected int getChildDrawingOrder(int v, int v1) {
        int v2 = this.mCircleViewIndex;
        if(v2 < 0) {
            return v1;
        }
        if(v1 == v - 1) {
            return v2;
        }
        return v1 < v2 ? v1 : v1 + 1;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    public int getProgressCircleDiameter() {
        return this.mCircleDiameter;
    }

    public int getProgressViewEndOffset() {
        return this.mSpinnerOffsetEnd;
    }

    public int getProgressViewStartOffset() {
        return this.mOriginalOffsetTop;
    }

    @Override  // androidx.core.view.NestedScrollingChild, android.view.View
    public boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    // 去混淆评级： 低(20)
    private boolean isAnimationRunning(Animation animation0) {
        return animation0 != null && animation0.hasStarted() && !animation0.hasEnded();
    }

    @Override  // androidx.core.view.NestedScrollingChild, android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    private void moveSpinner(float f) {
        this.mProgress.setArrowEnabled(true);
        float f1 = Math.min(1.0f, Math.abs(f / this.mTotalDragDistance));
        float f2 = ((float)Math.max(((double)f1) - 0.4, 0.0)) * 5.0f / 3.0f;
        float f3 = Math.abs(f) - this.mTotalDragDistance;
        int v = this.mCustomSlingshotDistance;
        if(v <= 0) {
            v = this.mUsingCustomStart ? this.mSpinnerOffsetEnd - this.mOriginalOffsetTop : this.mSpinnerOffsetEnd;
        }
        float f4 = Math.max(0.0f, Math.min(f3, ((float)v) * 2.0f) / ((float)v));
        float f5 = ((float)(((double)(f4 / 4.0f)) - Math.pow(f4 / 4.0f, 2.0))) * 2.0f;
        int v1 = this.mOriginalOffsetTop + ((int)(((float)v) * f1 + ((float)v) * f5 * 2.0f));
        if(this.mCircleView.getVisibility() != 0) {
            this.mCircleView.setVisibility(0);
        }
        if(!this.mScale) {
            this.mCircleView.setScaleX(1.0f);
            this.mCircleView.setScaleY(1.0f);
        }
        if(this.mScale) {
            this.setAnimationProgress(Math.min(1.0f, f / this.mTotalDragDistance));
        }
        if(f >= this.mTotalDragDistance) {
            if(this.mProgress.getAlpha() < 0xFF && !this.isAnimationRunning(this.mAlphaMaxAnimation)) {
                this.startProgressAlphaMaxAnimation();
            }
        }
        else if(this.mProgress.getAlpha() > 76 && !this.isAnimationRunning(this.mAlphaStartAnimation)) {
            this.startProgressAlphaStartAnimation();
        }
        this.mProgress.setStartEndTrim(0.0f, Math.min(0.8f, f2 * 0.8f));
        this.mProgress.setArrowScale(Math.min(1.0f, f2));
        this.mProgress.setProgressRotation((f2 * 0.4f - 0.25f + f5 * 2.0f) * 0.5f);
        this.setTargetOffsetTopAndBottom(v1 - this.mCurrentTargetOffsetTop);
    }

    void moveToStart(float f) {
        this.setTargetOffsetTopAndBottom(this.mFrom + ((int)(((float)(this.mOriginalOffsetTop - this.mFrom)) * f)) - this.mCircleView.getTop());
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.reset();
    }

    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        this.ensureTarget();
        int v = motionEvent0.getActionMasked();
        if(this.mReturningToStart && v == 0) {
            this.mReturningToStart = false;
        }
        if(this.isEnabled() && !this.mReturningToStart && !this.canChildScrollUp() && !this.mRefreshing && !this.mNestedScrollInProgress) {
            if(v != 6) {
                switch(v) {
                    case 0: {
                        this.setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop());
                        this.mActivePointerId = motionEvent0.getPointerId(0);
                        this.mIsBeingDragged = false;
                        int v1 = motionEvent0.findPointerIndex(this.mActivePointerId);
                        if(v1 < 0) {
                            return false;
                        }
                        this.mInitialDownY = motionEvent0.getY(v1);
                        return this.mIsBeingDragged;
                    }
                    case 2: {
                        int v2 = this.mActivePointerId;
                        if(v2 == -1) {
                            Log.e("SwipeRefreshLayout", "Got ACTION_MOVE event but don\'t have an active pointer id.");
                            return false;
                        }
                        int v3 = motionEvent0.findPointerIndex(v2);
                        if(v3 < 0) {
                            return false;
                        }
                        this.startDragging(motionEvent0.getY(v3));
                        return this.mIsBeingDragged;
                    }
                    case 1: 
                    case 3: {
                        this.mIsBeingDragged = false;
                        this.mActivePointerId = -1;
                        return false;
                    }
                    default: {
                        return this.mIsBeingDragged;
                    }
                }
            }
            this.onSecondaryPointerUp(motionEvent0);
            return this.mIsBeingDragged;
        }
        return false;
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v4 = this.getMeasuredWidth();
        int v5 = this.getMeasuredHeight();
        if(this.getChildCount() == 0) {
            return;
        }
        if(this.mTarget == null) {
            this.ensureTarget();
        }
        View view0 = this.mTarget;
        if(view0 == null) {
            return;
        }
        int v6 = this.getPaddingLeft();
        int v7 = this.getPaddingTop();
        view0.layout(v6, v7, v4 - this.getPaddingLeft() - this.getPaddingRight() + v6, v5 - this.getPaddingTop() - this.getPaddingBottom() + v7);
        int v8 = this.mCircleView.getMeasuredWidth();
        int v9 = this.mCircleView.getMeasuredHeight();
        this.mCircleView.layout(v4 / 2 - v8 / 2, this.mCurrentTargetOffsetTop, v4 / 2 + v8 / 2, v9 + this.mCurrentTargetOffsetTop);
    }

    @Override  // android.view.View
    public void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        if(this.mTarget == null) {
            this.ensureTarget();
        }
        View view0 = this.mTarget;
        if(view0 == null) {
            return;
        }
        view0.measure(View.MeasureSpec.makeMeasureSpec(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), 0x40000000), View.MeasureSpec.makeMeasureSpec(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), 0x40000000));
        this.mCircleView.measure(View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 0x40000000), View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 0x40000000));
        this.mCircleViewIndex = -1;
        for(int v2 = 0; v2 < this.getChildCount(); ++v2) {
            if(this.getChildAt(v2) == this.mCircleView) {
                this.mCircleViewIndex = v2;
                return;
            }
        }
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view0, float f, float f1, boolean z) {
        return this.dispatchNestedFling(f, f1, z);
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view0, float f, float f1) {
        return this.dispatchNestedPreFling(f, f1);
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view0, int v, int v1, int[] arr_v) {
        if(v1 > 0) {
            float f = this.mTotalUnconsumed;
            if(f > 0.0f) {
                if(((float)v1) > f) {
                    arr_v[1] = v1 - ((int)f);
                    this.mTotalUnconsumed = 0.0f;
                }
                else {
                    this.mTotalUnconsumed = f - ((float)v1);
                    arr_v[1] = v1;
                }
                this.moveSpinner(this.mTotalUnconsumed);
            }
        }
        if(this.mUsingCustomStart && v1 > 0 && this.mTotalUnconsumed == 0.0f && Math.abs(v1 - arr_v[1]) > 0) {
            this.mCircleView.setVisibility(8);
        }
        int[] arr_v1 = this.mParentScrollConsumed;
        if(this.dispatchNestedPreScroll(v - arr_v[0], v1 - arr_v[1], arr_v1, null)) {
            arr_v[0] += arr_v1[0];
            arr_v[1] += arr_v1[1];
        }
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3) {
        this.dispatchNestedScroll(v, v1, v2, v3, this.mParentOffsetInWindow);
        int v4 = v3 + this.mParentOffsetInWindow[1];
        if(v4 < 0 && !this.canChildScrollUp()) {
            this.mTotalUnconsumed += (float)Math.abs(v4);
            this.moveSpinner(this.mTotalUnconsumed);
        }
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view0, View view1, int v) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view0, view1, v);
        this.startNestedScroll(v & 2);
        this.mTotalUnconsumed = 0.0f;
        this.mNestedScrollInProgress = true;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionIndex();
        if(motionEvent0.getPointerId(v) == this.mActivePointerId) {
            this.mActivePointerId = motionEvent0.getPointerId((v == 0 ? 1 : 0));
        }
    }

    // 去混淆评级： 低(30)
    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view0, View view1, int v) {
        return this.isEnabled() && !this.mReturningToStart && !this.mRefreshing && (v & 2) != 0;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view0) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view0);
        this.mNestedScrollInProgress = false;
        float f = this.mTotalUnconsumed;
        if(f > 0.0f) {
            this.finishSpinner(f);
            this.mTotalUnconsumed = 0.0f;
        }
        this.stopNestedScroll();
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        if(this.mReturningToStart && v == 0) {
            this.mReturningToStart = false;
        }
        if(this.isEnabled() && !this.mReturningToStart && !this.canChildScrollUp() && !this.mRefreshing && !this.mNestedScrollInProgress) {
            switch(v) {
                case 0: {
                    this.mActivePointerId = motionEvent0.getPointerId(0);
                    this.mIsBeingDragged = false;
                    return true;
                }
                case 1: {
                    int v1 = motionEvent0.findPointerIndex(this.mActivePointerId);
                    if(v1 < 0) {
                        Log.e("SwipeRefreshLayout", "Got ACTION_UP event but don\'t have an active pointer id.");
                        return false;
                    }
                    if(this.mIsBeingDragged) {
                        float f = motionEvent0.getY(v1);
                        this.mIsBeingDragged = false;
                        this.finishSpinner((f - this.mInitialMotionY) * 0.5f);
                    }
                    this.mActivePointerId = -1;
                    return false;
                }
                case 2: {
                    int v2 = motionEvent0.findPointerIndex(this.mActivePointerId);
                    if(v2 < 0) {
                        Log.e("SwipeRefreshLayout", "Got ACTION_MOVE event but have an invalid active pointer id.");
                        return false;
                    }
                    float f1 = motionEvent0.getY(v2);
                    this.startDragging(f1);
                    if(this.mIsBeingDragged) {
                        float f2 = (f1 - this.mInitialMotionY) * 0.5f;
                        if(f2 > 0.0f) {
                            this.moveSpinner(f2);
                            return true;
                        }
                        return false;
                    }
                    return true;
                }
                case 3: {
                    return false;
                }
                case 5: {
                    int v3 = motionEvent0.getActionIndex();
                    if(v3 < 0) {
                        Log.e("SwipeRefreshLayout", "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return false;
                    }
                    this.mActivePointerId = motionEvent0.getPointerId(v3);
                    return true;
                }
                case 6: {
                    this.onSecondaryPointerUp(motionEvent0);
                    return true;
                }
                default: {
                    return true;
                }
            }
        }
        return false;
    }

    @Override  // android.view.ViewGroup
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if((Build.VERSION.SDK_INT >= 21 || !(this.mTarget instanceof AbsListView)) && (this.mTarget == null || ViewCompat.isNestedScrollingEnabled(this.mTarget))) {
            super.requestDisallowInterceptTouchEvent(z);
        }
    }

    void reset() {
        this.mCircleView.clearAnimation();
        this.mProgress.stop();
        this.mCircleView.setVisibility(8);
        this.setColorViewAlpha(0xFF);
        if(this.mScale) {
            this.setAnimationProgress(0.0f);
        }
        else {
            this.setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCurrentTargetOffsetTop);
        }
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    void setAnimationProgress(float f) {
        this.mCircleView.setScaleX(f);
        this.mCircleView.setScaleY(f);
    }

    @Deprecated
    public void setColorScheme(@ColorRes int[] arr_v) {
        this.setColorSchemeResources(arr_v);
    }

    public void setColorSchemeColors(@ColorInt int[] arr_v) {
        this.ensureTarget();
        this.mProgress.setColorSchemeColors(arr_v);
    }

    public void setColorSchemeResources(@ColorRes int[] arr_v) {
        Context context0 = this.getContext();
        int[] arr_v1 = new int[arr_v.length];
        for(int v = 0; v < arr_v.length; ++v) {
            arr_v1[v] = ContextCompat.getColor(context0, arr_v[v]);
        }
        this.setColorSchemeColors(arr_v1);
    }

    private void setColorViewAlpha(int v) {
        this.mCircleView.getBackground().setAlpha(v);
        this.mProgress.setAlpha(v);
    }

    public void setDistanceToTriggerSync(int v) {
        this.mTotalDragDistance = (float)v;
    }

    @Override  // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if(!z) {
            this.reset();
        }
    }

    @Override  // androidx.core.view.NestedScrollingChild, android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(z);
    }

    public void setOnChildScrollUpCallback(@Nullable OnChildScrollUpCallback swipeRefreshLayout$OnChildScrollUpCallback0) {
        this.mChildScrollUpCallback = swipeRefreshLayout$OnChildScrollUpCallback0;
    }

    public void setOnRefreshListener(@Nullable OnRefreshListener swipeRefreshLayout$OnRefreshListener0) {
        this.mListener = swipeRefreshLayout$OnRefreshListener0;
    }

    @Deprecated
    public void setProgressBackgroundColor(int v) {
        this.setProgressBackgroundColorSchemeResource(v);
    }

    public void setProgressBackgroundColorSchemeColor(@ColorInt int v) {
        this.mCircleView.setBackgroundColor(v);
    }

    public void setProgressBackgroundColorSchemeResource(@ColorRes int v) {
        this.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this.getContext(), v));
    }

    public void setProgressViewEndTarget(boolean z, int v) {
        this.mSpinnerOffsetEnd = v;
        this.mScale = z;
        this.mCircleView.invalidate();
    }

    public void setProgressViewOffset(boolean z, int v, int v1) {
        this.mScale = z;
        this.mOriginalOffsetTop = v;
        this.mSpinnerOffsetEnd = v1;
        this.mUsingCustomStart = true;
        this.reset();
        this.mRefreshing = false;
    }

    private void setRefreshing(boolean z, boolean z1) {
        if(this.mRefreshing != z) {
            this.mNotify = z1;
            this.ensureTarget();
            this.mRefreshing = z;
            if(this.mRefreshing) {
                this.animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
                return;
            }
            this.startScaleDownAnimation(this.mRefreshListener);
        }
    }

    public void setRefreshing(boolean z) {
        if(z && !this.mRefreshing) {
            this.mRefreshing = true;
            this.setTargetOffsetTopAndBottom((this.mUsingCustomStart ? this.mSpinnerOffsetEnd : this.mSpinnerOffsetEnd + this.mOriginalOffsetTop) - this.mCurrentTargetOffsetTop);
            this.mNotify = false;
            this.startScaleUpAnimation(this.mRefreshListener);
            return;
        }
        this.setRefreshing(z, false);
    }

    public void setSize(int v) {
        if(v != 0 && v != 1) {
            return;
        }
        DisplayMetrics displayMetrics0 = this.getResources().getDisplayMetrics();
        this.mCircleDiameter = v == 0 ? ((int)(displayMetrics0.density * 56.0f)) : ((int)(displayMetrics0.density * 40.0f));
        this.mCircleView.setImageDrawable(null);
        this.mProgress.setStyle(v);
        this.mCircleView.setImageDrawable(this.mProgress);
    }

    public void setSlingshotDistance(@Px int v) {
        this.mCustomSlingshotDistance = v;
    }

    void setTargetOffsetTopAndBottom(int v) {
        this.mCircleView.bringToFront();
        ViewCompat.offsetTopAndBottom(this.mCircleView, v);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    private Animation startAlphaAnimation(int v, int v1) {
        Animation animation0 = new Animation() {
            @Override  // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation0) {
                SwipeRefreshLayout.this.mProgress.setAlpha(((int)(((float)v) + ((float)(v1 - v)) * f)));
            }
        };
        animation0.setDuration(300L);
        this.mCircleView.setAnimationListener(null);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(animation0);
        return animation0;
    }

    private void startDragging(float f) {
        float f1 = this.mInitialDownY;
        int v = this.mTouchSlop;
        if(f - f1 > ((float)v) && !this.mIsBeingDragged) {
            this.mInitialMotionY = f1 + ((float)v);
            this.mIsBeingDragged = true;
            this.mProgress.setAlpha(76);
        }
    }

    @Override  // androidx.core.view.NestedScrollingChild, android.view.View
    public boolean startNestedScroll(int v) {
        return this.mNestedScrollingChildHelper.startNestedScroll(v);
    }

    private void startProgressAlphaMaxAnimation() {
        this.mAlphaMaxAnimation = this.startAlphaAnimation(this.mProgress.getAlpha(), 0xFF);
    }

    private void startProgressAlphaStartAnimation() {
        this.mAlphaStartAnimation = this.startAlphaAnimation(this.mProgress.getAlpha(), 76);
    }

    void startScaleDownAnimation(Animation.AnimationListener animation$AnimationListener0) {
        this.mScaleDownAnimation = new Animation() {
            @Override  // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation0) {
                SwipeRefreshLayout.this.setAnimationProgress(1.0f - f);
            }
        };
        this.mScaleDownAnimation.setDuration(150L);
        this.mCircleView.setAnimationListener(animation$AnimationListener0);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownAnimation);
    }

    private void startScaleDownReturnToStartAnimation(int v, Animation.AnimationListener animation$AnimationListener0) {
        this.mFrom = v;
        this.mStartingScale = this.mCircleView.getScaleX();
        this.mScaleDownToStartAnimation = new Animation() {
            @Override  // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation0) {
                SwipeRefreshLayout.this.setAnimationProgress(SwipeRefreshLayout.this.mStartingScale + -SwipeRefreshLayout.this.mStartingScale * f);
                SwipeRefreshLayout.this.moveToStart(f);
            }
        };
        this.mScaleDownToStartAnimation.setDuration(150L);
        if(animation$AnimationListener0 != null) {
            this.mCircleView.setAnimationListener(animation$AnimationListener0);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
    }

    private void startScaleUpAnimation(Animation.AnimationListener animation$AnimationListener0) {
        this.mCircleView.setVisibility(0);
        this.mProgress.setAlpha(0xFF);
        this.mScaleAnimation = new Animation() {
            @Override  // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation0) {
                SwipeRefreshLayout.this.setAnimationProgress(f);
            }
        };
        this.mScaleAnimation.setDuration(((long)this.mMediumAnimationDuration));
        if(animation$AnimationListener0 != null) {
            this.mCircleView.setAnimationListener(animation$AnimationListener0);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleAnimation);
    }

    @Override  // androidx.core.view.NestedScrollingChild, android.view.View
    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }
}

