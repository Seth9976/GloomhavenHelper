package androidx.versionedparcelable;

import android.os.Parcelable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.io.InputStream;
import java.io.OutputStream;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ParcelUtils {
    public static VersionedParcelable fromInputStream(InputStream inputStream0) {
        return new VersionedParcelStream(inputStream0, null).readVersionedParcelable();
    }

    public static VersionedParcelable fromParcelable(Parcelable parcelable0) {
        if(!(parcelable0 instanceof ParcelImpl)) {
            throw new IllegalArgumentException("Invalid parcel");
        }
        return ((ParcelImpl)parcelable0).getVersionedParcel();
    }

    public static void toOutputStream(VersionedParcelable versionedParcelable0, OutputStream outputStream0) {
        VersionedParcelStream versionedParcelStream0 = new VersionedParcelStream(null, outputStream0);
        versionedParcelStream0.writeVersionedParcelable(versionedParcelable0);
        versionedParcelStream0.closeField();
    }

    public static Parcelable toParcelable(VersionedParcelable versionedParcelable0) {
        return new ParcelImpl(versionedParcelable0);
    }
}

