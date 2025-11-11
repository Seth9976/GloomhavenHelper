package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

public abstract class AutoScrollHelper implements View.OnTouchListener {
    static class ClampedScroller {
        private long mDeltaTime;
        private int mDeltaX;
        private int mDeltaY;
        private int mEffectiveRampDown;
        private int mRampDownDuration;
        private int mRampUpDuration;
        private long mStartTime;
        private long mStopTime;
        private float mStopValue;
        private float mTargetVelocityX;
        private float mTargetVelocityY;

        ClampedScroller() {
            this.mStartTime = 0x8000000000000000L;
            this.mStopTime = -1L;
            this.mDeltaTime = 0L;
            this.mDeltaX = 0;
            this.mDeltaY = 0;
        }

        public void computeScrollDelta() {
            if(this.mDeltaTime == 0L) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long v = AnimationUtils.currentAnimationTimeMillis();
            float f = this.interpolateValue(this.getValueAt(v));
            long v1 = v - this.mDeltaTime;
            this.mDeltaTime = v;
            float f1 = ((float)v1) * f;
            this.mDeltaX = (int)(this.mTargetVelocityX * f1);
            this.mDeltaY = (int)(f1 * this.mTargetVelocityY);
        }

        public int getDeltaX() {
            return this.mDeltaX;
        }

        public int getDeltaY() {
            return this.mDeltaY;
        }

        public int getHorizontalDirection() {
            return (int)(this.mTargetVelocityX / Math.abs(this.mTargetVelocityX));
        }

        private float getValueAt(long v) {
            if(v < this.mStartTime) {
                return 0.0f;
            }
            return this.mStopTime < 0L || v < this.mStopTime ? AutoScrollHelper.constrain(((float)(v - this.mStartTime)) / ((float)this.mRampUpDuration), 0.0f, 1.0f) * 0.5f : 1.0f - this.mStopValue + this.mStopValue * AutoScrollHelper.constrain(((float)(v - this.mStopTime)) / ((float)this.mEffectiveRampDown), 0.0f, 1.0f);
        }

        public int getVerticalDirection() {
            return (int)(this.mTargetVelocityY / Math.abs(this.mTargetVelocityY));
        }

        private float interpolateValue(float f) {
            return -4.0f * f * f + f * 4.0f;
        }

        public boolean isFinished() {
            return this.mStopTime > 0L && AnimationUtils.currentAnimationTimeMillis() > this.mStopTime + ((long)this.mEffectiveRampDown);
        }

        public void requestStop() {
            long v = AnimationUtils.currentAnimationTimeMillis();
            this.mEffectiveRampDown = AutoScrollHelper.constrain(((int)(v - this.mStartTime)), 0, this.mRampDownDuration);
            this.mStopValue = this.getValueAt(v);
            this.mStopTime = v;
        }

        public void setRampDownDuration(int v) {
            this.mRampDownDuration = v;
        }

        public void setRampUpDuration(int v) {
            this.mRampUpDuration = v;
        }

        public void setTargetVelocity(float f, float f1) {
            this.mTargetVelocityX = f;
            this.mTargetVelocityY = f1;
        }

        public void start() {
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            this.mStopTime = -1L;
            this.mDeltaTime = this.mStartTime;
            this.mStopValue = 0.5f;
            this.mDeltaX = 0;
            this.mDeltaY = 0;
        }
    }

    class ScrollAnimationRunnable implements Runnable {
        @Override
        public void run() {
            if(!AutoScrollHelper.this.mAnimating) {
                return;
            }
            if(AutoScrollHelper.this.mNeedsReset) {
                AutoScrollHelper.this.mNeedsReset = false;
                AutoScrollHelper.this.mScroller.start();
            }
            ClampedScroller autoScrollHelper$ClampedScroller0 = AutoScrollHelper.this.mScroller;
            if(!autoScrollHelper$ClampedScroller0.isFinished() && AutoScrollHelper.this.shouldAnimate()) {
                if(AutoScrollHelper.this.mNeedsCancel) {
                    AutoScrollHelper.this.mNeedsCancel = false;
                    AutoScrollHelper.this.cancelTargetTouch();
                }
                autoScrollHelper$ClampedScroller0.computeScrollDelta();
                AutoScrollHelper.this.scrollTargetBy(autoScrollHelper$ClampedScroller0.getDeltaX(), autoScrollHelper$ClampedScroller0.getDeltaY());
                ViewCompat.postOnAnimation(AutoScrollHelper.this.mTarget, this);
                return;
            }
            AutoScrollHelper.this.mAnimating = false;
        }
    }

    private static final int DEFAULT_ACTIVATION_DELAY = 0;
    private static final int DEFAULT_EDGE_TYPE = 1;
    private static final float DEFAULT_MAXIMUM_EDGE = 3.402823E+38f;
    private static final int DEFAULT_MAXIMUM_VELOCITY_DIPS = 0x627;
    private static final int DEFAULT_MINIMUM_VELOCITY_DIPS = 315;
    private static final int DEFAULT_RAMP_DOWN_DURATION = 500;
    private static final int DEFAULT_RAMP_UP_DURATION = 500;
    private static final float DEFAULT_RELATIVE_EDGE = 0.2f;
    private static final float DEFAULT_RELATIVE_VELOCITY = 1.0f;
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    private static final int HORIZONTAL = 0;
    public static final float NO_MAX = 3.402823E+38f;
    public static final float NO_MIN = 0.0f;
    public static final float RELATIVE_UNSPECIFIED = 0.0f;
    private static final int VERTICAL = 1;
    private int mActivationDelay;
    private boolean mAlreadyDelayed;
    boolean mAnimating;
    private final Interpolator mEdgeInterpolator;
    private int mEdgeType;
    private boolean mEnabled;
    private boolean mExclusive;
    private float[] mMaximumEdges;
    private float[] mMaximumVelocity;
    private float[] mMinimumVelocity;
    boolean mNeedsCancel;
    boolean mNeedsReset;
    private float[] mRelativeEdges;
    private float[] mRelativeVelocity;
    private Runnable mRunnable;
    final ClampedScroller mScroller;
    final View mTarget;

    static {
        AutoScrollHelper.DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    }

    public AutoScrollHelper(@NonNull View view0) {
        this.mScroller = new ClampedScroller();
        this.mEdgeInterpolator = new AccelerateInterpolator();
        this.mRelativeEdges = new float[]{0.0f, 0.0f};
        this.mMaximumEdges = new float[]{3.402823E+38f, 3.402823E+38f};
        this.mRelativeVelocity = new float[]{0.0f, 0.0f};
        this.mMinimumVelocity = new float[]{0.0f, 0.0f};
        this.mMaximumVelocity = new float[]{3.402823E+38f, 3.402823E+38f};
        this.mTarget = view0;
        DisplayMetrics displayMetrics0 = Resources.getSystem().getDisplayMetrics();
        int v = (int)(displayMetrics0.density * 315.0f + 0.5f);
        float f = (float)(((int)(displayMetrics0.density * 1575.0f + 0.5f)));
        this.setMaximumVelocity(f, f);
        this.setMinimumVelocity(((float)v), ((float)v));
        this.setEdgeType(1);
        this.setMaximumEdges(3.402823E+38f, 3.402823E+38f);
        this.setRelativeEdges(0.2f, 0.2f);
        this.setRelativeVelocity(1.0f, 1.0f);
        this.setActivationDelay(AutoScrollHelper.DEFAULT_ACTIVATION_DELAY);
        this.setRampUpDuration(500);
        this.setRampDownDuration(500);
    }

    public abstract boolean canTargetScrollHorizontally(int arg1);

    public abstract boolean canTargetScrollVertically(int arg1);

    void cancelTargetTouch() {
        long v = SystemClock.uptimeMillis();
        MotionEvent motionEvent0 = MotionEvent.obtain(v, v, 3, 0.0f, 0.0f, 0);
        this.mTarget.onTouchEvent(motionEvent0);
        motionEvent0.recycle();
    }

    private float computeTargetVelocity(int v, float f, float f1, float f2) {
        float f3 = this.getEdgeValue(this.mRelativeEdges[v], f1, this.mMaximumEdges[v], f);
        if(f3 == 0.0f) {
            return 0.0f;
        }
        float f4 = this.mRelativeVelocity[v];
        float f5 = this.mMinimumVelocity[v];
        float f6 = this.mMaximumVelocity[v];
        float f7 = f4 * f2;
        return f3 > 0.0f ? AutoScrollHelper.constrain(f3 * f7, f5, f6) : -AutoScrollHelper.constrain(-f3 * f7, f5, f6);
    }

    static float constrain(float f, float f1, float f2) {
        if(f > f2) {
            return f2;
        }
        return f < f1 ? f1 : f;
    }

    static int constrain(int v, int v1, int v2) {
        if(v > v2) {
            return v2;
        }
        return v >= v1 ? v : v1;
    }

    private float constrainEdgeValue(float f, float f1) {
        if(f1 == 0.0f) {
            return 0.0f;
        }
        int v = this.mEdgeType;
        switch(v) {
            case 0: 
            case 1: {
                if(f < f1) {
                    if(f >= 0.0f) {
                        return 1.0f - f / f1;
                    }
                    return !this.mAnimating || v != 1 ? 0.0f : 1.0f;
                }
                return 0.0f;
            }
            case 2: {
                return f < 0.0f ? f / -f1 : 0.0f;
            }
            default: {
                return 0.0f;
            }
        }
    }

    private float getEdgeValue(float f, float f1, float f2, float f3) {
        float f4 = AutoScrollHelper.constrain(f * f1, 0.0f, f2);
        float f5 = this.constrainEdgeValue(f1 - f3, f4) - this.constrainEdgeValue(f3, f4);
        if(f5 < 0.0f) {
            return AutoScrollHelper.constrain(-this.mEdgeInterpolator.getInterpolation(-f5), -1.0f, 1.0f);
        }
        return f5 > 0.0f ? AutoScrollHelper.constrain(this.mEdgeInterpolator.getInterpolation(f5), -1.0f, 1.0f) : 0.0f;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isExclusive() {
        return this.mExclusive;
    }

    @Override  // android.view.View$OnTouchListener
    public boolean onTouch(View view0, MotionEvent motionEvent0) {
        if(!this.mEnabled) {
            return false;
        }
        switch(motionEvent0.getActionMasked()) {
            case 0: {
                this.mNeedsCancel = true;
                this.mAlreadyDelayed = false;
                break;
            }
            case 2: {
                break;
            }
            case 1: 
            case 3: {
                this.requestStop();
                return this.mExclusive && this.mAnimating;
            }
            default: {
                return this.mExclusive && this.mAnimating;
            }
        }
        float f = this.computeTargetVelocity(0, motionEvent0.getX(), ((float)view0.getWidth()), ((float)this.mTarget.getWidth()));
        float f1 = this.computeTargetVelocity(1, motionEvent0.getY(), ((float)view0.getHeight()), ((float)this.mTarget.getHeight()));
        this.mScroller.setTargetVelocity(f, f1);
        if(!this.mAnimating && this.shouldAnimate()) {
            this.startAnimating();
        }
        return this.mExclusive && this.mAnimating;
    }

    private void requestStop() {
        if(this.mNeedsReset) {
            this.mAnimating = false;
            return;
        }
        this.mScroller.requestStop();
    }

    public abstract void scrollTargetBy(int arg1, int arg2);

    @NonNull
    public AutoScrollHelper setActivationDelay(int v) {
        this.mActivationDelay = v;
        return this;
    }

    @NonNull
    public AutoScrollHelper setEdgeType(int v) {
        this.mEdgeType = v;
        return this;
    }

    public AutoScrollHelper setEnabled(boolean z) {
        if(this.mEnabled && !z) {
            this.requestStop();
        }
        this.mEnabled = z;
        return this;
    }

    public AutoScrollHelper setExclusive(boolean z) {
        this.mExclusive = z;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMaximumEdges(float f, float f1) {
        float[] arr_f = this.mMaximumEdges;
        arr_f[0] = f;
        arr_f[1] = f1;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMaximumVelocity(float f, float f1) {
        float[] arr_f = this.mMaximumVelocity;
        arr_f[0] = f / 1000.0f;
        arr_f[1] = f1 / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMinimumVelocity(float f, float f1) {
        float[] arr_f = this.mMinimumVelocity;
        arr_f[0] = f / 1000.0f;
        arr_f[1] = f1 / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRampDownDuration(int v) {
        this.mScroller.setRampDownDuration(v);
        return this;
    }

    @NonNull
    public AutoScrollHelper setRampUpDuration(int v) {
        this.mScroller.setRampUpDuration(v);
        return this;
    }

    @NonNull
    public AutoScrollHelper setRelativeEdges(float f, float f1) {
        float[] arr_f = this.mRelativeEdges;
        arr_f[0] = f;
        arr_f[1] = f1;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRelativeVelocity(float f, float f1) {
        float[] arr_f = this.mRelativeVelocity;
        arr_f[0] = f / 1000.0f;
        arr_f[1] = f1 / 1000.0f;
        return this;
    }

    boolean shouldAnimate() {
        int v = this.mScroller.getVerticalDirection();
        int v1 = this.mScroller.getHorizontalDirection();
        return v != 0 && this.canTargetScrollVertically(v) || v1 != 0 && this.canTargetScrollHorizontally(v1);
    }

    private void startAnimating() {
        if(this.mRunnable == null) {
            this.mRunnable = new ScrollAnimationRunnable(this);
        }
        this.mAnimating = true;
        this.mNeedsReset = true;
        if(this.mAlreadyDelayed) {
            this.mRunnable.run();
        }
        else {
            int v = this.mActivationDelay;
            if(v > 0) {
                ViewCompat.postOnAnimationDelayed(this.mTarget, this.mRunnable, ((long)v));
            }
            else {
                this.mRunnable.run();
            }
        }
        this.mAlreadyDelayed = true;
    }
}

