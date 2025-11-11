package androidx.viewpager.widget;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.annotation.CallSuper;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup {
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface DecorView {
    }

    static class ItemInfo {
        Object object;
        float offset;
        int position;
        boolean scrolling;
        float widthFactor;

    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor;

        public LayoutParams() {
            super(-1, -1);
            this.widthFactor = 0.0f;
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.widthFactor = 0.0f;
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, ViewPager.LAYOUT_ATTRS);
            this.gravity = typedArray0.getInteger(0, 0x30);
            typedArray0.recycle();
        }
    }

    class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        private boolean canScroll() {
            return ViewPager.this.mAdapter != null && ViewPager.this.mAdapter.getCount() > 1;
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            super.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
            accessibilityEvent0.setClassName("androidx.viewpager.widget.ViewPager");
            accessibilityEvent0.setScrollable(this.canScroll());
            if(accessibilityEvent0.getEventType() == 0x1000 && ViewPager.this.mAdapter != null) {
                accessibilityEvent0.setItemCount(ViewPager.this.mAdapter.getCount());
                accessibilityEvent0.setFromIndex(ViewPager.this.mCurItem);
                accessibilityEvent0.setToIndex(ViewPager.this.mCurItem);
            }
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
            accessibilityNodeInfoCompat0.setClassName("androidx.viewpager.widget.ViewPager");
            accessibilityNodeInfoCompat0.setScrollable(this.canScroll());
            if(ViewPager.this.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat0.addAction(0x1000);
            }
            if(ViewPager.this.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat0.addAction(0x2000);
            }
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
            if(super.performAccessibilityAction(view0, v, bundle0)) {
                return true;
            }
            switch(v) {
                case 0x1000: {
                    if(ViewPager.this.canScrollHorizontally(1)) {
                        ViewPager.this.setCurrentItem(ViewPager.this.mCurItem + 1);
                        return true;
                    }
                    return false;
                }
                case 0x2000: {
                    if(ViewPager.this.canScrollHorizontally(-1)) {
                        ViewPager.this.setCurrentItem(ViewPager.this.mCurItem - 1);
                        return true;
                    }
                    return false;
                }
                default: {
                    return false;
                }
            }
        }
    }

    public interface OnAdapterChangeListener {
        void onAdapterChanged(@NonNull ViewPager arg1, @Nullable PagerAdapter arg2, @Nullable PagerAdapter arg3);
    }

    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int arg1);

        void onPageScrolled(int arg1, float arg2, @Px int arg3);

        void onPageSelected(int arg1);
    }

    public interface PageTransformer {
        void transformPage(@NonNull View arg1, float arg2);
    }

    class PagerObserver extends DataSetObserver {
        @Override  // android.database.DataSetObserver
        public void onChanged() {
            ViewPager.this.dataSetChanged();
        }

        @Override  // android.database.DataSetObserver
        public void onInvalidated() {
            ViewPager.this.dataSetChanged();
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        Parcelable adapterState;
        ClassLoader loader;
        int position;

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

        SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            if(classLoader0 == null) {
                classLoader0 = this.getClass().getClassLoader();
            }
            this.position = parcel0.readInt();
            this.adapterState = parcel0.readParcelable(classLoader0);
            this.loader = classLoader0;
        }

        public SavedState(@NonNull Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override
        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.position + "}";
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.position);
            parcel0.writeParcelable(this.adapterState, v);
        }
    }

    public static class SimpleOnPageChangeListener implements OnPageChangeListener {
        @Override  // androidx.viewpager.widget.ViewPager$OnPageChangeListener
        public void onPageScrollStateChanged(int v) {
        }

        @Override  // androidx.viewpager.widget.ViewPager$OnPageChangeListener
        public void onPageScrolled(int v, float f, int v1) {
        }

        @Override  // androidx.viewpager.widget.ViewPager$OnPageChangeListener
        public void onPageSelected(int v) {
        }
    }

    static class ViewPositionComparator implements Comparator {
        public int compare(View view0, View view1) {
            LayoutParams viewPager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            LayoutParams viewPager$LayoutParams1 = (LayoutParams)view1.getLayoutParams();
            if(viewPager$LayoutParams0.isDecor != viewPager$LayoutParams1.isDecor) {
                return viewPager$LayoutParams0.isDecor ? 1 : -1;
            }
            return viewPager$LayoutParams0.position - viewPager$LayoutParams1.position;
        }

        @Override
        public int compare(Object object0, Object object1) {
            return this.compare(((View)object0), ((View)object1));
        }
    }

    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator COMPARATOR = null;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    static final int[] LAYOUT_ATTRS = null;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private int mActivePointerId;
    PagerAdapter mAdapter;
    private List mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private OnPageChangeListener mOnPageChangeListener;
    private List mOnPageChangeListeners;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private int mPageTransformerLayerType;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffect mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private static final Interpolator sInterpolator;
    private static final ViewPositionComparator sPositionComparator;

    static {
        ViewPager.LAYOUT_ATTRS = new int[]{0x10100B3};
        ViewPager.COMPARATOR = new Comparator() {
            public int compare(ItemInfo viewPager$ItemInfo0, ItemInfo viewPager$ItemInfo1) {
                return viewPager$ItemInfo0.position - viewPager$ItemInfo1.position;
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((ItemInfo)object0), ((ItemInfo)object1));
            }
        };
        ViewPager.sInterpolator = new Interpolator() {
            @Override  // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return (f - 1.0f) * (f - 1.0f) * (f - 1.0f) * (f - 1.0f) * (f - 1.0f) + 1.0f;
            }
        };
        ViewPager.sPositionComparator = new ViewPositionComparator();
    }

    public ViewPager(@NonNull Context context0) {
        super(context0);
        this.mItems = new ArrayList();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.402823E+38f;
        this.mLastOffset = 3.402823E+38f;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() {
            @Override
            public void run() {
                ViewPager.this.setScrollState(0);
                ViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        this.initViewPager();
    }

    public ViewPager(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mItems = new ArrayList();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.402823E+38f;
        this.mLastOffset = 3.402823E+38f;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() {
            @Override
            public void run() {
                ViewPager.this.setScrollState(0);
                ViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        this.initViewPager();
    }

    @Override  // android.view.ViewGroup
    public void addFocusables(ArrayList arrayList0, int v, int v1) {
        int v2 = arrayList0.size();
        int v3 = this.getDescendantFocusability();
        if(v3 != 0x60000) {
            for(int v4 = 0; v4 < this.getChildCount(); ++v4) {
                View view0 = this.getChildAt(v4);
                if(view0.getVisibility() == 0) {
                    ItemInfo viewPager$ItemInfo0 = this.infoForChild(view0);
                    if(viewPager$ItemInfo0 != null && viewPager$ItemInfo0.position == this.mCurItem) {
                        view0.addFocusables(arrayList0, v, v1);
                    }
                }
            }
        }
        if(v3 == 0x40000 && v2 != arrayList0.size() || !this.isFocusable()) {
            return;
        }
        if((v1 & 1) == 1 && this.isInTouchMode() && !this.isFocusableInTouchMode()) {
            return;
        }
        arrayList0.add(this);
    }

    ItemInfo addNewItem(int v, int v1) {
        ItemInfo viewPager$ItemInfo0 = new ItemInfo();
        viewPager$ItemInfo0.position = v;
        viewPager$ItemInfo0.object = this.mAdapter.instantiateItem(this, v);
        viewPager$ItemInfo0.widthFactor = 1.0f;
        if(v1 >= 0 && v1 < this.mItems.size()) {
            this.mItems.add(v1, viewPager$ItemInfo0);
            return viewPager$ItemInfo0;
        }
        this.mItems.add(viewPager$ItemInfo0);
        return viewPager$ItemInfo0;
    }

    public void addOnAdapterChangeListener(@NonNull OnAdapterChangeListener viewPager$OnAdapterChangeListener0) {
        if(this.mAdapterChangeListeners == null) {
            this.mAdapterChangeListeners = new ArrayList();
        }
        this.mAdapterChangeListeners.add(viewPager$OnAdapterChangeListener0);
    }

    public void addOnPageChangeListener(@NonNull OnPageChangeListener viewPager$OnPageChangeListener0) {
        if(this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(viewPager$OnPageChangeListener0);
    }

    @Override  // android.view.ViewGroup
    public void addTouchables(ArrayList arrayList0) {
        for(int v = 0; v < this.getChildCount(); ++v) {
            View view0 = this.getChildAt(v);
            if(view0.getVisibility() == 0) {
                ItemInfo viewPager$ItemInfo0 = this.infoForChild(view0);
                if(viewPager$ItemInfo0 != null && viewPager$ItemInfo0.position == this.mCurItem) {
                    view0.addTouchables(arrayList0);
                }
            }
        }
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(!this.checkLayoutParams(viewGroup$LayoutParams0)) {
            viewGroup$LayoutParams0 = this.generateLayoutParams(viewGroup$LayoutParams0);
        }
        ((LayoutParams)viewGroup$LayoutParams0).isDecor |= ViewPager.isDecorView(view0);
        if(this.mInLayout) {
            if(((LayoutParams)viewGroup$LayoutParams0) != null && ((LayoutParams)viewGroup$LayoutParams0).isDecor) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            ((LayoutParams)viewGroup$LayoutParams0).needsMeasure = true;
            this.addViewInLayout(view0, v, viewGroup$LayoutParams0);
            return;
        }
        super.addView(view0, v, viewGroup$LayoutParams0);
    }

    public boolean arrowScroll(int v) {
        View view0 = this.findFocus();
        boolean z = false;
        View view1 = null;
        if(view0 != this && view0 != null) {
            for(ViewParent viewParent0 = view0.getParent(); true; viewParent0 = viewParent0.getParent()) {
                boolean z1 = false;
                if(!(viewParent0 instanceof ViewGroup)) {
                    break;
                }
                if(viewParent0 == this) {
                    z1 = true;
                    break;
                }
            }
            if(z1) {
                view1 = view0;
            }
            else {
                StringBuilder stringBuilder0 = new StringBuilder();
                stringBuilder0.append(view0.getClass().getSimpleName());
                for(ViewParent viewParent1 = view0.getParent(); viewParent1 instanceof ViewGroup; viewParent1 = viewParent1.getParent()) {
                    stringBuilder0.append(" => ");
                    stringBuilder0.append(viewParent1.getClass().getSimpleName());
                }
                Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + stringBuilder0.toString());
            }
        }
        View view2 = FocusFinder.getInstance().findNextFocus(this, view1, v);
        if(view2 == null || view2 == view1) {
            if(v == 1 || v == 17) {
                z = this.pageLeft();
            }
            else if(v == 2 || v == 66) {
                z = this.pageRight();
            }
        }
        else if(v != 17) {
            if(v == 66) {
                z = view1 == null || this.getChildRectInPagerCoordinates(this.mTempRect, view2).left > this.getChildRectInPagerCoordinates(this.mTempRect, view1).left ? view2.requestFocus() : this.pageRight();
            }
        }
        else if(view1 != null && this.getChildRectInPagerCoordinates(this.mTempRect, view2).left >= this.getChildRectInPagerCoordinates(this.mTempRect, view1).left) {
            z = this.pageLeft();
        }
        else {
            z = view2.requestFocus();
        }
        if(z) {
            this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(v));
        }
        return z;
    }

    public boolean beginFakeDrag() {
        if(this.mIsBeingDragged) {
            return false;
        }
        this.mFakeDragging = true;
        this.setScrollState(1);
        this.mLastMotionX = 0.0f;
        this.mInitialMotionX = 0.0f;
        VelocityTracker velocityTracker0 = this.mVelocityTracker;
        if(velocityTracker0 == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        else {
            velocityTracker0.clear();
        }
        long v = SystemClock.uptimeMillis();
        MotionEvent motionEvent0 = MotionEvent.obtain(v, v, 0, 0.0f, 0.0f, 0);
        this.mVelocityTracker.addMovement(motionEvent0);
        motionEvent0.recycle();
        this.mFakeDragBeginTime = v;
        return true;
    }

    private void calculatePageOffsets(ItemInfo viewPager$ItemInfo0, int v, ItemInfo viewPager$ItemInfo1) {
        int v1 = this.mAdapter.getCount();
        int v2 = this.getClientWidth();
        float f = v2 <= 0 ? 0.0f : ((float)this.mPageMargin) / ((float)v2);
        if(viewPager$ItemInfo1 != null) {
            int v3 = viewPager$ItemInfo1.position;
            if(v3 < viewPager$ItemInfo0.position) {
                float f1 = viewPager$ItemInfo1.offset + viewPager$ItemInfo1.widthFactor + f;
                int v4 = v3 + 1;
                int v5 = 0;
                while(v4 <= viewPager$ItemInfo0.position && v5 < this.mItems.size()) {
                    ItemInfo viewPager$ItemInfo2;
                    for(viewPager$ItemInfo2 = (ItemInfo)this.mItems.get(v5); v4 > viewPager$ItemInfo2.position && v5 < this.mItems.size() - 1; viewPager$ItemInfo2 = (ItemInfo)this.mItems.get(v5)) {
                        ++v5;
                    }
                    while(v4 < viewPager$ItemInfo2.position) {
                        f1 += f + 1.0f;
                        ++v4;
                    }
                    viewPager$ItemInfo2.offset = f1;
                    f1 += viewPager$ItemInfo2.widthFactor + f;
                    ++v4;
                }
            }
            else if(v3 > viewPager$ItemInfo0.position) {
                int v6 = this.mItems.size() - 1;
                float f2 = viewPager$ItemInfo1.offset;
                for(int v7 = v3 - 1; v7 >= viewPager$ItemInfo0.position && v6 >= 0; --v7) {
                    ItemInfo viewPager$ItemInfo3;
                    for(viewPager$ItemInfo3 = (ItemInfo)this.mItems.get(v6); v7 < viewPager$ItemInfo3.position && v6 > 0; viewPager$ItemInfo3 = (ItemInfo)this.mItems.get(v6)) {
                        --v6;
                    }
                    while(v7 > viewPager$ItemInfo3.position) {
                        f2 -= f + 1.0f;
                        --v7;
                    }
                    f2 -= viewPager$ItemInfo3.widthFactor + f;
                    viewPager$ItemInfo3.offset = f2;
                }
            }
        }
        int v8 = this.mItems.size();
        float f3 = viewPager$ItemInfo0.offset;
        int v9 = viewPager$ItemInfo0.position - 1;
        this.mFirstOffset = viewPager$ItemInfo0.position == 0 ? viewPager$ItemInfo0.offset : -3.402823E+38f;
        this.mLastOffset = viewPager$ItemInfo0.position == v1 - 1 ? viewPager$ItemInfo0.offset + viewPager$ItemInfo0.widthFactor - 1.0f : 3.402823E+38f;
        int v10 = v - 1;
        while(v10 >= 0) {
            ItemInfo viewPager$ItemInfo4 = (ItemInfo)this.mItems.get(v10);
            while(v9 > viewPager$ItemInfo4.position) {
                f3 -= f + 1.0f;
                --v9;
            }
            f3 -= viewPager$ItemInfo4.widthFactor + f;
            viewPager$ItemInfo4.offset = f3;
            if(viewPager$ItemInfo4.position == 0) {
                this.mFirstOffset = f3;
            }
            --v10;
            --v9;
        }
        float f4 = viewPager$ItemInfo0.offset + viewPager$ItemInfo0.widthFactor + f;
        int v11 = viewPager$ItemInfo0.position + 1;
        int v12 = v + 1;
        while(v12 < v8) {
            ItemInfo viewPager$ItemInfo5 = (ItemInfo)this.mItems.get(v12);
            while(v11 < viewPager$ItemInfo5.position) {
                f4 += f + 1.0f;
                ++v11;
            }
            if(viewPager$ItemInfo5.position == v1 - 1) {
                this.mLastOffset = viewPager$ItemInfo5.widthFactor + f4 - 1.0f;
            }
            viewPager$ItemInfo5.offset = f4;
            f4 += viewPager$ItemInfo5.widthFactor + f;
            ++v12;
            ++v11;
        }
        this.mNeedCalculatePageOffsets = false;
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
        return z && view0.canScrollHorizontally(-v);
    }

    @Override  // android.view.View
    public boolean canScrollHorizontally(int v) {
        if(this.mAdapter == null) {
            return false;
        }
        int v1 = this.getClientWidth();
        int v2 = this.getScrollX();
        return v >= 0 ? v > 0 && v2 < ((int)(((float)v1) * this.mLastOffset)) : v2 > ((int)(((float)v1) * this.mFirstOffset));
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams0);
    }

    public void clearOnPageChangeListeners() {
        List list0 = this.mOnPageChangeListeners;
        if(list0 != null) {
            list0.clear();
        }
    }

    private void completeScroll(boolean z) {
        int v = this.mScrollState == 2 ? 1 : 0;
        if(v != 0) {
            this.setScrollingCacheEnabled(false);
            if(!this.mScroller.isFinished() != 0) {
                this.mScroller.abortAnimation();
                int v1 = this.getScrollX();
                int v2 = this.getScrollY();
                int v3 = this.mScroller.getCurrX();
                int v4 = this.mScroller.getCurrY();
                if(v1 != v3 || v2 != v4) {
                    this.scrollTo(v3, v4);
                    if(v3 != v1) {
                        this.pageScrolled(v3);
                    }
                }
            }
        }
        this.mPopulatePending = false;
        int v5 = v;
        for(int v6 = 0; v6 < this.mItems.size(); ++v6) {
            ItemInfo viewPager$ItemInfo0 = (ItemInfo)this.mItems.get(v6);
            if(viewPager$ItemInfo0.scrolling) {
                viewPager$ItemInfo0.scrolling = false;
                v5 = 1;
            }
        }
        if(v5 != 0) {
            if(z) {
                ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
                return;
            }
            this.mEndScrollRunnable.run();
        }
    }

    @Override  // android.view.View
    public void computeScroll() {
        this.mIsScrollStarted = true;
        if(!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            int v = this.getScrollX();
            int v1 = this.getScrollY();
            int v2 = this.mScroller.getCurrX();
            int v3 = this.mScroller.getCurrY();
            if(v != v2 || v1 != v3) {
                this.scrollTo(v2, v3);
                if(!this.pageScrolled(v2)) {
                    this.mScroller.abortAnimation();
                    this.scrollTo(0, v3);
                }
            }
            ViewCompat.postInvalidateOnAnimation(this);
            return;
        }
        this.completeScroll(true);
    }

    void dataSetChanged() {
        int v = this.mAdapter.getCount();
        this.mExpectedAdapterCount = v;
        boolean z = this.mItems.size() < this.mOffscreenPageLimit * 2 + 1 && this.mItems.size() < v;
        int v1 = this.mCurItem;
        for(int v2 = 0; v2 < this.mItems.size(); ++v2) {
            ItemInfo viewPager$ItemInfo0 = (ItemInfo)this.mItems.get(v2);
        }
        Collections.sort(this.mItems, ViewPager.COMPARATOR);
        if(z) {
            int v3 = this.getChildCount();
            for(int v4 = 0; v4 < v3; ++v4) {
                LayoutParams viewPager$LayoutParams0 = (LayoutParams)this.getChildAt(v4).getLayoutParams();
                if(!viewPager$LayoutParams0.isDecor) {
                    viewPager$LayoutParams0.widthFactor = 0.0f;
                }
            }
            this.setCurrentItemInternal(v1, false, true);
            this.requestLayout();
        }
    }

    private int determineTargetPage(int v, float f, int v1, int v2) {
        if(Math.abs(v2) <= this.mFlingDistance || Math.abs(v1) <= this.mMinimumVelocity) {
            v += (int)(f + (v < this.mCurItem ? 0.6f : 0.4f));
        }
        else if(v1 <= 0) {
            ++v;
        }
        if(this.mItems.size() > 0) {
            ItemInfo viewPager$ItemInfo0 = (ItemInfo)this.mItems.get(0);
            int v3 = Math.min(v, ((ItemInfo)this.mItems.get(this.mItems.size() - 1)).position);
            return Math.max(viewPager$ItemInfo0.position, v3);
        }
        return v;
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        return super.dispatchKeyEvent(keyEvent0) || this.executeKeyEvent(keyEvent0);
    }

    private void dispatchOnPageScrolled(int v, float f, int v1) {
        OnPageChangeListener viewPager$OnPageChangeListener0 = this.mOnPageChangeListener;
        if(viewPager$OnPageChangeListener0 != null) {
            viewPager$OnPageChangeListener0.onPageScrolled(v, f, v1);
        }
        List list0 = this.mOnPageChangeListeners;
        if(list0 != null) {
            int v3 = list0.size();
            for(int v2 = 0; v2 < v3; ++v2) {
                OnPageChangeListener viewPager$OnPageChangeListener1 = (OnPageChangeListener)this.mOnPageChangeListeners.get(v2);
                if(viewPager$OnPageChangeListener1 != null) {
                    viewPager$OnPageChangeListener1.onPageScrolled(v, f, v1);
                }
            }
        }
        OnPageChangeListener viewPager$OnPageChangeListener2 = this.mInternalPageChangeListener;
        if(viewPager$OnPageChangeListener2 != null) {
            viewPager$OnPageChangeListener2.onPageScrolled(v, f, v1);
        }
    }

    private void dispatchOnPageSelected(int v) {
        OnPageChangeListener viewPager$OnPageChangeListener0 = this.mOnPageChangeListener;
        if(viewPager$OnPageChangeListener0 != null) {
            viewPager$OnPageChangeListener0.onPageSelected(v);
        }
        List list0 = this.mOnPageChangeListeners;
        if(list0 != null) {
            int v2 = list0.size();
            for(int v1 = 0; v1 < v2; ++v1) {
                OnPageChangeListener viewPager$OnPageChangeListener1 = (OnPageChangeListener)this.mOnPageChangeListeners.get(v1);
                if(viewPager$OnPageChangeListener1 != null) {
                    viewPager$OnPageChangeListener1.onPageSelected(v);
                }
            }
        }
        OnPageChangeListener viewPager$OnPageChangeListener2 = this.mInternalPageChangeListener;
        if(viewPager$OnPageChangeListener2 != null) {
            viewPager$OnPageChangeListener2.onPageSelected(v);
        }
    }

    private void dispatchOnScrollStateChanged(int v) {
        OnPageChangeListener viewPager$OnPageChangeListener0 = this.mOnPageChangeListener;
        if(viewPager$OnPageChangeListener0 != null) {
            viewPager$OnPageChangeListener0.onPageScrollStateChanged(v);
        }
        List list0 = this.mOnPageChangeListeners;
        if(list0 != null) {
            int v2 = list0.size();
            for(int v1 = 0; v1 < v2; ++v1) {
                OnPageChangeListener viewPager$OnPageChangeListener1 = (OnPageChangeListener)this.mOnPageChangeListeners.get(v1);
                if(viewPager$OnPageChangeListener1 != null) {
                    viewPager$OnPageChangeListener1.onPageScrollStateChanged(v);
                }
            }
        }
        OnPageChangeListener viewPager$OnPageChangeListener2 = this.mInternalPageChangeListener;
        if(viewPager$OnPageChangeListener2 != null) {
            viewPager$OnPageChangeListener2.onPageScrollStateChanged(v);
        }
    }

    @Override  // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        if(accessibilityEvent0.getEventType() == 0x1000) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent0);
        }
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            if(view0.getVisibility() == 0) {
                ItemInfo viewPager$ItemInfo0 = this.infoForChild(view0);
                if(viewPager$ItemInfo0 != null && viewPager$ItemInfo0.position == this.mCurItem && view0.dispatchPopulateAccessibilityEvent(accessibilityEvent0)) {
                    return true;
                }
            }
        }
        return false;
    }

    float distanceInfluenceForSnapDuration(float f) {
        return (float)Math.sin((f - 0.5f) * 0.471239f);
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        super.draw(canvas0);
        int v = this.getOverScrollMode();
        int v1 = 0;
        if(v == 0 || v == 1 && (this.mAdapter != null && this.mAdapter.getCount() > 1)) {
            if(!this.mLeftEdge.isFinished()) {
                int v2 = canvas0.save();
                int v3 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
                int v4 = this.getWidth();
                canvas0.rotate(270.0f);
                canvas0.translate(((float)(this.getPaddingTop() - v3)), this.mFirstOffset * ((float)v4));
                this.mLeftEdge.setSize(v3, v4);
                v1 = this.mLeftEdge.draw(canvas0);
                canvas0.restoreToCount(v2);
            }
            if(!this.mRightEdge.isFinished()) {
                int v5 = canvas0.save();
                int v6 = this.getWidth();
                int v7 = this.getHeight();
                int v8 = this.getPaddingTop();
                int v9 = this.getPaddingBottom();
                canvas0.rotate(90.0f);
                canvas0.translate(((float)(-this.getPaddingTop())), -(this.mLastOffset + 1.0f) * ((float)v6));
                this.mRightEdge.setSize(v7 - v8 - v9, v6);
                v1 |= this.mRightEdge.draw(canvas0);
                canvas0.restoreToCount(v5);
            }
        }
        else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if(v1 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override  // android.view.ViewGroup
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable0 = this.mMarginDrawable;
        if(drawable0 != null && drawable0.isStateful()) {
            drawable0.setState(this.getDrawableState());
        }
    }

    private void enableLayers(boolean z) {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = z ? this.mPageTransformerLayerType : 0;
            this.getChildAt(v1).setLayerType(v2, null);
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker0 = this.mVelocityTracker;
        if(velocityTracker0 != null) {
            velocityTracker0.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void endFakeDrag() {
        if(!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if(this.mAdapter != null) {
            VelocityTracker velocityTracker0 = this.mVelocityTracker;
            velocityTracker0.computeCurrentVelocity(1000, ((float)this.mMaximumVelocity));
            int v = (int)velocityTracker0.getXVelocity(this.mActivePointerId);
            this.mPopulatePending = true;
            int v1 = this.getClientWidth();
            int v2 = this.getScrollX();
            ItemInfo viewPager$ItemInfo0 = this.infoForCurrentScrollPosition();
            this.setCurrentItemInternal(this.determineTargetPage(viewPager$ItemInfo0.position, (((float)v2) / ((float)v1) - viewPager$ItemInfo0.offset) / viewPager$ItemInfo0.widthFactor, v, ((int)(this.mLastMotionX - this.mInitialMotionX))), true, true, v);
        }
        this.endDrag();
        this.mFakeDragging = false;
    }

    public boolean executeKeyEvent(@NonNull KeyEvent keyEvent0) {
        if(keyEvent0.getAction() == 0) {
            int v = keyEvent0.getKeyCode();
            if(v != 61) {
                switch(v) {
                    case 21: {
                        return keyEvent0.hasModifiers(2) ? this.pageLeft() : this.arrowScroll(17);
                    }
                    case 22: {
                        return keyEvent0.hasModifiers(2) ? this.pageRight() : this.arrowScroll(66);
                    }
                    default: {
                        return false;
                    }
                }
            }
            if(keyEvent0.hasNoModifiers()) {
                return this.arrowScroll(2);
            }
            return keyEvent0.hasModifiers(1) ? this.arrowScroll(1) : false;
        }
        return false;
    }

    public void fakeDragBy(float f) {
        if(!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if(this.mAdapter == null) {
            return;
        }
        this.mLastMotionX += f;
        float f1 = ((float)this.getScrollX()) - f;
        float f2 = (float)this.getClientWidth();
        float f3 = this.mFirstOffset * f2;
        float f4 = this.mLastOffset * f2;
        ItemInfo viewPager$ItemInfo0 = (ItemInfo)this.mItems.get(0);
        ItemInfo viewPager$ItemInfo1 = (ItemInfo)this.mItems.get(this.mItems.size() - 1);
        if(viewPager$ItemInfo0.position != 0) {
            f3 = viewPager$ItemInfo0.offset * f2;
        }
        if(viewPager$ItemInfo1.position != this.mAdapter.getCount() - 1) {
            f4 = viewPager$ItemInfo1.offset * f2;
        }
        if(f1 < f3) {
            f1 = f3;
        }
        else if(f1 > f4) {
            f1 = f4;
        }
        this.mLastMotionX += f1 - ((float)(((int)f1)));
        this.scrollTo(((int)f1), this.getScrollY());
        this.pageScrolled(((int)f1));
        long v = SystemClock.uptimeMillis();
        MotionEvent motionEvent0 = MotionEvent.obtain(this.mFakeDragBeginTime, v, 2, this.mLastMotionX, 0.0f, 0);
        this.mVelocityTracker.addMovement(motionEvent0);
        motionEvent0.recycle();
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return this.generateDefaultLayoutParams();
    }

    @Nullable
    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override  // android.view.ViewGroup
    protected int getChildDrawingOrder(int v, int v1) {
        if(this.mDrawingOrder == 2) {
            v1 = v - 1 - v1;
        }
        return ((LayoutParams)((View)this.mDrawingOrderedChildren.get(v1)).getLayoutParams()).childIndex;
    }

    private Rect getChildRectInPagerCoordinates(Rect rect0, View view0) {
        if(rect0 == null) {
            rect0 = new Rect();
        }
        if(view0 == null) {
            rect0.set(0, 0, 0, 0);
            return rect0;
        }
        rect0.left = view0.getLeft();
        rect0.right = view0.getRight();
        rect0.top = view0.getTop();
        rect0.bottom = view0.getBottom();
        for(ViewParent viewParent0 = view0.getParent(); viewParent0 instanceof ViewGroup && viewParent0 != this; viewParent0 = ((ViewGroup)viewParent0).getParent()) {
            rect0.left += ((ViewGroup)viewParent0).getLeft();
            rect0.right += ((ViewGroup)viewParent0).getRight();
            rect0.top += ((ViewGroup)viewParent0).getTop();
            rect0.bottom += ((ViewGroup)viewParent0).getBottom();
        }
        return rect0;
    }

    private int getClientWidth() {
        return this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    ItemInfo infoForAnyChild(View view0) {
        ViewParent viewParent0;
        while((viewParent0 = view0.getParent()) != this) {
            if(viewParent0 != null && viewParent0 instanceof View) {
                view0 = (View)viewParent0;
                continue;
            }
            return null;
        }
        return this.infoForChild(view0);
    }

    ItemInfo infoForChild(View view0) {
        for(int v = 0; v < this.mItems.size(); ++v) {
            ItemInfo viewPager$ItemInfo0 = (ItemInfo)this.mItems.get(v);
            if(this.mAdapter.isViewFromObject(view0, viewPager$ItemInfo0.object)) {
                return viewPager$ItemInfo0;
            }
        }
        return null;
    }

    private ItemInfo infoForCurrentScrollPosition() {
        int v = this.getClientWidth();
        float f = v <= 0 ? 0.0f : ((float)this.getScrollX()) / ((float)v);
        float f1 = v <= 0 ? 0.0f : ((float)this.mPageMargin) / ((float)v);
        ItemInfo viewPager$ItemInfo0 = null;
        int v1 = 0;
        boolean z = true;
        int v2 = -1;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while(v1 < this.mItems.size()) {
            ItemInfo viewPager$ItemInfo1 = (ItemInfo)this.mItems.get(v1);
            if(!z && viewPager$ItemInfo1.position != v2 + 1) {
                viewPager$ItemInfo1 = this.mTempItem;
                viewPager$ItemInfo1.offset = f2 + f3 + f1;
                viewPager$ItemInfo1.position = v2 + 1;
                viewPager$ItemInfo1.widthFactor = 1.0f;
                --v1;
            }
            f2 = viewPager$ItemInfo1.offset;
            float f4 = viewPager$ItemInfo1.widthFactor + f2 + f1;
            if(!z && f < f2) {
                return viewPager$ItemInfo0;
            }
            if(f >= f4 && v1 != this.mItems.size() - 1) {
                v2 = viewPager$ItemInfo1.position;
                f3 = viewPager$ItemInfo1.widthFactor;
                ++v1;
                viewPager$ItemInfo0 = viewPager$ItemInfo1;
                z = false;
                continue;
            }
            return viewPager$ItemInfo1;
        }
        return viewPager$ItemInfo0;
    }

    ItemInfo infoForPosition(int v) {
        for(int v1 = 0; v1 < this.mItems.size(); ++v1) {
            ItemInfo viewPager$ItemInfo0 = (ItemInfo)this.mItems.get(v1);
            if(viewPager$ItemInfo0.position == v) {
                return viewPager$ItemInfo0;
            }
        }
        return null;
    }

    void initViewPager() {
        this.setWillNotDraw(false);
        this.setDescendantFocusability(0x40000);
        this.setFocusable(true);
        Context context0 = this.getContext();
        this.mScroller = new Scroller(context0, ViewPager.sInterpolator);
        ViewConfiguration viewConfiguration0 = ViewConfiguration.get(context0);
        float f = context0.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration0.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int)(400.0f * f);
        this.mMaximumVelocity = viewConfiguration0.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(context0);
        this.mRightEdge = new EdgeEffect(context0);
        this.mFlingDistance = (int)(25.0f * f);
        this.mCloseEnough = (int)(2.0f * f);
        this.mDefaultGutterSize = (int)(f * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate(this));
        if(ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {
            private final Rect mTempRect;

            {
                this.mTempRect = new Rect();
            }

            @Override  // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
                WindowInsetsCompat windowInsetsCompat1 = ViewCompat.onApplyWindowInsets(view0, windowInsetsCompat0);
                if(windowInsetsCompat1.isConsumed()) {
                    return windowInsetsCompat1;
                }
                Rect rect0 = this.mTempRect;
                rect0.left = windowInsetsCompat1.getSystemWindowInsetLeft();
                rect0.top = windowInsetsCompat1.getSystemWindowInsetTop();
                rect0.right = windowInsetsCompat1.getSystemWindowInsetRight();
                rect0.bottom = windowInsetsCompat1.getSystemWindowInsetBottom();
                int v1 = ViewPager.this.getChildCount();
                for(int v = 0; v < v1; ++v) {
                    WindowInsetsCompat windowInsetsCompat2 = ViewCompat.dispatchApplyWindowInsets(ViewPager.this.getChildAt(v), windowInsetsCompat1);
                    rect0.left = Math.min(windowInsetsCompat2.getSystemWindowInsetLeft(), rect0.left);
                    rect0.top = Math.min(windowInsetsCompat2.getSystemWindowInsetTop(), rect0.top);
                    rect0.right = Math.min(windowInsetsCompat2.getSystemWindowInsetRight(), rect0.right);
                    rect0.bottom = Math.min(windowInsetsCompat2.getSystemWindowInsetBottom(), rect0.bottom);
                }
                return windowInsetsCompat1.replaceSystemWindowInsets(rect0.left, rect0.top, rect0.right, rect0.bottom);
            }
        });
    }

    private static boolean isDecorView(@NonNull View view0) {
        return view0.getClass().getAnnotation(DecorView.class) != null;
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    private boolean isGutterDrag(float f, float f1) {
        return f < ((float)this.mGutterSize) && f1 > 0.0f || f > ((float)(this.getWidth() - this.mGutterSize)) && f1 < 0.0f;
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.mEndScrollRunnable);
        if(this.mScroller != null && !this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override  // android.view.View
    protected void onDraw(Canvas canvas0) {
        float f4;
        float f3;
        float f2;
        super.onDraw(canvas0);
        if(this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
            int v = this.getScrollX();
            int v1 = this.getWidth();
            float f = ((float)this.mPageMargin) / ((float)v1);
            int v2 = 0;
            ItemInfo viewPager$ItemInfo0 = (ItemInfo)this.mItems.get(0);
            float f1 = viewPager$ItemInfo0.offset;
            int v3 = this.mItems.size();
            int v4 = viewPager$ItemInfo0.position;
            int v5 = ((ItemInfo)this.mItems.get(v3 - 1)).position;
            while(v4 < v5) {
                while(v4 > viewPager$ItemInfo0.position && v2 < v3) {
                    ++v2;
                    viewPager$ItemInfo0 = (ItemInfo)this.mItems.get(v2);
                }
                if(v4 == viewPager$ItemInfo0.position) {
                    f2 = (viewPager$ItemInfo0.offset + viewPager$ItemInfo0.widthFactor) * ((float)v1);
                    f3 = viewPager$ItemInfo0.offset + viewPager$ItemInfo0.widthFactor + f;
                }
                else {
                    f3 = f1 + (f + 1.0f);
                    f2 = (f1 + 1.0f) * ((float)v1);
                }
                if(((float)this.mPageMargin) + f2 > ((float)v)) {
                    f4 = f;
                    this.mMarginDrawable.setBounds(Math.round(f2), this.mTopPageBounds, Math.round(((float)this.mPageMargin) + f2), this.mBottomPageBounds);
                    this.mMarginDrawable.draw(canvas0);
                }
                else {
                    f4 = f;
                }
                if(f2 > ((float)(v + v1))) {
                    break;
                }
                ++v4;
                f1 = f3;
                f = f4;
            }
        }
    }

    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getAction();
        switch(v & 0xFF) {
            case 0: {
                break;
            }
            case 1: 
            case 3: {
                this.resetTouch();
                return false;
            label_5:
                if(this.mIsBeingDragged) {
                    return true;
                }
                if(this.mIsUnableToDrag) {
                    return false;
                }
                break;
            }
            default: {
                goto label_5;
            }
        }
        switch(v & 0xFF) {
            case 0: {
                float f = motionEvent0.getX();
                this.mInitialMotionX = f;
                this.mLastMotionX = f;
                float f1 = motionEvent0.getY();
                this.mInitialMotionY = f1;
                this.mLastMotionY = f1;
                this.mActivePointerId = motionEvent0.getPointerId(0);
                this.mIsUnableToDrag = false;
                this.mIsScrollStarted = true;
                this.mScroller.computeScrollOffset();
                if(this.mScrollState != 2 || Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) <= this.mCloseEnough) {
                    this.completeScroll(false);
                    this.mIsBeingDragged = false;
                }
                else {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = false;
                    this.populate();
                    this.mIsBeingDragged = true;
                    this.requestParentDisallowInterceptTouchEvent(true);
                    this.setScrollState(1);
                }
                break;
            }
            case 2: {
                int v1 = this.mActivePointerId;
                if(v1 != -1) {
                    int v2 = motionEvent0.findPointerIndex(v1);
                    float f2 = motionEvent0.getX(v2);
                    float f3 = f2 - this.mLastMotionX;
                    float f4 = Math.abs(f3);
                    float f5 = motionEvent0.getY(v2);
                    float f6 = Math.abs(f5 - this.mInitialMotionY);
                    if(f3 != 0.0f && !this.isGutterDrag(this.mLastMotionX, f3) && this.canScroll(this, false, ((int)f3), ((int)f2), ((int)f5))) {
                        this.mLastMotionX = f2;
                        this.mLastMotionY = f5;
                        this.mIsUnableToDrag = true;
                        return false;
                    }
                    if(f4 > ((float)this.mTouchSlop) && f4 * 0.5f > f6) {
                        this.mIsBeingDragged = true;
                        this.requestParentDisallowInterceptTouchEvent(true);
                        this.setScrollState(1);
                        this.mLastMotionX = f3 > 0.0f ? this.mInitialMotionX + ((float)this.mTouchSlop) : this.mInitialMotionX - ((float)this.mTouchSlop);
                        this.mLastMotionY = f5;
                        this.setScrollingCacheEnabled(true);
                    }
                    else if(f6 > ((float)this.mTouchSlop)) {
                        this.mIsUnableToDrag = true;
                    }
                    if(this.mIsBeingDragged && this.performDrag(f2)) {
                        ViewCompat.postInvalidateOnAnimation(this);
                    }
                }
                break;
            }
            default: {
                if((v & 0xFF) == 6) {
                    this.onSecondaryPointerUp(motionEvent0);
                }
            }
        }
        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent0);
        return this.mIsBeingDragged;
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v19;
        int v18;
        int v4 = this.getChildCount();
        int v5 = v2 - v;
        int v6 = v3 - v1;
        int v7 = this.getPaddingLeft();
        int v8 = this.getPaddingTop();
        int v9 = this.getPaddingRight();
        int v10 = this.getPaddingBottom();
        int v11 = this.getScrollX();
        int v12 = v10;
        int v13 = 0;
        int v14 = v8;
        int v15 = v7;
        for(int v16 = 0; v16 < v4; ++v16) {
            View view0 = this.getChildAt(v16);
            if(view0.getVisibility() != 8) {
                LayoutParams viewPager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(viewPager$LayoutParams0.isDecor) {
                    int v17 = viewPager$LayoutParams0.gravity & 0x70;
                    switch(viewPager$LayoutParams0.gravity & 7) {
                        case 1: {
                            v18 = Math.max((v5 - view0.getMeasuredWidth()) / 2, v15);
                            break;
                        }
                        case 3: {
                            v18 = v15;
                            v15 = view0.getMeasuredWidth() + v15;
                            break;
                        }
                        case 5: {
                            v18 = v5 - v9 - view0.getMeasuredWidth();
                            v9 += view0.getMeasuredWidth();
                            break;
                        }
                        default: {
                            v18 = v15;
                        }
                    }
                    switch(v17) {
                        case 16: {
                            v19 = Math.max((v6 - view0.getMeasuredHeight()) / 2, v14);
                            break;
                        }
                        case 0x30: {
                            v19 = v14;
                            v14 = view0.getMeasuredHeight() + v14;
                            break;
                        }
                        case 80: {
                            v19 = v6 - v12 - view0.getMeasuredHeight();
                            v12 += view0.getMeasuredHeight();
                            break;
                        }
                        default: {
                            v19 = v14;
                        }
                    }
                    view0.layout(v18 + v11, v19, view0.getMeasuredWidth() + (v18 + v11), v19 + view0.getMeasuredHeight());
                    ++v13;
                }
            }
        }
        for(int v20 = 0; v20 < v4; ++v20) {
            View view1 = this.getChildAt(v20);
            if(view1.getVisibility() != 8) {
                LayoutParams viewPager$LayoutParams1 = (LayoutParams)view1.getLayoutParams();
                if(!viewPager$LayoutParams1.isDecor) {
                    ItemInfo viewPager$ItemInfo0 = this.infoForChild(view1);
                    if(viewPager$ItemInfo0 != null) {
                        float f = (float)(v5 - v15 - v9);
                        int v21 = ((int)(viewPager$ItemInfo0.offset * f)) + v15;
                        if(viewPager$LayoutParams1.needsMeasure) {
                            viewPager$LayoutParams1.needsMeasure = false;
                            view1.measure(View.MeasureSpec.makeMeasureSpec(((int)(f * viewPager$LayoutParams1.widthFactor)), 0x40000000), View.MeasureSpec.makeMeasureSpec(v6 - v14 - v12, 0x40000000));
                        }
                        view1.layout(v21, v14, view1.getMeasuredWidth() + v21, view1.getMeasuredHeight() + v14);
                    }
                }
            }
        }
        this.mTopPageBounds = v14;
        this.mBottomPageBounds = v6 - v12;
        this.mDecorChildCount = v13;
        if(this.mFirstLayout) {
            this.scrollToItem(this.mCurItem, false, 0, false);
        }
        this.mFirstLayout = false;
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v17;
        int v16;
        int v15;
        this.setMeasuredDimension(ViewPager.getDefaultSize(0, v), ViewPager.getDefaultSize(0, v1));
        int v2 = this.getMeasuredWidth();
        this.mGutterSize = Math.min(v2 / 10, this.mDefaultGutterSize);
        int v3 = this.getPaddingLeft();
        int v4 = this.getPaddingRight();
        int v5 = this.getMeasuredHeight();
        int v6 = this.getPaddingTop();
        int v7 = this.getPaddingBottom();
        int v8 = this.getChildCount();
        int v9 = v5 - v6 - v7;
        int v10 = v2 - v3 - v4;
        for(int v11 = 0; true; ++v11) {
            boolean z = true;
            int v12 = 0x40000000;
            if(v11 >= v8) {
                break;
            }
            View view0 = this.getChildAt(v11);
            if(view0.getVisibility() != 8) {
                LayoutParams viewPager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(viewPager$LayoutParams0 != null && viewPager$LayoutParams0.isDecor) {
                    int v13 = viewPager$LayoutParams0.gravity & 7;
                    boolean z1 = (viewPager$LayoutParams0.gravity & 0x70) == 0x30 || (viewPager$LayoutParams0.gravity & 0x70) == 80;
                    if(v13 != 3 && v13 != 5) {
                        z = false;
                    }
                    int v14 = 0x80000000;
                    if(z1) {
                        v14 = 0x40000000;
                        v15 = 0x80000000;
                    }
                    else {
                        v15 = z ? 0x40000000 : 0x80000000;
                    }
                    switch(viewPager$LayoutParams0.width) {
                        case -2: {
                            v16 = v10;
                            break;
                        }
                        case -1: {
                            v16 = v10;
                            v14 = 0x40000000;
                            break;
                        }
                        default: {
                            v16 = viewPager$LayoutParams0.width;
                            v14 = 0x40000000;
                        }
                    }
                    switch(viewPager$LayoutParams0.height) {
                        case -2: {
                            v17 = v9;
                            v12 = v15;
                            break;
                        }
                        case -1: {
                            v17 = v9;
                            break;
                        }
                        default: {
                            v17 = viewPager$LayoutParams0.height;
                        }
                    }
                    view0.measure(View.MeasureSpec.makeMeasureSpec(v16, v14), View.MeasureSpec.makeMeasureSpec(v17, v12));
                    if(z1) {
                        v9 -= view0.getMeasuredHeight();
                    }
                    else if(z) {
                        v10 -= view0.getMeasuredWidth();
                    }
                }
            }
        }
        this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(v10, 0x40000000);
        this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(v9, 0x40000000);
        this.mInLayout = true;
        this.populate();
        this.mInLayout = false;
        int v19 = this.getChildCount();
        for(int v18 = 0; v18 < v19; ++v18) {
            View view1 = this.getChildAt(v18);
            if(view1.getVisibility() != 8) {
                LayoutParams viewPager$LayoutParams1 = (LayoutParams)view1.getLayoutParams();
                if(viewPager$LayoutParams1 == null || !viewPager$LayoutParams1.isDecor) {
                    view1.measure(View.MeasureSpec.makeMeasureSpec(((int)(((float)v10) * viewPager$LayoutParams1.widthFactor)), 0x40000000), this.mChildHeightMeasureSpec);
                }
            }
        }
    }

    @CallSuper
    protected void onPageScrolled(int v, float f, int v1) {
        int v11;
        if(this.mDecorChildCount > 0) {
            int v3 = this.getScrollX();
            int v4 = this.getPaddingLeft();
            int v5 = this.getPaddingRight();
            int v6 = this.getWidth();
            int v7 = this.getChildCount();
            int v8 = v5;
            int v9 = v4;
            for(int v10 = 0; v10 < v7; ++v10) {
                View view0 = this.getChildAt(v10);
                LayoutParams viewPager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(viewPager$LayoutParams0.isDecor) {
                    switch(viewPager$LayoutParams0.gravity & 7) {
                        case 1: {
                            v11 = v9;
                            v9 = Math.max((v6 - view0.getMeasuredWidth()) / 2, v9);
                            break;
                        }
                        case 3: {
                            v11 = view0.getWidth() + v9;
                            break;
                        }
                        case 5: {
                            int v12 = v6 - v8 - view0.getMeasuredWidth();
                            v8 += view0.getMeasuredWidth();
                            v11 = v9;
                            v9 = v12;
                            break;
                        }
                        default: {
                            v11 = v9;
                        }
                    }
                    int v13 = v9 + v3 - view0.getLeft();
                    if(v13 != 0) {
                        view0.offsetLeftAndRight(v13);
                    }
                    v9 = v11;
                }
            }
        }
        this.dispatchOnPageScrolled(v, f, v1);
        if(this.mPageTransformer != null) {
            int v14 = this.getScrollX();
            int v15 = this.getChildCount();
            for(int v2 = 0; v2 < v15; ++v2) {
                View view1 = this.getChildAt(v2);
                if(!((LayoutParams)view1.getLayoutParams()).isDecor) {
                    float f1 = ((float)(view1.getLeft() - v14)) / ((float)this.getClientWidth());
                    this.mPageTransformer.transformPage(view1, f1);
                }
            }
        }
        this.mCalledSuper = true;
    }

    @Override  // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int v, Rect rect0) {
        int v4;
        int v3;
        int v1 = this.getChildCount();
        int v2 = -1;
        if((v & 2) == 0) {
            v3 = v1 - 1;
            v4 = -1;
        }
        else {
            v2 = v1;
            v3 = 0;
            v4 = 1;
        }
        while(v3 != v2) {
            View view0 = this.getChildAt(v3);
            if(view0.getVisibility() == 0) {
                ItemInfo viewPager$ItemInfo0 = this.infoForChild(view0);
                if(viewPager$ItemInfo0 != null && viewPager$ItemInfo0.position == this.mCurItem && view0.requestFocus(v, rect0)) {
                    return true;
                }
            }
            v3 += v4;
        }
        return false;
    }

    @Override  // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        PagerAdapter pagerAdapter0 = this.mAdapter;
        if(pagerAdapter0 != null) {
            pagerAdapter0.restoreState(((SavedState)parcelable0).adapterState, ((SavedState)parcelable0).loader);
            this.setCurrentItemInternal(((SavedState)parcelable0).position, false, true);
            return;
        }
        this.mRestoredCurItem = ((SavedState)parcelable0).position;
        this.mRestoredAdapterState = ((SavedState)parcelable0).adapterState;
        this.mRestoredClassLoader = ((SavedState)parcelable0).loader;
    }

    @Override  // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.position = this.mCurItem;
        PagerAdapter pagerAdapter0 = this.mAdapter;
        if(pagerAdapter0 != null) {
            parcelable0.adapterState = pagerAdapter0.saveState();
        }
        return parcelable0;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionIndex();
        if(motionEvent0.getPointerId(v) == this.mActivePointerId) {
            int v1 = v == 0 ? 1 : 0;
            this.mLastMotionX = motionEvent0.getX(v1);
            this.mActivePointerId = motionEvent0.getPointerId(v1);
            VelocityTracker velocityTracker0 = this.mVelocityTracker;
            if(velocityTracker0 != null) {
                velocityTracker0.clear();
            }
        }
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        if(v != v2) {
            this.recomputeScrollPosition(v, v2, this.mPageMargin, this.mPageMargin);
        }
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        boolean z = false;
        if(this.mFakeDragging) {
            return true;
        }
        if(motionEvent0.getAction() == 0 && motionEvent0.getEdgeFlags() != 0) {
            return false;
        }
        if(this.mAdapter != null && this.mAdapter.getCount() != 0) {
            if(this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent0);
            switch(motionEvent0.getAction() & 0xFF) {
                case 0: {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = false;
                    this.populate();
                    float f = motionEvent0.getX();
                    this.mInitialMotionX = f;
                    this.mLastMotionX = f;
                    float f1 = motionEvent0.getY();
                    this.mInitialMotionY = f1;
                    this.mLastMotionY = f1;
                    this.mActivePointerId = motionEvent0.getPointerId(0);
                    break;
                }
                case 1: {
                    if(this.mIsBeingDragged) {
                        VelocityTracker velocityTracker0 = this.mVelocityTracker;
                        velocityTracker0.computeCurrentVelocity(1000, ((float)this.mMaximumVelocity));
                        int v = (int)velocityTracker0.getXVelocity(this.mActivePointerId);
                        this.mPopulatePending = true;
                        int v1 = this.getClientWidth();
                        int v2 = this.getScrollX();
                        ItemInfo viewPager$ItemInfo0 = this.infoForCurrentScrollPosition();
                        this.setCurrentItemInternal(this.determineTargetPage(viewPager$ItemInfo0.position, (((float)v2) / ((float)v1) - viewPager$ItemInfo0.offset) / (viewPager$ItemInfo0.widthFactor + ((float)this.mPageMargin) / ((float)v1)), v, ((int)(motionEvent0.getX(motionEvent0.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX))), true, true, v);
                        z = this.resetTouch();
                    }
                    break;
                }
                case 2: {
                    if(this.mIsBeingDragged) {
                    label_51:
                        if(this.mIsBeingDragged) {
                            z = this.performDrag(motionEvent0.getX(motionEvent0.findPointerIndex(this.mActivePointerId)));
                        }
                    }
                    else {
                        int v3 = motionEvent0.findPointerIndex(this.mActivePointerId);
                        if(v3 == -1) {
                            z = this.resetTouch();
                        }
                        else {
                            float f2 = motionEvent0.getX(v3);
                            float f3 = Math.abs(f2 - this.mLastMotionX);
                            float f4 = motionEvent0.getY(v3);
                            if(f3 > ((float)this.mTouchSlop) && f3 > Math.abs(f4 - this.mLastMotionY)) {
                                this.mIsBeingDragged = true;
                                this.requestParentDisallowInterceptTouchEvent(true);
                                this.mLastMotionX = f2 - this.mInitialMotionX > 0.0f ? this.mInitialMotionX + ((float)this.mTouchSlop) : this.mInitialMotionX - ((float)this.mTouchSlop);
                                this.mLastMotionY = f4;
                                this.setScrollState(1);
                                this.setScrollingCacheEnabled(true);
                                ViewParent viewParent0 = this.getParent();
                                if(viewParent0 != null) {
                                    viewParent0.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                            goto label_51;
                        }
                    }
                    break;
                }
                case 3: {
                    if(this.mIsBeingDragged) {
                        this.scrollToItem(this.mCurItem, true, 0, false);
                        z = this.resetTouch();
                    }
                    break;
                }
                case 5: {
                    int v4 = motionEvent0.getActionIndex();
                    this.mLastMotionX = motionEvent0.getX(v4);
                    this.mActivePointerId = motionEvent0.getPointerId(v4);
                    break;
                }
                case 6: {
                    this.onSecondaryPointerUp(motionEvent0);
                    this.mLastMotionX = motionEvent0.getX(motionEvent0.findPointerIndex(this.mActivePointerId));
                }
            }
            if(z) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            return true;
        }
        return false;
    }

    boolean pageLeft() {
        int v = this.mCurItem;
        if(v > 0) {
            this.setCurrentItem(v - 1, true);
            return true;
        }
        return false;
    }

    boolean pageRight() {
        if(this.mAdapter != null && this.mCurItem < this.mAdapter.getCount() - 1) {
            this.setCurrentItem(this.mCurItem + 1, true);
            return true;
        }
        return false;
    }

    private boolean pageScrolled(int v) {
        if(this.mItems.size() == 0) {
            if(this.mFirstLayout) {
                return false;
            }
            this.mCalledSuper = false;
            this.onPageScrolled(0, 0.0f, 0);
            if(!this.mCalledSuper) {
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            }
            return false;
        }
        ItemInfo viewPager$ItemInfo0 = this.infoForCurrentScrollPosition();
        int v1 = this.getClientWidth();
        int v2 = v1 + this.mPageMargin;
        int v3 = viewPager$ItemInfo0.position;
        float f = (((float)v) / ((float)v1) - viewPager$ItemInfo0.offset) / (viewPager$ItemInfo0.widthFactor + ((float)this.mPageMargin) / ((float)v1));
        this.mCalledSuper = false;
        this.onPageScrolled(v3, f, ((int)(((float)v2) * f)));
        if(!this.mCalledSuper) {
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        return true;
    }

    private boolean performDrag(float f) {
        boolean z2;
        boolean z1;
        float f1 = this.mLastMotionX - f;
        this.mLastMotionX = f;
        float f2 = ((float)this.getScrollX()) + f1;
        float f3 = (float)this.getClientWidth();
        float f4 = this.mFirstOffset * f3;
        float f5 = this.mLastOffset * f3;
        boolean z = false;
        ItemInfo viewPager$ItemInfo0 = (ItemInfo)this.mItems.get(0);
        ItemInfo viewPager$ItemInfo1 = (ItemInfo)this.mItems.get(this.mItems.size() - 1);
        if(viewPager$ItemInfo0.position == 0) {
            z1 = true;
        }
        else {
            f4 = viewPager$ItemInfo0.offset * f3;
            z1 = false;
        }
        if(viewPager$ItemInfo1.position == this.mAdapter.getCount() - 1) {
            z2 = true;
        }
        else {
            f5 = viewPager$ItemInfo1.offset * f3;
            z2 = false;
        }
        if(f2 < f4) {
            if(z1) {
                this.mLeftEdge.onPull(Math.abs(f4 - f2) / f3);
                z = true;
            }
            f2 = f4;
        }
        else if(f2 > f5) {
            if(z2) {
                this.mRightEdge.onPull(Math.abs(f2 - f5) / f3);
                z = true;
            }
            f2 = f5;
        }
        this.mLastMotionX += f2 - ((float)(((int)f2)));
        this.scrollTo(((int)f2), this.getScrollY());
        this.pageScrolled(((int)f2));
        return z;
    }

    void populate() {
        this.populate(this.mCurItem);
    }

    void populate(int v) {
        String s;
        ItemInfo viewPager$ItemInfo1;
        ItemInfo viewPager$ItemInfo0;
        int v1 = this.mCurItem;
        if(v1 == v) {
            viewPager$ItemInfo0 = null;
        }
        else {
            viewPager$ItemInfo0 = this.infoForPosition(v1);
            this.mCurItem = v;
        }
        if(this.mAdapter == null) {
            this.sortChildDrawingOrder();
            return;
        }
        if(this.mPopulatePending) {
            this.sortChildDrawingOrder();
            return;
        }
        if(this.getWindowToken() == null) {
            return;
        }
        this.mAdapter.startUpdate(this);
        int v2 = this.mOffscreenPageLimit;
        int v3 = Math.max(0, this.mCurItem - v2);
        int v4 = this.mAdapter.getCount();
        int v5 = Math.min(v4 - 1, this.mCurItem + v2);
        if(v4 == this.mExpectedAdapterCount) {
            int v6 = 0;
            while(true) {
                if(v6 < this.mItems.size()) {
                    viewPager$ItemInfo1 = (ItemInfo)this.mItems.get(v6);
                    if(viewPager$ItemInfo1.position < this.mCurItem) {
                        ++v6;
                        continue;
                    }
                    else if(viewPager$ItemInfo1.position == this.mCurItem) {
                        break;
                    }
                }
                viewPager$ItemInfo1 = null;
                break;
            }
            if(viewPager$ItemInfo1 == null && v4 > 0) {
                viewPager$ItemInfo1 = this.addNewItem(this.mCurItem, v6);
            }
            if(viewPager$ItemInfo1 != null) {
                ItemInfo viewPager$ItemInfo2 = v6 - 1 < 0 ? null : ((ItemInfo)this.mItems.get(v6 - 1));
                int v7 = this.getClientWidth();
                float f = v7 > 0 ? 2.0f - viewPager$ItemInfo1.widthFactor + ((float)this.getPaddingLeft()) / ((float)v7) : 0.0f;
                int v8 = this.mCurItem - 1;
                int v9 = v6 - 1;
                int v10 = v6;
                float f1 = 0.0f;
                while(v8 >= 0) {
                    if(f1 < f || v8 >= v3) {
                        if(viewPager$ItemInfo2 == null || v8 != viewPager$ItemInfo2.position) {
                            f1 += this.addNewItem(v8, v9 + 1).widthFactor;
                            ++v10;
                        }
                        else {
                            f1 += viewPager$ItemInfo2.widthFactor;
                            --v9;
                        }
                        viewPager$ItemInfo2 = v9 < 0 ? null : ((ItemInfo)this.mItems.get(v9));
                    }
                    else {
                        if(viewPager$ItemInfo2 == null) {
                            break;
                        }
                        if(v8 == viewPager$ItemInfo2.position && !viewPager$ItemInfo2.scrolling) {
                            this.mItems.remove(v9);
                            this.mAdapter.destroyItem(this, v8, viewPager$ItemInfo2.object);
                            --v9;
                            --v10;
                            viewPager$ItemInfo2 = v9 < 0 ? null : ((ItemInfo)this.mItems.get(v9));
                        }
                    }
                    --v8;
                }
                float f2 = viewPager$ItemInfo1.widthFactor;
                int v11 = v10 + 1;
                if(f2 < 2.0f) {
                    ItemInfo viewPager$ItemInfo3 = v11 >= this.mItems.size() ? null : ((ItemInfo)this.mItems.get(v11));
                    float f3 = v7 > 0 ? ((float)this.getPaddingRight()) / ((float)v7) + 2.0f : 0.0f;
                    int v12 = this.mCurItem;
                    while(true) {
                        ++v12;
                        if(v12 >= v4) {
                            break;
                        }
                        if(f2 >= f3 && v12 > v5) {
                            if(viewPager$ItemInfo3 == null) {
                                break;
                            }
                            if(v12 != viewPager$ItemInfo3.position || viewPager$ItemInfo3.scrolling) {
                                continue;
                            }
                            this.mItems.remove(v11);
                            this.mAdapter.destroyItem(this, v12, viewPager$ItemInfo3.object);
                        }
                        else if(viewPager$ItemInfo3 != null && v12 == viewPager$ItemInfo3.position) {
                            f2 += viewPager$ItemInfo3.widthFactor;
                            ++v11;
                        }
                        else {
                            ItemInfo viewPager$ItemInfo4 = this.addNewItem(v12, v11);
                            ++v11;
                            f2 += viewPager$ItemInfo4.widthFactor;
                        }
                        viewPager$ItemInfo3 = v11 < this.mItems.size() ? ((ItemInfo)this.mItems.get(v11)) : null;
                    }
                }
                this.calculatePageOffsets(viewPager$ItemInfo1, v10, viewPager$ItemInfo0);
                this.mAdapter.setPrimaryItem(this, this.mCurItem, viewPager$ItemInfo1.object);
            }
            this.mAdapter.finishUpdate(this);
            int v13 = this.getChildCount();
            for(int v14 = 0; v14 < v13; ++v14) {
                View view0 = this.getChildAt(v14);
                LayoutParams viewPager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                viewPager$LayoutParams0.childIndex = v14;
                if(!viewPager$LayoutParams0.isDecor && viewPager$LayoutParams0.widthFactor == 0.0f) {
                    ItemInfo viewPager$ItemInfo5 = this.infoForChild(view0);
                    if(viewPager$ItemInfo5 != null) {
                        viewPager$LayoutParams0.widthFactor = viewPager$ItemInfo5.widthFactor;
                        viewPager$LayoutParams0.position = viewPager$ItemInfo5.position;
                    }
                }
            }
            this.sortChildDrawingOrder();
            if(this.hasFocus()) {
                View view1 = this.findFocus();
                ItemInfo viewPager$ItemInfo6 = view1 == null ? null : this.infoForAnyChild(view1);
                if(viewPager$ItemInfo6 == null || viewPager$ItemInfo6.position != this.mCurItem) {
                    for(int v15 = 0; v15 < this.getChildCount(); ++v15) {
                        View view2 = this.getChildAt(v15);
                        ItemInfo viewPager$ItemInfo7 = this.infoForChild(view2);
                        if(viewPager$ItemInfo7 != null && viewPager$ItemInfo7.position == this.mCurItem && view2.requestFocus(2)) {
                            break;
                        }
                    }
                }
            }
            return;
        }
        try {
            s = this.getResources().getResourceName(this.getId());
        }
        catch(Resources.NotFoundException unused_ex) {
            s = Integer.toHexString(this.getId());
        }
        throw new IllegalStateException("The application\'s PagerAdapter changed the adapter\'s contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.mExpectedAdapterCount + ", found: " + v4 + " Pager id: " + s + " Pager class: " + this.getClass() + " Problematic adapter: " + this.mAdapter.getClass());
    }

    private void recomputeScrollPosition(int v, int v1, int v2, int v3) {
        if(v1 > 0 && !this.mItems.isEmpty()) {
            if(!this.mScroller.isFinished()) {
                this.mScroller.setFinalX(this.getCurrentItem() * this.getClientWidth());
                return;
            }
            int v4 = this.getPaddingLeft();
            int v5 = this.getPaddingRight();
            int v6 = this.getPaddingLeft();
            int v7 = this.getPaddingRight();
            this.scrollTo(((int)(((float)this.getScrollX()) / ((float)(v1 - v6 - v7 + v3)) * ((float)(v - v4 - v5 + v2)))), this.getScrollY());
            return;
        }
        ItemInfo viewPager$ItemInfo0 = this.infoForPosition(this.mCurItem);
        int v8 = (int)((viewPager$ItemInfo0 == null ? 0.0f : Math.min(viewPager$ItemInfo0.offset, this.mLastOffset)) * ((float)(v - this.getPaddingLeft() - this.getPaddingRight())));
        if(v8 != this.getScrollX()) {
            this.completeScroll(false);
            this.scrollTo(v8, this.getScrollY());
        }
    }

    private void removeNonDecorViews() {
        for(int v = 0; v < this.getChildCount(); ++v) {
            if(!((LayoutParams)this.getChildAt(v).getLayoutParams()).isDecor) {
                this.removeViewAt(v);
                --v;
            }
        }
    }

    public void removeOnAdapterChangeListener(@NonNull OnAdapterChangeListener viewPager$OnAdapterChangeListener0) {
        List list0 = this.mAdapterChangeListeners;
        if(list0 != null) {
            list0.remove(viewPager$OnAdapterChangeListener0);
        }
    }

    public void removeOnPageChangeListener(@NonNull OnPageChangeListener viewPager$OnPageChangeListener0) {
        List list0 = this.mOnPageChangeListeners;
        if(list0 != null) {
            list0.remove(viewPager$OnPageChangeListener0);
        }
    }

    @Override  // android.view.ViewGroup
    public void removeView(View view0) {
        if(this.mInLayout) {
            this.removeViewInLayout(view0);
            return;
        }
        super.removeView(view0);
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        ViewParent viewParent0 = this.getParent();
        if(viewParent0 != null) {
            viewParent0.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean resetTouch() {
        this.mActivePointerId = -1;
        this.endDrag();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        return this.mLeftEdge.isFinished() || this.mRightEdge.isFinished();
    }

    private void scrollToItem(int v, boolean z, int v1, boolean z1) {
        ItemInfo viewPager$ItemInfo0 = this.infoForPosition(v);
        int v2 = viewPager$ItemInfo0 == null ? 0 : ((int)(((float)this.getClientWidth()) * Math.max(this.mFirstOffset, Math.min(viewPager$ItemInfo0.offset, this.mLastOffset))));
        if(z) {
            this.smoothScrollTo(v2, 0, v1);
            if(z1) {
                this.dispatchOnPageSelected(v);
            }
        }
        else {
            if(z1) {
                this.dispatchOnPageSelected(v);
            }
            this.completeScroll(false);
            this.scrollTo(v2, 0);
            this.pageScrolled(v2);
        }
    }

    public void setAdapter(@Nullable PagerAdapter pagerAdapter0) {
        PagerAdapter pagerAdapter1 = this.mAdapter;
        if(pagerAdapter1 != null) {
            pagerAdapter1.setViewPagerObserver(null);
            this.mAdapter.startUpdate(this);
            for(int v1 = 0; v1 < this.mItems.size(); ++v1) {
                ItemInfo viewPager$ItemInfo0 = (ItemInfo)this.mItems.get(v1);
                this.mAdapter.destroyItem(this, viewPager$ItemInfo0.position, viewPager$ItemInfo0.object);
            }
            this.mAdapter.finishUpdate(this);
            this.mItems.clear();
            this.removeNonDecorViews();
            this.mCurItem = 0;
            this.scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter2 = this.mAdapter;
        this.mAdapter = pagerAdapter0;
        this.mExpectedAdapterCount = 0;
        if(this.mAdapter != null) {
            if(this.mObserver == null) {
                this.mObserver = new PagerObserver(this);
            }
            this.mAdapter.setViewPagerObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean z = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if(this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                this.setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            }
            else if(z) {
                this.requestLayout();
            }
            else {
                this.populate();
            }
        }
        if(this.mAdapterChangeListeners != null && !this.mAdapterChangeListeners.isEmpty()) {
            int v2 = this.mAdapterChangeListeners.size();
            for(int v = 0; v < v2; ++v) {
                ((OnAdapterChangeListener)this.mAdapterChangeListeners.get(v)).onAdapterChanged(this, pagerAdapter2, pagerAdapter0);
            }
        }
    }

    public void setCurrentItem(int v) {
        this.mPopulatePending = false;
        this.setCurrentItemInternal(v, !this.mFirstLayout, false);
    }

    public void setCurrentItem(int v, boolean z) {
        this.mPopulatePending = false;
        this.setCurrentItemInternal(v, z, false);
    }

    void setCurrentItemInternal(int v, boolean z, boolean z1) {
        this.setCurrentItemInternal(v, z, z1, 0);
    }

    void setCurrentItemInternal(int v, boolean z, boolean z1, int v1) {
        boolean z2 = true;
        if(this.mAdapter != null && this.mAdapter.getCount() > 0) {
            if(!z1 && this.mCurItem == v && this.mItems.size() != 0) {
                this.setScrollingCacheEnabled(false);
                return;
            }
            if(v < 0) {
                v = 0;
            }
            else if(v >= this.mAdapter.getCount()) {
                v = this.mAdapter.getCount() - 1;
            }
            if(v > this.mCurItem + this.mOffscreenPageLimit || v < this.mCurItem - this.mOffscreenPageLimit) {
                for(int v2 = 0; v2 < this.mItems.size(); ++v2) {
                    ((ItemInfo)this.mItems.get(v2)).scrolling = true;
                }
            }
            if(this.mCurItem == v) {
                z2 = false;
            }
            if(this.mFirstLayout) {
                this.mCurItem = v;
                if(z2) {
                    this.dispatchOnPageSelected(v);
                }
                this.requestLayout();
                return;
            }
            this.populate(v);
            this.scrollToItem(v, z, v1, z2);
            return;
        }
        this.setScrollingCacheEnabled(false);
    }

    OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener viewPager$OnPageChangeListener0) {
        OnPageChangeListener viewPager$OnPageChangeListener1 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = viewPager$OnPageChangeListener0;
        return viewPager$OnPageChangeListener1;
    }

    public void setOffscreenPageLimit(int v) {
        if(v < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + v + " too small; defaulting to " + 1);
            v = 1;
        }
        if(v != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = v;
            this.populate();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener viewPager$OnPageChangeListener0) {
        this.mOnPageChangeListener = viewPager$OnPageChangeListener0;
    }

    public void setPageMargin(int v) {
        int v1 = this.mPageMargin;
        this.mPageMargin = v;
        int v2 = this.getWidth();
        this.recomputeScrollPosition(v2, v2, v, v1);
        this.requestLayout();
    }

    public void setPageMarginDrawable(@DrawableRes int v) {
        this.setPageMarginDrawable(ContextCompat.getDrawable(this.getContext(), v));
    }

    public void setPageMarginDrawable(@Nullable Drawable drawable0) {
        this.mMarginDrawable = drawable0;
        if(drawable0 != null) {
            this.refreshDrawableState();
        }
        this.setWillNotDraw(drawable0 == null);
        this.invalidate();
    }

    public void setPageTransformer(boolean z, @Nullable PageTransformer viewPager$PageTransformer0) {
        this.setPageTransformer(z, viewPager$PageTransformer0, 2);
    }

    public void setPageTransformer(boolean z, @Nullable PageTransformer viewPager$PageTransformer0, int v) {
        int v1 = 1;
        boolean z1 = viewPager$PageTransformer0 != null != (this.mPageTransformer != null);
        this.mPageTransformer = viewPager$PageTransformer0;
        this.setChildrenDrawingOrderEnabled(viewPager$PageTransformer0 != null);
        if(viewPager$PageTransformer0 == null) {
            this.mDrawingOrder = 0;
        }
        else {
            if(z) {
                v1 = 2;
            }
            this.mDrawingOrder = v1;
            this.mPageTransformerLayerType = v;
        }
        if(z1) {
            this.populate();
        }
    }

    void setScrollState(int v) {
        if(this.mScrollState == v) {
            return;
        }
        this.mScrollState = v;
        if(this.mPageTransformer != null) {
            this.enableLayers(v != 0);
        }
        this.dispatchOnScrollStateChanged(v);
    }

    private void setScrollingCacheEnabled(boolean z) {
        if(this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    void smoothScrollTo(int v, int v1) {
        this.smoothScrollTo(v, v1, 0);
    }

    void smoothScrollTo(int v, int v1, int v2) {
        int v4;
        if(this.getChildCount() == 0) {
            this.setScrollingCacheEnabled(false);
            return;
        }
        if(this.mScroller == null || this.mScroller.isFinished()) {
            v4 = this.getScrollX();
        }
        else {
            int v3 = this.mIsScrollStarted ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            this.setScrollingCacheEnabled(false);
            v4 = v3;
        }
        int v5 = this.getScrollY();
        int v6 = v - v4;
        int v7 = v1 - v5;
        if(v6 == 0 && v7 == 0) {
            this.completeScroll(false);
            this.populate();
            this.setScrollState(0);
            return;
        }
        this.setScrollingCacheEnabled(true);
        this.setScrollState(2);
        int v8 = this.getClientWidth();
        int v9 = Math.abs(v2);
        this.mIsScrollStarted = false;
        this.mScroller.startScroll(v4, v5, v6, v7, Math.min((v9 <= 0 ? ((int)((((float)Math.abs(v6)) / (((float)v8) * 1.0f + ((float)this.mPageMargin)) + 1.0f) * 100.0f)) : Math.round(Math.abs((((float)(v8 / 2)) + this.distanceInfluenceForSnapDuration(Math.min(1.0f, ((float)Math.abs(v6)) * 1.0f / ((float)v8))) * ((float)(v8 / 2))) / ((float)v9)) * 1000.0f) * 4), 600));
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void sortChildDrawingOrder() {
        if(this.mDrawingOrder != 0) {
            ArrayList arrayList0 = this.mDrawingOrderedChildren;
            if(arrayList0 == null) {
                this.mDrawingOrderedChildren = new ArrayList();
            }
            else {
                arrayList0.clear();
            }
            int v = this.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view0 = this.getChildAt(v1);
                this.mDrawingOrderedChildren.add(view0);
            }
            Collections.sort(this.mDrawingOrderedChildren, ViewPager.sPositionComparator);
        }
    }

    @Override  // android.view.View
    protected boolean verifyDrawable(Drawable drawable0) {
        return super.verifyDrawable(drawable0) || drawable0 == this.mMarginDrawable;
    }
}

