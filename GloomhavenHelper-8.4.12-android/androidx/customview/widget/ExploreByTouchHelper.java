package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewParentCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import java.util.ArrayList;
import java.util.List;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    class MyNodeProvider extends AccessibilityNodeProviderCompat {
        @Override  // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int v) {
            return AccessibilityNodeInfoCompat.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(v));
        }

        @Override  // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat findFocus(int v) {
            int v1 = v == 2 ? ExploreByTouchHelper.this.mAccessibilityFocusedVirtualViewId : ExploreByTouchHelper.this.mKeyboardFocusedVirtualViewId;
            return v1 == 0x80000000 ? null : this.createAccessibilityNodeInfo(v1);
        }

        @Override  // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public boolean performAction(int v, int v1, Bundle bundle0) {
            return ExploreByTouchHelper.this.performAction(v, v1, bundle0);
        }
    }

    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = 0x80000000;
    private static final Rect INVALID_PARENT_BOUNDS;
    private static final BoundsAdapter NODE_ADAPTER;
    private static final CollectionAdapter SPARSE_VALUES_ADAPTER;
    int mAccessibilityFocusedVirtualViewId;
    private final View mHost;
    private int mHoveredVirtualViewId;
    int mKeyboardFocusedVirtualViewId;
    private final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final int[] mTempGlobalRect;
    private final Rect mTempParentRect;
    private final Rect mTempScreenRect;
    private final Rect mTempVisibleRect;

    static {
        ExploreByTouchHelper.INVALID_PARENT_BOUNDS = new Rect(0x7FFFFFFF, 0x7FFFFFFF, 0x80000000, 0x80000000);
        ExploreByTouchHelper.NODE_ADAPTER = new BoundsAdapter() {
            public void obtainBounds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0, Rect rect0) {
                accessibilityNodeInfoCompat0.getBoundsInParent(rect0);
            }

            @Override  // androidx.customview.widget.FocusStrategy$BoundsAdapter
            public void obtainBounds(Object object0, Rect rect0) {
                this.obtainBounds(((AccessibilityNodeInfoCompat)object0), rect0);
            }
        };
        ExploreByTouchHelper.SPARSE_VALUES_ADAPTER = new CollectionAdapter() {
            public AccessibilityNodeInfoCompat get(SparseArrayCompat sparseArrayCompat0, int v) {
                return (AccessibilityNodeInfoCompat)sparseArrayCompat0.valueAt(v);
            }

            @Override  // androidx.customview.widget.FocusStrategy$CollectionAdapter
            public Object get(Object object0, int v) {
                return this.get(((SparseArrayCompat)object0), v);
            }

            public int size(SparseArrayCompat sparseArrayCompat0) {
                return sparseArrayCompat0.size();
            }

            @Override  // androidx.customview.widget.FocusStrategy$CollectionAdapter
            public int size(Object object0) {
                return this.size(((SparseArrayCompat)object0));
            }
        };
    }

    public ExploreByTouchHelper(@NonNull View view0) {
        this.mTempScreenRect = new Rect();
        this.mTempParentRect = new Rect();
        this.mTempVisibleRect = new Rect();
        this.mTempGlobalRect = new int[2];
        this.mAccessibilityFocusedVirtualViewId = 0x80000000;
        this.mKeyboardFocusedVirtualViewId = 0x80000000;
        this.mHoveredVirtualViewId = 0x80000000;
        if(view0 == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.mHost = view0;
        this.mManager = (AccessibilityManager)view0.getContext().getSystemService("accessibility");
        view0.setFocusable(true);
        if(ViewCompat.getImportantForAccessibility(view0) == 0) {
            ViewCompat.setImportantForAccessibility(view0, 1);
        }
    }

    private boolean clearAccessibilityFocus(int v) {
        if(this.mAccessibilityFocusedVirtualViewId == v) {
            this.mAccessibilityFocusedVirtualViewId = 0x80000000;
            this.mHost.invalidate();
            this.sendEventForVirtualView(v, 0x10000);
            return true;
        }
        return false;
    }

    public final boolean clearKeyboardFocusForVirtualView(int v) {
        if(this.mKeyboardFocusedVirtualViewId != v) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = 0x80000000;
        this.sendEventForVirtualView(v, 8);
        return true;
    }

    private boolean clickKeyboardFocusedVirtualView() {
        return this.mKeyboardFocusedVirtualViewId != 0x80000000 && this.onPerformActionForVirtualView(this.mKeyboardFocusedVirtualViewId, 16, null);
    }

    private AccessibilityEvent createEvent(int v, int v1) {
        return v == -1 ? this.createEventForHost(v1) : this.createEventForChild(v, v1);
    }

    private AccessibilityEvent createEventForChild(int v, int v1) {
        AccessibilityEvent accessibilityEvent0 = AccessibilityEvent.obtain(v1);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = this.obtainAccessibilityNodeInfo(v);
        accessibilityEvent0.getText().add(accessibilityNodeInfoCompat0.getText());
        accessibilityEvent0.setContentDescription(accessibilityNodeInfoCompat0.getContentDescription());
        accessibilityEvent0.setScrollable(accessibilityNodeInfoCompat0.isScrollable());
        accessibilityEvent0.setPassword(accessibilityNodeInfoCompat0.isPassword());
        accessibilityEvent0.setEnabled(accessibilityNodeInfoCompat0.isEnabled());
        accessibilityEvent0.setChecked(accessibilityNodeInfoCompat0.isChecked());
        if(accessibilityEvent0.getText().isEmpty() && accessibilityEvent0.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEvent0.setClassName(accessibilityNodeInfoCompat0.getClassName());
        AccessibilityRecordCompat.setSource(accessibilityEvent0, this.mHost, v);
        accessibilityEvent0.setPackageName("com.esotericsoftware.gloomhavenhelper");
        return accessibilityEvent0;
    }

    private AccessibilityEvent createEventForHost(int v) {
        AccessibilityEvent accessibilityEvent0 = AccessibilityEvent.obtain(v);
        this.mHost.onInitializeAccessibilityEvent(accessibilityEvent0);
        return accessibilityEvent0;
    }

    @NonNull
    private AccessibilityNodeInfoCompat createNodeForChild(int v) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = AccessibilityNodeInfoCompat.obtain();
        accessibilityNodeInfoCompat0.setEnabled(true);
        accessibilityNodeInfoCompat0.setFocusable(true);
        accessibilityNodeInfoCompat0.setClassName("android.view.View");
        accessibilityNodeInfoCompat0.setBoundsInParent(ExploreByTouchHelper.INVALID_PARENT_BOUNDS);
        accessibilityNodeInfoCompat0.setBoundsInScreen(ExploreByTouchHelper.INVALID_PARENT_BOUNDS);
        accessibilityNodeInfoCompat0.setParent(this.mHost);
        this.onPopulateNodeForVirtualView(v, accessibilityNodeInfoCompat0);
        if(accessibilityNodeInfoCompat0.getText() == null && accessibilityNodeInfoCompat0.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        accessibilityNodeInfoCompat0.getBoundsInParent(this.mTempParentRect);
        if(this.mTempParentRect.equals(ExploreByTouchHelper.INVALID_PARENT_BOUNDS)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int v1 = accessibilityNodeInfoCompat0.getActions();
        if((v1 & 0x40) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if((v1 & 0x80) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        accessibilityNodeInfoCompat0.setPackageName("com.esotericsoftware.gloomhavenhelper");
        accessibilityNodeInfoCompat0.setSource(this.mHost, v);
        if(this.mAccessibilityFocusedVirtualViewId == v) {
            accessibilityNodeInfoCompat0.setAccessibilityFocused(true);
            accessibilityNodeInfoCompat0.addAction(0x80);
        }
        else {
            accessibilityNodeInfoCompat0.setAccessibilityFocused(false);
            accessibilityNodeInfoCompat0.addAction(0x40);
        }
        boolean z = this.mKeyboardFocusedVirtualViewId == v;
        if(z) {
            accessibilityNodeInfoCompat0.addAction(2);
        }
        else if(accessibilityNodeInfoCompat0.isFocusable()) {
            accessibilityNodeInfoCompat0.addAction(1);
        }
        accessibilityNodeInfoCompat0.setFocused(z);
        this.mHost.getLocationOnScreen(this.mTempGlobalRect);
        accessibilityNodeInfoCompat0.getBoundsInScreen(this.mTempScreenRect);
        if(this.mTempScreenRect.equals(ExploreByTouchHelper.INVALID_PARENT_BOUNDS)) {
            accessibilityNodeInfoCompat0.getBoundsInParent(this.mTempScreenRect);
            if(accessibilityNodeInfoCompat0.mParentVirtualDescendantId != -1) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat1 = AccessibilityNodeInfoCompat.obtain();
                for(int v2 = accessibilityNodeInfoCompat0.mParentVirtualDescendantId; v2 != -1; v2 = accessibilityNodeInfoCompat1.mParentVirtualDescendantId) {
                    accessibilityNodeInfoCompat1.setParent(this.mHost, -1);
                    accessibilityNodeInfoCompat1.setBoundsInParent(ExploreByTouchHelper.INVALID_PARENT_BOUNDS);
                    this.onPopulateNodeForVirtualView(v2, accessibilityNodeInfoCompat1);
                    accessibilityNodeInfoCompat1.getBoundsInParent(this.mTempParentRect);
                    this.mTempScreenRect.offset(this.mTempParentRect.left, this.mTempParentRect.top);
                }
                accessibilityNodeInfoCompat1.recycle();
            }
            int v3 = this.mTempGlobalRect[0];
            int v4 = this.mHost.getScrollX();
            int v5 = this.mTempGlobalRect[1];
            int v6 = this.mHost.getScrollY();
            this.mTempScreenRect.offset(v3 - v4, v5 - v6);
        }
        if(this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
            int v7 = this.mTempGlobalRect[0];
            int v8 = this.mHost.getScrollX();
            int v9 = this.mTempGlobalRect[1];
            int v10 = this.mHost.getScrollY();
            this.mTempVisibleRect.offset(v7 - v8, v9 - v10);
            if(this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                accessibilityNodeInfoCompat0.setBoundsInScreen(this.mTempScreenRect);
                if(this.isVisibleToUser(this.mTempScreenRect)) {
                    accessibilityNodeInfoCompat0.setVisibleToUser(true);
                }
            }
        }
        return accessibilityNodeInfoCompat0;
    }

    @NonNull
    private AccessibilityNodeInfoCompat createNodeForHost() {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = AccessibilityNodeInfoCompat.obtain(this.mHost);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, accessibilityNodeInfoCompat0);
        ArrayList arrayList0 = new ArrayList();
        this.getVisibleVirtualViews(arrayList0);
        if(accessibilityNodeInfoCompat0.getChildCount() > 0 && arrayList0.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int v1 = arrayList0.size();
        for(int v = 0; v < v1; ++v) {
            int v2 = (int)(((Integer)arrayList0.get(v)));
            accessibilityNodeInfoCompat0.addChild(this.mHost, v2);
        }
        return accessibilityNodeInfoCompat0;
    }

    public final boolean dispatchHoverEvent(@NonNull MotionEvent motionEvent0) {
        if(this.mManager.isEnabled() && this.mManager.isTouchExplorationEnabled()) {
            int v = motionEvent0.getAction();
            if(v != 7) {
                switch(v) {
                    case 9: {
                        break;
                    }
                    case 10: {
                        if(this.mHoveredVirtualViewId != 0x80000000) {
                            this.updateHoveredVirtualView(0x80000000);
                            return true;
                        }
                        return false;
                    }
                    default: {
                        return false;
                    }
                }
            }
            int v1 = this.getVirtualViewAt(motionEvent0.getX(), motionEvent0.getY());
            this.updateHoveredVirtualView(v1);
            return v1 != 0x80000000;
        }
        return false;
    }

    public final boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent0) {
        int v = 0;
        if(keyEvent0.getAction() != 1) {
            int v1 = keyEvent0.getKeyCode();
            switch(v1) {
                case 19: 
                case 20: 
                case 21: 
                case 22: {
                    if(keyEvent0.hasNoModifiers()) {
                        int v2 = ExploreByTouchHelper.keyToDirection(v1);
                        int v3 = keyEvent0.getRepeatCount();
                        boolean z;
                        for(z = false; v < v3 + 1 && this.moveFocus(v2, null); z = true) {
                            ++v;
                        }
                        return z;
                    }
                    break;
                }
                case 61: {
                    if(keyEvent0.hasNoModifiers()) {
                        return this.moveFocus(2, null);
                    }
                    if(keyEvent0.hasModifiers(1)) {
                        v = this.moveFocus(1, null);
                    }
                    break;
                }
                case 23: 
                case 66: {
                    if(keyEvent0.hasNoModifiers() && keyEvent0.getRepeatCount() == 0) {
                        this.clickKeyboardFocusedVirtualView();
                        return true;
                    }
                    break;
                }
                default: {
                    return false;
                }
            }
        }
        return v != 0;
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    @Override  // androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view0) {
        if(this.mNodeProvider == null) {
            this.mNodeProvider = new MyNodeProvider(this);
        }
        return this.mNodeProvider;
    }

    private SparseArrayCompat getAllNodes() {
        ArrayList arrayList0 = new ArrayList();
        this.getVisibleVirtualViews(arrayList0);
        SparseArrayCompat sparseArrayCompat0 = new SparseArrayCompat();
        for(int v = 0; v < arrayList0.size(); ++v) {
            sparseArrayCompat0.put(v, this.createNodeForChild(v));
        }
        return sparseArrayCompat0;
    }

    private void getBoundsInParent(int v, Rect rect0) {
        this.obtainAccessibilityNodeInfo(v).getBoundsInParent(rect0);
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return this.getAccessibilityFocusedVirtualViewId();
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    protected abstract int getVirtualViewAt(float arg1, float arg2);

    protected abstract void getVisibleVirtualViews(List arg1);

    private static Rect guessPreviouslyFocusedRect(@NonNull View view0, int v, @NonNull Rect rect0) {
        int v1 = view0.getWidth();
        int v2 = view0.getHeight();
        switch(v) {
            case 17: {
                rect0.set(v1, 0, v1, v2);
                return rect0;
            }
            case 33: {
                rect0.set(0, v2, v1, v2);
                return rect0;
            }
            case 66: {
                rect0.set(-1, 0, -1, v2);
                return rect0;
            }
            case 130: {
                rect0.set(0, -1, v1, -1);
                return rect0;
            }
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
        }
    }

    public final void invalidateRoot() {
        this.invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int v) {
        this.invalidateVirtualView(v, 0);
    }

    public final void invalidateVirtualView(int v, int v1) {
        if(v != 0x80000000 && this.mManager.isEnabled()) {
            ViewParent viewParent0 = this.mHost.getParent();
            if(viewParent0 != null) {
                AccessibilityEvent accessibilityEvent0 = this.createEvent(v, 0x800);
                AccessibilityEventCompat.setContentChangeTypes(accessibilityEvent0, v1);
                ViewParentCompat.requestSendAccessibilityEvent(viewParent0, this.mHost, accessibilityEvent0);
            }
        }
    }

    private boolean isVisibleToUser(Rect rect0) {
        if(rect0 == null || rect0.isEmpty() || this.mHost.getWindowVisibility() != 0) {
            return false;
        }
        ViewParent viewParent0 = this.mHost.getParent();
        while(viewParent0 instanceof View) {
            if(((View)viewParent0).getAlpha() > 0.0f && ((View)viewParent0).getVisibility() == 0) {
                viewParent0 = ((View)viewParent0).getParent();
                continue;
            }
            return false;
        }
        return viewParent0 != null;
    }

    private static int keyToDirection(int v) {
        switch(v) {
            case 19: {
                return 33;
            }
            case 21: {
                return 17;
            }
            case 22: {
                return 66;
            }
            default: {
                return 130;
            }
        }
    }

    private boolean moveFocus(int v, @Nullable Rect rect0) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat1;
        SparseArrayCompat sparseArrayCompat0 = this.getAllNodes();
        int v1 = 0x80000000;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = this.mKeyboardFocusedVirtualViewId == 0x80000000 ? null : ((AccessibilityNodeInfoCompat)sparseArrayCompat0.get(this.mKeyboardFocusedVirtualViewId));
        if(v == 17 || v == 33 || v == 66 || v == 130) {
            Rect rect1 = new Rect();
            int v2 = this.mKeyboardFocusedVirtualViewId;
            if(v2 != 0x80000000) {
                this.getBoundsInParent(v2, rect1);
            }
            else if(rect0 == null) {
                ExploreByTouchHelper.guessPreviouslyFocusedRect(this.mHost, v, rect1);
            }
            else {
                rect1.set(rect0);
            }
            accessibilityNodeInfoCompat1 = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInAbsoluteDirection(sparseArrayCompat0, ExploreByTouchHelper.SPARSE_VALUES_ADAPTER, ExploreByTouchHelper.NODE_ADAPTER, accessibilityNodeInfoCompat0, rect1, v);
        }
        else {
            if(v != 1 && v != 2) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            boolean z = ViewCompat.getLayoutDirection(this.mHost) == 1;
            accessibilityNodeInfoCompat1 = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInRelativeDirection(sparseArrayCompat0, ExploreByTouchHelper.SPARSE_VALUES_ADAPTER, ExploreByTouchHelper.NODE_ADAPTER, accessibilityNodeInfoCompat0, v, z, false);
        }
        if(accessibilityNodeInfoCompat1 != null) {
            v1 = sparseArrayCompat0.keyAt(sparseArrayCompat0.indexOfValue(accessibilityNodeInfoCompat1));
        }
        return this.requestKeyboardFocusForVirtualView(v1);
    }

    @NonNull
    AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int v) {
        return v == -1 ? this.createNodeForHost() : this.createNodeForChild(v);
    }

    public final void onFocusChanged(boolean z, int v, @Nullable Rect rect0) {
        int v1 = this.mKeyboardFocusedVirtualViewId;
        if(v1 != 0x80000000) {
            this.clearKeyboardFocusForVirtualView(v1);
        }
        if(z) {
            this.moveFocus(v, rect0);
        }
    }

    @Override  // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        super.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
    }

    @Override  // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
    }

    protected abstract boolean onPerformActionForVirtualView(int arg1, int arg2, @Nullable Bundle arg3);

    protected void onPopulateEventForHost(@NonNull AccessibilityEvent accessibilityEvent0) {
    }

    protected void onPopulateEventForVirtualView(int v, @NonNull AccessibilityEvent accessibilityEvent0) {
    }

    protected void onPopulateNodeForHost(@NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
    }

    protected abstract void onPopulateNodeForVirtualView(int arg1, @NonNull AccessibilityNodeInfoCompat arg2);

    protected void onVirtualViewKeyboardFocusChanged(int v, boolean z) {
    }

    boolean performAction(int v, int v1, Bundle bundle0) {
        return v == -1 ? this.performActionForHost(v1, bundle0) : this.performActionForChild(v, v1, bundle0);
    }

    private boolean performActionForChild(int v, int v1, Bundle bundle0) {
        switch(v1) {
            case 1: {
                return this.requestKeyboardFocusForVirtualView(v);
            }
            case 2: {
                return this.clearKeyboardFocusForVirtualView(v);
            }
            case 0x40: {
                return this.requestAccessibilityFocus(v);
            }
            case 0x80: {
                return this.clearAccessibilityFocus(v);
            }
            default: {
                return this.onPerformActionForVirtualView(v, v1, bundle0);
            }
        }
    }

    private boolean performActionForHost(int v, Bundle bundle0) {
        return ViewCompat.performAccessibilityAction(this.mHost, v, bundle0);
    }

    private boolean requestAccessibilityFocus(int v) {
        if(this.mManager.isEnabled() && this.mManager.isTouchExplorationEnabled()) {
            int v1 = this.mAccessibilityFocusedVirtualViewId;
            if(v1 != v) {
                if(v1 != 0x80000000) {
                    this.clearAccessibilityFocus(v1);
                }
                this.mAccessibilityFocusedVirtualViewId = v;
                this.mHost.invalidate();
                this.sendEventForVirtualView(v, 0x8000);
                return true;
            }
            return false;
        }
        return false;
    }

    public final boolean requestKeyboardFocusForVirtualView(int v) {
        if(!this.mHost.isFocused() && !this.mHost.requestFocus()) {
            return false;
        }
        int v1 = this.mKeyboardFocusedVirtualViewId;
        if(v1 == v) {
            return false;
        }
        if(v1 != 0x80000000) {
            this.clearKeyboardFocusForVirtualView(v1);
        }
        this.mKeyboardFocusedVirtualViewId = v;
        this.sendEventForVirtualView(v, 8);
        return true;
    }

    public final boolean sendEventForVirtualView(int v, int v1) {
        if(v != 0x80000000 && this.mManager.isEnabled()) {
            ViewParent viewParent0 = this.mHost.getParent();
            if(viewParent0 == null) {
                return false;
            }
            AccessibilityEvent accessibilityEvent0 = this.createEvent(v, v1);
            return ViewParentCompat.requestSendAccessibilityEvent(viewParent0, this.mHost, accessibilityEvent0);
        }
        return false;
    }

    private void updateHoveredVirtualView(int v) {
        int v1 = this.mHoveredVirtualViewId;
        if(v1 == v) {
            return;
        }
        this.mHoveredVirtualViewId = v;
        this.sendEventForVirtualView(v, 0x80);
        this.sendEventForVirtualView(v1, 0x100);
    }
}

