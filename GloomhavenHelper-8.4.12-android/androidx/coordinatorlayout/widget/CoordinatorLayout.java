package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnPreDrawListener;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.R.attr;
import androidx.coordinatorlayout.R.style;
import androidx.coordinatorlayout.R.styleable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pools.Pool;
import androidx.core.util.Pools.SynchronizedPool;
import androidx.core.view.GravityCompat;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent2 {
    public interface AttachedBehavior {
        @NonNull
        Behavior getBehavior();
    }

    public static abstract class Behavior {
        public Behavior() {
        }

        public Behavior(Context context0, AttributeSet attributeSet0) {
        }

        public boolean blocksInteractionBelow(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0) {
            return false;
        }

        public boolean getInsetDodgeRect(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull Rect rect0) [...] // Inlined contents

        @ColorInt
        public int getScrimColor(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0) [...] // Inlined contents

        @FloatRange(from = 0.0, to = 1.0)
        public float getScrimOpacity(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0) [...] // Inlined contents

        @Nullable
        public static Object getTag(@NonNull View view0) {
            return ((LayoutParams)view0.getLayoutParams()).mBehaviorTag;
        }

        public boolean layoutDependsOn(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1) [...] // Inlined contents

        @NonNull
        public WindowInsetsCompat onApplyWindowInsets(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull WindowInsetsCompat windowInsetsCompat0) [...] // Inlined contents

        public void onAttachedToLayoutParams(@NonNull LayoutParams coordinatorLayout$LayoutParams0) {
        }

        public boolean onDependentViewChanged(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1) [...] // Inlined contents

        public void onDependentViewRemoved(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1) {
        }

        public void onDetachedFromLayoutParams() {
        }

        public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull MotionEvent motionEvent0) [...] // Inlined contents

        public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, int v) [...] // Inlined contents

        public boolean onMeasureChild(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, int v, int v1, int v2, int v3) [...] // Inlined contents

        public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, float f, float f1, boolean z) [...] // Inlined contents

        public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, float f, float f1) [...] // Inlined contents

        @Deprecated
        public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, int v, int v1, @NonNull int[] arr_v) {
        }

        public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, int v, int v1, @NonNull int[] arr_v, int v2) {
        }

        @Deprecated
        public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, int v, int v1, int v2, int v3) {
        }

        public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, int v, int v1, int v2, int v3, int v4) {
        }

        @Deprecated
        public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, @NonNull View view2, int v) {
        }

        public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, @NonNull View view2, int v, int v1) {
        }

        public boolean onRequestChildRectangleOnScreen(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull Rect rect0, boolean z) [...] // Inlined contents

        public void onRestoreInstanceState(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull Parcelable parcelable0) {
        }

        @Nullable
        public Parcelable onSaveInstanceState(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, @NonNull View view2, int v) [...] // Inlined contents

        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, @NonNull View view2, int v, int v1) {
            return false;
        }

        @Deprecated
        public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1) {
        }

        public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull View view1, int v) {
        }

        public boolean onTouchEvent(@NonNull CoordinatorLayout coordinatorLayout0, @NonNull View view0, @NonNull MotionEvent motionEvent0) [...] // Inlined contents

        public static void setTag(@NonNull View view0, @Nullable Object object0) {
            ((LayoutParams)view0.getLayoutParams()).mBehaviorTag = object0;
        }
    }

    @Deprecated
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DefaultBehavior {
        Class value();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DispatchChangeEvent {
    }

    class HierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        @Override  // android.view.ViewGroup$OnHierarchyChangeListener
        public void onChildViewAdded(View view0, View view1) {
            if(CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
                CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewAdded(view0, view1);
            }
        }

        @Override  // android.view.ViewGroup$OnHierarchyChangeListener
        public void onChildViewRemoved(View view0, View view1) {
            CoordinatorLayout.this.onChildViewsChanged(2);
            if(CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
                CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewRemoved(view0, view1);
            }
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int anchorGravity;
        public int dodgeInsetEdges;
        public int gravity;
        public int insetEdge;
        public int keyline;
        View mAnchorDirectChild;
        int mAnchorId;
        View mAnchorView;
        Behavior mBehavior;
        boolean mBehaviorResolved;
        Object mBehaviorTag;
        private boolean mDidAcceptNestedScrollNonTouch;
        private boolean mDidAcceptNestedScrollTouch;
        private boolean mDidBlockInteraction;
        private boolean mDidChangeAfterNestedScroll;
        int mInsetOffsetX;
        int mInsetOffsetY;
        final Rect mLastChildRect;

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        LayoutParams(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.CoordinatorLayout_Layout);
            this.gravity = typedArray0.getInteger(styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.mAnchorId = typedArray0.getResourceId(styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            this.anchorGravity = typedArray0.getInteger(styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.keyline = typedArray0.getInteger(styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            this.insetEdge = typedArray0.getInt(styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.dodgeInsetEdges = typedArray0.getInt(styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            this.mBehaviorResolved = typedArray0.hasValue(styleable.CoordinatorLayout_Layout_layout_behavior);
            if(this.mBehaviorResolved) {
                this.mBehavior = CoordinatorLayout.parseBehavior(context0, attributeSet0, typedArray0.getString(styleable.CoordinatorLayout_Layout_layout_behavior));
            }
            typedArray0.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        public LayoutParams(LayoutParams coordinatorLayout$LayoutParams0) {
            super(coordinatorLayout$LayoutParams0);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        boolean checkAnchorChanged() {
            return this.mAnchorView == null && this.mAnchorId != -1;
        }

        boolean dependsOn(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
            return view1 == this.mAnchorDirectChild || this.shouldDodge(view1, ViewCompat.getLayoutDirection(coordinatorLayout0));
        }

        boolean didBlockInteraction() {
            if(this.mBehavior == null) {
                this.mDidBlockInteraction = false;
            }
            return this.mDidBlockInteraction;
        }

        View findAnchorView(CoordinatorLayout coordinatorLayout0, View view0) {
            if(this.mAnchorId == -1) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
                return null;
            }
            if(this.mAnchorView == null || !this.verifyAnchorView(view0, coordinatorLayout0)) {
                this.resolveAnchorView(view0, coordinatorLayout0);
            }
            return this.mAnchorView;
        }

        @IdRes
        public int getAnchorId() {
            return this.mAnchorId;
        }

        @Nullable
        public Behavior getBehavior() {
            return this.mBehavior;
        }

        boolean getChangedAfterNestedScroll() {
            return this.mDidChangeAfterNestedScroll;
        }

        Rect getLastChildRect() {
            return this.mLastChildRect;
        }

        void invalidateAnchor() {
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
        }

        boolean isBlockingInteractionBelow(CoordinatorLayout coordinatorLayout0, View view0) {
            if(this.mDidBlockInteraction) {
                return true;
            }
            boolean z = this.mBehavior == null ? false : this.mBehavior.blocksInteractionBelow(coordinatorLayout0, view0);
            this.mDidBlockInteraction = z;
            return z;
        }

        boolean isNestedScrollAccepted(int v) {
            switch(v) {
                case 0: {
                    return this.mDidAcceptNestedScrollTouch;
                }
                case 1: {
                    return this.mDidAcceptNestedScrollNonTouch;
                }
                default: {
                    return false;
                }
            }
        }

        void resetChangedAfterNestedScroll() {
            this.mDidChangeAfterNestedScroll = false;
        }

        void resetNestedScroll(int v) {
            this.setNestedScrollAccepted(v, false);
        }

        void resetTouchBehaviorTracking() {
            this.mDidBlockInteraction = false;
        }

        private void resolveAnchorView(View view0, CoordinatorLayout coordinatorLayout0) {
            this.mAnchorView = coordinatorLayout0.findViewById(this.mAnchorId);
            View view1 = this.mAnchorView;
            if(view1 != null) {
                if(view1 == coordinatorLayout0) {
                    if(!coordinatorLayout0.isInEditMode()) {
                        throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                    }
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                    return;
                }
                for(ViewParent viewParent0 = view1.getParent(); viewParent0 != coordinatorLayout0 && viewParent0 != null; viewParent0 = viewParent0.getParent()) {
                    if(viewParent0 == view0) {
                        if(!coordinatorLayout0.isInEditMode()) {
                            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                        }
                        this.mAnchorDirectChild = null;
                        this.mAnchorView = null;
                        return;
                    }
                    if(viewParent0 instanceof View) {
                        view1 = (View)viewParent0;
                    }
                }
                this.mAnchorDirectChild = view1;
                return;
            }
            if(!coordinatorLayout0.isInEditMode()) {
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout0.getResources().getResourceName(this.mAnchorId) + " to anchor view " + view0);
            }
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
        }

        public void setAnchorId(@IdRes int v) {
            this.invalidateAnchor();
            this.mAnchorId = v;
        }

        public void setBehavior(@Nullable Behavior coordinatorLayout$Behavior0) {
            if(this.mBehavior != coordinatorLayout$Behavior0) {
                this.mBehavior = coordinatorLayout$Behavior0;
                this.mBehaviorTag = null;
                this.mBehaviorResolved = true;
            }
        }

        void setChangedAfterNestedScroll(boolean z) {
            this.mDidChangeAfterNestedScroll = z;
        }

        void setLastChildRect(Rect rect0) {
            this.mLastChildRect.set(rect0);
        }

        void setNestedScrollAccepted(int v, boolean z) {
            switch(v) {
                case 0: {
                    this.mDidAcceptNestedScrollTouch = z;
                    return;
                }
                case 1: {
                    this.mDidAcceptNestedScrollNonTouch = z;
                }
            }
        }

        private boolean shouldDodge(View view0, int v) {
            int v1 = GravityCompat.getAbsoluteGravity(((LayoutParams)view0.getLayoutParams()).insetEdge, v);
            return v1 != 0 && (GravityCompat.getAbsoluteGravity(this.dodgeInsetEdges, v) & v1) == v1;
        }

        private boolean verifyAnchorView(View view0, CoordinatorLayout coordinatorLayout0) {
            if(this.mAnchorView.getId() != this.mAnchorId) {
                return false;
            }
            View view1 = this.mAnchorView;
            ViewParent viewParent0 = view1.getParent();
            while(viewParent0 != coordinatorLayout0) {
                if(viewParent0 != null && viewParent0 != view0) {
                    if(viewParent0 instanceof View) {
                        view1 = (View)viewParent0;
                    }
                    viewParent0 = viewParent0.getParent();
                    continue;
                }
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
                return false;
            }
            this.mAnchorDirectChild = view1;
            return true;
        }
    }

    class OnPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        @Override  // android.view.ViewTreeObserver$OnPreDrawListener
        public boolean onPreDraw() {
            CoordinatorLayout.this.onChildViewsChanged(0);
            return true;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        SparseArray behaviorStates;

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

        public SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            int v = parcel0.readInt();
            int[] arr_v = new int[v];
            parcel0.readIntArray(arr_v);
            Parcelable[] arr_parcelable = parcel0.readParcelableArray(classLoader0);
            this.behaviorStates = new SparseArray(v);
            for(int v1 = 0; v1 < v; ++v1) {
                this.behaviorStates.append(arr_v[v1], arr_parcelable[v1]);
            }
        }

        public SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            int v2 = this.behaviorStates == null ? 0 : this.behaviorStates.size();
            parcel0.writeInt(v2);
            int[] arr_v = new int[v2];
            Parcelable[] arr_parcelable = new Parcelable[v2];
            for(int v1 = 0; v1 < v2; ++v1) {
                arr_v[v1] = this.behaviorStates.keyAt(v1);
                arr_parcelable[v1] = (Parcelable)this.behaviorStates.valueAt(v1);
            }
            parcel0.writeIntArray(arr_v);
            parcel0.writeParcelableArray(arr_parcelable, v);
        }
    }

    static class ViewElevationComparator implements Comparator {
        public int compare(View view0, View view1) {
            float f = ViewCompat.getZ(view0);
            float f1 = ViewCompat.getZ(view1);
            if(f > f1) {
                return -1;
            }
            return f < f1 ? 1 : 0;
        }

        @Override
        public int compare(Object object0, Object object1) {
            return this.compare(((View)object0), ((View)object1));
        }
    }

    static final Class[] CONSTRUCTOR_PARAMS = null;
    static final int EVENT_NESTED_SCROLL = 1;
    static final int EVENT_PRE_DRAW = 0;
    static final int EVENT_VIEW_REMOVED = 2;
    static final String TAG = "CoordinatorLayout";
    static final Comparator TOP_SORTED_CHILDREN_COMPARATOR = null;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    private View mBehaviorTouchView;
    private final DirectedAcyclicGraph mChildDag;
    private final List mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private WindowInsetsCompat mLastInsets;
    private boolean mNeedsPreDrawListener;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List mTempDependenciesList;
    private final int[] mTempIntPair;
    private final List mTempList1;
    static final ThreadLocal sConstructors;
    private static final Pool sRectPool;

    static {
        Package package0 = CoordinatorLayout.class.getPackage();
        CoordinatorLayout.WIDGET_PACKAGE_NAME = package0 == null ? null : package0.getName();
        CoordinatorLayout.TOP_SORTED_CHILDREN_COMPARATOR = Build.VERSION.SDK_INT >= 21 ? new ViewElevationComparator() : null;
        CoordinatorLayout.CONSTRUCTOR_PARAMS = new Class[]{Context.class, AttributeSet.class};
        CoordinatorLayout.sConstructors = new ThreadLocal();
        CoordinatorLayout.sRectPool = new SynchronizedPool(12);
    }

    public CoordinatorLayout(@NonNull Context context0) {
        this(context0, null);
    }

    public CoordinatorLayout(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.coordinatorLayoutStyle);
    }

    public CoordinatorLayout(@NonNull Context context0, @Nullable AttributeSet attributeSet0, @AttrRes int v) {
        super(context0, attributeSet0, v);
        this.mDependencySortedChildren = new ArrayList();
        this.mChildDag = new DirectedAcyclicGraph();
        this.mTempList1 = new ArrayList();
        this.mTempDependenciesList = new ArrayList();
        this.mTempIntPair = new int[2];
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        TypedArray typedArray0 = v == 0 ? context0.obtainStyledAttributes(attributeSet0, styleable.CoordinatorLayout, 0, style.Widget_Support_CoordinatorLayout) : context0.obtainStyledAttributes(attributeSet0, styleable.CoordinatorLayout, v, 0);
        int v2 = typedArray0.getResourceId(styleable.CoordinatorLayout_keylines, 0);
        if(v2 != 0) {
            Resources resources0 = context0.getResources();
            this.mKeylines = resources0.getIntArray(v2);
            float f = resources0.getDisplayMetrics().density;
            for(int v1 = 0; v1 < this.mKeylines.length; ++v1) {
                this.mKeylines[v1] = (int)(((float)this.mKeylines[v1]) * f);
            }
        }
        this.mStatusBarBackground = typedArray0.getDrawable(styleable.CoordinatorLayout_statusBarBackground);
        typedArray0.recycle();
        this.setupForInsets();
        super.setOnHierarchyChangeListener(new HierarchyChangeListener(this));
    }

    @NonNull
    private static Rect acquireTempRect() {
        Rect rect0 = (Rect)CoordinatorLayout.sRectPool.acquire();
        return rect0 == null ? new Rect() : rect0;
    }

    void addPreDrawListener() {
        if(this.mIsAttachedToWindow) {
            if(this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener(this);
            }
            this.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams0);
    }

    private static int clamp(int v, int v1, int v2) {
        if(v < v1) {
            return v1;
        }
        return v <= v2 ? v : v2;
    }

    private void constrainChildRect(LayoutParams coordinatorLayout$LayoutParams0, Rect rect0, int v, int v1) {
        int v2 = this.getWidth();
        int v3 = this.getHeight();
        int v4 = Math.max(this.getPaddingLeft() + coordinatorLayout$LayoutParams0.leftMargin, Math.min(rect0.left, v2 - this.getPaddingRight() - v - coordinatorLayout$LayoutParams0.rightMargin));
        int v5 = Math.max(this.getPaddingTop() + coordinatorLayout$LayoutParams0.topMargin, Math.min(rect0.top, v3 - this.getPaddingBottom() - v1 - coordinatorLayout$LayoutParams0.bottomMargin));
        rect0.set(v4, v5, v + v4, v1 + v5);
    }

    private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat windowInsetsCompat0) {
        if(windowInsetsCompat0.isConsumed()) {
            return windowInsetsCompat0;
        }
        int v1 = this.getChildCount();
        for(int v = 0; v < v1; ++v) {
            View view0 = this.getChildAt(v);
            if(ViewCompat.getFitsSystemWindows(view0) && ((LayoutParams)view0.getLayoutParams()).getBehavior() != null && windowInsetsCompat0.isConsumed()) {
                break;
            }
        }
        return windowInsetsCompat0;
    }

    public void dispatchDependentViewsChanged(@NonNull View view0) {
        List list0 = this.mChildDag.getIncomingEdges(view0);
        if(list0 != null && !list0.isEmpty()) {
            for(int v = 0; v < list0.size(); ++v) {
                boolean z = ((LayoutParams)((View)list0.get(v)).getLayoutParams()).getBehavior() == null;
            }
        }
    }

    public boolean doViewsOverlap(@NonNull View view0, @NonNull View view1) {
        boolean z = false;
        if(view0.getVisibility() == 0 && view1.getVisibility() == 0) {
            Rect rect0 = CoordinatorLayout.acquireTempRect();
            this.getChildRect(view0, view0.getParent() != this, rect0);
            Rect rect1 = CoordinatorLayout.acquireTempRect();
            this.getChildRect(view1, view1.getParent() != this, rect1);
            if(rect0.left <= rect1.right && rect0.top <= rect1.bottom && rect0.right >= rect1.left && rect0.bottom >= rect1.top) {
                z = true;
            }
            CoordinatorLayout.releaseTempRect(rect0);
            CoordinatorLayout.releaseTempRect(rect1);
            return z;
        }
        return false;
    }

    @Override  // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas0, View view0, long v) {
        boolean z = ((LayoutParams)view0.getLayoutParams()).mBehavior == null;
        return super.drawChild(canvas0, view0, v);
    }

    @Override  // android.view.ViewGroup
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] arr_v = this.getDrawableState();
        Drawable drawable0 = this.mStatusBarBackground;
        int v = 0;
        if(drawable0 != null && drawable0.isStateful()) {
            v = drawable0.setState(arr_v);
        }
        if(v != 0) {
            this.invalidate();
        }
    }

    void ensurePreDrawListener() {
        int v = this.getChildCount();
        boolean z = false;
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.hasDependencies(this.getChildAt(v1))) {
                z = true;
                break;
            }
        }
        if(z != this.mNeedsPreDrawListener) {
            if(z) {
                this.addPreDrawListener();
                return;
            }
            this.removePreDrawListener();
        }
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return this.generateLayoutParams(viewGroup$LayoutParams0);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(viewGroup$LayoutParams0 instanceof LayoutParams) {
            return new LayoutParams(((LayoutParams)viewGroup$LayoutParams0));
        }
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0)) : new LayoutParams(viewGroup$LayoutParams0);
    }

    void getChildRect(View view0, boolean z, Rect rect0) {
        if(!view0.isLayoutRequested() && view0.getVisibility() != 8) {
            if(z) {
                this.getDescendantRect(view0, rect0);
                return;
            }
            rect0.set(view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom());
            return;
        }
        rect0.setEmpty();
    }

    @NonNull
    public List getDependencies(@NonNull View view0) {
        List list0 = this.mChildDag.getOutgoingEdges(view0);
        this.mTempDependenciesList.clear();
        if(list0 != null) {
            this.mTempDependenciesList.addAll(list0);
        }
        return this.mTempDependenciesList;
    }

    @VisibleForTesting
    final List getDependencySortedChildren() {
        this.prepareChildren();
        return Collections.unmodifiableList(this.mDependencySortedChildren);
    }

    @NonNull
    public List getDependents(@NonNull View view0) {
        List list0 = this.mChildDag.getIncomingEdges(view0);
        this.mTempDependenciesList.clear();
        if(list0 != null) {
            this.mTempDependenciesList.addAll(list0);
        }
        return this.mTempDependenciesList;
    }

    void getDescendantRect(View view0, Rect rect0) {
        ViewGroupUtils.getDescendantRect(this, view0, rect0);
    }

    void getDesiredAnchoredChildRect(View view0, int v, Rect rect0, Rect rect1) {
        LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        int v1 = view0.getMeasuredWidth();
        int v2 = view0.getMeasuredHeight();
        this.getDesiredAnchoredChildRectWithoutConstraints(view0, v, rect0, rect1, coordinatorLayout$LayoutParams0, v1, v2);
        this.constrainChildRect(coordinatorLayout$LayoutParams0, rect1, v1, v2);
    }

    private void getDesiredAnchoredChildRectWithoutConstraints(View view0, int v, Rect rect0, Rect rect1, LayoutParams coordinatorLayout$LayoutParams0, int v1, int v2) {
        int v6;
        int v5;
        int v3 = GravityCompat.getAbsoluteGravity(CoordinatorLayout.resolveAnchoredChildGravity(coordinatorLayout$LayoutParams0.gravity), v);
        int v4 = GravityCompat.getAbsoluteGravity(CoordinatorLayout.resolveGravity(coordinatorLayout$LayoutParams0.anchorGravity), v);
        switch(v4 & 7) {
            case 1: {
                v5 = rect0.left + rect0.width() / 2;
                break;
            }
            case 5: {
                v5 = rect0.right;
                break;
            }
            default: {
                v5 = rect0.left;
            }
        }
        switch(v4 & 0x70) {
            case 16: {
                v6 = rect0.top + rect0.height() / 2;
                break;
            }
            case 80: {
                v6 = rect0.bottom;
                break;
            }
            default: {
                v6 = rect0.top;
            }
        }
        if((v3 & 7) == 1) {
            v5 -= v1 / 2;
        }
        else if((v3 & 7) != 5) {
            v5 -= v1;
        }
        if((v3 & 0x70) == 16) {
            v6 -= v2 / 2;
        }
        else if((v3 & 0x70) != 80) {
            v6 -= v2;
        }
        rect1.set(v5, v6, v1 + v5, v2 + v6);
    }

    private int getKeyline(int v) {
        int[] arr_v = this.mKeylines;
        if(arr_v == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + v);
            return 0;
        }
        if(v >= 0 && v < arr_v.length) {
            return arr_v[v];
        }
        Log.e("CoordinatorLayout", "Keyline index " + v + " out of range for " + this);
        return 0;
    }

    void getLastChildRect(View view0, Rect rect0) {
        rect0.set(((LayoutParams)view0.getLayoutParams()).getLastChildRect());
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public final WindowInsetsCompat getLastWindowInsets() {
        return this.mLastInsets;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    LayoutParams getResolvedLayoutParams(View view0) {
        LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(!coordinatorLayout$LayoutParams0.mBehaviorResolved) {
            if(view0 instanceof AttachedBehavior) {
                Behavior coordinatorLayout$Behavior0 = ((AttachedBehavior)view0).getBehavior();
                if(coordinatorLayout$Behavior0 == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                coordinatorLayout$LayoutParams0.setBehavior(coordinatorLayout$Behavior0);
                coordinatorLayout$LayoutParams0.mBehaviorResolved = true;
                return coordinatorLayout$LayoutParams0;
            }
            Class class0 = view0.getClass();
            DefaultBehavior coordinatorLayout$DefaultBehavior0 = null;
            while(class0 != null) {
                coordinatorLayout$DefaultBehavior0 = (DefaultBehavior)class0.getAnnotation(DefaultBehavior.class);
                if(coordinatorLayout$DefaultBehavior0 != null) {
                    break;
                }
                class0 = class0.getSuperclass();
            }
            if(coordinatorLayout$DefaultBehavior0 != null) {
                try {
                    coordinatorLayout$LayoutParams0.setBehavior(((Behavior)coordinatorLayout$DefaultBehavior0.value().getDeclaredConstructor().newInstance()));
                }
                catch(Exception exception0) {
                    Log.e("CoordinatorLayout", "Default behavior class " + coordinatorLayout$DefaultBehavior0.value().getName() + " could not be instantiated. Did you forget" + " a default constructor?", exception0);
                }
            }
            coordinatorLayout$LayoutParams0.mBehaviorResolved = true;
        }
        return coordinatorLayout$LayoutParams0;
    }

    @Nullable
    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    @Override  // android.view.View
    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), this.getPaddingTop() + this.getPaddingBottom());
    }

    @Override  // android.view.View
    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), this.getPaddingLeft() + this.getPaddingRight());
    }

    private void getTopSortedChildren(List list0) {
        list0.clear();
        boolean z = this.isChildrenDrawingOrderEnabled();
        int v = this.getChildCount();
        for(int v1 = v - 1; v1 >= 0; --v1) {
            list0.add(this.getChildAt((z ? this.getChildDrawingOrder(v, v1) : v1)));
        }
        Comparator comparator0 = CoordinatorLayout.TOP_SORTED_CHILDREN_COMPARATOR;
        if(comparator0 != null) {
            Collections.sort(list0, comparator0);
        }
    }

    private boolean hasDependencies(View view0) {
        return this.mChildDag.hasOutgoingEdges(view0);
    }

    public boolean isPointInChildBounds(@NonNull View view0, int v, int v1) {
        Rect rect0 = CoordinatorLayout.acquireTempRect();
        this.getDescendantRect(view0, rect0);
        try {
            return rect0.contains(v, v1);
        }
        finally {
            CoordinatorLayout.releaseTempRect(rect0);
        }
    }

    private void layoutChild(View view0, int v) {
        LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        Rect rect0 = CoordinatorLayout.acquireTempRect();
        rect0.set(this.getPaddingLeft() + coordinatorLayout$LayoutParams0.leftMargin, this.getPaddingTop() + coordinatorLayout$LayoutParams0.topMargin, this.getWidth() - this.getPaddingRight() - coordinatorLayout$LayoutParams0.rightMargin, this.getHeight() - this.getPaddingBottom() - coordinatorLayout$LayoutParams0.bottomMargin);
        if(this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this) && !ViewCompat.getFitsSystemWindows(view0)) {
            rect0.left += this.mLastInsets.getSystemWindowInsetLeft();
            rect0.top += this.mLastInsets.getSystemWindowInsetTop();
            rect0.right -= this.mLastInsets.getSystemWindowInsetRight();
            rect0.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
        }
        Rect rect1 = CoordinatorLayout.acquireTempRect();
        GravityCompat.apply(CoordinatorLayout.resolveGravity(coordinatorLayout$LayoutParams0.gravity), view0.getMeasuredWidth(), view0.getMeasuredHeight(), rect0, rect1, v);
        view0.layout(rect1.left, rect1.top, rect1.right, rect1.bottom);
        CoordinatorLayout.releaseTempRect(rect0);
        CoordinatorLayout.releaseTempRect(rect1);
    }

    private void layoutChildWithAnchor(View view0, View view1, int v) {
        Rect rect0 = CoordinatorLayout.acquireTempRect();
        Rect rect1 = CoordinatorLayout.acquireTempRect();
        try {
            this.getDescendantRect(view1, rect0);
            this.getDesiredAnchoredChildRect(view0, v, rect0, rect1);
            view0.layout(rect1.left, rect1.top, rect1.right, rect1.bottom);
        }
        finally {
            CoordinatorLayout.releaseTempRect(rect0);
            CoordinatorLayout.releaseTempRect(rect1);
        }
    }

    private void layoutChildWithKeyline(View view0, int v, int v1) {
        LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        int v2 = GravityCompat.getAbsoluteGravity(CoordinatorLayout.resolveKeylineGravity(coordinatorLayout$LayoutParams0.gravity), v1);
        int v3 = this.getWidth();
        int v4 = this.getHeight();
        int v5 = view0.getMeasuredWidth();
        int v6 = view0.getMeasuredHeight();
        if(v1 == 1) {
            v = v3 - v;
        }
        int v7 = this.getKeyline(v) - v5;
        int v8 = 0;
        switch(v2 & 7) {
            case 1: {
                v7 += v5 / 2;
                break;
            }
            case 5: {
                v7 += v5;
            }
        }
        switch(v2 & 0x70) {
            case 16: {
                v8 = v6 / 2;
                break;
            }
            case 80: {
                v8 = v6;
            }
        }
        int v9 = Math.max(this.getPaddingLeft() + coordinatorLayout$LayoutParams0.leftMargin, Math.min(v7, v3 - this.getPaddingRight() - v5 - coordinatorLayout$LayoutParams0.rightMargin));
        int v10 = Math.max(this.getPaddingTop() + coordinatorLayout$LayoutParams0.topMargin, Math.min(v8, v4 - this.getPaddingBottom() - v6 - coordinatorLayout$LayoutParams0.bottomMargin));
        view0.layout(v9, v10, v5 + v9, v6 + v10);
    }

    private void offsetChildByInset(View view0, Rect rect0, int v) {
        boolean z2;
        boolean z1;
        if(!ViewCompat.isLaidOut(view0)) {
            return;
        }
        if(view0.getWidth() > 0 && view0.getHeight() > 0) {
            LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            Rect rect1 = CoordinatorLayout.acquireTempRect();
            Rect rect2 = CoordinatorLayout.acquireTempRect();
            rect2.set(view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom());
            boolean z = coordinatorLayout$LayoutParams0.getBehavior() == null;
            rect1.set(rect2);
            CoordinatorLayout.releaseTempRect(rect2);
            if(rect1.isEmpty()) {
                CoordinatorLayout.releaseTempRect(rect1);
                return;
            }
            int v1 = GravityCompat.getAbsoluteGravity(coordinatorLayout$LayoutParams0.dodgeInsetEdges, v);
            if((v1 & 0x30) == 0x30) {
                int v2 = rect1.top - coordinatorLayout$LayoutParams0.topMargin - coordinatorLayout$LayoutParams0.mInsetOffsetY;
                if(v2 < rect0.top) {
                    this.setInsetOffsetY(view0, rect0.top - v2);
                    z1 = true;
                }
                else {
                    z1 = false;
                }
            }
            else {
                z1 = false;
            }
            if((v1 & 80) == 80) {
                int v3 = this.getHeight() - rect1.bottom - coordinatorLayout$LayoutParams0.bottomMargin + coordinatorLayout$LayoutParams0.mInsetOffsetY;
                if(v3 < rect0.bottom) {
                    this.setInsetOffsetY(view0, v3 - rect0.bottom);
                    z1 = true;
                }
            }
            if(!z1) {
                this.setInsetOffsetY(view0, 0);
            }
            if((v1 & 3) == 3) {
                int v4 = rect1.left - coordinatorLayout$LayoutParams0.leftMargin - coordinatorLayout$LayoutParams0.mInsetOffsetX;
                if(v4 < rect0.left) {
                    this.setInsetOffsetX(view0, rect0.left - v4);
                    z2 = true;
                }
                else {
                    z2 = false;
                }
            }
            else {
                z2 = false;
            }
            if((v1 & 5) == 5) {
                int v5 = this.getWidth() - rect1.right - coordinatorLayout$LayoutParams0.rightMargin + coordinatorLayout$LayoutParams0.mInsetOffsetX;
                if(v5 < rect0.right) {
                    this.setInsetOffsetX(view0, v5 - rect0.right);
                    z2 = true;
                }
            }
            if(!z2) {
                this.setInsetOffsetX(view0, 0);
            }
            CoordinatorLayout.releaseTempRect(rect1);
        }
    }

    void offsetChildToAnchor(View view0, int v) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(((LayoutParams)viewGroup$LayoutParams0).mAnchorView != null) {
            Rect rect0 = CoordinatorLayout.acquireTempRect();
            Rect rect1 = CoordinatorLayout.acquireTempRect();
            Rect rect2 = CoordinatorLayout.acquireTempRect();
            this.getDescendantRect(((LayoutParams)viewGroup$LayoutParams0).mAnchorView, rect0);
            boolean z = false;
            this.getChildRect(view0, false, rect1);
            int v1 = view0.getMeasuredWidth();
            int v2 = view0.getMeasuredHeight();
            this.getDesiredAnchoredChildRectWithoutConstraints(view0, v, rect0, rect2, ((LayoutParams)viewGroup$LayoutParams0), v1, v2);
            if(rect2.left != rect1.left || rect2.top != rect1.top) {
                z = true;
            }
            this.constrainChildRect(((LayoutParams)viewGroup$LayoutParams0), rect2, v1, v2);
            int v3 = rect2.left - rect1.left;
            int v4 = rect2.top - rect1.top;
            if(v3 != 0) {
                ViewCompat.offsetLeftAndRight(view0, v3);
            }
            if(v4 != 0) {
                ViewCompat.offsetTopAndBottom(view0, v4);
            }
            if(z) {
                boolean z1 = ((LayoutParams)viewGroup$LayoutParams0).getBehavior() == null;
            }
            CoordinatorLayout.releaseTempRect(rect0);
            CoordinatorLayout.releaseTempRect(rect1);
            CoordinatorLayout.releaseTempRect(rect2);
        }
    }

    @Override  // android.view.ViewGroup
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.resetTouchBehaviors(false);
        if(this.mNeedsPreDrawListener) {
            if(this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener(this);
            }
            this.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        if(this.mLastInsets == null && ViewCompat.getFitsSystemWindows(this)) {
            ViewCompat.requestApplyInsets(this);
        }
        this.mIsAttachedToWindow = true;
    }

    final void onChildViewsChanged(int v) {
        int v6;
        int v1 = ViewCompat.getLayoutDirection(this);
        int v2 = this.mDependencySortedChildren.size();
        Rect rect0 = CoordinatorLayout.acquireTempRect();
        Rect rect1 = CoordinatorLayout.acquireTempRect();
        Rect rect2 = CoordinatorLayout.acquireTempRect();
        int v3 = 0;
        while(v3 < v2) {
            View view0 = (View)this.mDependencySortedChildren.get(v3);
            LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(v != 0 || view0.getVisibility() != 8) {
                for(int v4 = 0; v4 < v3; ++v4) {
                    View view1 = (View)this.mDependencySortedChildren.get(v4);
                    if(coordinatorLayout$LayoutParams0.mAnchorDirectChild == view1) {
                        this.offsetChildToAnchor(view0, v1);
                    }
                }
                this.getChildRect(view0, true, rect1);
                if(coordinatorLayout$LayoutParams0.insetEdge != 0 && !rect1.isEmpty()) {
                    int v5 = GravityCompat.getAbsoluteGravity(coordinatorLayout$LayoutParams0.insetEdge, v1);
                    switch(v5 & 0x70) {
                        case 0x30: {
                            rect0.top = Math.max(rect0.top, rect1.bottom);
                            break;
                        }
                        case 80: {
                            rect0.bottom = Math.max(rect0.bottom, this.getHeight() - rect1.top);
                        }
                    }
                    switch(v5 & 7) {
                        case 3: {
                            rect0.left = Math.max(rect0.left, rect1.right);
                            break;
                        }
                        case 5: {
                            rect0.right = Math.max(rect0.right, this.getWidth() - rect1.left);
                        }
                    }
                }
                if(coordinatorLayout$LayoutParams0.dodgeInsetEdges != 0 && view0.getVisibility() == 0) {
                    this.offsetChildByInset(view0, rect0, v1);
                }
                if(v == 2) {
                    v6 = v3 + 1;
                label_39:
                    while(v6 < v2) {
                        boolean z = ((LayoutParams)((View)this.mDependencySortedChildren.get(v6)).getLayoutParams()).getBehavior() == null;
                        ++v6;
                    }
                }
                else {
                    this.getLastChildRect(view0, rect2);
                    if(!rect2.equals(rect1)) {
                        this.recordLastChildRect(view0, rect1);
                        v6 = v3 + 1;
                        goto label_39;
                    }
                }
            }
            ++v3;
        }
        CoordinatorLayout.releaseTempRect(rect0);
        CoordinatorLayout.releaseTempRect(rect1);
        CoordinatorLayout.releaseTempRect(rect2);
    }

    @Override  // android.view.ViewGroup
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.resetTouchBehaviors(false);
        if(this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            this.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        View view0 = this.mNestedScrollingTarget;
        if(view0 != null) {
            this.onStopNestedScroll(view0);
        }
        this.mIsAttachedToWindow = false;
    }

    @Override  // android.view.View
    public void onDraw(Canvas canvas0) {
        super.onDraw(canvas0);
        if(this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            int v = this.mLastInsets == null ? 0 : this.mLastInsets.getSystemWindowInsetTop();
            if(v > 0) {
                this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), v);
                this.mStatusBarBackground.draw(canvas0);
            }
        }
    }

    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        if(v == 0) {
            this.resetTouchBehaviors(true);
        }
        boolean z = this.performIntercept(motionEvent0, 0);
        if(v == 1 || v == 3) {
            this.resetTouchBehaviors(true);
        }
        return z;
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v4 = ViewCompat.getLayoutDirection(this);
        int v5 = this.mDependencySortedChildren.size();
        for(int v6 = 0; v6 < v5; ++v6) {
            View view0 = (View)this.mDependencySortedChildren.get(v6);
            if(view0.getVisibility() != 8) {
                boolean z1 = ((LayoutParams)view0.getLayoutParams()).getBehavior() == null;
                this.onLayoutChild(view0, v4);
            }
        }
    }

    public void onLayoutChild(@NonNull View view0, int v) {
        LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(coordinatorLayout$LayoutParams0.checkAnchorChanged()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        if(coordinatorLayout$LayoutParams0.mAnchorView != null) {
            this.layoutChildWithAnchor(view0, coordinatorLayout$LayoutParams0.mAnchorView, v);
            return;
        }
        if(coordinatorLayout$LayoutParams0.keyline >= 0) {
            this.layoutChildWithKeyline(view0, coordinatorLayout$LayoutParams0.keyline, v);
            return;
        }
        this.layoutChild(view0, v);
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v31;
        int v30;
        int v29;
        LayoutParams coordinatorLayout$LayoutParams1;
        int v28;
        int v27;
        int v22;
        int v21;
        int v18;
        this.prepareChildren();
        this.ensurePreDrawListener();
        int v2 = this.getPaddingLeft();
        int v3 = this.getPaddingTop();
        int v4 = this.getPaddingRight();
        int v5 = this.getPaddingBottom();
        int v6 = ViewCompat.getLayoutDirection(this);
        int v7 = View.MeasureSpec.getMode(v);
        int v8 = View.MeasureSpec.getSize(v);
        int v9 = View.MeasureSpec.getMode(v1);
        int v10 = View.MeasureSpec.getSize(v1);
        int v11 = this.getSuggestedMinimumWidth();
        int v12 = this.getSuggestedMinimumHeight();
        boolean z = this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this);
        int v13 = this.mDependencySortedChildren.size();
        int v14 = v11;
        int v15 = v12;
        int v16 = 0;
        int v17 = 0;
        while(v17 < v13) {
            View view0 = (View)this.mDependencySortedChildren.get(v17);
            if(view0.getVisibility() == 8) {
                v18 = v17;
            }
            else {
                LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(coordinatorLayout$LayoutParams0.keyline < 0 || v7 == 0) {
                    v21 = v15;
                }
                else {
                    int v19 = this.getKeyline(coordinatorLayout$LayoutParams0.keyline);
                    int v20 = GravityCompat.getAbsoluteGravity(CoordinatorLayout.resolveKeylineGravity(coordinatorLayout$LayoutParams0.gravity), v6) & 7;
                    v21 = v15;
                    if(v20 == 3 && v6 != 1 || v20 == 5 && v6 == 1) {
                        v22 = Math.max(0, v8 - v4 - v19);
                        goto label_37;
                    }
                    else if(v20 == 5 && v6 != 1 || v20 == 3 && v6 == 1) {
                        v22 = Math.max(0, v19 - v2);
                        goto label_37;
                    }
                }
                v22 = 0;
            label_37:
                if(!z || ViewCompat.getFitsSystemWindows(view0)) {
                    v27 = v;
                    v28 = v1;
                }
                else {
                    int v23 = this.mLastInsets.getSystemWindowInsetLeft();
                    int v24 = this.mLastInsets.getSystemWindowInsetRight();
                    int v25 = this.mLastInsets.getSystemWindowInsetTop();
                    int v26 = this.mLastInsets.getSystemWindowInsetBottom();
                    v27 = View.MeasureSpec.makeMeasureSpec(v8 - (v23 + v24), v7);
                    v28 = View.MeasureSpec.makeMeasureSpec(v10 - (v25 + v26), v9);
                }
                if(coordinatorLayout$LayoutParams0.getBehavior() == null) {
                    coordinatorLayout$LayoutParams1 = coordinatorLayout$LayoutParams0;
                    v30 = v16;
                    v31 = v14;
                    v29 = v21;
                }
                else {
                    coordinatorLayout$LayoutParams1 = coordinatorLayout$LayoutParams0;
                    v29 = v21;
                    v30 = v16;
                    v31 = v14;
                }
                v18 = v17;
                this.onMeasureChild(view0, v27, v22, v28, 0);
                int v32 = Math.max(v31, v2 + v4 + view0.getMeasuredWidth() + coordinatorLayout$LayoutParams1.leftMargin + coordinatorLayout$LayoutParams1.rightMargin);
                int v33 = Math.max(v29, v3 + v5 + view0.getMeasuredHeight() + coordinatorLayout$LayoutParams1.topMargin + coordinatorLayout$LayoutParams1.bottomMargin);
                v14 = v32;
                v16 = View.combineMeasuredStates(v30, view0.getMeasuredState());
                v15 = v33;
            }
            v17 = v18 + 1;
        }
        this.setMeasuredDimension(View.resolveSizeAndState(v14, v, 0xFF000000 & v16), View.resolveSizeAndState(v15, v1, v16 << 16));
    }

    public void onMeasureChild(View view0, int v, int v1, int v2, int v3) {
        this.measureChildWithMargins(view0, v, v1, v2, v3);
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view0, float f, float f1, boolean z) {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view1 = this.getChildAt(v1);
            if(view1.getVisibility() != 8) {
                boolean z1 = !((LayoutParams)view1.getLayoutParams()).isNestedScrollAccepted(0);
            }
        }
        return false;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view0, float f, float f1) {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view1 = this.getChildAt(v1);
            if(view1.getVisibility() != 8) {
                boolean z = !((LayoutParams)view1.getLayoutParams()).isNestedScrollAccepted(0);
            }
        }
        return false;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view0, int v, int v1, int[] arr_v) {
        this.onNestedPreScroll(view0, v, v1, arr_v, 0);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view0, int v, int v1, int[] arr_v, int v2) {
        int v3 = this.getChildCount();
        boolean z = false;
        int v5 = 0;
        int v6 = 0;
        for(int v4 = 0; v4 < v3; ++v4) {
            View view1 = this.getChildAt(v4);
            if(view1.getVisibility() != 8) {
                LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view1.getLayoutParams();
                if(coordinatorLayout$LayoutParams0.isNestedScrollAccepted(v2)) {
                    Behavior coordinatorLayout$Behavior0 = coordinatorLayout$LayoutParams0.getBehavior();
                    if(coordinatorLayout$Behavior0 != null) {
                        this.mTempIntPair[1] = 0;
                        this.mTempIntPair[0] = 0;
                        coordinatorLayout$Behavior0.onNestedPreScroll(this, view1, view0, v, v1, this.mTempIntPair, v2);
                        v5 = v <= 0 ? Math.min(v5, this.mTempIntPair[0]) : Math.max(v5, this.mTempIntPair[0]);
                        v6 = v1 <= 0 ? Math.min(v6, this.mTempIntPair[1]) : Math.max(v6, this.mTempIntPair[1]);
                        z = true;
                    }
                }
            }
        }
        arr_v[0] = v5;
        arr_v[1] = v6;
        if(z) {
            this.onChildViewsChanged(1);
        }
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3) {
        this.onNestedScroll(view0, v, v1, v2, v3, 0);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3, int v4) {
        int v5 = this.getChildCount();
        boolean z = false;
        for(int v6 = 0; v6 < v5; ++v6) {
            View view1 = this.getChildAt(v6);
            if(view1.getVisibility() != 8) {
                LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view1.getLayoutParams();
                if(coordinatorLayout$LayoutParams0.isNestedScrollAccepted(v4) && coordinatorLayout$LayoutParams0.getBehavior() != null) {
                    z = true;
                }
            }
        }
        if(z) {
            this.onChildViewsChanged(1);
        }
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view0, View view1, int v) {
        this.onNestedScrollAccepted(view0, view1, v, 0);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view0, View view1, int v, int v1) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view0, view1, v, v1);
        this.mNestedScrollingTarget = view1;
        int v2 = this.getChildCount();
        for(int v3 = 0; v3 < v2; ++v3) {
            boolean z = !((LayoutParams)this.getChildAt(v3).getLayoutParams()).isNestedScrollAccepted(v1);
        }
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        SparseArray sparseArray0 = ((SavedState)parcelable0).behaviorStates;
        int v1 = this.getChildCount();
        for(int v = 0; v < v1; ++v) {
            View view0 = this.getChildAt(v);
            int v2 = view0.getId();
            if(v2 != -1 && this.getResolvedLayoutParams(view0).getBehavior() != null) {
                boolean z = ((Parcelable)sparseArray0.get(v2)) == null;
            }
        }
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        SparseArray sparseArray0 = new SparseArray();
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            int v2 = view0.getId();
            Behavior coordinatorLayout$Behavior0 = ((LayoutParams)view0.getLayoutParams()).getBehavior();
            if(v2 != -1 && coordinatorLayout$Behavior0 != null) {
                Parcelable parcelable1 = coordinatorLayout$Behavior0.onSaveInstanceState(this, view0);
                if(parcelable1 != null) {
                    sparseArray0.append(v2, parcelable1);
                }
            }
        }
        parcelable0.behaviorStates = sparseArray0;
        return parcelable0;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view0, View view1, int v) {
        return this.onStartNestedScroll(view0, view1, v, 0);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view0, View view1, int v, int v1) {
        int v2 = this.getChildCount();
        boolean z = false;
        for(int v3 = 0; v3 < v2; ++v3) {
            View view2 = this.getChildAt(v3);
            if(view2.getVisibility() != 8) {
                LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view2.getLayoutParams();
                Behavior coordinatorLayout$Behavior0 = coordinatorLayout$LayoutParams0.getBehavior();
                if(coordinatorLayout$Behavior0 == null) {
                    coordinatorLayout$LayoutParams0.setNestedScrollAccepted(v1, false);
                }
                else {
                    boolean z1 = coordinatorLayout$Behavior0.onStartNestedScroll(this, view2, view0, view1, v, v1);
                    coordinatorLayout$LayoutParams0.setNestedScrollAccepted(v1, z1);
                    z |= z1;
                }
            }
        }
        return z;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view0) {
        this.onStopNestedScroll(view0, 0);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View view0, int v) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view0, v);
        int v1 = this.getChildCount();
        for(int v2 = 0; v2 < v1; ++v2) {
            LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)this.getChildAt(v2).getLayoutParams();
            if(coordinatorLayout$LayoutParams0.isNestedScrollAccepted(v)) {
                coordinatorLayout$LayoutParams0.resetNestedScroll(v);
                coordinatorLayout$LayoutParams0.resetChangedAfterNestedScroll();
            }
        }
        this.mNestedScrollingTarget = null;
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        boolean z1;
        boolean z;
        int v = motionEvent0.getActionMasked();
        if(this.mBehaviorTouchView == null) {
            z = this.performIntercept(motionEvent0, 1);
            if(z) {
                z1 = ((LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior() == null ? false : false;
            }
            else {
                z1 = false;
            }
        }
        else {
            z = false;
            z1 = ((LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior() == null ? false : false;
        }
        MotionEvent motionEvent1 = null;
        if(this.mBehaviorTouchView == null) {
            z1 = super.onTouchEvent(motionEvent0);
        }
        else if(z) {
            long v1 = SystemClock.uptimeMillis();
            motionEvent1 = MotionEvent.obtain(v1, v1, 3, 0.0f, 0.0f, 0);
            super.onTouchEvent(motionEvent1);
        }
        if(motionEvent1 != null) {
            motionEvent1.recycle();
        }
        if(v == 1 || v == 3) {
            this.resetTouchBehaviors(false);
        }
        return z1;
    }

    static Behavior parseBehavior(Context context0, AttributeSet attributeSet0, String s) {
        if(TextUtils.isEmpty(s)) {
            return null;
        }
        if(s.startsWith(".")) {
            s = "com.esotericsoftware.gloomhavenhelper" + s;
        }
        else if(s.indexOf(46) < 0 && !TextUtils.isEmpty(CoordinatorLayout.WIDGET_PACKAGE_NAME)) {
            s = CoordinatorLayout.WIDGET_PACKAGE_NAME + '.' + s;
        }
        try {
            Map map0 = (Map)CoordinatorLayout.sConstructors.get();
            if(map0 == null) {
                map0 = new HashMap();
                CoordinatorLayout.sConstructors.set(map0);
            }
            Constructor constructor0 = (Constructor)map0.get(s);
            if(constructor0 == null) {
                constructor0 = context0.getClassLoader().loadClass(s).getConstructor(CoordinatorLayout.CONSTRUCTOR_PARAMS);
                constructor0.setAccessible(true);
                map0.put(s, constructor0);
            }
            return (Behavior)constructor0.newInstance(context0, attributeSet0);
        }
        catch(Exception exception0) {
            throw new RuntimeException("Could not inflate Behavior subclass " + s, exception0);
        }
    }

    private boolean performIntercept(MotionEvent motionEvent0, int v) {
        int v1 = motionEvent0.getActionMasked();
        List list0 = this.mTempList1;
        this.getTopSortedChildren(list0);
        int v2 = list0.size();
        MotionEvent motionEvent1 = null;
        boolean z = false;
        for(int v3 = 0; v3 < v2; ++v3) {
            View view0 = (View)list0.get(v3);
            LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            Behavior coordinatorLayout$Behavior0 = coordinatorLayout$LayoutParams0.getBehavior();
            if(!z || v1 == 0) {
                boolean z1 = coordinatorLayout$LayoutParams0.didBlockInteraction();
                boolean z2 = coordinatorLayout$LayoutParams0.isBlockingInteractionBelow(this, view0);
                z = z2 && !z1;
                if(!z2 || z) {
                    continue;
                }
                break;
            }
            else if(coordinatorLayout$Behavior0 != null && motionEvent1 == null) {
                long v4 = SystemClock.uptimeMillis();
                motionEvent1 = MotionEvent.obtain(v4, v4, 3, 0.0f, 0.0f, 0);
            }
        }
        list0.clear();
        return false;
    }

    private void prepareChildren() {
        this.mDependencySortedChildren.clear();
        this.mChildDag.clear();
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            LayoutParams coordinatorLayout$LayoutParams0 = this.getResolvedLayoutParams(view0);
            coordinatorLayout$LayoutParams0.findAnchorView(this, view0);
            this.mChildDag.addNode(view0);
            for(int v2 = 0; v2 < v; ++v2) {
                if(v2 != v1) {
                    View view1 = this.getChildAt(v2);
                    if(coordinatorLayout$LayoutParams0.dependsOn(this, view0, view1)) {
                        if(!this.mChildDag.contains(view1)) {
                            this.mChildDag.addNode(view1);
                        }
                        this.mChildDag.addEdge(view1, view0);
                    }
                }
            }
        }
        ArrayList arrayList0 = this.mChildDag.getSortedList();
        this.mDependencySortedChildren.addAll(arrayList0);
        Collections.reverse(this.mDependencySortedChildren);
    }

    void recordLastChildRect(View view0, Rect rect0) {
        ((LayoutParams)view0.getLayoutParams()).setLastChildRect(rect0);
    }

    private static void releaseTempRect(@NonNull Rect rect0) {
        rect0.setEmpty();
        CoordinatorLayout.sRectPool.release(rect0);
    }

    void removePreDrawListener() {
        if(this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            this.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }

    @Override  // android.view.ViewGroup
    public boolean requestChildRectangleOnScreen(View view0, Rect rect0, boolean z) {
        boolean z1 = ((LayoutParams)view0.getLayoutParams()).getBehavior() == null;
        return super.requestChildRectangleOnScreen(view0, rect0, z);
    }

    @Override  // android.view.ViewGroup
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if(z && !this.mDisallowInterceptReset) {
            this.resetTouchBehaviors(false);
            this.mDisallowInterceptReset = true;
        }
    }

    private void resetTouchBehaviors(boolean z) {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            if(((LayoutParams)this.getChildAt(v1).getLayoutParams()).getBehavior() != null) {
                long v2 = SystemClock.uptimeMillis();
                MotionEvent.obtain(v2, v2, 3, 0.0f, 0.0f, 0).recycle();
            }
        }
        for(int v3 = 0; v3 < v; ++v3) {
            ((LayoutParams)this.getChildAt(v3).getLayoutParams()).resetTouchBehaviorTracking();
        }
        this.mBehaviorTouchView = null;
        this.mDisallowInterceptReset = false;
    }

    private static int resolveAnchoredChildGravity(int v) {
        return v == 0 ? 17 : v;
    }

    private static int resolveGravity(int v) {
        if((v & 7) == 0) {
            v |= 0x800003;
        }
        return (v & 0x70) == 0 ? v | 0x30 : v;
    }

    private static int resolveKeylineGravity(int v) {
        return v == 0 ? 0x800035 : v;
    }

    @Override  // android.view.View
    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        this.setupForInsets();
    }

    private void setInsetOffsetX(View view0, int v) {
        LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(coordinatorLayout$LayoutParams0.mInsetOffsetX != v) {
            ViewCompat.offsetLeftAndRight(view0, v - coordinatorLayout$LayoutParams0.mInsetOffsetX);
            coordinatorLayout$LayoutParams0.mInsetOffsetX = v;
        }
    }

    private void setInsetOffsetY(View view0, int v) {
        LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(coordinatorLayout$LayoutParams0.mInsetOffsetY != v) {
            ViewCompat.offsetTopAndBottom(view0, v - coordinatorLayout$LayoutParams0.mInsetOffsetY);
            coordinatorLayout$LayoutParams0.mInsetOffsetY = v;
        }
    }

    @Override  // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener viewGroup$OnHierarchyChangeListener0) {
        this.mOnHierarchyChangeListener = viewGroup$OnHierarchyChangeListener0;
    }

    public void setStatusBarBackground(@Nullable Drawable drawable0) {
        Drawable drawable1 = null;
        Drawable drawable2 = this.mStatusBarBackground;
        if(drawable2 != drawable0) {
            if(drawable2 != null) {
                drawable2.setCallback(null);
            }
            if(drawable0 != null) {
                drawable1 = drawable0.mutate();
            }
            this.mStatusBarBackground = drawable1;
            Drawable drawable3 = this.mStatusBarBackground;
            if(drawable3 != null) {
                if(drawable3.isStateful()) {
                    this.mStatusBarBackground.setState(this.getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection(this));
                this.mStatusBarBackground.setVisible(this.getVisibility() == 0, false);
                this.mStatusBarBackground.setCallback(this);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setStatusBarBackgroundColor(@ColorInt int v) {
        this.setStatusBarBackground(new ColorDrawable(v));
    }

    public void setStatusBarBackgroundResource(@DrawableRes int v) {
        this.setStatusBarBackground((v == 0 ? null : ContextCompat.getDrawable(this.getContext(), v)));
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
        super.setVisibility(v);
        if(this.mStatusBarBackground != null && this.mStatusBarBackground.isVisible() != (v == 0)) {
            this.mStatusBarBackground.setVisible(v == 0, false);
        }
    }

    // 检测为 Lambda 实现
    final WindowInsetsCompat setWindowInsets(WindowInsetsCompat windowInsetsCompat0) [...]

    private void setupForInsets() {
        if(Build.VERSION.SDK_INT < 21) {
            return;
        }
        if(ViewCompat.getFitsSystemWindows(this)) {
            if(this.mApplyWindowInsetsListener == null) {
                this.mApplyWindowInsetsListener = (/* 缺少LAMBDA参数 */, WindowInsetsCompat windowInsetsCompat0) -> {
                    if(!ObjectsCompat.equals(CoordinatorLayout.this.mLastInsets, windowInsetsCompat0)) {
                        CoordinatorLayout.this.mLastInsets = windowInsetsCompat0;
                        boolean z = true;
                        CoordinatorLayout.this.mDrawStatusBarBackground = windowInsetsCompat0 != null && windowInsetsCompat0.getSystemWindowInsetTop() > 0;
                        if(CoordinatorLayout.this.mDrawStatusBarBackground || CoordinatorLayout.this.getBackground() != null) {
                            z = false;
                        }
                        CoordinatorLayout.this.setWillNotDraw(z);
                        windowInsetsCompat0 = CoordinatorLayout.this.dispatchApplyWindowInsetsToBehaviors(windowInsetsCompat0);
                        CoordinatorLayout.this.requestLayout();
                    }
                    return windowInsetsCompat0;
                };
            }
            ViewCompat.setOnApplyWindowInsetsListener(this, this.mApplyWindowInsetsListener);
            this.setSystemUiVisibility(0x500);
            return;
        }
        ViewCompat.setOnApplyWindowInsetsListener(this, null);

        class androidx.coordinatorlayout.widget.CoordinatorLayout.1 implements OnApplyWindowInsetsListener {
            @Override  // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
                return CoordinatorLayout.this.setWindowInsets(windowInsetsCompat0);
            }
        }

    }

    @Override  // android.view.View
    protected boolean verifyDrawable(Drawable drawable0) {
        return super.verifyDrawable(drawable0) || drawable0 == this.mStatusBarBackground;
    }
}

