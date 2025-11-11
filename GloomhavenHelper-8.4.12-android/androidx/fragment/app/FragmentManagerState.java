package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    FragmentState[] mActive;
    int[] mAdded;
    BackStackState[] mBackStack;
    int mNextFragmentIndex;
    int mPrimaryNavActiveIndex;

    static {
        FragmentManagerState.CREATOR = new Parcelable.Creator() {
            public FragmentManagerState createFromParcel(Parcel parcel0) {
                return new FragmentManagerState(parcel0);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public FragmentManagerState[] newArray(int v) {
                return new FragmentManagerState[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    public FragmentManagerState() {
        this.mPrimaryNavActiveIndex = -1;
    }

    public FragmentManagerState(Parcel parcel0) {
        this.mPrimaryNavActiveIndex = -1;
        this.mActive = (FragmentState[])parcel0.createTypedArray(FragmentState.CREATOR);
        this.mAdded = parcel0.createIntArray();
        this.mBackStack = (BackStackState[])parcel0.createTypedArray(BackStackState.CREATOR);
        this.mPrimaryNavActiveIndex = parcel0.readInt();
        this.mNextFragmentIndex = parcel0.readInt();
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeTypedArray(this.mActive, v);
        parcel0.writeIntArray(this.mAdded);
        parcel0.writeTypedArray(this.mBackStack, v);
        parcel0.writeInt(this.mPrimaryNavActiveIndex);
        parcel0.writeInt(this.mNextFragmentIndex);
    }
}

