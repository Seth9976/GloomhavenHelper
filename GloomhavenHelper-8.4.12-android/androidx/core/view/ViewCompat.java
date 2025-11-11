package androidx.core.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View.DragShadowBuilder;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.View.OnUnhandledKeyEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.collection.ArrayMap;
import androidx.core.R.id;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewCompat {
    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusDirection {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusRealDirection {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusRelativeDirection {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NestedScrollType {
    }

    public interface OnUnhandledKeyEventListenerCompat {
        boolean onUnhandledKeyEvent(View arg1, KeyEvent arg2);
    }

    @RequiresApi(28)
    static class OnUnhandledKeyEventListenerWrapper implements View.OnUnhandledKeyEventListener {
        private OnUnhandledKeyEventListenerCompat mCompatListener;

        OnUnhandledKeyEventListenerWrapper(OnUnhandledKeyEventListenerCompat viewCompat$OnUnhandledKeyEventListenerCompat0) {
            this.mCompatListener = viewCompat$OnUnhandledKeyEventListenerCompat0;
        }

        @Override  // android.view.View$OnUnhandledKeyEventListener
        public boolean onUnhandledKeyEvent(View view0, KeyEvent keyEvent0) {
            return this.mCompatListener.onUnhandledKeyEvent(view0, keyEvent0);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollAxis {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollIndicators {
    }

    static class UnhandledKeyEventManager {
        private SparseArray mCapturedKeys;
        private WeakReference mLastDispatchedPreViewKeyEvent;
        @Nullable
        private WeakHashMap mViewsContainingListeners;
        private static final ArrayList sViewsWithListeners;

        static {
            UnhandledKeyEventManager.sViewsWithListeners = new ArrayList();
        }

        UnhandledKeyEventManager() {
            this.mViewsContainingListeners = null;
            this.mCapturedKeys = null;
            this.mLastDispatchedPreViewKeyEvent = null;
        }

        static UnhandledKeyEventManager at(View view0) {
            UnhandledKeyEventManager viewCompat$UnhandledKeyEventManager0 = (UnhandledKeyEventManager)view0.getTag(id.tag_unhandled_key_event_manager);
            if(viewCompat$UnhandledKeyEventManager0 == null) {
                viewCompat$UnhandledKeyEventManager0 = new UnhandledKeyEventManager();
                view0.setTag(id.tag_unhandled_key_event_manager, viewCompat$UnhandledKeyEventManager0);
            }
            return viewCompat$UnhandledKeyEventManager0;
        }

        boolean dispatch(View view0, KeyEvent keyEvent0) {
            if(keyEvent0.getAction() == 0) {
                this.recalcViewsWithUnhandled();
            }
            View view1 = this.dispatchInOrder(view0, keyEvent0);
            if(keyEvent0.getAction() == 0) {
                int v = keyEvent0.getKeyCode();
                if(view1 != null && !KeyEvent.isModifierKey(v)) {
                    this.getCapturedKeys().put(v, new WeakReference(view1));
                }
            }
            return view1 != null;
        }

        @Nullable
        private View dispatchInOrder(View view0, KeyEvent keyEvent0) {
            if(this.mViewsContainingListeners != null && this.mViewsContainingListeners.containsKey(view0)) {
                if(view0 instanceof ViewGroup) {
                    for(int v = ((ViewGroup)view0).getChildCount() - 1; v >= 0; --v) {
                        View view1 = this.dispatchInOrder(((ViewGroup)view0).getChildAt(v), keyEvent0);
                        if(view1 != null) {
                            return view1;
                        }
                    }
                }
                return this.onUnhandledKeyEvent(view0, keyEvent0) ? view0 : null;
            }
            return null;
        }

        private SparseArray getCapturedKeys() {
            if(this.mCapturedKeys == null) {
                this.mCapturedKeys = new SparseArray();
            }
            return this.mCapturedKeys;
        }

        private boolean onUnhandledKeyEvent(@NonNull View view0, @NonNull KeyEvent keyEvent0) {
            ArrayList arrayList0 = (ArrayList)view0.getTag(id.tag_unhandled_key_listeners);
            if(arrayList0 != null) {
                for(int v = arrayList0.size() - 1; v >= 0; --v) {
                    if(((OnUnhandledKeyEventListenerCompat)arrayList0.get(v)).onUnhandledKeyEvent(view0, keyEvent0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        boolean preDispatch(KeyEvent keyEvent0) {
            if(this.mLastDispatchedPreViewKeyEvent != null && this.mLastDispatchedPreViewKeyEvent.get() == keyEvent0) {
                return false;
            }
            this.mLastDispatchedPreViewKeyEvent = new WeakReference(keyEvent0);
            WeakReference weakReference0 = null;
            SparseArray sparseArray0 = this.getCapturedKeys();
            if(keyEvent0.getAction() == 1) {
                int v = sparseArray0.indexOfKey(keyEvent0.getKeyCode());
                if(v >= 0) {
                    weakReference0 = (WeakReference)sparseArray0.valueAt(v);
                    sparseArray0.removeAt(v);
                }
            }
            if(weakReference0 == null) {
                weakReference0 = (WeakReference)sparseArray0.get(keyEvent0.getKeyCode());
            }
            if(weakReference0 != null) {
                View view0 = (View)weakReference0.get();
                if(view0 != null && ViewCompat.isAttachedToWindow(view0)) {
                    this.onUnhandledKeyEvent(view0, keyEvent0);
                }
                return true;
            }
            return false;
        }

        private void recalcViewsWithUnhandled() {
            WeakHashMap weakHashMap0 = this.mViewsContainingListeners;
            if(weakHashMap0 != null) {
                weakHashMap0.clear();
            }
            if(UnhandledKeyEventManager.sViewsWithListeners.isEmpty()) {
                return;
            }
            synchronized(UnhandledKeyEventManager.sViewsWithListeners) {
                if(this.mViewsContainingListeners == null) {
                    this.mViewsContainingListeners = new WeakHashMap();
                }
                for(int v1 = UnhandledKeyEventManager.sViewsWithListeners.size() - 1; v1 >= 0; --v1) {
                    View view0 = (View)((WeakReference)UnhandledKeyEventManager.sViewsWithListeners.get(v1)).get();
                    if(view0 == null) {
                        UnhandledKeyEventManager.sViewsWithListeners.remove(v1);
                    }
                    else {
                        this.mViewsContainingListeners.put(view0, Boolean.TRUE);
                        for(ViewParent viewParent0 = view0.getParent(); viewParent0 instanceof View; viewParent0 = viewParent0.getParent()) {
                            this.mViewsContainingListeners.put(((View)viewParent0), Boolean.TRUE);
                        }
                    }
                }
            }
        }

        static void registerListeningView(View view0) {
            synchronized(UnhandledKeyEventManager.sViewsWithListeners) {
                for(Object object0: UnhandledKeyEventManager.sViewsWithListeners) {
                    if(((WeakReference)object0).get() == view0) {
                        return;
                    }
                    if(false) {
                        break;
                    }
                }
                WeakReference weakReference0 = new WeakReference(view0);
                UnhandledKeyEventManager.sViewsWithListeners.add(weakReference0);
            }
        }

        static void unregisterListeningView(View view0) {
            synchronized(UnhandledKeyEventManager.sViewsWithListeners) {
                for(int v1 = 0; v1 < UnhandledKeyEventManager.sViewsWithListeners.size(); ++v1) {
                    if(((WeakReference)UnhandledKeyEventManager.sViewsWithListeners.get(v1)).get() == view0) {
                        UnhandledKeyEventManager.sViewsWithListeners.remove(v1);
                        return;
                    }
                }
            }
        }
    }

    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    @Deprecated
    public static final int LAYER_TYPE_HARDWARE = 2;
    @Deprecated
    public static final int LAYER_TYPE_NONE = 0;
    @Deprecated
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;
    @Deprecated
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
    @Deprecated
    public static final int MEASURED_SIZE_MASK = 0xFFFFFF;
    @Deprecated
    public static final int MEASURED_STATE_MASK = 0xFF000000;
    @Deprecated
    public static final int MEASURED_STATE_TOO_SMALL = 0x1000000;
    @Deprecated
    public static final int OVER_SCROLL_ALWAYS = 0;
    @Deprecated
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    @Deprecated
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 0x20;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    private static final String TAG = "ViewCompat";
    public static final int TYPE_NON_TOUCH = 1;
    public static final int TYPE_TOUCH;
    private static boolean sAccessibilityDelegateCheckFailed;
    private static Field sAccessibilityDelegateField;
    private static Method sChildrenDrawingOrderMethod;
    private static Method sDispatchFinishTemporaryDetach;
    private static Method sDispatchStartTemporaryDetach;
    private static Field sMinHeightField;
    private static boolean sMinHeightFieldFetched;
    private static Field sMinWidthField;
    private static boolean sMinWidthFieldFetched;
    private static final AtomicInteger sNextGeneratedId;
    private static boolean sTempDetachBound;
    private static ThreadLocal sThreadLocalRect;
    private static WeakHashMap sTransitionNameMap;
    private static WeakHashMap sViewPropertyAnimatorMap;

    static {
        ViewCompat.sNextGeneratedId = new AtomicInteger(1);
        ViewCompat.sViewPropertyAnimatorMap = null;
        ViewCompat.sAccessibilityDelegateCheckFailed = false;
    }

    public static void addKeyboardNavigationClusters(@NonNull View view0, @NonNull Collection collection0, int v) {
        if(Build.VERSION.SDK_INT >= 26) {
            view0.addKeyboardNavigationClusters(collection0, v);
        }
    }

    public static void addOnUnhandledKeyEventListener(@NonNull View view0, @NonNull OnUnhandledKeyEventListenerCompat viewCompat$OnUnhandledKeyEventListenerCompat0) {
        if(Build.VERSION.SDK_INT >= 28) {
            Map map0 = (Map)view0.getTag(id.tag_unhandled_key_listeners);
            if(map0 == null) {
                map0 = new ArrayMap();
                view0.setTag(id.tag_unhandled_key_listeners, map0);
            }
            OnUnhandledKeyEventListenerWrapper viewCompat$OnUnhandledKeyEventListenerWrapper0 = new OnUnhandledKeyEventListenerWrapper(viewCompat$OnUnhandledKeyEventListenerCompat0);
            map0.put(viewCompat$OnUnhandledKeyEventListenerCompat0, viewCompat$OnUnhandledKeyEventListenerWrapper0);
            view0.addOnUnhandledKeyEventListener(viewCompat$OnUnhandledKeyEventListenerWrapper0);
            return;
        }
        ArrayList arrayList0 = (ArrayList)view0.getTag(id.tag_unhandled_key_listeners);
        if(arrayList0 == null) {
            arrayList0 = new ArrayList();
            view0.setTag(id.tag_unhandled_key_listeners, arrayList0);
        }
        arrayList0.add(viewCompat$OnUnhandledKeyEventListenerCompat0);
        if(arrayList0.size() == 1) {
            UnhandledKeyEventManager.registerListeningView(view0);
        }
    }

    @NonNull
    public static ViewPropertyAnimatorCompat animate(@NonNull View view0) {
        if(ViewCompat.sViewPropertyAnimatorMap == null) {
            ViewCompat.sViewPropertyAnimatorMap = new WeakHashMap();
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = (ViewPropertyAnimatorCompat)ViewCompat.sViewPropertyAnimatorMap.get(view0);
        if(viewPropertyAnimatorCompat0 == null) {
            viewPropertyAnimatorCompat0 = new ViewPropertyAnimatorCompat(view0);
            ViewCompat.sViewPropertyAnimatorMap.put(view0, viewPropertyAnimatorCompat0);
        }
        return viewPropertyAnimatorCompat0;
    }

    private static void bindTempDetach() {
        try {
            ViewCompat.sDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach");
            ViewCompat.sDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach");
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.e("ViewCompat", "Couldn\'t find method", noSuchMethodException0);
        }
        ViewCompat.sTempDetachBound = true;
    }

    @Deprecated
    public static boolean canScrollHorizontally(View view0, int v) {
        return view0.canScrollHorizontally(v);
    }

    @Deprecated
    public static boolean canScrollVertically(View view0, int v) {
        return view0.canScrollVertically(v);
    }

    public static void cancelDragAndDrop(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 24) {
            view0.cancelDragAndDrop();
        }
    }

    @Deprecated
    public static int combineMeasuredStates(int v, int v1) {
        return View.combineMeasuredStates(v, v1);
    }

    private static void compatOffsetLeftAndRight(View view0, int v) {
        view0.offsetLeftAndRight(v);
        if(view0.getVisibility() == 0) {
            ViewCompat.tickleInvalidationFlag(view0);
            ViewParent viewParent0 = view0.getParent();
            if(viewParent0 instanceof View) {
                ViewCompat.tickleInvalidationFlag(((View)viewParent0));
            }
        }
    }

    private static void compatOffsetTopAndBottom(View view0, int v) {
        view0.offsetTopAndBottom(v);
        if(view0.getVisibility() == 0) {
            ViewCompat.tickleInvalidationFlag(view0);
            ViewParent viewParent0 = view0.getParent();
            if(viewParent0 instanceof View) {
                ViewCompat.tickleInvalidationFlag(((View)viewParent0));
            }
        }
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(@NonNull View view0, WindowInsetsCompat windowInsetsCompat0) {
        if(Build.VERSION.SDK_INT >= 21) {
            WindowInsets windowInsets0 = (WindowInsets)WindowInsetsCompat.unwrap(windowInsetsCompat0);
            WindowInsets windowInsets1 = view0.dispatchApplyWindowInsets(windowInsets0);
            if(windowInsets1 != windowInsets0) {
                windowInsets0 = new WindowInsets(windowInsets1);
            }
            return WindowInsetsCompat.wrap(windowInsets0);
        }
        return windowInsetsCompat0;
    }

    public static void dispatchFinishTemporaryDetach(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 24) {
            view0.dispatchFinishTemporaryDetach();
            return;
        }
        if(!ViewCompat.sTempDetachBound) {
            ViewCompat.bindTempDetach();
        }
        Method method0 = ViewCompat.sDispatchFinishTemporaryDetach;
        if(method0 != null) {
            try {
                method0.invoke(view0);
            }
            catch(Exception exception0) {
                Log.d("ViewCompat", "Error calling dispatchFinishTemporaryDetach", exception0);
            }
            return;
        }
        view0.onFinishTemporaryDetach();
    }

    public static boolean dispatchNestedFling(@NonNull View view0, float f, float f1, boolean z) {
        if(Build.VERSION.SDK_INT >= 21) {
            return view0.dispatchNestedFling(f, f1, z);
        }
        return view0 instanceof NestedScrollingChild ? ((NestedScrollingChild)view0).dispatchNestedFling(f, f1, z) : false;
    }

    public static boolean dispatchNestedPreFling(@NonNull View view0, float f, float f1) {
        if(Build.VERSION.SDK_INT >= 21) {
            return view0.dispatchNestedPreFling(f, f1);
        }
        return view0 instanceof NestedScrollingChild ? ((NestedScrollingChild)view0).dispatchNestedPreFling(f, f1) : false;
    }

    public static boolean dispatchNestedPreScroll(@NonNull View view0, int v, int v1, @Nullable int[] arr_v, @Nullable int[] arr_v1) {
        if(Build.VERSION.SDK_INT >= 21) {
            return view0.dispatchNestedPreScroll(v, v1, arr_v, arr_v1);
        }
        return view0 instanceof NestedScrollingChild ? ((NestedScrollingChild)view0).dispatchNestedPreScroll(v, v1, arr_v, arr_v1) : false;
    }

    public static boolean dispatchNestedPreScroll(@NonNull View view0, int v, int v1, @Nullable int[] arr_v, @Nullable int[] arr_v1, int v2) {
        if(view0 instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2)view0).dispatchNestedPreScroll(v, v1, arr_v, arr_v1, v2);
        }
        return v2 == 0 ? ViewCompat.dispatchNestedPreScroll(view0, v, v1, arr_v, arr_v1) : false;
    }

    public static boolean dispatchNestedScroll(@NonNull View view0, int v, int v1, int v2, int v3, @Nullable int[] arr_v) {
        if(Build.VERSION.SDK_INT >= 21) {
            return view0.dispatchNestedScroll(v, v1, v2, v3, arr_v);
        }
        return view0 instanceof NestedScrollingChild ? ((NestedScrollingChild)view0).dispatchNestedScroll(v, v1, v2, v3, arr_v) : false;
    }

    public static boolean dispatchNestedScroll(@NonNull View view0, int v, int v1, int v2, int v3, @Nullable int[] arr_v, int v4) {
        if(view0 instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2)view0).dispatchNestedScroll(v, v1, v2, v3, arr_v, v4);
        }
        return v4 == 0 ? ViewCompat.dispatchNestedScroll(view0, v, v1, v2, v3, arr_v) : false;
    }

    public static void dispatchStartTemporaryDetach(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 24) {
            view0.dispatchStartTemporaryDetach();
            return;
        }
        if(!ViewCompat.sTempDetachBound) {
            ViewCompat.bindTempDetach();
        }
        Method method0 = ViewCompat.sDispatchStartTemporaryDetach;
        if(method0 != null) {
            try {
                method0.invoke(view0);
            }
            catch(Exception exception0) {
                Log.d("ViewCompat", "Error calling dispatchStartTemporaryDetach", exception0);
            }
            return;
        }
        view0.onStartTemporaryDetach();
    }

    @UiThread
    static boolean dispatchUnhandledKeyEventBeforeCallback(View view0, KeyEvent keyEvent0) {
        return Build.VERSION.SDK_INT < 28 ? UnhandledKeyEventManager.at(view0).dispatch(view0, keyEvent0) : false;
    }

    @UiThread
    static boolean dispatchUnhandledKeyEventBeforeHierarchy(View view0, KeyEvent keyEvent0) {
        return Build.VERSION.SDK_INT < 28 ? UnhandledKeyEventManager.at(view0).preDispatch(keyEvent0) : false;
    }

    public static int generateViewId() {
        int v;
        if(Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        do {
            v = ViewCompat.sNextGeneratedId.get();
        }
        while(!ViewCompat.sNextGeneratedId.compareAndSet(v, (v + 1 <= 0xFFFFFF ? v + 1 : 1)));
        return v;
    }

    public static int getAccessibilityLiveRegion(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 19 ? 0 : view0.getAccessibilityLiveRegion();
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 16) {
            AccessibilityNodeProvider accessibilityNodeProvider0 = view0.getAccessibilityNodeProvider();
            return accessibilityNodeProvider0 == null ? null : new AccessibilityNodeProviderCompat(accessibilityNodeProvider0);
        }
        return null;
    }

    @Deprecated
    public static float getAlpha(View view0) {
        return view0.getAlpha();
    }

    public static ColorStateList getBackgroundTintList(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return view0.getBackgroundTintList();
        }
        return view0 instanceof TintableBackgroundView ? ((TintableBackgroundView)view0).getSupportBackgroundTintList() : null;
    }

    public static PorterDuff.Mode getBackgroundTintMode(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return view0.getBackgroundTintMode();
        }
        return view0 instanceof TintableBackgroundView ? ((TintableBackgroundView)view0).getSupportBackgroundTintMode() : null;
    }

    @Nullable
    public static Rect getClipBounds(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 18 ? null : view0.getClipBounds();
    }

    @Nullable
    public static Display getDisplay(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 17) {
            return view0.getDisplay();
        }
        return ViewCompat.isAttachedToWindow(view0) ? ((WindowManager)view0.getContext().getSystemService("window")).getDefaultDisplay() : null;
    }

    public static float getElevation(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 21 ? 0.0f : view0.getElevation();
    }

    private static Rect getEmptyTempRect() {
        if(ViewCompat.sThreadLocalRect == null) {
            ViewCompat.sThreadLocalRect = new ThreadLocal();
        }
        Rect rect0 = (Rect)ViewCompat.sThreadLocalRect.get();
        if(rect0 == null) {
            rect0 = new Rect();
            ViewCompat.sThreadLocalRect.set(rect0);
        }
        rect0.setEmpty();
        return rect0;
    }

    public static boolean getFitsSystemWindows(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 16 ? false : view0.getFitsSystemWindows();
    }

    public static int getImportantForAccessibility(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 16 ? 0 : view0.getImportantForAccessibility();
    }

    @SuppressLint({"InlinedApi"})
    public static int getImportantForAutofill(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 26 ? 0 : view0.getImportantForAutofill();
    }

    public static int getLabelFor(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 17 ? 0 : view0.getLabelFor();
    }

    @Deprecated
    public static int getLayerType(View view0) {
        return view0.getLayerType();
    }

    public static int getLayoutDirection(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 17 ? 0 : view0.getLayoutDirection();
    }

    @Nullable
    @Deprecated
    public static Matrix getMatrix(View view0) {
        return view0.getMatrix();
    }

    @Deprecated
    public static int getMeasuredHeightAndState(View view0) {
        return view0.getMeasuredHeightAndState();
    }

    @Deprecated
    public static int getMeasuredState(View view0) {
        return view0.getMeasuredState();
    }

    @Deprecated
    public static int getMeasuredWidthAndState(View view0) {
        return view0.getMeasuredWidthAndState();
    }

    public static int getMinimumHeight(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 16) {
            return view0.getMinimumHeight();
        }
        if(!ViewCompat.sMinHeightFieldFetched) {
            try {
                ViewCompat.sMinHeightField = View.class.getDeclaredField("mMinHeight");
                ViewCompat.sMinHeightField.setAccessible(true);
            }
            catch(NoSuchFieldException unused_ex) {
            }
            ViewCompat.sMinHeightFieldFetched = true;
        }
        Field field0 = ViewCompat.sMinHeightField;
        if(field0 != null) {
            try {
                return (int)(((Integer)field0.get(view0)));
            }
            catch(Exception unused_ex) {
            }
        }
        return 0;
    }

    public static int getMinimumWidth(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 16) {
            return view0.getMinimumWidth();
        }
        if(!ViewCompat.sMinWidthFieldFetched) {
            try {
                ViewCompat.sMinWidthField = View.class.getDeclaredField("mMinWidth");
                ViewCompat.sMinWidthField.setAccessible(true);
            }
            catch(NoSuchFieldException unused_ex) {
            }
            ViewCompat.sMinWidthFieldFetched = true;
        }
        Field field0 = ViewCompat.sMinWidthField;
        if(field0 != null) {
            try {
                return (int)(((Integer)field0.get(view0)));
            }
            catch(Exception unused_ex) {
            }
        }
        return 0;
    }

    public static int getNextClusterForwardId(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 26 ? -1 : view0.getNextClusterForwardId();
    }

    @Deprecated
    public static int getOverScrollMode(View view0) {
        return view0.getOverScrollMode();
    }

    @Px
    public static int getPaddingEnd(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 17 ? view0.getPaddingRight() : view0.getPaddingEnd();
    }

    @Px
    public static int getPaddingStart(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 17 ? view0.getPaddingLeft() : view0.getPaddingStart();
    }

    public static ViewParent getParentForAccessibility(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 16 ? view0.getParent() : view0.getParentForAccessibility();
    }

    @Deprecated
    public static float getPivotX(View view0) {
        return view0.getPivotX();
    }

    @Deprecated
    public static float getPivotY(View view0) {
        return view0.getPivotY();
    }

    @Deprecated
    public static float getRotation(View view0) {
        return view0.getRotation();
    }

    @Deprecated
    public static float getRotationX(View view0) {
        return view0.getRotationX();
    }

    @Deprecated
    public static float getRotationY(View view0) {
        return view0.getRotationY();
    }

    @Deprecated
    public static float getScaleX(View view0) {
        return view0.getScaleX();
    }

    @Deprecated
    public static float getScaleY(View view0) {
        return view0.getScaleY();
    }

    public static int getScrollIndicators(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 23 ? 0 : view0.getScrollIndicators();
    }

    @Nullable
    public static String getTransitionName(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return view0.getTransitionName();
        }
        return ViewCompat.sTransitionNameMap == null ? null : ((String)ViewCompat.sTransitionNameMap.get(view0));
    }

    @Deprecated
    public static float getTranslationX(View view0) {
        return view0.getTranslationX();
    }

    @Deprecated
    public static float getTranslationY(View view0) {
        return view0.getTranslationY();
    }

    public static float getTranslationZ(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 21 ? 0.0f : view0.getTranslationZ();
    }

    public static int getWindowSystemUiVisibility(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 16 ? 0 : view0.getWindowSystemUiVisibility();
    }

    @Deprecated
    public static float getX(View view0) {
        return view0.getX();
    }

    @Deprecated
    public static float getY(View view0) {
        return view0.getY();
    }

    public static float getZ(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 21 ? 0.0f : view0.getZ();
    }

    public static boolean hasAccessibilityDelegate(@NonNull View view0) {
        if(ViewCompat.sAccessibilityDelegateCheckFailed) {
            return false;
        }
        if(ViewCompat.sAccessibilityDelegateField == null) {
            try {
                ViewCompat.sAccessibilityDelegateField = View.class.getDeclaredField("mAccessibilityDelegate");
                ViewCompat.sAccessibilityDelegateField.setAccessible(true);
            }
            catch(Throwable unused_ex) {
                ViewCompat.sAccessibilityDelegateCheckFailed = true;
                return false;
            }
        }
        try {
            if(ViewCompat.sAccessibilityDelegateField.get(view0) != null) {
                return true;
            }
        }
        catch(Throwable unused_ex) {
            ViewCompat.sAccessibilityDelegateCheckFailed = true;
        }
        return false;
    }

    public static boolean hasExplicitFocusable(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 26 ? view0.hasFocusable() : view0.hasExplicitFocusable();
    }

    public static boolean hasNestedScrollingParent(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return view0.hasNestedScrollingParent();
        }
        return view0 instanceof NestedScrollingChild ? ((NestedScrollingChild)view0).hasNestedScrollingParent() : false;
    }

    public static boolean hasNestedScrollingParent(@NonNull View view0, int v) {
        if(view0 instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2)view0).hasNestedScrollingParent(v);
            return false;
        }
        return v == 0 ? ViewCompat.hasNestedScrollingParent(view0) : false;
    }

    public static boolean hasOnClickListeners(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 15 ? false : view0.hasOnClickListeners();
    }

    public static boolean hasOverlappingRendering(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 16 ? true : view0.hasOverlappingRendering();
    }

    public static boolean hasTransientState(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 16 ? false : view0.hasTransientState();
    }

    public static boolean isAttachedToWindow(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 19 ? view0.getWindowToken() != null : view0.isAttachedToWindow();
    }

    public static boolean isFocusedByDefault(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 26 ? false : view0.isFocusedByDefault();
    }

    public static boolean isImportantForAccessibility(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 21 ? true : view0.isImportantForAccessibility();
    }

    public static boolean isImportantForAutofill(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 26 ? true : view0.isImportantForAutofill();
    }

    public static boolean isInLayout(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 18 ? false : view0.isInLayout();
    }

    public static boolean isKeyboardNavigationCluster(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 26 ? false : view0.isKeyboardNavigationCluster();
    }

    public static boolean isLaidOut(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 19 ? view0.getWidth() > 0 && view0.getHeight() > 0 : view0.isLaidOut();
    }

    public static boolean isLayoutDirectionResolved(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 19 ? false : view0.isLayoutDirectionResolved();
    }

    public static boolean isNestedScrollingEnabled(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return view0.isNestedScrollingEnabled();
        }
        return view0 instanceof NestedScrollingChild ? ((NestedScrollingChild)view0).isNestedScrollingEnabled() : false;
    }

    @Deprecated
    public static boolean isOpaque(View view0) {
        return view0.isOpaque();
    }

    public static boolean isPaddingRelative(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 17 ? false : view0.isPaddingRelative();
    }

    @Deprecated
    public static void jumpDrawablesToCurrentState(View view0) {
        view0.jumpDrawablesToCurrentState();
    }

    public static View keyboardNavigationClusterSearch(@NonNull View view0, View view1, int v) {
        return Build.VERSION.SDK_INT < 26 ? null : view0.keyboardNavigationClusterSearch(view1, v);
    }

    public static void offsetLeftAndRight(@NonNull View view0, int v) {
        if(Build.VERSION.SDK_INT >= 23) {
            view0.offsetLeftAndRight(v);
            return;
        }
        if(Build.VERSION.SDK_INT >= 21) {
            Rect rect0 = ViewCompat.getEmptyTempRect();
            int v1 = 0;
            ViewParent viewParent0 = view0.getParent();
            if(viewParent0 instanceof View) {
                rect0.set(((View)viewParent0).getLeft(), ((View)viewParent0).getTop(), ((View)viewParent0).getRight(), ((View)viewParent0).getBottom());
                v1 = !rect0.intersects(view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom());
            }
            ViewCompat.compatOffsetLeftAndRight(view0, v);
            if(v1 != 0 && rect0.intersect(view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom())) {
                ((View)viewParent0).invalidate(rect0);
            }
        }
        else {
            ViewCompat.compatOffsetLeftAndRight(view0, v);
        }
    }

    public static void offsetTopAndBottom(@NonNull View view0, int v) {
        if(Build.VERSION.SDK_INT >= 23) {
            view0.offsetTopAndBottom(v);
            return;
        }
        if(Build.VERSION.SDK_INT >= 21) {
            Rect rect0 = ViewCompat.getEmptyTempRect();
            int v1 = 0;
            ViewParent viewParent0 = view0.getParent();
            if(viewParent0 instanceof View) {
                rect0.set(((View)viewParent0).getLeft(), ((View)viewParent0).getTop(), ((View)viewParent0).getRight(), ((View)viewParent0).getBottom());
                v1 = !rect0.intersects(view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom());
            }
            ViewCompat.compatOffsetTopAndBottom(view0, v);
            if(v1 != 0 && rect0.intersect(view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom())) {
                ((View)viewParent0).invalidate(rect0);
            }
        }
        else {
            ViewCompat.compatOffsetTopAndBottom(view0, v);
        }
    }

    public static WindowInsetsCompat onApplyWindowInsets(@NonNull View view0, WindowInsetsCompat windowInsetsCompat0) {
        if(Build.VERSION.SDK_INT >= 21) {
            WindowInsets windowInsets0 = (WindowInsets)WindowInsetsCompat.unwrap(windowInsetsCompat0);
            WindowInsets windowInsets1 = view0.onApplyWindowInsets(windowInsets0);
            if(windowInsets1 != windowInsets0) {
                windowInsets0 = new WindowInsets(windowInsets1);
            }
            return WindowInsetsCompat.wrap(windowInsets0);
        }
        return windowInsetsCompat0;
    }

    @Deprecated
    public static void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        view0.onInitializeAccessibilityEvent(accessibilityEvent0);
    }

    public static void onInitializeAccessibilityNodeInfo(@NonNull View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        view0.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat0.unwrap());
    }

    @Deprecated
    public static void onPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        view0.onPopulateAccessibilityEvent(accessibilityEvent0);
    }

    public static boolean performAccessibilityAction(@NonNull View view0, int v, Bundle bundle0) {
        return Build.VERSION.SDK_INT < 16 ? false : view0.performAccessibilityAction(v, bundle0);
    }

    public static void postInvalidateOnAnimation(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 16) {
            view0.postInvalidateOnAnimation();
            return;
        }
        view0.postInvalidate();
    }

    public static void postInvalidateOnAnimation(@NonNull View view0, int v, int v1, int v2, int v3) {
        if(Build.VERSION.SDK_INT >= 16) {
            view0.postInvalidateOnAnimation(v, v1, v2, v3);
            return;
        }
        view0.postInvalidate(v, v1, v2, v3);
    }

    public static void postOnAnimation(@NonNull View view0, Runnable runnable0) {
        if(Build.VERSION.SDK_INT >= 16) {
            view0.postOnAnimation(runnable0);
            return;
        }
        view0.postDelayed(runnable0, ValueAnimator.getFrameDelay());
    }

    public static void postOnAnimationDelayed(@NonNull View view0, Runnable runnable0, long v) {
        if(Build.VERSION.SDK_INT >= 16) {
            view0.postOnAnimationDelayed(runnable0, v);
            return;
        }
        view0.postDelayed(runnable0, ValueAnimator.getFrameDelay() + v);
    }

    public static void removeOnUnhandledKeyEventListener(@NonNull View view0, @NonNull OnUnhandledKeyEventListenerCompat viewCompat$OnUnhandledKeyEventListenerCompat0) {
        if(Build.VERSION.SDK_INT >= 28) {
            Map map0 = (Map)view0.getTag(id.tag_unhandled_key_listeners);
            if(map0 == null) {
                return;
            }
            View.OnUnhandledKeyEventListener view$OnUnhandledKeyEventListener0 = (View.OnUnhandledKeyEventListener)map0.get(viewCompat$OnUnhandledKeyEventListenerCompat0);
            if(view$OnUnhandledKeyEventListener0 != null) {
                view0.removeOnUnhandledKeyEventListener(view$OnUnhandledKeyEventListener0);
            }
            return;
        }
        ArrayList arrayList0 = (ArrayList)view0.getTag(id.tag_unhandled_key_listeners);
        if(arrayList0 != null) {
            arrayList0.remove(viewCompat$OnUnhandledKeyEventListenerCompat0);
            if(arrayList0.size() == 0) {
                UnhandledKeyEventManager.unregisterListeningView(view0);
            }
        }
    }

    public static void requestApplyInsets(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 20) {
            view0.requestApplyInsets();
            return;
        }
        if(Build.VERSION.SDK_INT >= 16) {
            view0.requestFitSystemWindows();
        }
    }

    @NonNull
    public static View requireViewById(@NonNull View view0, @IdRes int v) {
        if(Build.VERSION.SDK_INT >= 28) {
            return view0.requireViewById(v);
        }
        View view1 = view0.findViewById(v);
        if(view1 == null) {
            throw new IllegalArgumentException("ID does not reference a View inside this View");
        }
        return view1;
    }

    @Deprecated
    public static int resolveSizeAndState(int v, int v1, int v2) {
        return View.resolveSizeAndState(v, v1, v2);
    }

    public static boolean restoreDefaultFocus(@NonNull View view0) {
        return Build.VERSION.SDK_INT < 26 ? view0.requestFocus() : view0.restoreDefaultFocus();
    }

    public static void setAccessibilityDelegate(@NonNull View view0, AccessibilityDelegateCompat accessibilityDelegateCompat0) {
        view0.setAccessibilityDelegate((accessibilityDelegateCompat0 == null ? null : accessibilityDelegateCompat0.getBridge()));
    }

    public static void setAccessibilityLiveRegion(@NonNull View view0, int v) {
        if(Build.VERSION.SDK_INT >= 19) {
            view0.setAccessibilityLiveRegion(v);
        }
    }

    @Deprecated
    public static void setActivated(View view0, boolean z) {
        view0.setActivated(z);
    }

    @Deprecated
    public static void setAlpha(View view0, @FloatRange(from = 0.0, to = 1.0) float f) {
        view0.setAlpha(f);
    }

    public static void setAutofillHints(@NonNull View view0, @Nullable String[] arr_s) {
        if(Build.VERSION.SDK_INT >= 26) {
            view0.setAutofillHints(arr_s);
        }
    }

    public static void setBackground(@NonNull View view0, @Nullable Drawable drawable0) {
        if(Build.VERSION.SDK_INT >= 16) {
            view0.setBackground(drawable0);
            return;
        }
        view0.setBackgroundDrawable(drawable0);
    }

    public static void setBackgroundTintList(@NonNull View view0, ColorStateList colorStateList0) {
        if(Build.VERSION.SDK_INT >= 21) {
            view0.setBackgroundTintList(colorStateList0);
            if(Build.VERSION.SDK_INT == 21) {
                Drawable drawable0 = view0.getBackground();
                if(drawable0 != null && (view0.getBackgroundTintList() != null || view0.getBackgroundTintMode() != null)) {
                    if(drawable0.isStateful()) {
                        drawable0.setState(view0.getDrawableState());
                    }
                    view0.setBackground(drawable0);
                }
            }
        }
        else if(view0 instanceof TintableBackgroundView) {
            ((TintableBackgroundView)view0).setSupportBackgroundTintList(colorStateList0);
        }
    }

    public static void setBackgroundTintMode(@NonNull View view0, PorterDuff.Mode porterDuff$Mode0) {
        if(Build.VERSION.SDK_INT >= 21) {
            view0.setBackgroundTintMode(porterDuff$Mode0);
            if(Build.VERSION.SDK_INT == 21) {
                Drawable drawable0 = view0.getBackground();
                if(drawable0 != null && (view0.getBackgroundTintList() != null || view0.getBackgroundTintMode() != null)) {
                    if(drawable0.isStateful()) {
                        drawable0.setState(view0.getDrawableState());
                    }
                    view0.setBackground(drawable0);
                }
            }
        }
        else if(view0 instanceof TintableBackgroundView) {
            ((TintableBackgroundView)view0).setSupportBackgroundTintMode(porterDuff$Mode0);
        }
    }

    @Deprecated
    public static void setChildrenDrawingOrderEnabled(ViewGroup viewGroup0, boolean z) {
        if(ViewCompat.sChildrenDrawingOrderMethod == null) {
            try {
                ViewCompat.sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.e("ViewCompat", "Unable to find childrenDrawingOrderEnabled", noSuchMethodException0);
            }
            ViewCompat.sChildrenDrawingOrderMethod.setAccessible(true);
        }
        try {
            ViewCompat.sChildrenDrawingOrderMethod.invoke(viewGroup0, Boolean.valueOf(z));
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", illegalAccessException0);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", illegalArgumentException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", invocationTargetException0);
        }
    }

    public static void setClipBounds(@NonNull View view0, Rect rect0) {
        if(Build.VERSION.SDK_INT >= 18) {
            view0.setClipBounds(rect0);
        }
    }

    public static void setElevation(@NonNull View view0, float f) {
        if(Build.VERSION.SDK_INT >= 21) {
            view0.setElevation(f);
        }
    }

    @Deprecated
    public static void setFitsSystemWindows(View view0, boolean z) {
        view0.setFitsSystemWindows(z);
    }

    public static void setFocusedByDefault(@NonNull View view0, boolean z) {
        if(Build.VERSION.SDK_INT >= 26) {
            view0.setFocusedByDefault(z);
        }
    }

    public static void setHasTransientState(@NonNull View view0, boolean z) {
        if(Build.VERSION.SDK_INT >= 16) {
            view0.setHasTransientState(z);
        }
    }

    public static void setImportantForAccessibility(@NonNull View view0, int v) {
        if(Build.VERSION.SDK_INT >= 19) {
            view0.setImportantForAccessibility(v);
            return;
        }
        if(Build.VERSION.SDK_INT >= 16) {
            if(v == 4) {
                v = 2;
            }
            view0.setImportantForAccessibility(v);
        }
    }

    public static void setImportantForAutofill(@NonNull View view0, int v) {
        if(Build.VERSION.SDK_INT >= 26) {
            view0.setImportantForAutofill(v);
        }
    }

    public static void setKeyboardNavigationCluster(@NonNull View view0, boolean z) {
        if(Build.VERSION.SDK_INT >= 26) {
            view0.setKeyboardNavigationCluster(z);
        }
    }

    public static void setLabelFor(@NonNull View view0, @IdRes int v) {
        if(Build.VERSION.SDK_INT >= 17) {
            view0.setLabelFor(v);
        }
    }

    public static void setLayerPaint(@NonNull View view0, Paint paint0) {
        if(Build.VERSION.SDK_INT >= 17) {
            view0.setLayerPaint(paint0);
            return;
        }
        view0.setLayerType(view0.getLayerType(), paint0);
        view0.invalidate();
    }

    @Deprecated
    public static void setLayerType(View view0, int v, Paint paint0) {
        view0.setLayerType(v, paint0);
    }

    public static void setLayoutDirection(@NonNull View view0, int v) {
        if(Build.VERSION.SDK_INT >= 17) {
            view0.setLayoutDirection(v);
        }
    }

    public static void setNestedScrollingEnabled(@NonNull View view0, boolean z) {
        if(Build.VERSION.SDK_INT >= 21) {
            view0.setNestedScrollingEnabled(z);
            return;
        }
        if(view0 instanceof NestedScrollingChild) {
            ((NestedScrollingChild)view0).setNestedScrollingEnabled(z);
        }
    }

    public static void setNextClusterForwardId(@NonNull View view0, int v) {
        if(Build.VERSION.SDK_INT >= 26) {
            view0.setNextClusterForwardId(v);
        }
    }

    public static void setOnApplyWindowInsetsListener(@NonNull View view0, OnApplyWindowInsetsListener onApplyWindowInsetsListener0) {
        if(Build.VERSION.SDK_INT >= 21) {
            if(onApplyWindowInsetsListener0 == null) {
                view0.setOnApplyWindowInsetsListener(null);
                return;
            }
            view0.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @Override  // android.view.View$OnApplyWindowInsetsListener
                public WindowInsets onApplyWindowInsets(View view0, WindowInsets windowInsets0) {
                    WindowInsetsCompat windowInsetsCompat0 = WindowInsetsCompat.wrap(windowInsets0);
                    return (WindowInsets)WindowInsetsCompat.unwrap(onApplyWindowInsetsListener0.onApplyWindowInsets(view0, windowInsetsCompat0));
                }
            });
        }
    }

    @Deprecated
    public static void setOverScrollMode(View view0, int v) {
        view0.setOverScrollMode(v);
    }

    public static void setPaddingRelative(@NonNull View view0, @Px int v, @Px int v1, @Px int v2, @Px int v3) {
        if(Build.VERSION.SDK_INT >= 17) {
            view0.setPaddingRelative(v, v1, v2, v3);
            return;
        }
        view0.setPadding(v, v1, v2, v3);
    }

    @Deprecated
    public static void setPivotX(View view0, float f) {
        view0.setPivotX(f);
    }

    @Deprecated
    public static void setPivotY(View view0, float f) {
        view0.setPivotY(f);
    }

    public static void setPointerIcon(@NonNull View view0, PointerIconCompat pointerIconCompat0) {
        if(Build.VERSION.SDK_INT >= 24) {
            view0.setPointerIcon(((PointerIcon)(pointerIconCompat0 == null ? null : pointerIconCompat0.getPointerIcon())));
        }
    }

    @Deprecated
    public static void setRotation(View view0, float f) {
        view0.setRotation(f);
    }

    @Deprecated
    public static void setRotationX(View view0, float f) {
        view0.setRotationX(f);
    }

    @Deprecated
    public static void setRotationY(View view0, float f) {
        view0.setRotationY(f);
    }

    @Deprecated
    public static void setSaveFromParentEnabled(View view0, boolean z) {
        view0.setSaveFromParentEnabled(z);
    }

    @Deprecated
    public static void setScaleX(View view0, float f) {
        view0.setScaleX(f);
    }

    @Deprecated
    public static void setScaleY(View view0, float f) {
        view0.setScaleY(f);
    }

    public static void setScrollIndicators(@NonNull View view0, int v) {
        if(Build.VERSION.SDK_INT >= 23) {
            view0.setScrollIndicators(v);
        }
    }

    public static void setScrollIndicators(@NonNull View view0, int v, int v1) {
        if(Build.VERSION.SDK_INT >= 23) {
            view0.setScrollIndicators(v, v1);
        }
    }

    public static void setTooltipText(@NonNull View view0, @Nullable CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 26) {
            view0.setTooltipText(charSequence0);
        }
    }

    public static void setTransitionName(@NonNull View view0, String s) {
        if(Build.VERSION.SDK_INT >= 21) {
            view0.setTransitionName(s);
            return;
        }
        if(ViewCompat.sTransitionNameMap == null) {
            ViewCompat.sTransitionNameMap = new WeakHashMap();
        }
        ViewCompat.sTransitionNameMap.put(view0, s);
    }

    @Deprecated
    public static void setTranslationX(View view0, float f) {
        view0.setTranslationX(f);
    }

    @Deprecated
    public static void setTranslationY(View view0, float f) {
        view0.setTranslationY(f);
    }

    public static void setTranslationZ(@NonNull View view0, float f) {
        if(Build.VERSION.SDK_INT >= 21) {
            view0.setTranslationZ(f);
        }
    }

    @Deprecated
    public static void setX(View view0, float f) {
        view0.setX(f);
    }

    @Deprecated
    public static void setY(View view0, float f) {
        view0.setY(f);
    }

    public static void setZ(@NonNull View view0, float f) {
        if(Build.VERSION.SDK_INT >= 21) {
            view0.setZ(f);
        }
    }

    public static boolean startDragAndDrop(@NonNull View view0, ClipData clipData0, View.DragShadowBuilder view$DragShadowBuilder0, Object object0, int v) {
        return Build.VERSION.SDK_INT < 24 ? view0.startDrag(clipData0, view$DragShadowBuilder0, object0, v) : view0.startDragAndDrop(clipData0, view$DragShadowBuilder0, object0, v);
    }

    public static boolean startNestedScroll(@NonNull View view0, int v) {
        if(Build.VERSION.SDK_INT >= 21) {
            return view0.startNestedScroll(v);
        }
        return view0 instanceof NestedScrollingChild ? ((NestedScrollingChild)view0).startNestedScroll(v) : false;
    }

    public static boolean startNestedScroll(@NonNull View view0, int v, int v1) {
        if(view0 instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2)view0).startNestedScroll(v, v1);
        }
        return v1 == 0 ? ViewCompat.startNestedScroll(view0, v) : false;
    }

    public static void stopNestedScroll(@NonNull View view0) {
        if(Build.VERSION.SDK_INT >= 21) {
            view0.stopNestedScroll();
            return;
        }
        if(view0 instanceof NestedScrollingChild) {
            ((NestedScrollingChild)view0).stopNestedScroll();
        }
    }

    public static void stopNestedScroll(@NonNull View view0, int v) {
        if(view0 instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2)view0).stopNestedScroll(v);
            return;
        }
        if(v == 0) {
            ViewCompat.stopNestedScroll(view0);
        }
    }

    private static void tickleInvalidationFlag(View view0) {
        float f = view0.getTranslationY();
        view0.setTranslationY(f + 1.0f);
        view0.setTranslationY(f);
    }

    public static void updateDragShadow(@NonNull View view0, View.DragShadowBuilder view$DragShadowBuilder0) {
        if(Build.VERSION.SDK_INT >= 24) {
            view0.updateDragShadow(view$DragShadowBuilder0);
        }
    }
}

