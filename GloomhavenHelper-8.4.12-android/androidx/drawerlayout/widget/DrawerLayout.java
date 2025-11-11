package androidx.drawerlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper.Callback;
import androidx.customview.widget.ViewDragHelper;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup {
    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect;

        AccessibilityDelegate() {
            this.mTmpRect = new Rect();
        }

        private void addChildrenForAccessibility(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0, ViewGroup viewGroup0) {
            int v = viewGroup0.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view0 = viewGroup0.getChildAt(v1);
                if(DrawerLayout.includeChildForAccessibility(view0)) {
                    accessibilityNodeInfoCompat0.addChild(view0);
                }
            }
        }

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat1) {
            accessibilityNodeInfoCompat1.getBoundsInParent(this.mTmpRect);
            accessibilityNodeInfoCompat0.setBoundsInParent(this.mTmpRect);
            accessibilityNodeInfoCompat1.getBoundsInScreen(this.mTmpRect);
            accessibilityNodeInfoCompat0.setBoundsInScreen(this.mTmpRect);
            accessibilityNodeInfoCompat0.setVisibleToUser(accessibilityNodeInfoCompat1.isVisibleToUser());
            accessibilityNodeInfoCompat0.setPackageName(accessibilityNodeInfoCompat1.getPackageName());
            accessibilityNodeInfoCompat0.setClassName(accessibilityNodeInfoCompat1.getClassName());
            accessibilityNodeInfoCompat0.setContentDescription(accessibilityNodeInfoCompat1.getContentDescription());
            accessibilityNodeInfoCompat0.setEnabled(accessibilityNodeInfoCompat1.isEnabled());
            accessibilityNodeInfoCompat0.setClickable(accessibilityNodeInfoCompat1.isClickable());
            accessibilityNodeInfoCompat0.setFocusable(accessibilityNodeInfoCompat1.isFocusable());
            accessibilityNodeInfoCompat0.setFocused(accessibilityNodeInfoCompat1.isFocused());
            accessibilityNodeInfoCompat0.setAccessibilityFocused(accessibilityNodeInfoCompat1.isAccessibilityFocused());
            accessibilityNodeInfoCompat0.setSelected(accessibilityNodeInfoCompat1.isSelected());
            accessibilityNodeInfoCompat0.setLongClickable(accessibilityNodeInfoCompat1.isLongClickable());
            accessibilityNodeInfoCompat0.addAction(accessibilityNodeInfoCompat1.getActions());
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public boolean dispatchPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            if(accessibilityEvent0.getEventType() == 0x20) {
                List list0 = accessibilityEvent0.getText();
                View view1 = DrawerLayout.this.findVisibleDrawer();
                if(view1 != null) {
                    int v = DrawerLayout.this.getDrawerViewAbsoluteGravity(view1);
                    CharSequence charSequence0 = DrawerLayout.this.getDrawerTitle(v);
                    if(charSequence0 != null) {
                        list0.add(charSequence0);
                    }
                }
                return true;
            }
            return super.dispatchPopulateAccessibilityEvent(view0, accessibilityEvent0);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            super.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
            accessibilityEvent0.setClassName("androidx.drawerlayout.widget.DrawerLayout");
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            if(DrawerLayout.CAN_HIDE_DESCENDANTS) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
            }
            else {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat1 = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat0);
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat1);
                accessibilityNodeInfoCompat0.setSource(view0);
                ViewParent viewParent0 = ViewCompat.getParentForAccessibility(view0);
                if(viewParent0 instanceof View) {
                    accessibilityNodeInfoCompat0.setParent(((View)viewParent0));
                }
                this.copyNodeInfoNoChildren(accessibilityNodeInfoCompat0, accessibilityNodeInfoCompat1);
                accessibilityNodeInfoCompat1.recycle();
                this.addChildrenForAccessibility(accessibilityNodeInfoCompat0, ((ViewGroup)view0));
            }
            accessibilityNodeInfoCompat0.setClassName("androidx.drawerlayout.widget.DrawerLayout");
            accessibilityNodeInfoCompat0.setFocusable(false);
            accessibilityNodeInfoCompat0.setFocused(false);
            accessibilityNodeInfoCompat0.removeAction(AccessibilityActionCompat.ACTION_FOCUS);
            accessibilityNodeInfoCompat0.removeAction(AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup0, View view0, AccessibilityEvent accessibilityEvent0) {
            return DrawerLayout.CAN_HIDE_DESCENDANTS || DrawerLayout.includeChildForAccessibility(view0) ? super.onRequestSendAccessibilityEvent(viewGroup0, view0, accessibilityEvent0) : false;
        }
    }

    static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
            if(!DrawerLayout.includeChildForAccessibility(view0)) {
                accessibilityNodeInfoCompat0.setParent(null);
            }
        }
    }

    public interface DrawerListener {
        void onDrawerClosed(@NonNull View arg1);

        void onDrawerOpened(@NonNull View arg1);

        void onDrawerSlide(@NonNull View arg1, float arg2);

        void onDrawerStateChanged(int arg1);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int FLAG_IS_CLOSING = 4;
        private static final int FLAG_IS_OPENED = 1;
        private static final int FLAG_IS_OPENING = 2;
        public int gravity;
        boolean isPeeking;
        float onScreen;
        int openState;

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.gravity = 0;
        }

        public LayoutParams(int v, int v1, int v2) {
            this(v, v1);
            this.gravity = v2;
        }

        public LayoutParams(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.gravity = 0;
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, DrawerLayout.LAYOUT_ATTRS);
            this.gravity = typedArray0.getInt(0, 0);
            typedArray0.recycle();
        }

        public LayoutParams(@NonNull ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
            this.gravity = 0;
        }

        public LayoutParams(@NonNull ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
            this.gravity = 0;
        }

        public LayoutParams(@NonNull LayoutParams drawerLayout$LayoutParams0) {
            super(drawerLayout$LayoutParams0);
            this.gravity = drawerLayout$LayoutParams0.gravity;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        int lockModeEnd;
        int lockModeLeft;
        int lockModeRight;
        int lockModeStart;
        int openDrawerGravity;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return new SavedState(parcel0, classLoader0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                @Override  // android.os.Parcelable$ClassLoaderCreator
                public Object createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return this.createFromParcel(parcel0, classLoader0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        public SavedState(@NonNull Parcel parcel0, @Nullable ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.openDrawerGravity = parcel0.readInt();
            this.lockModeLeft = parcel0.readInt();
            this.lockModeRight = parcel0.readInt();
            this.lockModeStart = parcel0.readInt();
            this.lockModeEnd = parcel0.readInt();
        }

        public SavedState(@NonNull Parcelable parcelable0) {
            super(parcelable0);
            this.openDrawerGravity = 0;
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.openDrawerGravity);
            parcel0.writeInt(this.lockModeLeft);
            parcel0.writeInt(this.lockModeRight);
            parcel0.writeInt(this.lockModeStart);
            parcel0.writeInt(this.lockModeEnd);
        }
    }

    public static abstract class SimpleDrawerListener implements DrawerListener {
        @Override  // androidx.drawerlayout.widget.DrawerLayout$DrawerListener
        public void onDrawerClosed(View view0) {
        }

        @Override  // androidx.drawerlayout.widget.DrawerLayout$DrawerListener
        public void onDrawerOpened(View view0) {
        }

        @Override  // androidx.drawerlayout.widget.DrawerLayout$DrawerListener
        public void onDrawerSlide(View view0, float f) {
        }

        @Override  // androidx.drawerlayout.widget.DrawerLayout$DrawerListener
        public void onDrawerStateChanged(int v) {
        }
    }

    class ViewDragCallback extends Callback {
        private final int mAbsGravity;
        private ViewDragHelper mDragger;
        private final Runnable mPeekRunnable;

        ViewDragCallback(int v) {
            this.mPeekRunnable = () -> {
                int v2;
                View view0;
                int v = ViewDragCallback.this.mDragger.getEdgeSize();
                int v1 = 0;
                boolean z = ViewDragCallback.this.mAbsGravity == 3;
                if(z) {
                    view0 = DrawerLayout.this.findDrawerWithGravity(3);
                    if(view0 != null) {
                        v1 = -view0.getWidth();
                    }
                    v2 = v1 + v;
                }
                else {
                    view0 = DrawerLayout.this.findDrawerWithGravity(5);
                    v2 = DrawerLayout.this.getWidth() - v;
                }
                if(view0 != null && (z && view0.getLeft() < v2 || !z && view0.getLeft() > v2) && DrawerLayout.this.getDrawerLockMode(view0) == 0) {
                    LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                    ViewDragCallback.this.mDragger.smoothSlideViewTo(view0, v2, view0.getTop());
                    drawerLayout$LayoutParams0.isPeeking = true;
                    DrawerLayout.this.invalidate();
                    ViewDragCallback.this.closeOtherDrawer();
                    DrawerLayout.this.cancelChildViewTouch();
                }
            };
            this.mAbsGravity = v;
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public int clampViewPositionHorizontal(View view0, int v, int v1) {
            if(DrawerLayout.this.checkDrawerViewAbsoluteGravity(view0, 3)) {
                return Math.max(-view0.getWidth(), Math.min(v, 0));
            }
            int v2 = DrawerLayout.this.getWidth();
            return Math.max(v2 - view0.getWidth(), Math.min(v, v2));
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public int clampViewPositionVertical(View view0, int v, int v1) {
            return view0.getTop();
        }

        private void closeOtherDrawer() {
            View view0 = DrawerLayout.this.findDrawerWithGravity((this.mAbsGravity == 3 ? 5 : 3));
            if(view0 != null) {
                DrawerLayout.this.closeDrawer(view0);
            }
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public int getViewHorizontalDragRange(View view0) {
            return DrawerLayout.this.isDrawerView(view0) ? view0.getWidth() : 0;
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onEdgeDragStarted(int v, int v1) {
            View view0 = (v & 1) == 1 ? DrawerLayout.this.findDrawerWithGravity(3) : DrawerLayout.this.findDrawerWithGravity(5);
            if(view0 != null && DrawerLayout.this.getDrawerLockMode(view0) == 0) {
                this.mDragger.captureChildView(view0, v1);
            }
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public boolean onEdgeLock(int v) {
            return false;
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onEdgeTouched(int v, int v1) {
            DrawerLayout.this.postDelayed(this.mPeekRunnable, 0xA0L);
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewCaptured(View view0, int v) {
            ((LayoutParams)view0.getLayoutParams()).isPeeking = false;
            this.closeOtherDrawer();
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewDragStateChanged(int v) {
            View view0 = this.mDragger.getCapturedView();
            DrawerLayout.this.updateDrawerState(this.mAbsGravity, v, view0);
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewPositionChanged(View view0, int v, int v1, int v2, int v3) {
            int v4 = view0.getWidth();
            float f = DrawerLayout.this.checkDrawerViewAbsoluteGravity(view0, 3) ? ((float)(v + v4)) / ((float)v4) : ((float)(DrawerLayout.this.getWidth() - v)) / ((float)v4);
            DrawerLayout.this.setDrawerViewOffset(view0, f);
            view0.setVisibility((f == 0.0f ? 4 : 0));
            DrawerLayout.this.invalidate();
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewReleased(View view0, float f, float f1) {
            int v1;
            float f2 = DrawerLayout.this.getDrawerViewOffset(view0);
            int v = view0.getWidth();
            if(!DrawerLayout.this.checkDrawerViewAbsoluteGravity(view0, 3)) {
                int v2 = DrawerLayout.this.getWidth();
                if(f < 0.0f || f == 0.0f && f2 > 0.5f) {
                    v2 -= v;
                }
                v1 = v2;
            }
            else if(f <= 0.0f && (f != 0.0f || f2 <= 0.5f)) {
                v1 = -v;
            }
            else {
                v1 = 0;
            }
            this.mDragger.settleCapturedViewAt(v1, view0.getTop());
            DrawerLayout.this.invalidate();
        }

        // 检测为 Lambda 实现
        void peekDrawer() [...]

        public void removeCallbacks() {
            DrawerLayout.this.removeCallbacks(this.mPeekRunnable);
        }

        public void setDragger(ViewDragHelper viewDragHelper0) {
            this.mDragger = viewDragHelper0;
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public boolean tryCaptureView(View view0, int v) {
            return DrawerLayout.this.isDrawerView(view0) && DrawerLayout.this.checkDrawerViewAbsoluteGravity(view0, this.mAbsGravity) && DrawerLayout.this.getDrawerLockMode(view0) == 0;
        }

        class androidx.drawerlayout.widget.DrawerLayout.ViewDragCallback.1 implements Runnable {
            @Override
            public void run() {
                ViewDragCallback.this.peekDrawer();
            }
        }

    }

    private static final boolean ALLOW_EDGE_LOCK = false;
    static final boolean CAN_HIDE_DESCENDANTS = false;
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = 0x99000000;
    private static final int DRAWER_ELEVATION = 10;
    static final int[] LAYOUT_ATTRS = null;
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 0x40;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 0xA0;
    private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION = false;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final int[] THEME_ATTRS = null;
    private static final float TOUCH_SLOP_SENSITIVITY = 1.0f;
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private Rect mChildHitRect;
    private Matrix mChildInvertedMatrix;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private boolean mDrawStatusBarBackground;
    private float mDrawerElevation;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private final ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    @Nullable
    private DrawerListener mListener;
    private List mListeners;
    private int mLockModeEnd;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mLockModeStart;
    private int mMinDrawerMargin;
    private final ArrayList mNonDrawerViews;
    private final ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowEnd;
    private Drawable mShadowLeft;
    private Drawable mShadowLeftResolved;
    private Drawable mShadowRight;
    private Drawable mShadowRightResolved;
    private Drawable mShadowStart;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;

    static {
        boolean z = true;
        DrawerLayout.THEME_ATTRS = new int[]{0x1010434};
        DrawerLayout.LAYOUT_ATTRS = new int[]{0x10100B3};
        DrawerLayout.CAN_HIDE_DESCENDANTS = Build.VERSION.SDK_INT >= 19;
        if(Build.VERSION.SDK_INT < 21) {
            z = false;
        }
        DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION = z;
    }

    public DrawerLayout(@NonNull Context context0) {
        this(context0, null);
    }

    public DrawerLayout(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public DrawerLayout(@NonNull Context context0, @Nullable AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
        this.mScrimColor = 0x99000000;
        this.mScrimPaint = new Paint();
        this.mFirstLayout = true;
        this.mLockModeLeft = 3;
        this.mLockModeRight = 3;
        this.mLockModeStart = 3;
        this.mLockModeEnd = 3;
        this.mShadowStart = null;
        this.mShadowEnd = null;
        this.mShadowLeft = null;
        this.mShadowRight = null;
        this.setDescendantFocusability(0x40000);
        float f = this.getResources().getDisplayMetrics().density;
        this.mMinDrawerMargin = (int)(64.0f * f + 0.5f);
        this.mLeftCallback = new ViewDragCallback(this, 3);
        this.mRightCallback = new ViewDragCallback(this, 5);
        this.mLeftDragger = ViewDragHelper.create(this, 1.0f, this.mLeftCallback);
        this.mLeftDragger.setEdgeTrackingEnabled(1);
        this.mLeftDragger.setMinVelocity(400.0f * f);
        this.mLeftCallback.setDragger(this.mLeftDragger);
        this.mRightDragger = ViewDragHelper.create(this, 1.0f, this.mRightCallback);
        this.mRightDragger.setEdgeTrackingEnabled(2);
        this.mRightDragger.setMinVelocity(400.0f * f);
        this.mRightCallback.setDragger(this.mRightDragger);
        this.setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility(this, 1);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate(this));
        this.setMotionEventSplittingEnabled(false);
        if(ViewCompat.getFitsSystemWindows(this)) {
            if(Build.VERSION.SDK_INT >= 21) {
                this.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    @Override  // android.view.View$OnApplyWindowInsetsListener
                    public WindowInsets onApplyWindowInsets(View view0, WindowInsets windowInsets0) {
                        ((DrawerLayout)view0).setChildInsets(windowInsets0, windowInsets0.getSystemWindowInsetTop() > 0);
                        return windowInsets0.consumeSystemWindowInsets();
                    }
                });
                this.setSystemUiVisibility(0x500);
                TypedArray typedArray0 = context0.obtainStyledAttributes(DrawerLayout.THEME_ATTRS);
                try {
                    this.mStatusBarBackground = typedArray0.getDrawable(0);
                }
                finally {
                    typedArray0.recycle();
                }
            }
            else {
                this.mStatusBarBackground = null;
            }
        }
        this.mDrawerElevation = f * 10.0f;
        this.mNonDrawerViews = new ArrayList();
    }

    public void addDrawerListener(@NonNull DrawerListener drawerLayout$DrawerListener0) {
        if(drawerLayout$DrawerListener0 == null) {
            return;
        }
        if(this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(drawerLayout$DrawerListener0);
    }

    @Override  // android.view.ViewGroup
    public void addFocusables(ArrayList arrayList0, int v, int v1) {
        if(this.getDescendantFocusability() == 0x60000) {
            return;
        }
        int v2 = this.getChildCount();
        boolean z = false;
        for(int v4 = 0; v4 < v2; ++v4) {
            View view0 = this.getChildAt(v4);
            if(!this.isDrawerView(view0)) {
                this.mNonDrawerViews.add(view0);
            }
            else if(this.isDrawerOpen(view0)) {
                view0.addFocusables(arrayList0, v, v1);
                z = true;
            }
        }
        if(!z) {
            int v5 = this.mNonDrawerViews.size();
            for(int v3 = 0; v3 < v5; ++v3) {
                View view1 = (View)this.mNonDrawerViews.get(v3);
                if(view1.getVisibility() == 0) {
                    view1.addFocusables(arrayList0, v, v1);
                }
            }
        }
        this.mNonDrawerViews.clear();
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        super.addView(view0, v, viewGroup$LayoutParams0);
        if(this.findOpenDrawer() != null || this.isDrawerView(view0)) {
            ViewCompat.setImportantForAccessibility(view0, 4);
        }
        else {
            ViewCompat.setImportantForAccessibility(view0, 1);
        }
        if(!DrawerLayout.CAN_HIDE_DESCENDANTS) {
            ViewCompat.setAccessibilityDelegate(view0, this.mChildAccessibilityDelegate);
        }
    }

    void cancelChildViewTouch() {
        if(!this.mChildrenCanceledTouch) {
            long v = SystemClock.uptimeMillis();
            MotionEvent motionEvent0 = MotionEvent.obtain(v, v, 3, 0.0f, 0.0f, 0);
            int v1 = this.getChildCount();
            for(int v2 = 0; v2 < v1; ++v2) {
                this.getChildAt(v2).dispatchTouchEvent(motionEvent0);
            }
            motionEvent0.recycle();
            this.mChildrenCanceledTouch = true;
        }
    }

    boolean checkDrawerViewAbsoluteGravity(View view0, int v) {
        return (this.getDrawerViewAbsoluteGravity(view0) & v) == v;
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams0);
    }

    public void closeDrawer(int v) {
        this.closeDrawer(v, true);
    }

    public void closeDrawer(int v, boolean z) {
        View view0 = this.findDrawerWithGravity(v);
        if(view0 == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + DrawerLayout.gravityToString(v));
        }
        this.closeDrawer(view0, z);
    }

    public void closeDrawer(@NonNull View view0) {
        this.closeDrawer(view0, true);
    }

    public void closeDrawer(@NonNull View view0, boolean z) {
        if(!this.isDrawerView(view0)) {
            throw new IllegalArgumentException("View " + view0 + " is not a sliding drawer");
        }
        LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(this.mFirstLayout) {
            drawerLayout$LayoutParams0.onScreen = 0.0f;
            drawerLayout$LayoutParams0.openState = 0;
        }
        else if(z) {
            drawerLayout$LayoutParams0.openState |= 4;
            if(this.checkDrawerViewAbsoluteGravity(view0, 3)) {
                int v = view0.getWidth();
                int v1 = view0.getTop();
                this.mLeftDragger.smoothSlideViewTo(view0, -v, v1);
            }
            else {
                int v2 = this.getWidth();
                int v3 = view0.getTop();
                this.mRightDragger.smoothSlideViewTo(view0, v2, v3);
            }
        }
        else {
            this.moveDrawerToOffset(view0, 0.0f);
            this.updateDrawerState(drawerLayout$LayoutParams0.gravity, 0, view0);
            view0.setVisibility(4);
        }
        this.invalidate();
    }

    public void closeDrawers() {
        this.closeDrawers(false);
    }

    void closeDrawers(boolean z) {
        int v = this.getChildCount();
        boolean z1 = false;
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(this.isDrawerView(view0) && (!z || drawerLayout$LayoutParams0.isPeeking)) {
                int v2 = view0.getWidth();
                if(this.checkDrawerViewAbsoluteGravity(view0, 3)) {
                    int v3 = view0.getTop();
                    z1 |= this.mLeftDragger.smoothSlideViewTo(view0, -v2, v3);
                }
                else {
                    int v4 = this.getWidth();
                    int v5 = view0.getTop();
                    z1 |= this.mRightDragger.smoothSlideViewTo(view0, v4, v5);
                }
                drawerLayout$LayoutParams0.isPeeking = false;
            }
        }
        this.mLeftCallback.removeCallbacks();
        this.mRightCallback.removeCallbacks();
        if(z1) {
            this.invalidate();
        }
    }

    @Override  // android.view.View
    public void computeScroll() {
        int v = this.getChildCount();
        float f = 0.0f;
        for(int v1 = 0; v1 < v; ++v1) {
            f = Math.max(f, ((LayoutParams)this.getChildAt(v1).getLayoutParams()).onScreen);
        }
        this.mScrimOpacity = f;
        if(this.mLeftDragger.continueSettling(true) || this.mRightDragger.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override  // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent0) {
        if((motionEvent0.getSource() & 2) != 0 && motionEvent0.getAction() != 10 && this.mScrimOpacity > 0.0f) {
            int v = this.getChildCount();
            if(v != 0) {
                float f = motionEvent0.getX();
                float f1 = motionEvent0.getY();
                for(int v1 = v - 1; v1 >= 0; --v1) {
                    View view0 = this.getChildAt(v1);
                    if(this.isInBoundsOfChild(f, f1, view0) && !this.isContentView(view0) && this.dispatchTransformedGenericPointerEvent(motionEvent0, view0)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return super.dispatchGenericMotionEvent(motionEvent0);
    }

    void dispatchOnDrawerClosed(View view0) {
        LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if((drawerLayout$LayoutParams0.openState & 1) == 1) {
            drawerLayout$LayoutParams0.openState = 0;
            List list0 = this.mListeners;
            if(list0 != null) {
                for(int v = list0.size() - 1; v >= 0; --v) {
                    ((DrawerListener)this.mListeners.get(v)).onDrawerClosed(view0);
                }
            }
            this.updateChildrenImportantForAccessibility(view0, false);
            if(this.hasWindowFocus()) {
                View view1 = this.getRootView();
                if(view1 != null) {
                    view1.sendAccessibilityEvent(0x20);
                }
            }
        }
    }

    void dispatchOnDrawerOpened(View view0) {
        LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if((drawerLayout$LayoutParams0.openState & 1) == 0) {
            drawerLayout$LayoutParams0.openState = 1;
            List list0 = this.mListeners;
            if(list0 != null) {
                for(int v = list0.size() - 1; v >= 0; --v) {
                    ((DrawerListener)this.mListeners.get(v)).onDrawerOpened(view0);
                }
            }
            this.updateChildrenImportantForAccessibility(view0, true);
            if(this.hasWindowFocus()) {
                this.sendAccessibilityEvent(0x20);
            }
        }
    }

    void dispatchOnDrawerSlide(View view0, float f) {
        List list0 = this.mListeners;
        if(list0 != null) {
            for(int v = list0.size() - 1; v >= 0; --v) {
                ((DrawerListener)this.mListeners.get(v)).onDrawerSlide(view0, f);
            }
        }
    }

    private boolean dispatchTransformedGenericPointerEvent(MotionEvent motionEvent0, View view0) {
        boolean z;
        if(!view0.getMatrix().isIdentity()) {
            MotionEvent motionEvent1 = this.getTransformedMotionEvent(motionEvent0, view0);
            z = view0.dispatchGenericMotionEvent(motionEvent1);
            motionEvent1.recycle();
            return z;
        }
        float f = (float)(this.getScrollX() - view0.getLeft());
        float f1 = (float)(this.getScrollY() - view0.getTop());
        motionEvent0.offsetLocation(f, f1);
        z = view0.dispatchGenericMotionEvent(motionEvent0);
        motionEvent0.offsetLocation(-f, -f1);
        return z;
    }

    @Override  // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas0, View view0, long v) {
        int v6;
        int v1 = this.getHeight();
        boolean z = this.isContentView(view0);
        int v2 = this.getWidth();
        int v3 = canvas0.save();
        int v4 = 0;
        if(z) {
            int v5 = this.getChildCount();
            v6 = v2;
            int v8 = 0;
            for(int v7 = 0; v7 < v5; ++v7) {
                View view1 = this.getChildAt(v7);
                if(view1 != view0 && view1.getVisibility() == 0 && DrawerLayout.hasOpaqueBackground(view1) && this.isDrawerView(view1) && view1.getHeight() >= v1) {
                    if(this.checkDrawerViewAbsoluteGravity(view1, 3)) {
                        int v9 = view1.getRight();
                        if(v9 > v8) {
                            v8 = v9;
                        }
                    }
                    else {
                        int v10 = view1.getLeft();
                        if(v10 < v6) {
                            v6 = v10;
                        }
                    }
                }
            }
            canvas0.clipRect(v8, 0, v6, this.getHeight());
            v4 = v8;
        }
        else {
            v6 = v2;
        }
        boolean z1 = super.drawChild(canvas0, view0, v);
        canvas0.restoreToCount(v3);
        float f = this.mScrimOpacity;
        if(f > 0.0f && z) {
            this.mScrimPaint.setColor(this.mScrimColor & 0xFFFFFF | ((int)(((float)((0xFF000000 & this.mScrimColor) >>> 24)) * f)) << 24);
            canvas0.drawRect(((float)v4), 0.0f, ((float)v6), ((float)this.getHeight()), this.mScrimPaint);
            return z1;
        }
        if(this.mShadowLeftResolved != null && this.checkDrawerViewAbsoluteGravity(view0, 3)) {
            int v11 = this.mShadowLeftResolved.getIntrinsicWidth();
            int v12 = view0.getRight();
            this.mShadowLeftResolved.setBounds(v12, view0.getTop(), v11 + v12, view0.getBottom());
            this.mShadowLeftResolved.setAlpha(((int)(Math.max(0.0f, Math.min(((float)v12) / ((float)this.mLeftDragger.getEdgeSize()), 1.0f)) * 255.0f)));
            this.mShadowLeftResolved.draw(canvas0);
            return z1;
        }
        if(this.mShadowRightResolved != null && this.checkDrawerViewAbsoluteGravity(view0, 5)) {
            int v13 = this.mShadowRightResolved.getIntrinsicWidth();
            int v14 = view0.getLeft();
            float f1 = Math.max(0.0f, Math.min(((float)(this.getWidth() - v14)) / ((float)this.mRightDragger.getEdgeSize()), 1.0f));
            this.mShadowRightResolved.setBounds(v14 - v13, view0.getTop(), v14, view0.getBottom());
            this.mShadowRightResolved.setAlpha(((int)(f1 * 255.0f)));
            this.mShadowRightResolved.draw(canvas0);
        }
        return z1;
    }

    View findDrawerWithGravity(int v) {
        int v1 = GravityCompat.getAbsoluteGravity(v, ViewCompat.getLayoutDirection(this));
        int v2 = this.getChildCount();
        for(int v3 = 0; v3 < v2; ++v3) {
            View view0 = this.getChildAt(v3);
            if((this.getDrawerViewAbsoluteGravity(view0) & 7) == (v1 & 7)) {
                return view0;
            }
        }
        return null;
    }

    View findOpenDrawer() {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            if((((LayoutParams)view0.getLayoutParams()).openState & 1) == 1) {
                return view0;
            }
        }
        return null;
    }

    View findVisibleDrawer() {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            if(this.isDrawerView(view0) && this.isDrawerVisible(view0)) {
                return view0;
            }
        }
        return null;
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(viewGroup$LayoutParams0 instanceof LayoutParams) {
            return new LayoutParams(((LayoutParams)viewGroup$LayoutParams0));
        }
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0)) : new LayoutParams(viewGroup$LayoutParams0);
    }

    // 去混淆评级： 低(20)
    public float getDrawerElevation() {
        return DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION ? this.mDrawerElevation : 0.0f;
    }

    public int getDrawerLockMode(int v) {
        int v1 = ViewCompat.getLayoutDirection(this);
        switch(v) {
            case 3: {
                int v2 = this.mLockModeLeft;
                if(v2 != 3) {
                    return v2;
                }
                int v3 = v1 == 0 ? this.mLockModeStart : this.mLockModeEnd;
                return v3 == 3 ? 0 : v3;
            }
            case 5: {
                int v4 = this.mLockModeRight;
                if(v4 != 3) {
                    return v4;
                }
                int v5 = v1 == 0 ? this.mLockModeEnd : this.mLockModeStart;
                return v5 == 3 ? 0 : v5;
            }
            case 0x800003: {
                int v6 = this.mLockModeStart;
                if(v6 != 3) {
                    return v6;
                }
                int v7 = v1 == 0 ? this.mLockModeLeft : this.mLockModeRight;
                return v7 == 3 ? 0 : v7;
            }
            case 0x800005: {
                int v8 = this.mLockModeEnd;
                if(v8 != 3) {
                    return v8;
                }
                int v9 = v1 == 0 ? this.mLockModeRight : this.mLockModeLeft;
                return v9 == 3 ? 0 : v9;
            }
            default: {
                return 0;
            }
        }
    }

    public int getDrawerLockMode(@NonNull View view0) {
        if(!this.isDrawerView(view0)) {
            throw new IllegalArgumentException("View " + view0 + " is not a drawer");
        }
        return this.getDrawerLockMode(((LayoutParams)view0.getLayoutParams()).gravity);
    }

    @Nullable
    public CharSequence getDrawerTitle(int v) {
        int v1 = GravityCompat.getAbsoluteGravity(v, ViewCompat.getLayoutDirection(this));
        if(v1 == 3) {
            return this.mTitleLeft;
        }
        return v1 == 5 ? this.mTitleRight : null;
    }

    int getDrawerViewAbsoluteGravity(View view0) {
        return GravityCompat.getAbsoluteGravity(((LayoutParams)view0.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
    }

    float getDrawerViewOffset(View view0) {
        return ((LayoutParams)view0.getLayoutParams()).onScreen;
    }

    @Nullable
    public Drawable getStatusBarBackgroundDrawable() {
        return this.mStatusBarBackground;
    }

    private MotionEvent getTransformedMotionEvent(MotionEvent motionEvent0, View view0) {
        int v = this.getScrollX();
        int v1 = view0.getLeft();
        int v2 = this.getScrollY();
        int v3 = view0.getTop();
        MotionEvent motionEvent1 = MotionEvent.obtain(motionEvent0);
        motionEvent1.offsetLocation(((float)(v - v1)), ((float)(v2 - v3)));
        Matrix matrix0 = view0.getMatrix();
        if(!matrix0.isIdentity()) {
            if(this.mChildInvertedMatrix == null) {
                this.mChildInvertedMatrix = new Matrix();
            }
            matrix0.invert(this.mChildInvertedMatrix);
            motionEvent1.transform(this.mChildInvertedMatrix);
        }
        return motionEvent1;
    }

    static String gravityToString(int v) {
        if((v & 3) == 3) {
            return "LEFT";
        }
        return (v & 5) == 5 ? "RIGHT" : Integer.toHexString(v);
    }

    private static boolean hasOpaqueBackground(View view0) {
        Drawable drawable0 = view0.getBackground();
        return drawable0 != null && drawable0.getOpacity() == -1;
    }

    private boolean hasPeekingDrawer() {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            if(((LayoutParams)this.getChildAt(v1).getLayoutParams()).isPeeking) {
                return true;
            }
        }
        return false;
    }

    private boolean hasVisibleDrawer() {
        return this.findVisibleDrawer() != null;
    }

    static boolean includeChildForAccessibility(View view0) {
        switch(ViewCompat.getImportantForAccessibility(view0)) {
            case 2: 
            case 4: {
                return false;
            }
            default: {
                return true;
            }
        }
    }

    boolean isContentView(View view0) {
        return ((LayoutParams)view0.getLayoutParams()).gravity == 0;
    }

    public boolean isDrawerOpen(int v) {
        View view0 = this.findDrawerWithGravity(v);
        return view0 == null ? false : this.isDrawerOpen(view0);
    }

    public boolean isDrawerOpen(@NonNull View view0) {
        if(!this.isDrawerView(view0)) {
            throw new IllegalArgumentException("View " + view0 + " is not a drawer");
        }
        return (((LayoutParams)view0.getLayoutParams()).openState & 1) == 1;
    }

    boolean isDrawerView(View view0) {
        int v = GravityCompat.getAbsoluteGravity(((LayoutParams)view0.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view0));
        return (v & 3) == 0 ? (v & 5) != 0 : true;
    }

    public boolean isDrawerVisible(int v) {
        View view0 = this.findDrawerWithGravity(v);
        return view0 == null ? false : this.isDrawerVisible(view0);
    }

    public boolean isDrawerVisible(@NonNull View view0) {
        if(!this.isDrawerView(view0)) {
            throw new IllegalArgumentException("View " + view0 + " is not a drawer");
        }
        return ((LayoutParams)view0.getLayoutParams()).onScreen > 0.0f;
    }

    private boolean isInBoundsOfChild(float f, float f1, View view0) {
        if(this.mChildHitRect == null) {
            this.mChildHitRect = new Rect();
        }
        view0.getHitRect(this.mChildHitRect);
        return this.mChildHitRect.contains(((int)f), ((int)f1));
    }

    private boolean mirror(Drawable drawable0, int v) {
        if(drawable0 != null && DrawableCompat.isAutoMirrored(drawable0)) {
            DrawableCompat.setLayoutDirection(drawable0, v);
            return true;
        }
        return false;
    }

    void moveDrawerToOffset(View view0, float f) {
        float f1 = this.getDrawerViewOffset(view0);
        float f2 = (float)view0.getWidth();
        view0.offsetLeftAndRight((this.checkDrawerViewAbsoluteGravity(view0, 3) ? ((int)(f2 * f)) - ((int)(f1 * f2)) : -(((int)(f2 * f)) - ((int)(f1 * f2)))));
        this.setDrawerViewOffset(view0, f);
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
    }

    @Override  // android.view.View
    public void onDraw(Canvas canvas0) {
        int v;
        super.onDraw(canvas0);
        if(this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            if(Build.VERSION.SDK_INT >= 21) {
                Object object0 = this.mLastInsets;
                v = object0 == null ? 0 : ((WindowInsets)object0).getSystemWindowInsetTop();
            }
            else {
                v = 0;
            }
            if(v > 0) {
                this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), v);
                this.mStatusBarBackground.draw(canvas0);
            }
        }
    }

    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        boolean z2;
        int v = motionEvent0.getActionMasked();
        boolean z = this.mLeftDragger.shouldInterceptTouchEvent(motionEvent0);
        boolean z1 = this.mRightDragger.shouldInterceptTouchEvent(motionEvent0);
        switch(v) {
            case 0: {
                float f = motionEvent0.getX();
                float f1 = motionEvent0.getY();
                this.mInitialMotionX = f;
                this.mInitialMotionY = f1;
                if(this.mScrimOpacity > 0.0f) {
                    View view0 = this.mLeftDragger.findTopChildUnder(((int)f), ((int)f1));
                    z2 = view0 == null || !this.isContentView(view0) ? false : true;
                }
                else {
                    z2 = false;
                }
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                return z || z1 || z2 || this.hasPeekingDrawer() || this.mChildrenCanceledTouch;
            }
            case 2: {
                if(this.mLeftDragger.checkTouchSlop(3)) {
                    this.mLeftCallback.removeCallbacks();
                    this.mRightCallback.removeCallbacks();
                    return z || z1 || this.hasPeekingDrawer() || this.mChildrenCanceledTouch;
                }
                return z || z1 || this.hasPeekingDrawer() || this.mChildrenCanceledTouch;
            }
            case 1: 
            case 3: {
                this.closeDrawers(true);
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                return z || z1 || this.hasPeekingDrawer() || this.mChildrenCanceledTouch;
            }
            default: {
                return z || z1 || this.hasPeekingDrawer() || this.mChildrenCanceledTouch;
            }
        }
    }

    @Override  // android.view.View
    public boolean onKeyDown(int v, KeyEvent keyEvent0) {
        if(v == 4 && this.hasVisibleDrawer()) {
            keyEvent0.startTracking();
            return true;
        }
        return super.onKeyDown(v, keyEvent0);
    }

    @Override  // android.view.View
    public boolean onKeyUp(int v, KeyEvent keyEvent0) {
        if(v == 4) {
            View view0 = this.findVisibleDrawer();
            if(view0 != null && this.getDrawerLockMode(view0) == 0) {
                this.closeDrawers();
            }
            return view0 != null;
        }
        return super.onKeyUp(v, keyEvent0);
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        float f;
        int v9;
        this.mInLayout = true;
        int v4 = v2 - v;
        int v5 = this.getChildCount();
        for(int v6 = 0; v6 < v5; ++v6) {
            View view0 = this.getChildAt(v6);
            if(view0.getVisibility() != 8) {
                LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(this.isContentView(view0)) {
                    view0.layout(drawerLayout$LayoutParams0.leftMargin, drawerLayout$LayoutParams0.topMargin, drawerLayout$LayoutParams0.leftMargin + view0.getMeasuredWidth(), drawerLayout$LayoutParams0.topMargin + view0.getMeasuredHeight());
                }
                else {
                    int v7 = view0.getMeasuredWidth();
                    int v8 = view0.getMeasuredHeight();
                    if(this.checkDrawerViewAbsoluteGravity(view0, 3)) {
                        v9 = ((int)(drawerLayout$LayoutParams0.onScreen * ((float)v7))) - v7;
                        f = ((float)(v7 + v9)) / ((float)v7);
                    }
                    else {
                        int v10 = v4 - ((int)(drawerLayout$LayoutParams0.onScreen * ((float)v7)));
                        f = ((float)(v4 - v10)) / ((float)v7);
                        v9 = v10;
                    }
                    boolean z1 = f != drawerLayout$LayoutParams0.onScreen;
                    switch(drawerLayout$LayoutParams0.gravity & 0x70) {
                        case 16: {
                            int v11 = v3 - v1;
                            int v12 = (v11 - v8) / 2;
                            if(v12 < drawerLayout$LayoutParams0.topMargin) {
                                v12 = drawerLayout$LayoutParams0.topMargin;
                            }
                            else if(v12 + v8 > v11 - drawerLayout$LayoutParams0.bottomMargin) {
                                v12 = v11 - drawerLayout$LayoutParams0.bottomMargin - v8;
                            }
                            view0.layout(v9, v12, v7 + v9, v8 + v12);
                            break;
                        }
                        case 80: {
                            view0.layout(v9, v3 - v1 - drawerLayout$LayoutParams0.bottomMargin - view0.getMeasuredHeight(), v7 + v9, v3 - v1 - drawerLayout$LayoutParams0.bottomMargin);
                            break;
                        }
                        default: {
                            view0.layout(v9, drawerLayout$LayoutParams0.topMargin, v7 + v9, drawerLayout$LayoutParams0.topMargin + v8);
                        }
                    }
                    if(z1) {
                        this.setDrawerViewOffset(view0, f);
                    }
                    int v13 = drawerLayout$LayoutParams0.onScreen > 0.0f ? 0 : 4;
                    if(view0.getVisibility() != v13) {
                        view0.setVisibility(v13);
                    }
                }
            }
        }
        this.mInLayout = false;
        this.mFirstLayout = false;
    }

    @Override  // android.view.View
    @SuppressLint({"WrongConstant"})
    protected void onMeasure(int v, int v1) {
        int v2 = View.MeasureSpec.getMode(v);
        int v3 = View.MeasureSpec.getMode(v1);
        int v4 = View.MeasureSpec.getSize(v);
        int v5 = View.MeasureSpec.getSize(v1);
        if(v2 != 0x40000000 || v3 != 0x40000000) {
            if(!this.isInEditMode()) {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
            if(v2 != 0x80000000 && v2 == 0) {
                v4 = 300;
            }
            if(v3 != 0x80000000 && v3 == 0) {
                v5 = 300;
            }
        }
        this.setMeasuredDimension(v4, v5);
        boolean z = this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this);
        int v6 = ViewCompat.getLayoutDirection(this);
        int v7 = this.getChildCount();
        boolean z1 = false;
        boolean z2 = false;
        for(int v8 = 0; v8 < v7; ++v8) {
            View view0 = this.getChildAt(v8);
            if(view0.getVisibility() != 8) {
                LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(z) {
                    int v9 = GravityCompat.getAbsoluteGravity(drawerLayout$LayoutParams0.gravity, v6);
                    if(!ViewCompat.getFitsSystemWindows(view0)) {
                        if(Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsets1 = (WindowInsets)this.mLastInsets;
                            if(v9 == 3) {
                                windowInsets1 = windowInsets1.replaceSystemWindowInsets(windowInsets1.getSystemWindowInsetLeft(), windowInsets1.getSystemWindowInsetTop(), 0, windowInsets1.getSystemWindowInsetBottom());
                            }
                            else if(v9 == 5) {
                                windowInsets1 = windowInsets1.replaceSystemWindowInsets(0, windowInsets1.getSystemWindowInsetTop(), windowInsets1.getSystemWindowInsetRight(), windowInsets1.getSystemWindowInsetBottom());
                            }
                            drawerLayout$LayoutParams0.leftMargin = windowInsets1.getSystemWindowInsetLeft();
                            drawerLayout$LayoutParams0.topMargin = windowInsets1.getSystemWindowInsetTop();
                            drawerLayout$LayoutParams0.rightMargin = windowInsets1.getSystemWindowInsetRight();
                            drawerLayout$LayoutParams0.bottomMargin = windowInsets1.getSystemWindowInsetBottom();
                        }
                    }
                    else if(Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsets0 = (WindowInsets)this.mLastInsets;
                        if(v9 == 3) {
                            windowInsets0 = windowInsets0.replaceSystemWindowInsets(windowInsets0.getSystemWindowInsetLeft(), windowInsets0.getSystemWindowInsetTop(), 0, windowInsets0.getSystemWindowInsetBottom());
                        }
                        else if(v9 == 5) {
                            windowInsets0 = windowInsets0.replaceSystemWindowInsets(0, windowInsets0.getSystemWindowInsetTop(), windowInsets0.getSystemWindowInsetRight(), windowInsets0.getSystemWindowInsetBottom());
                        }
                        view0.dispatchApplyWindowInsets(windowInsets0);
                    }
                }
                if(this.isContentView(view0)) {
                    view0.measure(View.MeasureSpec.makeMeasureSpec(v4 - drawerLayout$LayoutParams0.leftMargin - drawerLayout$LayoutParams0.rightMargin, 0x40000000), View.MeasureSpec.makeMeasureSpec(v5 - drawerLayout$LayoutParams0.topMargin - drawerLayout$LayoutParams0.bottomMargin, 0x40000000));
                }
                else {
                    if(!this.isDrawerView(view0)) {
                        throw new IllegalStateException("Child " + view0 + " at index " + v8 + " does not have a valid layout_gravity - must be Gravity.LEFT, " + "Gravity.RIGHT or Gravity.NO_GRAVITY");
                    }
                    if(DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION) {
                        float f = ViewCompat.getElevation(view0);
                        float f1 = this.mDrawerElevation;
                        if(f != f1) {
                            ViewCompat.setElevation(view0, f1);
                        }
                    }
                    int v10 = this.getDrawerViewAbsoluteGravity(view0) & 7;
                    if(v10 == 3 && z1 || v10 != 3 && z2) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + DrawerLayout.gravityToString(v10) + " but this " + "DrawerLayout" + " already has a " + "drawer view along that edge");
                    }
                    if(v10 == 3) {
                        z1 = true;
                    }
                    else {
                        z2 = true;
                    }
                    view0.measure(DrawerLayout.getChildMeasureSpec(v, this.mMinDrawerMargin + drawerLayout$LayoutParams0.leftMargin + drawerLayout$LayoutParams0.rightMargin, drawerLayout$LayoutParams0.width), DrawerLayout.getChildMeasureSpec(v1, drawerLayout$LayoutParams0.topMargin + drawerLayout$LayoutParams0.bottomMargin, drawerLayout$LayoutParams0.height));
                    continue;
                }
            }
        }
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        if(((SavedState)parcelable0).openDrawerGravity != 0) {
            View view0 = this.findDrawerWithGravity(((SavedState)parcelable0).openDrawerGravity);
            if(view0 != null) {
                this.openDrawer(view0);
            }
        }
        if(((SavedState)parcelable0).lockModeLeft != 3) {
            this.setDrawerLockMode(((SavedState)parcelable0).lockModeLeft, 3);
        }
        if(((SavedState)parcelable0).lockModeRight != 3) {
            this.setDrawerLockMode(((SavedState)parcelable0).lockModeRight, 5);
        }
        if(((SavedState)parcelable0).lockModeStart != 3) {
            this.setDrawerLockMode(((SavedState)parcelable0).lockModeStart, 0x800003);
        }
        if(((SavedState)parcelable0).lockModeEnd != 3) {
            this.setDrawerLockMode(((SavedState)parcelable0).lockModeEnd, 0x800005);
        }
    }

    @Override  // android.view.View
    public void onRtlPropertiesChanged(int v) {
        this.resolveShadowDrawables();
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        int v = this.getChildCount();
        int v1 = 0;
        while(v1 < v) {
            LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)this.getChildAt(v1).getLayoutParams();
            boolean z = true;
            boolean z1 = drawerLayout$LayoutParams0.openState == 1;
            if(drawerLayout$LayoutParams0.openState != 2) {
                z = false;
            }
            if(!z1 && !z) {
                ++v1;
            }
            else {
                parcelable0.openDrawerGravity = drawerLayout$LayoutParams0.gravity;
                if(true) {
                    break;
                }
            }
        }
        parcelable0.lockModeLeft = this.mLockModeLeft;
        parcelable0.lockModeRight = this.mLockModeRight;
        parcelable0.lockModeStart = this.mLockModeStart;
        parcelable0.lockModeEnd = this.mLockModeEnd;
        return parcelable0;
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        boolean z;
        this.mLeftDragger.processTouchEvent(motionEvent0);
        this.mRightDragger.processTouchEvent(motionEvent0);
        int v = motionEvent0.getAction();
        if((v & 0xFF) != 3) {
            switch(v & 0xFF) {
                case 0: {
                    float f = motionEvent0.getX();
                    float f1 = motionEvent0.getY();
                    this.mInitialMotionX = f;
                    this.mInitialMotionY = f1;
                    this.mDisallowInterceptRequested = false;
                    this.mChildrenCanceledTouch = false;
                    return true;
                }
                case 1: {
                    float f2 = motionEvent0.getX();
                    float f3 = motionEvent0.getY();
                    View view0 = this.mLeftDragger.findTopChildUnder(((int)f2), ((int)f3));
                    if(view0 == null || !this.isContentView(view0)) {
                        z = true;
                    }
                    else {
                        float f4 = f2 - this.mInitialMotionX;
                        float f5 = f3 - this.mInitialMotionY;
                        int v1 = this.mLeftDragger.getTouchSlop();
                        if(f4 * f4 + f5 * f5 < ((float)(v1 * v1))) {
                            View view1 = this.findOpenDrawer();
                            if(view1 == null) {
                                z = true;
                            }
                            else if(this.getDrawerLockMode(view1) == 2) {
                                z = true;
                            }
                            else {
                                z = false;
                            }
                        }
                        else {
                            z = true;
                        }
                    }
                    this.closeDrawers(z);
                    this.mDisallowInterceptRequested = false;
                    return true;
                }
                default: {
                    return true;
                }
            }
        }
        this.closeDrawers(true);
        this.mDisallowInterceptRequested = false;
        this.mChildrenCanceledTouch = false;
        return true;
    }

    public void openDrawer(int v) {
        this.openDrawer(v, true);
    }

    public void openDrawer(int v, boolean z) {
        View view0 = this.findDrawerWithGravity(v);
        if(view0 == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + DrawerLayout.gravityToString(v));
        }
        this.openDrawer(view0, z);
    }

    public void openDrawer(@NonNull View view0) {
        this.openDrawer(view0, true);
    }

    public void openDrawer(@NonNull View view0, boolean z) {
        if(!this.isDrawerView(view0)) {
            throw new IllegalArgumentException("View " + view0 + " is not a sliding drawer");
        }
        LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(this.mFirstLayout) {
            drawerLayout$LayoutParams0.onScreen = 1.0f;
            drawerLayout$LayoutParams0.openState = 1;
            this.updateChildrenImportantForAccessibility(view0, true);
        }
        else if(z) {
            drawerLayout$LayoutParams0.openState |= 2;
            if(this.checkDrawerViewAbsoluteGravity(view0, 3)) {
                int v = view0.getTop();
                this.mLeftDragger.smoothSlideViewTo(view0, 0, v);
            }
            else {
                int v1 = this.getWidth();
                int v2 = view0.getWidth();
                int v3 = view0.getTop();
                this.mRightDragger.smoothSlideViewTo(view0, v1 - v2, v3);
            }
        }
        else {
            this.moveDrawerToOffset(view0, 1.0f);
            this.updateDrawerState(drawerLayout$LayoutParams0.gravity, 0, view0);
            view0.setVisibility(0);
        }
        this.invalidate();
    }

    public void removeDrawerListener(@NonNull DrawerListener drawerLayout$DrawerListener0) {
        if(drawerLayout$DrawerListener0 == null) {
            return;
        }
        List list0 = this.mListeners;
        if(list0 == null) {
            return;
        }
        list0.remove(drawerLayout$DrawerListener0);
    }

    @Override  // android.view.ViewGroup
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.mDisallowInterceptRequested = z;
        if(z) {
            this.closeDrawers(true);
        }
    }

    @Override  // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if(!this.mInLayout) {
            super.requestLayout();
        }
    }

    private Drawable resolveLeftShadow() {
        int v = ViewCompat.getLayoutDirection(this);
        if(v == 0) {
            Drawable drawable0 = this.mShadowStart;
            if(drawable0 != null) {
                this.mirror(drawable0, 0);
                return this.mShadowStart;
            }
        }
        else {
            Drawable drawable1 = this.mShadowEnd;
            if(drawable1 != null) {
                this.mirror(drawable1, v);
                return this.mShadowEnd;
            }
        }
        return this.mShadowLeft;
    }

    private Drawable resolveRightShadow() {
        int v = ViewCompat.getLayoutDirection(this);
        if(v == 0) {
            Drawable drawable0 = this.mShadowEnd;
            if(drawable0 != null) {
                this.mirror(drawable0, 0);
                return this.mShadowEnd;
            }
        }
        else {
            Drawable drawable1 = this.mShadowStart;
            if(drawable1 != null) {
                this.mirror(drawable1, v);
                return this.mShadowStart;
            }
        }
        return this.mShadowRight;
    }

    private void resolveShadowDrawables() {
        if(DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return;
        }
        this.mShadowLeftResolved = this.resolveLeftShadow();
        this.mShadowRightResolved = this.resolveRightShadow();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setChildInsets(Object object0, boolean z) {
        this.mLastInsets = object0;
        this.mDrawStatusBarBackground = z;
        this.setWillNotDraw(!z && this.getBackground() == null);
        this.requestLayout();
    }

    public void setDrawerElevation(float f) {
        this.mDrawerElevation = f;
        for(int v = 0; v < this.getChildCount(); ++v) {
            View view0 = this.getChildAt(v);
            if(this.isDrawerView(view0)) {
                ViewCompat.setElevation(view0, this.mDrawerElevation);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(DrawerListener drawerLayout$DrawerListener0) {
        DrawerListener drawerLayout$DrawerListener1 = this.mListener;
        if(drawerLayout$DrawerListener1 != null) {
            this.removeDrawerListener(drawerLayout$DrawerListener1);
        }
        if(drawerLayout$DrawerListener0 != null) {
            this.addDrawerListener(drawerLayout$DrawerListener0);
        }
        this.mListener = drawerLayout$DrawerListener0;
    }

    public void setDrawerLockMode(int v) {
        this.setDrawerLockMode(v, 3);
        this.setDrawerLockMode(v, 5);
    }

    public void setDrawerLockMode(int v, int v1) {
        int v2 = GravityCompat.getAbsoluteGravity(v1, ViewCompat.getLayoutDirection(this));
        switch(v1) {
            case 3: {
                this.mLockModeLeft = v;
                break;
            }
            case 5: {
                this.mLockModeRight = v;
                break;
            }
            case 0x800003: {
                this.mLockModeStart = v;
                break;
            }
            case 0x800005: {
                this.mLockModeEnd = v;
            }
        }
        if(v != 0) {
            (v2 == 3 ? this.mLeftDragger : this.mRightDragger).cancel();
        }
        switch(v) {
            case 1: {
                View view0 = this.findDrawerWithGravity(v2);
                if(view0 != null) {
                    this.closeDrawer(view0);
                    return;
                }
                break;
            }
            case 2: {
                View view1 = this.findDrawerWithGravity(v2);
                if(view1 != null) {
                    this.openDrawer(view1);
                    return;
                }
                break;
            }
        }
    }

    public void setDrawerLockMode(int v, @NonNull View view0) {
        if(!this.isDrawerView(view0)) {
            throw new IllegalArgumentException("View " + view0 + " is not a " + "drawer with appropriate layout_gravity");
        }
        this.setDrawerLockMode(v, ((LayoutParams)view0.getLayoutParams()).gravity);
    }

    public void setDrawerShadow(@DrawableRes int v, int v1) {
        this.setDrawerShadow(ContextCompat.getDrawable(this.getContext(), v), v1);
    }

    public void setDrawerShadow(Drawable drawable0, int v) {
        if(DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return;
        }
        if((v & 0x800003) == 0x800003) {
            this.mShadowStart = drawable0;
        }
        else {
            if((v & 0x800005) == 0x800005) {
                this.mShadowEnd = drawable0;
                this.resolveShadowDrawables();
                this.invalidate();
                return;
            }
            if((v & 3) == 3) {
                this.mShadowLeft = drawable0;
            }
            else if((v & 5) == 5) {
                this.mShadowRight = drawable0;
            }
            else {
                return;
            }
        }
        this.resolveShadowDrawables();
        this.invalidate();
    }

    public void setDrawerTitle(int v, @Nullable CharSequence charSequence0) {
        int v1 = GravityCompat.getAbsoluteGravity(v, ViewCompat.getLayoutDirection(this));
        if(v1 == 3) {
            this.mTitleLeft = charSequence0;
            return;
        }
        if(v1 == 5) {
            this.mTitleRight = charSequence0;
        }
    }

    void setDrawerViewOffset(View view0, float f) {
        LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(f == drawerLayout$LayoutParams0.onScreen) {
            return;
        }
        drawerLayout$LayoutParams0.onScreen = f;
        this.dispatchOnDrawerSlide(view0, f);
    }

    public void setScrimColor(@ColorInt int v) {
        this.mScrimColor = v;
        this.invalidate();
    }

    public void setStatusBarBackground(int v) {
        this.mStatusBarBackground = v == 0 ? null : ContextCompat.getDrawable(this.getContext(), v);
        this.invalidate();
    }

    public void setStatusBarBackground(@Nullable Drawable drawable0) {
        this.mStatusBarBackground = drawable0;
        this.invalidate();
    }

    public void setStatusBarBackgroundColor(@ColorInt int v) {
        this.mStatusBarBackground = new ColorDrawable(v);
        this.invalidate();
    }

    private void updateChildrenImportantForAccessibility(View view0, boolean z) {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view1 = this.getChildAt(v1);
            if((z || this.isDrawerView(view1)) && (!z || view1 != view0)) {
                ViewCompat.setImportantForAccessibility(view1, 4);
            }
            else {
                ViewCompat.setImportantForAccessibility(view1, 1);
            }
        }
    }

    void updateDrawerState(int v, int v1, View view0) {
        int v2 = this.mLeftDragger.getViewDragState();
        int v3 = this.mRightDragger.getViewDragState();
        int v4 = 2;
        if(v2 == 1 || v3 == 1) {
            v4 = 1;
        }
        else if(v2 != 2 && v3 != 2) {
            v4 = 0;
        }
        if(view0 != null && v1 == 0) {
            LayoutParams drawerLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(drawerLayout$LayoutParams0.onScreen == 0.0f) {
                this.dispatchOnDrawerClosed(view0);
            }
            else if(drawerLayout$LayoutParams0.onScreen == 1.0f) {
                this.dispatchOnDrawerOpened(view0);
            }
        }
        if(v4 != this.mDrawerState) {
            this.mDrawerState = v4;
            List list0 = this.mListeners;
            if(list0 != null) {
                for(int v5 = list0.size() - 1; v5 >= 0; --v5) {
                    ((DrawerListener)this.mListeners.get(v5)).onDrawerStateChanged(v4);
                }
            }
        }
    }
}

