package androidx.slidingpanelayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper.Callback;
import androidx.customview.widget.ViewDragHelper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect;

        AccessibilityDelegate() {
            this.mTmpRect = new Rect();
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
            accessibilityNodeInfoCompat0.setMovementGranularities(accessibilityNodeInfoCompat1.getMovementGranularities());
        }

        public boolean filter(View view0) {
            return SlidingPaneLayout.this.isDimmed(view0);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            super.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
            accessibilityEvent0.setClassName("androidx.slidingpanelayout.widget.SlidingPaneLayout");
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat1 = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat0);
            super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat1);
            this.copyNodeInfoNoChildren(accessibilityNodeInfoCompat0, accessibilityNodeInfoCompat1);
            accessibilityNodeInfoCompat1.recycle();
            accessibilityNodeInfoCompat0.setClassName("androidx.slidingpanelayout.widget.SlidingPaneLayout");
            accessibilityNodeInfoCompat0.setSource(view0);
            ViewParent viewParent0 = ViewCompat.getParentForAccessibility(view0);
            if(viewParent0 instanceof View) {
                accessibilityNodeInfoCompat0.setParent(((View)viewParent0));
            }
            int v = SlidingPaneLayout.this.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view1 = SlidingPaneLayout.this.getChildAt(v1);
                if(!this.filter(view1) && view1.getVisibility() == 0) {
                    ViewCompat.setImportantForAccessibility(view1, 1);
                    accessibilityNodeInfoCompat0.addChild(view1);
                }
            }
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup0, View view0, AccessibilityEvent accessibilityEvent0) {
            return this.filter(view0) ? false : super.onRequestSendAccessibilityEvent(viewGroup0, view0, accessibilityEvent0);
        }
    }

    class DisableLayerRunnable implements Runnable {
        final View mChildView;

        DisableLayerRunnable(View view0) {
            this.mChildView = view0;
        }

        @Override
        public void run() {
            if(this.mChildView.getParent() == SlidingPaneLayout.this) {
                this.mChildView.setLayerType(0, null);
                SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
            }
            SlidingPaneLayout.this.mPostedRunnables.remove(this);
        }
    }

    class DragHelperCallback extends Callback {
        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public int clampViewPositionHorizontal(View view0, int v, int v1) {
            LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)SlidingPaneLayout.this.mSlideableView.getLayoutParams();
            if(SlidingPaneLayout.this.isLayoutRtlSupport()) {
                int v2 = SlidingPaneLayout.this.getWidth() - (SlidingPaneLayout.this.getPaddingRight() + slidingPaneLayout$LayoutParams0.rightMargin + SlidingPaneLayout.this.mSlideableView.getWidth());
                return Math.max(Math.min(v, v2), v2 - SlidingPaneLayout.this.mSlideRange);
            }
            int v3 = SlidingPaneLayout.this.getPaddingLeft() + slidingPaneLayout$LayoutParams0.leftMargin;
            return Math.min(Math.max(v, v3), SlidingPaneLayout.this.mSlideRange + v3);
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public int clampViewPositionVertical(View view0, int v, int v1) {
            return view0.getTop();
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public int getViewHorizontalDragRange(View view0) {
            return SlidingPaneLayout.this.mSlideRange;
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onEdgeDragStarted(int v, int v1) {
            SlidingPaneLayout.this.mDragHelper.captureChildView(SlidingPaneLayout.this.mSlideableView, v1);
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewCaptured(View view0, int v) {
            SlidingPaneLayout.this.setAllChildrenVisible();
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewDragStateChanged(int v) {
            if(SlidingPaneLayout.this.mDragHelper.getViewDragState() == 0) {
                if(SlidingPaneLayout.this.mSlideOffset == 0.0f) {
                    SlidingPaneLayout.this.updateObscuredViewsVisibility(SlidingPaneLayout.this.mSlideableView);
                    SlidingPaneLayout.this.dispatchOnPanelClosed(SlidingPaneLayout.this.mSlideableView);
                    SlidingPaneLayout.this.mPreservedOpenState = false;
                    return;
                }
                SlidingPaneLayout.this.dispatchOnPanelOpened(SlidingPaneLayout.this.mSlideableView);
                SlidingPaneLayout.this.mPreservedOpenState = true;
            }
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewPositionChanged(View view0, int v, int v1, int v2, int v3) {
            SlidingPaneLayout.this.onPanelDragged(v);
            SlidingPaneLayout.this.invalidate();
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewReleased(View view0, float f, float f1) {
            int v2;
            LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(SlidingPaneLayout.this.isLayoutRtlSupport()) {
                int v = SlidingPaneLayout.this.getPaddingRight() + slidingPaneLayout$LayoutParams0.rightMargin;
                if(f < 0.0f || f == 0.0f && SlidingPaneLayout.this.mSlideOffset > 0.5f) {
                    v += SlidingPaneLayout.this.mSlideRange;
                }
                int v1 = SlidingPaneLayout.this.mSlideableView.getWidth();
                v2 = SlidingPaneLayout.this.getWidth() - v - v1;
            }
            else {
                int v3 = SlidingPaneLayout.this.getPaddingLeft();
                v2 = slidingPaneLayout$LayoutParams0.leftMargin + v3;
                if(f > 0.0f || f == 0.0f && SlidingPaneLayout.this.mSlideOffset > 0.5f) {
                    v2 += SlidingPaneLayout.this.mSlideRange;
                }
            }
            int v4 = view0.getTop();
            SlidingPaneLayout.this.mDragHelper.settleCapturedViewAt(v2, v4);
            SlidingPaneLayout.this.invalidate();
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public boolean tryCaptureView(View view0, int v) {
            return SlidingPaneLayout.this.mIsUnableToDrag ? false : ((LayoutParams)view0.getLayoutParams()).slideable;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int[] ATTRS;
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        public float weight;

        static {
            LayoutParams.ATTRS = new int[]{0x1010181};
        }

        public LayoutParams() {
            super(-1, -1);
            this.weight = 0.0f;
        }

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.weight = 0.0f;
        }

        public LayoutParams(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.weight = 0.0f;
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, LayoutParams.ATTRS);
            this.weight = typedArray0.getFloat(0, 0.0f);
            typedArray0.recycle();
        }

        public LayoutParams(@NonNull ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
            this.weight = 0.0f;
        }

        public LayoutParams(@NonNull ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
            this.weight = 0.0f;
        }

        public LayoutParams(@NonNull LayoutParams slidingPaneLayout$LayoutParams0) {
            super(slidingPaneLayout$LayoutParams0);
            this.weight = slidingPaneLayout$LayoutParams0.weight;
        }
    }

    public interface PanelSlideListener {
        void onPanelClosed(@NonNull View arg1);

        void onPanelOpened(@NonNull View arg1);

        void onPanelSlide(@NonNull View arg1, float arg2);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        boolean isOpen;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return new SavedState(parcel0, null);
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

        SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.isOpen = parcel0.readInt() != 0;
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(((int)this.isOpen));
        }
    }

    public static class SimplePanelSlideListener implements PanelSlideListener {
        @Override  // androidx.slidingpanelayout.widget.SlidingPaneLayout$PanelSlideListener
        public void onPanelClosed(View view0) {
        }

        @Override  // androidx.slidingpanelayout.widget.SlidingPaneLayout$PanelSlideListener
        public void onPanelOpened(View view0) {
        }

        @Override  // androidx.slidingpanelayout.widget.SlidingPaneLayout$PanelSlideListener
        public void onPanelSlide(View view0, float f) {
        }
    }

    private static final int DEFAULT_FADE_COLOR = -858993460;
    private static final int DEFAULT_OVERHANG_SIZE = 0x20;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private boolean mDisplayListReflectionLoaded;
    final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private Method mGetDisplayList;
    private float mInitialMotionX;
    private float mInitialMotionY;
    boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    final ArrayList mPostedRunnables;
    boolean mPreservedOpenState;
    private Field mRecreateDisplayList;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    float mSlideOffset;
    int mSlideRange;
    View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    public SlidingPaneLayout(@NonNull Context context0) {
        this(context0, null);
    }

    public SlidingPaneLayout(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public SlidingPaneLayout(@NonNull Context context0, @Nullable AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mSliderFadeColor = -858993460;
        this.mFirstLayout = true;
        this.mTmpRect = new Rect();
        this.mPostedRunnables = new ArrayList();
        float f = context0.getResources().getDisplayMetrics().density;
        this.mOverhangSize = (int)(32.0f * f + 0.5f);
        this.setWillNotDraw(false);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate(this));
        ViewCompat.setImportantForAccessibility(this, 1);
        this.mDragHelper = ViewDragHelper.create(this, 0.5f, new DragHelperCallback(this));
        this.mDragHelper.setMinVelocity(f * 400.0f);
    }

    protected boolean canScroll(View view0, boolean z, int v, int v1, int v2) {
        if(view0 instanceof ViewGroup) {
            int v3 = view0.getScrollX();
            int v4 = view0.getScrollY();
            for(int v5 = ((ViewGroup)view0).getChildCount() - 1; v5 >= 0; --v5) {
                View view1 = ((ViewGroup)view0).getChildAt(v5);
                int v6 = v1 + v3;
                if(v6 >= view1.getLeft() && v6 < view1.getRight()) {
                    int v7 = v2 + v4;
                    if(v7 >= view1.getTop() && v7 < view1.getBottom() && this.canScroll(view1, true, v, v6 - view1.getLeft(), v7 - view1.getTop())) {
                        return true;
                    }
                }
            }
        }
        return z ? view0.canScrollHorizontally((this.isLayoutRtlSupport() ? v : -v)) : false;
    }

    @Deprecated
    public boolean canSlide() {
        return this.mCanSlide;
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams0);
    }

    private boolean closePane(View view0, int v) {
        if(!this.mFirstLayout && !this.smoothSlideTo(0.0f, v)) {
            return false;
        }
        this.mPreservedOpenState = false;
        return true;
    }

    public boolean closePane() {
        return this.closePane(this.mSlideableView, 0);
    }

    @Override  // android.view.View
    public void computeScroll() {
        if(this.mDragHelper.continueSettling(true)) {
            if(!this.mCanSlide) {
                this.mDragHelper.abort();
                return;
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void dimChildView(View view0, float f, int v) {
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(f > 0.0f && v != 0) {
            if(slidingPaneLayout$LayoutParams0.dimPaint == null) {
                slidingPaneLayout$LayoutParams0.dimPaint = new Paint();
            }
            slidingPaneLayout$LayoutParams0.dimPaint.setColorFilter(new PorterDuffColorFilter(((int)(((float)((0xFF000000 & v) >>> 24)) * f)) << 24 | v & 0xFFFFFF, PorterDuff.Mode.SRC_OVER));
            if(view0.getLayerType() != 2) {
                view0.setLayerType(2, slidingPaneLayout$LayoutParams0.dimPaint);
            }
            this.invalidateChildRegion(view0);
            return;
        }
        if(view0.getLayerType() != 0) {
            if(slidingPaneLayout$LayoutParams0.dimPaint != null) {
                slidingPaneLayout$LayoutParams0.dimPaint.setColorFilter(null);
            }
            DisableLayerRunnable slidingPaneLayout$DisableLayerRunnable0 = new DisableLayerRunnable(this, view0);
            this.mPostedRunnables.add(slidingPaneLayout$DisableLayerRunnable0);
            ViewCompat.postOnAnimation(this, slidingPaneLayout$DisableLayerRunnable0);
        }
    }

    void dispatchOnPanelClosed(View view0) {
        PanelSlideListener slidingPaneLayout$PanelSlideListener0 = this.mPanelSlideListener;
        if(slidingPaneLayout$PanelSlideListener0 != null) {
            slidingPaneLayout$PanelSlideListener0.onPanelClosed(view0);
        }
        this.sendAccessibilityEvent(0x20);
    }

    void dispatchOnPanelOpened(View view0) {
        PanelSlideListener slidingPaneLayout$PanelSlideListener0 = this.mPanelSlideListener;
        if(slidingPaneLayout$PanelSlideListener0 != null) {
            slidingPaneLayout$PanelSlideListener0.onPanelOpened(view0);
        }
        this.sendAccessibilityEvent(0x20);
    }

    void dispatchOnPanelSlide(View view0) {
        PanelSlideListener slidingPaneLayout$PanelSlideListener0 = this.mPanelSlideListener;
        if(slidingPaneLayout$PanelSlideListener0 != null) {
            slidingPaneLayout$PanelSlideListener0.onPanelSlide(view0, this.mSlideOffset);
        }
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        int v4;
        int v3;
        super.draw(canvas0);
        Drawable drawable0 = this.isLayoutRtlSupport() ? this.mShadowDrawableRight : this.mShadowDrawableLeft;
        View view0 = this.getChildCount() <= 1 ? null : this.getChildAt(1);
        if(view0 != null && drawable0 != null) {
            int v = view0.getTop();
            int v1 = view0.getBottom();
            int v2 = drawable0.getIntrinsicWidth();
            if(this.isLayoutRtlSupport()) {
                v3 = view0.getRight();
                v4 = v2 + v3;
            }
            else {
                int v5 = view0.getLeft();
                v4 = v5;
                v3 = v5 - v2;
            }
            drawable0.setBounds(v3, v, v4, v1);
            drawable0.draw(canvas0);
        }
    }

    @Override  // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas0, View view0, long v) {
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        int v1 = canvas0.save();
        if(this.mCanSlide && !slidingPaneLayout$LayoutParams0.slideable && this.mSlideableView != null) {
            canvas0.getClipBounds(this.mTmpRect);
            if(this.isLayoutRtlSupport()) {
                this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
            }
            else {
                this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
            }
            canvas0.clipRect(this.mTmpRect);
        }
        boolean z = super.drawChild(canvas0, view0, v);
        canvas0.restoreToCount(v1);
        return z;
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0)) : new LayoutParams(viewGroup$LayoutParams0);
    }

    @ColorInt
    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }

    @Px
    public int getParallaxDistance() {
        return this.mParallaxBy;
    }

    @ColorInt
    public int getSliderFadeColor() {
        return this.mSliderFadeColor;
    }

    void invalidateChildRegion(View view0) {
        if(Build.VERSION.SDK_INT >= 17) {
            ViewCompat.setLayerPaint(view0, ((LayoutParams)view0.getLayoutParams()).dimPaint);
            return;
        }
        if(Build.VERSION.SDK_INT >= 16) {
            if(!this.mDisplayListReflectionLoaded) {
                try {
                    this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", null);
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    Log.e("SlidingPaneLayout", "Couldn\'t fetch getDisplayList method; dimming won\'t work right.", noSuchMethodException0);
                }
                try {
                    this.mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
                    this.mRecreateDisplayList.setAccessible(true);
                }
                catch(NoSuchFieldException noSuchFieldException0) {
                    Log.e("SlidingPaneLayout", "Couldn\'t fetch mRecreateDisplayList field; dimming will be slow.", noSuchFieldException0);
                }
                this.mDisplayListReflectionLoaded = true;
            }
            if(this.mGetDisplayList != null) {
                Field field0 = this.mRecreateDisplayList;
                if(field0 != null) {
                    try {
                        field0.setBoolean(view0, true);
                        this.mGetDisplayList.invoke(view0, null);
                    }
                    catch(Exception exception0) {
                        Log.e("SlidingPaneLayout", "Error refreshing display list state", exception0);
                    }
                    ViewCompat.postInvalidateOnAnimation(this, view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom());
                    return;
                }
            }
            view0.invalidate();
            return;
        }
        ViewCompat.postInvalidateOnAnimation(this, view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom());
    }

    boolean isDimmed(View view0) {
        if(view0 == null) {
            return false;
        }
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        return this.mCanSlide && slidingPaneLayout$LayoutParams0.dimWhenOffset && this.mSlideOffset > 0.0f;
    }

    boolean isLayoutRtlSupport() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    public boolean isOpen() {
        return !this.mCanSlide || this.mSlideOffset == 1.0f;
    }

    public boolean isSlideable() {
        return this.mCanSlide;
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
        int v = this.mPostedRunnables.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((DisableLayerRunnable)this.mPostedRunnables.get(v1)).run();
        }
        this.mPostedRunnables.clear();
    }

    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        if(!this.mCanSlide && v == 0 && this.getChildCount() > 1) {
            View view0 = this.getChildAt(1);
            if(view0 != null) {
                int v1 = (int)motionEvent0.getX();
                int v2 = (int)motionEvent0.getY();
                this.mPreservedOpenState = !this.mDragHelper.isViewUnder(view0, v1, v2);
            }
        }
        if(this.mCanSlide && (!this.mIsUnableToDrag || v == 0)) {
            if(v != 1 && v != 3) {
                switch(v) {
                    case 0: {
                        this.mIsUnableToDrag = false;
                        float f = motionEvent0.getX();
                        float f1 = motionEvent0.getY();
                        this.mInitialMotionX = f;
                        this.mInitialMotionY = f1;
                        return !this.mDragHelper.isViewUnder(this.mSlideableView, ((int)f), ((int)f1)) || !this.isDimmed(this.mSlideableView) ? this.mDragHelper.shouldInterceptTouchEvent(motionEvent0) : this.mDragHelper.shouldInterceptTouchEvent(motionEvent0) || true;
                    }
                    case 2: {
                        float f2 = motionEvent0.getX();
                        float f3 = motionEvent0.getY();
                        float f4 = Math.abs(f2 - this.mInitialMotionX);
                        if(f4 > ((float)this.mDragHelper.getTouchSlop()) && Math.abs(f3 - this.mInitialMotionY) > f4) {
                            this.mDragHelper.cancel();
                            this.mIsUnableToDrag = true;
                            return false;
                        }
                        return this.mDragHelper.shouldInterceptTouchEvent(motionEvent0);
                    }
                    default: {
                        return this.mDragHelper.shouldInterceptTouchEvent(motionEvent0);
                    }
                }
            }
            this.mDragHelper.cancel();
            return false;
        }
        this.mDragHelper.cancel();
        return super.onInterceptTouchEvent(motionEvent0);
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v21;
        int v20;
        int v18;
        int v17;
        boolean z1 = this.isLayoutRtlSupport();
        if(z1) {
            this.mDragHelper.setEdgeTrackingEnabled(2);
        }
        else {
            this.mDragHelper.setEdgeTrackingEnabled(1);
        }
        int v4 = v2 - v;
        int v5 = z1 ? this.getPaddingRight() : this.getPaddingLeft();
        int v6 = z1 ? this.getPaddingLeft() : this.getPaddingRight();
        int v7 = this.getPaddingTop();
        int v8 = this.getChildCount();
        if(this.mFirstLayout) {
            this.mSlideOffset = !this.mCanSlide || !this.mPreservedOpenState ? 0.0f : 1.0f;
        }
        int v9 = v5;
        int v10 = v9;
        int v11 = 0;
        while(v11 < v8) {
            View view0 = this.getChildAt(v11);
            if(view0.getVisibility() != 8) {
                LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                int v12 = view0.getMeasuredWidth();
                if(slidingPaneLayout$LayoutParams0.slideable) {
                    int v13 = v4 - v6;
                    int v14 = Math.min(v9, v13 - this.mOverhangSize) - v10 - (slidingPaneLayout$LayoutParams0.leftMargin + slidingPaneLayout$LayoutParams0.rightMargin);
                    this.mSlideRange = v14;
                    int v15 = z1 ? slidingPaneLayout$LayoutParams0.rightMargin : slidingPaneLayout$LayoutParams0.leftMargin;
                    slidingPaneLayout$LayoutParams0.dimWhenOffset = v10 + v15 + v14 + v12 / 2 > v13;
                    int v16 = (int)(((float)v14) * this.mSlideOffset);
                    v17 = v15 + v16 + v10;
                    this.mSlideOffset = ((float)v16) / ((float)this.mSlideRange);
                }
                else {
                    if(this.mCanSlide) {
                        int v19 = this.mParallaxBy;
                        if(v19 != 0) {
                            v18 = (int)((1.0f - this.mSlideOffset) * ((float)v19));
                            v17 = v9;
                            goto label_39;
                        }
                    }
                    v17 = v9;
                }
                v18 = 0;
            label_39:
                if(z1) {
                    v20 = v4 - v17 + v18;
                    v21 = v20 - v12;
                }
                else {
                    v21 = v17 - v18;
                    v20 = v21 + v12;
                }
                view0.layout(v21, v7, v20, view0.getMeasuredHeight() + v7);
                v9 += view0.getWidth();
                v10 = v17;
            }
            ++v11;
        }
        if(this.mFirstLayout) {
            if(this.mCanSlide) {
                if(this.mParallaxBy != 0) {
                    this.parallaxOtherViews(this.mSlideOffset);
                }
                if(((LayoutParams)this.mSlideableView.getLayoutParams()).dimWhenOffset) {
                    this.dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
                }
            }
            else {
                for(int v22 = 0; v22 < v8; ++v22) {
                    this.dimChildView(this.getChildAt(v22), 0.0f, this.mSliderFadeColor);
                }
            }
            this.updateObscuredViewsVisibility(this.mSlideableView);
        }
        this.mFirstLayout = false;
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v23;
        int v22;
        int v20;
        int v15;
        int v14;
        int v7;
        int v6;
        int v2 = View.MeasureSpec.getMode(v);
        int v3 = View.MeasureSpec.getSize(v);
        int v4 = View.MeasureSpec.getMode(v1);
        int v5 = View.MeasureSpec.getSize(v1);
        if(v2 != 0x40000000) {
            if(!this.isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            }
            if(v2 != 0x80000000 && v2 == 0) {
                v3 = 300;
            }
        }
        else if(v4 == 0) {
            if(!this.isInEditMode()) {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            }
            v4 = 0x80000000;
            v5 = 300;
        }
        switch(v4) {
            case 0x80000000: {
                v7 = v5 - this.getPaddingTop() - this.getPaddingBottom();
                v6 = 0;
                break;
            }
            case 0x40000000: {
                v6 = v5 - this.getPaddingTop() - this.getPaddingBottom();
                v7 = v6;
                break;
            }
            default: {
                v6 = 0;
                v7 = 0;
            }
        }
        int v8 = v3 - this.getPaddingLeft() - this.getPaddingRight();
        int v9 = this.getChildCount();
        if(v9 > 2) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        this.mSlideableView = null;
        int v10 = v6;
        int v11 = v8;
        int v12 = 0;
        boolean z = false;
        float f = 0.0f;
        while(v12 < v9) {
            View view0 = this.getChildAt(v12);
            LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(view0.getVisibility() == 8) {
                slidingPaneLayout$LayoutParams0.dimWhenOffset = false;
            }
            else if(slidingPaneLayout$LayoutParams0.weight > 0.0f) {
                f += slidingPaneLayout$LayoutParams0.weight;
                if(slidingPaneLayout$LayoutParams0.width != 0) {
                    goto label_44;
                }
            }
            else {
            label_44:
                int v13 = slidingPaneLayout$LayoutParams0.leftMargin + slidingPaneLayout$LayoutParams0.rightMargin;
                if(slidingPaneLayout$LayoutParams0.width == -2) {
                    v14 = View.MeasureSpec.makeMeasureSpec(v8 - v13, 0x80000000);
                }
                else {
                    v14 = slidingPaneLayout$LayoutParams0.width == -1 ? View.MeasureSpec.makeMeasureSpec(v8 - v13, 0x40000000) : View.MeasureSpec.makeMeasureSpec(slidingPaneLayout$LayoutParams0.width, 0x40000000);
                }
                switch(slidingPaneLayout$LayoutParams0.height) {
                    case -2: {
                        v15 = View.MeasureSpec.makeMeasureSpec(v7, 0x80000000);
                        break;
                    }
                    case -1: {
                        v15 = View.MeasureSpec.makeMeasureSpec(v7, 0x40000000);
                        break;
                    }
                    default: {
                        v15 = View.MeasureSpec.makeMeasureSpec(slidingPaneLayout$LayoutParams0.height, 0x40000000);
                    }
                }
                view0.measure(v14, v15);
                int v16 = view0.getMeasuredWidth();
                int v17 = view0.getMeasuredHeight();
                if(v4 == 0x80000000 && v17 > v10) {
                    v10 = Math.min(v17, v7);
                }
                v11 -= v16;
                slidingPaneLayout$LayoutParams0.slideable = v11 < 0;
                if(slidingPaneLayout$LayoutParams0.slideable) {
                    this.mSlideableView = view0;
                }
                z |= v11 < 0;
            }
            ++v12;
        }
        if(z || f > 0.0f) {
            int v18 = v8 - this.mOverhangSize;
            int v19 = 0;
            while(v19 < v9) {
                View view1 = this.getChildAt(v19);
                if(view1.getVisibility() == 8) {
                    v20 = v18;
                }
                else {
                    LayoutParams slidingPaneLayout$LayoutParams1 = (LayoutParams)view1.getLayoutParams();
                    if(view1.getVisibility() == 8) {
                        v20 = v18;
                    }
                    else {
                        boolean z1 = slidingPaneLayout$LayoutParams1.width == 0 && slidingPaneLayout$LayoutParams1.weight > 0.0f;
                        int v21 = z1 ? 0 : view1.getMeasuredWidth();
                        if(z && view1 != this.mSlideableView) {
                            if(slidingPaneLayout$LayoutParams1.width < 0 && (v21 > v18 || slidingPaneLayout$LayoutParams1.weight > 0.0f)) {
                                if(z1) {
                                    switch(slidingPaneLayout$LayoutParams1.height) {
                                        case -2: {
                                            v22 = View.MeasureSpec.makeMeasureSpec(v7, 0x80000000);
                                            break;
                                        }
                                        case -1: {
                                            v22 = View.MeasureSpec.makeMeasureSpec(v7, 0x40000000);
                                            break;
                                        }
                                        default: {
                                            v22 = View.MeasureSpec.makeMeasureSpec(slidingPaneLayout$LayoutParams1.height, 0x40000000);
                                        }
                                    }
                                }
                                else {
                                    v22 = View.MeasureSpec.makeMeasureSpec(view1.getMeasuredHeight(), 0x40000000);
                                }
                                view1.measure(View.MeasureSpec.makeMeasureSpec(v18, 0x40000000), v22);
                            }
                            v20 = v18;
                        }
                        else if(slidingPaneLayout$LayoutParams1.weight > 0.0f) {
                            if(slidingPaneLayout$LayoutParams1.width == 0) {
                                switch(slidingPaneLayout$LayoutParams1.height) {
                                    case -2: {
                                        v23 = View.MeasureSpec.makeMeasureSpec(v7, 0x80000000);
                                        break;
                                    }
                                    case -1: {
                                        v23 = View.MeasureSpec.makeMeasureSpec(v7, 0x40000000);
                                        break;
                                    }
                                    default: {
                                        v23 = View.MeasureSpec.makeMeasureSpec(slidingPaneLayout$LayoutParams1.height, 0x40000000);
                                    }
                                }
                            }
                            else {
                                v23 = View.MeasureSpec.makeMeasureSpec(view1.getMeasuredHeight(), 0x40000000);
                            }
                            if(z) {
                                int v24 = v8 - (slidingPaneLayout$LayoutParams1.leftMargin + slidingPaneLayout$LayoutParams1.rightMargin);
                                v20 = v18;
                                int v25 = View.MeasureSpec.makeMeasureSpec(v24, 0x40000000);
                                if(v21 != v24) {
                                    view1.measure(v25, v23);
                                }
                            }
                            else {
                                v20 = v18;
                                view1.measure(View.MeasureSpec.makeMeasureSpec(v21 + ((int)(slidingPaneLayout$LayoutParams1.weight * ((float)Math.max(0, v11)) / f)), 0x40000000), v23);
                            }
                        }
                        else {
                            v20 = v18;
                        }
                    }
                }
                ++v19;
                v18 = v20;
            }
        }
        this.setMeasuredDimension(v3, v10 + this.getPaddingTop() + this.getPaddingBottom());
        this.mCanSlide = z;
        if(this.mDragHelper.getViewDragState() != 0 && !z) {
            this.mDragHelper.abort();
        }
    }

    void onPanelDragged(int v) {
        if(this.mSlideableView == null) {
            this.mSlideOffset = 0.0f;
            return;
        }
        boolean z = this.isLayoutRtlSupport();
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)this.mSlideableView.getLayoutParams();
        int v1 = this.mSlideableView.getWidth();
        if(z) {
            v = this.getWidth() - v - v1;
        }
        this.mSlideOffset = ((float)(v - ((z ? this.getPaddingRight() : this.getPaddingLeft()) + (z ? slidingPaneLayout$LayoutParams0.rightMargin : slidingPaneLayout$LayoutParams0.leftMargin)))) / ((float)this.mSlideRange);
        if(this.mParallaxBy != 0) {
            this.parallaxOtherViews(this.mSlideOffset);
        }
        if(slidingPaneLayout$LayoutParams0.dimWhenOffset) {
            this.dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
        }
        this.dispatchOnPanelSlide(this.mSlideableView);
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        if(((SavedState)parcelable0).isOpen) {
            this.openPane();
        }
        else {
            this.closePane();
        }
        this.mPreservedOpenState = ((SavedState)parcelable0).isOpen;
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.isOpen = this.isSlideable() ? this.isOpen() : this.mPreservedOpenState;
        return parcelable0;
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        if(v != v2) {
            this.mFirstLayout = true;
        }
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        if(!this.mCanSlide) {
            return super.onTouchEvent(motionEvent0);
        }
        this.mDragHelper.processTouchEvent(motionEvent0);
        switch(motionEvent0.getActionMasked()) {
            case 0: {
                float f = motionEvent0.getX();
                float f1 = motionEvent0.getY();
                this.mInitialMotionX = f;
                this.mInitialMotionY = f1;
                return true;
            }
            case 1: {
                if(this.isDimmed(this.mSlideableView)) {
                    float f2 = motionEvent0.getX();
                    float f3 = motionEvent0.getY();
                    float f4 = f2 - this.mInitialMotionX;
                    float f5 = f3 - this.mInitialMotionY;
                    int v = this.mDragHelper.getTouchSlop();
                    if(f4 * f4 + f5 * f5 < ((float)(v * v)) && this.mDragHelper.isViewUnder(this.mSlideableView, ((int)f2), ((int)f3))) {
                        this.closePane(this.mSlideableView, 0);
                        return true;
                    }
                }
                return true;
            }
            default: {
                return true;
            }
        }
    }

    private boolean openPane(View view0, int v) {
        if(!this.mFirstLayout && !this.smoothSlideTo(1.0f, v)) {
            return false;
        }
        this.mPreservedOpenState = true;
        return true;
    }

    public boolean openPane() {
        return this.openPane(this.mSlideableView, 0);
    }

    private void parallaxOtherViews(float f) {
        boolean z = this.isLayoutRtlSupport();
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)this.mSlideableView.getLayoutParams();
        boolean z1 = slidingPaneLayout$LayoutParams0.dimWhenOffset && (z ? slidingPaneLayout$LayoutParams0.rightMargin : slidingPaneLayout$LayoutParams0.leftMargin) <= 0;
        int v1 = this.getChildCount();
        for(int v = 0; v < v1; ++v) {
            View view0 = this.getChildAt(v);
            if(view0 != this.mSlideableView) {
                int v2 = (int)((1.0f - this.mParallaxOffset) * ((float)this.mParallaxBy));
                this.mParallaxOffset = f;
                view0.offsetLeftAndRight((z ? -(v2 - ((int)((1.0f - f) * ((float)this.mParallaxBy)))) : v2 - ((int)((1.0f - f) * ((float)this.mParallaxBy)))));
                if(z1) {
                    this.dimChildView(view0, (z ? this.mParallaxOffset - 1.0f : 1.0f - this.mParallaxOffset), this.mCoveredFadeColor);
                }
            }
        }
    }

    @Override  // android.view.ViewGroup
    public void requestChildFocus(View view0, View view1) {
        super.requestChildFocus(view0, view1);
        if(!this.isInTouchMode() && !this.mCanSlide) {
            this.mPreservedOpenState = view0 == this.mSlideableView;
        }
    }

    void setAllChildrenVisible() {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            if(view0.getVisibility() == 4) {
                view0.setVisibility(0);
            }
        }
    }

    public void setCoveredFadeColor(@ColorInt int v) {
        this.mCoveredFadeColor = v;
    }

    public void setPanelSlideListener(@Nullable PanelSlideListener slidingPaneLayout$PanelSlideListener0) {
        this.mPanelSlideListener = slidingPaneLayout$PanelSlideListener0;
    }

    public void setParallaxDistance(@Px int v) {
        this.mParallaxBy = v;
        this.requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable0) {
        this.setShadowDrawableLeft(drawable0);
    }

    public void setShadowDrawableLeft(@Nullable Drawable drawable0) {
        this.mShadowDrawableLeft = drawable0;
    }

    public void setShadowDrawableRight(@Nullable Drawable drawable0) {
        this.mShadowDrawableRight = drawable0;
    }

    @Deprecated
    public void setShadowResource(@DrawableRes int v) {
        this.setShadowDrawable(this.getResources().getDrawable(v));
    }

    public void setShadowResourceLeft(int v) {
        this.setShadowDrawableLeft(ContextCompat.getDrawable(this.getContext(), v));
    }

    public void setShadowResourceRight(int v) {
        this.setShadowDrawableRight(ContextCompat.getDrawable(this.getContext(), v));
    }

    public void setSliderFadeColor(@ColorInt int v) {
        this.mSliderFadeColor = v;
    }

    @Deprecated
    public void smoothSlideClosed() {
        this.closePane();
    }

    @Deprecated
    public void smoothSlideOpen() {
        this.openPane();
    }

    boolean smoothSlideTo(float f, int v) {
        int v3;
        if(!this.mCanSlide) {
            return false;
        }
        boolean z = this.isLayoutRtlSupport();
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)this.mSlideableView.getLayoutParams();
        if(z) {
            int v1 = this.getPaddingRight() + slidingPaneLayout$LayoutParams0.rightMargin;
            int v2 = this.mSlideableView.getWidth();
            v3 = (int)(((float)this.getWidth()) - (((float)v1) + f * ((float)this.mSlideRange) + ((float)v2)));
        }
        else {
            v3 = (int)(((float)(this.getPaddingLeft() + slidingPaneLayout$LayoutParams0.leftMargin)) + f * ((float)this.mSlideRange));
        }
        View view0 = this.mSlideableView;
        int v4 = view0.getTop();
        if(this.mDragHelper.smoothSlideViewTo(view0, v3, v4)) {
            this.setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }

    void updateObscuredViewsVisibility(View view0) {
        int v8;
        int v7;
        int v6;
        int v5;
        boolean z = this.isLayoutRtlSupport();
        int v = z ? this.getWidth() - this.getPaddingRight() : this.getPaddingLeft();
        int v1 = z ? this.getPaddingLeft() : this.getWidth() - this.getPaddingRight();
        int v2 = this.getPaddingTop();
        int v3 = this.getHeight();
        int v4 = this.getPaddingBottom();
        if(view0 == null || !SlidingPaneLayout.viewIsOpaque(view0)) {
            v5 = 0;
            v6 = 0;
            v7 = 0;
            v8 = 0;
        }
        else {
            v5 = view0.getLeft();
            v6 = view0.getRight();
            v7 = view0.getTop();
            v8 = view0.getBottom();
        }
        int v9 = this.getChildCount();
        for(int v10 = 0; v10 < v9; ++v10) {
            View view1 = this.getChildAt(v10);
            if(view1 == view0) {
                break;
            }
            if(view1.getVisibility() != 8) {
                view1.setVisibility((Math.max((z ? v1 : v), view1.getLeft()) < v5 || Math.max(v2, view1.getTop()) < v7 || Math.min((z ? v : v1), view1.getRight()) > v6 || Math.min(v3 - v4, view1.getBottom()) > v8 ? 0 : 4));
            }
        }
    }

    private static boolean viewIsOpaque(View view0) {
        if(view0.isOpaque()) {
            return true;
        }
        if(Build.VERSION.SDK_INT >= 18) {
            return false;
        }
        Drawable drawable0 = view0.getBackground();
        return drawable0 != null && drawable0.getOpacity() == -1;
    }
}

