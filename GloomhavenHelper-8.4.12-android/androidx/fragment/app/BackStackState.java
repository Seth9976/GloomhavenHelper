package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

final class BackStackState implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int[] mOps;
    final boolean mReorderingAllowed;
    final ArrayList mSharedElementSourceNames;
    final ArrayList mSharedElementTargetNames;
    final int mTransition;
    final int mTransitionStyle;

    static {
        BackStackState.CREATOR = new Parcelable.Creator() {
            public BackStackState createFromParcel(Parcel parcel0) {
                return new BackStackState(parcel0);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public BackStackState[] newArray(int v) {
                return new BackStackState[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    public BackStackState(Parcel parcel0) {
        this.mOps = parcel0.createIntArray();
        this.mTransition = parcel0.readInt();
        this.mTransitionStyle = parcel0.readInt();
        this.mName = parcel0.readString();
        this.mIndex = parcel0.readInt();
        this.mBreadCrumbTitleRes = parcel0.readInt();
        this.mBreadCrumbTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel0);
        this.mBreadCrumbShortTitleRes = parcel0.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel0);
        this.mSharedElementSourceNames = parcel0.createStringArrayList();
        this.mSharedElementTargetNames = parcel0.createStringArrayList();
        this.mReorderingAllowed = parcel0.readInt() != 0;
    }

    public BackStackState(BackStackRecord backStackRecord0) {
        int v = backStackRecord0.mOps.size();
        this.mOps = new int[v * 6];
        if(!backStackRecord0.mAddToBackStack) {
            throw new IllegalStateException("Not on back stack");
        }
        int v1 = 0;
        for(int v2 = 0; v1 < v; v2 += 6) {
            Op backStackRecord$Op0 = (Op)backStackRecord0.mOps.get(v1);
            this.mOps[v2] = backStackRecord$Op0.cmd;
            this.mOps[v2 + 1] = backStackRecord$Op0.fragment == null ? -1 : backStackRecord$Op0.fragment.mIndex;
            this.mOps[v2 + 2] = backStackRecord$Op0.enterAnim;
            this.mOps[v2 + 3] = backStackRecord$Op0.exitAnim;
            this.mOps[v2 + 4] = backStackRecord$Op0.popEnterAnim;
            this.mOps[v2 + 5] = backStackRecord$Op0.popExitAnim;
            ++v1;
        }
        this.mTransition = backStackRecord0.mTransition;
        this.mTransitionStyle = backStackRecord0.mTransitionStyle;
        this.mName = backStackRecord0.mName;
        this.mIndex = backStackRecord0.mIndex;
        this.mBreadCrumbTitleRes = backStackRecord0.mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = backStackRecord0.mBreadCrumbTitleText;
        this.mBreadCrumbShortTitleRes = backStackRecord0.mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = backStackRecord0.mBreadCrumbShortTitleText;
        this.mSharedElementSourceNames = backStackRecord0.mSharedElementSourceNames;
        this.mSharedElementTargetNames = backStackRecord0.mSharedElementTargetNames;
        this.mReorderingAllowed = backStackRecord0.mReorderingAllowed;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BackStackRecord instantiate(FragmentManagerImpl fragmentManagerImpl0) {
        BackStackRecord backStackRecord0 = new BackStackRecord(fragmentManagerImpl0);
        int v1 = 0;
        for(int v = 0; v < this.mOps.length; v += 6) {
            Op backStackRecord$Op0 = new Op();
            backStackRecord$Op0.cmd = this.mOps[v];
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Instantiate " + backStackRecord0 + " op #" + v1 + " base fragment #" + this.mOps[v + 1]);
            }
            int v2 = this.mOps[v + 1];
            backStackRecord$Op0.fragment = v2 >= 0 ? ((Fragment)fragmentManagerImpl0.mActive.get(v2)) : null;
            backStackRecord$Op0.enterAnim = this.mOps[v + 2];
            backStackRecord$Op0.exitAnim = this.mOps[v + 3];
            backStackRecord$Op0.popEnterAnim = this.mOps[v + 4];
            backStackRecord$Op0.popExitAnim = this.mOps[v + 5];
            backStackRecord0.mEnterAnim = backStackRecord$Op0.enterAnim;
            backStackRecord0.mExitAnim = backStackRecord$Op0.exitAnim;
            backStackRecord0.mPopEnterAnim = backStackRecord$Op0.popEnterAnim;
            backStackRecord0.mPopExitAnim = backStackRecord$Op0.popExitAnim;
            backStackRecord0.addOp(backStackRecord$Op0);
            ++v1;
        }
        backStackRecord0.mTransition = this.mTransition;
        backStackRecord0.mTransitionStyle = this.mTransitionStyle;
        backStackRecord0.mName = this.mName;
        backStackRecord0.mIndex = this.mIndex;
        backStackRecord0.mAddToBackStack = true;
        backStackRecord0.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
        backStackRecord0.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
        backStackRecord0.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
        backStackRecord0.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
        backStackRecord0.mSharedElementSourceNames = this.mSharedElementSourceNames;
        backStackRecord0.mSharedElementTargetNames = this.mSharedElementTargetNames;
        backStackRecord0.mReorderingAllowed = this.mReorderingAllowed;
        backStackRecord0.bumpBackStackNesting(1);
        return backStackRecord0;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeIntArray(this.mOps);
        parcel0.writeInt(this.mTransition);
        parcel0.writeInt(this.mTransitionStyle);
        parcel0.writeString(this.mName);
        parcel0.writeInt(this.mIndex);
        parcel0.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, parcel0, 0);
        parcel0.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, parcel0, 0);
        parcel0.writeStringList(this.mSharedElementSourceNames);
        parcel0.writeStringList(this.mSharedElementTargetNames);
        parcel0.writeInt(((int)this.mReorderingAllowed));
    }
}

