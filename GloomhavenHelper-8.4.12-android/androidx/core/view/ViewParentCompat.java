package androidx.core.view;

import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

public final class ViewParentCompat {
    private static final String TAG = "ViewParentCompat";

    public static void notifySubtreeAccessibilityStateChanged(ViewParent viewParent0, View view0, View view1, int v) {
        if(Build.VERSION.SDK_INT >= 19) {
            viewParent0.notifySubtreeAccessibilityStateChanged(view0, view1, v);
        }
    }

    public static boolean onNestedFling(ViewParent viewParent0, View view0, float f, float f1, boolean z) {
        if(Build.VERSION.SDK_INT >= 21) {
            try {
                return viewParent0.onNestedFling(view0, f, f1, z);
            }
            catch(AbstractMethodError abstractMethodError0) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent0 + " does not implement interface " + "method onNestedFling", abstractMethodError0);
                return false;
            }
        }
        return viewParent0 instanceof NestedScrollingParent ? ((NestedScrollingParent)viewParent0).onNestedFling(view0, f, f1, z) : false;
    }

    public static boolean onNestedPreFling(ViewParent viewParent0, View view0, float f, float f1) {
        if(Build.VERSION.SDK_INT >= 21) {
            try {
                return viewParent0.onNestedPreFling(view0, f, f1);
            }
            catch(AbstractMethodError abstractMethodError0) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent0 + " does not implement interface " + "method onNestedPreFling", abstractMethodError0);
                return false;
            }
        }
        return viewParent0 instanceof NestedScrollingParent ? ((NestedScrollingParent)viewParent0).onNestedPreFling(view0, f, f1) : false;
    }

    public static void onNestedPreScroll(ViewParent viewParent0, View view0, int v, int v1, int[] arr_v) {
        ViewParentCompat.onNestedPreScroll(viewParent0, view0, v, v1, arr_v, 0);
    }

    public static void onNestedPreScroll(ViewParent viewParent0, View view0, int v, int v1, int[] arr_v, int v2) {
        if(viewParent0 instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2)viewParent0).onNestedPreScroll(view0, v, v1, arr_v, v2);
            return;
        }
        if(v2 == 0) {
            if(Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent0.onNestedPreScroll(view0, v, v1, arr_v);
                }
                catch(AbstractMethodError abstractMethodError0) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent0 + " does not implement interface " + "method onNestedPreScroll", abstractMethodError0);
                }
                return;
            }
            if(viewParent0 instanceof NestedScrollingParent) {
                ((NestedScrollingParent)viewParent0).onNestedPreScroll(view0, v, v1, arr_v);
            }
        }
    }

    public static void onNestedScroll(ViewParent viewParent0, View view0, int v, int v1, int v2, int v3) {
        ViewParentCompat.onNestedScroll(viewParent0, view0, v, v1, v2, v3, 0);
    }

    public static void onNestedScroll(ViewParent viewParent0, View view0, int v, int v1, int v2, int v3, int v4) {
        if(viewParent0 instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2)viewParent0).onNestedScroll(view0, v, v1, v2, v3, v4);
            return;
        }
        if(v4 == 0) {
            if(Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent0.onNestedScroll(view0, v, v1, v2, v3);
                }
                catch(AbstractMethodError abstractMethodError0) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent0 + " does not implement interface " + "method onNestedScroll", abstractMethodError0);
                }
                return;
            }
            if(viewParent0 instanceof NestedScrollingParent) {
                ((NestedScrollingParent)viewParent0).onNestedScroll(view0, v, v1, v2, v3);
            }
        }
    }

    public static void onNestedScrollAccepted(ViewParent viewParent0, View view0, View view1, int v) {
        ViewParentCompat.onNestedScrollAccepted(viewParent0, view0, view1, v, 0);
    }

    public static void onNestedScrollAccepted(ViewParent viewParent0, View view0, View view1, int v, int v1) {
        if(viewParent0 instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2)viewParent0).onNestedScrollAccepted(view0, view1, v, v1);
            return;
        }
        if(v1 == 0) {
            if(Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent0.onNestedScrollAccepted(view0, view1, v);
                }
                catch(AbstractMethodError abstractMethodError0) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent0 + " does not implement interface " + "method onNestedScrollAccepted", abstractMethodError0);
                }
                return;
            }
            if(viewParent0 instanceof NestedScrollingParent) {
                ((NestedScrollingParent)viewParent0).onNestedScrollAccepted(view0, view1, v);
            }
        }
    }

    public static boolean onStartNestedScroll(ViewParent viewParent0, View view0, View view1, int v) {
        return ViewParentCompat.onStartNestedScroll(viewParent0, view0, view1, v, 0);
    }

    public static boolean onStartNestedScroll(ViewParent viewParent0, View view0, View view1, int v, int v1) {
        if(viewParent0 instanceof NestedScrollingParent2) {
            return ((NestedScrollingParent2)viewParent0).onStartNestedScroll(view0, view1, v, v1);
        }
        if(v1 == 0) {
            if(Build.VERSION.SDK_INT >= 21) {
                try {
                    return viewParent0.onStartNestedScroll(view0, view1, v);
                }
                catch(AbstractMethodError abstractMethodError0) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent0 + " does not implement interface " + "method onStartNestedScroll", abstractMethodError0);
                    return false;
                }
            }
            return viewParent0 instanceof NestedScrollingParent ? ((NestedScrollingParent)viewParent0).onStartNestedScroll(view0, view1, v) : false;
        }
        return false;
    }

    public static void onStopNestedScroll(ViewParent viewParent0, View view0) {
        ViewParentCompat.onStopNestedScroll(viewParent0, view0, 0);
    }

    public static void onStopNestedScroll(ViewParent viewParent0, View view0, int v) {
        if(viewParent0 instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2)viewParent0).onStopNestedScroll(view0, v);
            return;
        }
        if(v == 0) {
            if(Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent0.onStopNestedScroll(view0);
                }
                catch(AbstractMethodError abstractMethodError0) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent0 + " does not implement interface " + "method onStopNestedScroll", abstractMethodError0);
                }
                return;
            }
            if(viewParent0 instanceof NestedScrollingParent) {
                ((NestedScrollingParent)viewParent0).onStopNestedScroll(view0);
            }
        }
    }

    @Deprecated
    public static boolean requestSendAccessibilityEvent(ViewParent viewParent0, View view0, AccessibilityEvent accessibilityEvent0) {
        return viewParent0.requestSendAccessibilityEvent(view0, accessibilityEvent0);
    }
}

