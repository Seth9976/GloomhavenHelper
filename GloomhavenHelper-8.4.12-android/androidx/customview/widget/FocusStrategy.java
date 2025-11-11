package androidx.customview.widget;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class FocusStrategy {
    public interface BoundsAdapter {
        void obtainBounds(Object arg1, Rect arg2);
    }

    public interface CollectionAdapter {
        Object get(Object arg1, int arg2);

        int size(Object arg1);
    }

    static class SequentialComparator implements Comparator {
        private final BoundsAdapter mAdapter;
        private final boolean mIsLayoutRtl;
        private final Rect mTemp1;
        private final Rect mTemp2;

        SequentialComparator(boolean z, BoundsAdapter focusStrategy$BoundsAdapter0) {
            this.mTemp1 = new Rect();
            this.mTemp2 = new Rect();
            this.mIsLayoutRtl = z;
            this.mAdapter = focusStrategy$BoundsAdapter0;
        }

        @Override
        public int compare(Object object0, Object object1) {
            Rect rect0 = this.mTemp1;
            Rect rect1 = this.mTemp2;
            this.mAdapter.obtainBounds(object0, rect0);
            this.mAdapter.obtainBounds(object1, rect1);
            if(rect0.top < rect1.top) {
                return -1;
            }
            if(rect0.top > rect1.top) {
                return 1;
            }
            if(rect0.left < rect1.left) {
                return this.mIsLayoutRtl ? 1 : -1;
            }
            if(rect0.left > rect1.left) {
                return this.mIsLayoutRtl ? -1 : 1;
            }
            if(rect0.bottom < rect1.bottom) {
                return -1;
            }
            if(rect0.bottom > rect1.bottom) {
                return 1;
            }
            if(rect0.right < rect1.right) {
                return this.mIsLayoutRtl ? 1 : -1;
            }
            if(rect0.right > rect1.right) {
                return this.mIsLayoutRtl ? -1 : 1;
            }
            return 0;
        }
    }

    private static boolean beamBeats(int v, @NonNull Rect rect0, @NonNull Rect rect1, @NonNull Rect rect2) {
        boolean z = FocusStrategy.beamsOverlap(v, rect0, rect1);
        if(!FocusStrategy.beamsOverlap(v, rect0, rect2) && z) {
            return FocusStrategy.isToDirectionOf(v, rect0, rect2) ? v == 17 || v == 66 || FocusStrategy.majorAxisDistance(v, rect0, rect1) < FocusStrategy.majorAxisDistanceToFarEdge(v, rect0, rect2) : true;
        }
        return false;
    }

    private static boolean beamsOverlap(int v, @NonNull Rect rect0, @NonNull Rect rect1) {
        switch(v) {
            case 17: 
            case 66: {
                return rect1.bottom >= rect0.top && rect1.top <= rect0.bottom;
            }
            case 33: 
            case 130: {
                return rect1.right >= rect0.left && rect1.left <= rect0.right;
            }
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
        }
    }

    public static Object findNextFocusInAbsoluteDirection(@NonNull Object object0, @NonNull CollectionAdapter focusStrategy$CollectionAdapter0, @NonNull BoundsAdapter focusStrategy$BoundsAdapter0, @Nullable Object object1, @NonNull Rect rect0, int v) {
        Rect rect1 = new Rect(rect0);
        switch(v) {
            case 17: {
                rect1.offset(rect0.width() + 1, 0);
                break;
            }
            case 33: {
                rect1.offset(0, rect0.height() + 1);
                break;
            }
            case 66: {
                rect1.offset(-(rect0.width() + 1), 0);
                break;
            }
            case 130: {
                rect1.offset(0, -(rect0.height() + 1));
                break;
            }
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
        }
        Object object2 = null;
        int v2 = focusStrategy$CollectionAdapter0.size(object0);
        Rect rect2 = new Rect();
        for(int v1 = 0; v1 < v2; ++v1) {
            Object object3 = focusStrategy$CollectionAdapter0.get(object0, v1);
            if(object3 != object1) {
                focusStrategy$BoundsAdapter0.obtainBounds(object3, rect2);
                if(FocusStrategy.isBetterCandidate(v, rect0, rect2, rect1)) {
                    rect1.set(rect2);
                    object2 = object3;
                }
            }
        }
        return object2;
    }

    public static Object findNextFocusInRelativeDirection(@NonNull Object object0, @NonNull CollectionAdapter focusStrategy$CollectionAdapter0, @NonNull BoundsAdapter focusStrategy$BoundsAdapter0, @Nullable Object object1, int v, boolean z, boolean z1) {
        int v1 = focusStrategy$CollectionAdapter0.size(object0);
        ArrayList arrayList0 = new ArrayList(v1);
        for(int v2 = 0; v2 < v1; ++v2) {
            arrayList0.add(focusStrategy$CollectionAdapter0.get(object0, v2));
        }
        Collections.sort(arrayList0, new SequentialComparator(z, focusStrategy$BoundsAdapter0));
        switch(v) {
            case 1: {
                return FocusStrategy.getPreviousFocusable(object1, arrayList0, z1);
            }
            case 2: {
                return FocusStrategy.getNextFocusable(object1, arrayList0, z1);
            }
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
            }
        }
    }

    private static Object getNextFocusable(Object object0, ArrayList arrayList0, boolean z) {
        int v = arrayList0.size();
        int v1 = object0 == null ? -1 : arrayList0.lastIndexOf(object0);
        if(v1 + 1 < v) {
            return arrayList0.get(v1 + 1);
        }
        return !z || v <= 0 ? null : arrayList0.get(0);
    }

    private static Object getPreviousFocusable(Object object0, ArrayList arrayList0, boolean z) {
        int v = arrayList0.size();
        int v1 = object0 == null ? v : arrayList0.indexOf(object0);
        if(v1 - 1 >= 0) {
            return arrayList0.get(v1 - 1);
        }
        return !z || v <= 0 ? null : arrayList0.get(v - 1);
    }

    private static int getWeightedDistanceFor(int v, int v1) {
        return v * 13 * v + v1 * v1;
    }

    private static boolean isBetterCandidate(int v, @NonNull Rect rect0, @NonNull Rect rect1, @NonNull Rect rect2) {
        if(!FocusStrategy.isCandidate(rect0, rect1, v)) {
            return false;
        }
        if(!FocusStrategy.isCandidate(rect0, rect2, v)) {
            return true;
        }
        if(FocusStrategy.beamBeats(v, rect0, rect1, rect2)) {
            return true;
        }
        return FocusStrategy.beamBeats(v, rect0, rect2, rect1) ? false : FocusStrategy.getWeightedDistanceFor(FocusStrategy.majorAxisDistance(v, rect0, rect1), FocusStrategy.minorAxisDistance(v, rect0, rect1)) < FocusStrategy.getWeightedDistanceFor(FocusStrategy.majorAxisDistance(v, rect0, rect2), FocusStrategy.minorAxisDistance(v, rect0, rect2));
    }

    private static boolean isCandidate(@NonNull Rect rect0, @NonNull Rect rect1, int v) {
        switch(v) {
            case 17: {
                return (rect0.right > rect1.right || rect0.left >= rect1.right) && rect0.left > rect1.left;
            }
            case 33: {
                return (rect0.bottom > rect1.bottom || rect0.top >= rect1.bottom) && rect0.top > rect1.top;
            }
            case 66: {
                return (rect0.left < rect1.left || rect0.right <= rect1.left) && rect0.right < rect1.right;
            }
            case 130: {
                return (rect0.top < rect1.top || rect0.bottom <= rect1.top) && rect0.bottom < rect1.bottom;
            }
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
        }
    }

    private static boolean isToDirectionOf(int v, @NonNull Rect rect0, @NonNull Rect rect1) {
        switch(v) {
            case 17: {
                return rect0.left >= rect1.right;
            }
            case 33: {
                return rect0.top >= rect1.bottom;
            }
            case 66: {
                return rect0.right <= rect1.left;
            }
            case 130: {
                return rect0.bottom <= rect1.top;
            }
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
        }
    }

    private static int majorAxisDistance(int v, @NonNull Rect rect0, @NonNull Rect rect1) {
        return Math.max(0, FocusStrategy.majorAxisDistanceRaw(v, rect0, rect1));
    }

    private static int majorAxisDistanceRaw(int v, @NonNull Rect rect0, @NonNull Rect rect1) {
        switch(v) {
            case 17: {
                return rect0.left - rect1.right;
            }
            case 33: {
                return rect0.top - rect1.bottom;
            }
            case 66: {
                return rect1.left - rect0.right;
            }
            case 130: {
                return rect1.top - rect0.bottom;
            }
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
        }
    }

    private static int majorAxisDistanceToFarEdge(int v, @NonNull Rect rect0, @NonNull Rect rect1) {
        return Math.max(1, FocusStrategy.majorAxisDistanceToFarEdgeRaw(v, rect0, rect1));
    }

    private static int majorAxisDistanceToFarEdgeRaw(int v, @NonNull Rect rect0, @NonNull Rect rect1) {
        switch(v) {
            case 17: {
                return rect0.left - rect1.left;
            }
            case 33: {
                return rect0.top - rect1.top;
            }
            case 66: {
                return rect1.right - rect0.right;
            }
            case 130: {
                return rect1.bottom - rect0.bottom;
            }
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
        }
    }

    private static int minorAxisDistance(int v, @NonNull Rect rect0, @NonNull Rect rect1) {
        switch(v) {
            case 17: 
            case 66: {
                return Math.abs(rect0.top + rect0.height() / 2 - (rect1.top + rect1.height() / 2));
            }
            case 33: 
            case 130: {
                return Math.abs(rect0.left + rect0.width() / 2 - (rect1.left + rect1.width() / 2));
            }
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
        }
    }
}

