package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.Log;
import androidx.lifecycle.ViewModelStore;

final class FragmentState implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    final int mIndex;
    Fragment mInstance;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;

    static {
        FragmentState.CREATOR = new Parcelable.Creator() {
            public FragmentState createFromParcel(Parcel parcel0) {
                return new FragmentState(parcel0);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public FragmentState[] newArray(int v) {
                return new FragmentState[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    FragmentState(Parcel parcel0) {
        this.mClassName = parcel0.readString();
        this.mIndex = parcel0.readInt();
        boolean z = true;
        this.mFromLayout = parcel0.readInt() != 0;
        this.mFragmentId = parcel0.readInt();
        this.mContainerId = parcel0.readInt();
        this.mTag = parcel0.readString();
        this.mRetainInstance = parcel0.readInt() != 0;
        this.mDetached = parcel0.readInt() != 0;
        this.mArguments = parcel0.readBundle();
        if(parcel0.readInt() == 0) {
            z = false;
        }
        this.mHidden = z;
        this.mSavedFragmentState = parcel0.readBundle();
    }

    FragmentState(Fragment fragment0) {
        this.mClassName = fragment0.getClass().getName();
        this.mIndex = fragment0.mIndex;
        this.mFromLayout = fragment0.mFromLayout;
        this.mFragmentId = fragment0.mFragmentId;
        this.mContainerId = fragment0.mContainerId;
        this.mTag = fragment0.mTag;
        this.mRetainInstance = fragment0.mRetainInstance;
        this.mDetached = fragment0.mDetached;
        this.mArguments = fragment0.mArguments;
        this.mHidden = fragment0.mHidden;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Fragment instantiate(FragmentHostCallback fragmentHostCallback0, FragmentContainer fragmentContainer0, Fragment fragment0, FragmentManagerNonConfig fragmentManagerNonConfig0, ViewModelStore viewModelStore0) {
        if(this.mInstance == null) {
            Context context0 = fragmentHostCallback0.getContext();
            Bundle bundle0 = this.mArguments;
            if(bundle0 != null) {
                bundle0.setClassLoader(context0.getClassLoader());
            }
            this.mInstance = fragmentContainer0 == null ? Fragment.instantiate(context0, this.mClassName, this.mArguments) : fragmentContainer0.instantiate(context0, this.mClassName, this.mArguments);
            Bundle bundle1 = this.mSavedFragmentState;
            if(bundle1 != null) {
                bundle1.setClassLoader(context0.getClassLoader());
                this.mInstance.mSavedFragmentState = this.mSavedFragmentState;
            }
            this.mInstance.setIndex(this.mIndex, fragment0);
            this.mInstance.mFromLayout = this.mFromLayout;
            this.mInstance.mRestored = true;
            this.mInstance.mFragmentId = this.mFragmentId;
            this.mInstance.mContainerId = this.mContainerId;
            this.mInstance.mTag = this.mTag;
            this.mInstance.mRetainInstance = this.mRetainInstance;
            this.mInstance.mDetached = this.mDetached;
            this.mInstance.mHidden = this.mHidden;
            this.mInstance.mFragmentManager = fragmentHostCallback0.mFragmentManager;
            if(FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Instantiated fragment " + this.mInstance);
            }
        }
        this.mInstance.mChildNonConfig = fragmentManagerNonConfig0;
        this.mInstance.mViewModelStore = viewModelStore0;
        return this.mInstance;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeString(this.mClassName);
        parcel0.writeInt(this.mIndex);
        parcel0.writeInt(((int)this.mFromLayout));
        parcel0.writeInt(this.mFragmentId);
        parcel0.writeInt(this.mContainerId);
        parcel0.writeString(this.mTag);
        parcel0.writeInt(((int)this.mRetainInstance));
        parcel0.writeInt(((int)this.mDetached));
        parcel0.writeBundle(this.mArguments);
        parcel0.writeInt(((int)this.mHidden));
        parcel0.writeBundle(this.mSavedFragmentState);
    }
}

