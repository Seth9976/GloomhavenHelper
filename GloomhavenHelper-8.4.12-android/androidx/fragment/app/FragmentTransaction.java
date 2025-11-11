package androidx.fragment.app;

import android.view.View;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

public abstract class FragmentTransaction {
    public static final int TRANSIT_ENTER_MASK = 0x1000;
    public static final int TRANSIT_EXIT_MASK = 0x2000;
    public static final int TRANSIT_FRAGMENT_CLOSE = 0x2002;
    public static final int TRANSIT_FRAGMENT_FADE = 0x1003;
    public static final int TRANSIT_FRAGMENT_OPEN = 0x1001;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_UNSET = -1;

    @NonNull
    public abstract FragmentTransaction add(@IdRes int arg1, @NonNull Fragment arg2);

    @NonNull
    public abstract FragmentTransaction add(@IdRes int arg1, @NonNull Fragment arg2, @Nullable String arg3);

    @NonNull
    public abstract FragmentTransaction add(@NonNull Fragment arg1, @Nullable String arg2);

    @NonNull
    public abstract FragmentTransaction addSharedElement(@NonNull View arg1, @NonNull String arg2);

    @NonNull
    public abstract FragmentTransaction addToBackStack(@Nullable String arg1);

    @NonNull
    public abstract FragmentTransaction attach(@NonNull Fragment arg1);

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    @NonNull
    public abstract FragmentTransaction detach(@NonNull Fragment arg1);

    @NonNull
    public abstract FragmentTransaction disallowAddToBackStack();

    @NonNull
    public abstract FragmentTransaction hide(@NonNull Fragment arg1);

    public abstract boolean isAddToBackStackAllowed();

    public abstract boolean isEmpty();

    @NonNull
    public abstract FragmentTransaction remove(@NonNull Fragment arg1);

    @NonNull
    public abstract FragmentTransaction replace(@IdRes int arg1, @NonNull Fragment arg2);

    @NonNull
    public abstract FragmentTransaction replace(@IdRes int arg1, @NonNull Fragment arg2, @Nullable String arg3);

    @NonNull
    public abstract FragmentTransaction runOnCommit(@NonNull Runnable arg1);

    @Deprecated
    public abstract FragmentTransaction setAllowOptimization(boolean arg1);

    @NonNull
    public abstract FragmentTransaction setBreadCrumbShortTitle(@StringRes int arg1);

    @NonNull
    public abstract FragmentTransaction setBreadCrumbShortTitle(@Nullable CharSequence arg1);

    @NonNull
    public abstract FragmentTransaction setBreadCrumbTitle(@StringRes int arg1);

    @NonNull
    public abstract FragmentTransaction setBreadCrumbTitle(@Nullable CharSequence arg1);

    @NonNull
    public abstract FragmentTransaction setCustomAnimations(@AnimRes @AnimatorRes int arg1, @AnimRes @AnimatorRes int arg2);

    @NonNull
    public abstract FragmentTransaction setCustomAnimations(@AnimRes @AnimatorRes int arg1, @AnimRes @AnimatorRes int arg2, @AnimRes @AnimatorRes int arg3, @AnimRes @AnimatorRes int arg4);

    @NonNull
    public abstract FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment arg1);

    @NonNull
    public abstract FragmentTransaction setReorderingAllowed(boolean arg1);

    @NonNull
    public abstract FragmentTransaction setTransition(int arg1);

    @NonNull
    public abstract FragmentTransaction setTransitionStyle(@StyleRes int arg1);

    @NonNull
    public abstract FragmentTransaction show(@NonNull Fragment arg1);
}

