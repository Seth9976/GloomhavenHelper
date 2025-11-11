package androidx.core.os;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;

@Deprecated
public final class ParcelableCompat {
    static class ParcelableCompatCreatorHoneycombMR2 implements Parcelable.ClassLoaderCreator {
        private final ParcelableCompatCreatorCallbacks mCallbacks;

        ParcelableCompatCreatorHoneycombMR2(ParcelableCompatCreatorCallbacks parcelableCompatCreatorCallbacks0) {
            this.mCallbacks = parcelableCompatCreatorCallbacks0;
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.mCallbacks.createFromParcel(parcel0, null);
        }

        @Override  // android.os.Parcelable$ClassLoaderCreator
        public Object createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
            return this.mCallbacks.createFromParcel(parcel0, classLoader0);
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.mCallbacks.newArray(v);
        }
    }

    @Deprecated
    public static Parcelable.Creator newCreator(ParcelableCompatCreatorCallbacks parcelableCompatCreatorCallbacks0) {
        return new ParcelableCompatCreatorHoneycombMR2(parcelableCompatCreatorCallbacks0);
    }
}

