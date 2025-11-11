package androidx.customview.view;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class AbsSavedState implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    public static final AbsSavedState EMPTY_STATE;
    private final Parcelable mSuperState;

    static {
        AbsSavedState.EMPTY_STATE = new AbsSavedState() {
            {
                super(null);
            }
        };
        AbsSavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
            public AbsSavedState createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0, null);
            }

            public AbsSavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                if(parcel0.readParcelable(classLoader0) != null) {
                    throw new IllegalStateException("superState must be null");
                }
                return AbsSavedState.EMPTY_STATE;
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            @Override  // android.os.Parcelable$ClassLoaderCreator
            public Object createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                return this.createFromParcel(parcel0, classLoader0);
            }

            public AbsSavedState[] newArray(int v) {
                return new AbsSavedState[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    private AbsSavedState() {
        this.mSuperState = null;
    }

    protected AbsSavedState(@NonNull Parcel parcel0) {
        this(parcel0, null);
    }

    protected AbsSavedState(@NonNull Parcel parcel0, @Nullable ClassLoader classLoader0) {
        Parcelable parcelable0 = parcel0.readParcelable(classLoader0);
        if(parcelable0 == null) {
            parcelable0 = AbsSavedState.EMPTY_STATE;
        }
        this.mSuperState = parcelable0;
    }

    protected AbsSavedState(@NonNull Parcelable parcelable0) {
        if(parcelable0 == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        if(parcelable0 == AbsSavedState.EMPTY_STATE) {
            parcelable0 = null;
        }
        this.mSuperState = parcelable0;
    }

    AbsSavedState(androidx.customview.view.AbsSavedState.1 absSavedState$10) {
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public final Parcelable getSuperState() {
        return this.mSuperState;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeParcelable(this.mSuperState, v);
    }
}

