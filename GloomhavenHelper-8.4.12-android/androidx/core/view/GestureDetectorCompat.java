package androidx.core.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public final class GestureDetectorCompat {
    interface GestureDetectorCompatImpl {
        boolean isLongpressEnabled();

        boolean onTouchEvent(MotionEvent arg1);

        void setIsLongpressEnabled(boolean arg1);

        void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener arg1);
    }

    static class GestureDetectorCompatImplBase implements GestureDetectorCompatImpl {
        class GestureHandler extends Handler {
            GestureHandler() {
            }

            GestureHandler(Handler handler0) {
                super(handler0.getLooper());
            }

            @Override  // android.os.Handler
            public void handleMessage(Message message0) {
                switch(message0.what) {
                    case 1: {
                        GestureDetectorCompatImplBase.this.mListener.onShowPress(GestureDetectorCompatImplBase.this.mCurrentDownEvent);
                        return;
                    }
                    case 2: {
                        GestureDetectorCompatImplBase.this.dispatchLongPress();
                        return;
                    }
                    case 3: {
                        if(GestureDetectorCompatImplBase.this.mDoubleTapListener != null) {
                            if(!GestureDetectorCompatImplBase.this.mStillDown) {
                                GestureDetectorCompatImplBase.this.mDoubleTapListener.onSingleTapConfirmed(GestureDetectorCompatImplBase.this.mCurrentDownEvent);
                                return;
                            }
                            GestureDetectorCompatImplBase.this.mDeferConfirmSingleTap = true;
                            return;
                        }
                        return;
                    }
                    default: {
                        throw new RuntimeException("Unknown message " + message0);
                    }
                }
            }
        }

        private static final int DOUBLE_TAP_TIMEOUT = 0;
        private static final int LONGPRESS_TIMEOUT = 0;
        private static final int LONG_PRESS = 2;
        private static final int SHOW_PRESS = 1;
        private static final int TAP = 3;
        private static final int TAP_TIMEOUT;
        private boolean mAlwaysInBiggerTapRegion;
        private boolean mAlwaysInTapRegion;
        MotionEvent mCurrentDownEvent;
        boolean mDeferConfirmSingleTap;
        GestureDetector.OnDoubleTapListener mDoubleTapListener;
        private int mDoubleTapSlopSquare;
        private float mDownFocusX;
        private float mDownFocusY;
        private final Handler mHandler;
        private boolean mInLongPress;
        private boolean mIsDoubleTapping;
        private boolean mIsLongpressEnabled;
        private float mLastFocusX;
        private float mLastFocusY;
        final GestureDetector.OnGestureListener mListener;
        private int mMaximumFlingVelocity;
        private int mMinimumFlingVelocity;
        private MotionEvent mPreviousUpEvent;
        boolean mStillDown;
        private int mTouchSlopSquare;
        private VelocityTracker mVelocityTracker;

        static {
            GestureDetectorCompatImplBase.LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
            GestureDetectorCompatImplBase.TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
            GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
        }

        GestureDetectorCompatImplBase(Context context0, GestureDetector.OnGestureListener gestureDetector$OnGestureListener0, Handler handler0) {
            this.mHandler = handler0 == null ? new GestureHandler(this) : new GestureHandler(this, handler0);
            this.mListener = gestureDetector$OnGestureListener0;
            if(gestureDetector$OnGestureListener0 instanceof GestureDetector.OnDoubleTapListener) {
                this.setOnDoubleTapListener(((GestureDetector.OnDoubleTapListener)gestureDetector$OnGestureListener0));
            }
            this.init(context0);
        }

        private void cancel() {
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(3);
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
            this.mIsDoubleTapping = false;
            this.mStillDown = false;
            this.mAlwaysInTapRegion = false;
            this.mAlwaysInBiggerTapRegion = false;
            this.mDeferConfirmSingleTap = false;
            if(this.mInLongPress) {
                this.mInLongPress = false;
            }
        }

        private void cancelTaps() {
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(3);
            this.mIsDoubleTapping = false;
            this.mAlwaysInTapRegion = false;
            this.mAlwaysInBiggerTapRegion = false;
            this.mDeferConfirmSingleTap = false;
            if(this.mInLongPress) {
                this.mInLongPress = false;
            }
        }

        void dispatchLongPress() {
            this.mHandler.removeMessages(3);
            this.mDeferConfirmSingleTap = false;
            this.mInLongPress = true;
            this.mListener.onLongPress(this.mCurrentDownEvent);
        }

        private void init(Context context0) {
            if(context0 == null) {
                throw new IllegalArgumentException("Context must not be null");
            }
            if(this.mListener == null) {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }
            this.mIsLongpressEnabled = true;
            ViewConfiguration viewConfiguration0 = ViewConfiguration.get(context0);
            int v = viewConfiguration0.getScaledTouchSlop();
            int v1 = viewConfiguration0.getScaledDoubleTapSlop();
            this.mMinimumFlingVelocity = viewConfiguration0.getScaledMinimumFlingVelocity();
            this.mMaximumFlingVelocity = viewConfiguration0.getScaledMaximumFlingVelocity();
            this.mTouchSlopSquare = v * v;
            this.mDoubleTapSlopSquare = v1 * v1;
        }

        private boolean isConsideredDoubleTap(MotionEvent motionEvent0, MotionEvent motionEvent1, MotionEvent motionEvent2) {
            if(!this.mAlwaysInBiggerTapRegion) {
                return false;
            }
            if(motionEvent2.getEventTime() - motionEvent1.getEventTime() > ((long)GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT)) {
                return false;
            }
            int v = ((int)motionEvent0.getX()) - ((int)motionEvent2.getX());
            int v1 = ((int)motionEvent0.getY()) - ((int)motionEvent2.getY());
            return v * v + v1 * v1 < this.mDoubleTapSlopSquare;
        }

        @Override  // androidx.core.view.GestureDetectorCompat$GestureDetectorCompatImpl
        public boolean isLongpressEnabled() {
            return this.mIsLongpressEnabled;
        }

        @Override  // androidx.core.view.GestureDetectorCompat$GestureDetectorCompatImpl
        public boolean onTouchEvent(MotionEvent motionEvent0) {
            boolean z4;
            int v7;
            boolean z2;
            int v = motionEvent0.getAction();
            if(this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent0);
            boolean z = (v & 0xFF) == 6;
            int v1 = z ? motionEvent0.getActionIndex() : -1;
            int v2 = motionEvent0.getPointerCount();
            float f = 0.0f;
            float f1 = 0.0f;
            for(int v3 = 0; v3 < v2; ++v3) {
                if(v1 != v3) {
                    f += motionEvent0.getX(v3);
                    f1 += motionEvent0.getY(v3);
                }
            }
            int v4 = z ? v2 - 1 : v2;
            float f2 = f / ((float)v4);
            float f3 = f1 / ((float)v4);
        alab1:
            switch(v & 0xFF) {
                case 0: {
                    if(this.mDoubleTapListener == null) {
                        z2 = false;
                    }
                    else {
                        boolean z1 = this.mHandler.hasMessages(3);
                        if(z1) {
                            this.mHandler.removeMessages(3);
                        }
                        if(this.mCurrentDownEvent == null || (this.mPreviousUpEvent == null || !z1 || !this.isConsideredDoubleTap(this.mCurrentDownEvent, this.mPreviousUpEvent, motionEvent0))) {
                            this.mHandler.sendEmptyMessageDelayed(3, ((long)GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT));
                            z2 = false;
                        }
                        else {
                            this.mIsDoubleTapping = true;
                            z2 = this.mDoubleTapListener.onDoubleTap(this.mCurrentDownEvent) | this.mDoubleTapListener.onDoubleTapEvent(motionEvent0);
                        }
                    }
                    this.mLastFocusX = f2;
                    this.mDownFocusX = f2;
                    this.mLastFocusY = f3;
                    this.mDownFocusY = f3;
                    MotionEvent motionEvent1 = this.mCurrentDownEvent;
                    if(motionEvent1 != null) {
                        motionEvent1.recycle();
                    }
                    this.mCurrentDownEvent = MotionEvent.obtain(motionEvent0);
                    this.mAlwaysInTapRegion = true;
                    this.mAlwaysInBiggerTapRegion = true;
                    this.mStillDown = true;
                    this.mInLongPress = false;
                    this.mDeferConfirmSingleTap = false;
                    if(this.mIsLongpressEnabled) {
                        this.mHandler.removeMessages(2);
                        long v5 = this.mCurrentDownEvent.getDownTime();
                        this.mHandler.sendEmptyMessageAtTime(2, v5 + ((long)GestureDetectorCompatImplBase.TAP_TIMEOUT) + ((long)GestureDetectorCompatImplBase.LONGPRESS_TIMEOUT));
                    }
                    long v6 = this.mCurrentDownEvent.getDownTime();
                    this.mHandler.sendEmptyMessageAtTime(1, v6 + ((long)GestureDetectorCompatImplBase.TAP_TIMEOUT));
                    return z2 | this.mListener.onDown(motionEvent0);
                }
                case 1: {
                    this.mStillDown = false;
                    MotionEvent motionEvent2 = MotionEvent.obtain(motionEvent0);
                    if(this.mIsDoubleTapping) {
                        v7 = this.mDoubleTapListener.onDoubleTapEvent(motionEvent0);
                    }
                    else if(this.mInLongPress) {
                        this.mHandler.removeMessages(3);
                        this.mInLongPress = false;
                        v7 = 0;
                    }
                    else if(this.mAlwaysInTapRegion) {
                        boolean z3 = this.mListener.onSingleTapUp(motionEvent0);
                        if(this.mDeferConfirmSingleTap) {
                            GestureDetector.OnDoubleTapListener gestureDetector$OnDoubleTapListener0 = this.mDoubleTapListener;
                            if(gestureDetector$OnDoubleTapListener0 != null) {
                                gestureDetector$OnDoubleTapListener0.onSingleTapConfirmed(motionEvent0);
                            }
                        }
                        v7 = z3;
                    }
                    else {
                        VelocityTracker velocityTracker0 = this.mVelocityTracker;
                        int v8 = motionEvent0.getPointerId(0);
                        velocityTracker0.computeCurrentVelocity(1000, ((float)this.mMaximumFlingVelocity));
                        float f4 = velocityTracker0.getYVelocity(v8);
                        float f5 = velocityTracker0.getXVelocity(v8);
                        v7 = Math.abs(f4) > ((float)this.mMinimumFlingVelocity) || Math.abs(f5) > ((float)this.mMinimumFlingVelocity) ? this.mListener.onFling(this.mCurrentDownEvent, motionEvent0, f5, f4) : 0;
                    }
                    MotionEvent motionEvent3 = this.mPreviousUpEvent;
                    if(motionEvent3 != null) {
                        motionEvent3.recycle();
                    }
                    this.mPreviousUpEvent = motionEvent2;
                    VelocityTracker velocityTracker1 = this.mVelocityTracker;
                    if(velocityTracker1 != null) {
                        velocityTracker1.recycle();
                        this.mVelocityTracker = null;
                    }
                    this.mIsDoubleTapping = false;
                    this.mDeferConfirmSingleTap = false;
                    this.mHandler.removeMessages(1);
                    this.mHandler.removeMessages(2);
                    return v7;
                }
                case 2: {
                    if(!this.mInLongPress) {
                        float f6 = this.mLastFocusX - f2;
                        float f7 = this.mLastFocusY - f3;
                        if(this.mIsDoubleTapping) {
                            return this.mDoubleTapListener.onDoubleTapEvent(motionEvent0);
                        }
                        if(this.mAlwaysInTapRegion) {
                            int v9 = (int)(f2 - this.mDownFocusX);
                            int v10 = (int)(f3 - this.mDownFocusY);
                            int v11 = v9 * v9 + v10 * v10;
                            if(v11 > this.mTouchSlopSquare) {
                                z4 = this.mListener.onScroll(this.mCurrentDownEvent, motionEvent0, f6, f7);
                                this.mLastFocusX = f2;
                                this.mLastFocusY = f3;
                                this.mAlwaysInTapRegion = false;
                                this.mHandler.removeMessages(3);
                                this.mHandler.removeMessages(1);
                                this.mHandler.removeMessages(2);
                            }
                            else {
                                z4 = false;
                            }
                            if(v11 > this.mTouchSlopSquare) {
                                this.mAlwaysInBiggerTapRegion = false;
                            }
                            return z4;
                        }
                        if(Math.abs(f6) >= 1.0f || Math.abs(f7) >= 1.0f) {
                            boolean z5 = this.mListener.onScroll(this.mCurrentDownEvent, motionEvent0, f6, f7);
                            this.mLastFocusX = f2;
                            this.mLastFocusY = f3;
                            return z5;
                        }
                    }
                    break;
                }
                case 3: {
                    this.cancel();
                    return false;
                }
                case 5: {
                    this.mLastFocusX = f2;
                    this.mDownFocusX = f2;
                    this.mLastFocusY = f3;
                    this.mDownFocusY = f3;
                    this.cancelTaps();
                    return false;
                }
                case 6: {
                    this.mLastFocusX = f2;
                    this.mDownFocusX = f2;
                    this.mLastFocusY = f3;
                    this.mDownFocusY = f3;
                    this.mVelocityTracker.computeCurrentVelocity(1000, ((float)this.mMaximumFlingVelocity));
                    int v12 = motionEvent0.getActionIndex();
                    int v13 = motionEvent0.getPointerId(v12);
                    float f8 = this.mVelocityTracker.getXVelocity(v13);
                    float f9 = this.mVelocityTracker.getYVelocity(v13);
                    for(int v14 = 0; true; ++v14) {
                        if(v14 >= v2) {
                            break alab1;
                        }
                        if(v14 != v12) {
                            int v15 = motionEvent0.getPointerId(v14);
                            if(this.mVelocityTracker.getXVelocity(v15) * f8 + this.mVelocityTracker.getYVelocity(v15) * f9 < 0.0f) {
                                this.mVelocityTracker.clear();
                                return false;
                            }
                        }
                    }
                }
                default: {
                    return false;
                }
            }
            return false;
        }

        @Override  // androidx.core.view.GestureDetectorCompat$GestureDetectorCompatImpl
        public void setIsLongpressEnabled(boolean z) {
            this.mIsLongpressEnabled = z;
        }

        @Override  // androidx.core.view.GestureDetectorCompat$GestureDetectorCompatImpl
        public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener gestureDetector$OnDoubleTapListener0) {
            this.mDoubleTapListener = gestureDetector$OnDoubleTapListener0;
        }
    }

    static class GestureDetectorCompatImplJellybeanMr2 implements GestureDetectorCompatImpl {
        private final GestureDetector mDetector;

        GestureDetectorCompatImplJellybeanMr2(Context context0, GestureDetector.OnGestureListener gestureDetector$OnGestureListener0, Handler handler0) {
            this.mDetector = new GestureDetector(context0, gestureDetector$OnGestureListener0, handler0);
        }

        @Override  // androidx.core.view.GestureDetectorCompat$GestureDetectorCompatImpl
        public boolean isLongpressEnabled() {
            return this.mDetector.isLongpressEnabled();
        }

        @Override  // androidx.core.view.GestureDetectorCompat$GestureDetectorCompatImpl
        public boolean onTouchEvent(MotionEvent motionEvent0) {
            return this.mDetector.onTouchEvent(motionEvent0);
        }

        @Override  // androidx.core.view.GestureDetectorCompat$GestureDetectorCompatImpl
        public void setIsLongpressEnabled(boolean z) {
            this.mDetector.setIsLongpressEnabled(z);
        }

        @Override  // androidx.core.view.GestureDetectorCompat$GestureDetectorCompatImpl
        public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener gestureDetector$OnDoubleTapListener0) {
            this.mDetector.setOnDoubleTapListener(gestureDetector$OnDoubleTapListener0);
        }
    }

    private final GestureDetectorCompatImpl mImpl;

    public GestureDetectorCompat(Context context0, GestureDetector.OnGestureListener gestureDetector$OnGestureListener0) {
        this(context0, gestureDetector$OnGestureListener0, null);
    }

    public GestureDetectorCompat(Context context0, GestureDetector.OnGestureListener gestureDetector$OnGestureListener0, Handler handler0) {
        if(Build.VERSION.SDK_INT > 17) {
            this.mImpl = new GestureDetectorCompatImplJellybeanMr2(context0, gestureDetector$OnGestureListener0, handler0);
            return;
        }
        this.mImpl = new GestureDetectorCompatImplBase(context0, gestureDetector$OnGestureListener0, handler0);
    }

    public boolean isLongpressEnabled() {
        return this.mImpl.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent motionEvent0) {
        return this.mImpl.onTouchEvent(motionEvent0);
    }

    public void setIsLongpressEnabled(boolean z) {
        this.mImpl.setIsLongpressEnabled(z);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener gestureDetector$OnDoubleTapListener0) {
        this.mImpl.setOnDoubleTapListener(gestureDetector$OnDoubleTapListener0);
    }
}

