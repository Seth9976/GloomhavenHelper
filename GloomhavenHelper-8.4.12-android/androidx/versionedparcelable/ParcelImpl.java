package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY})
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    private final VersionedParcelable mParcel;

    static {
        ParcelImpl.CREATOR = new Parcelable.Creator() {
            public ParcelImpl createFromParcel(Parcel parcel0) {
                return new ParcelImpl(parcel0);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public ParcelImpl[] newArray(int v) {
                return new ParcelImpl[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    protected ParcelImpl(Parcel parcel0) {
        this.mParcel = new VersionedParcelParcel(parcel0).readVersionedParcelable();
    }

    public ParcelImpl(VersionedParcelable versionedParcelable0) {
        this.mParcel = versionedParcelable0;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VersionedParcelable getVersionedParcel() {
        return this.mParcel;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        new VersionedParcelParcel(parcel0).writeVersionedParcelable(this.mParcel);
    }
}

