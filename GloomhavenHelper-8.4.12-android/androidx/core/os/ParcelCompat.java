package androidx.core.os;

import android.os.Parcel;

public final class ParcelCompat {
    public static boolean readBoolean(Parcel parcel0) {
        return parcel0.readInt() != 0;
    }

    public static void writeBoolean(Parcel parcel0, boolean z) {
        parcel0.writeInt(((int)z));
    }
}

