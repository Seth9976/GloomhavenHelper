package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({Scope.LIBRARY})
public class IconCompatParcelizer {
    public static IconCompat read(VersionedParcel versionedParcel0) {
        IconCompat iconCompat0 = new IconCompat();
        iconCompat0.mType = versionedParcel0.readInt(iconCompat0.mType, 1);
        iconCompat0.mData = versionedParcel0.readByteArray(iconCompat0.mData, 2);
        iconCompat0.mParcelable = versionedParcel0.readParcelable(iconCompat0.mParcelable, 3);
        iconCompat0.mInt1 = versionedParcel0.readInt(iconCompat0.mInt1, 4);
        iconCompat0.mInt2 = versionedParcel0.readInt(iconCompat0.mInt2, 5);
        iconCompat0.mTintList = (ColorStateList)versionedParcel0.readParcelable(iconCompat0.mTintList, 6);
        iconCompat0.mTintModeStr = versionedParcel0.readString(iconCompat0.mTintModeStr, 7);
        iconCompat0.onPostParceling();
        return iconCompat0;
    }

    public static void write(IconCompat iconCompat0, VersionedParcel versionedParcel0) {
        versionedParcel0.setSerializationFlags(true, true);
        iconCompat0.onPreParceling(versionedParcel0.isStream());
        versionedParcel0.writeInt(iconCompat0.mType, 1);
        versionedParcel0.writeByteArray(iconCompat0.mData, 2);
        versionedParcel0.writeParcelable(iconCompat0.mParcelable, 3);
        versionedParcel0.writeInt(iconCompat0.mInt1, 4);
        versionedParcel0.writeInt(iconCompat0.mInt2, 5);
        versionedParcel0.writeParcelable(iconCompat0.mTintList, 6);
        versionedParcel0.writeString(iconCompat0.mTintModeStr, 7);
    }
}

