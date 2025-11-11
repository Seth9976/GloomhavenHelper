package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.util.LogWriter;
import androidx.core.view.ViewCompat;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements BackStackEntry, OpGenerator {
    static final class Op {
        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        int popEnterAnim;
        int popExitAnim;

        Op() {
        }

        Op(int v, Fragment fragment0) {
            this.cmd = v;
            this.fragment = fragment0;
        }
    }

    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SET_PRIMARY_NAV = 8;
    static final int OP_SHOW = 5;
    static final int OP_UNSET_PRIMARY_NAV = 9;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    ArrayList mCommitRunnables;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    int mIndex;
    final FragmentManagerImpl mManager;
    @Nullable
    String mName;
    ArrayList mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed;
    ArrayList mSharedElementSourceNames;
    ArrayList mSharedElementTargetNames;
    int mTransition;
    int mTransitionStyle;

    public BackStackRecord(FragmentManagerImpl fragmentManagerImpl0) {
        this.mOps = new ArrayList();
        this.mAllowAddToBackStack = true;
        this.mIndex = -1;
        this.mReorderingAllowed = false;
        this.mManager = fragmentManagerImpl0;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction add(int v, Fragment fragment0) {
        this.doAddOp(v, fragment0, null, 1);
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction add(int v, Fragment fragment0, @Nullable String s) {
        this.doAddOp(v, fragment0, s, 1);
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction add(Fragment fragment0, @Nullable String s) {
        this.doAddOp(0, fragment0, s, 1);
        return this;
    }

    void addOp(Op backStackRecord$Op0) {
        this.mOps.add(backStackRecord$Op0);
        backStackRecord$Op0.enterAnim = this.mEnterAnim;
        backStackRecord$Op0.exitAnim = this.mExitAnim;
        backStackRecord$Op0.popEnterAnim = this.mPopEnterAnim;
        backStackRecord$Op0.popExitAnim = this.mPopExitAnim;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction addSharedElement(View view0, String s) {
        String s1 = ViewCompat.getTransitionName(view0);
        if(s1 == null) {
            throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
        }
        if(this.mSharedElementSourceNames == null) {
            this.mSharedElementSourceNames = new ArrayList();
            this.mSharedElementTargetNames = new ArrayList();
        }
        else {
            if(this.mSharedElementTargetNames.contains(s)) {
                throw new IllegalArgumentException("A shared element with the target name \'" + s + "\' has already been added to the transaction.");
            }
            if(this.mSharedElementSourceNames.contains(s1)) {
                throw new IllegalArgumentException("A shared element with the source name \'" + s1 + " has already been added to the transaction.");
            }
        }
        this.mSharedElementSourceNames.add(s1);
        this.mSharedElementTargetNames.add(s);
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction addToBackStack(@Nullable String s) {
        if(!this.mAllowAddToBackStack) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.mAddToBackStack = true;
        this.mName = s;
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction attach(Fragment fragment0) {
        this.addOp(new Op(7, fragment0));
        return this;
    }

    void bumpBackStackNesting(int v) {
        if(!this.mAddToBackStack) {
            return;
        }
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Bump nesting in " + this + " by " + v);
        }
        int v1 = this.mOps.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            Op backStackRecord$Op0 = (Op)this.mOps.get(v2);
            if(backStackRecord$Op0.fragment != null) {
                backStackRecord$Op0.fragment.mBackStackNesting += v;
                if(FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Bump nesting of " + backStackRecord$Op0.fragment + " to " + backStackRecord$Op0.fragment.mBackStackNesting);
                }
            }
        }
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public int commit() {
        return this.commitInternal(false);
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public int commitAllowingStateLoss() {
        return this.commitInternal(true);
    }

    int commitInternal(boolean z) {
        if(this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter0 = new PrintWriter(new LogWriter("FragmentManager"));
            this.dump("  ", null, printWriter0, null);
            printWriter0.close();
        }
        this.mCommitted = true;
        this.mIndex = this.mAddToBackStack ? this.mManager.allocBackStackIndex(this) : -1;
        this.mManager.enqueueAction(this, z);
        return this.mIndex;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public void commitNow() {
        this.disallowAddToBackStack();
        this.mManager.execSingleAction(this, false);
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public void commitNowAllowingStateLoss() {
        this.disallowAddToBackStack();
        this.mManager.execSingleAction(this, true);
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction detach(Fragment fragment0) {
        this.addOp(new Op(6, fragment0));
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction disallowAddToBackStack() {
        if(this.mAddToBackStack) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.mAllowAddToBackStack = false;
        return this;
    }

    private void doAddOp(int v, Fragment fragment0, @Nullable String s, int v1) {
        Class class0 = fragment0.getClass();
        int v2 = class0.getModifiers();
        if(class0.isAnonymousClass() || !Modifier.isPublic(v2) || class0.isMemberClass() && !Modifier.isStatic(v2)) {
            throw new IllegalStateException("Fragment " + class0.getCanonicalName() + " must be a public static class to be  properly recreated from" + " instance state.");
        }
        fragment0.mFragmentManager = this.mManager;
        if(s != null) {
            if(fragment0.mTag != null && !s.equals(fragment0.mTag)) {
                throw new IllegalStateException("Can\'t change tag of fragment " + fragment0 + ": was " + fragment0.mTag + " now " + s);
            }
            fragment0.mTag = s;
        }
        switch(v) {
            case -1: {
                throw new IllegalArgumentException("Can\'t add fragment " + fragment0 + " with tag " + s + " to container view with no id");
            label_11:
                if(fragment0.mFragmentId != 0 && fragment0.mFragmentId != v) {
                    throw new IllegalStateException("Can\'t change container ID of fragment " + fragment0 + ": was " + fragment0.mFragmentId + " now " + v);
                }
                fragment0.mFragmentId = v;
                fragment0.mContainerId = v;
                break;
            }
            case 0: {
                break;
            }
            default: {
                goto label_11;
            }
        }
        this.addOp(new Op(v1, fragment0));
    }

    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        this.dump(s, printWriter0, true);
    }

    public void dump(String s, PrintWriter printWriter0, boolean z) {
        String s1;
        if(z) {
            printWriter0.print(s);
            printWriter0.print("mName=");
            printWriter0.print(this.mName);
            printWriter0.print(" mIndex=");
            printWriter0.print(this.mIndex);
            printWriter0.print(" mCommitted=");
            printWriter0.println(this.mCommitted);
            if(this.mTransition != 0) {
                printWriter0.print(s);
                printWriter0.print("mTransition=#");
                printWriter0.print(Integer.toHexString(this.mTransition));
                printWriter0.print(" mTransitionStyle=#");
                printWriter0.println(Integer.toHexString(this.mTransitionStyle));
            }
            if(this.mEnterAnim != 0 || this.mExitAnim != 0) {
                printWriter0.print(s);
                printWriter0.print("mEnterAnim=#");
                printWriter0.print(Integer.toHexString(this.mEnterAnim));
                printWriter0.print(" mExitAnim=#");
                printWriter0.println(Integer.toHexString(this.mExitAnim));
            }
            if(this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
                printWriter0.print(s);
                printWriter0.print("mPopEnterAnim=#");
                printWriter0.print(Integer.toHexString(this.mPopEnterAnim));
                printWriter0.print(" mPopExitAnim=#");
                printWriter0.println(Integer.toHexString(this.mPopExitAnim));
            }
            if(this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
                printWriter0.print(s);
                printWriter0.print("mBreadCrumbTitleRes=#");
                printWriter0.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                printWriter0.print(" mBreadCrumbTitleText=");
                printWriter0.println(this.mBreadCrumbTitleText);
            }
            if(this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
                printWriter0.print(s);
                printWriter0.print("mBreadCrumbShortTitleRes=#");
                printWriter0.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                printWriter0.print(" mBreadCrumbShortTitleText=");
                printWriter0.println(this.mBreadCrumbShortTitleText);
            }
        }
        if(!this.mOps.isEmpty()) {
            printWriter0.print(s);
            printWriter0.println("Operations:");
            stringBuilder0.toString();
            int v = this.mOps.size();
            for(int v1 = 0; v1 < v; ++v1) {
                Op backStackRecord$Op0 = (Op)this.mOps.get(v1);
                switch(backStackRecord$Op0.cmd) {
                    case 0: {
                        s1 = "NULL";
                        break;
                    }
                    case 1: {
                        s1 = "ADD";
                        break;
                    }
                    case 2: {
                        s1 = "REPLACE";
                        break;
                    }
                    case 3: {
                        s1 = "REMOVE";
                        break;
                    }
                    case 4: {
                        s1 = "HIDE";
                        break;
                    }
                    case 5: {
                        s1 = "SHOW";
                        break;
                    }
                    case 6: {
                        s1 = "DETACH";
                        break;
                    }
                    case 7: {
                        s1 = "ATTACH";
                        break;
                    }
                    case 8: {
                        s1 = "SET_PRIMARY_NAV";
                        break;
                    }
                    case 9: {
                        s1 = "UNSET_PRIMARY_NAV";
                        break;
                    }
                    default: {
                        s1 = "cmd=" + backStackRecord$Op0.cmd;
                    }
                }
                printWriter0.print(s);
                printWriter0.print("  Op #");
                printWriter0.print(v1);
                printWriter0.print(": ");
                printWriter0.print(s1);
                printWriter0.print(" ");
                printWriter0.println(backStackRecord$Op0.fragment);
                if(z) {
                    if(backStackRecord$Op0.enterAnim != 0 || backStackRecord$Op0.exitAnim != 0) {
                        printWriter0.print(s);
                        printWriter0.print("enterAnim=#");
                        printWriter0.print(Integer.toHexString(backStackRecord$Op0.enterAnim));
                        printWriter0.print(" exitAnim=#");
                        printWriter0.println(Integer.toHexString(backStackRecord$Op0.exitAnim));
                    }
                    if(backStackRecord$Op0.popEnterAnim != 0 || backStackRecord$Op0.popExitAnim != 0) {
                        printWriter0.print(s);
                        printWriter0.print("popEnterAnim=#");
                        printWriter0.print(Integer.toHexString(backStackRecord$Op0.popEnterAnim));
                        printWriter0.print(" popExitAnim=#");
                        printWriter0.println(Integer.toHexString(backStackRecord$Op0.popExitAnim));
                    }
                }
            }
        }
    }

    void executeOps() {
        int v = this.mOps.size();
        for(int v1 = 0; v1 < v; ++v1) {
            Op backStackRecord$Op0 = (Op)this.mOps.get(v1);
            Fragment fragment0 = backStackRecord$Op0.fragment;
            if(fragment0 != null) {
                fragment0.setNextTransition(this.mTransition, this.mTransitionStyle);
            }
            int v2 = backStackRecord$Op0.cmd;
            if(v2 == 1) {
                fragment0.setNextAnim(backStackRecord$Op0.enterAnim);
                this.mManager.addFragment(fragment0, false);
            }
            else {
                switch(v2) {
                    case 3: {
                        fragment0.setNextAnim(backStackRecord$Op0.exitAnim);
                        this.mManager.removeFragment(fragment0);
                        break;
                    }
                    case 4: {
                        fragment0.setNextAnim(backStackRecord$Op0.exitAnim);
                        this.mManager.hideFragment(fragment0);
                        break;
                    }
                    case 5: {
                        fragment0.setNextAnim(backStackRecord$Op0.enterAnim);
                        this.mManager.showFragment(fragment0);
                        break;
                    }
                    case 6: {
                        fragment0.setNextAnim(backStackRecord$Op0.exitAnim);
                        this.mManager.detachFragment(fragment0);
                        break;
                    }
                    case 7: {
                        fragment0.setNextAnim(backStackRecord$Op0.enterAnim);
                        this.mManager.attachFragment(fragment0);
                        break;
                    }
                    case 8: {
                        this.mManager.setPrimaryNavigationFragment(fragment0);
                        break;
                    }
                    case 9: {
                        this.mManager.setPrimaryNavigationFragment(null);
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Unknown cmd: " + backStackRecord$Op0.cmd);
                    }
                }
            }
            if(!this.mReorderingAllowed && backStackRecord$Op0.cmd != 1 && fragment0 != null) {
                this.mManager.moveFragmentToExpectedState(fragment0);
            }
        }
        if(!this.mReorderingAllowed) {
            this.mManager.moveToState(this.mManager.mCurState, true);
        }
    }

    void executePopOps(boolean z) {
        for(int v = this.mOps.size() - 1; v >= 0; --v) {
            Op backStackRecord$Op0 = (Op)this.mOps.get(v);
            Fragment fragment0 = backStackRecord$Op0.fragment;
            if(fragment0 != null) {
                fragment0.setNextTransition(FragmentManagerImpl.reverseTransit(this.mTransition), this.mTransitionStyle);
            }
            int v1 = backStackRecord$Op0.cmd;
            if(v1 == 1) {
                fragment0.setNextAnim(backStackRecord$Op0.popExitAnim);
                this.mManager.removeFragment(fragment0);
            }
            else {
                switch(v1) {
                    case 3: {
                        fragment0.setNextAnim(backStackRecord$Op0.popEnterAnim);
                        this.mManager.addFragment(fragment0, false);
                        break;
                    }
                    case 4: {
                        fragment0.setNextAnim(backStackRecord$Op0.popEnterAnim);
                        this.mManager.showFragment(fragment0);
                        break;
                    }
                    case 5: {
                        fragment0.setNextAnim(backStackRecord$Op0.popExitAnim);
                        this.mManager.hideFragment(fragment0);
                        break;
                    }
                    case 6: {
                        fragment0.setNextAnim(backStackRecord$Op0.popEnterAnim);
                        this.mManager.attachFragment(fragment0);
                        break;
                    }
                    case 7: {
                        fragment0.setNextAnim(backStackRecord$Op0.popExitAnim);
                        this.mManager.detachFragment(fragment0);
                        break;
                    }
                    case 8: {
                        this.mManager.setPrimaryNavigationFragment(null);
                        break;
                    }
                    case 9: {
                        this.mManager.setPrimaryNavigationFragment(fragment0);
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Unknown cmd: " + backStackRecord$Op0.cmd);
                    }
                }
            }
            if(!this.mReorderingAllowed && backStackRecord$Op0.cmd != 3 && fragment0 != null) {
                this.mManager.moveFragmentToExpectedState(fragment0);
            }
        }
        if(!this.mReorderingAllowed && z) {
            this.mManager.moveToState(this.mManager.mCurState, true);
        }
    }

    Fragment expandOps(ArrayList arrayList0, Fragment fragment0) {
        for(int v = 0; v < this.mOps.size(); ++v) {
            Op backStackRecord$Op0 = (Op)this.mOps.get(v);
            switch(backStackRecord$Op0.cmd) {
                case 2: {
                    Fragment fragment1 = backStackRecord$Op0.fragment;
                    int v1 = fragment1.mContainerId;
                    int v2 = arrayList0.size() - 1;
                    Fragment fragment2 = fragment0;
                    int v3 = v;
                    boolean z = false;
                    while(v2 >= 0) {
                        Fragment fragment3 = (Fragment)arrayList0.get(v2);
                        if(fragment3.mContainerId == v1) {
                            if(fragment3 == fragment1) {
                                z = true;
                            }
                            else {
                                if(fragment3 == fragment2) {
                                    this.mOps.add(v3, new Op(9, fragment3));
                                    ++v3;
                                    fragment2 = null;
                                }
                                Op backStackRecord$Op1 = new Op(3, fragment3);
                                backStackRecord$Op1.enterAnim = backStackRecord$Op0.enterAnim;
                                backStackRecord$Op1.popEnterAnim = backStackRecord$Op0.popEnterAnim;
                                backStackRecord$Op1.exitAnim = backStackRecord$Op0.exitAnim;
                                backStackRecord$Op1.popExitAnim = backStackRecord$Op0.popExitAnim;
                                this.mOps.add(v3, backStackRecord$Op1);
                                arrayList0.remove(fragment3);
                                ++v3;
                            }
                        }
                        --v2;
                    }
                    if(z) {
                        this.mOps.remove(v3);
                        v = v3 - 1;
                    }
                    else {
                        backStackRecord$Op0.cmd = 1;
                        arrayList0.add(fragment1);
                        v = v3;
                    }
                    fragment0 = fragment2;
                    break;
                }
                case 3: 
                case 6: {
                    arrayList0.remove(backStackRecord$Op0.fragment);
                    if(backStackRecord$Op0.fragment == fragment0) {
                        this.mOps.add(v, new Op(9, backStackRecord$Op0.fragment));
                        ++v;
                        fragment0 = null;
                    }
                    break;
                }
                case 1: 
                case 7: {
                    arrayList0.add(backStackRecord$Op0.fragment);
                    break;
                }
                case 8: {
                    this.mOps.add(v, new Op(9, fragment0));
                    ++v;
                    fragment0 = backStackRecord$Op0.fragment;
                }
            }
        }
        return fragment0;
    }

    @Override  // androidx.fragment.app.FragmentManagerImpl$OpGenerator
    public boolean generateOps(ArrayList arrayList0, ArrayList arrayList1) {
        if(FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList0.add(this);
        arrayList1.add(Boolean.FALSE);
        if(this.mAddToBackStack) {
            this.mManager.addBackStackState(this);
        }
        return true;
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    @Nullable
    public CharSequence getBreadCrumbShortTitle() {
        return this.mBreadCrumbShortTitleRes == 0 ? this.mBreadCrumbShortTitleText : this.mManager.mHost.getContext().getText(this.mBreadCrumbShortTitleRes);
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    @Nullable
    public CharSequence getBreadCrumbTitle() {
        return this.mBreadCrumbTitleRes == 0 ? this.mBreadCrumbTitleText : this.mManager.mHost.getContext().getText(this.mBreadCrumbTitleRes);
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    public int getId() {
        return this.mIndex;
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    @Nullable
    public String getName() {
        return this.mName;
    }

    public int getTransition() {
        return this.mTransition;
    }

    public int getTransitionStyle() {
        return this.mTransitionStyle;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction hide(Fragment fragment0) {
        this.addOp(new Op(4, fragment0));
        return this;
    }

    boolean interactsWith(int v) {
        int v1 = this.mOps.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            Op backStackRecord$Op0 = (Op)this.mOps.get(v2);
            int v3 = backStackRecord$Op0.fragment == null ? 0 : backStackRecord$Op0.fragment.mContainerId;
            if(v3 != 0 && v3 == v) {
                return true;
            }
        }
        return false;
    }

    boolean interactsWith(ArrayList arrayList0, int v, int v1) {
        if(v1 == v) {
            return false;
        }
        int v2 = this.mOps.size();
        int v4 = -1;
        for(int v3 = 0; v3 < v2; ++v3) {
            Op backStackRecord$Op0 = (Op)this.mOps.get(v3);
            int v5 = backStackRecord$Op0.fragment == null ? 0 : backStackRecord$Op0.fragment.mContainerId;
            if(v5 != 0 && v5 != v4) {
                for(int v6 = v; v6 < v1; ++v6) {
                    BackStackRecord backStackRecord0 = (BackStackRecord)arrayList0.get(v6);
                    int v7 = backStackRecord0.mOps.size();
                    for(int v8 = 0; v8 < v7; ++v8) {
                        Op backStackRecord$Op1 = (Op)backStackRecord0.mOps.get(v8);
                        if((backStackRecord$Op1.fragment == null ? 0 : backStackRecord$Op1.fragment.mContainerId) == v5) {
                            return true;
                        }
                    }
                }
                v4 = v5;
            }
        }
        return false;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }

    // 去混淆评级： 低(40)
    private static boolean isFragmentPostponed(Op backStackRecord$Op0) {
        return backStackRecord$Op0.fragment != null && backStackRecord$Op0.fragment.mAdded && backStackRecord$Op0.fragment.mView != null && !backStackRecord$Op0.fragment.mDetached && !backStackRecord$Op0.fragment.mHidden && backStackRecord$Op0.fragment.isPostponed();
    }

    boolean isPostponed() {
        for(int v = 0; v < this.mOps.size(); ++v) {
            if(BackStackRecord.isFragmentPostponed(((Op)this.mOps.get(v)))) {
                return true;
            }
        }
        return false;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction remove(Fragment fragment0) {
        this.addOp(new Op(3, fragment0));
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction replace(int v, Fragment fragment0) {
        return this.replace(v, fragment0, null);
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction replace(int v, Fragment fragment0, @Nullable String s) {
        if(v == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        this.doAddOp(v, fragment0, s, 2);
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction runOnCommit(Runnable runnable0) {
        if(runnable0 == null) {
            throw new IllegalArgumentException("runnable cannot be null");
        }
        this.disallowAddToBackStack();
        if(this.mCommitRunnables == null) {
            this.mCommitRunnables = new ArrayList();
        }
        this.mCommitRunnables.add(runnable0);
        return this;
    }

    public void runOnCommitRunnables() {
        ArrayList arrayList0 = this.mCommitRunnables;
        if(arrayList0 != null) {
            int v1 = arrayList0.size();
            for(int v = 0; v < v1; ++v) {
                ((Runnable)this.mCommitRunnables.get(v)).run();
            }
            this.mCommitRunnables = null;
        }
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setAllowOptimization(boolean z) {
        return this.setReorderingAllowed(z);
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setBreadCrumbShortTitle(int v) {
        this.mBreadCrumbShortTitleRes = v;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setBreadCrumbShortTitle(@Nullable CharSequence charSequence0) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = charSequence0;
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setBreadCrumbTitle(int v) {
        this.mBreadCrumbTitleRes = v;
        this.mBreadCrumbTitleText = null;
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setBreadCrumbTitle(@Nullable CharSequence charSequence0) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = charSequence0;
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setCustomAnimations(int v, int v1) {
        return this.setCustomAnimations(v, v1, 0, 0);
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setCustomAnimations(int v, int v1, int v2, int v3) {
        this.mEnterAnim = v;
        this.mExitAnim = v1;
        this.mPopEnterAnim = v2;
        this.mPopExitAnim = v3;
        return this;
    }

    void setOnStartPostponedListener(OnStartEnterTransitionListener fragment$OnStartEnterTransitionListener0) {
        for(int v = 0; v < this.mOps.size(); ++v) {
            Op backStackRecord$Op0 = (Op)this.mOps.get(v);
            if(BackStackRecord.isFragmentPostponed(backStackRecord$Op0)) {
                backStackRecord$Op0.fragment.setOnStartEnterTransitionListener(fragment$OnStartEnterTransitionListener0);
            }
        }
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment fragment0) {
        this.addOp(new Op(8, fragment0));
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setReorderingAllowed(boolean z) {
        this.mReorderingAllowed = z;
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setTransition(int v) {
        this.mTransition = v;
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setTransitionStyle(int v) {
        this.mTransitionStyle = v;
        return this;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction show(Fragment fragment0) {
        this.addOp(new Op(5, fragment0));
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(0x80);
        stringBuilder0.append("BackStackEntry{");
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(this)));
        if(this.mIndex >= 0) {
            stringBuilder0.append(" #");
            stringBuilder0.append(this.mIndex);
        }
        if(this.mName != null) {
            stringBuilder0.append(" ");
            stringBuilder0.append(this.mName);
        }
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }

    Fragment trackAddedFragmentsInPop(ArrayList arrayList0, Fragment fragment0) {
        for(int v = 0; v < this.mOps.size(); ++v) {
            Op backStackRecord$Op0 = (Op)this.mOps.get(v);
            switch(backStackRecord$Op0.cmd) {
                case 3: 
                case 6: {
                    arrayList0.add(backStackRecord$Op0.fragment);
                    break;
                }
                case 1: 
                case 7: {
                    arrayList0.remove(backStackRecord$Op0.fragment);
                    break;
                }
                case 8: {
                    fragment0 = null;
                    break;
                }
                case 9: {
                    fragment0 = backStackRecord$Op0.fragment;
                }
            }
        }
        return fragment0;
    }
}

