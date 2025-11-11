package android.support.v4.graphics.drawable;

import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({Scope.LIBRARY})
public final class IconCompatParcelizer extends androidx.core.graphics.drawable.IconCompatParcelizer {
    @Override  // androidx.core.graphics.drawable.IconCompatParcelizer
    public static IconCompat read(VersionedParcel versionedParcel0) {
        return androidx.core.graphics.drawable.IconCompatParcelizer.read(versionedParcel0);
    }

    @Override  // androidx.core.graphics.drawable.IconCompatParcelizer
    public static void write(IconCompat iconCompat0, VersionedParcel versionedParcel0) {
        androidx.core.graphics.drawable.IconCompatParcelizer.write(iconCompat0, versionedParcel0);
    }
}

