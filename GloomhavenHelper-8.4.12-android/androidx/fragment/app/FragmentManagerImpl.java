package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import androidx.core.util.DebugUtils;
import androidx.core.util.LogWriter;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelStore;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
    static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
        View mView;

        AnimateOnHWLayerIfNeededListener(View view0, Animation.AnimationListener animation$AnimationListener0) {
            super(animation$AnimationListener0);
            this.mView = view0;
        }

        @Override  // androidx.fragment.app.FragmentManagerImpl$AnimationListenerWrapper
        @CallSuper
        public void onAnimationEnd(Animation animation0) {
            if(ViewCompat.isAttachedToWindow(this.mView) || Build.VERSION.SDK_INT >= 24) {
                this.mView.post(new Runnable() {
                    @Override
                    public void run() {
                        AnimateOnHWLayerIfNeededListener.this.mView.setLayerType(0, null);
                    }
                });
            }
            else {
                this.mView.setLayerType(0, null);
            }
            super.onAnimationEnd(animation0);
        }
    }

    static class AnimationListenerWrapper implements Animation.AnimationListener {
        private final Animation.AnimationListener mWrapped;

        AnimationListenerWrapper(Animation.AnimationListener animation$AnimationListener0) {
            this.mWrapped = animation$AnimationListener0;
        }

        @Override  // android.view.animation.Animation$AnimationListener
        @CallSuper
        public void onAnimationEnd(Animation animation0) {
            Animation.AnimationListener animation$AnimationListener0 = this.mWrapped;
            if(animation$AnimationListener0 != null) {
                animation$AnimationListener0.onAnimationEnd(animation0);
            }
        }

        @Override  // android.view.animation.Animation$AnimationListener
        @CallSuper
        public void onAnimationRepeat(Animation animation0) {
            Animation.AnimationListener animation$AnimationListener0 = this.mWrapped;
            if(animation$AnimationListener0 != null) {
                animation$AnimationListener0.onAnimationRepeat(animation0);
            }
        }

        @Override  // android.view.animation.Animation$AnimationListener
        @CallSuper
        public void onAnimationStart(Animation animation0) {
            Animation.AnimationListener animation$AnimationListener0 = this.mWrapped;
            if(animation$AnimationListener0 != null) {
                animation$AnimationListener0.onAnimationStart(animation0);
            }
        }
    }

    static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        AnimationOrAnimator(Animator animator0) {
            this.animation = null;
            this.animator = animator0;
            if(animator0 == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }

        AnimationOrAnimator(Animation animation0) {
            this.animation = animation0;
            this.animator = null;
            if(animation0 == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }
    }

    static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
        View mView;

        AnimatorOnHWLayerIfNeededListener(View view0) {
            this.mView = view0;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            this.mView.setLayerType(0, null);
            animator0.removeListener(this);
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationStart(Animator animator0) {
            this.mView.setLayerType(2, null);
        }
    }

    static class EndViewTransitionAnimator extends AnimationSet implements Runnable {
        private boolean mAnimating;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        EndViewTransitionAnimator(@NonNull Animation animation0, @NonNull ViewGroup viewGroup0, @NonNull View view0) {
            super(false);
            this.mAnimating = true;
            this.mParent = viewGroup0;
            this.mChild = view0;
            this.addAnimation(animation0);
            this.mParent.post(this);
        }

        @Override  // android.view.animation.AnimationSet
        public boolean getTransformation(long v, Transformation transformation0) {
            this.mAnimating = true;
            if(this.mEnded) {
                return !this.mTransitionEnded;
            }
            if(!super.getTransformation(v, transformation0)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        @Override  // android.view.animation.Animation
        public boolean getTransformation(long v, Transformation transformation0, float f) {
            this.mAnimating = true;
            if(this.mEnded) {
                return !this.mTransitionEnded;
            }
            if(!super.getTransformation(v, transformation0, f)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        @Override
        public void run() {
            if(!this.mEnded && this.mAnimating) {
                this.mAnimating = false;
                this.mParent.post(this);
                return;
            }
            this.mParent.endViewTransition(this.mChild);
            this.mTransitionEnded = true;
        }
    }

    static final class FragmentLifecycleCallbacksHolder {
        final FragmentLifecycleCallbacks mCallback;
        final boolean mRecursive;

        FragmentLifecycleCallbacksHolder(FragmentLifecycleCallbacks fragmentManager$FragmentLifecycleCallbacks0, boolean z) {
            this.mCallback = fragmentManager$FragmentLifecycleCallbacks0;
            this.mRecursive = z;
        }
    }

    static class FragmentTag {
        public static final int[] Fragment = null;
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        static {
            FragmentTag.Fragment = new int[]{0x1010003, 0x10100D0, 0x10100D1};
        }
    }

    interface OpGenerator {
        boolean generateOps(ArrayList arg1, ArrayList arg2);
    }

    class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;

        PopBackStackState(String s, int v, int v1) {
            this.mName = s;
            this.mId = v;
            this.mFlags = v1;
        }

        @Override  // androidx.fragment.app.FragmentManagerImpl$OpGenerator
        public boolean generateOps(ArrayList arrayList0, ArrayList arrayList1) {
            if(FragmentManagerImpl.this.mPrimaryNav != null && this.mId < 0 && this.mName == null) {
                FragmentManager fragmentManager0 = FragmentManagerImpl.this.mPrimaryNav.peekChildFragmentManager();
                return fragmentManager0 == null || !fragmentManager0.popBackStackImmediate() ? FragmentManagerImpl.this.popBackStackState(arrayList0, arrayList1, this.mName, this.mId, this.mFlags) : false;
            }
            return FragmentManagerImpl.this.popBackStackState(arrayList0, arrayList1, this.mName, this.mId, this.mFlags);
        }
    }

    static class StartEnterTransitionListener implements OnStartEnterTransitionListener {
        final boolean mIsBack;
        private int mNumPostponed;
        final BackStackRecord mRecord;

        StartEnterTransitionListener(BackStackRecord backStackRecord0, boolean z) {
            this.mIsBack = z;
            this.mRecord = backStackRecord0;
        }

        public void cancelTransaction() {
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
        }

        public void completeTransaction() {
            int v1 = this.mNumPostponed <= 0 ? 0 : 1;
            FragmentManagerImpl fragmentManagerImpl0 = this.mRecord.mManager;
            int v2 = fragmentManagerImpl0.mAdded.size();
            for(int v = 0; v < v2; ++v) {
                Fragment fragment0 = (Fragment)fragmentManagerImpl0.mAdded.get(v);
                fragment0.setOnStartEnterTransitionListener(null);
                if(v1 != 0 && fragment0.isPostponed()) {
                    fragment0.startPostponedEnterTransition();
                }
            }
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, ((boolean)(v1 ^ 1)), true);
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        @Override  // androidx.fragment.app.Fragment$OnStartEnterTransitionListener
        public void onStartEnterTransition() {
            --this.mNumPostponed;
            if(this.mNumPostponed != 0) {
                return;
            }
            this.mRecord.mManager.scheduleCommit();
        }

        @Override  // androidx.fragment.app.Fragment$OnStartEnterTransitionListener
        public void startListening() {
            ++this.mNumPostponed;
        }
    }

    static final Interpolator ACCELERATE_CUBIC = null;
    static final Interpolator ACCELERATE_QUINT = null;
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC = null;
    static final Interpolator DECELERATE_QUINT = null;
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    SparseArray mActive;
    final ArrayList mAdded;
    ArrayList mAvailBackStackIndices;
    ArrayList mBackStack;
    ArrayList mBackStackChangeListeners;
    ArrayList mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    private final CopyOnWriteArrayList mLifecycleCallbacks;
    boolean mNeedMenuInvalidate;
    int mNextFragmentIndex;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList mPendingActions;
    ArrayList mPostponedTransactions;
    @Nullable
    Fragment mPrimaryNav;
    FragmentManagerNonConfig mSavedNonConfig;
    SparseArray mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    boolean mStopped;
    ArrayList mTmpAddedFragments;
    ArrayList mTmpIsPop;
    ArrayList mTmpRecords;
    static Field sAnimationListenerField;

    static {
        FragmentManagerImpl.DECELERATE_QUINT = new DecelerateInterpolator(2.5f);
        FragmentManagerImpl.DECELERATE_CUBIC = new DecelerateInterpolator(1.5f);
        FragmentManagerImpl.ACCELERATE_QUINT = new AccelerateInterpolator(2.5f);
        FragmentManagerImpl.ACCELERATE_CUBIC = new AccelerateInterpolator(1.5f);
    }

    FragmentManagerImpl() {
        this.mNextFragmentIndex = 0;
        this.mAdded = new ArrayList();
        this.mLifecycleCallbacks = new CopyOnWriteArrayList();
        this.mCurState = 0;
        this.mStateBundle = null;
        this.mStateArray = null;
        this.mExecCommit = () -> {
            FragmentManagerImpl.this.ensureExecReady(true);
            boolean z;
            for(z = false; FragmentManagerImpl.this.generateOpsForPendingActions(FragmentManagerImpl.this.mTmpRecords, FragmentManagerImpl.this.mTmpIsPop); z = true) {
                try {
                    FragmentManagerImpl.this.mExecutingActions = true;
                    FragmentManagerImpl.this.removeRedundantOperationsAndExecute(FragmentManagerImpl.this.mTmpRecords, FragmentManagerImpl.this.mTmpIsPop);
                }
                finally {
                    FragmentManagerImpl.this.cleanupExec();
                }
            }
            FragmentManagerImpl.this.doPendingDeferredStart();
            FragmentManagerImpl.this.burpActive();
            return z;
        };
    }

    private void addAddedFragments(ArraySet arraySet0) {
        int v = this.mCurState;
        if(v < 1) {
            return;
        }
        int v1 = Math.min(v, 3);
        int v2 = this.mAdded.size();
        for(int v3 = 0; v3 < v2; ++v3) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v3);
            if(fragment0.mState < v1) {
                this.moveToState(fragment0, v1, fragment0.getNextAnim(), fragment0.getNextTransition(), false);
                if(fragment0.mView != null && !fragment0.mHidden && fragment0.mIsNewlyAdded) {
                    arraySet0.add(fragment0);
                }
            }
        }
    }

    void addBackStackState(BackStackRecord backStackRecord0) {
        if(this.mBackStack == null) {
            this.mBackStack = new ArrayList();
        }
        this.mBackStack.add(backStackRecord0);
    }

    public void addFragment(Fragment fragment0, boolean z) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "add: " + fragment0);
        }
        this.makeActive(fragment0);
        if(!fragment0.mDetached) {
            if(this.mAdded.contains(fragment0)) {
                throw new IllegalStateException("Fragment already added: " + fragment0);
            }
            synchronized(this.mAdded) {
                this.mAdded.add(fragment0);
            }
            fragment0.mAdded = true;
            fragment0.mRemoving = false;
            if(fragment0.mView == null) {
                fragment0.mHiddenChanged = false;
            }
            if(fragment0.mHasMenu && fragment0.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            if(z) {
                this.moveToState(fragment0);
            }
        }
    }

    @Override  // androidx.fragment.app.FragmentManager
    public void addOnBackStackChangedListener(OnBackStackChangedListener fragmentManager$OnBackStackChangedListener0) {
        if(this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList();
        }
        this.mBackStackChangeListeners.add(fragmentManager$OnBackStackChangedListener0);
    }

    public int allocBackStackIndex(BackStackRecord backStackRecord0) {
        synchronized(this) {
            if(this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                int v1 = (int)(((Integer)this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1)));
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Adding back stack index " + v1 + " with " + backStackRecord0);
                }
                this.mBackStackIndices.set(v1, backStackRecord0);
                return v1;
            }
            if(this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList();
            }
            int v2 = this.mBackStackIndices.size();
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Setting back stack index " + v2 + " to " + backStackRecord0);
            }
            this.mBackStackIndices.add(backStackRecord0);
            return v2;
        }
    }

    private void animateRemoveFragment(@NonNull Fragment fragment0, @NonNull AnimationOrAnimator fragmentManagerImpl$AnimationOrAnimator0, int v) {
        View view0 = fragment0.mView;
        ViewGroup viewGroup0 = fragment0.mContainer;
        viewGroup0.startViewTransition(view0);
        fragment0.setStateAfterAnimating(v);
        if(fragmentManagerImpl$AnimationOrAnimator0.animation != null) {
            EndViewTransitionAnimator fragmentManagerImpl$EndViewTransitionAnimator0 = new EndViewTransitionAnimator(fragmentManagerImpl$AnimationOrAnimator0.animation, viewGroup0, view0);
            fragment0.setAnimatingAway(fragment0.mView);
            fragmentManagerImpl$EndViewTransitionAnimator0.setAnimationListener(new AnimationListenerWrapper(FragmentManagerImpl.getAnimationListener(fragmentManagerImpl$EndViewTransitionAnimator0)) {
                @Override  // androidx.fragment.app.FragmentManagerImpl$AnimationListenerWrapper
                public void onAnimationEnd(Animation animation0) {
                    super.onAnimationEnd(animation0);
                    androidx.fragment.app.FragmentManagerImpl.2.1 fragmentManagerImpl$2$10 = new Runnable() {
                        @Override
                        public void run() {
                            if(androidx.fragment.app.FragmentManagerImpl.2.this.val$fragment.getAnimatingAway() != null) {
                                androidx.fragment.app.FragmentManagerImpl.2.this.val$fragment.setAnimatingAway(null);
                                int v = androidx.fragment.app.FragmentManagerImpl.2.this.val$fragment.getStateAfterAnimating();
                                FragmentManagerImpl.this.moveToState(androidx.fragment.app.FragmentManagerImpl.2.this.val$fragment, v, 0, 0, false);
                            }
                        }
                    };
                    viewGroup0.post(fragmentManagerImpl$2$10);
                }
            });
            FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(view0, fragmentManagerImpl$AnimationOrAnimator0);
            fragment0.mView.startAnimation(fragmentManagerImpl$EndViewTransitionAnimator0);
            return;
        }
        fragment0.setAnimator(fragmentManagerImpl$AnimationOrAnimator0.animator);
        androidx.fragment.app.FragmentManagerImpl.3 fragmentManagerImpl$30 = new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                viewGroup0.endViewTransition(view0);
                Animator animator1 = fragment0.getAnimator();
                fragment0.setAnimator(null);
                if(animator1 != null && viewGroup0.indexOfChild(view0) < 0) {
                    int v = fragment0.getStateAfterAnimating();
                    FragmentManagerImpl.this.moveToState(fragment0, v, 0, 0, false);
                }
            }
        };
        fragmentManagerImpl$AnimationOrAnimator0.animator.addListener(fragmentManagerImpl$30);
        fragmentManagerImpl$AnimationOrAnimator0.animator.setTarget(fragment0.mView);
        FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(fragment0.mView, fragmentManagerImpl$AnimationOrAnimator0);
        fragmentManagerImpl$AnimationOrAnimator0.animator.start();
    }

    public void attachController(FragmentHostCallback fragmentHostCallback0, FragmentContainer fragmentContainer0, Fragment fragment0) {
        if(this.mHost != null) {
            throw new IllegalStateException("Already attached");
        }
        this.mHost = fragmentHostCallback0;
        this.mContainer = fragmentContainer0;
        this.mParent = fragment0;
    }

    public void attachFragment(Fragment fragment0) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "attach: " + fragment0);
        }
        if(fragment0.mDetached) {
            fragment0.mDetached = false;
            if(!fragment0.mAdded) {
                if(this.mAdded.contains(fragment0)) {
                    throw new IllegalStateException("Fragment already added: " + fragment0);
                }
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "add from attach: " + fragment0);
                }
                synchronized(this.mAdded) {
                    this.mAdded.add(fragment0);
                }
                fragment0.mAdded = true;
                if(fragment0.mHasMenu && fragment0.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
            }
        }
    }

    @Override  // androidx.fragment.app.FragmentManager
    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    private void burpActive() {
        SparseArray sparseArray0 = this.mActive;
        if(sparseArray0 != null) {
            for(int v = sparseArray0.size() - 1; v >= 0; --v) {
                if(this.mActive.valueAt(v) == null) {
                    this.mActive.delete(this.mActive.keyAt(v));
                }
            }
        }
    }

    private void checkStateLoss() {
        if(this.isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if(this.mNoTransactionsBecause != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.mNoTransactionsBecause);
        }
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    void completeExecute(BackStackRecord backStackRecord0, boolean z, boolean z1, boolean z2) {
        if(z) {
            backStackRecord0.executePopOps(z2);
        }
        else {
            backStackRecord0.executeOps();
        }
        ArrayList arrayList0 = new ArrayList(1);
        ArrayList arrayList1 = new ArrayList(1);
        arrayList0.add(backStackRecord0);
        arrayList1.add(Boolean.valueOf(z));
        if(z1) {
            FragmentTransition.startTransitions(this, arrayList0, arrayList1, 0, 1, true);
        }
        if(z2) {
            this.moveToState(this.mCurState, true);
        }
        SparseArray sparseArray0 = this.mActive;
        if(sparseArray0 != null) {
            int v = sparseArray0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                Fragment fragment0 = (Fragment)this.mActive.valueAt(v1);
                if(fragment0 != null && fragment0.mView != null && fragment0.mIsNewlyAdded && backStackRecord0.interactsWith(fragment0.mContainerId)) {
                    if(fragment0.mPostponedAlpha > 0.0f) {
                        fragment0.mView.setAlpha(fragment0.mPostponedAlpha);
                    }
                    if(z2) {
                        fragment0.mPostponedAlpha = 0.0f;
                    }
                    else {
                        fragment0.mPostponedAlpha = -1.0f;
                        fragment0.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    void completeShowHideFragment(Fragment fragment0) {
        if(fragment0.mView != null) {
            AnimationOrAnimator fragmentManagerImpl$AnimationOrAnimator0 = this.loadAnimation(fragment0, fragment0.getNextTransition(), !fragment0.mHidden, fragment0.getNextTransitionStyle());
            if(fragmentManagerImpl$AnimationOrAnimator0 == null || fragmentManagerImpl$AnimationOrAnimator0.animator == null) {
                if(fragmentManagerImpl$AnimationOrAnimator0 != null) {
                    FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(fragment0.mView, fragmentManagerImpl$AnimationOrAnimator0);
                    fragment0.mView.startAnimation(fragmentManagerImpl$AnimationOrAnimator0.animation);
                    fragmentManagerImpl$AnimationOrAnimator0.animation.start();
                }
                int v = !fragment0.mHidden || fragment0.isHideReplaced() ? 0 : 8;
                fragment0.mView.setVisibility(v);
                if(fragment0.isHideReplaced()) {
                    fragment0.setHideReplaced(false);
                }
            }
            else {
                fragmentManagerImpl$AnimationOrAnimator0.animator.setTarget(fragment0.mView);
                if(!fragment0.mHidden) {
                    fragment0.mView.setVisibility(0);
                }
                else if(fragment0.isHideReplaced()) {
                    fragment0.setHideReplaced(false);
                }
                else {
                    ViewGroup viewGroup0 = fragment0.mContainer;
                    View view0 = fragment0.mView;
                    viewGroup0.startViewTransition(view0);
                    androidx.fragment.app.FragmentManagerImpl.4 fragmentManagerImpl$40 = new AnimatorListenerAdapter() {
                        @Override  // android.animation.AnimatorListenerAdapter
                        public void onAnimationEnd(Animator animator0) {
                            viewGroup0.endViewTransition(view0);
                            animator0.removeListener(this);
                            if(fragment0.mView != null) {
                                fragment0.mView.setVisibility(8);
                            }
                        }
                    };
                    fragmentManagerImpl$AnimationOrAnimator0.animator.addListener(fragmentManagerImpl$40);
                }
                FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(fragment0.mView, fragmentManagerImpl$AnimationOrAnimator0);
                fragmentManagerImpl$AnimationOrAnimator0.animator.start();
            }
        }
        if(fragment0.mAdded && fragment0.mHasMenu && fragment0.mMenuVisible) {
            this.mNeedMenuInvalidate = true;
        }
        fragment0.mHiddenChanged = false;
    }

    public void detachFragment(Fragment fragment0) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "detach: " + fragment0);
        }
        if(!fragment0.mDetached) {
            fragment0.mDetached = true;
            if(fragment0.mAdded) {
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "remove from detach: " + fragment0);
                }
                synchronized(this.mAdded) {
                    this.mAdded.remove(fragment0);
                }
                if(fragment0.mHasMenu && fragment0.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                fragment0.mAdded = false;
            }
        }
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.dispatchStateChange(2);
    }

    public void dispatchConfigurationChanged(Configuration configuration0) {
        for(int v = 0; v < this.mAdded.size(); ++v) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v);
            if(fragment0 != null) {
                fragment0.performConfigurationChanged(configuration0);
            }
        }
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem0) {
        if(this.mCurState < 1) {
            return false;
        }
        for(int v = 0; v < this.mAdded.size(); ++v) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v);
            if(fragment0 != null && fragment0.performContextItemSelected(menuItem0)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.dispatchStateChange(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu menu0, MenuInflater menuInflater0) {
        if(this.mCurState < 1) {
            return false;
        }
        ArrayList arrayList0 = null;
        boolean z = false;
        for(int v1 = 0; v1 < this.mAdded.size(); ++v1) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v1);
            if(fragment0 != null && fragment0.performCreateOptionsMenu(menu0, menuInflater0)) {
                if(arrayList0 == null) {
                    arrayList0 = new ArrayList();
                }
                arrayList0.add(fragment0);
                z = true;
            }
        }
        if(this.mCreatedMenus != null) {
            for(int v = 0; v < this.mCreatedMenus.size(); ++v) {
                boolean z1 = arrayList0 != null && arrayList0.contains(((Fragment)this.mCreatedMenus.get(v)));
            }
        }
        this.mCreatedMenus = arrayList0;
        return z;
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        this.execPendingActions();
        this.dispatchStateChange(0);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
    }

    public void dispatchDestroyView() {
        this.dispatchStateChange(1);
    }

    public void dispatchLowMemory() {
        for(int v = 0; v < this.mAdded.size(); ++v) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v);
            if(fragment0 != null) {
                fragment0.performLowMemory();
            }
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        for(int v = this.mAdded.size() - 1; v >= 0; --v) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v);
            if(fragment0 != null) {
                fragment0.performMultiWindowModeChanged(z);
            }
        }
    }

    void dispatchOnFragmentActivityCreated(@NonNull Fragment fragment0, @Nullable Bundle bundle0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentActivityCreated(fragment0, bundle0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentAttached(@NonNull Fragment fragment0, @NonNull Context context0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentAttached(fragment0, context0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentCreated(@NonNull Fragment fragment0, @Nullable Bundle bundle0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentCreated(fragment0, bundle0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentDestroyed(@NonNull Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentDestroyed(fragment0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentDetached(@NonNull Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentDetached(fragment0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentPaused(@NonNull Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentPaused(fragment0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentPreAttached(@NonNull Fragment fragment0, @NonNull Context context0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentPreAttached(fragment0, context0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentPreCreated(@NonNull Fragment fragment0, @Nullable Bundle bundle0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentPreCreated(fragment0, bundle0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentResumed(@NonNull Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentResumed(fragment0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentSaveInstanceState(@NonNull Fragment fragment0, @NonNull Bundle bundle0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentSaveInstanceState(fragment0, bundle0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentStarted(@NonNull Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentStarted(fragment0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentStopped(@NonNull Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentStopped(fragment0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentViewCreated(@NonNull Fragment fragment0, @NonNull View view0, @Nullable Bundle bundle0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentViewCreated(fragment0, view0, bundle0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentViewDestroyed(@NonNull Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mParent;
        if(fragment1 != null) {
            FragmentManager fragmentManager0 = fragment1.getFragmentManager();
            if(fragmentManager0 instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl)fragmentManager0).dispatchOnFragmentViewDestroyed(fragment0, true);
            }
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem0) {
        if(this.mCurState < 1) {
            return false;
        }
        for(int v = 0; v < this.mAdded.size(); ++v) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v);
            if(fragment0 != null && fragment0.performOptionsItemSelected(menuItem0)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu0) {
        if(this.mCurState < 1) {
            return;
        }
        for(int v = 0; v < this.mAdded.size(); ++v) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v);
            if(fragment0 != null) {
                fragment0.performOptionsMenuClosed(menu0);
            }
        }
    }

    public void dispatchPause() {
        this.dispatchStateChange(3);
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        for(int v = this.mAdded.size() - 1; v >= 0; --v) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v);
            if(fragment0 != null) {
                fragment0.performPictureInPictureModeChanged(z);
            }
        }
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu0) {
        if(this.mCurState < 1) {
            return false;
        }
        boolean z = false;
        for(int v = 0; v < this.mAdded.size(); ++v) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v);
            if(fragment0 != null && fragment0.performPrepareOptionsMenu(menu0)) {
                z = true;
            }
        }
        return z;
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.dispatchStateChange(4);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.dispatchStateChange(3);
    }

    private void dispatchStateChange(int v) {
        try {
            this.mExecutingActions = true;
            this.moveToState(v, false);
        }
        finally {
            this.mExecutingActions = false;
        }
        this.execPendingActions();
    }

    public void dispatchStop() {
        this.mStopped = true;
        this.dispatchStateChange(2);
    }

    void doPendingDeferredStart() {
        if(this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            this.startPendingDeferredFragments();
        }
    }

    @Override  // androidx.fragment.app.FragmentManager
    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        SparseArray sparseArray0 = this.mActive;
        if(sparseArray0 != null) {
            int v1 = sparseArray0.size();
            if(v1 > 0) {
                printWriter0.print(s);
                printWriter0.print("Active Fragments in ");
                printWriter0.print(Integer.toHexString(System.identityHashCode(this)));
                printWriter0.println(":");
                for(int v2 = 0; v2 < v1; ++v2) {
                    Fragment fragment0 = (Fragment)this.mActive.valueAt(v2);
                    printWriter0.print(s);
                    printWriter0.print("  #");
                    printWriter0.print(v2);
                    printWriter0.print(": ");
                    printWriter0.println(fragment0);
                    if(fragment0 != null) {
                        fragment0.dump(s + "    ", fileDescriptor0, printWriter0, arr_s);
                    }
                }
            }
        }
        int v3 = this.mAdded.size();
        if(v3 > 0) {
            printWriter0.print(s);
            printWriter0.println("Added Fragments:");
            for(int v4 = 0; v4 < v3; ++v4) {
                Fragment fragment1 = (Fragment)this.mAdded.get(v4);
                printWriter0.print(s);
                printWriter0.print("  #");
                printWriter0.print(v4);
                printWriter0.print(": ");
                printWriter0.println("Fragment{35a5d537}");
            }
        }
        ArrayList arrayList0 = this.mCreatedMenus;
        if(arrayList0 != null) {
            int v5 = arrayList0.size();
            if(v5 > 0) {
                printWriter0.print(s);
                printWriter0.println("Fragments Created Menus:");
                for(int v6 = 0; v6 < v5; ++v6) {
                    Fragment fragment2 = (Fragment)this.mCreatedMenus.get(v6);
                    printWriter0.print(s);
                    printWriter0.print("  #");
                    printWriter0.print(v6);
                    printWriter0.print(": ");
                    printWriter0.println("Fragment{35a5d537}");
                }
            }
        }
        ArrayList arrayList1 = this.mBackStack;
        if(arrayList1 != null) {
            int v7 = arrayList1.size();
            if(v7 > 0) {
                printWriter0.print(s);
                printWriter0.println("Back Stack:");
                for(int v8 = 0; v8 < v7; ++v8) {
                    BackStackRecord backStackRecord0 = (BackStackRecord)this.mBackStack.get(v8);
                    printWriter0.print(s);
                    printWriter0.print("  #");
                    printWriter0.print(v8);
                    printWriter0.print(": ");
                    printWriter0.println(backStackRecord0.toString());
                    backStackRecord0.dump(s + "    ", fileDescriptor0, printWriter0, arr_s);
                }
            }
        }
        synchronized(this) {
            if(this.mBackStackIndices != null) {
                int v10 = this.mBackStackIndices.size();
                if(v10 > 0) {
                    printWriter0.print(s);
                    printWriter0.println("Back Stack Indices:");
                    for(int v11 = 0; v11 < v10; ++v11) {
                        BackStackRecord backStackRecord1 = (BackStackRecord)this.mBackStackIndices.get(v11);
                        printWriter0.print(s);
                        printWriter0.print("  #");
                        printWriter0.print(v11);
                        printWriter0.print(": ");
                        printWriter0.println(backStackRecord1);
                    }
                }
            }
            if(this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                printWriter0.print(s);
                printWriter0.print("mAvailBackStackIndices: ");
                printWriter0.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
        }
        ArrayList arrayList2 = this.mPendingActions;
        if(arrayList2 != null) {
            int v12 = arrayList2.size();
            if(v12 > 0) {
                printWriter0.print(s);
                printWriter0.println("Pending Actions:");
                for(int v = 0; v < v12; ++v) {
                    OpGenerator fragmentManagerImpl$OpGenerator0 = (OpGenerator)this.mPendingActions.get(v);
                    printWriter0.print(s);
                    printWriter0.print("  #");
                    printWriter0.print(v);
                    printWriter0.print(": ");
                    printWriter0.println(fragmentManagerImpl$OpGenerator0);
                }
            }
        }
        printWriter0.print(s);
        printWriter0.println("FragmentManager misc state:");
        printWriter0.print(s);
        printWriter0.print("  mHost=");
        printWriter0.println(this.mHost);
        printWriter0.print(s);
        printWriter0.print("  mContainer=");
        printWriter0.println(this.mContainer);
        if(this.mParent != null) {
            printWriter0.print(s);
            printWriter0.print("  mParent=");
            printWriter0.println(this.mParent);
        }
        printWriter0.print(s);
        printWriter0.print("  mCurState=");
        printWriter0.print(this.mCurState);
        printWriter0.print(" mStateSaved=");
        printWriter0.print(this.mStateSaved);
        printWriter0.print(" mStopped=");
        printWriter0.print(this.mStopped);
        printWriter0.print(" mDestroyed=");
        printWriter0.println(this.mDestroyed);
        if(this.mNeedMenuInvalidate) {
            printWriter0.print(s);
            printWriter0.print("  mNeedMenuInvalidate=");
            printWriter0.println(this.mNeedMenuInvalidate);
        }
        if(this.mNoTransactionsBecause != null) {
            printWriter0.print(s);
            printWriter0.print("  mNoTransactionsBecause=");
            printWriter0.println(this.mNoTransactionsBecause);
        }
    }

    private void endAnimatingAwayFragments() {
        int v1 = this.mActive == null ? 0 : this.mActive.size();
        for(int v = 0; v < v1; ++v) {
            Fragment fragment0 = (Fragment)this.mActive.valueAt(v);
            if(fragment0 != null) {
                if(fragment0.getAnimatingAway() != null) {
                    int v2 = fragment0.getStateAfterAnimating();
                    View view0 = fragment0.getAnimatingAway();
                    Animation animation0 = view0.getAnimation();
                    if(animation0 != null) {
                        animation0.cancel();
                        view0.clearAnimation();
                    }
                    fragment0.setAnimatingAway(null);
                    this.moveToState(fragment0, v2, 0, 0, false);
                }
                else if(fragment0.getAnimator() != null) {
                    fragment0.getAnimator().end();
                }
            }
        }
    }

    public void enqueueAction(OpGenerator fragmentManagerImpl$OpGenerator0, boolean z) {
        if(!z) {
            this.checkStateLoss();
        }
        synchronized(this) {
            if(!this.mDestroyed && this.mHost != null) {
                if(this.mPendingActions == null) {
                    this.mPendingActions = new ArrayList();
                }
                this.mPendingActions.add(fragmentManagerImpl$OpGenerator0);
                this.scheduleCommit();
                return;
            }
            if(z) {
                return;
            }
        }
        throw new IllegalStateException("Activity has been destroyed");
    }

    private void ensureExecReady(boolean z) {
        if(this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if(this.mHost == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        if(Looper.myLooper() == this.mHost.getHandler().getLooper()) {
            if(!z) {
                this.checkStateLoss();
            }
            if(this.mTmpRecords == null) {
                this.mTmpRecords = new ArrayList();
                this.mTmpIsPop = new ArrayList();
            }
            try {
                this.mExecutingActions = true;
                this.executePostponedTransaction(null, null);
                this.mExecutingActions = false;
                return;
            }
            catch(Throwable throwable0) {
                this.mExecutingActions = false;
                throw throwable0;
            }
        }
        throw new IllegalStateException("Must be called from main thread of fragment host");
    }

    void ensureInflatedFragmentView(Fragment fragment0) {
        if(fragment0.mFromLayout && !fragment0.mPerformedCreateView) {
            fragment0.performCreateView(fragment0.performGetLayoutInflater(fragment0.mSavedFragmentState), null, fragment0.mSavedFragmentState);
            if(fragment0.mView != null) {
                fragment0.mInnerView = fragment0.mView;
                fragment0.mView.setSaveFromParentEnabled(false);
                if(fragment0.mHidden) {
                    fragment0.mView.setVisibility(8);
                }
                fragment0.onViewCreated(fragment0.mView, fragment0.mSavedFragmentState);
                this.dispatchOnFragmentViewCreated(fragment0, fragment0.mView, fragment0.mSavedFragmentState, false);
                return;
            }
            fragment0.mInnerView = null;
        }
    }

    // 检测为 Lambda 实现
    public boolean execPendingActions() [...]

    public void execSingleAction(OpGenerator fragmentManagerImpl$OpGenerator0, boolean z) {
        if(z && (this.mHost == null || this.mDestroyed)) {
            return;
        }
        this.ensureExecReady(z);
        if(fragmentManagerImpl$OpGenerator0.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
            try {
                this.mExecutingActions = true;
                this.removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            }
            finally {
                this.cleanupExec();
            }
        }
        this.doPendingDeferredStart();
        this.burpActive();
    }

    private static void executeOps(ArrayList arrayList0, ArrayList arrayList1, int v, int v1) {
        while(v < v1) {
            BackStackRecord backStackRecord0 = (BackStackRecord)arrayList0.get(v);
            boolean z = true;
            if(((Boolean)arrayList1.get(v)).booleanValue()) {
                backStackRecord0.bumpBackStackNesting(-1);
                if(v != v1 - 1) {
                    z = false;
                }
                backStackRecord0.executePopOps(z);
            }
            else {
                backStackRecord0.bumpBackStackNesting(1);
                backStackRecord0.executeOps();
            }
            ++v;
        }
    }

    private void executeOpsTogether(ArrayList arrayList0, ArrayList arrayList1, int v, int v1) {
        int v5;
        int v2 = v;
        boolean z = ((BackStackRecord)arrayList0.get(v2)).mReorderingAllowed;
        ArrayList arrayList2 = this.mTmpAddedFragments;
        if(arrayList2 == null) {
            this.mTmpAddedFragments = new ArrayList();
        }
        else {
            arrayList2.clear();
        }
        this.mTmpAddedFragments.addAll(this.mAdded);
        Fragment fragment0 = this.getPrimaryNavigationFragment();
        int v3 = v2;
        boolean z1 = false;
        while(v3 < v1) {
            BackStackRecord backStackRecord0 = (BackStackRecord)arrayList0.get(v3);
            fragment0 = ((Boolean)arrayList1.get(v3)).booleanValue() ? backStackRecord0.trackAddedFragmentsInPop(this.mTmpAddedFragments, fragment0) : backStackRecord0.expandOps(this.mTmpAddedFragments, fragment0);
            z1 = z1 || backStackRecord0.mAddToBackStack;
            ++v3;
        }
        this.mTmpAddedFragments.clear();
        if(!z) {
            FragmentTransition.startTransitions(this, arrayList0, arrayList1, v, v1, false);
        }
        FragmentManagerImpl.executeOps(arrayList0, arrayList1, v, v1);
        if(z) {
            ArraySet arraySet0 = new ArraySet();
            this.addAddedFragments(arraySet0);
            int v4 = this.postponePostponableTransactions(arrayList0, arrayList1, v, v1, arraySet0);
            this.makeRemovedFragmentsInvisible(arraySet0);
            v5 = v4;
        }
        else {
            v5 = v1;
        }
        if(v5 != v2 && z) {
            FragmentTransition.startTransitions(this, arrayList0, arrayList1, v, v5, true);
            this.moveToState(this.mCurState, true);
        }
        while(v2 < v1) {
            BackStackRecord backStackRecord1 = (BackStackRecord)arrayList0.get(v2);
            if(((Boolean)arrayList1.get(v2)).booleanValue() && backStackRecord1.mIndex >= 0) {
                this.freeBackStackIndex(backStackRecord1.mIndex);
                backStackRecord1.mIndex = -1;
            }
            backStackRecord1.runOnCommitRunnables();
            ++v2;
        }
        if(z1) {
            this.reportBackStackChanged();
        }
    }

    @Override  // androidx.fragment.app.FragmentManager
    public boolean executePendingTransactions() {
        boolean z = this.execPendingActions();
        this.forcePostponedTransactions();
        return z;
    }

    private void executePostponedTransaction(ArrayList arrayList0, ArrayList arrayList1) {
        int v = this.mPostponedTransactions == null ? 0 : this.mPostponedTransactions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            StartEnterTransitionListener fragmentManagerImpl$StartEnterTransitionListener0 = (StartEnterTransitionListener)this.mPostponedTransactions.get(v1);
            if(arrayList0 != null && !fragmentManagerImpl$StartEnterTransitionListener0.mIsBack) {
                int v2 = arrayList0.indexOf(fragmentManagerImpl$StartEnterTransitionListener0.mRecord);
                if(v2 != -1 && ((Boolean)arrayList1.get(v2)).booleanValue()) {
                    fragmentManagerImpl$StartEnterTransitionListener0.cancelTransaction();
                }
            }
            else if(fragmentManagerImpl$StartEnterTransitionListener0.isReady() || arrayList0 != null && fragmentManagerImpl$StartEnterTransitionListener0.mRecord.interactsWith(arrayList0, 0, arrayList0.size())) {
                this.mPostponedTransactions.remove(v1);
                --v1;
                --v;
                if(arrayList0 != null && !fragmentManagerImpl$StartEnterTransitionListener0.mIsBack) {
                    int v3 = arrayList0.indexOf(fragmentManagerImpl$StartEnterTransitionListener0.mRecord);
                    if(v3 != -1 && ((Boolean)arrayList1.get(v3)).booleanValue()) {
                        fragmentManagerImpl$StartEnterTransitionListener0.cancelTransaction();
                        continue;
                    }
                }
                fragmentManagerImpl$StartEnterTransitionListener0.completeTransaction();
            }
        }
    }

    @Override  // androidx.fragment.app.FragmentManager
    @Nullable
    public Fragment findFragmentById(int v) {
        for(int v1 = this.mAdded.size() - 1; v1 >= 0; --v1) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v1);
            if(fragment0 != null && fragment0.mFragmentId == v) {
                return fragment0;
            }
        }
        SparseArray sparseArray0 = this.mActive;
        if(sparseArray0 != null) {
            for(int v2 = sparseArray0.size() - 1; v2 >= 0; --v2) {
                Fragment fragment1 = (Fragment)this.mActive.valueAt(v2);
                if(fragment1 != null && fragment1.mFragmentId == v) {
                    return fragment1;
                }
            }
        }
        return null;
    }

    @Override  // androidx.fragment.app.FragmentManager
    @Nullable
    public Fragment findFragmentByTag(@Nullable String s) {
        if(s != null) {
            for(int v = this.mAdded.size() - 1; v >= 0; --v) {
                Fragment fragment0 = (Fragment)this.mAdded.get(v);
                if(fragment0 != null && s.equals(fragment0.mTag)) {
                    return fragment0;
                }
            }
        }
        SparseArray sparseArray0 = this.mActive;
        if(sparseArray0 != null && s != null) {
            for(int v1 = sparseArray0.size() - 1; v1 >= 0; --v1) {
                Fragment fragment1 = (Fragment)this.mActive.valueAt(v1);
                if(fragment1 != null && s.equals(fragment1.mTag)) {
                    return fragment1;
                }
            }
        }
        return null;
    }

    public Fragment findFragmentByWho(String s) {
        SparseArray sparseArray0 = this.mActive;
        if(sparseArray0 != null && s != null) {
            for(int v = sparseArray0.size() - 1; v >= 0; --v) {
                Fragment fragment0 = (Fragment)this.mActive.valueAt(v);
                if(fragment0 != null) {
                    Fragment fragment1 = fragment0.findFragmentByWho(s);
                    if(fragment1 != null) {
                        return fragment1;
                    }
                }
            }
        }
        return null;
    }

    private Fragment findFragmentUnder(Fragment fragment0) {
        ViewGroup viewGroup0 = fragment0.mContainer;
        if(viewGroup0 != null && fragment0.mView != null) {
            for(int v = this.mAdded.indexOf(fragment0) - 1; v >= 0; --v) {
                Fragment fragment1 = (Fragment)this.mAdded.get(v);
                if(fragment1.mContainer == viewGroup0 && fragment1.mView != null) {
                    return fragment1;
                }
            }
            return null;
        }
        return null;
    }

    private void forcePostponedTransactions() {
        if(this.mPostponedTransactions != null) {
            while(!this.mPostponedTransactions.isEmpty()) {
                ((StartEnterTransitionListener)this.mPostponedTransactions.remove(0)).completeTransaction();
            }
        }
    }

    public void freeBackStackIndex(int v) {
        synchronized(this) {
            this.mBackStackIndices.set(v, null);
            if(this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList();
            }
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Freeing back stack index " + v);
            }
            this.mAvailBackStackIndices.add(v);
        }
    }

    private boolean generateOpsForPendingActions(ArrayList arrayList0, ArrayList arrayList1) {
        synchronized(this) {
            if(this.mPendingActions != null && this.mPendingActions.size() != 0) {
                int v2 = this.mPendingActions.size();
                boolean z = false;
                for(int v = 0; v < v2; ++v) {
                    z |= ((OpGenerator)this.mPendingActions.get(v)).generateOps(arrayList0, arrayList1);
                }
                this.mPendingActions.clear();
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                return z;
            }
            return false;
        }
    }

    int getActiveFragmentCount() {
        return this.mActive == null ? 0 : this.mActive.size();
    }

    List getActiveFragments() {
        SparseArray sparseArray0 = this.mActive;
        if(sparseArray0 == null) {
            return null;
        }
        int v = sparseArray0.size();
        List list0 = new ArrayList(v);
        for(int v1 = 0; v1 < v; ++v1) {
            ((ArrayList)list0).add(this.mActive.valueAt(v1));
        }
        return list0;
    }

    private static Animation.AnimationListener getAnimationListener(Animation animation0) {
        try {
            if(FragmentManagerImpl.sAnimationListenerField == null) {
                FragmentManagerImpl.sAnimationListenerField = Animation.class.getDeclaredField("mListener");
                FragmentManagerImpl.sAnimationListenerField.setAccessible(true);
            }
            return (Animation.AnimationListener)FragmentManagerImpl.sAnimationListenerField.get(animation0);
        }
        catch(NoSuchFieldException noSuchFieldException0) {
            Log.e("FragmentManager", "No field with the name mListener is found in Animation class", noSuchFieldException0);
            return null;
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.e("FragmentManager", "Cannot access Animation\'s mListener field", illegalAccessException0);
            return null;
        }
    }

    @Override  // androidx.fragment.app.FragmentManager
    public BackStackEntry getBackStackEntryAt(int v) {
        return (BackStackEntry)this.mBackStack.get(v);
    }

    @Override  // androidx.fragment.app.FragmentManager
    public int getBackStackEntryCount() {
        return this.mBackStack == null ? 0 : this.mBackStack.size();
    }

    @Override  // androidx.fragment.app.FragmentManager
    @Nullable
    public Fragment getFragment(Bundle bundle0, String s) {
        int v = bundle0.getInt(s, -1);
        if(v == -1) {
            return null;
        }
        Fragment fragment0 = (Fragment)this.mActive.get(v);
        if(fragment0 == null) {
            this.throwException(new IllegalStateException("Fragment no longer exists for key " + s + ": index " + v));
        }
        return fragment0;
    }

    @Override  // androidx.fragment.app.FragmentManager
    public List getFragments() {
        if(this.mAdded.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList0 = this.mAdded;
        return (List)this.mAdded.clone();
    }

    LayoutInflater.Factory2 getLayoutInflaterFactory() [...] // Inlined contents

    @Override  // androidx.fragment.app.FragmentManager
    @Nullable
    public Fragment getPrimaryNavigationFragment() {
        return this.mPrimaryNav;
    }

    public void hideFragment(Fragment fragment0) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "hide: " + fragment0);
        }
        if(!fragment0.mHidden) {
            fragment0.mHidden = true;
            fragment0.mHiddenChanged ^= true;
        }
    }

    @Override  // androidx.fragment.app.FragmentManager
    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    boolean isStateAtLeast(int v) {
        return this.mCurState >= v;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.fragment.app.FragmentManager
    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    AnimationOrAnimator loadAnimation(Fragment fragment0, int v, boolean z, int v1) {
        int v2 = fragment0.getNextAnim();
        if(v2 != 0) {
            boolean z1 = "anim".equals(this.mHost.getContext().getResources().getResourceTypeName(v2));
            boolean z2 = false;
            if(z1) {
                try {
                    Animation animation0 = AnimationUtils.loadAnimation(this.mHost.getContext(), v2);
                    if(animation0 != null) {
                        return new AnimationOrAnimator(animation0);
                    }
                    z2 = true;
                    goto label_12;
                }
                catch(Resources.NotFoundException resources$NotFoundException0) {
                }
                catch(RuntimeException unused_ex) {
                    goto label_12;
                }
                throw resources$NotFoundException0;
            }
        label_12:
            if(!z2) {
                try {
                    Animator animator0 = AnimatorInflater.loadAnimator(this.mHost.getContext(), v2);
                    if(animator0 != null) {
                        return new AnimationOrAnimator(animator0);
                    }
                }
                catch(RuntimeException runtimeException0) {
                    if(z1) {
                        throw runtimeException0;
                    }
                    Animation animation1 = AnimationUtils.loadAnimation(this.mHost.getContext(), v2);
                    if(animation1 != null) {
                        return new AnimationOrAnimator(animation1);
                    }
                }
            }
        }
        if(v == 0) {
            return null;
        }
        int v3 = FragmentManagerImpl.transitToStyleIndex(v, z);
        if(v3 < 0) {
            return null;
        }
        switch(v3) {
            case 1: {
                return FragmentManagerImpl.makeOpenCloseAnimation(this.mHost.getContext(), 1.125f, 1.0f, 0.0f, 1.0f);
            }
            case 2: {
                return FragmentManagerImpl.makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 0.975f, 1.0f, 0.0f);
            }
            case 3: {
                return FragmentManagerImpl.makeOpenCloseAnimation(this.mHost.getContext(), 0.975f, 1.0f, 0.0f, 1.0f);
            }
            case 4: {
                return FragmentManagerImpl.makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 1.075f, 1.0f, 0.0f);
            }
            case 5: {
                return FragmentManagerImpl.makeFadeAnimation(this.mHost.getContext(), 0.0f, 1.0f);
            }
            case 6: {
                return FragmentManagerImpl.makeFadeAnimation(this.mHost.getContext(), 1.0f, 0.0f);
            }
            default: {
                if(v1 == 0 && this.mHost.onHasWindowAnimations()) {
                    this.mHost.onGetWindowAnimations();
                    return null;
                }
                return null;
            }
        }
    }

    void makeActive(Fragment fragment0) {
        if(fragment0.mIndex >= 0) {
            return;
        }
        int v = this.mNextFragmentIndex;
        this.mNextFragmentIndex = v + 1;
        fragment0.setIndex(v, this.mParent);
        if(this.mActive == null) {
            this.mActive = new SparseArray();
        }
        this.mActive.put(fragment0.mIndex, fragment0);
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Allocated fragment index " + fragment0);
        }
    }

    static AnimationOrAnimator makeFadeAnimation(Context context0, float f, float f1) {
        AlphaAnimation alphaAnimation0 = new AlphaAnimation(f, f1);
        alphaAnimation0.setInterpolator(FragmentManagerImpl.DECELERATE_CUBIC);
        alphaAnimation0.setDuration(220L);
        return new AnimationOrAnimator(alphaAnimation0);
    }

    void makeInactive(Fragment fragment0) {
        if(fragment0.mIndex < 0) {
            return;
        }
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Freeing fragment index " + fragment0);
        }
        this.mActive.put(fragment0.mIndex, null);
        fragment0.initState();
    }

    static AnimationOrAnimator makeOpenCloseAnimation(Context context0, float f, float f1, float f2, float f3) {
        AnimationSet animationSet0 = new AnimationSet(false);
        ScaleAnimation scaleAnimation0 = new ScaleAnimation(f, f1, f, f1, 1, 0.5f, 1, 0.5f);
        scaleAnimation0.setInterpolator(FragmentManagerImpl.DECELERATE_QUINT);
        scaleAnimation0.setDuration(220L);
        animationSet0.addAnimation(scaleAnimation0);
        AlphaAnimation alphaAnimation0 = new AlphaAnimation(f2, f3);
        alphaAnimation0.setInterpolator(FragmentManagerImpl.DECELERATE_CUBIC);
        alphaAnimation0.setDuration(220L);
        animationSet0.addAnimation(alphaAnimation0);
        return new AnimationOrAnimator(animationSet0);
    }

    private void makeRemovedFragmentsInvisible(ArraySet arraySet0) {
        int v = arraySet0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            Fragment fragment0 = (Fragment)arraySet0.valueAt(v1);
            if(!fragment0.mAdded) {
                View view0 = fragment0.getView();
                fragment0.mPostponedAlpha = view0.getAlpha();
                view0.setAlpha(0.0f);
            }
        }
    }

    static boolean modifiesAlpha(Animator animator0) {
        if(animator0 == null) {
            return false;
        }
        if(animator0 instanceof ValueAnimator) {
            PropertyValuesHolder[] arr_propertyValuesHolder = ((ValueAnimator)animator0).getValues();
            for(int v = 0; v < arr_propertyValuesHolder.length; ++v) {
                if("alpha".equals(arr_propertyValuesHolder[v].getPropertyName())) {
                    return true;
                }
            }
            return false;
        }
        if(animator0 instanceof AnimatorSet) {
            ArrayList arrayList0 = ((AnimatorSet)animator0).getChildAnimations();
            for(int v1 = 0; v1 < arrayList0.size(); ++v1) {
                if(FragmentManagerImpl.modifiesAlpha(((Animator)arrayList0.get(v1)))) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean modifiesAlpha(AnimationOrAnimator fragmentManagerImpl$AnimationOrAnimator0) {
        if(fragmentManagerImpl$AnimationOrAnimator0.animation instanceof AlphaAnimation) {
            return true;
        }
        if(fragmentManagerImpl$AnimationOrAnimator0.animation instanceof AnimationSet) {
            List list0 = ((AnimationSet)fragmentManagerImpl$AnimationOrAnimator0.animation).getAnimations();
            for(int v = 0; v < list0.size(); ++v) {
                if(list0.get(v) instanceof AlphaAnimation) {
                    return true;
                }
            }
            return false;
        }
        return FragmentManagerImpl.modifiesAlpha(fragmentManagerImpl$AnimationOrAnimator0.animator);
    }

    void moveFragmentToExpectedState(Fragment fragment0) {
        int v1;
        if(fragment0 == null) {
            return;
        }
        int v = this.mCurState;
        if(!fragment0.mRemoving) {
            v1 = v;
        }
        else if(fragment0.isInBackStack()) {
            v1 = Math.min(v, 1);
        }
        else {
            v1 = Math.min(v, 0);
        }
        this.moveToState(fragment0, v1, fragment0.getNextTransition(), fragment0.getNextTransitionStyle(), false);
        if(fragment0.mView != null) {
            Fragment fragment1 = this.findFragmentUnder(fragment0);
            if(fragment1 != null) {
                ViewGroup viewGroup0 = fragment0.mContainer;
                int v2 = viewGroup0.indexOfChild(fragment1.mView);
                int v3 = viewGroup0.indexOfChild(fragment0.mView);
                if(v3 < v2) {
                    viewGroup0.removeViewAt(v3);
                    viewGroup0.addView(fragment0.mView, v2);
                }
            }
            if(fragment0.mIsNewlyAdded && fragment0.mContainer != null) {
                if(fragment0.mPostponedAlpha > 0.0f) {
                    fragment0.mView.setAlpha(fragment0.mPostponedAlpha);
                }
                fragment0.mPostponedAlpha = 0.0f;
                fragment0.mIsNewlyAdded = false;
                AnimationOrAnimator fragmentManagerImpl$AnimationOrAnimator0 = this.loadAnimation(fragment0, fragment0.getNextTransition(), true, fragment0.getNextTransitionStyle());
                if(fragmentManagerImpl$AnimationOrAnimator0 != null) {
                    FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(fragment0.mView, fragmentManagerImpl$AnimationOrAnimator0);
                    if(fragmentManagerImpl$AnimationOrAnimator0.animation == null) {
                        fragmentManagerImpl$AnimationOrAnimator0.animator.setTarget(fragment0.mView);
                        fragmentManagerImpl$AnimationOrAnimator0.animator.start();
                    }
                    else {
                        fragment0.mView.startAnimation(fragmentManagerImpl$AnimationOrAnimator0.animation);
                    }
                }
            }
        }
        if(fragment0.mHiddenChanged) {
            this.completeShowHideFragment(fragment0);
        }
    }

    void moveToState(int v, boolean z) {
        if(this.mHost == null && v != 0) {
            throw new IllegalStateException("No activity");
        }
        if(!z && v == this.mCurState) {
            return;
        }
        this.mCurState = v;
        if(this.mActive != null) {
            int v1 = this.mAdded.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                this.moveFragmentToExpectedState(((Fragment)this.mAdded.get(v2)));
            }
            int v3 = this.mActive.size();
            for(int v4 = 0; v4 < v3; ++v4) {
                Fragment fragment0 = (Fragment)this.mActive.valueAt(v4);
                if(fragment0 != null && (fragment0.mRemoving || fragment0.mDetached) && !fragment0.mIsNewlyAdded) {
                    this.moveFragmentToExpectedState(fragment0);
                }
            }
            this.startPendingDeferredFragments();
            if(this.mNeedMenuInvalidate) {
                FragmentHostCallback fragmentHostCallback0 = this.mHost;
                if(fragmentHostCallback0 != null && this.mCurState == 4) {
                    fragmentHostCallback0.onSupportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }

    void moveToState(Fragment fragment0) {
        this.moveToState(fragment0, this.mCurState, 0, 0, false);
    }

    void moveToState(Fragment fragment0, int v, int v1, int v2, boolean z) {
        String s;
        ViewGroup viewGroup0;
        int v4;
        int v3 = 1;
        if(!fragment0.mAdded || fragment0.mDetached) {
            v4 = v;
            if(v4 > 1) {
                v4 = 1;
            }
        }
        else {
            v4 = v;
        }
        if(fragment0.mRemoving && v4 > fragment0.mState) {
            v4 = fragment0.mState != 0 || !fragment0.isInBackStack() ? fragment0.mState : 1;
        }
        int v5 = !fragment0.mDeferStart || fragment0.mState >= 3 || v4 <= 2 ? v4 : 2;
        if(fragment0.mState <= v5) {
            if(fragment0.mFromLayout && !fragment0.mInLayout) {
                return;
            }
            if(fragment0.getAnimatingAway() != null || fragment0.getAnimator() != null) {
                fragment0.setAnimatingAway(null);
                fragment0.setAnimator(null);
                this.moveToState(fragment0, fragment0.getStateAfterAnimating(), 0, 0, true);
            }
            switch(fragment0.mState) {
                case 0: {
                    if(v5 > 0) {
                        if(FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "moveto CREATED: " + fragment0);
                        }
                        if(fragment0.mSavedFragmentState != null) {
                            fragment0.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                            fragment0.mSavedViewState = fragment0.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                            fragment0.mTarget = this.getFragment(fragment0.mSavedFragmentState, "android:target_state");
                            if(fragment0.mTarget != null) {
                                fragment0.mTargetRequestCode = fragment0.mSavedFragmentState.getInt("android:target_req_state", 0);
                            }
                            if(fragment0.mSavedUserVisibleHint == null) {
                                fragment0.mUserVisibleHint = fragment0.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                            }
                            else {
                                fragment0.mUserVisibleHint = fragment0.mSavedUserVisibleHint.booleanValue();
                                fragment0.mSavedUserVisibleHint = null;
                            }
                            if(!fragment0.mUserVisibleHint) {
                                fragment0.mDeferStart = true;
                                if(v5 > 2) {
                                    v5 = 2;
                                }
                            }
                        }
                        FragmentHostCallback fragmentHostCallback0 = this.mHost;
                        fragment0.mHost = fragmentHostCallback0;
                        fragment0.mParentFragment = this.mParent;
                        fragment0.mFragmentManager = this.mParent == null ? fragmentHostCallback0.getFragmentManagerImpl() : this.mParent.mChildFragmentManager;
                        if(fragment0.mTarget != null) {
                            if(this.mActive.get(fragment0.mTarget.mIndex) != fragment0.mTarget) {
                                throw new IllegalStateException("Fragment " + fragment0 + " declared target fragment " + fragment0.mTarget + " that does not belong to this FragmentManager!");
                            }
                            if(fragment0.mTarget.mState < 1) {
                                this.moveToState(fragment0.mTarget, 1, 0, 0, true);
                            }
                        }
                        this.dispatchOnFragmentPreAttached(fragment0, this.mHost.getContext(), false);
                        fragment0.mCalled = false;
                        fragment0.onAttach(this.mHost.getContext());
                        if(!fragment0.mCalled) {
                            throw new SuperNotCalledException("Fragment " + fragment0 + " did not call through to super.onAttach()");
                        }
                        if(fragment0.mParentFragment == null) {
                            this.mHost.onAttachFragment(fragment0);
                        }
                        this.dispatchOnFragmentAttached(fragment0, this.mHost.getContext(), false);
                        if(fragment0.mIsCreated) {
                            fragment0.restoreChildFragmentState(fragment0.mSavedFragmentState);
                            fragment0.mState = 1;
                        }
                        else {
                            this.dispatchOnFragmentPreCreated(fragment0, fragment0.mSavedFragmentState, false);
                            fragment0.performCreate(fragment0.mSavedFragmentState);
                            this.dispatchOnFragmentCreated(fragment0, fragment0.mSavedFragmentState, false);
                        }
                        fragment0.mRetaining = false;
                    }
                    goto label_65;
                }
                case 1: {
                label_65:
                    this.ensureInflatedFragmentView(fragment0);
                    if(v5 > 1) {
                        if(FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + fragment0);
                        }
                        if(!fragment0.mFromLayout) {
                            switch(fragment0.mContainerId) {
                                case -1: {
                                    this.throwException(new IllegalArgumentException("Cannot create fragment " + fragment0 + " for a container view with no id"));
                                label_73:
                                    viewGroup0 = (ViewGroup)this.mContainer.onFindViewById(fragment0.mContainerId);
                                    if(viewGroup0 == null && !fragment0.mRestored) {
                                        try {
                                            s = "unknown";
                                            s = fragment0.getResources().getResourceName(fragment0.mContainerId);
                                        }
                                        catch(Resources.NotFoundException unused_ex) {
                                        }
                                        this.throwException(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(fragment0.mContainerId) + " (" + s + ") for fragment " + fragment0));
                                    }
                                    break;
                                }
                                case 0: {
                                    viewGroup0 = null;
                                    break;
                                }
                                default: {
                                    goto label_73;
                                }
                            }
                            fragment0.mContainer = viewGroup0;
                            fragment0.performCreateView(fragment0.performGetLayoutInflater(fragment0.mSavedFragmentState), viewGroup0, fragment0.mSavedFragmentState);
                            if(fragment0.mView == null) {
                                fragment0.mInnerView = null;
                            }
                            else {
                                fragment0.mInnerView = fragment0.mView;
                                fragment0.mView.setSaveFromParentEnabled(false);
                                if(viewGroup0 != null) {
                                    viewGroup0.addView(fragment0.mView);
                                }
                                if(fragment0.mHidden) {
                                    fragment0.mView.setVisibility(8);
                                }
                                fragment0.onViewCreated(fragment0.mView, fragment0.mSavedFragmentState);
                                this.dispatchOnFragmentViewCreated(fragment0, fragment0.mView, fragment0.mSavedFragmentState, false);
                                if(fragment0.mView.getVisibility() != 0 || fragment0.mContainer == null) {
                                    v3 = 0;
                                }
                                fragment0.mIsNewlyAdded = v3;
                            }
                        }
                        fragment0.performActivityCreated(fragment0.mSavedFragmentState);
                        this.dispatchOnFragmentActivityCreated(fragment0, fragment0.mSavedFragmentState, false);
                        if(fragment0.mView != null) {
                            fragment0.restoreViewState(fragment0.mSavedFragmentState);
                        }
                        fragment0.mSavedFragmentState = null;
                    }
                    goto label_101;
                }
                case 2: {
                label_101:
                    if(v5 > 2) {
                        if(FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "moveto STARTED: " + fragment0);
                        }
                        fragment0.performStart();
                        this.dispatchOnFragmentStarted(fragment0, false);
                    }
                    break;
                }
                case 3: {
                    break;
                }
                default: {
                    v3 = v5;
                    goto label_183;
                }
            }
            if(v5 > 3) {
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "moveto RESUMED: " + fragment0);
                }
                fragment0.performResume();
                this.dispatchOnFragmentResumed(fragment0, false);
                fragment0.mSavedFragmentState = null;
                fragment0.mSavedViewState = null;
            }
            v3 = v5;
        }
        else if(fragment0.mState > v5) {
            switch(fragment0.mState) {
                case 1: {
                label_149:
                    if(v5 < 1) {
                        if(this.mDestroyed) {
                            if(fragment0.getAnimatingAway() != null) {
                                View view0 = fragment0.getAnimatingAway();
                                fragment0.setAnimatingAway(null);
                                view0.clearAnimation();
                            }
                            else if(fragment0.getAnimator() != null) {
                                Animator animator0 = fragment0.getAnimator();
                                fragment0.setAnimator(null);
                                animator0.cancel();
                            }
                        }
                        if(fragment0.getAnimatingAway() != null || fragment0.getAnimator() != null) {
                            fragment0.setStateAfterAnimating(v5);
                        }
                        else {
                            if(FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "movefrom CREATED: " + fragment0);
                            }
                            if(fragment0.mRetaining) {
                                fragment0.mState = 0;
                            }
                            else {
                                fragment0.performDestroy();
                                this.dispatchOnFragmentDestroyed(fragment0, false);
                            }
                            fragment0.performDetach();
                            this.dispatchOnFragmentDetached(fragment0, false);
                            if(!z) {
                                if(fragment0.mRetaining) {
                                    fragment0.mHost = null;
                                    fragment0.mParentFragment = null;
                                    fragment0.mFragmentManager = null;
                                }
                                else {
                                    this.makeInactive(fragment0);
                                }
                            }
                            goto label_182;
                        }
                    }
                    else {
                        v3 = v5;
                    }
                    break;
                }
                case 2: {
                label_128:
                    if(v5 < 2) {
                        if(FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + fragment0);
                        }
                        if(fragment0.mView != null && this.mHost.onShouldSaveFragmentState(fragment0) && fragment0.mSavedViewState == null) {
                            this.saveFragmentViewState(fragment0);
                        }
                        fragment0.performDestroyView();
                        this.dispatchOnFragmentViewDestroyed(fragment0, false);
                        if(fragment0.mView != null && fragment0.mContainer != null) {
                            fragment0.mContainer.endViewTransition(fragment0.mView);
                            fragment0.mView.clearAnimation();
                            AnimationOrAnimator fragmentManagerImpl$AnimationOrAnimator0 = this.mCurState <= 0 || this.mDestroyed || fragment0.mView.getVisibility() != 0 || fragment0.mPostponedAlpha < 0.0f ? null : this.loadAnimation(fragment0, v1, false, v2);
                            fragment0.mPostponedAlpha = 0.0f;
                            if(fragmentManagerImpl$AnimationOrAnimator0 != null) {
                                this.animateRemoveFragment(fragment0, fragmentManagerImpl$AnimationOrAnimator0, v5);
                            }
                            fragment0.mContainer.removeView(fragment0.mView);
                        }
                        fragment0.mContainer = null;
                        fragment0.mView = null;
                        fragment0.mViewLifecycleOwner = null;
                        fragment0.mViewLifecycleOwnerLiveData.setValue(null);
                        fragment0.mInnerView = null;
                        fragment0.mInLayout = false;
                    }
                    goto label_149;
                }
                case 3: {
                label_123:
                    if(v5 < 3) {
                        if(FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "movefrom STARTED: " + fragment0);
                        }
                        fragment0.performStop();
                        this.dispatchOnFragmentStopped(fragment0, false);
                    }
                    goto label_128;
                }
                case 4: {
                    if(v5 < 4) {
                        if(FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "movefrom RESUMED: " + fragment0);
                        }
                        fragment0.performPause();
                        this.dispatchOnFragmentPaused(fragment0, false);
                    }
                    goto label_123;
                }
                default: {
                    goto label_182;
                }
            }
        }
        else {
        label_182:
            v3 = v5;
        }
    label_183:
        if(fragment0.mState != v3) {
            Log.w("FragmentManager", "moveToState: Fragment state for " + fragment0 + " not updated inline; " + "expected state " + v3 + " found " + fragment0.mState);
            fragment0.mState = v3;
        }
    }

    public void noteStateNotSaved() {
        this.mSavedNonConfig = null;
        this.mStateSaved = false;
        this.mStopped = false;
        int v1 = this.mAdded.size();
        for(int v = 0; v < v1; ++v) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v);
            if(fragment0 != null) {
                fragment0.noteStateNotSaved();
            }
        }
    }

    @Override  // android.view.LayoutInflater$Factory2
    public View onCreateView(View view0, String s, Context context0, AttributeSet attributeSet0) {
        Fragment fragment2;
        if(!"fragment".equals(s)) {
            return null;
        }
        String s1 = attributeSet0.getAttributeValue(null, "class");
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, FragmentTag.Fragment);
        int v = 0;
        String s2 = s1 == null ? typedArray0.getString(0) : s1;
        int v1 = typedArray0.getResourceId(1, -1);
        String s3 = typedArray0.getString(2);
        typedArray0.recycle();
        if(!Fragment.isSupportFragmentClass(this.mHost.getContext(), s2)) {
            return null;
        }
        if(view0 != null) {
            v = view0.getId();
        }
        if(v == -1 && v1 == -1 && s3 == null) {
            throw new IllegalArgumentException(attributeSet0.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + s2);
        }
        Fragment fragment0 = v1 == -1 ? null : this.findFragmentById(v1);
        if(fragment0 == null && s3 != null) {
            fragment0 = this.findFragmentByTag(s3);
        }
        if(fragment0 == null && v != -1) {
            fragment0 = this.findFragmentById(v);
        }
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(v1) + " fname=" + s2 + " existing=" + fragment0);
        }
        if(fragment0 == null) {
            Fragment fragment1 = this.mContainer.instantiate(context0, s2, null);
            fragment1.mFromLayout = true;
            fragment1.mFragmentId = v1 == 0 ? v : v1;
            fragment1.mContainerId = v;
            fragment1.mTag = s3;
            fragment1.mInLayout = true;
            fragment1.mFragmentManager = this;
            FragmentHostCallback fragmentHostCallback0 = this.mHost;
            fragment1.mHost = fragmentHostCallback0;
            fragment1.onInflate(fragmentHostCallback0.getContext(), attributeSet0, fragment1.mSavedFragmentState);
            this.addFragment(fragment1, true);
            fragment2 = fragment1;
        }
        else if(!fragment0.mInLayout) {
            fragment0.mInLayout = true;
            fragment0.mHost = this.mHost;
            if(!fragment0.mRetaining) {
                fragment0.onInflate(this.mHost.getContext(), attributeSet0, fragment0.mSavedFragmentState);
            }
            fragment2 = fragment0;
        }
        else {
            throw new IllegalArgumentException(attributeSet0.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(v1) + ", tag " + s3 + ", or parent id 0x" + Integer.toHexString(v) + " with another fragment for " + s2);
        }
        if(this.mCurState >= 1 || !fragment2.mFromLayout) {
            this.moveToState(fragment2);
        }
        else {
            this.moveToState(fragment2, 1, 0, 0, false);
        }
        if(fragment2.mView == null) {
            throw new IllegalStateException("Fragment " + s2 + " did not create a view.");
        }
        if(v1 != 0) {
            fragment2.mView.setId(v1);
        }
        if(fragment2.mView.getTag() == null) {
            fragment2.mView.setTag(s3);
        }
        return fragment2.mView;
    }

    @Override  // android.view.LayoutInflater$Factory
    public View onCreateView(String s, Context context0, AttributeSet attributeSet0) {
        return this.onCreateView(null, s, context0, attributeSet0);
    }

    public void performPendingDeferredStart(Fragment fragment0) {
        if(fragment0.mDeferStart) {
            if(this.mExecutingActions) {
                this.mHavePendingDeferredStart = true;
                return;
            }
            fragment0.mDeferStart = false;
            this.moveToState(fragment0, this.mCurState, 0, 0, false);
        }
    }

    @Override  // androidx.fragment.app.FragmentManager
    public void popBackStack() {
        this.enqueueAction(new PopBackStackState(this, null, -1, 0), false);
    }

    @Override  // androidx.fragment.app.FragmentManager
    public void popBackStack(int v, int v1) {
        if(v < 0) {
            throw new IllegalArgumentException("Bad id: " + v);
        }
        this.enqueueAction(new PopBackStackState(this, null, v, v1), false);
    }

    @Override  // androidx.fragment.app.FragmentManager
    public void popBackStack(@Nullable String s, int v) {
        this.enqueueAction(new PopBackStackState(this, s, -1, v), false);
    }

    private boolean popBackStackImmediate(String s, int v, int v1) {
        this.execPendingActions();
        this.ensureExecReady(true);
        Fragment fragment0 = this.mPrimaryNav;
        if(fragment0 != null && v < 0 && s == null) {
            FragmentManager fragmentManager0 = fragment0.peekChildFragmentManager();
            if(fragmentManager0 != null && fragmentManager0.popBackStackImmediate()) {
                return true;
            }
        }
        boolean z = this.popBackStackState(this.mTmpRecords, this.mTmpIsPop, s, v, v1);
        if(z) {
            try {
                this.mExecutingActions = true;
                this.removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            }
            finally {
                this.cleanupExec();
            }
        }
        this.doPendingDeferredStart();
        this.burpActive();
        return z;
    }

    @Override  // androidx.fragment.app.FragmentManager
    public boolean popBackStackImmediate() {
        this.checkStateLoss();
        return this.popBackStackImmediate(null, -1, 0);
    }

    @Override  // androidx.fragment.app.FragmentManager
    public boolean popBackStackImmediate(int v, int v1) {
        this.checkStateLoss();
        this.execPendingActions();
        if(v < 0) {
            throw new IllegalArgumentException("Bad id: " + v);
        }
        return this.popBackStackImmediate(null, v, v1);
    }

    @Override  // androidx.fragment.app.FragmentManager
    public boolean popBackStackImmediate(@Nullable String s, int v) {
        this.checkStateLoss();
        return this.popBackStackImmediate(s, -1, v);
    }

    boolean popBackStackState(ArrayList arrayList0, ArrayList arrayList1, String s, int v, int v1) {
        int v3;
        ArrayList arrayList2 = this.mBackStack;
        if(arrayList2 == null) {
            return false;
        }
        if(s == null && v < 0 && (v1 & 1) == 0) {
            int v2 = arrayList2.size();
            if(v2 - 1 < 0) {
                return false;
            }
            arrayList0.add(this.mBackStack.remove(v2 - 1));
            arrayList1.add(Boolean.TRUE);
            return true;
        }
        if(s != null || v >= 0) {
            for(v3 = this.mBackStack.size() - 1; v3 >= 0; --v3) {
                BackStackRecord backStackRecord0 = (BackStackRecord)this.mBackStack.get(v3);
                if(s != null && s.equals(backStackRecord0.getName()) || v >= 0 && v == backStackRecord0.mIndex) {
                    break;
                }
            }
            if(v3 < 0) {
                return false;
            }
            if((v1 & 1) != 0) {
                --v3;
                while(v3 >= 0) {
                    BackStackRecord backStackRecord1 = (BackStackRecord)this.mBackStack.get(v3);
                    if((s == null || !s.equals(backStackRecord1.getName())) && (v < 0 || v != backStackRecord1.mIndex)) {
                        break;
                    }
                    --v3;
                }
            }
        }
        else {
            v3 = -1;
        }
        if(v3 == this.mBackStack.size() - 1) {
            return false;
        }
        for(int v4 = this.mBackStack.size() - 1; v4 > v3; --v4) {
            arrayList0.add(this.mBackStack.remove(v4));
            arrayList1.add(Boolean.TRUE);
        }
        return true;
    }

    private int postponePostponableTransactions(ArrayList arrayList0, ArrayList arrayList1, int v, int v1, ArraySet arraySet0) {
        int v2 = v1 - 1;
        int v3 = v1;
        while(v2 >= v) {
            BackStackRecord backStackRecord0 = (BackStackRecord)arrayList0.get(v2);
            boolean z = ((Boolean)arrayList1.get(v2)).booleanValue();
            if(backStackRecord0.isPostponed() && !backStackRecord0.interactsWith(arrayList0, v2 + 1, v1)) {
                if(this.mPostponedTransactions == null) {
                    this.mPostponedTransactions = new ArrayList();
                }
                StartEnterTransitionListener fragmentManagerImpl$StartEnterTransitionListener0 = new StartEnterTransitionListener(backStackRecord0, z);
                this.mPostponedTransactions.add(fragmentManagerImpl$StartEnterTransitionListener0);
                backStackRecord0.setOnStartPostponedListener(fragmentManagerImpl$StartEnterTransitionListener0);
                if(z) {
                    backStackRecord0.executeOps();
                }
                else {
                    backStackRecord0.executePopOps(false);
                }
                --v3;
                if(v2 != v3) {
                    arrayList0.remove(v2);
                    arrayList0.add(v3, backStackRecord0);
                }
                this.addAddedFragments(arraySet0);
            }
            --v2;
        }
        return v3;
    }

    @Override  // androidx.fragment.app.FragmentManager
    public void putFragment(Bundle bundle0, String s, Fragment fragment0) {
        if(fragment0.mIndex < 0) {
            this.throwException(new IllegalStateException("Fragment " + fragment0 + " is not currently in the FragmentManager"));
        }
        bundle0.putInt(s, fragment0.mIndex);
    }

    @Override  // androidx.fragment.app.FragmentManager
    public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentManager$FragmentLifecycleCallbacks0, boolean z) {
        FragmentLifecycleCallbacksHolder fragmentManagerImpl$FragmentLifecycleCallbacksHolder0 = new FragmentLifecycleCallbacksHolder(fragmentManager$FragmentLifecycleCallbacks0, z);
        this.mLifecycleCallbacks.add(fragmentManagerImpl$FragmentLifecycleCallbacksHolder0);
    }

    public void removeFragment(Fragment fragment0) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "remove: " + fragment0 + " nesting=" + fragment0.mBackStackNesting);
        }
        boolean z = fragment0.isInBackStack();
        if(!fragment0.mDetached || !z != 0) {
            synchronized(this.mAdded) {
                this.mAdded.remove(fragment0);
            }
            if(fragment0.mHasMenu && fragment0.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment0.mAdded = false;
            fragment0.mRemoving = true;
        }
    }

    @Override  // androidx.fragment.app.FragmentManager
    public void removeOnBackStackChangedListener(OnBackStackChangedListener fragmentManager$OnBackStackChangedListener0) {
        ArrayList arrayList0 = this.mBackStackChangeListeners;
        if(arrayList0 != null) {
            arrayList0.remove(fragmentManager$OnBackStackChangedListener0);
        }
    }

    private void removeRedundantOperationsAndExecute(ArrayList arrayList0, ArrayList arrayList1) {
        if(arrayList0 != null && !arrayList0.isEmpty()) {
            if(arrayList1 == null || arrayList0.size() != arrayList1.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            this.executePostponedTransaction(arrayList0, arrayList1);
            int v = arrayList0.size();
            int v2 = 0;
            for(int v1 = 0; v1 < v; ++v1) {
                if(!((BackStackRecord)arrayList0.get(v1)).mReorderingAllowed) {
                    if(v2 != v1) {
                        this.executeOpsTogether(arrayList0, arrayList1, v2, v1);
                    }
                    v2 = v1 + 1;
                    if(((Boolean)arrayList1.get(v1)).booleanValue()) {
                        while(v2 < v && ((Boolean)arrayList1.get(v2)).booleanValue() && !((BackStackRecord)arrayList0.get(v2)).mReorderingAllowed) {
                            ++v2;
                        }
                    }
                    this.executeOpsTogether(arrayList0, arrayList1, v1, v2);
                    v1 = v2 - 1;
                }
            }
            if(v2 != v) {
                this.executeOpsTogether(arrayList0, arrayList1, v2, v);
            }
        }
    }

    void reportBackStackChanged() {
        if(this.mBackStackChangeListeners != null) {
            for(int v = 0; v < this.mBackStackChangeListeners.size(); ++v) {
                ((OnBackStackChangedListener)this.mBackStackChangeListeners.get(v)).onBackStackChanged();
            }
        }
    }

    void restoreAllState(Parcelable parcelable0, FragmentManagerNonConfig fragmentManagerNonConfig0) {
        List list2;
        List list1;
        if(parcelable0 == null) {
            return;
        }
        if(((FragmentManagerState)parcelable0).mActive == null) {
            return;
        }
        if(fragmentManagerNonConfig0 == null) {
            list1 = null;
            list2 = null;
        }
        else {
            List list0 = fragmentManagerNonConfig0.getFragments();
            list1 = fragmentManagerNonConfig0.getChildNonConfigs();
            list2 = fragmentManagerNonConfig0.getViewModelStores();
            int v = list0 == null ? 0 : list0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                Fragment fragment0 = (Fragment)list0.get(v1);
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment0);
                }
                int v2;
                for(v2 = 0; v2 < ((FragmentManagerState)parcelable0).mActive.length && ((FragmentManagerState)parcelable0).mActive[v2].mIndex != fragment0.mIndex; ++v2) {
                }
                if(v2 == ((FragmentManagerState)parcelable0).mActive.length) {
                    this.throwException(new IllegalStateException("Could not find active fragment with index " + fragment0.mIndex));
                }
                FragmentState fragmentState0 = ((FragmentManagerState)parcelable0).mActive[v2];
                fragmentState0.mInstance = fragment0;
                fragment0.mSavedViewState = null;
                fragment0.mBackStackNesting = 0;
                fragment0.mInLayout = false;
                fragment0.mAdded = false;
                fragment0.mTarget = null;
                if(fragmentState0.mSavedFragmentState != null) {
                    fragmentState0.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                    fragment0.mSavedViewState = fragmentState0.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                    fragment0.mSavedFragmentState = fragmentState0.mSavedFragmentState;
                }
            }
        }
        this.mActive = new SparseArray(((FragmentManagerState)parcelable0).mActive.length);
        for(int v3 = 0; v3 < ((FragmentManagerState)parcelable0).mActive.length; ++v3) {
            FragmentState fragmentState1 = ((FragmentManagerState)parcelable0).mActive[v3];
            if(fragmentState1 != null) {
                FragmentManagerNonConfig fragmentManagerNonConfig1 = list1 == null || v3 >= list1.size() ? null : ((FragmentManagerNonConfig)list1.get(v3));
                ViewModelStore viewModelStore0 = list2 == null || v3 >= list2.size() ? null : ((ViewModelStore)list2.get(v3));
                Fragment fragment1 = fragmentState1.instantiate(this.mHost, this.mContainer, this.mParent, fragmentManagerNonConfig1, viewModelStore0);
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "restoreAllState: active #" + v3 + ": " + fragment1);
                }
                this.mActive.put(fragment1.mIndex, fragment1);
                fragmentState1.mInstance = null;
            }
        }
        if(fragmentManagerNonConfig0 != null) {
            List list3 = fragmentManagerNonConfig0.getFragments();
            int v4 = list3 == null ? 0 : list3.size();
            for(int v5 = 0; v5 < v4; ++v5) {
                Fragment fragment2 = (Fragment)list3.get(v5);
                if(fragment2.mTargetIndex >= 0) {
                    fragment2.mTarget = (Fragment)this.mActive.get(fragment2.mTargetIndex);
                    if(fragment2.mTarget == null) {
                        Log.w("FragmentManager", "Re-attaching retained fragment " + fragment2 + " target no longer exists: " + fragment2.mTargetIndex);
                    }
                }
            }
        }
        this.mAdded.clear();
        if(((FragmentManagerState)parcelable0).mAdded != null) {
            int v6 = 0;
            while(v6 < ((FragmentManagerState)parcelable0).mAdded.length) {
                Fragment fragment3 = (Fragment)this.mActive.get(((FragmentManagerState)parcelable0).mAdded[v6]);
                if(fragment3 == null) {
                    this.throwException(new IllegalStateException("No instantiated fragment for index #" + ((FragmentManagerState)parcelable0).mAdded[v6]));
                }
                fragment3.mAdded = true;
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "restoreAllState: added #" + v6 + ": " + fragment3);
                }
                if(this.mAdded.contains(fragment3)) {
                    throw new IllegalStateException("Already added!");
                }
                synchronized(this.mAdded) {
                    this.mAdded.add(fragment3);
                    ++v6;
                }
            }
        }
        if(((FragmentManagerState)parcelable0).mBackStack == null) {
            this.mBackStack = null;
        }
        else {
            this.mBackStack = new ArrayList(((FragmentManagerState)parcelable0).mBackStack.length);
            for(int v7 = 0; v7 < ((FragmentManagerState)parcelable0).mBackStack.length; ++v7) {
                BackStackRecord backStackRecord0 = ((FragmentManagerState)parcelable0).mBackStack[v7].instantiate(this);
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + v7 + " (index " + backStackRecord0.mIndex + "): " + backStackRecord0);
                    PrintWriter printWriter0 = new PrintWriter(new LogWriter("FragmentManager"));
                    backStackRecord0.dump("  ", printWriter0, false);
                    printWriter0.close();
                }
                this.mBackStack.add(backStackRecord0);
                if(backStackRecord0.mIndex >= 0) {
                    this.setBackStackIndex(backStackRecord0.mIndex, backStackRecord0);
                }
            }
        }
        if(((FragmentManagerState)parcelable0).mPrimaryNavActiveIndex >= 0) {
            this.mPrimaryNav = (Fragment)this.mActive.get(((FragmentManagerState)parcelable0).mPrimaryNavActiveIndex);
        }
        this.mNextFragmentIndex = ((FragmentManagerState)parcelable0).mNextFragmentIndex;
    }

    FragmentManagerNonConfig retainNonConfig() {
        FragmentManagerImpl.setRetaining(this.mSavedNonConfig);
        return this.mSavedNonConfig;
    }

    public static int reverseTransit(int v) {
        switch(v) {
            case 0x1001: {
                return 0x2002;
            }
            case 0x1003: {
                return 0x1003;
            }
            case 0x2002: {
                return 0x1001;
            }
            default: {
                return 0;
            }
        }
    }

    Parcelable saveAllState() {
        int[] arr_v;
        this.forcePostponedTransactions();
        this.endAnimatingAwayFragments();
        this.execPendingActions();
        this.mStateSaved = true;
        BackStackState[] arr_backStackState = null;
        this.mSavedNonConfig = null;
        if(this.mActive != null && this.mActive.size() > 0) {
            int v = this.mActive.size();
            FragmentState[] arr_fragmentState = new FragmentState[v];
            boolean z = false;
            for(int v2 = 0; v2 < v; ++v2) {
                Fragment fragment0 = (Fragment)this.mActive.valueAt(v2);
                if(fragment0 != null) {
                    if(fragment0.mIndex < 0) {
                        this.throwException(new IllegalStateException("Failure saving state: active " + fragment0 + " has cleared index: " + fragment0.mIndex));
                    }
                    FragmentState fragmentState0 = new FragmentState(fragment0);
                    arr_fragmentState[v2] = fragmentState0;
                    if(fragment0.mState <= 0 || fragmentState0.mSavedFragmentState != null) {
                        fragmentState0.mSavedFragmentState = fragment0.mSavedFragmentState;
                    }
                    else {
                        fragmentState0.mSavedFragmentState = this.saveFragmentBasicState(fragment0);
                        if(fragment0.mTarget != null) {
                            if(fragment0.mTarget.mIndex < 0) {
                                this.throwException(new IllegalStateException("Failure saving state: " + fragment0 + " has target not in fragment manager: " + fragment0.mTarget));
                            }
                            if(fragmentState0.mSavedFragmentState == null) {
                                fragmentState0.mSavedFragmentState = new Bundle();
                            }
                            this.putFragment(fragmentState0.mSavedFragmentState, "android:target_state", fragment0.mTarget);
                            if(fragment0.mTargetRequestCode != 0) {
                                fragmentState0.mSavedFragmentState.putInt("android:target_req_state", fragment0.mTargetRequestCode);
                            }
                        }
                    }
                    if(FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Saved state of " + fragment0 + ": " + fragmentState0.mSavedFragmentState);
                    }
                    z = true;
                }
            }
            if(!z) {
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "saveAllState: no fragments!");
                }
                return null;
            }
            int v3 = this.mAdded.size();
            if(v3 > 0) {
                arr_v = new int[v3];
                for(int v4 = 0; v4 < v3; ++v4) {
                    arr_v[v4] = ((Fragment)this.mAdded.get(v4)).mIndex;
                    if(arr_v[v4] < 0) {
                        this.throwException(new IllegalStateException("Failure saving state: active " + this.mAdded.get(v4) + " has cleared index: " + arr_v[v4]));
                    }
                    if(FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "saveAllState: adding fragment #" + v4 + ": " + this.mAdded.get(v4));
                    }
                }
            }
            else {
                arr_v = null;
            }
            ArrayList arrayList0 = this.mBackStack;
            if(arrayList0 != null) {
                int v5 = arrayList0.size();
                if(v5 > 0) {
                    arr_backStackState = new BackStackState[v5];
                    for(int v1 = 0; v1 < v5; ++v1) {
                        arr_backStackState[v1] = new BackStackState(((BackStackRecord)this.mBackStack.get(v1)));
                        if(FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "saveAllState: adding back stack #" + v1 + ": " + this.mBackStack.get(v1));
                        }
                    }
                }
            }
            Parcelable parcelable0 = new FragmentManagerState();
            parcelable0.mActive = arr_fragmentState;
            parcelable0.mAdded = arr_v;
            parcelable0.mBackStack = arr_backStackState;
            Fragment fragment1 = this.mPrimaryNav;
            if(fragment1 != null) {
                parcelable0.mPrimaryNavActiveIndex = fragment1.mIndex;
            }
            parcelable0.mNextFragmentIndex = this.mNextFragmentIndex;
            this.saveNonConfig();
            return parcelable0;
        }
        return null;
    }

    Bundle saveFragmentBasicState(Fragment fragment0) {
        Bundle bundle0;
        if(this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }
        fragment0.performSaveInstanceState(this.mStateBundle);
        this.dispatchOnFragmentSaveInstanceState(fragment0, this.mStateBundle, false);
        if(this.mStateBundle.isEmpty()) {
            bundle0 = null;
        }
        else {
            bundle0 = this.mStateBundle;
            this.mStateBundle = null;
        }
        if(fragment0.mView != null) {
            this.saveFragmentViewState(fragment0);
        }
        if(fragment0.mSavedViewState != null) {
            if(bundle0 == null) {
                bundle0 = new Bundle();
            }
            bundle0.putSparseParcelableArray("android:view_state", fragment0.mSavedViewState);
        }
        if(!fragment0.mUserVisibleHint) {
            if(bundle0 == null) {
                bundle0 = new Bundle();
            }
            bundle0.putBoolean("android:user_visible_hint", fragment0.mUserVisibleHint);
        }
        return bundle0;
    }

    @Override  // androidx.fragment.app.FragmentManager
    @Nullable
    public SavedState saveFragmentInstanceState(Fragment fragment0) {
        if(fragment0.mIndex < 0) {
            this.throwException(new IllegalStateException("Fragment " + fragment0 + " is not currently in the FragmentManager"));
        }
        if(fragment0.mState > 0) {
            Bundle bundle0 = this.saveFragmentBasicState(fragment0);
            return bundle0 == null ? null : new SavedState(bundle0);
        }
        return null;
    }

    void saveFragmentViewState(Fragment fragment0) {
        if(fragment0.mInnerView == null) {
            return;
        }
        SparseArray sparseArray0 = this.mStateArray;
        if(sparseArray0 == null) {
            this.mStateArray = new SparseArray();
        }
        else {
            sparseArray0.clear();
        }
        fragment0.mInnerView.saveHierarchyState(this.mStateArray);
        if(this.mStateArray.size() > 0) {
            fragment0.mSavedViewState = this.mStateArray;
            this.mStateArray = null;
        }
    }

    void saveNonConfig() {
        FragmentManagerNonConfig fragmentManagerNonConfig0;
        List list2;
        List list1;
        List list0;
        if(this.mActive == null) {
            list0 = null;
            list1 = null;
            list2 = null;
        }
        else {
            list0 = null;
            list1 = null;
            list2 = null;
            for(int v = 0; v < this.mActive.size(); ++v) {
                Fragment fragment0 = (Fragment)this.mActive.valueAt(v);
                if(fragment0 != null) {
                    if(fragment0.mRetainInstance) {
                        if(list0 == null) {
                            list0 = new ArrayList();
                        }
                        ((ArrayList)list0).add(fragment0);
                        fragment0.mTargetIndex = fragment0.mTarget == null ? -1 : fragment0.mTarget.mIndex;
                        if(FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment0);
                        }
                    }
                    if(fragment0.mChildFragmentManager == null) {
                        fragmentManagerNonConfig0 = fragment0.mChildNonConfig;
                    }
                    else {
                        fragment0.mChildFragmentManager.saveNonConfig();
                        fragmentManagerNonConfig0 = fragment0.mChildFragmentManager.mSavedNonConfig;
                    }
                    if(list1 == null && fragmentManagerNonConfig0 != null) {
                        list1 = new ArrayList(this.mActive.size());
                        for(int v1 = 0; v1 < v; ++v1) {
                            ((ArrayList)list1).add(null);
                        }
                    }
                    if(list1 != null) {
                        ((ArrayList)list1).add(fragmentManagerNonConfig0);
                    }
                    if(list2 == null && fragment0.mViewModelStore != null) {
                        list2 = new ArrayList(this.mActive.size());
                        for(int v2 = 0; v2 < v; ++v2) {
                            ((ArrayList)list2).add(null);
                        }
                    }
                    if(list2 != null) {
                        ((ArrayList)list2).add(fragment0.mViewModelStore);
                    }
                }
            }
        }
        if(list0 == null && list1 == null && list2 == null) {
            this.mSavedNonConfig = null;
            return;
        }
        this.mSavedNonConfig = new FragmentManagerNonConfig(list0, list1, list2);
    }

    void scheduleCommit() {
        boolean z = false;
        synchronized(this) {
            boolean z1 = this.mPostponedTransactions != null && !this.mPostponedTransactions.isEmpty();
            if(this.mPendingActions != null && this.mPendingActions.size() == 1) {
                z = true;
            }
            if(z1 || z) {
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                this.mHost.getHandler().post(this.mExecCommit);
            }
        }
    }

    public void setBackStackIndex(int v, BackStackRecord backStackRecord0) {
        synchronized(this) {
            if(this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList();
            }
            int v2 = this.mBackStackIndices.size();
            if(v < v2) {
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Setting back stack index " + v + " to " + backStackRecord0);
                }
                this.mBackStackIndices.set(v, backStackRecord0);
            }
            else {
                while(v2 < v) {
                    this.mBackStackIndices.add(null);
                    if(this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList();
                    }
                    if(FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Adding available back stack index " + v2);
                    }
                    this.mAvailBackStackIndices.add(v2);
                    ++v2;
                }
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Adding back stack index " + v + " with " + backStackRecord0);
                }
                this.mBackStackIndices.add(backStackRecord0);
            }
        }
    }

    private static void setHWLayerAnimListenerIfAlpha(View view0, AnimationOrAnimator fragmentManagerImpl$AnimationOrAnimator0) {
        if(view0 != null && fragmentManagerImpl$AnimationOrAnimator0 != null && FragmentManagerImpl.shouldRunOnHWLayer(view0, fragmentManagerImpl$AnimationOrAnimator0)) {
            if(fragmentManagerImpl$AnimationOrAnimator0.animator != null) {
                AnimatorOnHWLayerIfNeededListener fragmentManagerImpl$AnimatorOnHWLayerIfNeededListener0 = new AnimatorOnHWLayerIfNeededListener(view0);
                fragmentManagerImpl$AnimationOrAnimator0.animator.addListener(fragmentManagerImpl$AnimatorOnHWLayerIfNeededListener0);
                return;
            }
            Animation.AnimationListener animation$AnimationListener0 = FragmentManagerImpl.getAnimationListener(fragmentManagerImpl$AnimationOrAnimator0.animation);
            view0.setLayerType(2, null);
            AnimateOnHWLayerIfNeededListener fragmentManagerImpl$AnimateOnHWLayerIfNeededListener0 = new AnimateOnHWLayerIfNeededListener(view0, animation$AnimationListener0);
            fragmentManagerImpl$AnimationOrAnimator0.animation.setAnimationListener(fragmentManagerImpl$AnimateOnHWLayerIfNeededListener0);
        }
    }

    public void setPrimaryNavigationFragment(Fragment fragment0) {
        if(fragment0 != null && (this.mActive.get(fragment0.mIndex) != fragment0 || fragment0.mHost != null && fragment0.getFragmentManager() != this)) {
            throw new IllegalArgumentException("Fragment " + fragment0 + " is not an active fragment of FragmentManager " + this);
        }
        this.mPrimaryNav = fragment0;
    }

    private static void setRetaining(FragmentManagerNonConfig fragmentManagerNonConfig0) {
        if(fragmentManagerNonConfig0 == null) {
            return;
        }
        List list0 = fragmentManagerNonConfig0.getFragments();
        if(list0 != null) {
            for(Object object0: list0) {
                ((Fragment)object0).mRetaining = true;
            }
        }
        List list1 = fragmentManagerNonConfig0.getChildNonConfigs();
        if(list1 != null) {
            for(Object object1: list1) {
                FragmentManagerImpl.setRetaining(((FragmentManagerNonConfig)object1));
            }
        }
    }

    // 去混淆评级： 低(20)
    static boolean shouldRunOnHWLayer(View view0, AnimationOrAnimator fragmentManagerImpl$AnimationOrAnimator0) {
        return view0 != null && fragmentManagerImpl$AnimationOrAnimator0 != null && (Build.VERSION.SDK_INT >= 19 && view0.getLayerType() == 0 && ViewCompat.hasOverlappingRendering(view0) && FragmentManagerImpl.modifiesAlpha(fragmentManagerImpl$AnimationOrAnimator0));
    }

    public void showFragment(Fragment fragment0) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "show: " + fragment0);
        }
        if(fragment0.mHidden) {
            fragment0.mHidden = false;
            fragment0.mHiddenChanged = !fragment0.mHiddenChanged;
        }
    }

    void startPendingDeferredFragments() {
        if(this.mActive == null) {
            return;
        }
        for(int v = 0; v < this.mActive.size(); ++v) {
            Fragment fragment0 = (Fragment)this.mActive.valueAt(v);
            if(fragment0 != null) {
                this.performPendingDeferredStart(fragment0);
            }
        }
    }

    private void throwException(RuntimeException runtimeException0) {
        Log.e("FragmentManager", runtimeException0.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter0 = new PrintWriter(new LogWriter("FragmentManager"));
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 == null) {
            try {
                this.dump("  ", null, printWriter0, new String[0]);
            }
            catch(Exception exception1) {
                Log.e("FragmentManager", "Failed dumping state", exception1);
            }
        }
        else {
            try {
                fragmentHostCallback0.onDump("  ", null, printWriter0, new String[0]);
            }
            catch(Exception exception0) {
                Log.e("FragmentManager", "Failed dumping state", exception0);
            }
        }
        throw runtimeException0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(0x80);
        stringBuilder0.append("FragmentManager{");
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder0.append(" in ");
        Fragment fragment0 = this.mParent;
        if(fragment0 == null) {
            DebugUtils.buildShortClassTag(this.mHost, stringBuilder0);
        }
        else {
            DebugUtils.buildShortClassTag(fragment0, stringBuilder0);
        }
        stringBuilder0.append("}}");
        return stringBuilder0.toString();
    }

    public static int transitToStyleIndex(int v, boolean z) {
        switch(v) {
            case 0x1001: {
                return z ? 1 : 2;
            }
            case 0x1003: {
                return z ? 5 : 6;
            }
            case 0x2002: {
                return z ? 3 : 4;
            }
            default: {
                return -1;
            }
        }
    }

    @Override  // androidx.fragment.app.FragmentManager
    public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentManager$FragmentLifecycleCallbacks0) {
        synchronized(this.mLifecycleCallbacks) {
            int v2 = this.mLifecycleCallbacks.size();
            for(int v1 = 0; v1 < v2; ++v1) {
                if(((FragmentLifecycleCallbacksHolder)this.mLifecycleCallbacks.get(v1)).mCallback == fragmentManager$FragmentLifecycleCallbacks0) {
                    this.mLifecycleCallbacks.remove(v1);
                    break;
                }
            }
        }
    }

    class androidx.fragment.app.FragmentManagerImpl.1 implements Runnable {
        @Override
        public void run() {
            FragmentManagerImpl.this.execPendingActions();
        }
    }

}

