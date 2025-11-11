package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "GassRequestParcelCreator")
public final class zzdjv extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 2)
    private final String packageName;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(id = 3)
    private final String zzgxu;

    static {
        zzdjv.CREATOR = new zzdju();
    }

    @Constructor
    zzdjv(@Param(id = 1) int v, @Param(id = 2) String s, @Param(id = 3) String s1) {
        this.versionCode = v;
        this.packageName = s;
        this.zzgxu = s1;
    }

    public zzdjv(String s, String s1) {
        this(1, s, s1);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel0, 2, this.packageName, false);
        SafeParcelWriter.writeString(parcel0, 3, this.zzgxu, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

