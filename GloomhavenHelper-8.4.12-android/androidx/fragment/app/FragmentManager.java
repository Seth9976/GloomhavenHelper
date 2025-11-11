package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public abstract class FragmentManager {
    public interface BackStackEntry {
        @Nullable
        CharSequence getBreadCrumbShortTitle();

        @StringRes
        int getBreadCrumbShortTitleRes();

        @Nullable
        CharSequence getBreadCrumbTitle();

        @StringRes
        int getBreadCrumbTitleRes();

        int getId();

        @Nullable
        String getName();
    }

    public static abstract class FragmentLifecycleCallbacks {
        public void onFragmentActivityCreated(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0, @Nullable Bundle bundle0) {
        }

        public void onFragmentAttached(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0, @NonNull Context context0) {
        }

        public void onFragmentCreated(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0, @Nullable Bundle bundle0) {
        }

        public void onFragmentDestroyed(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0) {
        }

        public void onFragmentDetached(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0) {
        }

        public void onFragmentPaused(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0) {
        }

        public void onFragmentPreAttached(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0, @NonNull Context context0) {
        }

        public void onFragmentPreCreated(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0, @Nullable Bundle bundle0) {
        }

        public void onFragmentResumed(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0) {
        }

        public void onFragmentSaveInstanceState(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0, @NonNull Bundle bundle0) {
        }

        public void onFragmentStarted(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0) {
        }

        public void onFragmentStopped(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0) {
        }

        public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0, @NonNull View view0, @Nullable Bundle bundle0) {
        }

        public void onFragmentViewDestroyed(@NonNull FragmentManager fragmentManager0, @NonNull Fragment fragment0) {
        }
    }

    public interface OnBackStackChangedListener {
        void onBackStackChanged();
    }

    public static final int POP_BACK_STACK_INCLUSIVE = 1;

    public abstract void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener arg1);

    @NonNull
    public abstract FragmentTransaction beginTransaction();

    public abstract void dump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4);

    public static void enableDebugLogging(boolean z) {
        FragmentManagerImpl.DEBUG = z;
    }

    public abstract boolean executePendingTransactions();

    @Nullable
    public abstract Fragment findFragmentById(@IdRes int arg1);

    @Nullable
    public abstract Fragment findFragmentByTag(@Nullable String arg1);

    @NonNull
    public abstract BackStackEntry getBackStackEntryAt(int arg1);

    public abstract int getBackStackEntryCount();

    @Nullable
    public abstract Fragment getFragment(@NonNull Bundle arg1, @NonNull String arg2);

    @NonNull
    public abstract List getFragments();

    @Nullable
    public abstract Fragment getPrimaryNavigationFragment();

    public abstract boolean isDestroyed();

    public abstract boolean isStateSaved();

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Deprecated
    public FragmentTransaction openTransaction() {
        return this.beginTransaction();
    }

    public abstract void popBackStack();

    public abstract void popBackStack(int arg1, int arg2);

    public abstract void popBackStack(@Nullable String arg1, int arg2);

    public abstract boolean popBackStackImmediate();

    public abstract boolean popBackStackImmediate(int arg1, int arg2);

    public abstract boolean popBackStackImmediate(@Nullable String arg1, int arg2);

    public abstract void putFragment(@NonNull Bundle arg1, @NonNull String arg2, @NonNull Fragment arg3);

    public abstract void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks arg1, boolean arg2);

    public abstract void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener arg1);

    @Nullable
    public abstract SavedState saveFragmentInstanceState(Fragment arg1);

    public abstract void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks arg1);
}

