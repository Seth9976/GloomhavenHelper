package androidx.versionedparcelable;

import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class CustomVersionedParcelable implements VersionedParcelable {
    @RestrictTo({Scope.LIBRARY_GROUP})
    public void onPostParceling() {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void onPreParceling(boolean z) {
    }
}

