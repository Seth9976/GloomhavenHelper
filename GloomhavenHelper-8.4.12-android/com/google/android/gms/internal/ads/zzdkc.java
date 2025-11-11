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

@Class(creator = "ProgramRequestCreator")
public final class zzdkc extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(id = 3)
    private final String zzgwx;
    @Field(id = 4)
    private final String zzgwy;
    @Field(id = 2)
    private final int zzgwz;

    static {
        zzdkc.CREATOR = new zzdkf();
    }

    @Constructor
    zzdkc(@Param(id = 1) int v, @Param(id = 2) int v1, @Param(id = 3) String s, @Param(id = 4) String s1) {
        this.versionCode = v;
        this.zzgwz = v1;
        this.zzgwx = s;
        this.zzgwy = s1;
    }

    public zzdkc(int v, String s, String s1) {
        this(1, v, s, s1);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel0, 2, this.zzgwz);
        SafeParcelWriter.writeString(parcel0, 3, this.zzgwx, false);
        SafeParcelWriter.writeString(parcel0, 4, this.zzgwy, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

