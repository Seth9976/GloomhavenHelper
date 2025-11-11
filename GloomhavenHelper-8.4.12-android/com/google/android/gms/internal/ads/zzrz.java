package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.List;

@Class(creator = "CacheOfferingCreator")
@Reserved({1})
public final class zzrz extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Nullable
    @Field(id = 2)
    public final String url;
    @Field(id = 3)
    private final long zzbrs;
    @Field(id = 4)
    private final String zzbrt;
    @Field(id = 5)
    private final String zzbru;
    @Field(id = 6)
    private final String zzbrv;
    @Field(id = 7)
    private final Bundle zzbrw;
    @Field(id = 8)
    public final boolean zzbrx;
    @Field(id = 9)
    public long zzbry;

    static {
        zzrz.CREATOR = new zzsc();
    }

    @Constructor
    zzrz(@Nullable @Param(id = 2) String s, @Param(id = 3) long v, @Param(id = 4) String s1, @Param(id = 5) String s2, @Param(id = 6) String s3, @Param(id = 7) Bundle bundle0, @Param(id = 8) boolean z, @Param(id = 9) long v1) {
        this.url = s;
        this.zzbrs = v;
        if(s1 == null) {
            s1 = "";
        }
        this.zzbrt = s1;
        if(s2 == null) {
            s2 = "";
        }
        this.zzbru = s2;
        if(s3 == null) {
            s3 = "";
        }
        this.zzbrv = s3;
        if(bundle0 == null) {
            bundle0 = new Bundle();
        }
        this.zzbrw = bundle0;
        this.zzbrx = z;
        this.zzbry = v1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.url, false);
        SafeParcelWriter.writeLong(parcel0, 3, this.zzbrs);
        SafeParcelWriter.writeString(parcel0, 4, this.zzbrt, false);
        SafeParcelWriter.writeString(parcel0, 5, this.zzbru, false);
        SafeParcelWriter.writeString(parcel0, 6, this.zzbrv, false);
        SafeParcelWriter.writeBundle(parcel0, 7, this.zzbrw, false);
        SafeParcelWriter.writeBoolean(parcel0, 8, this.zzbrx);
        SafeParcelWriter.writeLong(parcel0, 9, this.zzbry);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    @Nullable
    public static zzrz zzbz(String s) {
        return zzrz.zzd(Uri.parse(s));
    }

    @Nullable
    public static zzrz zzd(Uri uri0) {
        try {
            if(!"gcache".equals(uri0.getScheme())) {
                return null;
            }
            List list0 = uri0.getPathSegments();
            if(list0.size() != 2) {
                zzawf.zzfa(("Expected 2 path parts for namespace and id, found :" + list0.size()));
                return null;
            }
            Object object0 = list0.get(0);
            Object object1 = list0.get(1);
            String s = uri0.getHost();
            String s1 = uri0.getQueryParameter("url");
            boolean z = "1".equals(uri0.getQueryParameter("read_only"));
            String s2 = uri0.getQueryParameter("expiration");
            long v = s2 == null ? 0L : Long.parseLong(s2);
            Bundle bundle0 = new Bundle();
            for(Object object2: uri0.getQueryParameterNames()) {
                String s3 = (String)object2;
                if(s3.startsWith("tag.")) {
                    bundle0.putString(s3.substring(4), uri0.getQueryParameter(s3));
                }
            }
            return new zzrz(s1, v, s, ((String)object0), ((String)object1), bundle0, z, 0L);
        }
        catch(NullPointerException | NumberFormatException nullPointerException0) {
            zzawf.zzd("Unable to parse Uri into cache offering.", nullPointerException0);
            return null;
        }
    }
}

