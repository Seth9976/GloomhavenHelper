package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FragmentTransition {
    static class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

    }

    private static final int[] INVERSE_OPS;
    private static final FragmentTransitionImpl PLATFORM_IMPL;
    private static final FragmentTransitionImpl SUPPORT_IMPL;

    static {
        FragmentTransition.INVERSE_OPS = new int[]{0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
        FragmentTransition.PLATFORM_IMPL = Build.VERSION.SDK_INT < 21 ? null : new FragmentTransitionCompat21();
        FragmentTransition.SUPPORT_IMPL = FragmentTransition.resolveSupportImpl();
    }

    private static void addSharedElementsWithMatchingNames(ArrayList arrayList0, ArrayMap arrayMap0, Collection collection0) {
        for(int v = arrayMap0.size() - 1; v >= 0; --v) {
            View view0 = (View)arrayMap0.valueAt(v);
            if(collection0.contains(ViewCompat.getTransitionName(view0))) {
                arrayList0.add(view0);
            }
        }
    }

    private static void addToFirstInLastOut(BackStackRecord backStackRecord0, Op backStackRecord$Op0, SparseArray sparseArray0, boolean z, boolean z1) {
        FragmentContainerTransition fragmentTransition$FragmentContainerTransition2;
        int v2;
        boolean z4;
        boolean z3;
        int v3;
        boolean z5;
        int v4;
        Fragment fragment0 = backStackRecord$Op0.fragment;
        if(fragment0 == null) {
            return;
        }
        int v = fragment0.mContainerId;
        if(v == 0) {
            return;
        }
        int v1 = z ? FragmentTransition.INVERSE_OPS[backStackRecord$Op0.cmd] : backStackRecord$Op0.cmd;
        boolean z2 = false;
        if(v1 == 1) {
        label_48:
            z2 = z1 ? fragment0.mIsNewlyAdded : !fragment0.mAdded && !fragment0.mHidden;
            z3 = true;
            z4 = false;
            v2 = 0;
        }
        else {
            switch(v1) {
                case 4: {
                    if(!z1) {
                        v4 = !fragment0.mAdded || fragment0.mHidden ? 0 : 1;
                    }
                    else if(fragment0.mHiddenChanged && fragment0.mAdded && fragment0.mHidden) {
                        v4 = 1;
                    }
                    else {
                        v4 = 0;
                    }
                    v2 = v4;
                    z3 = false;
                    z4 = true;
                    break;
                }
                case 5: {
                    if(!z1) {
                        z5 = fragment0.mHidden;
                    }
                    else if(fragment0.mHiddenChanged && !fragment0.mHidden && fragment0.mAdded) {
                        z5 = true;
                    }
                    else {
                        z5 = false;
                    }
                    z2 = z5;
                    z3 = true;
                    z4 = false;
                    v2 = 0;
                    break;
                }
                case 3: 
                case 6: {
                    if(!z1) {
                        v3 = !fragment0.mAdded || fragment0.mHidden ? 0 : 1;
                    }
                    else if(!fragment0.mAdded && fragment0.mView != null && fragment0.mView.getVisibility() == 0 && fragment0.mPostponedAlpha >= 0.0f) {
                        v3 = 1;
                    }
                    else {
                        v3 = 0;
                    }
                    v2 = v3;
                    z3 = false;
                    z4 = true;
                    break;
                }
                case 7: {
                    goto label_48;
                }
                default: {
                    z3 = false;
                    z4 = false;
                    v2 = 0;
                }
            }
        }
        FragmentContainerTransition fragmentTransition$FragmentContainerTransition0 = (FragmentContainerTransition)sparseArray0.get(v);
        if(z2) {
            FragmentContainerTransition fragmentTransition$FragmentContainerTransition1 = FragmentTransition.ensureContainer(fragmentTransition$FragmentContainerTransition0, sparseArray0, v);
            fragmentTransition$FragmentContainerTransition1.lastIn = fragment0;
            fragmentTransition$FragmentContainerTransition1.lastInIsPop = z;
            fragmentTransition$FragmentContainerTransition1.lastInTransaction = backStackRecord0;
            fragmentTransition$FragmentContainerTransition2 = fragmentTransition$FragmentContainerTransition1;
        }
        else {
            fragmentTransition$FragmentContainerTransition2 = fragmentTransition$FragmentContainerTransition0;
        }
        if(!z1 && z3) {
            if(fragmentTransition$FragmentContainerTransition2 != null && fragmentTransition$FragmentContainerTransition2.firstOut == fragment0) {
                fragmentTransition$FragmentContainerTransition2.firstOut = null;
            }
            FragmentManagerImpl fragmentManagerImpl0 = backStackRecord0.mManager;
            if(fragment0.mState < 1 && fragmentManagerImpl0.mCurState >= 1 && !backStackRecord0.mReorderingAllowed) {
                fragmentManagerImpl0.makeActive(fragment0);
                fragmentManagerImpl0.moveToState(fragment0, 1, 0, 0, false);
            }
        }
        if(v2 != 0 && (fragmentTransition$FragmentContainerTransition2 == null || fragmentTransition$FragmentContainerTransition2.firstOut == null)) {
            fragmentTransition$FragmentContainerTransition2 = FragmentTransition.ensureContainer(fragmentTransition$FragmentContainerTransition2, sparseArray0, v);
            fragmentTransition$FragmentContainerTransition2.firstOut = fragment0;
            fragmentTransition$FragmentContainerTransition2.firstOutIsPop = z;
            fragmentTransition$FragmentContainerTransition2.firstOutTransaction = backStackRecord0;
        }
        if(!z1 && z4 && fragmentTransition$FragmentContainerTransition2 != null && fragmentTransition$FragmentContainerTransition2.lastIn == fragment0) {
            fragmentTransition$FragmentContainerTransition2.lastIn = null;
        }
    }

    public static void calculateFragments(BackStackRecord backStackRecord0, SparseArray sparseArray0, boolean z) {
        int v = backStackRecord0.mOps.size();
        for(int v1 = 0; v1 < v; ++v1) {
            FragmentTransition.addToFirstInLastOut(backStackRecord0, ((Op)backStackRecord0.mOps.get(v1)), sparseArray0, false, z);
        }
    }

    private static ArrayMap calculateNameOverrides(int v, ArrayList arrayList0, ArrayList arrayList1, int v1, int v2) {
        ArrayList arrayList3;
        ArrayList arrayList2;
        ArrayMap arrayMap0 = new ArrayMap();
        for(int v3 = v2 - 1; v3 >= v1; --v3) {
            BackStackRecord backStackRecord0 = (BackStackRecord)arrayList0.get(v3);
            if(backStackRecord0.interactsWith(v)) {
                boolean z = ((Boolean)arrayList1.get(v3)).booleanValue();
                if(backStackRecord0.mSharedElementSourceNames != null) {
                    int v4 = backStackRecord0.mSharedElementSourceNames.size();
                    if(z) {
                        arrayList2 = backStackRecord0.mSharedElementSourceNames;
                        arrayList3 = backStackRecord0.mSharedElementTargetNames;
                    }
                    else {
                        arrayList2 = backStackRecord0.mSharedElementTargetNames;
                        arrayList3 = backStackRecord0.mSharedElementSourceNames;
                    }
                    for(int v5 = 0; v5 < v4; ++v5) {
                        String s = (String)arrayList3.get(v5);
                        String s1 = (String)arrayList2.get(v5);
                        String s2 = (String)arrayMap0.remove(s1);
                        if(s2 == null) {
                            arrayMap0.put(s, s1);
                        }
                        else {
                            arrayMap0.put(s, s2);
                        }
                    }
                }
            }
        }
        return arrayMap0;
    }

    public static void calculatePopFragments(BackStackRecord backStackRecord0, SparseArray sparseArray0, boolean z) {
        if(!backStackRecord0.mManager.mContainer.onHasView()) {
            return;
        }
        for(int v = backStackRecord0.mOps.size() - 1; v >= 0; --v) {
            FragmentTransition.addToFirstInLastOut(backStackRecord0, ((Op)backStackRecord0.mOps.get(v)), sparseArray0, true, z);
        }
    }

    static void callSharedElementStartEnd(Fragment fragment0, Fragment fragment1, boolean z, ArrayMap arrayMap0, boolean z1) {
        if((z ? fragment1.getEnterTransitionCallback() : fragment0.getEnterTransitionCallback()) != null) {
            ArrayList arrayList0 = new ArrayList();
            ArrayList arrayList1 = new ArrayList();
            int v1 = arrayMap0 == null ? 0 : arrayMap0.size();
            for(int v = 0; v < v1; ++v) {
                arrayList1.add(arrayMap0.keyAt(v));
                arrayList0.add(arrayMap0.valueAt(v));
            }
            if(z1) {
            }
        }
    }

    private static boolean canHandleAll(FragmentTransitionImpl fragmentTransitionImpl0, List list0) {
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(!fragmentTransitionImpl0.canHandle(list0.get(v1))) {
                return false;
            }
        }
        return true;
    }

    static ArrayMap captureInSharedElements(FragmentTransitionImpl fragmentTransitionImpl0, ArrayMap arrayMap0, Object object0, FragmentContainerTransition fragmentTransition$FragmentContainerTransition0) {
        ArrayList arrayList0;
        SharedElementCallback sharedElementCallback0;
        Fragment fragment0 = fragmentTransition$FragmentContainerTransition0.lastIn;
        View view0 = fragment0.getView();
        if(!arrayMap0.isEmpty() && object0 != null && view0 != null) {
            ArrayMap arrayMap1 = new ArrayMap();
            fragmentTransitionImpl0.findNamedViews(arrayMap1, view0);
            BackStackRecord backStackRecord0 = fragmentTransition$FragmentContainerTransition0.lastInTransaction;
            if(fragmentTransition$FragmentContainerTransition0.lastInIsPop) {
                sharedElementCallback0 = fragment0.getExitTransitionCallback();
                arrayList0 = backStackRecord0.mSharedElementSourceNames;
            }
            else {
                sharedElementCallback0 = fragment0.getEnterTransitionCallback();
                arrayList0 = backStackRecord0.mSharedElementTargetNames;
            }
            if(arrayList0 != null) {
                arrayMap1.retainAll(arrayList0);
                arrayMap1.retainAll(arrayMap0.values());
            }
            if(sharedElementCallback0 != null) {
                for(int v = arrayList0.size() - 1; v >= 0; --v) {
                    String s = (String)arrayList0.get(v);
                    View view1 = (View)arrayMap1.get(s);
                    if(view1 == null) {
                        String s1 = FragmentTransition.findKeyForValue(arrayMap0, s);
                        if(s1 != null) {
                            arrayMap0.remove(s1);
                        }
                    }
                    else if(!s.equals(ViewCompat.getTransitionName(view1))) {
                        String s2 = FragmentTransition.findKeyForValue(arrayMap0, s);
                        if(s2 != null) {
                            arrayMap0.put(s2, ViewCompat.getTransitionName(view1));
                        }
                    }
                }
                return arrayMap1;
            }
            FragmentTransition.retainValues(arrayMap0, arrayMap1);
            return arrayMap1;
        }
        arrayMap0.clear();
        return null;
    }

    private static ArrayMap captureOutSharedElements(FragmentTransitionImpl fragmentTransitionImpl0, ArrayMap arrayMap0, Object object0, FragmentContainerTransition fragmentTransition$FragmentContainerTransition0) {
        ArrayList arrayList0;
        SharedElementCallback sharedElementCallback0;
        if(!arrayMap0.isEmpty() && object0 != null) {
            Fragment fragment0 = fragmentTransition$FragmentContainerTransition0.firstOut;
            ArrayMap arrayMap1 = new ArrayMap();
            fragmentTransitionImpl0.findNamedViews(arrayMap1, fragment0.getView());
            BackStackRecord backStackRecord0 = fragmentTransition$FragmentContainerTransition0.firstOutTransaction;
            if(fragmentTransition$FragmentContainerTransition0.firstOutIsPop) {
                sharedElementCallback0 = fragment0.getEnterTransitionCallback();
                arrayList0 = backStackRecord0.mSharedElementTargetNames;
            }
            else {
                sharedElementCallback0 = fragment0.getExitTransitionCallback();
                arrayList0 = backStackRecord0.mSharedElementSourceNames;
            }
            arrayMap1.retainAll(arrayList0);
            if(sharedElementCallback0 != null) {
                for(int v = arrayList0.size() - 1; v >= 0; --v) {
                    String s = (String)arrayList0.get(v);
                    View view0 = (View)arrayMap1.get(s);
                    if(view0 == null) {
                        arrayMap0.remove(s);
                    }
                    else if(!s.equals(ViewCompat.getTransitionName(view0))) {
                        String s1 = (String)arrayMap0.remove(s);
                        arrayMap0.put(ViewCompat.getTransitionName(view0), s1);
                    }
                }
                return arrayMap1;
            }
            arrayMap0.retainAll(arrayMap1.keySet());
            return arrayMap1;
        }
        arrayMap0.clear();
        return null;
    }

    private static FragmentTransitionImpl chooseImpl(Fragment fragment0, Fragment fragment1) {
        ArrayList arrayList0 = new ArrayList();
        if(fragment0 != null) {
            Object object0 = fragment0.getExitTransition();
            if(object0 != null) {
                arrayList0.add(object0);
            }
            Object object1 = fragment0.getReturnTransition();
            if(object1 != null) {
                arrayList0.add(object1);
            }
            Object object2 = fragment0.getSharedElementReturnTransition();
            if(object2 != null) {
                arrayList0.add(object2);
            }
        }
        if(fragment1 != null) {
            Object object3 = fragment1.getEnterTransition();
            if(object3 != null) {
                arrayList0.add(object3);
            }
            Object object4 = fragment1.getReenterTransition();
            if(object4 != null) {
                arrayList0.add(object4);
            }
            Object object5 = fragment1.getSharedElementEnterTransition();
            if(object5 != null) {
                arrayList0.add(object5);
            }
        }
        if(arrayList0.isEmpty()) {
            return null;
        }
        if(FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.canHandleAll(FragmentTransition.PLATFORM_IMPL, arrayList0)) {
            return FragmentTransition.PLATFORM_IMPL;
        }
        if(FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.canHandleAll(FragmentTransition.SUPPORT_IMPL, arrayList0)) {
            return FragmentTransition.SUPPORT_IMPL;
        }
        if(FragmentTransition.PLATFORM_IMPL != null || FragmentTransition.SUPPORT_IMPL != null) {
            throw new IllegalArgumentException("Invalid Transition types");
        }
        return null;
    }

    static ArrayList configureEnteringExitingViews(FragmentTransitionImpl fragmentTransitionImpl0, Object object0, Fragment fragment0, ArrayList arrayList0, View view0) {
        if(object0 != null) {
            ArrayList arrayList1 = new ArrayList();
            View view1 = fragment0.getView();
            if(view1 != null) {
                fragmentTransitionImpl0.captureTransitioningViews(arrayList1, view1);
            }
            if(arrayList0 != null) {
                arrayList1.removeAll(arrayList0);
            }
            if(!arrayList1.isEmpty()) {
                arrayList1.add(view0);
                fragmentTransitionImpl0.addTargets(object0, arrayList1);
                return arrayList1;
            }
            return arrayList1;
        }
        return null;
    }

    private static Object configureSharedElementsOrdered(FragmentTransitionImpl fragmentTransitionImpl0, ViewGroup viewGroup0, View view0, ArrayMap arrayMap0, FragmentContainerTransition fragmentTransition$FragmentContainerTransition0, ArrayList arrayList0, ArrayList arrayList1, Object object0, Object object1) {
        Rect rect0;
        Object object3;
        Fragment fragment0 = fragmentTransition$FragmentContainerTransition0.lastIn;
        Fragment fragment1 = fragmentTransition$FragmentContainerTransition0.firstOut;
        if(fragment0 != null && fragment1 != null) {
            boolean z = fragmentTransition$FragmentContainerTransition0.lastInIsPop;
            Object object2 = arrayMap0.isEmpty() ? null : FragmentTransition.getSharedElementTransition(fragmentTransitionImpl0, fragment0, fragment1, z);
            ArrayMap arrayMap1 = FragmentTransition.captureOutSharedElements(fragmentTransitionImpl0, arrayMap0, object2, fragmentTransition$FragmentContainerTransition0);
            if(arrayMap0.isEmpty()) {
                object3 = null;
            }
            else {
                arrayList0.addAll(arrayMap1.values());
                object3 = object2;
            }
            if(object0 == null && object1 == null && object3 == null) {
                return null;
            }
            FragmentTransition.callSharedElementStartEnd(fragment0, fragment1, z, arrayMap1, true);
            if(object3 == null) {
                rect0 = null;
            }
            else {
                rect0 = new Rect();
                fragmentTransitionImpl0.setSharedElementTargets(object3, view0, arrayList0);
                FragmentTransition.setOutEpicenter(fragmentTransitionImpl0, object3, object1, arrayMap1, fragmentTransition$FragmentContainerTransition0.firstOutIsPop, fragmentTransition$FragmentContainerTransition0.firstOutTransaction);
                if(object0 != null) {
                    fragmentTransitionImpl0.setEpicenter(object0, rect0);
                }
            }
            OneShotPreDrawListener.add(viewGroup0, new Runnable() {
                @Override
                public void run() {
                    ArrayMap arrayMap0 = FragmentTransition.captureInSharedElements(fragmentTransitionImpl0, arrayMap0, object3, fragmentTransition$FragmentContainerTransition0);
                    if(arrayMap0 != null) {
                        Collection collection0 = arrayMap0.values();
                        arrayList1.addAll(collection0);
                        arrayList1.add(view0);
                    }
                    FragmentTransition.callSharedElementStartEnd(fragment0, fragment1, z, arrayMap0, false);
                    Object object0 = object3;
                    if(object0 != null) {
                        fragmentTransitionImpl0.swapSharedElementTargets(object0, arrayList0, arrayList1);
                        View view0 = FragmentTransition.getInEpicenterView(arrayMap0, fragmentTransition$FragmentContainerTransition0, object0, z);
                        if(view0 != null) {
                            fragmentTransitionImpl0.getBoundsOnScreen(view0, rect0);
                        }
                    }
                }
            });
            return object3;
        }
        return null;
    }

    private static Object configureSharedElementsReordered(FragmentTransitionImpl fragmentTransitionImpl0, ViewGroup viewGroup0, View view0, ArrayMap arrayMap0, FragmentContainerTransition fragmentTransition$FragmentContainerTransition0, ArrayList arrayList0, ArrayList arrayList1, Object object0, Object object1) {
        View view2;
        Rect rect1;
        Object object3;
        Fragment fragment0 = fragmentTransition$FragmentContainerTransition0.lastIn;
        Fragment fragment1 = fragmentTransition$FragmentContainerTransition0.firstOut;
        if(fragment0 != null) {
            fragment0.getView().setVisibility(0);
        }
        if(fragment0 != null && fragment1 != null) {
            boolean z = fragmentTransition$FragmentContainerTransition0.lastInIsPop;
            Object object2 = arrayMap0.isEmpty() ? null : FragmentTransition.getSharedElementTransition(fragmentTransitionImpl0, fragment0, fragment1, z);
            ArrayMap arrayMap1 = FragmentTransition.captureOutSharedElements(fragmentTransitionImpl0, arrayMap0, object2, fragmentTransition$FragmentContainerTransition0);
            ArrayMap arrayMap2 = FragmentTransition.captureInSharedElements(fragmentTransitionImpl0, arrayMap0, object2, fragmentTransition$FragmentContainerTransition0);
            if(arrayMap0.isEmpty()) {
                if(arrayMap1 != null) {
                    arrayMap1.clear();
                }
                if(arrayMap2 != null) {
                    arrayMap2.clear();
                }
                object3 = null;
            }
            else {
                FragmentTransition.addSharedElementsWithMatchingNames(arrayList0, arrayMap1, arrayMap0.keySet());
                FragmentTransition.addSharedElementsWithMatchingNames(arrayList1, arrayMap2, arrayMap0.values());
                object3 = object2;
            }
            if(object0 == null && object1 == null && object3 == null) {
                return null;
            }
            FragmentTransition.callSharedElementStartEnd(fragment0, fragment1, z, arrayMap1, true);
            if(object3 == null) {
                view2 = null;
                rect1 = null;
            }
            else {
                arrayList1.add(view0);
                fragmentTransitionImpl0.setSharedElementTargets(object3, view0, arrayList0);
                FragmentTransition.setOutEpicenter(fragmentTransitionImpl0, object3, object1, arrayMap1, fragmentTransition$FragmentContainerTransition0.firstOutIsPop, fragmentTransition$FragmentContainerTransition0.firstOutTransaction);
                Rect rect0 = new Rect();
                View view1 = FragmentTransition.getInEpicenterView(arrayMap2, fragmentTransition$FragmentContainerTransition0, object0, z);
                if(view1 != null) {
                    fragmentTransitionImpl0.setEpicenter(object0, rect0);
                }
                rect1 = rect0;
                view2 = view1;
            }
            OneShotPreDrawListener.add(viewGroup0, new Runnable() {
                @Override
                public void run() {
                    FragmentTransition.callSharedElementStartEnd(fragment0, fragment1, z, arrayMap2, false);
                    View view0 = view2;
                    if(view0 != null) {
                        fragmentTransitionImpl0.getBoundsOnScreen(view0, rect1);
                    }
                }
            });
            return object3;
        }
        return null;
    }

    private static void configureTransitionsOrdered(FragmentManagerImpl fragmentManagerImpl0, int v, FragmentContainerTransition fragmentTransition$FragmentContainerTransition0, View view0, ArrayMap arrayMap0) {
        Object object3;
        ViewGroup viewGroup0 = fragmentManagerImpl0.mContainer.onHasView() ? ((ViewGroup)fragmentManagerImpl0.mContainer.onFindViewById(v)) : null;
        if(viewGroup0 == null) {
            return;
        }
        Fragment fragment0 = fragmentTransition$FragmentContainerTransition0.lastIn;
        Fragment fragment1 = fragmentTransition$FragmentContainerTransition0.firstOut;
        FragmentTransitionImpl fragmentTransitionImpl0 = FragmentTransition.chooseImpl(fragment1, fragment0);
        if(fragmentTransitionImpl0 == null) {
            return;
        }
        boolean z = fragmentTransition$FragmentContainerTransition0.firstOutIsPop;
        Object object0 = FragmentTransition.getEnterTransition(fragmentTransitionImpl0, fragment0, fragmentTransition$FragmentContainerTransition0.lastInIsPop);
        Object object1 = FragmentTransition.getExitTransition(fragmentTransitionImpl0, fragment1, z);
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        Object object2 = FragmentTransition.configureSharedElementsOrdered(fragmentTransitionImpl0, viewGroup0, view0, arrayMap0, fragmentTransition$FragmentContainerTransition0, arrayList0, arrayList1, object0, object1);
        if(object0 != null || object2 != null) {
            object3 = object1;
        }
        else {
            object3 = object1;
            if(object3 == null) {
                return;
            }
        }
        ArrayList arrayList2 = FragmentTransition.configureEnteringExitingViews(fragmentTransitionImpl0, object3, fragment1, arrayList0, view0);
        Object object4 = arrayList2 == null || arrayList2.isEmpty() ? null : object3;
        fragmentTransitionImpl0.addTarget(object0, view0);
        Object object5 = FragmentTransition.mergeTransitions(fragmentTransitionImpl0, object0, object4, object2, fragment0, fragmentTransition$FragmentContainerTransition0.lastInIsPop);
        if(object5 != null) {
            ArrayList arrayList3 = new ArrayList();
            fragmentTransitionImpl0.scheduleRemoveTargets(object5, object0, arrayList3, object4, arrayList2, object2, arrayList1);
            FragmentTransition.scheduleTargetChange(fragmentTransitionImpl0, viewGroup0, fragment0, view0, arrayList1, object0, arrayList3, object4, arrayList2);
            fragmentTransitionImpl0.setNameOverridesOrdered(viewGroup0, arrayList1, arrayMap0);
            fragmentTransitionImpl0.beginDelayedTransition(viewGroup0, object5);
            fragmentTransitionImpl0.scheduleNameReset(viewGroup0, arrayList1, arrayMap0);
        }
    }

    private static void configureTransitionsReordered(FragmentManagerImpl fragmentManagerImpl0, int v, FragmentContainerTransition fragmentTransition$FragmentContainerTransition0, View view0, ArrayMap arrayMap0) {
        Object object3;
        ViewGroup viewGroup0 = fragmentManagerImpl0.mContainer.onHasView() ? ((ViewGroup)fragmentManagerImpl0.mContainer.onFindViewById(v)) : null;
        if(viewGroup0 == null) {
            return;
        }
        Fragment fragment0 = fragmentTransition$FragmentContainerTransition0.lastIn;
        Fragment fragment1 = fragmentTransition$FragmentContainerTransition0.firstOut;
        FragmentTransitionImpl fragmentTransitionImpl0 = FragmentTransition.chooseImpl(fragment1, fragment0);
        if(fragmentTransitionImpl0 == null) {
            return;
        }
        boolean z = fragmentTransition$FragmentContainerTransition0.lastInIsPop;
        boolean z1 = fragmentTransition$FragmentContainerTransition0.firstOutIsPop;
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        Object object0 = FragmentTransition.getEnterTransition(fragmentTransitionImpl0, fragment0, z);
        Object object1 = FragmentTransition.getExitTransition(fragmentTransitionImpl0, fragment1, z1);
        Object object2 = FragmentTransition.configureSharedElementsReordered(fragmentTransitionImpl0, viewGroup0, view0, arrayMap0, fragmentTransition$FragmentContainerTransition0, arrayList1, arrayList0, object0, object1);
        if(object0 != null || object2 != null) {
            object3 = object1;
        }
        else {
            object3 = object1;
            if(object3 == null) {
                return;
            }
        }
        ArrayList arrayList2 = FragmentTransition.configureEnteringExitingViews(fragmentTransitionImpl0, object3, fragment1, arrayList1, view0);
        ArrayList arrayList3 = FragmentTransition.configureEnteringExitingViews(fragmentTransitionImpl0, object0, fragment0, arrayList0, view0);
        FragmentTransition.setViewVisibility(arrayList3, 4);
        Object object4 = FragmentTransition.mergeTransitions(fragmentTransitionImpl0, object0, object3, object2, fragment0, z);
        if(object4 != null) {
            FragmentTransition.replaceHide(fragmentTransitionImpl0, object3, fragment1, arrayList2);
            ArrayList arrayList4 = fragmentTransitionImpl0.prepareSetNameOverridesReordered(arrayList0);
            fragmentTransitionImpl0.scheduleRemoveTargets(object4, object0, arrayList3, object3, arrayList2, object2, arrayList0);
            fragmentTransitionImpl0.beginDelayedTransition(viewGroup0, object4);
            fragmentTransitionImpl0.setNameOverridesReordered(viewGroup0, arrayList1, arrayList0, arrayList4, arrayMap0);
            FragmentTransition.setViewVisibility(arrayList3, 0);
            fragmentTransitionImpl0.swapSharedElementTargets(object2, arrayList1, arrayList0);
        }
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition fragmentTransition$FragmentContainerTransition0, SparseArray sparseArray0, int v) {
        if(fragmentTransition$FragmentContainerTransition0 == null) {
            fragmentTransition$FragmentContainerTransition0 = new FragmentContainerTransition();
            sparseArray0.put(v, fragmentTransition$FragmentContainerTransition0);
        }
        return fragmentTransition$FragmentContainerTransition0;
    }

    private static String findKeyForValue(ArrayMap arrayMap0, String s) {
        int v = arrayMap0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(s.equals(arrayMap0.valueAt(v1))) {
                return (String)arrayMap0.keyAt(v1);
            }
        }
        return null;
    }

    private static Object getEnterTransition(FragmentTransitionImpl fragmentTransitionImpl0, Fragment fragment0, boolean z) {
        if(fragment0 == null) {
            return null;
        }
        return z ? fragmentTransitionImpl0.cloneTransition(fragment0.getReenterTransition()) : fragmentTransitionImpl0.cloneTransition(fragment0.getEnterTransition());
    }

    private static Object getExitTransition(FragmentTransitionImpl fragmentTransitionImpl0, Fragment fragment0, boolean z) {
        if(fragment0 == null) {
            return null;
        }
        return z ? fragmentTransitionImpl0.cloneTransition(fragment0.getReturnTransition()) : fragmentTransitionImpl0.cloneTransition(fragment0.getExitTransition());
    }

    static View getInEpicenterView(ArrayMap arrayMap0, FragmentContainerTransition fragmentTransition$FragmentContainerTransition0, Object object0, boolean z) {
        BackStackRecord backStackRecord0 = fragmentTransition$FragmentContainerTransition0.lastInTransaction;
        if(object0 != null && arrayMap0 != null && backStackRecord0.mSharedElementSourceNames != null && !backStackRecord0.mSharedElementSourceNames.isEmpty()) {
            return z ? ((View)arrayMap0.get(((String)backStackRecord0.mSharedElementSourceNames.get(0)))) : ((View)arrayMap0.get(((String)backStackRecord0.mSharedElementTargetNames.get(0))));
        }
        return null;
    }

    private static Object getSharedElementTransition(FragmentTransitionImpl fragmentTransitionImpl0, Fragment fragment0, Fragment fragment1, boolean z) {
        if(fragment0 != null && fragment1 != null) {
            return z ? fragmentTransitionImpl0.wrapTransitionInSet(fragmentTransitionImpl0.cloneTransition(fragment1.getSharedElementReturnTransition())) : fragmentTransitionImpl0.wrapTransitionInSet(fragmentTransitionImpl0.cloneTransition(fragment0.getSharedElementEnterTransition()));
        }
        return null;
    }

    private static Object mergeTransitions(FragmentTransitionImpl fragmentTransitionImpl0, Object object0, Object object1, Object object2, Fragment fragment0, boolean z) {
        if(object0 != null && object1 != null && fragment0 != null) {
            if(z) {
                return fragment0.getAllowReturnTransitionOverlap() ? fragmentTransitionImpl0.mergeTransitionsTogether(object1, object0, object2) : fragmentTransitionImpl0.mergeTransitionsInSequence(object1, object0, object2);
            }
            return fragment0.getAllowEnterTransitionOverlap() ? fragmentTransitionImpl0.mergeTransitionsTogether(object1, object0, object2) : fragmentTransitionImpl0.mergeTransitionsInSequence(object1, object0, object2);
        }
        return fragmentTransitionImpl0.mergeTransitionsTogether(object1, object0, object2);
    }

    private static void replaceHide(FragmentTransitionImpl fragmentTransitionImpl0, Object object0, Fragment fragment0, ArrayList arrayList0) {
        if(fragment0 != null && object0 != null && fragment0.mAdded && fragment0.mHidden && fragment0.mHiddenChanged) {
            fragment0.setHideReplaced(true);
            fragmentTransitionImpl0.scheduleHideFragmentView(object0, fragment0.getView(), arrayList0);
            OneShotPreDrawListener.add(fragment0.mContainer, () -> {
                if(this.val$exitingViews == null) {
                    return;
                }
                for(int v1 = this.val$exitingViews.size() - 1; v1 >= 0; --v1) {
                    ((View)this.val$exitingViews.get(v1)).setVisibility(4);
                }
            });
        }

        final class androidx.fragment.app.FragmentTransition.1 implements Runnable {
            androidx.fragment.app.FragmentTransition.1(ArrayList arrayList0) {
            }

            @Override
            public void run() {
                FragmentTransition.setViewVisibility(this.val$exitingViews, 4);
            }
        }

    }

    private static FragmentTransitionImpl resolveSupportImpl() {
        try {
            return (FragmentTransitionImpl)Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor().newInstance();
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    private static void retainValues(ArrayMap arrayMap0, ArrayMap arrayMap1) {
        for(int v = arrayMap0.size() - 1; v >= 0; --v) {
            if(!arrayMap1.containsKey(((String)arrayMap0.valueAt(v)))) {
                arrayMap0.removeAt(v);
            }
        }
    }

    private static void scheduleTargetChange(FragmentTransitionImpl fragmentTransitionImpl0, ViewGroup viewGroup0, Fragment fragment0, View view0, ArrayList arrayList0, Object object0, ArrayList arrayList1, Object object1, ArrayList arrayList2) {
        OneShotPreDrawListener.add(viewGroup0, new Runnable() {
            @Override
            public void run() {
                Object object0 = object0;
                if(object0 != null) {
                    fragmentTransitionImpl0.removeTarget(object0, view0);
                    ArrayList arrayList0 = FragmentTransition.configureEnteringExitingViews(fragmentTransitionImpl0, object0, fragment0, arrayList0, view0);
                    arrayList1.addAll(arrayList0);
                }
                if(arrayList2 != null) {
                    if(object1 != null) {
                        ArrayList arrayList1 = new ArrayList();
                        arrayList1.add(view0);
                        fragmentTransitionImpl0.replaceTargets(object1, arrayList2, arrayList1);
                    }
                    arrayList2.clear();
                    arrayList2.add(view0);
                }
            }
        });
    }

    private static void setOutEpicenter(FragmentTransitionImpl fragmentTransitionImpl0, Object object0, Object object1, ArrayMap arrayMap0, boolean z, BackStackRecord backStackRecord0) {
        if(backStackRecord0.mSharedElementSourceNames != null && !backStackRecord0.mSharedElementSourceNames.isEmpty()) {
            View view0 = (View)arrayMap0.get((z ? ((String)backStackRecord0.mSharedElementTargetNames.get(0)) : ((String)backStackRecord0.mSharedElementSourceNames.get(0))));
            fragmentTransitionImpl0.setEpicenter(object0, view0);
            if(object1 != null) {
                fragmentTransitionImpl0.setEpicenter(object1, view0);
            }
        }
    }

    // 检测为 Lambda 实现
    static void setViewVisibility(ArrayList arrayList0, int v) [...]

    static void startTransitions(FragmentManagerImpl fragmentManagerImpl0, ArrayList arrayList0, ArrayList arrayList1, int v, int v1, boolean z) {
        if(fragmentManagerImpl0.mCurState < 1) {
            return;
        }
        SparseArray sparseArray0 = new SparseArray();
        for(int v2 = v; v2 < v1; ++v2) {
            BackStackRecord backStackRecord0 = (BackStackRecord)arrayList0.get(v2);
            if(((Boolean)arrayList1.get(v2)).booleanValue()) {
                FragmentTransition.calculatePopFragments(backStackRecord0, sparseArray0, z);
            }
            else {
                FragmentTransition.calculateFragments(backStackRecord0, sparseArray0, z);
            }
        }
        if(sparseArray0.size() != 0) {
            View view0 = new View(fragmentManagerImpl0.mHost.getContext());
            int v3 = sparseArray0.size();
            for(int v4 = 0; v4 < v3; ++v4) {
                int v5 = sparseArray0.keyAt(v4);
                ArrayMap arrayMap0 = FragmentTransition.calculateNameOverrides(v5, arrayList0, arrayList1, v, v1);
                FragmentContainerTransition fragmentTransition$FragmentContainerTransition0 = (FragmentContainerTransition)sparseArray0.valueAt(v4);
                if(z) {
                    FragmentTransition.configureTransitionsReordered(fragmentManagerImpl0, v5, fragmentTransition$FragmentContainerTransition0, view0, arrayMap0);
                }
                else {
                    FragmentTransition.configureTransitionsOrdered(fragmentManagerImpl0, v5, fragmentTransition$FragmentContainerTransition0, view0, arrayMap0);
                }
            }
        }
    }

    static boolean supportsTransition() [...] // 潜在的解密器
}

