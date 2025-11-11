package androidx.core.provider;

import android.util.Base64;
import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.util.List;

public final class FontRequest {
    private final List mCertificates;
    private final int mCertificatesArray;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;

    public FontRequest(@NonNull String s, @NonNull String s1, @NonNull String s2, @ArrayRes int v) {
        this.mProviderAuthority = (String)Preconditions.checkNotNull(s);
        this.mProviderPackage = (String)Preconditions.checkNotNull(s1);
        this.mQuery = (String)Preconditions.checkNotNull(s2);
        this.mCertificates = null;
        Preconditions.checkArgument(v != 0);
        this.mCertificatesArray = v;
        this.mIdentifier = this.mProviderAuthority + "-" + this.mProviderPackage + "-" + this.mQuery;
    }

    public FontRequest(@NonNull String s, @NonNull String s1, @NonNull String s2, @NonNull List list0) {
        this.mProviderAuthority = (String)Preconditions.checkNotNull(s);
        this.mProviderPackage = (String)Preconditions.checkNotNull(s1);
        this.mQuery = (String)Preconditions.checkNotNull(s2);
        this.mCertificates = (List)Preconditions.checkNotNull(list0);
        this.mCertificatesArray = 0;
        this.mIdentifier = this.mProviderAuthority + "-" + this.mProviderPackage + "-" + this.mQuery;
    }

    @Nullable
    public List getCertificates() {
        return this.mCertificates;
    }

    @ArrayRes
    public int getCertificatesArrayResId() {
        return this.mCertificatesArray;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public String getIdentifier() {
        return this.mIdentifier;
    }

    @NonNull
    public String getProviderAuthority() {
        return this.mProviderAuthority;
    }

    @NonNull
    public String getProviderPackage() {
        return this.mProviderPackage;
    }

    @NonNull
    public String getQuery() {
        return this.mQuery;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("FontRequest {mProviderAuthority: " + this.mProviderAuthority + ", mProviderPackage: " + this.mProviderPackage + ", mQuery: " + this.mQuery + ", mCertificates:");
        for(int v = 0; v < this.mCertificates.size(); ++v) {
            stringBuilder0.append(" [");
            List list0 = (List)this.mCertificates.get(v);
            for(int v1 = 0; v1 < list0.size(); ++v1) {
                stringBuilder0.append(" \"");
                stringBuilder0.append(Base64.encodeToString(((byte[])list0.get(v1)), 0));
                stringBuilder0.append("\"");
            }
            stringBuilder0.append(" ]");
        }
        stringBuilder0.append("}");
        stringBuilder0.append("mCertificatesArray: " + this.mCertificatesArray);
        return stringBuilder0.toString();
    }
}

