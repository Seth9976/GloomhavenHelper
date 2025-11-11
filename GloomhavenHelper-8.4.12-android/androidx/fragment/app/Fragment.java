package androidx.fragment.app;

import android.animation.Animator;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnCreateContextMenuListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.LayoutInflaterCompat;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, LifecycleOwner, ViewModelStoreOwner {
    static class AnimationInfo {
        Boolean mAllowEnterTransitionOverlap;
        Boolean mAllowReturnTransitionOverlap;
        View mAnimatingAway;
        Animator mAnimator;
        Object mEnterTransition;
        SharedElementCallback mEnterTransitionCallback;
        boolean mEnterTransitionPostponed;
        Object mExitTransition;
        SharedElementCallback mExitTransitionCallback;
        boolean mIsHideReplaced;
        int mNextAnim;
        int mNextTransition;
        int mNextTransitionStyle;
        Object mReenterTransition;
        Object mReturnTransition;
        Object mSharedElementEnterTransition;
        Object mSharedElementReturnTransition;
        OnStartEnterTransitionListener mStartEnterTransitionListener;
        int mStateAfterAnimating;

        AnimationInfo() {
            this.mEnterTransition = null;
            this.mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
            this.mExitTransition = null;
            this.mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
            this.mSharedElementEnterTransition = null;
            this.mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
            this.mEnterTransitionCallback = null;
            this.mExitTransitionCallback = null;
        }
    }

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String s, Exception exception0) {
            super(s, exception0);
        }
    }

    interface OnStartEnterTransitionListener {
        void onStartEnterTransition();

        void startListening();
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR;
        final Bundle mState;

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

        SavedState(Bundle bundle0) {
            this.mState = bundle0;
        }

        SavedState(Parcel parcel0, ClassLoader classLoader0) {
            this.mState = parcel0.readBundle();
            if(classLoader0 != null) {
                Bundle bundle0 = this.mState;
                if(bundle0 != null) {
                    bundle0.setClassLoader(classLoader0);
                }
            }
        }

        @Override  // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            parcel0.writeBundle(this.mState);
        }
    }

    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 4;
    static final int STARTED = 3;
    static final Object USE_DEFAULT_TRANSITION;
    boolean mAdded;
    AnimationInfo mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    FragmentManagerImpl mChildFragmentManager;
    FragmentManagerNonConfig mChildNonConfig;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    FragmentManagerImpl mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    FragmentHostCallback mHost;
    boolean mInLayout;
    int mIndex;
    View mInnerView;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    LifecycleRegistry mLifecycleRegistry;
    boolean mMenuVisible;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    @Nullable
    Boolean mSavedUserVisibleHint;
    SparseArray mSavedViewState;
    int mState;
    String mTag;
    Fragment mTarget;
    int mTargetIndex;
    int mTargetRequestCode;
    boolean mUserVisibleHint;
    View mView;
    LifecycleOwner mViewLifecycleOwner;
    MutableLiveData mViewLifecycleOwnerLiveData;
    LifecycleRegistry mViewLifecycleRegistry;
    ViewModelStore mViewModelStore;
    String mWho;
    private static final SimpleArrayMap sClassMap;

    static {
        Fragment.sClassMap = new SimpleArrayMap();
        Fragment.USE_DEFAULT_TRANSITION = new Object();
    }

    public Fragment() {
        this.mState = 0;
        this.mIndex = -1;
        this.mTargetIndex = -1;
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mViewLifecycleOwnerLiveData = new MutableLiveData();
    }

    // 检测为 Lambda 实现
    void callStartTransitionListener() [...]

    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        printWriter0.print(s);
        printWriter0.print("mFragmentId=#");
        printWriter0.print(Integer.toHexString(this.mFragmentId));
        printWriter0.print(" mContainerId=#");
        printWriter0.print(Integer.toHexString(this.mContainerId));
        printWriter0.print(" mTag=");
        printWriter0.println(this.mTag);
        printWriter0.print(s);
        printWriter0.print("mState=");
        printWriter0.print(this.mState);
        printWriter0.print(" mIndex=");
        printWriter0.print(this.mIndex);
        printWriter0.print(" mWho=");
        printWriter0.print(this.mWho);
        printWriter0.print(" mBackStackNesting=");
        printWriter0.println(this.mBackStackNesting);
        printWriter0.print(s);
        printWriter0.print("mAdded=");
        printWriter0.print(this.mAdded);
        printWriter0.print(" mRemoving=");
        printWriter0.print(this.mRemoving);
        printWriter0.print(" mFromLayout=");
        printWriter0.print(this.mFromLayout);
        printWriter0.print(" mInLayout=");
        printWriter0.println(this.mInLayout);
        printWriter0.print(s);
        printWriter0.print("mHidden=");
        printWriter0.print(this.mHidden);
        printWriter0.print(" mDetached=");
        printWriter0.print(this.mDetached);
        printWriter0.print(" mMenuVisible=");
        printWriter0.print(this.mMenuVisible);
        printWriter0.print(" mHasMenu=");
        printWriter0.println(this.mHasMenu);
        printWriter0.print(s);
        printWriter0.print("mRetainInstance=");
        printWriter0.print(this.mRetainInstance);
        printWriter0.print(" mRetaining=");
        printWriter0.print(this.mRetaining);
        printWriter0.print(" mUserVisibleHint=");
        printWriter0.println(this.mUserVisibleHint);
        if(this.mFragmentManager != null) {
            printWriter0.print(s);
            printWriter0.print("mFragmentManager=");
            printWriter0.println(this.mFragmentManager);
        }
        if(this.mHost != null) {
            printWriter0.print(s);
            printWriter0.print("mHost=");
            printWriter0.println(this.mHost);
        }
        if(this.mParentFragment != null) {
            printWriter0.print(s);
            printWriter0.print("mParentFragment=");
            printWriter0.println(this.mParentFragment);
        }
        if(this.mArguments != null) {
            printWriter0.print(s);
            printWriter0.print("mArguments=");
            printWriter0.println(this.mArguments);
        }
        if(this.mSavedFragmentState != null) {
            printWriter0.print(s);
            printWriter0.print("mSavedFragmentState=");
            printWriter0.println(this.mSavedFragmentState);
        }
        if(this.mSavedViewState != null) {
            printWriter0.print(s);
            printWriter0.print("mSavedViewState=");
            printWriter0.println(this.mSavedViewState);
        }
        if(this.mTarget != null) {
            printWriter0.print(s);
            printWriter0.print("mTarget=");
            printWriter0.print(this.mTarget);
            printWriter0.print(" mTargetRequestCode=");
            printWriter0.println(this.mTargetRequestCode);
        }
        if(this.getNextAnim() != 0) {
            printWriter0.print(s);
            printWriter0.print("mNextAnim=");
            printWriter0.println(this.getNextAnim());
        }
        if(this.mContainer != null) {
            printWriter0.print(s);
            printWriter0.print("mContainer=");
            printWriter0.println(this.mContainer);
        }
        if(this.mView != null) {
            printWriter0.print(s);
            printWriter0.print("mView=");
            printWriter0.println(this.mView);
        }
        if(this.mInnerView != null) {
            printWriter0.print(s);
            printWriter0.print("mInnerView=");
            printWriter0.println(this.mView);
        }
        if(this.getAnimatingAway() != null) {
            printWriter0.print(s);
            printWriter0.print("mAnimatingAway=");
            printWriter0.println(this.getAnimatingAway());
            printWriter0.print(s);
            printWriter0.print("mStateAfterAnimating=");
            printWriter0.println(this.getStateAfterAnimating());
        }
        if(this.getContext() != null) {
            LoaderManager.getInstance(this).dump(s, fileDescriptor0, printWriter0, arr_s);
        }
        if(this.mChildFragmentManager != null) {
            printWriter0.print(s);
            printWriter0.println("Child " + this.mChildFragmentManager + ":");
            this.mChildFragmentManager.dump(s + "  ", fileDescriptor0, printWriter0, arr_s);
        }
    }

    private AnimationInfo ensureAnimationInfo() {
        if(this.mAnimationInfo == null) {
            this.mAnimationInfo = new AnimationInfo();
        }
        return this.mAnimationInfo;
    }

    @Override
    public final boolean equals(Object object0) {
        return super.equals(object0);
    }

    Fragment findFragmentByWho(String s) {
        if(s.equals(this.mWho)) {
            return this;
        }
        return this.mChildFragmentManager == null ? null : this.mChildFragmentManager.findFragmentByWho(s);
    }

    @Nullable
    public final FragmentActivity getActivity() {
        return this.mHost == null ? null : ((FragmentActivity)this.mHost.getActivity());
    }

    public boolean getAllowEnterTransitionOverlap() {
        return this.mAnimationInfo == null || this.mAnimationInfo.mAllowEnterTransitionOverlap == null ? true : this.mAnimationInfo.mAllowEnterTransitionOverlap.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        return this.mAnimationInfo == null || this.mAnimationInfo.mAllowReturnTransitionOverlap == null ? true : this.mAnimationInfo.mAllowReturnTransitionOverlap.booleanValue();
    }

    View getAnimatingAway() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mAnimatingAway;
    }

    Animator getAnimator() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mAnimator;
    }

    @Nullable
    public final Bundle getArguments() {
        return this.mArguments;
    }

    @NonNull
    public final FragmentManager getChildFragmentManager() {
        if(this.mChildFragmentManager == null) {
            this.instantiateChildFragmentManager();
            int v = this.mState;
            if(v >= 4) {
                this.mChildFragmentManager.dispatchResume();
                return this.mChildFragmentManager;
            }
            if(v >= 3) {
                this.mChildFragmentManager.dispatchStart();
                return this.mChildFragmentManager;
            }
            if(v >= 2) {
                this.mChildFragmentManager.dispatchActivityCreated();
                return this.mChildFragmentManager;
            }
            if(v >= 1) {
                this.mChildFragmentManager.dispatchCreate();
            }
        }
        return this.mChildFragmentManager;
    }

    @Nullable
    public Context getContext() {
        return this.mHost == null ? null : this.mHost.getContext();
    }

    @Nullable
    public Object getEnterTransition() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mEnterTransition;
    }

    SharedElementCallback getEnterTransitionCallback() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mEnterTransitionCallback;
    }

    @Nullable
    public Object getExitTransition() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mExitTransition;
    }

    SharedElementCallback getExitTransitionCallback() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mExitTransitionCallback;
    }

    @Nullable
    public final FragmentManager getFragmentManager() {
        return this.mFragmentManager;
    }

    @Nullable
    public final Object getHost() {
        return this.mHost == null ? null : this.mHost.onGetHost();
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        return this.mLayoutInflater == null ? this.performGetLayoutInflater(null) : this.mLayoutInflater;
    }

    @NonNull
    @RestrictTo({Scope.LIBRARY_GROUP})
    @Deprecated
    public LayoutInflater getLayoutInflater(@Nullable Bundle bundle0) {
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater layoutInflater0 = fragmentHostCallback0.onGetLayoutInflater();
        this.getChildFragmentManager();
        LayoutInflaterCompat.setFactory2(layoutInflater0, this.mChildFragmentManager);
        return layoutInflater0;
    }

    @Override  // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public LoaderManager getLoaderManager() {
        return LoaderManager.getInstance(this);
    }

    int getNextAnim() {
        return this.mAnimationInfo == null ? 0 : this.mAnimationInfo.mNextAnim;
    }

    int getNextTransition() {
        return this.mAnimationInfo == null ? 0 : this.mAnimationInfo.mNextTransition;
    }

    int getNextTransitionStyle() {
        return this.mAnimationInfo == null ? 0 : this.mAnimationInfo.mNextTransitionStyle;
    }

    @Nullable
    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public Object getReenterTransition() {
        AnimationInfo fragment$AnimationInfo0 = this.mAnimationInfo;
        if(fragment$AnimationInfo0 == null) {
            return null;
        }
        return fragment$AnimationInfo0.mReenterTransition == Fragment.USE_DEFAULT_TRANSITION ? this.getExitTransition() : this.mAnimationInfo.mReenterTransition;
    }

    @NonNull
    public final Resources getResources() {
        return this.requireContext().getResources();
    }

    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    @Nullable
    public Object getReturnTransition() {
        AnimationInfo fragment$AnimationInfo0 = this.mAnimationInfo;
        if(fragment$AnimationInfo0 == null) {
            return null;
        }
        return fragment$AnimationInfo0.mReturnTransition == Fragment.USE_DEFAULT_TRANSITION ? this.getEnterTransition() : this.mAnimationInfo.mReturnTransition;
    }

    @Nullable
    public Object getSharedElementEnterTransition() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mSharedElementEnterTransition;
    }

    @Nullable
    public Object getSharedElementReturnTransition() {
        AnimationInfo fragment$AnimationInfo0 = this.mAnimationInfo;
        if(fragment$AnimationInfo0 == null) {
            return null;
        }
        return fragment$AnimationInfo0.mSharedElementReturnTransition == Fragment.USE_DEFAULT_TRANSITION ? this.getSharedElementEnterTransition() : this.mAnimationInfo.mSharedElementReturnTransition;
    }

    int getStateAfterAnimating() {
        return this.mAnimationInfo == null ? 0 : this.mAnimationInfo.mStateAfterAnimating;
    }

    @NonNull
    public final String getString(@StringRes int v) {
        return this.getResources().getString(v);
    }

    @NonNull
    public final String getString(@StringRes int v, Object[] arr_object) {
        return this.getResources().getString(v, arr_object);
    }

    @Nullable
    public final String getTag() {
        return this.mTag;
    }

    @Nullable
    public final Fragment getTargetFragment() {
        return this.mTarget;
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    @NonNull
    public final CharSequence getText(@StringRes int v) {
        return this.getResources().getText(v);
    }

    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    @Nullable
    public View getView() {
        return this.mView;
    }

    @MainThread
    @NonNull
    public LifecycleOwner getViewLifecycleOwner() {
        LifecycleOwner lifecycleOwner0 = this.mViewLifecycleOwner;
        if(lifecycleOwner0 == null) {
            throw new IllegalStateException("Can\'t access the Fragment View\'s LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
        }
        return lifecycleOwner0;
    }

    @NonNull
    public LiveData getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override  // androidx.lifecycle.ViewModelStoreOwner
    @NonNull
    public ViewModelStore getViewModelStore() {
        if(this.getContext() == null) {
            throw new IllegalStateException("Can\'t access ViewModels from detached fragment");
        }
        if(this.mViewModelStore == null) {
            this.mViewModelStore = new ViewModelStore();
        }
        return this.mViewModelStore;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    void initState() {
        this.mIndex = -1;
        this.mWho = null;
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = null;
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
        this.mRetaining = false;
    }

    public static Fragment instantiate(Context context0, String s) {
        return Fragment.instantiate(context0, s, null);
    }

    public static Fragment instantiate(Context context0, String s, @Nullable Bundle bundle0) {
        try {
            Class class0 = (Class)Fragment.sClassMap.get(s);
            if(class0 == null) {
                class0 = context0.getClassLoader().loadClass(s);
                Fragment.sClassMap.put(s, class0);
            }
            Fragment fragment0 = (Fragment)class0.getConstructor().newInstance();
            if(bundle0 != null) {
                bundle0.setClassLoader(fragment0.getClass().getClassLoader());
                fragment0.setArguments(bundle0);
            }
            return fragment0;
        }
        catch(ClassNotFoundException classNotFoundException0) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an" + " empty constructor that is public", classNotFoundException0);
        }
        catch(java.lang.InstantiationException instantiationException0) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an" + " empty constructor that is public", instantiationException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an" + " empty constructor that is public", illegalAccessException0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": could not find Fragment constructor", noSuchMethodException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": calling Fragment constructor caused an exception", invocationTargetException0);
        }
    }

    void instantiateChildFragmentManager() {
        if(this.mHost == null) {
            throw new IllegalStateException("Fragment has not been attached yet.");
        }
        this.mChildFragmentManager = new FragmentManagerImpl();
        this.mChildFragmentManager.attachController(this.mHost, new FragmentContainer() {
            @Override  // androidx.fragment.app.FragmentContainer
            public Fragment instantiate(Context context0, String s, Bundle bundle0) {
                return Fragment.this.mHost.instantiate(context0, s, bundle0);
            }

            @Override  // androidx.fragment.app.FragmentContainer
            @Nullable
            public View onFindViewById(int v) {
                if(Fragment.this.mView == null) {
                    throw new IllegalStateException("Fragment does not have a view");
                }
                return Fragment.this.mView.findViewById(v);
            }

            @Override  // androidx.fragment.app.FragmentContainer
            public boolean onHasView() {
                return Fragment.this.mView != null;
            }
        }, this);
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    boolean isHideReplaced() {
        return this.mAnimationInfo == null ? false : this.mAnimationInfo.mIsHideReplaced;
    }

    final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    boolean isPostponed() {
        return this.mAnimationInfo == null ? false : this.mAnimationInfo.mEnterTransitionPostponed;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        return this.mState >= 4;
    }

    public final boolean isStateSaved() {
        return this.mFragmentManager == null ? false : this.mFragmentManager.isStateSaved();
    }

    static boolean isSupportFragmentClass(Context context0, String s) {
        try {
            Class class0 = (Class)Fragment.sClassMap.get(s);
            if(class0 == null) {
                class0 = context0.getClassLoader().loadClass(s);
                Fragment.sClassMap.put(s, class0);
            }
            return Fragment.class.isAssignableFrom(class0);
        }
        catch(ClassNotFoundException unused_ex) {
            return false;
        }
    }

    // 去混淆评级： 低(20)
    public final boolean isVisible() {
        return this.isAdded() && !this.isHidden() && (this.mView != null && this.mView.getWindowToken() != null && this.mView.getVisibility() == 0);
    }

    void noteStateNotSaved() {
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.noteStateNotSaved();
        }
    }

    @CallSuper
    public void onActivityCreated(@Nullable Bundle bundle0) {
        this.mCalled = true;
    }

    public void onActivityResult(int v, int v1, Intent intent0) {
    }

    @CallSuper
    @Deprecated
    public void onAttach(Activity activity0) {
        this.mCalled = true;
    }

    @CallSuper
    public void onAttach(Context context0) {
        this.mCalled = true;
        Activity activity0 = this.mHost == null ? null : this.mHost.getActivity();
        if(activity0 != null) {
            this.mCalled = false;
            this.onAttach(activity0);
        }
    }

    public void onAttachFragment(Fragment fragment0) {
    }

    @Override  // android.content.ComponentCallbacks
    @CallSuper
    public void onConfigurationChanged(Configuration configuration0) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem0) [...] // Inlined contents

    @CallSuper
    public void onCreate(@Nullable Bundle bundle0) {
        this.mCalled = true;
        this.restoreChildFragmentState(bundle0);
        if(this.mChildFragmentManager != null && !this.mChildFragmentManager.isStateAtLeast(1)) {
            this.mChildFragmentManager.dispatchCreate();
        }
    }

    public Animation onCreateAnimation(int v, boolean z, int v1) [...] // Inlined contents

    public Animator onCreateAnimator(int v, boolean z, int v1) [...] // Inlined contents

    @Override  // android.view.View$OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu0, View view0, ContextMenu.ContextMenuInfo contextMenu$ContextMenuInfo0) {
        this.getActivity().onCreateContextMenu(contextMenu0, view0, contextMenu$ContextMenuInfo0);
    }

    public void onCreateOptionsMenu(Menu menu0, MenuInflater menuInflater0) {
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater0, @Nullable ViewGroup viewGroup0, @Nullable Bundle bundle0) {
        return null;
    }

    @CallSuper
    public void onDestroy() {
        boolean z = true;
        this.mCalled = true;
        FragmentActivity fragmentActivity0 = this.getActivity();
        if(fragmentActivity0 == null || !fragmentActivity0.isChangingConfigurations()) {
            z = false;
        }
        ViewModelStore viewModelStore0 = this.mViewModelStore;
        if(viewModelStore0 != null && !z) {
            viewModelStore0.clear();
        }
    }

    public void onDestroyOptionsMenu() {
    }

    @CallSuper
    public void onDestroyView() {
        this.mCalled = true;
    }

    @CallSuper
    public void onDetach() {
        this.mCalled = true;
    }

    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle bundle0) {
        return this.getLayoutInflater(bundle0);
    }

    public void onHiddenChanged(boolean z) {
    }

    @CallSuper
    @Deprecated
    public void onInflate(Activity activity0, AttributeSet attributeSet0, Bundle bundle0) {
        this.mCalled = true;
    }

    @CallSuper
    public void onInflate(Context context0, AttributeSet attributeSet0, Bundle bundle0) {
        this.mCalled = true;
        Activity activity0 = this.mHost == null ? null : this.mHost.getActivity();
        if(activity0 != null) {
            this.mCalled = false;
            this.onInflate(activity0, attributeSet0, bundle0);
        }
    }

    @Override  // android.content.ComponentCallbacks
    @CallSuper
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem0) [...] // Inlined contents

    public void onOptionsMenuClosed(Menu menu0) {
    }

    @CallSuper
    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z) {
    }

    public void onPrepareOptionsMenu(Menu menu0) {
    }

    public void onRequestPermissionsResult(int v, @NonNull String[] arr_s, @NonNull int[] arr_v) {
    }

    @CallSuper
    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle0) {
    }

    @CallSuper
    public void onStart() {
        this.mCalled = true;
    }

    @CallSuper
    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(@NonNull View view0, @Nullable Bundle bundle0) {
    }

    @CallSuper
    public void onViewStateRestored(@Nullable Bundle bundle0) {
        this.mCalled = true;
    }

    @Nullable
    FragmentManager peekChildFragmentManager() {
        return this.mChildFragmentManager;
    }

    void performActivityCreated(Bundle bundle0) {
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.noteStateNotSaved();
        }
        this.mState = 2;
        this.mCalled = false;
        this.onActivityCreated(bundle0);
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
        }
        FragmentManagerImpl fragmentManagerImpl1 = this.mChildFragmentManager;
        if(fragmentManagerImpl1 != null) {
            fragmentManagerImpl1.dispatchActivityCreated();
        }
    }

    void performConfigurationChanged(Configuration configuration0) {
        this.onConfigurationChanged(configuration0);
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.dispatchConfigurationChanged(configuration0);
        }
    }

    // 去混淆评级： 低(20)
    boolean performContextItemSelected(MenuItem menuItem0) {
        return !this.mHidden && (this.mChildFragmentManager != null && this.mChildFragmentManager.dispatchContextItemSelected(menuItem0));
    }

    void performCreate(Bundle bundle0) {
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.noteStateNotSaved();
        }
        this.mState = 1;
        this.mCalled = false;
        this.onCreate(bundle0);
        this.mIsCreated = true;
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_CREATE);
    }

    boolean performCreateOptionsMenu(Menu menu0, MenuInflater menuInflater0) {
        boolean z = false;
        if(!this.mHidden) {
            if(this.mHasMenu && this.mMenuVisible) {
                z = true;
            }
            FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
            if(fragmentManagerImpl0 != null) {
                return z | fragmentManagerImpl0.dispatchCreateOptionsMenu(menu0, menuInflater0);
            }
        }
        return z;
    }

    void performCreateView(@NonNull LayoutInflater layoutInflater0, @Nullable ViewGroup viewGroup0, @Nullable Bundle bundle0) {
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.noteStateNotSaved();
        }
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new LifecycleOwner() {
            @Override  // androidx.lifecycle.LifecycleOwner
            public Lifecycle getLifecycle() {
                if(Fragment.this.mViewLifecycleRegistry == null) {
                    Fragment.this.mViewLifecycleRegistry = new LifecycleRegistry(Fragment.this.mViewLifecycleOwner);
                }
                return Fragment.this.mViewLifecycleRegistry;
            }
        };
        this.mViewLifecycleRegistry = null;
        this.mView = this.onCreateView(layoutInflater0, viewGroup0, bundle0);
        if(this.mView != null) {
            this.mViewLifecycleOwner.getLifecycle();
            this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
            return;
        }
        if(this.mViewLifecycleRegistry != null) {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        }
        this.mViewLifecycleOwner = null;
    }

    void performDestroy() {
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_DESTROY);
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.dispatchDestroy();
        }
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        this.onDestroy();
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
        }
        this.mChildFragmentManager = null;
    }

    void performDestroyView() {
        if(this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Event.ON_DESTROY);
        }
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.dispatchDestroyView();
        }
        this.mState = 1;
        this.mCalled = false;
        this.onDestroyView();
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        }
        LoaderManager.getInstance(this).markForRedelivery();
        this.mPerformedCreateView = false;
    }

    void performDetach() {
        this.mCalled = false;
        this.onDetach();
        this.mLayoutInflater = null;
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDetach()");
        }
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            if(!this.mRetaining) {
                throw new IllegalStateException("Child FragmentManager of " + this + " was not " + " destroyed and this fragment is not retaining instance");
            }
            fragmentManagerImpl0.dispatchDestroy();
            this.mChildFragmentManager = null;
        }
    }

    @NonNull
    LayoutInflater performGetLayoutInflater(@Nullable Bundle bundle0) {
        this.mLayoutInflater = this.onGetLayoutInflater(bundle0);
        return this.mLayoutInflater;
    }

    void performLowMemory() {
        this.onLowMemory();
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.dispatchLowMemory();
        }
    }

    void performMultiWindowModeChanged(boolean z) {
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.dispatchMultiWindowModeChanged(z);
        }
    }

    // 去混淆评级： 低(20)
    boolean performOptionsItemSelected(MenuItem menuItem0) {
        return !this.mHidden && (this.mChildFragmentManager != null && this.mChildFragmentManager.dispatchOptionsItemSelected(menuItem0));
    }

    void performOptionsMenuClosed(Menu menu0) {
        if(!this.mHidden) {
            FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
            if(fragmentManagerImpl0 != null) {
                fragmentManagerImpl0.dispatchOptionsMenuClosed(menu0);
            }
        }
    }

    void performPause() {
        if(this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Event.ON_PAUSE);
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_PAUSE);
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.dispatchPause();
        }
        this.mState = 3;
        this.mCalled = false;
        this.onPause();
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
        }
    }

    void performPictureInPictureModeChanged(boolean z) {
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.dispatchPictureInPictureModeChanged(z);
        }
    }

    boolean performPrepareOptionsMenu(Menu menu0) {
        boolean z = false;
        if(!this.mHidden) {
            if(this.mHasMenu && this.mMenuVisible) {
                z = true;
            }
            FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
            if(fragmentManagerImpl0 != null) {
                return z | fragmentManagerImpl0.dispatchPrepareOptionsMenu(menu0);
            }
        }
        return z;
    }

    void performResume() {
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.noteStateNotSaved();
            this.mChildFragmentManager.execPendingActions();
        }
        this.mState = 4;
        this.mCalled = false;
        this.onResume();
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
        }
        FragmentManagerImpl fragmentManagerImpl1 = this.mChildFragmentManager;
        if(fragmentManagerImpl1 != null) {
            fragmentManagerImpl1.dispatchResume();
            this.mChildFragmentManager.execPendingActions();
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_RESUME);
        if(this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Event.ON_RESUME);
        }
    }

    void performSaveInstanceState(Bundle bundle0) {
        this.onSaveInstanceState(bundle0);
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            Parcelable parcelable0 = fragmentManagerImpl0.saveAllState();
            if(parcelable0 != null) {
                bundle0.putParcelable("android:support:fragments", parcelable0);
            }
        }
    }

    void performStart() {
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.noteStateNotSaved();
            this.mChildFragmentManager.execPendingActions();
        }
        this.mState = 3;
        this.mCalled = false;
        this.onStart();
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
        }
        FragmentManagerImpl fragmentManagerImpl1 = this.mChildFragmentManager;
        if(fragmentManagerImpl1 != null) {
            fragmentManagerImpl1.dispatchStart();
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_START);
        if(this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Event.ON_START);
        }
    }

    void performStop() {
        if(this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Event.ON_STOP);
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_STOP);
        FragmentManagerImpl fragmentManagerImpl0 = this.mChildFragmentManager;
        if(fragmentManagerImpl0 != null) {
            fragmentManagerImpl0.dispatchStop();
        }
        this.mState = 2;
        this.mCalled = false;
        this.onStop();
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
        }
    }

    public void postponeEnterTransition() {
        this.ensureAnimationInfo().mEnterTransitionPostponed = true;
    }

    public void registerForContextMenu(View view0) {
        view0.setOnCreateContextMenuListener(this);
    }

    public final void requestPermissions(@NonNull String[] arr_s, int v) {
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        fragmentHostCallback0.onRequestPermissionsFromFragment(this, arr_s, v);
    }

    @NonNull
    public final FragmentActivity requireActivity() {
        FragmentActivity fragmentActivity0 = this.getActivity();
        if(fragmentActivity0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
        }
        return fragmentActivity0;
    }

    @NonNull
    public final Context requireContext() {
        Context context0 = this.getContext();
        if(context0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to a context.");
        }
        return context0;
    }

    @NonNull
    public final FragmentManager requireFragmentManager() {
        FragmentManager fragmentManager0 = this.getFragmentManager();
        if(fragmentManager0 == null) {
            throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
        }
        return fragmentManager0;
    }

    @NonNull
    public final Object requireHost() {
        Object object0 = this.getHost();
        if(object0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to a host.");
        }
        return object0;
    }

    void restoreChildFragmentState(@Nullable Bundle bundle0) {
        if(bundle0 != null) {
            Parcelable parcelable0 = bundle0.getParcelable("android:support:fragments");
            if(parcelable0 != null) {
                if(this.mChildFragmentManager == null) {
                    this.instantiateChildFragmentManager();
                }
                this.mChildFragmentManager.restoreAllState(parcelable0, this.mChildNonConfig);
                this.mChildNonConfig = null;
                this.mChildFragmentManager.dispatchCreate();
            }
        }
    }

    final void restoreViewState(Bundle bundle0) {
        SparseArray sparseArray0 = this.mSavedViewState;
        if(sparseArray0 != null) {
            this.mInnerView.restoreHierarchyState(sparseArray0);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        this.onViewStateRestored(bundle0);
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
        if(this.mView != null) {
            this.mViewLifecycleRegistry.handleLifecycleEvent(Event.ON_CREATE);
        }
    }

    public void setAllowEnterTransitionOverlap(boolean z) {
        this.ensureAnimationInfo().mAllowEnterTransitionOverlap = Boolean.valueOf(z);
    }

    public void setAllowReturnTransitionOverlap(boolean z) {
        this.ensureAnimationInfo().mAllowReturnTransitionOverlap = Boolean.valueOf(z);
    }

    void setAnimatingAway(View view0) {
        this.ensureAnimationInfo().mAnimatingAway = view0;
    }

    void setAnimator(Animator animator0) {
        this.ensureAnimationInfo().mAnimator = animator0;
    }

    public void setArguments(@Nullable Bundle bundle0) {
        if(this.mIndex >= 0 && this.isStateSaved()) {
            throw new IllegalStateException("Fragment already active and state has been saved");
        }
        this.mArguments = bundle0;
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedElementCallback0) {
        this.ensureAnimationInfo().mEnterTransitionCallback = sharedElementCallback0;
    }

    public void setEnterTransition(@Nullable Object object0) {
        this.ensureAnimationInfo().mEnterTransition = object0;
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedElementCallback0) {
        this.ensureAnimationInfo().mExitTransitionCallback = sharedElementCallback0;
    }

    public void setExitTransition(@Nullable Object object0) {
        this.ensureAnimationInfo().mExitTransition = object0;
    }

    public void setHasOptionsMenu(boolean z) {
        if(this.mHasMenu != z) {
            this.mHasMenu = z;
            if(this.isAdded() && !this.isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    void setHideReplaced(boolean z) {
        this.ensureAnimationInfo().mIsHideReplaced = z;
    }

    final void setIndex(int v, Fragment fragment0) {
        this.mIndex = v;
        if(fragment0 != null) {
            this.mWho = fragment0.mWho + ":" + this.mIndex;
            return;
        }
        this.mWho = "android:fragment:" + this.mIndex;
    }

    public void setInitialSavedState(@Nullable SavedState fragment$SavedState0) {
        if(this.mIndex >= 0) {
            throw new IllegalStateException("Fragment already active");
        }
        this.mSavedFragmentState = fragment$SavedState0 == null || fragment$SavedState0.mState == null ? null : fragment$SavedState0.mState;
    }

    public void setMenuVisibility(boolean z) {
        if(this.mMenuVisible != z) {
            this.mMenuVisible = z;
            if(this.mHasMenu && this.isAdded() && !this.isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    void setNextAnim(int v) {
        if(this.mAnimationInfo == null && v == 0) {
            return;
        }
        this.ensureAnimationInfo().mNextAnim = v;
    }

    void setNextTransition(int v, int v1) {
        if(this.mAnimationInfo == null && v == 0 && v1 == 0) {
            return;
        }
        this.ensureAnimationInfo();
        this.mAnimationInfo.mNextTransition = v;
        this.mAnimationInfo.mNextTransitionStyle = v1;
    }

    void setOnStartEnterTransitionListener(OnStartEnterTransitionListener fragment$OnStartEnterTransitionListener0) {
        this.ensureAnimationInfo();
        if(fragment$OnStartEnterTransitionListener0 == this.mAnimationInfo.mStartEnterTransitionListener) {
            return;
        }
        if(fragment$OnStartEnterTransitionListener0 != null && this.mAnimationInfo.mStartEnterTransitionListener != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if(this.mAnimationInfo.mEnterTransitionPostponed) {
            this.mAnimationInfo.mStartEnterTransitionListener = fragment$OnStartEnterTransitionListener0;
        }
        if(fragment$OnStartEnterTransitionListener0 != null) {
            fragment$OnStartEnterTransitionListener0.startListening();
        }
    }

    public void setReenterTransition(@Nullable Object object0) {
        this.ensureAnimationInfo().mReenterTransition = object0;
    }

    public void setRetainInstance(boolean z) {
        this.mRetainInstance = z;
    }

    public void setReturnTransition(@Nullable Object object0) {
        this.ensureAnimationInfo().mReturnTransition = object0;
    }

    public void setSharedElementEnterTransition(@Nullable Object object0) {
        this.ensureAnimationInfo().mSharedElementEnterTransition = object0;
    }

    public void setSharedElementReturnTransition(@Nullable Object object0) {
        this.ensureAnimationInfo().mSharedElementReturnTransition = object0;
    }

    void setStateAfterAnimating(int v) {
        this.ensureAnimationInfo().mStateAfterAnimating = v;
    }

    public void setTargetFragment(@Nullable Fragment fragment0, int v) {
        FragmentManager fragmentManager0 = this.getFragmentManager();
        FragmentManager fragmentManager1 = fragment0 == null ? null : fragment0.getFragmentManager();
        if(fragmentManager0 != null && fragmentManager1 != null && fragmentManager0 != fragmentManager1) {
            throw new IllegalArgumentException("Fragment " + fragment0 + " must share the same FragmentManager to be set as a target fragment");
        }
        for(Fragment fragment1 = fragment0; fragment1 != null; fragment1 = fragment1.getTargetFragment()) {
            if(fragment1 == this) {
                throw new IllegalArgumentException("Setting " + fragment0 + " as the target of " + this + " would create a target cycle");
            }
        }
        this.mTarget = fragment0;
        this.mTargetRequestCode = v;
    }

    public void setUserVisibleHint(boolean z) {
        if(!this.mUserVisibleHint && z && this.mState < 3 && this.mFragmentManager != null && this.isAdded() && this.mIsCreated) {
            this.mFragmentManager.performPendingDeferredStart(this);
        }
        this.mUserVisibleHint = z;
        this.mDeferStart = this.mState < 3 && !z;
        if(this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z);
        }
    }

    public boolean shouldShowRequestPermissionRationale(@NonNull String s) {
        return this.mHost == null ? false : this.mHost.onShouldShowRequestPermissionRationale(s);
    }

    public void startActivity(Intent intent0) {
        this.startActivity(intent0, null);
    }

    public void startActivity(Intent intent0, @Nullable Bundle bundle0) {
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        fragmentHostCallback0.onStartActivityFromFragment(this, intent0, -1, bundle0);
    }

    public void startActivityForResult(Intent intent0, int v) {
        this.startActivityForResult(intent0, v, null);
    }

    public void startActivityForResult(Intent intent0, int v, @Nullable Bundle bundle0) {
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        fragmentHostCallback0.onStartActivityFromFragment(this, intent0, v, bundle0);
    }

    public void startIntentSenderForResult(IntentSender intentSender0, int v, @Nullable Intent intent0, int v1, int v2, int v3, Bundle bundle0) throws IntentSender.SendIntentException {
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        fragmentHostCallback0.onStartIntentSenderFromFragment(this, intentSender0, v, intent0, v1, v2, v3, bundle0);
    }

    public void startPostponedEnterTransition() {
        if(this.mFragmentManager != null && this.mFragmentManager.mHost != null) {
            if(Looper.myLooper() != this.mFragmentManager.mHost.getHandler().getLooper()) {
                this.mFragmentManager.mHost.getHandler().postAtFrontOfQueue(() -> {
                    OnStartEnterTransitionListener fragment$OnStartEnterTransitionListener0;
                    AnimationInfo fragment$AnimationInfo0 = Fragment.this.mAnimationInfo;
                    if(fragment$AnimationInfo0 == null) {
                        fragment$OnStartEnterTransitionListener0 = null;
                    }
                    else {
                        fragment$AnimationInfo0.mEnterTransitionPostponed = false;
                        fragment$OnStartEnterTransitionListener0 = fragment$AnimationInfo0.mStartEnterTransitionListener;
                        Fragment.this.mAnimationInfo.mStartEnterTransitionListener = null;
                    }
                    if(fragment$OnStartEnterTransitionListener0 != null) {
                        fragment$OnStartEnterTransitionListener0.onStartEnterTransition();
                    }
                });
                return;
            }
            this.callStartTransitionListener();
            return;
        }
        this.ensureAnimationInfo().mEnterTransitionPostponed = false;

        class androidx.fragment.app.Fragment.1 implements Runnable {
            @Override
            public void run() {
                Fragment.this.callStartTransitionListener();
            }
        }

    }

    @Override
    public String toString() [...] // 潜在的解密器

    public void unregisterForContextMenu(View view0) {
        view0.setOnCreateContextMenuListener(null);
    }
}

